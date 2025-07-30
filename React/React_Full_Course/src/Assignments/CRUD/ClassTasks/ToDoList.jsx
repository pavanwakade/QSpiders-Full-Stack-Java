import React, { useState } from 'react'

const ToDoList = () => {

  let starting = {
    id: Date.now(),
    priority: '',
    tasks: '',
    Alltasks: []
  }
  let [task, setTasks] = useState([]);
  const [form, setForm] = useState(starting);

  let { id, priority, tasks, Alltasks } = task


  let handleChange = (e) => {
    let { name, value } = e.target
    setForm((prev) => ({
      ...prev,
      [name]: value
    }));
  }


  const handleSubmit = (e) => {
    e.preventDefault();
    setTasks((prev) => [
      ...prev,
      {
        id: Date.now(),
        task: form.tasks,
        priority: form.priority
      }
    ]);
    setForm(starting);
  };

let handleDelete = (id) => {
  let deletetask = Alltasks.filter((_, i) => i !== id);
  setTasks(deletetask);
}

let handleUpdate = (id) => {
  const obj = Alltasks.find((val) => val.id === id);
  const filteredList = Alltasks.filter((val) => val.id !== id)
  setTasks({
    priority: obj.priority,
    tasks: obj.tasks,
    Alltasks: obj.filteredList
  })
}




return (
  <div >

    <form className='flex gap-4'>

      <input type="text" name='tasks' value={form.tasks} placeholder='Enter New Task'
        onChange={handleChange}
      />
      <input type="text" name='priority' value={form.priority} placeholder='Priority'
        onChange={handleChange}
      />
      <button type="button" onClick={() => handleSubmit()}>add Task</button>
    </form>
    <div >
      {
        task.map((val) => (
          <div key={val.id} className='flex m-2 bg-gray-400'>
            <p>{val}</p>
            <button type="button" onClick={() => handleUpdate(val.id)}>update</button>
            <button type="button" onClick={() => handleDelete(val.id)}>delete</button>

          </div>
        ))
      }
    </div>
  </div>
)
}

export default ToDoList