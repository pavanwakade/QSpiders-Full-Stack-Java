import React, { useState } from 'react'

const ToDoList = () => {

  const [form, setForm] = useState({
    id: '',
    priority: '',
    task: ''
  });
  const [tasks, setTasks] = useState([]);

  // let { id, priority, task, Alltasks } = tasks

  let handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: value
    }));
  };



  const handleSubmit = (e) => {
    e.preventDefault();
    setTasks((prev) => [
      ...prev,
      {
        id: Date.now(),
        task: form.task,
        priority: form.priority
      }
    ]);
    setForm({ id: '', priority: '', task: '' });
  };

  let handleDelete = (id) => {
    const updatedTasks = tasks.filter((item) => item.id !== id);
    setTasks(updatedTasks);
  }

  let handleUpdate = (id) => {
    const obj = tasks.find((val) => val.id === id);
    const filteredList = tasks.filter((val) => val.id !== id)
    setForm({
      id: obj.id,
      task: obj.task,
      priority: obj.priority
    });
    setTasks(filteredList);
  }




  return (
    <div className='flex flex-col w-[100%] h-[90vh] items-center justify-center'>

      <form onSubmit={handleSubmit} className='flex gap-4'>

        <input type="text" name='task' value={form.task} placeholder='Enter New Task'
          onChange={handleChange}
        />
        <select
          name="priority"
          value={form.priority}
          onChange={handleChange}
          className="px-2 border"
        >
          <option value="">Select Priority</option>
          <option value="High">High</option>
          <option value="Medium">Medium</option>
          <option value="Low">Low</option>
        </select>

        <button type="submit" >{form.id ? "Update" : "Add"}</button>
      </form>
      <table className='w-[50%] items-center  justify-center border-x-2'>
        <thead className='text-white bg-slate-400'>
          <tr className='border-x-2'>
            <th>task</th>
            <th>priority</th>
            <th>Actions</th>
          </tr>
        </thead>


        <tbody className=' w-[50%] items-center  justify-center'>
          {
            tasks.map((val) => (
              <tr key={val.id} className=' w-[60%]  border-y-2'>
                {/* <tr> */}
                <td className='px-4 py-2 w-[30%] whitespace-normal break-words'>
                  <p>{val.task}</p>
                </td>


                <td className='px-4 py-2 w-[5%] '>
                  <p>{val.priority}</p>
                </td>
                <td className='px-4 py-2 space-x-2 text-center w-[20%] '>
                  <button type="button" onClick={() => handleUpdate(val.id)} className='px-6 bg-green-500 rounded-md'>update</button>

                  <button type="button" onClick={() => handleDelete(val.id)} className='px-6 bg-red-500 rounded-md'>delete</button>
                </td>
                {/* </tr> */}
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  )
}

export default ToDoList