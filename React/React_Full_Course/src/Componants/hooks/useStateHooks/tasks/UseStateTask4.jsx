import { useState } from "react";
import "./UseState4Css.css";

let UseStateTask4=()=>{
    let {state,setState}=useState(0);
    return(
        <div className={state?"dark":"light"}>
            <button onClick={()=>{}}>{state?"dark":"light"}</button>
            <h1>dark and light Mode</h1>


        </div>
    )
}

export default UseStateTask4;