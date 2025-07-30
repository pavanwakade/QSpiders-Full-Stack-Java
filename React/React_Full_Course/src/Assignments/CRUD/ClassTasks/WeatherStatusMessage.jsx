import React, { useState } from 'react';

const WeatherStatusMessage = () => {
  const [weather, setWeather] = useState('sunny');

  const handleWeatherChange = (event) => {
    setWeather(event.target.value);
  };

  let message;
  switch (weather) {
    case 'sunny':
      message = 'It\'s sunny today!';
      break;
    case 'rain':
      message = 'Looks like rain!';
      break;
    case 'cloudy':
      message = 'Cloudy skies ahead.';
      break;
    default:
      message = 'Unknown weather condition.';
  }

  return (
    <div>
      <select value={weather} onChange={handleWeatherChange}>
        <option value="sunny">Sunny</option>
        <option value="rain">Rain</option>
        <option value="cloudy">Cloudy</option>
      </select>
      <p>{message}</p>
    </div>
  );
};

export default WeatherStatusMessage;