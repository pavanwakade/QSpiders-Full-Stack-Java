class Car {
   constructor() {
    super();

    this.state={
      cartitem: 0,
    };
  }

  render(){
    return(
      <div>
        <h1>{this.state.cartitem}</h1>
        <button onClick={(()=>{
          this.setState({cartitem:this.state.cartitem +=1})
        })}>add</button>
      </div>
    )
  }
}

export default Car