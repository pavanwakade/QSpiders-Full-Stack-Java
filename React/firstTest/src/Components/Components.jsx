import { Component } from "react";
import Aside from "./Aside";
import Footer from "./Footer";
import MainBody from "./MainBody";
import NavBar from "./NavBar";
import './main.css';

class Components extends Component {
    render() {
        return (
            <div className="container">
                <NavBar className="navbar" />
                <div className="mainbody">
                    <MainBody className="mbody" />
                    <Aside className="aside" />
                </div>
                <Footer className="footer" />
            </div>
        );
    }
}

export default Components;
