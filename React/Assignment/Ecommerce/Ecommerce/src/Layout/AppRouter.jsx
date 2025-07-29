import { createBrowserRouter } from 'react-router-dom';
import Login from '../Pages/Login';
import Home from '../Componants/Home';
import Products from '../Componants/Products';
import SignUp from '../Pages/SignUp';
import About from '../Pages/About';
export let routers = createBrowserRouter([
    {
        path: '/',
        element: <Login />,
    },
    {
        path: '/signup',
        element: <SignUp />,
    },
    {
        path: '/home',
        element: <Home />,
        children: [
            {
                path: '/home',
                element: <Products />
            },
            {
                path: '/home/about',
                element: <About />
            }
        ]
    }
])
