import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
export const fetchData = createAsyncThunk(
    'data/fetchData', async () => {
        const response = await fetch('https://api.github.com/users');
        return response.json();
    });

const dataSlice = createSlice({
    name: 'data',
    initialState: {
        items: [],
        loading: false
    },
    reducers: {
        inc :(state,action) => {},
        dec :(state,action) => {}
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchData.pending, (state) => {
                state.loading = true;
            })
            .addCase(fetchData.fulfilled, (state, action) => {
                state.items = action.payload;
                state.loading = false;
            })
            .addCase(fetchData.rejected, (state) => {
                state.loading = false;
            });
    }
});

export const {inc, dec } = dataSlice.actions;

export default dataSlice.reducer;