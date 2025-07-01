import { useState } from "react"

const CRUD0 = () => {
    const [user, setUser] = useState("")
    const [pass, setPass] = useState("")
    const [list, setList] = useState([])

    let handleSubmit = (e) => {
        e.preventDefault();
        setList([...list, { userName: user, password: pass }])
    }
    console.log(list);

                  let h = <hr className="my-5 border border-amber-500 w-[100%]" />;

    return (
        <>
        <h2>CRUD0</h2>
            <div className="flex align-content-center justify-center w-[30%] border border-red-500">
                <form>
                    <input type='text'
                        placeholder='enter name'
                        onChange={(e) => { setUser(e.target.value) }}
                    />
                    <br /><br />

                    <input type='text'
                        placeholder='enter password'
                        onChange={(e) => { setPass(e.target.value) }}
                    />

                    <br /> <br />

                    <button onClick={handleSubmit}>signup</button>

                </form>
            </div>
{h}
            <div className="flex border p-[10px] flex-wrap m-[10px]">
                {
                    list.map((val, i) => (
                        <div key={i}    className="flex border p-[10px] ">{val.userName}  {val.password}
                            <br />
                            <button className="flex border px-[10px]">edit</button>
                            <button className="flex border px-[10px]">delete</button>

                        </div>
                    ))
                }
            </div>

        </>
    )
}

export default CRUD0