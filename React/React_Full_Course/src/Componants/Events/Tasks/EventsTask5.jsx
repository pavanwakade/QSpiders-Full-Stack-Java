import React from 'react'

const EventsTask5 = () => {
  return (
    <>
    <div>
      <ol className='bg-[#ddc7c7] p-9 text-center text-2xl font-bold list-(--my-marker)'>
        <li onMouseEnter={(e)=>{e.target.style.backgroundColor="red"}} onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}>Home</li>
        <li onMouseEnter={(e)=>{e.target.style.backgroundColor="red"}} onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}>about</li>
        <li onMouseEnter={(e)=>{e.target.style.backgroundColor="red"}} onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}>Services</li>
        <li onMouseEnter={(e)=>{e.target.style.backgroundColor="red"}} onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}>Contact</li>
        <li onMouseEnter={(e)=>{e.target.style.backgroundColor="red"}} onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}>Blog</li>
      </ol>
    </div>
    </>
  )
}

export default EventsTask5