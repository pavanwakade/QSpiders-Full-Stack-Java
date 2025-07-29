import Admin from "../Pages/Admin";
import Home from "../Pages/Home";
import Login from "../Pages/Login";
import MainPage from "../Pages/MainPage";
import { createBrowserRouter } from "react-router-dom";
// import ErrorPage from "../Pages/ErrorPage";


export let routers = createBrowserRouter([
    {
        path: "/",
        element: <Login />,
        //  errorElement: <ErrorPage />,
    },
    {
        path: "/home",
        element: <Home />,
        children: [
            {
                path: "/home",
                element: <MainPage />,
                // errorElement: <ErrorPage />,
            },
            {
                path: "/home/admin",
                element: <Admin />
            }


        ]
    },

    {
        path: "/admin",
        element: <Admin />
    }
]);
