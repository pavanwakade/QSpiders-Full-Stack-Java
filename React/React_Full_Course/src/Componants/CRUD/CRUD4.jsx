import { useState } from "react";

let CRUD4 = ()=>
{
    let [state,setState] = useState({
        userName : "",
        passWord : "",
        id : Date.now(),
        list : [
            // {
            //     userName : "u1",
            //     passWord : "p1",
            //     id  : Date.now()+1
            // },
            // {
            //     userName : "u2",
            //     passWord : "p2",
            //     id  : Date.now()+2
            // },
            // {
            //     userName : "u3",
            //     passWord : "p3",
            //     id  : Date.now()+3
            // }
    ]
    })
    let {userName,passWord,list,id} = state;

    let handleChange = (e) =>
    {
        let{name,value}=e.target;
        setState({...state,[name]:value})
    }

    let handleSubmit = (e)=>
    {
        e.preventDefault();
        let tempObj = {
            userName :state.userName,
            passWord : state.passWord,
            id : Date.now()
        }
        setState({...state,list:[...state.list,tempObj]})
    }   
    console.log(list);
    
    let handleUpdate = (id) =>
    {
        let obj = state.list.find((val)=>
        {
            return val.id == id;
        })
        let filteredList =state.list.filter((val)=>{
            return val.id !=id
        })
        setState({userName:obj.userName,passWord:obj.passWord,id:obj.id,list:filteredList})
    }

    let handleDelete = (e) =>
    {
        e.preventDefault();
         let filteredList =state.list.filter((val)=>{
            return val.id != id
         })
         setState({...state,list:filteredList})
    }

    return(
        <div>
            <form action="" className="flex p-5 flex-col space-y-2  items-center  w-{100%} h-{100%} justify-between bg-gray-500">
                <input type="text"  placeholder="Enter name" name="userName" value={userName} onChange={handleChange} className="bg-slate-100 border border-white px-4 py-2 rounded h-8"/>
                <input type="text"  placeholder="Enter Password" name="passWord" value={passWord} onChange={handleChange} className="bg-slate-100 border border-white px-4 py-2 rounded h-8"/>
                <button onClick={handleSubmit} className="bg-gray-600 text-white border-white px-4 py-2 rounded ">Create</button>                
            </form>  
            <hr />
            <div className="flex bg-slate-400 space-x-2 justify-evenly flex-wrap ">
                {
                    state.list.length>0 && state.list.map((val,i)=>
                    {
                        return(
                            <div className="h-[15%] w-[15%] bg-cyan-400 space-x-2 space-y-2 border border-black rounded py-6 px-4 " key={i}>
                                <p></p>
                                <p>Name : {val.userName}</p>
                                <p>Password : {val.passWord}</p>
                                <div className="flex justify-evenly">
                                    <button className="bg-gray-600 text-white border-white rounded  px-4 py-2 " onClick={()=>{handleUpdate(val.id)}}>Update</button>
                                    <button className="bg-gray-600 text-white border-white rounded  px-4 py-2 " onClick={()=>{handleDelete(val.id)}}>Delete</button>
                                </div>
                            </div>
                        )
                    })
                }
            </div>        
        </div>
    )
}
export default CRUD4;

//Which we give all the information about the current element and  target element.
