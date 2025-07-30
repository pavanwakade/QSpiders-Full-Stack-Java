import React from 'react';

const EmptyToDoListMessage = ({ tasks }) => {
  return (
    <div>
      {tasks.length === 0 ? (
        <p>No tasks to show.</p>
      ) : (
        <ul>
          {tasks.map((task, index) => (
            <li key={index}>{task}</li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default EmptyToDoListMessage;