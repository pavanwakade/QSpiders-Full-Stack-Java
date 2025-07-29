import React, { useState } from 'react';

const AllCRUD = () => {
  const initialState = {
    id: Date.now(),
    username: "",
    email: "",
    phone: "",
    address: "",
    list: [],
    isEditing: false,
    editingId: null
  };

  const [state, setState] = useState(initialState);
  const { username, email, phone, address, list, isEditing, editingId } = state;

  const handleOnChange = (e) => {
    const { name, value } = e.target;
    setState({ ...state, [name]: value });
  };

  const handlSubmit = (e) => {
    e.preventDefault();

    if (isEditing) {
      // 🔁 Update existing record
      const updatedList = list.map((item) =>
        item.id === editingId
          ? { id: editingId, username, email, phone, address }
          : item
      );

      setState({
        ...state,
        username: "",
        email: "",
        phone: "",
        address: "",
        list: updatedList,
        isEditing: false,
        editingId: null,
      });
    } else {
      // ➕ Add new record
      const newEntry = {
        id: Date.now(),
        username,
        email,
        phone,
        address,
      };

      setState({
        ...state,
        username: "",
        email: "",
        phone: "",
        address: "",
        list: [...list, newEntry],
      });
    }
  };

  const handleDelete = (id) => {
    setState({
      ...state,
      list: list.filter((item) => item.id !== id),
    });
  };

  const handleUpdate = (id) => {
    const selectedItem = list.find((item) => item.id === id);

    setState({
      ...state,
      username: selectedItem.username,
      email: selectedItem.email,
      phone: selectedItem.phone,
      address: selectedItem.address,
      isEditing: true,
      editingId: id,
    });
  };

  return (
    <>
      <div>AllCRUD</div>

      {/* Form */}
      <form
        onSubmit={handlSubmit}
        className='flex flex-col space-y-2 items-center w-full justify-between bg-gray-500 p-5'
      >
        <input
          type="text"
          className='border border-blue-700'
          placeholder='username'
          name='username'
          value={username}
          onChange={handleOnChange}
        />

        <input
          type="text"
          className='border border-blue-700'
          placeholder='email'
          name='email'
          value={email}
          onChange={handleOnChange}
        />

        <input
          type="text"
          className='border border-blue-700'
          placeholder='phone'
          name='phone'
          value={phone}
          onChange={handleOnChange}
        />

        <input
          type="text"
          className='border border-blue-700'
          placeholder='address'
          name='address'
          value={address}
          onChange={handleOnChange}
        />

        <button
          type='submit'
          className={`${
            isEditing ? 'bg-yellow-500' : 'bg-blue-500'
          } text-white px-4 py-2 rounded`}
        >
          {isEditing ? 'Update' : 'Submit'}
        </button>
      </form>

      {/* List */}
      <div className='space-y-4 mt-4'>
        {list.map((item) => (
          <div
            key={item.id}
            className='flex flex-col space-y-2 items-center w-full justify-between bg-gray-300 p-5'
          >
            <p><b>Username:</b> {item.username}</p>
            <p><b>Email:</b> {item.email}</p>
            <p><b>Phone:</b> {item.phone}</p>
            <p><b>Address:</b> {item.address}</p>

            <div className='flex gap-4'>
              <button
                className='bg-red-500 text-white px-4 py-2 rounded'
                onClick={() => handleDelete(item.id)}
              >
                Delete
              </button>

              <button
                className='bg-yellow-500 text-white px-4 py-2 rounded'
                onClick={() => handleUpdate(item.id)}
              >
                Update
              </button>
            </div>
          </div>
        ))}
      </div>
    </>
  );
};

export default AllCRUD;
