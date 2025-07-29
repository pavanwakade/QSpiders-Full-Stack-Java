import React, { useState } from 'react';

function BasicFormWithValidation() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [errors, setErrors] = useState({});

  const handleSubmit = e => {
    e.preventDefault();
    const errs = {};
    if (!name) errs.name = 'Name required';
    if (!email || !/^[^@]+@[^@]+\.[^@]+$/.test(email)) errs.email = 'Valid email required';
    setErrors(errs);
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        value={name}
        onChange={e => setName(e.target.value)}
        placeholder="Name"
      />
      {errors.name && <div>{errors.name}</div>}
      <input
        value={email}
        onChange={e => setEmail(e.target.value)}
        placeholder="Email"
      />
      {errors.email && <div>{errors.email}</div>}
      <button type="submit">Submit</button>
    </form>
  );
}
export default BasicFormWithValidation;
