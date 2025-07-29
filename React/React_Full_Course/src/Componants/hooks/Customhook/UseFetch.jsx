import { useEffect, useState } from 'react'
import axios from 'axios'

const UseFetch = (url) => {
    let [state, setState] = useState(null);
    useEffect(() => {
        axios.get(url).then((res) => {
            setState(res.data)
        })
    }, [])
    if (state == null) {
        return "loading..."
    }
    else {
        return state
    }
}

export default UseFetch

// memowization
// useMemo
