import React from 'react';

const CardsRandomColor = ({ name, color }) => {
    return (
        <>
            {/* <div className='flex flex-wrap'> */}

                <div
                    className="flex items-center justify-center w-[30%] h-[180px] font-bold text-white rounded-lg shadow-md  transition-transform duration-200 hover:scale-105 hover:text-black"
                    style={{ backgroundColor: color }}
                >
                    <p>{name}</p>
                </div>
            {/* </div> */}
        </>
    );
};

export default CardsRandomColor;
