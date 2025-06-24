import PropsTasks1Child from './PropsTasks1Child'

const PropsTasks1 = () => {
  return (
    <>
    <div>
        PropsTasks1
    </div>
     {/* <PropsTasks1Child name="pavan"/>     {name: 'pavan'} */}
     <PropsTasks1Child props={{id:1,names:"pavan"}}/>     {/*  {props: {…}}props: {id: 1, names: 'pavan'} */}  {/*here we shouls take double {{}} because {} for jsx  another for {} object */}
     {/* <PropsTasks1Child props={()=>{  console.log("hello")   }}/>   */}
     
    </>
  )
}

export default PropsTasks1