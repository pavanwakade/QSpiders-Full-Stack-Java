let Telewindintro = () => {

    return (
        // <div className='Text-lg'>
        //     <h1>hello from Tailwind</h1>
        // </div>

        <div className="bg-[#353434] h-[50px] text-[#f8f6f6] flex items-center justify-between px-10">
            <div className="text-[25px] font-semibold">
                <a href="#">Logo</a>
            </div>

            <div className="flex space-x-8 text-[18px]">
                <a href="#" className="hover:underline decoration-[#ff03d5] hover:decoration-[3px]">Home</a>
                <a href="#" className="hover:underline decoration-[#ff03d5] hover:decoration-[3px]">About</a>
                <a href="#" className="hover:underline decoration-[#ff03d5] hover:decoration-[3px]">Career</a>
            </div>
        </div>

    )

}

export default Telewindintro;