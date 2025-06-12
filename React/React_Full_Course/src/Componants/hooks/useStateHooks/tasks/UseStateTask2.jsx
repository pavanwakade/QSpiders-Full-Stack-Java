let UseStateTask2 = () => {
    let count = 0;
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