import ToHeadbg from './../../../assets/ToDoBg.jpg';

export const TodoApp = () => {
  return (
    <>
      <div
        className=" h-[150px] bg-cover bg-center  blur-[1.3px] relative"
        style={{ backgroundImage: `url(${ToHeadbg}) ` }}
      ></div>
        <div className='text-[#ffffff] text-4xl font-extrabold absolute top-[15%] left-[45%]'>To Do List</div>
       
    
    </>
  );
};

export default TodoApp;
