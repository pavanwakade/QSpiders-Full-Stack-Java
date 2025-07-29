import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { addToCart, EmptyCart, removeFromCart } from './slice';

const ReduxApp = () => {
    let dispatch = useDispatch();
    let data = useSelector((state) => state.cartItems.count);

    return (
        <div className='flex flex-col items-center justify-center gap-3 '>
            <p>cartItems : {data}</p>
            <button type='button' className="px-2 py-1 transition-transform duration-200 transform bg-blue-500 rounded-md hover:scale-105" onClick={() => dispatch(addToCart(100))}>add to cart</button>
            <button type='button' className="px-2 py-1 transition-transform duration-200 transform bg-[#ff2c2c] rounded-md  hover:scale-105" onClick={() => dispatch(removeFromCart(100))}>Remove From cart</button>
            <button type='button' className="px-2 py-1 transition-transform duration-200 transform bg-[#4ddbff] rounded-md hover:scale-105" onClick={() => dispatch(EmptyCart())}>Empty cart</button>
        </div>
    )
}

export default ReduxApp