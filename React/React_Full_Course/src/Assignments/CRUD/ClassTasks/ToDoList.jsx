// import React, { useState } from 'react';

// const ToDoList = () => {
//   const [tasks, setTasks] = useState([]);
//   const [newTask, setNewTask] = useState('');

//   const handleInputChange = (event) => {
//     setNewTask(event.target.value);
//   };

//   const addTask = () => {
//     if (newTask.trim() !== '') {
//       setTasks([...tasks, newTask]);
//       setNewTask('');
//     }
//   };

//   return (
//     <div>
//       <input type="text" value={newTask} onChange={handleInputChange} />
//       <button onClick={addTask}>Add Task</button>
//       <ul>
//         {tasks.map((task, index) => (
//           <li key={index}>{task}</li>
//         ))}
//       </ul>
//     </div>
//   );
// };

// export default ToDoList;






import React, { useState } from 'react'

const ToDoList = () => {
  let [task, setTasks] = useState([]);
  let handleSubmit = () => {
    setTasks([...task, task])
  }
  return (
    <div className='flex gap-4'>

      <form>

        <input type="text" value={task} placeholder='Enter New Task'
          onChange={(e) => setTasks(e.target.value)}
        />
        <button type="button" onClick={() => handleSubmit()}>add Task</button>
      </form>
      {
        task.map((val, i) => (
          <div key={i}>{val}</div>
        ))
      }
    </div>
  )
}

export default ToDoList