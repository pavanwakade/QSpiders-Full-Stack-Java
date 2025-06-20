import Files from "./Files";
import Search from "./Search";
import Vs_Aside from "./Vs_Aside";
import VscodeFormat from "./VscodeFormat";


let Vscode = () => {

    return (

        <div>

            {/* <Telewindintro /> */}

            <div className="h-[100vh] flex">
                <Vs_Aside />
                <VscodeFormat></VscodeFormat>
                {/* <Files /> */}
                {/* <Search /> */}
            </div>
        </div>
    )
}
export default Vscode;