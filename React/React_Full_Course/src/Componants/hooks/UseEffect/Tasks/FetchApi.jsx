import { useEffect, useState } from 'react';

const FetchApi = () => {

  let [state, setState] = useState(0);
  let [api, setApi] = useState([]);

  // let data;
  let fetchDate = async () => {
    let response = await fetch(`https://api.github.com/users`);  //it call only once 
    let data = await response.json();
    setApi(data);
    console.log("api called");
    
    
  }
  console.log(api);
  
  useEffect(() => {
    fetchDate();
  }, [])
  


  return (
    <>
      <button onClick={() => { setState(state + 1) }}>click : {state}</button>

      <div>
        {
          api.map((item) => {
            return (
              <div key={item.id}>
                <h1>{item.login}</h1>
                <img src={item.avatar_url} alt={item.login} className='w-[80%],justify.center' />
              </div>
            );
          })
        }
      </div>
      <div>
        {}
      </div>

    </>
  )
}

export default FetchApi