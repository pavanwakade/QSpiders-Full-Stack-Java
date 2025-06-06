import { useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
  
    
     
      <div className="card" 
      style={{color: 'black'}}> 
        <button onClick={() => setCount((count) => count + 1)}    
          style={{backgroundColor: count === 0 ? 'green' : count < 5 ? 'yellow' :count > 10 ? 'red': 'white'}}>
          count is {count}   
        </button>
       
      </div>
  
  )
}

export default App
