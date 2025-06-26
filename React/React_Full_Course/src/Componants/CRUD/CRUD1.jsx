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

            <div>
                {
                    list.map((val, i) => (
                        <div key={i}>{val.userName}  {val.password}
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