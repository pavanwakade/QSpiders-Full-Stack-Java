import React, { useState } from 'react'

const Login = () => {
    let[email,setEmail]=useState('')
    let[password,setPassword]=useState('')
    let handleLogin=()=>{
        if (email==='pavan@gmail.com' && password==='pavan') {
            alert("Login sucessfull")
        }
    }
  return (
    <div>
      <form method="post">
     <h5 id='inputTitle'>Email</h5>  <input type="email" value={email} onChange={(e)=>setEmail(e.target.value)} placeholder='Email' /> <br />
     <h5 id='inputTitle'>Password</h5>       <input type="password" value={password} onChange={(e)=>setPassword(e.target.value)} placeholder='Password' />
     <br />
        <button type="submit" onClick={handleLogin}>Login</button>
      </form>   
       </div>
  )
}

export default Login
