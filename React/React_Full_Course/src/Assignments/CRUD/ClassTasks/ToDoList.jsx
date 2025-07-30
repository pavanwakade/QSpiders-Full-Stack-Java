import React, { useState } from 'react'

const ToDoList = () => {
  let [task, setTasks] = useState([]);
  let [newTask, setNewTask] = useState('');
  let handleSubmit = () => {
    setTasks([...task, newTask])
    setNewTask('')
  }
  return (
    <div >

      <form className='flex gap-4'>

        <input type="text" value={newTask} placeholder='Enter New Task'
          onChange={(e) => setNewTask(e.target.value)}
        />
        <button type="button" onClick={() => handleSubmit()}>add Task</button>
      </form>
      <div className='felx'>
        {
          task.map((val, i) => (
            <div key={i}>
              <div>{val}</div>
              <button type="button">update</button>
              <button type="button">delete</button>

            </div>
          ))
        }
      </div>
    </div>
  )
}

export default ToDoList