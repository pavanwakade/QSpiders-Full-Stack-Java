import React from 'react'

const Alerts = (type,message) => {
    let back;
    if (type==="Error") {
       back="red"    
    }
    else
    {
        back="green"
    }
 return(
    <div style={{background:{back}}}>
        {message}
    </div>
 )
}

export default Alerts
