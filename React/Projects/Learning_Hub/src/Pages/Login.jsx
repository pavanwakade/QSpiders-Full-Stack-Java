import React, { useState } from 'react'
import Swal from 'sweetalert2'
// import Alerts from '../HOF/Alerts'

const Login = () => {
    let [email, setEmail] = useState('')
    let [password, setPassword] = useState('')
    let handleLogin = (e) => {
        e.preventDefault();
        if (email === 'pavan@gmail.com' && password === 'pavan') {
            Swal.fire({
                title: 'Login Successful!',
                text: 'Welcome back!',
                icon: 'success',
                confirmButtonColor: '#8b5cf6', // purple button
                confirmButtonText: 'OK'
            });
            
            setEmail('')
            setPassword('')
        }
        else {
            Swal.fire({
                title: 'Login Failed',
                text: 'Email or password is incorrect!',
                icon: 'error',
                confirmButtonColor: '#8b5cf6', // purple button
                confirmButtonText: 'Try Again'
            });
            // <Alerts type="Error" message="hello" />
            setEmail('')
            setPassword('')
        }
    }
    return (
        <div>
            <form className='flex flex-col items-center justify-center h-screen gap-3'>
                <span> <p id='inputTitle'>Email</p>
                    <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder='Email' />
                </span>
                <span>
                    <p id='inputTitle'>Password</p>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder='Password' />
                </span>
                <button type="submit" onClick={handleLogin} id='loginbutton'>Login</button>
            </form>
        </div>
    )
}

export default Login
