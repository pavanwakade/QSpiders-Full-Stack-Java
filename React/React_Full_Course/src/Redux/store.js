import { configureStore } from "@reduxjs/toolkit";
import cartSlice from './slice.js' // <-- fixed import path

export let store = configureStore({
    reducer: {
        cartItems: cartSlice
    }
})