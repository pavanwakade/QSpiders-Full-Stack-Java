import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useState } from 'react'
const Login = () => {
  let [userName, setUserName] = useState("");
  let [pass, setPass] = useState("");

  let navigate = useNavigate();  //used to redirect to another page
  let hangleSubmit = (e) => {
    e.preventDefault();
    if (userName === "admin" && pass === "admin") {
      alert("Login Successfull");

      // Redirect to home page
      navigate("/home");

    } else {
      alert("Login Failed");
    }
  }
  return (
    <div>

      {/* <Link to={"/home"}>Home</Link> */}
      <h1>Login Page</h1>
      <form>
        <input type="text" placeholder='Enter UserName' className='border-[1px]' onChange={(e) => { setUserName(e.target.value) }} />
        <br />
        <input type="password" placeholder='Enter Password' className='border-[1px]' onChange={(e) => { setPass(e.target.value) }} />
        <br />
        <button onClick={hangleSubmit} className='border-[1px]'>Login</button>
      </form>
      <br />

    </div>
  )
}

export default Login