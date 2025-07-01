import { useState } from 'react';
import ToHeadbg from './../../../assets/ToDoBg.jpg';

export const TodoApp = () => {

    let [state, setState] = useState({
        id: Date.now(),
        task: '',
        list: []
    })

    let handleOnchange=(e)=>{
        let{name,value}=e.target;
        setState({...state,[name]:value})
    }
    return (
        <>
            {/* <img src={ToHeadbg} alt="bg" /> */}

            <form action="">
                <input type="text"
                    className=''
                    placeholder='Enter your task'
                    name='state'
                    value={''}
                    onChange={() => { }}
                />
            </form>

        </>
    );
};

export default TodoApp;
