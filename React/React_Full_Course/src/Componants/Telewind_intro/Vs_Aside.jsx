import filepng from './../../assets/files.png';

let Vs_Aside = () => {
  return (
    <div className="flex flex-col items-center gap-y-4 bg-[#292929] w-[5%] h-[100%]">
      <a href="#"><img src={filepng} alt="File" className="h-[30px] m-[10px]" title='files' /></a>
      <a href="#"><img src={filepng} alt="File" className="h-[30px] m-[10px]" title='' /></a>
      <a href="#"><img src={filepng} alt="File" className="h-[30px] m-[10px]" title='' /></a>
      <a href="#"><img src={filepng} alt="File" className="h-[30px] m-[10px]" title='' /></a>
    </div>
  );
};

export default Vs_Aside;
