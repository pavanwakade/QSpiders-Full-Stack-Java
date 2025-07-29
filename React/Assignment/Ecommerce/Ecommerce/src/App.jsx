import React from 'react'
import { RouterProvider } from 'react-router-dom'
import { routers } from './Layout/AppRouter'

const App = () => {
  return (

    <RouterProvider router={routers}></RouterProvider>
    // <>hii</>
  )
}

export default App