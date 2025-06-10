import { Component } from "react";
import './App.css';
import Aside from './Aside';
import Footer from './Componant/Footer';
import MainBody from './Componant/MainBody';
import NavBar from './Componant/NavBar';



class App  extends Component {
    render() {
        return (
          <div>
          <NavBar></NavBar>
          <div className='mainbody'>
          <Aside></Aside>
          <MainBody></MainBody>
          </div>
          <Footer></Footer>
          </div>
        );
    }
}
 export default App;


 // function App() {
//   return (
  
    
     
//       <div className="card" 
//       style={{color: 'black'}}> 
//         <button onClick={() => setCount((count) => count + 1)}    
//           style={{backgroundColor: count === 0 ? 'green' : count < 5 ? 'yellow' :count > 10 ? 'red': 'white'}}>
//           count is {count}   
//         </button>
       
//       </div>
  
//   )
// }