import React, { useState } from 'react'

const ToDoList = () => {

  let [task, setTasks] = useState([]);
  let [newTask, setNewTask] = useState('');


  let handleSubmit = () => {
    setTasks([...task, newTask])
    setNewTask('')
  }

  let handleDelete = (id) => {
    let deletetask = task.filter((_, i) => i !== id);
    setTasks(deletetask);
  }

  let handleUpdate = (id) => {
    let updatetask = task.find((i) => i == id);
    setTasks(...updatetask,task);
  }
  return (
    <div >

      <form className='flex gap-4'>

        <input type="text" value={newTask} placeholder='Enter New Task'
          onChange={(e) => setNewTask(e.target.value)}
        />
        <button type="button" onClick={() => handleSubmit()}>add Task</button>
      </form>
      <div >
        {
          task.map((val, i) => (
            <div key={i} className='flex m-2 bg-gray-400'>
              <p>{val}</p>
              <button type="button" onClick={() => handleUpdate(i)}>update</button>
              <button type="button" onClick={() => handleDelete(i)}>delete</button>

            </div>
          ))
        }
      </div>
    </div>
  )
}

export default ToDoList