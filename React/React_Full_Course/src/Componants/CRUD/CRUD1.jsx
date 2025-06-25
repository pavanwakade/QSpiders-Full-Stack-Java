import React, { useState } from 'react'

const CRUD1 = () => {

    const [user,setUser]=useState("");
    const [pass,setPass]=useState("");
    const [list,setList]=useState([]);

    let handleSubmit=(e)=>{
        setList([...list,{UserName:user,Password:pass}])
    }

  return (
    <>CRUD1
    
    
    
    </>
  )
}

export default CRUD1