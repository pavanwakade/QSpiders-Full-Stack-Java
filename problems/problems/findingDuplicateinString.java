{
  "name": "Telegram Chatbot with Gemini AI",
  "nodes": [
    {
      "parameters": {
        "httpMethod": "POST",
        "path": "telegram-webhook",
        "responseMode": "responseNode",
        "options": {}
      },
      "id": "webhook-trigger",
      "name": "Webhook Trigger",
      "type": "n8n-nodes-base.webhook",
      "typeVersion": 1,
      "position": [
        240,
        300
      ],
      "webhookId": "telegram-webhook"
    },
    {
      "parameters": {
        "jsCode": "// Extract and validate Telegram message data\nconst telegramData = $input.first().json;\n\n// Check if it's a valid message update\nif (!telegramData || !telegramData.message) {\n  return [];\n}\n\nconst message = telegramData.message;\nconst chatId = message.chat.id;\nconst messageText = message.text;\nconst userId = message.from.id;\nconst username = message.from.username || message.from.first_name || 'Unknown';\n\n// Skip non-text messages\nif (!messageText) {\n  return [{\n    json: {\n      chatId: chatId,\n      response: \"I can only process text messages at the moment. 📝\",\n      skipAI: true\n    }\n  }];\n}\n\n// Handle bot commands\nif (messageText.startsWith('/')) {\n  const command = messageText.split(' ')[0].toLowerCase();\n  \n  let response = '';\n  switch(command) {\n    case '/start':\n      response = `🤖 Hello ${username}! I'm an AI assistant powered by Google Gemini.\\n\\nSend me any message and I'll help you with:\\n• Answering questions\\n• Creative writing\\n• Problem solving\\n• General conversation\\n\\nTry asking me something!`;\n      break;\n    case '/help':\n      response = `ℹ️ **How to use this bot:**\\n\\n• Just send me any text message\\n• I'll respond using Google's Gemini AI\\n• Ask questions, request stories, get explanations\\n• Use /start to see the welcome message\\n\\n**Commands:**\\n/start - Welcome message\\n/help - This help message`;\n      break;\n    default:\n      // Let other commands pass through to AI\n      break;\n  }\n  \n  if (response) {\n    return [{\n      json: {\n        chatId: chatId,\n        response: response,\n        skipAI: true\n      }\n    }];\n  }\n}\n\n// Return structured data for AI processing\nreturn [{\n  json: {\n    chatId: chatId,\n    messageText: messageText,\n    userId: userId,\n    username: username,\n    originalMessage: message,\n    skipAI: false\n  }\n}];"
      },
      "id": "extract-message-data",
      "name": "Extract Message Data",
      "type": "n8n-nodes-base.code",
      "typeVersion": 2,
      "position": [
        460,
        300
      ]
    },
    {
      "parameters": {
        "conditions": {
          "options": {
            "caseSensitive": true,
            "leftValue": "",
            "typeValidation": "strict"
          },
          "conditions": [
            {
              "id": "condition-skip-ai",
              "leftValue": "={{ $json.skipAI }}",
              "rightValue": true,
              "operator": {
                "type": "boolean"
              }
            }
          ],
          "combinator": "and"
        },
        "options": {}
      },
      "id": "check-skip-ai",
      "name": "Check Skip AI",
      "type": "n8n-nodes-base.if",
      "typeVersion": 2,
      "position": [
        680,
        300
      ]
    },
    {
      "parameters": {
        "jsCode": "// Simple rate limiting implementation\nconst userId = $json.userId;\nconst currentTime = Date.now();\n\n// Initialize rate limit storage in workflow static data\nif (!$workflow.staticData.rateLimit) {\n  $workflow.staticData.rateLimit = {};\n}\n\nconst userRateLimit = $workflow.staticData.rateLimit[userId] || { lastRequest: 0, requestCount: 0 };\nconst timeDiff = currentTime - userRateLimit.lastRequest;\n\n// Rate limit: Max 1 request per 2 seconds, 10 requests per minute\nif (timeDiff < 2000) {\n  return [{\n    json: {\n      chatId: $json.chatId,\n      response: \"⏳ Please wait a moment before sending another message.\",\n      rateLimited: true\n    }\n  }];\n}\n\n// Check requests per minute\nif (timeDiff < 60000 && userRateLimit.requestCount >= 10) {\n  return [{\n    json: {\n      chatId: $json.chatId,\n      response: \"🚫 Too many requests. Please wait a minute before trying again.\",\n      rateLimited: true\n    }\n  }];\n}\n\n// Reset counter if more than a minute has passed\nif (timeDiff >= 60000) {\n  userRateLimit.requestCount = 0;\n}\n\n// Update rate limit data\nuserRateLimit.lastRequest = currentTime;\nuserRateLimit.requestCount++;\n$workflow.staticData.rateLimit[userId] = userRateLimit;\n\n// Continue with AI processing\nreturn [$input.first()];"
      },
      "id": "rate-limiter",
      "name": "Rate Limiter",
      "type": "n8n-nodes-base.code",
      "typeVersion": 2,
      "position": [
        900,
        400
      ]
    },
    {
      "parameters": {
        "url": "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent",
        "authentication": "genericCredentialType",
        "genericAuthType": "httpHeaderAuth",
        "sendHeaders": true,
        "headerParameters": {
          "parameters": [
            {
              "name": "x-goog-api-key",
              "value": "={{ $env.GEMINI_API_KEY }}"
            }
          ]
        },
        "sendBody": true,
        "bodyParameters": {
          "parameters": []
        },
        "jsonBody": "={\n  \"contents\": [\n    {\n      \"parts\": [\n        {\n          \"text\": \"{{ $json.messageText }}\"\n        }\n      ]\n    }\n  ],\n  \"generationConfig\": {\n    \"temperature\": 0.7,\n    \"topK\": 40,\n    \"topP\": 0.95,\n    \"maxOutputTokens\": 1024,\n    \"stopSequences\": []\n  },\n  \"safetySettings\": [\n    {\n      \"category\": \"HARM_CATEGORY_HARASSMENT\",\n      \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"\n    },\n    {\n      \"category\": \"HARM_CATEGORY_HATE_SPEECH\",\n      \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"\n    },\n    {\n      \"category\": \"HARM_CATEGORY_SEXUALLY_EXPLICIT\",\n      \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"\n    },\n    {\n      \"category\": \"HARM_CATEGORY_DANGEROUS_CONTENT\",\n      \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"\n    }\n  ]\n}",
        "options": {}
      },
      "id": "gemini-ai-request",
      "name": "Gemini AI Request",
      "type": "n8n-nodes-base.httpRequest",
      "typeVersion": 4.2,
      "position": [
        1120,
        400
      ],
      "onError": "continueErrorOutput"
    },
    {
      "parameters": {
        "jsCode": "// Process Gemini AI response with comprehensive error handling\nconst geminiResponse = $input.first().json;\nconst telegramData = $('Extract Message Data').first().json;\n\nlet aiResponse = \"Sorry, I couldn't process your request. Please try again. 🤖\";\nlet hasError = false;\n\ntry {\n  // Check for HTTP errors\n  if ($input.first().error) {\n    console.error('HTTP Error:', $input.first().error);\n    aiResponse = \"⚠️ I'm experiencing technical difficulties. Please try again in a moment.\";\n    hasError = true;\n  }\n  // Check for Gemini API errors\n  else if (geminiResponse.error) {\n    console.error('Gemini API Error:', geminiResponse.error);\n    \n    switch(geminiResponse.error.code) {\n      case 400:\n        aiResponse = \"❌ I couldn't understand your request. Please try rephrasing it.\";\n        break;\n      case 429:\n        aiResponse = \"⏳ I'm receiving too many requests right now. Please wait a moment and try again.\";\n        break;\n      case 403:\n        aiResponse = \"🚫 Access denied. The service might be temporarily unavailable.\";\n        break;\n      case 500:\n        aiResponse = \"🔧 The AI service is temporarily down. Please try again later.\";\n        break;\n      default:\n        aiResponse = \"⚠️ An unexpected error occurred. Please try again.\";\n    }\n    hasError = true;\n  }\n  // Process successful response\n  else if (geminiResponse.candidates && \n           geminiResponse.candidates.length > 0 && \n           geminiResponse.candidates[0].content &&\n           geminiResponse.candidates[0].content.parts &&\n           geminiResponse.candidates[0].content.parts.length > 0) {\n    \n    aiResponse = geminiResponse.candidates[0].content.parts[0].text;\n    \n    // Check if response was blocked by safety filters\n    if (geminiResponse.candidates[0].finishReason === 'SAFETY') {\n      aiResponse = \"🛡️ I can't provide a response to that request due to safety guidelines. Please try asking something else.\";\n    }\n    \n    // Ensure response isn't too long for Telegram (4096 char limit)\n    if (aiResponse.length > 4000) {\n      aiResponse = aiResponse.substring(0, 3900) + \"\\n\\n... (response truncated)\";\n    }\n  }\n  // Handle empty or malformed response\n  else {\n    console.warn('Unexpected response structure:', JSON.stringify(geminiResponse));\n    aiResponse = \"🤔 I received an unexpected response format. Please try again.\";\n    hasError = true;\n  }\n  \n} catch (error) {\n  console.error('Error processing Gemini response:', error);\n  aiResponse = \"💥 An error occurred while processing your request. Please try again.\";\n  hasError = true;\n}\n\nreturn [{\n  json: {\n    chatId: telegramData.chatId,\n    response: aiResponse,\n    originalMessage: telegramData.messageText,\n    username: telegramData.username,\n    hasError: hasError,\n    timestamp: new Date().toISOString()\n  }\n}];"
      },
      "id": "process-gemini-response",
      "name": "Process Gemini Response",
      "type": "n8n-nodes-base.code",
      "typeVersion": 2,
      "position": [
        1340,
        400
      ]
    },
    {
      "parameters": {
        "conditions": {
          "options": {
            "caseSensitive": true,
            "leftValue": "",
            "typeValidation": "strict"
          },
          "conditions": [
            {
              "id": "condition-rate-limited",
              "leftValue": "={{ $json.rateLimited }}",
              "rightValue": true,
              "operator": {
                "type": "boolean"
              }
            }
          ],
          "combinator": "and"
        },
        "options": {}
      },
      "id": "check-rate-limit",
      "name": "Check Rate Limit",
      "type": "n8n-nodes-base.if",
      "typeVersion": 2,
      "position": [
        1120,
        300
      ]
    },
    {
      "parameters": {
        "url": "=https://api.telegram.org/bot{{ $env.TELEGRAM_BOT_TOKEN }}/sendMessage",
        "sendBody": true,
        "bodyParameters": {
          "parameters": []
        },
        "jsonBody": "={\n  \"chat_id\": \"{{ $json.chatId }}\",\n  \"text\": \"{{ $json.response }}\",\n  \"parse_mode\": \"Markdown\",\n  \"disable_web_page_preview\": true\n}",
        "options": {}
      },
      "id": "send-telegram-response",
      "name": "Send Telegram Response",
      "type": "n8n-nodes-base.httpRequest",
      "typeVersion": 4.2,
      "position": [
        1560,
        300
      ],
      "onError": "continueErrorOutput"
    },
    {
      "parameters": {
        "respondWith": "text",
        "responseBody": "OK"
      },
      "id": "webhook-response",
      "name": "Webhook Response",
      "type": "n8n-nodes-base.respondToWebhook",
      "typeVersion": 1,
      "position": [
        1780,
        300
      ]
    },
    {
      "parameters": {
        "jsCode": "// Log interaction for monitoring and debugging\nconst data = $input.first().json;\n\nconst logEntry = {\n  timestamp: new Date().toISOString(),\n  chatId: data.chatId,\n  username: data.username,\n  originalMessage: data.originalMessage,\n  responseLength: data.response ? data.response.length : 0,\n  hasError: data.hasError || false,\n  rateLimited: data.rateLimited || false,\n  skipAI: data.skipAI || false\n};\n\nconsole.log('Telegram Bot Interaction:', JSON.stringify(logEntry, null, 2));\n\n// Return original data unchanged\nreturn [$input.first()];"
      },
      "id": "interaction-logger",
      "name": "Interaction Logger",
      "type": "n8n-nodes-base.code",
      "typeVersion": 2,
      "position": [
        1560,
        420
      ]
    }
  ],
  "connections": {
    "Webhook Trigger": {
      "main": [
        [
          {
            "node": "Extract Message Data",
            "type": "main",
            "index": 0
          }
        ]
      ]
    },
    "Extract Message Data": {
      "main": [
        [
          {
            "node": "Check Skip AI",
            "type": "main",
            "index": 0
          }
        ]
      ]
    },
    "Check Skip AI": {
      "main": [
        [
          {
            "node": "Send Telegram Response",
            "type": "main",
            "index": 0
          }
        ],
        [
          {
            "node": "Rate Limiter",
            "type": "main",
            "index": 0
          }
        ]
      ]
    },
    "Rate Limiter": {
      "main": [
        [
          {
            "node": "Check Rate Limit",
            "type": "main",
            "index": 0
          }
        ]
      ]
    },
    "Gemini AI Request": {
      "main": [
        [
          {
            "node": "Process Gemini Response",
            "type": "main",
            "index": 0
          }
        ]
      ]
    },
    "Process Gemini Response": {
      "main": [
        [
          {
            "node": "Send Telegram Response",
            "type": "main",
            "index": 0
          },
          {
            "node": "Interaction Logger",
            "type": "main",
            "index": 0
          }
        ]
      ]
    },
    "Check Rate Limit": {
      "main": [
        [
          {
            "node": "Send Telegram Response",
            "type": "main",
            "index": 0
          }
        ],
        [
          {
            "node": "Gemini AI Request",
            "type": "main",
            "index": 0
          }
        ]
      ]
    },
    "Send Telegram Response": {
      "main": [
        [
          {
            "node": "Webhook Response",
            "type": "main",
            "index": 0
          }
        ]
      ]
    }
  },
  "meta": {
    "templateCredsSetupCompleted": true,
    "instanceId": "your-instance-id"
  },
  "envVariables": {
    "GEMINI_API_KEY": "YOUR_NEW_SECURE_API_KEY_HERE",
    "TELEGRAM_BOT_TOKEN": "YOUR_TELEGRAM_BOT_TOKEN_HERE"
  },
  "settings": {
    "executionOrder": "v1"
  },
  "staticData": {
    "rateLimit": {}
  },
  "tags": [
    {
      "createdAt": "2024-12-01T10:00:00.000Z",
      "updatedAt": "2024-12-01T10:00:00.000Z",
      "id": "telegram-ai-bot",
      "name": "Telegram AI Bot"
    }
  ],
  "triggerCount": 1,
  "updatedAt": "2024-12-01T10:00:00.000Z",
  "versionId": "1"
}