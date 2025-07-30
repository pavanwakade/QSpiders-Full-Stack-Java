import React, { useState } from 'react';

const FormStateTracker = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');

  return (
    <div>
      <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder='enter name' /><br />
      <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder='Enter Email' /><br />
      <p>Name: {name}</p>
      <p>Email: {email}</p>
    </div>
  );
};

export default FormStateTracker;