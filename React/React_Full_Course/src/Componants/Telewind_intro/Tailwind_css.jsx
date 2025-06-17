// import Telewindintro from "./Telewindintro";

import Vs_Aside from "./Vs_Aside";
import VscodeFormat from "./VscodeFormat";

let Tailwind_css = () => {

    return (

        <div>

            {/* <Telewindintro /> */}

            <div className="h-screen flex gap-x-4">
                <VscodeFormat />
                <Vs_Aside className="mt-10" />
            </div>


        </div>
    )
}
export default Tailwind_css;