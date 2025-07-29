import React from 'react'
import { Link, Links, Outlet } from 'react-router-dom'

const Home = () => {
  return (
    // <div>Home
    //     <Link to={"/"}>Login</Link> <hr />
    //     <Link to={"admin"}>Admin</Link>
    //     <Outlet />
    // </div>
    <div>
      <nav className='h-[10%] bg-slate-400 w-80%' >
        <Link to={"/"}>Login</Link> 
        <Link to={"/admin"}>Admin</Link>
      </nav>

      <aside className='h-[90%] bg-slate-300 w-[20%]'>
        <Link to={"/"}>Login</Link> <br />
        <Link to={"/admin"}>Admin</Link>
      </aside>

      <main className='h-[90%] bg-slate-200 w-[80%]'>
        <h1>Welcome to Home Page</h1>
      </main>

      <Outlet />
    </div>
  )
}

export default Home



// !useNavigate