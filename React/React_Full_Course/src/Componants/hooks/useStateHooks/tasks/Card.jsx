import { useState } from "react";

let Card = () => {

    let [count, setcount] = useState(0);
    let[state,setState]=useState(0);
    return (
        <div>
            <div className="card" style={{ position: "relative", color: "black", height: "300px", width: "300px", display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center" }}>

                <h4 style={{ position: "absolute", top: "0px" }}> Hello Card</h4>
                <h2>cart</h2>

                <h1>{count}</h1>
                <div className="buttons" style={{ display: "flex", margin: "17px" }}>
                    <button onClick={() => {
                        setcount(count + 1)
                    }}
                        style={{ height: "35px", width: "50px", border: "1px black  solid" }}
                    >add</button>
                    <button onClick={() => {
                        setcount(count - 1)
                    }}
                        style={{ height: "35px", width: "50px", border: "1px black  solid" }}
                    >sub</button>

                    <button onClick={() => {
                        setcount(0)
                    }}
                        style={{ height: "35px", width: "50px", border: "1px black  solid" }}
                    >reset</button>
                </div>

                <button onClick={()=>{
                    setState(!state)
                }}>{(state ? "hide" : "show")}</button>
                {
                    state ?                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus tempora sit quas debitis vitae iste nostrum? Praesentium modi debitis alias ad illum reiciendis delectus dolores dolorem mollitia quidem, odit maxime.</p>
:""
                }
            </div>
        </div>
    )
}

export default Card;