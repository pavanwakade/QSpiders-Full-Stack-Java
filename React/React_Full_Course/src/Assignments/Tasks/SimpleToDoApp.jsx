import React, { useState } from 'react';

function SimpleToDoApp() {
  const [tasks, setTasks] = useState([]);
  const [input, setInput] = useState('');
  const addTask = () => {
    if (input.trim()) setTasks([...tasks, { text: input, done: false }]);
    setInput('');
  };
  const markDone = idx => {
    setTasks(tasks.map((t, i) => i === idx ? { ...t, done: !t.done } : t));
  };
  return (
    <div>
      <input value={input} onChange={e => setInput(e.target.value)} />
      <button onClick={addTask}>Add</button>
      {tasks.length === 0 ? (
        <div>No tasks</div>
      ) : (
        <ul>
          {tasks.map((t, i) => (
            <li key={i} onClick={() => markDone(i)} style={{ textDecoration: t.done ? 'line-through' : 'none' }}>{t.text}</li>
          ))}
        </ul>
      )}
    </div>
  );
}
export default SimpleToDoApp;
