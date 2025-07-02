import React, { useState } from 'react'

const FetchApi = () => {

let [state, setState] = useState(0);
let [api,setApi]=useState([]);

let fetchDate=async ()=>{
  let response=await fetch(`https://api.github.com/users`);
  let data=await response.json();
  setApi(data);
}

  return (
    <>FetchApi


    </>
  )
}

export default FetchApi