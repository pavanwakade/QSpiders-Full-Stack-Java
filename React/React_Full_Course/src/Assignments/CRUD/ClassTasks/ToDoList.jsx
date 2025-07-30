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
    const obj = {
      id: task.id,
      priority: task.priority,
      tasks: task.tasks,
      // Alltasks: [...Alltasks, obj]
    }
    setTasks({ ...Alltasks, obj, starting })

  }

  let handleDelete = (id) => {
    let deletetask = task.filter((_, i) => i !== id);
    setTasks(deletetask);
  }

  let handleUpdate = (id) => {
    const obj = Alltasks.find((val) => val.id === id);
    const filteredList = Alltasks.filter((val) => val.id !== id)
    setTasks({
      priority: obj.priority,
      tasks: obj.tasks,
    })
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