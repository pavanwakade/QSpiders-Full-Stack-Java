import { createSlice } from "@reduxjs/toolkit"

let initialState = {
    count: 0
}

let cartSlice = createSlice({
    name: "cart",
    initialState,
    reducers: {
        addToCart: (state, action) => {
            return { ...state, count: state.count + action.payload }
        },
        removeFromCart: (state, action) => {
            return { ...state, count: state.count - action.payload }
        },
        EmptyCart: () => {
            return initialState
        }
    }
})

export let { addToCart, removeFromCart, EmptyCart } = cartSlice.actions
export default cartSlice.reducer