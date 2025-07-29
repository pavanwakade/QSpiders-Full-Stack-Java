import React, { useEffect, useState } from 'react'
import axios from "axios";

const Api = () => {
    let [Apistate, setApi] = useState([])

useEffect(()=>{

    axios.get('https://api.github.com/users').then((response) => {


        setApi(response.data)

        // console.log(data);
        });
    }, [])

    return (
        <div>{
            Apistate.map((val, i) => (

                <div key={i}>
                    <img src={val.avatar_url}></img>
                    <h1>{val.login}</h1>
                    <h1>{val.login}</h1>
                </div>
            ))
        }

        </div>
    )
}


export default Api