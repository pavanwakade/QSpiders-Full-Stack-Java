import { useState } from "react";

let Card = () => {

    let [count, setcount ] = useState(0);
    return (
        <div>
            <div className="card" style={{ position:"relative",color: "black", height: "300px", width: "300px", display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center" }}>

                <h4 style={{position:"absolute",top:"0px"}}> Hello Card</h4>
                <h2>cart</h2>

                <h1>{count}</h1>
                <button onClick={() => {
                    setcount(count + 1)
                }} 
                style={{}}
                >add</button>

            </div>
        </div>
    )
}

export default Card;