import ToHeadbg from './../../../assets/ToDoBg.jpg';

export const TodoApp = () => {
    return (
        <>
            <div className="relative h-[25vh] w-full overflow-hidden">
                <div
                    className="absolute inset-0 bg-cover bg-center filter blur-sm scale-110"
                    style={{ backgroundImage: `url(${ToHeadbg})` }}
                ></div>
                <h1 className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 text-white text-4xl font-extrabold">
                    To Do List
                </h1>
            </div>

            <div className="bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500 w-full min-h-[75vh] text-white flex justify-center">
                <table className="w-[60%] mt-10 text-center border border-white">

                    <tbody>
                        <tr className="border-t border-white ">
                            <td className="p-4">
                                <input
                                    type="text"
                                    className="p-2 w-full text-black rounded text-center "
                                    placeholder="Enter your task"
                                />
                            </td>
                            <td className="">
                                <button className="bg-[#008000cc] hover:bg-[green] px-4 py-2 rounded">
                                    add
                                </button>
                            </td>

                            <td className="">
                                <button className="bg-red-600 hover:bg-red-700 px-4 py-2 rounded">
                                    Delete
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </>
    );
};

export default TodoApp;
