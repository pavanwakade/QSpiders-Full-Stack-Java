let NavBar =()=>{
    return(
        <div>
            {/* <h1>hello from nav</h1> */}
            <div className="navbar" style={{ display: "flex",justifyContent:"center",alignItems:"center", height: "70px", width: "100%" ,gap:"45px",backgroundColor:"white",position:"fixed",top:"0px"}}>
                <a href="#home">Home</a>
                <a href="#product">Product</a>
                <a href="#contact">Contact</a>
                <a href="#about">About</a>
                <a href="#cart">Cart</a>
            </div>
        </div>
    )
}

export default NavBar;