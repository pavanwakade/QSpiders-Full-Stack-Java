import React, { useState } from 'react'


const EventTask4 = () => {

  let[state,setState]=useState({
     m1s1:"gray",
     m1s2:"white"
   })

   let handleclick =() =>{
    setState((pre)=>{return{ ...pre,m1s1:"red"}})
   }
  return (
    <div>
      <h1>{state.m1s1}</h1>
      <button onClick={handleclick}>click</button>
    </div>
  )
}

export default EventTask4