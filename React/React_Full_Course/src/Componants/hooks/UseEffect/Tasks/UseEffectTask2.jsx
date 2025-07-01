import { useEffect, useState } from "react";

const UseEffectTask2 = () => {
    let [state1, setState1] = useState(0);
    let [state2, setState2] = useState(0);
    useEffect(() => {

        console.log("task 2");
    }, [state1])

    useEffect(() => { }, [])
    return (
        <>

        </>
    )
}

export default UseEffectTask2