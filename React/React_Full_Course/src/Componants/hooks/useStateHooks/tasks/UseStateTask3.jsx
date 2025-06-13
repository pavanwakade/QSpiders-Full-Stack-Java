import { useState } from "react";

let UseStateTask3 = () => {
    let [state, setState] = useState(false);

    return (
        <div>

            <button onClick={() => {
                setState(!state)
            }}>
                {
                    (state ? "hide" : "show")
                }
            </button>

            {
                state ? <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Harum quas quis, optio nostrum vel perspiciatis neque ea provident, nesciunt maxime aspernatur tenetur totam cum! Accusantium culpa totam ea nulla cumque.
                </p> : ""
            }
        </div>
    )
}


export default UseStateTask3;