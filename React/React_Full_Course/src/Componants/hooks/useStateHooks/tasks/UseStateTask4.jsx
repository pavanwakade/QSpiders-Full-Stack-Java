import { useState } from "react";
import "./UseState4Css.css";

let UseisDarkTask4=()=>{
    let [isDark,setDark]=useState(false);
    return(
        <div id="pp" className={isDark?"dark":"light"}>
            <h1>dark and light Mode</h1>
            <button onClick={()=>{setDark(!isDark)}}>
                {isDark?"light":"dark"}
                </button>


        </div>
    )
}

export default UseisDarkTask4;