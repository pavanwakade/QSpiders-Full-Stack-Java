import { useEffect, useState } from 'react';

const UseEffectTask1 = () => {
  let [state, setState] = useState(0);
  useEffect(() => {

    console.log("task 1");  //it render only one time when page render
  }, [])  //empty array([]) means run only one time    (componantDidMount)

  console.log("check render"); 


  return (

    <>
      <button className='bg-[gray] px-[30px]' type="button" onClick={() => { setState(state += 1) }}>{state}</button>

    </>
  )
}

export default UseEffectTask1