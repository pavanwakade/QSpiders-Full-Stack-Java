// Content script for voice typing functionality
let recognition = null;
let activeElement = null;
let cursorPosition = null;

// Listen for messages from the popup
chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
  if (request.action === 'startVoiceTyping') {
    startVoiceRecognition(request.language);
  } else if (request.action === 'stopVoiceTyping') {
    stopVoiceRecognition();
  }
});

// Start speech recognition
function startVoiceRecognition(language) {
  // First, check and store the cursor position
  checkCursorPosition();
  
  // If no valid cursor position was found, notify and exit
  if (!activeElement || 
      (activeElement.tagName !== 'INPUT' && 
       activeElement.tagName !== 'TEXTAREA' && 
       !activeElement.isContentEditable)) {
    // If no valid element is focused, show a notification
    chrome.runtime.sendMessage({
      action: 'showNotification',
      message: 'Please place cursor in an input field first'
    });
    chrome.runtime.sendMessage({ action: 'voiceTypingStopped' });
    return;
  }
  
  // Create speech recognition instance if needed
  if (!recognition) {
    recognition = new webkitSpeechRecognition() || new SpeechRecognition();
  }
  
  // Configure recognition
  recognition.continuous = true;
  recognition.interimResults = true;
  recognition.lang = language || 'en-US';
  
  let finalTranscript = '';
  let interimTranscript = '';
  
  // Handle results
  recognition.onresult = function(event) {
    // Recheck cursor position for dynamic environments where it might change
    checkCursorPosition();
    
    interimTranscript = '';
    
    for (let i = event.resultIndex; i < event.results.length; ++i) {
      if (event.results[i].isFinal) {
        finalTranscript += event.results[i][0].transcript;
      } else {
        interimTranscript += event.results[i][0].transcript;
      }
    }
    
    // Insert text based on element type
    if (activeElement.isContentEditable) {
      // For contentEditable elements like Google Docs, rich text editors
      insertTextAtCursor(finalTranscript + interimTranscript);
    } else {
      // For standard form inputs and textareas
      insertTextInField(finalTranscript + interimTranscript);
    }
    
    // Clear the final transcript for the next batch
    if (finalTranscript) {
      finalTranscript = '';
    }
  };
  
  // Handle errors
  recognition.onerror = function(event) {
    console.error('Speech recognition error:', event.error);
    stopVoiceRecognition();
    chrome.runtime.sendMessage({
      action: 'showNotification',
      message: 'Voice recognition error: ' + event.error
    });
  };
  
  // Handle end of recognition
  recognition.onend = function() {
    chrome.runtime.sendMessage({ action: 'voiceTypingStopped' });
  };
  
  // Start recognition
  try {
    recognition.start();
  } catch (e) {
    console.error('Could not start speech recognition:', e);
    chrome.runtime.sendMessage({
      action: 'showNotification',
      message: 'Could not start voice recognition'
    });
    chrome.runtime.sendMessage({ action: 'voiceTypingStopped' });
  }
}

// Stop speech recognition
function stopVoiceRecognition() {
  if (recognition) {
    recognition.stop();
    recognition = null;
  }
}

// Function to check and store the current cursor position
function checkCursorPosition() {
  // Store the currently focused element
  activeElement = document.activeElement;
  
  if (!activeElement) {
    return false;
  }
  
  // For standard input elements
  if (activeElement.tagName === 'INPUT' || activeElement.tagName === 'TEXTAREA') {
    cursorPosition = {
      start: activeElement.selectionStart,
      end: activeElement.selectionEnd
    };
    return true;
  } 
  // For contentEditable elements
  else if (activeElement.isContentEditable) {
    const selection = window.getSelection();
    if (selection.rangeCount > 0) {
      cursorPosition = {
        range: selection.getRangeAt(0).cloneRange()
      };
      return true;
    }
  }
  
  return false;
}

// Insert text for contentEditable elements
function insertTextAtCursor(text) {
  // Handle contentEditable elements
  const selection = window.getSelection();
  let range;
  
  // Use stored range if available, otherwise get current range
  if (cursorPosition && cursorPosition.range) {
    range = cursorPosition.range;
    selection.removeAllRanges();
    selection.addRange(range);
  } else if (selection.rangeCount > 0) {
    range = selection.getRangeAt(0);
  } else {
    // Fallback if no range is available
    range = document.createRange();
    range.selectNodeContents(activeElement);
    range.collapse(false);
    selection.removeAllRanges();
    selection.addRange(range);
  }
  
  range.deleteContents();
  const textNode = document.createTextNode(text);
  range.insertNode(textNode);
  
  // Move cursor to the end
  range.setStartAfter(textNode);
  range.setEndAfter(textNode);
  selection.removeAllRanges();
  selection.addRange(range);
  
  // Update cursor position
  cursorPosition = {
    range: selection.getRangeAt(0).cloneRange()
  };
}

// Insert text for input and textarea elements
function insertTextInField(text) {
  // Use stored cursor position if available
  let start, end;
  
  if (cursorPosition) {
    start = cursorPosition.start;
    end = cursorPosition.end;
  } else {
    start = activeElement.selectionStart;
    end = activeElement.selectionEnd;
  }
  
  const beforeText = activeElement.value.substring(0, start);
  const afterText = activeElement.value.substring(end);
  
  activeElement.value = beforeText + text + afterText;
  
  // Set cursor position after inserted text
  const newPosition = start + text.length;
  activeElement.selectionStart = newPosition;
  activeElement.selectionEnd = newPosition;
  
  // Update stored cursor position
  cursorPosition = {
    start: newPosition,
    end: newPosition
  };
  
  // Trigger input event to notify the page of changes
  const inputEvent = new Event('input', {
    bubbles: true,
    cancelable: true
  });
  activeElement.dispatchEvent(inputEvent);
}