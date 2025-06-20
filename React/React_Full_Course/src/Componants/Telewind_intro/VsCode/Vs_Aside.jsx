import { useState } from 'react';
import debug from './../../../assets/debug-64.png';
import extention from './../../../assets/extension-64.png';
import filepng from './../../../assets/files.png';
import searchs from './../../../assets/search-64.png';
import sourse from './../../../assets/share-64.png';
import Debug from './Debug';
import Files from './Files';
import Search from './Search';

let Vs_Aside = () => {
  const [active, setActive] = useState('');

  const renderComponent = () => {
    switch (active) {
      case 'Files':
        return <Files />;
      case 'Search' : return <Search />
      case 'Debug' : return <Debug />
      default:
        return null;
    }
  };


  return (
    <>
      <div className="flex flex-col items-center gap-y-4  bg-[#5a5a5a]  w-[4%] h-[100%] z-0 fixed top-10 left-0">
        <button onClick={() => setActive(active === 'Files' ? '' : 'Files')}><img src={filepng} alt="File" className="h-[25px] m-[10px]" title='files' /></button>
        <button onClick={() => setActive(active==='Search'?'':'Search')}><img src={searchs} alt="search" className="h-[25px] m-[10px]" title='search' /></button>
        <button onClick={() => setActive(active==='')}><img src={sourse} alt="sourse" className="h-[25px] m-[10px]" title='sourse' /></button>
        <button onClick={() => setActive(active==='Debug'?'':'Debug')}><img src={debug} alt="Debug" className="h-[25px] m-[10px]" title='debug' /></button>
        <button onClick={() => { }}><img src={extention} alt="extensions" className="h-[25px] m-[10px]" title='extensions' /></button>
      </div>
      {renderComponent()}
    </>
  );
};
export default Vs_Aside;
