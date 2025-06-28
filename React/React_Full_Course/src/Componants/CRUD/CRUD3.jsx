import React, { useState } from "react";

let CRUD3 = ()=>
{
    let [user,setUser] = useState();
    let [pass,setPass] = useState();
    let [email,setEmail] = useState();
    let [contact,setContact] = useState();

    // let id = Date.now();
    // console.log(id);
    
    let [list,setList] = useState([]);

    let handleSubmit = (e) =>
    {
        e.preventDefault(); 
        setList([...list,{userName:user,passWord:pass,userEmail:email,conatctNumber:contact,userId:Date.now()}]);
    }
    console.log(list);
    
    return(
        <div >
            <form action="" className="flex p-5 flex-col space-y-2  items-center  w-{100%} h-{100%} justify-between bg-gray-500">
                <input type="text" className="bg-slate-100 border border-white px-4 py-2 rounded h-8" placeholder="Enter Name" onChange={(e)=>{setUser(e.target.value)}}/>
                <input type="text" className="bg-slate-100 border border-white px-4 py-2 rounded h-8" placeholder="Enter Password" onChange={(e)=>setPass(e.target.value)}/>
                <input type="text" className="bg-slate-100 border border-white px-4 py-2 rounded h-8" placeholder="Enter Email" onChange={(e)=>setEmail(e.target.value)}/>
                <input type="text" className="bg-slate-100 border border-white px-4 py-2 rounded h-8" placeholder="Enter Contact_Number" onChange={(e)=>setContact(e.target.value)}/>

                <button onClick={handleSubmit} className="bg-gray-600 text-white border-white px-4 py-2 rounded ">Create</button>
            </form>
            <hr/>
            <div className="flex bg-slate-400 space-x-2 justify-evenly flex-wrap "> 
                {
                    list.map((val,i)=>
                    {
                        return(
                            <React.Fragment key={i} >
                                <div className="h-[15%] w-[15%] bg-cyan-400 space-x-2 space-y-2 border border-black rounded py-6 px-4 ">
                                    <p></p>
                                    <p>Name     : {val.userName}</p>
                                    <p>PassWord : {val.passWord}</p>
                                    <p>Email    : {val.userEmail}</p>
                                    <p>Contact  : {val.conatctNumber}</p>
                                    <div className="flex justify-evenly">
                                        <button className="bg-gray-600 text-white border-white rounded  px-4 py-2 ">Update</button>
                                        <button className="bg-gray-600 text-white border-white rounded px-4 py-2">Delete</button>
                                    </div>
                                </div>    
                                                            
                            </React.Fragment>
                        )
                    })
                }
            </div>
        </div>
    )
}

export default CRUD3;