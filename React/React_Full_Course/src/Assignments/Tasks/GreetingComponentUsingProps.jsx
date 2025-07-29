import React, { useState } from 'react';

function Greeting({ name }) {
  return <div>Hello, {name}!</div>;
}

function GreetingComponentUsingProps() {
  const [name, setName] = useState('John');
  return (
    <div>
      <Greeting name={name} />
      <button onClick={() => setName('John')}>John</button>
      <button onClick={() => setName('Jane')}>Jane</button>
      <button onClick={() => setName('Alex')}>Alex</button>
    </div>
  );
}
export default GreetingComponentUsingProps;
