import axios from "axios";
import { useEffect, useState } from "react";
import Loader from "../../../Loader/Loader";

const Hob2 = (Cbf2) => {
  return () => {
    const [state, setState] = useState(true);
    const [api, setApi] = useState([]);
    useEffect(() => {
      axios.get(`https://api.github.com/users`).then((ap) => {
        setApi(ap.data);
        setState(false)
        console.log(ap.data);
      })
    }, [])
    if (state == true) {
      return <Loader />
    }
    else {
      return <Cbf2 />
    }
  }
}

export default Hob2