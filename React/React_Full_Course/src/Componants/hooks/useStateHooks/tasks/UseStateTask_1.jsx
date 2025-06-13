import { useState } from "react";

let UseStateTask1 = () => {

    // let x= useState(0);
    // console.log(x);

    let [count, setcount] = useState(0)


    return (
        <div>
            {/* <h1>UseStateHookstask1</h1> */}

            <h2>{count}</h2>
            <button onClick={() => {
                setcount(count += 1)
            }}>add</button>


             <button onClick={() => {
                setcount(count -= 1)
            }}>sub</button>


             <button onClick={() => {
                setcount(0)
            }}>reset</button>
        </div>
    )
}

export default UseStateTask1;