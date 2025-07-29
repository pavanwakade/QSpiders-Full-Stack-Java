import { useState } from 'react'

const UseState = () => {

    let [state, setState] = useState({
    names: "",
    age: '',
    List: [],
    editingId: null 
});

let { names, age, List, editingId } = state;

let handleSubmit = (e) => {
    e.preventDefault();
    
    if (editingId) {
        setState((prev) => ({
            ...prev,
            List: prev.List.map(item => 
                item.id === editingId 
                    ? { ...item, names: prev.names, age: prev.age }
                    : item
            ),
            names: "",
            age: '',
            editingId: null
        }));
    } else {
        // Create new entry
        const newEntry = {
            id: Date.now(), // Generate fresh ID here
            names: state.names,
            age: state.age
        };
        setState((prev) => ({
            ...prev,
            List: [...prev.List, newEntry],
            names: "",
            age: ''
        }));
    }
};

let handleChange = (e) => {
    let { name, value } = e.target;
    setState((prev) => ({
        ...prev,
        [name]: value
    }));
};

let handleUpdate = (id) => {
    let selectedItem = state.List.find((item) => item.id === id);
    if (selectedItem) {
        setState((prev) => ({
            ...prev,
            names: selectedItem.names,
            age: selectedItem.age,
            editingId: id 
        }));
    }
};

let handleDelete = (id) => {
    setState((prev) => ({
        ...prev,
        List: prev.List.filter((item) => item.id !== id),
        ...(prev.editingId === id && {
            names: "",
            age: '',
            editingId: null
        })
    }));
};

// // Optional: Cancel edit function
// let handleCancelEdit = () => {
//     setState((prev) => ({
//         ...prev,
//         names: "",
//         age: '',
//         editingId: null
//     }));
// };

    return (
        <>
            <div className="flex items-center justify-center h-[50vh]">
                <form
                    onSubmit={handleSubmit}
                    className="flex flex-col w-full max-w-md gap-6 p-8 bg-white rounded-lg shadow-md"
                >
                    <input
                        type="text"
                        name="names"
                        placeholder="Enter name"
                        value={names}
                        onChange={handleChange}
                        className="px-3 py-2 border rounded"
                    />
                    <input
                        type="text"
                        name="age"
                        placeholder="Enter age"
                        value={age}
                        onChange={handleChange}
                        className="px-3 py-2 border rounded"
                    />
                    <button

                        className="px-6 py-2 text-white transition bg-blue-600 rounded-full shadow hover:bg-blue-700 "
                    >
                        add
                    </button>
                </form>
            </div>

            <div className='flex items-center justify-center p-[5px] bg-white  flex-wrap '>
                {
                    List.map((value) => (
                        <div key={value.id} className='flex flex-col items-start justify-center w-[25%] gap-1 p-5 m-5 border border-blue-500 rounded-md shadow transform transition-transform duration-200 hover:scale-105 hover:border-pink-500 hover:border-2 hover:text-white' style={{ background: `#${Math.floor(Math.random() * 16777215).toString(16).padStart(6, '0')}` }}>
                            <p><strong>ID:</strong> {value.id}</p>
                            <p><strong>Name:</strong> {value.names}</p>
                            <p><strong>Age:</strong> {value.age}</p>
                            <div className='flex items-start justify-center '>
                                <button type="button" onClick={() => handleUpdate(value.id)} className='bg-[blue] px-2 py-1 rounded-md text-white mx-5 my-3' >update</button>
                                <button type="button" onClick={() => handleDelete(value.id)} className='bg-[#ff404a] px-2 py-1 rounded-md text-white mx-5 my-3'>delete</button>
                            </div>
                        </div>
                    ))
                }
            </div>
        </>
    )
}

export default UseState