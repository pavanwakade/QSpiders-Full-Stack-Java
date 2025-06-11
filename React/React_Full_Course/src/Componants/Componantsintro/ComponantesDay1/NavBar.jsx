let NavBar =()=>{
    return(
        <div>
            {/* <h1>hello from nav</h1> */}
            <div className="navbar" style={{ display: "flex", height: "30px", width: "100%" ,gap:"15px"}}>
                <a href="http://">Home</a>
                <a href="http://">Product</a>
                <a href="http://">Contact</a>
                <a href="http://">About</a>
                <a href="http://">Cart</a>

            </div>
        </div>
    )
}

export default NavBar;