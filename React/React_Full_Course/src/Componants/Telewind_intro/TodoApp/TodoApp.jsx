import ToHeadbg from './../../../assets/ToDoBg.jpg';

export const TodoApp = () => {
  return (
    <>
      <div
        className=" h-[25vh] bg-cover bg-center  blur-[1.3px] relative" style={{ backgroundImage: `url(${ToHeadbg}) ` }}></div>
        <div className='text-[#ffffff] text-4xl font-extrabold absolute top-[15%] left-[45%]'>To Do List</div>

        <div className='bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500 ... w-[100%] h-[100vh] text-[white] flex justify-center text-center'>
            <table className='justify-center text-center border w-[50%] mt-10'>
                <thead>Todos</thead>
                <thead><button className='bg-[#ff0000bd] p-2'>delete</button></thead>
                <tbody><input type='text'></input>  </tbody>
            </table>
        </div>
    
    </>
  );
};

export default TodoApp;
