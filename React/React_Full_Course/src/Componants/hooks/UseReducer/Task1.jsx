import React from 'react'
import { useReducer } from 'react'

const Task1 = () => {
 let initialValue={
  items :{
    id :'',
    name :'',
    price : '',
    

  }
 }
  let [state,dispatch]=useReducer(reducer,initialValue)
  return (
    <div>
      <h1>Task 1</h1>
      <>
      
      </>
    </div>
  )
}

export default Task1