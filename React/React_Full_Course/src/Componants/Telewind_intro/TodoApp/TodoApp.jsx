import { useState } from 'react';
import ToHeadbg from './../../../assets/ToDoBg.jpg';

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

    let handleOnSubmit=()=>{
        setState({
            
        })
    }
    return (
        <>
            {/* <img src={ToHeadbg} alt="bg" /> */}

            <div className='flex items-center justify-center  '>

                <form action="" className=' '>
                    <input type="text"
                        className=''
                        placeholder='Enter your task'
                        name='tasks'
                        value={tasks}
                        onChange={handleOnchange}
                    />
                    <button className='bg-[#71dd71] px-[10px] mx-[20px]'>add</button>
                    {/* <button className='bg-[#eb5247] px-[10px] mx-[10px]'>delete</button> */}
                </form>
            </div>

        </>
    );
};

export default TodoApp;
