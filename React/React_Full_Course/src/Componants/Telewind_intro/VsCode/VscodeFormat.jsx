import arrowImg from './../../../assets/arrow.png';
import vscodepng from './../../../assets/vscode.png';

let VscodeFormat = () => {
    return (
        <div className="h-[100%] w-[100%] bg-[#0f0f0fd5] ">
            <div className="navbar h-[40px]  text-[white]  bg-[#5a5a5a] flex z-10">
                <div className="span1 flex h-full w-[30%] text-start justify-around items-center">
                    <a href=""><img src={vscodepng} alt="" className='h-[38px]' /></a>
                    <button>File</button>
                    <button>Selection</button>
                    <button>Edit</button>
                    <button>View</button>
                    <button>Go</button>
                    <button>Run</button>
                </div>
                <div className="span2 flex h-full w-[40%] items-center px-2 justify-around">
                    <button className="mr-2"><img src={arrowImg} style={{ height: "13px", transform: "rotate(180deg)" }} alt="backward" /></button>
                    <button><img src={arrowImg} style={{ height: "13px" }} alt="forward" /></button>
                    <input type="search" name="" id="" className='w-[90%] h-[70%] bg-[#3b3b3b] border border-solid white p-[1px] rounded-md outline-none text-center' placeholder='🔎 React ' />
                </div>
                <div className="span3"> </div>
            </div>

            {/* < Vs_Aside /> */}
        </div>
    )
}


export default VscodeFormat;