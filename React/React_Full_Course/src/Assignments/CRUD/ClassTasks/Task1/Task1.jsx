import React, { useState } from 'react';
import Welcome from './Welcome';

const Task1 = () => {
  const [state, setState] = useState({ name: "", isTrue: false });

  const handleSubmit = () => {
    setState({ name: state.name, isTrue: true });
  };

  return (
    <div>
      <form>
        <input
          type="text"
          name="name"
          value={state.name}
          placeholder="name"
          onChange={(e) =>
            setState({ name: e.target.value, isTrue: false })
          }
        /> <br />
        <br />
        <button type="button" onClick={handleSubmit} className='rounded-md border-x-2 bg-[#27F56F]'>Submit</button>
      </form>

      <p>{state.isTrue && <Welcome name={state.name} />}</p>
    </div>
  );
};

export default Task1;
