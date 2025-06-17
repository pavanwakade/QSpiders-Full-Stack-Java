let Telewindintro = () => {

    return (
        // <div className='Text-lg'>
        //     <h1>hello from Tailwind</h1>
        // </div>
<div className="bg-[#353434] h-[50px] text-white flex items-center justify-between px-10">
  {/* Left Section - Logo */}
  <div className="text-[25px] font-semibold">
    <a href="#">Logo</a>
  </div>

  {/* Right Section - Navigation Links */}
  <div className="flex space-x-8 text-[18px]">
    <a href="#" className="hover:underline decoration-[green] decoration-2">Home</a>
    <a href="#" className="hover:underline">About</a>
    <a href="#" className="hover:underline">Career</a>
  </div>
</div>

    )

}

export default Telewindintro;