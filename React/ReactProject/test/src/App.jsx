import { Component } from "react";
import './App.css';
import Aside from './Componants/Aside';
import Footer from "./Componants/Footer";
import MainBody from './Componants/MainBody';
import NavBar from "./Componants/NavBar";

class App extends Component {
    render() {
        return (
          <div className="app-container">
            <NavBar />
            <div className='mainbody'>
              <Aside />
              <MainBody />
            </div>
            <Footer />
          </div>
        );
    }
}
export default App;