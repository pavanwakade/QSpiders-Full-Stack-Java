import React from 'react'
import { useRef } from 'react'

const Task1UseRefHook = () => {
    let ref = useRef();
  return (
    <>
    <div>Task1UseRefHook</div>

    <form action="">
        <input type="text"
        placeholder='enter'
        
        />
    </form>
    </>
  )
}

export default Task1UseRefHook


