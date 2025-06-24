
const EventsTask5 = () => {
  return (
    <>
    <div>
      <ol className='bg-[#ddc7c7] p-9 text-center text-2xl font-bold'>
        <li onMouseOver={(e)=>{e.target.style.backgroundColor="red"; e.target.style.cursor="pointer";}} onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}>Home</li>
        <li onMouseOver={(e)=>{e.target.style.backgroundColor="red";e.target.style.cursor="pointer";}} onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}>about</li>
        <li onMouseOver={(e)=>{e.target.style.backgroundColor="red";e.target.style.cursor="pointer";}} onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}>Services</li>
        <li onMouseOver={(e)=>{e.target.style.backgroundColor="red";e.target.style.cursor="pointer";}} onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}>Contact</li>
        <li onMouseOver={(e)=>{e.target.style.backgroundColor="red";e.target.style.cursor="pointer";}} onMouseLeave={(e)=>{e.target.style.backgroundColor=""}}>Blog</li>
      </ol>
    </div>
    </>
  )
}

export default EventsTask5