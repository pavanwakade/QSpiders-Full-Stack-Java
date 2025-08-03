import React from 'react';

const EmptyToDoListMessage = ({ tasks }) => {
  return (
    <div>
      {tasks.length === 0 ? (
        <p>No tasks to show.</p>
      ) : (
        <ul>
          {tasks.map((task, i) => (
            <li key={i}>{i+1}{task}</li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default EmptyToDoListMessage;