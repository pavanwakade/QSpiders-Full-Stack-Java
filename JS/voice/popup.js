document.addEventListener('DOMContentLoaded', function() {
    const startButton = document.getElementById('start-btn');
    const stopButton = document.getElementById('stop-btn');
    const statusText = document.getElementById('status');
    const languageSelect = document.getElementById('language-select');
    
    // Check if we need to verify cursor position first
    let checkCursorPosition = true;
    
    // Load saved language preference and settings
    chrome.storage.local.get(['voiceLanguage', 'checkCursorPosition'], function(data) {
      if (data.voiceLanguage) {
        languageSelect.value = data.voiceLanguage;
      }
      if (data.checkCursorPosition !== undefined) {
        checkCursorPosition = data.checkCursorPosition;
      }
    });
    
    // Save language preference when changed
    languageSelect.addEventListener('change', function() {
      chrome.storage.local.set({ 'voiceLanguage': languageSelect.value });
    });
    
    // Add checkbox for cursor position checking
    const cursorCheckbox = document.getElementById('cursor-check');
    
    // Set initial state from storage
    cursorCheckbox.checked = checkCursorPosition;
    
    // Save preference when changed
    cursorCheckbox.addEventListener('change', function() {
      checkCursorPosition = cursorCheckbox.checked;
      chrome.storage.local.set({ 'checkCursorPosition': checkCursorPosition });
    });
    
    startButton.addEventListener('click', function() {
      startButton.disabled = true;
      startButton.classList.add('recording');
      stopButton.disabled = false;
      statusText.textContent = 'Checking cursor position and starting voice typing...';
      
      // Send message to content script to check cursor and start voice recognition
      chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
        chrome.tabs.sendMessage(tabs[0].id, {
          action: 'startVoiceTyping',
          language: languageSelect.value,
          checkCursor: checkCursorPosition
        });
      });
      
      // Update status after a short delay
      setTimeout(() => {
        if (startButton.classList.contains('recording')) {
          statusText.textContent = 'Voice typing active. Speak now...';
        }
      }, 1000);
    });
    
    stopButton.addEventListener('click', function() {
      stopVoiceTyping();
    });
    
    function stopVoiceTyping() {
      startButton.disabled = false;
      startButton.classList.remove('recording');
      stopButton.disabled = true;
      statusText.textContent = 'Voice typing stopped.';
      
      // Send message to content script to stop voice recognition
      chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
        chrome.tabs.sendMessage(tabs[0].id, {
          action: 'stopVoiceTyping'
        });
      });
    }
    
    // Listen for messages from content script
    chrome.runtime.onMessage.addListener(function(message) {
      if (message.action === 'voiceTypingStopped') {
        stopVoiceTyping();
      }
    });
  });