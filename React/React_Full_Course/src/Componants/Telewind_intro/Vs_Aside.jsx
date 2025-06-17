import filepng from './../../assets/files.png';
import searchs from './../../assets/search-64.png';
import extention from './../../assets/extension-64.png';
import sourse from './../../assets/share-64.png';
import debug from './../../assets/debug-64.png';

let Vs_Aside = () => {
  return (
    <div className="flex flex-col items-center gap-y-4 bg-[#292929] w-[5%] h-[100%]">
      <button><img src={filepng} alt="File" className="h-[30px] m-[10px]" title='files' /></button>
      <button><img src={searchs} alt="File" className="h-[30px] m-[10px]" title='search' /></button>
      <button><img src={sourse} alt="File" className="h-[30px] m-[10px]" title='sourse' /></button>
      <button><img src={extention} alt="File" className="h-[30px] m-[10px]" title='extensions' /></button>
      <button><img src={debug} alt="File" className="h-[30px] m-[10px]" title='debug' /></button>
    </div>
  );
};

export default Vs_Aside;
