import React, { useState } from 'react'

const ToDoList = () => {

  let starting = {
    id: Date.now(),
    priority: '',
    tasks: '',
    Alltasks: []
  }
  let [task, setTasks] = useState(starting);
  let { id, priority, tasks, Alltasks } = task


  let handleChange = (e) => {

  }
  let handleSubmit = (e) => {
    e.preventDefault();
    setTasks({ ...task, newTask })

  }

  let handleDelete = (id) => {
    let deletetask = task.filter((_, i) => i !== id);
    setTasks(deletetask);
  }

  let handleUpdate = (id) => {
    let oldid = id;
    setNewTask()
    setTasks(...task, newTask);
  }




  return (
    <div >

      <form className='flex gap-4'>

        <input type="text" name='tasks' value={tasks} placeholder='Enter New Task'
          onChange={handleChange}
        />
        <input type="text" name='priority' value={priority} placeholder='Priority'
          onChange={handleChange}
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