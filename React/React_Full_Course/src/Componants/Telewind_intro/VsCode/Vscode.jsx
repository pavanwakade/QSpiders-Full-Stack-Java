import Vs_Aside from "./Vs_Aside";
import VscodeFormat from "./VscodeFormat";


let Vscode = () => {

    return (

        <div>

            {/* <Telewindintro /> */}

            <div className="h-[100vh] flex">
                <Vs_Aside />
                <VscodeFormat></VscodeFormat>
            </div>
        </div>
    )
}
export default Vscode;