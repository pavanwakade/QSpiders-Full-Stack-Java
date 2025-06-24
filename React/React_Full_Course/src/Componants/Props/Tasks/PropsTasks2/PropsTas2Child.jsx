
const PropsTas2Child = (props) => {
    console.log(props);
    
    let{props:{state,setState}}=props;
    console.log(state);
    
  return (
    <>
    <div>PropsTas2Child</div>
    <h1>{state}</h1>
<button onClick={()=>{setState(state+1)}}    className="bg-[#7dd37d] ">add</button>

    </>
  )
}

export default PropsTas2Child