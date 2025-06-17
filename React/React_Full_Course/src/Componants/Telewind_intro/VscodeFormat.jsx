import arrowImg from './../../assets/arrow.png';
import vscodepng from './../../assets/vscode.png';

let VscodeFormat = () => {
    return (
        <div className="h-[100vh] w-[100%] bg-[#0f0f0fd5] ">

            <div className="navbar h-[40px]  text-[white]  bg-[#5a5a5a] flex ">
                <div className="span1 flex h-full w-[30%] text-start justify-around items-center">
                    <a href=""><img src={vscodepng} alt="" className='h-[38px]'/></a>
                    <a href="">File</a>
                    <a href="">Selection</a>
                    <a href="">View</a>
                    <a href="">Go</a>
                    <a href="">Run</a>
                </div>
                <div className="span2 flex h-full w-[40%] items-center px-2 justify-around">
                    <a href="#" className="mr-2"><img src={arrowImg} style={{ height: "13px", transform: "rotate(180deg)" }} alt="backward" /></a>
                    <a href="#"><img src={arrowImg} style={{ height: "13px" }} alt="forward" /></a>
                    <input type="search" name="" id="" className='w-[90%] h-[70%] bg-[#3b3b3b] border border-solid white p-[1px] rounded-md outline-none text-center' placeholder='🔎 React ' /> 
                </div>
                <div className="span3"> </div>
            </div>

            <div></div>

        </div>
    )
}

export default VscodeFormat;