import { useState } from 'react';

export const TodoApp = () => {

    let [state, setState] = useState({
        id: Date.now(),
        tasks: '',
        list: []
    })
    const { tasks, list } = state;

    let handleOnchange = (e) => {
        let { name, value } = e.target;
        setState({ ...state, [name]: value })
    }

    let handleOnSubmit = (e) => {
        e.preventDefault();
        let newTask = { tasks: state.tasks }

        setState({
            ...state,
            list: [...state.list, newTask],
            tasks: '',
        });

    }

    let handlDelete = (id) => {
        const filterdList = list.filter((val) => val !== id)
        setState({ ...state, list: filterdList })
    }

    let handlUpdate = (id) => {

    }
    return (
        <>
            {/* <img src={ToHeadbg} alt="bg" /> */}

            <div className='flex items-center justify-center  '>

                <form action="" className=' ' >
                    <input type="text"
                        className=''
                        placeholder='Enter your task'
                        name='tasks'
                        value={tasks}
                        onChange={handleOnchange}
                    />
                    <button className='bg-[#71dd71] px-[10px] mx-[20px]' onClick={handleOnSubmit}>add</button>
                </form>
            </div>
            <div className="mt-4 text-center" >
                {list.map((val, index) => (
                    <div key={val.id}>
                        <div key={index} className="my-1">
                            {val.tasks}
                            <button className='bg-[#eb5247] px-[10px] mx-[10px]' onClick={() => handlDelete(val.id)}>delete</button>
                        </div>
                    </div>
                ))}
            </div>
        </>
    );
};

export default TodoApp;
