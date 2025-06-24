import { useState } from "react";
import { FiAlignCenter, FiAlignJustify } from "react-icons/fi";

const EventsTask2 = () => {
    let [state, setState] = useState(true);

    return (
        <>
            {/* EventsTask2 */}
            <button onClick={() => setState(!state)}>
                {state
                    ? <FiAlignJustify size={30} />
                    : <FiAlignCenter size={30} />
                }
            </button>

            <div>
                {state
                    ? ""
                    : <div className="flex flex-col gap-[20px] pt-[30px] items-center w-[20%] h-[100vh] bg-[#e9c5c5]">
                        <a href="#">Home</a>
                        <a href="#">About</a>
                        <a href="#">Services</a>
                        <a href="#">Contact</a>
                        <a href="#">Help</a>
                    </div>
                }
            </div>
        </>
    );
};

export default EventsTask2;
