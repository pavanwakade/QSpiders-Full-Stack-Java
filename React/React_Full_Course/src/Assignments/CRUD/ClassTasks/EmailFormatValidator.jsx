import React, { useState } from 'react';

const EmailFormatValidator = () => {
  const [email, setEmail] = useState('');
  const [isValid, setIsValid] = useState(true);

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
    // Basic email format validation
    const emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    setIsValid(emailRegex.test(event.target.value));
  };

  return (
    <div>
      <label>Email:</label>
      <input type="email" value={email} onChange={handleEmailChange} /><br />
      {!isValid && <p>Invalid email format.</p>}
    </div>
  );
};

export default EmailFormatValidator;