/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: '#1a202c', // Dark blue-gray for backgrounds/main elements
        secondary: '#2d3748', // Slightly lighter blue-gray for cards/sections
        accent: '#4299e1', // A vibrant blue for accents, buttons, links
        textLight: '#e2e8f0', // Light gray for text on dark backgrounds
        textDark: '#2d3748', // Dark gray for text on light backgrounds
        borderLight: '#4a5568', // Light border for dark elements
        borderDark: '#cbd5e0', // Dark border for light elements
      },
    },
  },
  plugins: [],
}