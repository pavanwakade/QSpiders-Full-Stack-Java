// Background script
chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
    if (request.action === 'showNotification') {
      // Create a notification when needed
      chrome.notifications.create({
        type: 'basic',
        title: 'Voice Typing Extension',
        message: request.message || 'Notification from Voice Typing',
        iconUrl: 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0IiBmaWxsPSJub25lIiBzdHJva2U9IiM0Mjg1ZjQiIHN0cm9rZS13aWR0aD0iMiIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIj48cGF0aCBkPSJNMTIgMXYyIi8+PHBhdGggZD0iTTEyIDVoLjAxIi8+PHBhdGggZD0iTTE3IDhoLjAxIi8+PHBhdGggZD0iTTE2IDEyaDAxIi8+PHBhdGggZD0iTTEyIDE5di0yIi8+PHBhdGggZD0iTTEyIDE2SDEiLz48cGF0aCBkPSJNNyA4SDEiLz48cGF0aCBkPSJNOCAxMkgxIi8+PC9zdmc+'
      });
    }
  });