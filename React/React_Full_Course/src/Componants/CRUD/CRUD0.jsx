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


    return (
        <>CRUDz
            <div>
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
            <div>
                {
                    list.map((val, i) => (
                        <div key={i}>{val.userName}  {val.password}
                            <br />
                            <button>edit</button>
                            <button>delete</button>

                        </div>
                    ))
                }
            </div>

        </>
    )
}

export default CRUD0