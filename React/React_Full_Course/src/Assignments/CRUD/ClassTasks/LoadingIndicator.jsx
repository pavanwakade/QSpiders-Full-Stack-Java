import React, { useState, useEffect } from 'react';

const LoadingIndicator = () => {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState(null);

  useEffect(() => {
    // Simulate data fetching
    setTimeout(() => {
      setData('Data loaded!');
      setLoading(false);
    }, 2000);
  }, []);

  return (
    <div>
      {loading ? <p>Loading...</p> : <p>{data}</p>}
    </div>
  );
};

export default LoadingIndicator;