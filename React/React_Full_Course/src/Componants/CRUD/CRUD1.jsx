import { useState } from "react"

const CRUD = () => {
    const [user, setUser] = useState("")
    const [pass, setPass] = useState("")
    const [list, setList] = useState([])

    let handleSubmit = (e) => {
        e.preventDefault();
        setList([...list, { userName: user, password: pass }])
    }
    console.log(list);

    return (
        <>CRUD
<div>

            <form >
                <input type='text'
                    placeholder='enter name'
                    onChange={(e) => { setUser(e.target.value) }}
                    className="text-center border border-[gray]"
                />
                <br /><br />

                <input type='text'
                    placeholder='enter password'
                    onChange={(e) => { setPass(e.target.value) }}
                    className="text-center border border-[gray]"
                />

                <br /> <br />

                <button onClick={handleSubmit}    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-4 rounded mr-2">signup</button>

            </form>
                </div>

            <div >
                {
                    list.map((val, i) => (
                        <div key={i}>
                           <span> {val.userName} </span> 
                           <span>{val.password}</span>
                            <br />
                           <button
  className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-4 rounded mr-2">edit</button>
<button
  className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-4 rounded"
>
  delete
</button>
                        </div>
                    ))
                }
            </div>


        </>
    )
}

export default CRUD