import { useRef } from 'react';

const Task1UseRefHook = () => {
    let ref = useRef();
    // ref.current.style.background="red"
  return (
    <>
    <div>Task1UseRefHook</div>
    <form action="">
        <input type="text"
        placeholder='enter anything'
        className='border-[1px] text-center'
        ref={ref}
        />
    </form>
    <p onClick={()=>{ref.current.style.background="red"}}>
        click me
    </p>
    </>
  )
}

export default Task1UseRefHook


