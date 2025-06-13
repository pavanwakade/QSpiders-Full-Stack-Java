let UseStateTask2 = () => {
    let count = 0;

    //! it is just for understand  ki without state its not working it is just example
    return (


        <div>

            <h1>{count}</h1>
            <button onClick={() => {
                count += 1;
            }}>add</button>
        </div>
    )
}
export default UseStateTask2;