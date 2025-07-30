import { useState } from "react";

const CRUD4 = () => {
    const [state, setState] = useState({
        userName: "",
        passWord: "",
        list: []
    });

    const { userName, passWord, list } = state;

    const handleChange = (e) => {
        const { name, value } = e.target;
        setState({ ...state, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const tempObj = {
            userName: state.userName,
            passWord: state.passWord,
            id: Date.now()
        };
        setState({
            ...state,
            userName: "",
            passWord: "",
            list: [...state.list, tempObj]
        });
    };

    const handleUpdate = (id) => {
        const obj = list.find((val) => val.id === id);
        const filteredList = list.filter((val) => val.id !== id);
        setState({
            userName: obj.userName,
            passWord: obj.passWord,
            list: filteredList
        });
    };

    const handleDelete = (id) => {
        const filteredList = list.filter((val) => val.id !== id);
        setState({ ...state, list: filteredList });
    };

    return (
        <div>
            <form className="flex flex-col items-center justify-between w-full p-5 space-y-2 bg-gray-500">
                <input
                    className="h-8 px-4 py-2 border border-white rounded bg-slate-100"

                    placeholder="Enter name"
                    name="userName"
                    value={userName}
                    onChange={handleChange}
                />
                <input
                    className="h-8 px-4 py-2 border border-white rounded bg-slate-100"

                    type="text"
                    placeholder="Enter Password"
                    name="passWord"
                    value={passWord}
                    onChange={handleChange}
                />
                <button
                    onClick={handleSubmit}
                    className="px-4 py-2 text-white bg-gray-600 border-white rounded"
                >
                    Create
                </button>
            </form>
            <hr />

            {list.length === 0 && (
                <p className="p-4 text-center text-white">No users found.</p>
            )}

            <div className="flex bg-slate-400 space-x-2 justify-evenly flex-wrap p-4 w-[100%]">
                {list.map((val) => (
                    <div
                        className="h-[15%] w-[15%] bg-cyan-400 space-y-2 border border-black rounded py-6 px-4"
                        key={val.id}
                    >
                        <p><strong>Name:</strong> {val.userName}</p>
                        <p><strong>Password:</strong> {val.passWord}</p>
                        <div className="flex justify-evenly">
                            <button
                                className="px-4 py-2 text-white bg-gray-600 border-white rounded"
                                onClick={() => handleUpdate(val.id)}
                            >
                                Update
                            </button>
                            <button
                                className="px-4 py-2 text-white bg-gray-600 border-white rounded"
                                onClick={() => handleDelete(val.id)}
                            >
                                Delete
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default CRUD4;
