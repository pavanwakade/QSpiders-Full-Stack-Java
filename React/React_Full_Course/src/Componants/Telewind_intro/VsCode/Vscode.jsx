import Vs_Aside from "./Vs_Aside";
import VscodeFormat from "./VscodeFormat";


let Vscode = () => {

    return (

        <>

            {/* <Telewindintro /> */}

            <div className="h-[100vh] flex">
                <Vs_Aside />
                <VscodeFormat></VscodeFormat>
                {/* <TextArea /> */}
                {/* <Files /> */}
                {/* <Search /> */}
            </div>
        </>
    )
}
export default Vscode;