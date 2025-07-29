import React from 'react'
import { RouterProvider } from 'react-router-dom';
import { routers } from './Layout/AppRouts'

const Routing = () => {
    return (
        <>
            <RouterProvider router={routers}></RouterProvider>
        </>
    )
}

export default Routing
