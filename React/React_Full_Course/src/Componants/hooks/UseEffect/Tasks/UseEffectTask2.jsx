import { useEffect, useState } from "react";

const UseEffectTask2 = () => {
    let [state1, setState1] = useState(0);
    let [state2, setState2] = useState(0);
    useEffect(() => {

        console.log("task 2");
    }, [])

    useEffect(() => { }, [state1])
    return (
        <>

        </>
    )
}

export default UseEffectTask2