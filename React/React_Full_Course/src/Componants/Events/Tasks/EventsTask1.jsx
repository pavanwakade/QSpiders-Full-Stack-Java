
const EventsTask1 = () => {



  let handleMyMouse = (e, arg) => {
    e.target.style.backgroundColor = "yellow"
    console.log(e,arg);

  }
  return (
    <>
      <div>color change on hovor and else reset</div>


      {/* <div onMouseEnter={(e)=>{e.target.style.backgroundColor="yellow"}}
        onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}
    
        className='p-[20%]'
        >hello</div> */}



      {/*.//! when we want to pass argument with events than we need to create function pass in them */}

      <div onMouseEnter={(e) => { handleMyMouse(e, 10) }}
      
       onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}
        className='p-[20%]'>
        hello</div>
    </>
  )
}

export default EventsTask1