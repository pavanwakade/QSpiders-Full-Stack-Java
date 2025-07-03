import axios from "axios"
import { useEffect } from "react"

const Axios1 = () => {
    useEffect(() => {
        axios.get(`https://jsonplaceholder.typicode.com/todos`).then((api) => {
            console.log(api.data);
        })

        axios.get(`https://api.github.com/users`).then((api) => {
            console.log(api.data);
        })
    }, [])

    return (
        <>

        </>
    )
}

export default Axios1