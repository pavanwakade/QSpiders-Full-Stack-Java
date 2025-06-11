
// let Jxsintro = () => {
//     return (
//         <div>
//           <div>
//              {/* <JsxTask1 /> */}
//            <h1>hello</h1>
//           </div>
//         </div>
//     );


// }

// export default Jxsintro;

import { Component } from "react";
import JsxTask1 from './tasks/JsxTask1.jsx';

class Jxsintro extends Component{

    render(){
        return(

          <div>
           {/* <h1>hello</h1> */}
          <JsxTask1 />
          </div>

        );
    }
}

export default Jxsintro;