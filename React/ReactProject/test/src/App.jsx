import './App.css';
import {  Componant}  from "react";
import Aside from './Componant/Aside';
import Footer from './Componant/Footer';
import MainBody from './Componant/MainBody';
import NavBar from './Componant/NavBar';
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
