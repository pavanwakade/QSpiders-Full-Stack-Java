import { Component } from "react";
import Aside from "./Aside";
import Footer from "./Footer";
import MainBody from "./MainBody";
import NavBar from "./NavBar";

class Components extends Component {
    render() {
        return (
            <div className="container">
                <nav className="navbar">
                    <NavBar />
                </nav>
                <div className="mainbody">
                    <MainBody />
                    <aside className="aside">
                        <Aside />
                    </aside>
                </div>
                <footer className="footer">
                    <Footer />
                </footer>
            </div>
        );
    }
}

export default Components;