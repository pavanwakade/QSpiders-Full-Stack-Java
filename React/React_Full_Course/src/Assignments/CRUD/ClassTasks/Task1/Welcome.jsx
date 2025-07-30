import React, { useEffect, useState } from 'react';

const WelcomeAlert = ({ name }) => {
  const [show, setShow] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setShow(false);
    }, 3000); // 3 seconds

    return () => clearTimeout(timer); // Cleanup
  }, []);

  if (!show) return null;

  return (
    <div className="fixed top-10 right-10 bg-[#53df53] text-white px-16 py-3 rounded-lg shadow-lg transition-all duration-300">
      Welcome, {name}!
    </div>
  );
};

export default WelcomeAlert;
