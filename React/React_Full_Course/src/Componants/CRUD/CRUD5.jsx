import React, { useState } from 'react'

const CRUD5 = () => {

    let [state,setState]=useState({
        id:Date.now(),
        UserName:"",
        Email:"",
        PassWord:"",
        list:[]
    });
    let handleOnChange=(e)=>{
      let {value,name}=e.target;
      
        
    }
  return (
    <>
    <input
  type="text"
  className="bg-[#d6d3d3] text-white px-4 py-2 rounded"
  placeholder="Enter your name"
  name="UserName"
  value={state.UserName}
  onChange={() => {}}
/>
    </>
  )
}

export default CRUD5