import React, { useRef, useState } from 'react';

function FormWithFieldHighlightOnValidation() {
  const [fields, setFields] = useState({ name: '', email: '', age: '' });
  const [errors, setErrors] = useState({});
  const refs = {
    name: useRef(),
    email: useRef(),
    age: useRef(),
  };
  const handleChange = e => {
    setFields({ ...fields, [e.target.name]: e.target.value });
  };
  const handleSubmit = e => {
    e.preventDefault();
    const errs = {};
    Object.keys(fields).forEach(k => {
      if (!fields[k]) errs[k] = true;
    });
    setErrors(errs);
    if (Object.keys(errs).length > 0) {
      const first = Object.keys(errs)[0];
      refs[first].current && refs[first].current.scrollIntoView({ behavior: 'smooth' });
      refs[first].current && refs[first].current.focus();
    }
  };
  return (
    <form onSubmit={handleSubmit}>
      <input
        name="name"
        ref={refs.name}
        value={fields.name}
        onChange={handleChange}
        placeholder="Name"
        style={{ borderColor: errors.name ? 'red' : undefined }}
      />
      <input
        name="email"
        ref={refs.email}
        value={fields.email}
        onChange={handleChange}
        placeholder="Email"
        style={{ borderColor: errors.email ? 'red' : undefined }}
      />
      <input
        name="age"
        ref={refs.age}
        value={fields.age}
        onChange={handleChange}
        placeholder="Age"
        style={{ borderColor: errors.age ? 'red' : undefined }}
      />
      <button type="submit">Submit</button>
    </form>
  );
}
export default FormWithFieldHighlightOnValidation;
