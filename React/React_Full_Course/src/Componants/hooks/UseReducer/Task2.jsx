import React from 'react'
import { useReducer } from 'react'

const Task2 = () => {
    let initial = {
        items: {
            id: Date.now(),
            name: '',
            price: '',
            quantity: ''
        },
        cartitem: []
    }
    let reducer = (state, action) => {
        switch (action.type) {
            case 'ADD_ITEM':
                return { ...state, items: { ...state.items, [action.payload.name]: action.payload.value } }

            case 'CREATE_ITEM':
                return {
                    ...state, createItems: [...state.cartitem, state.items], items: {
                        id: Date.now(),
                        name: '',    
                        price: '',
                        quantity: ''
                    }
                }

            default: return state
        }
    }
    let [state, dispatch] = useReducer(reducer, initial)
    let [id, name, quantity] = state.items
    let handlechange = (e) => {
        dispatch({
            type: 'add',
            payload: { name: e.target.name, value: e.target.value },
        });
    };

    let handleChange = (e) => {
        e.preventDefault();
        dispatch({
            type: 'CREATE_ITEM'
        })

    }
    return (
        <>
            <div>UseReducer</div>
            <div>

            </div>
        </>
    )
}

export default Task2