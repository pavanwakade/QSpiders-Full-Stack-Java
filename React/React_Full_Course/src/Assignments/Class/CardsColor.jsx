import React from 'react';
import CardsRandomColor from './CardsRandomColor';

const CardsColor = () => {
    let arr = [
        "Ramu", "Shamu", "Ketan", "Navnath", "Amit",
        "Priya", "Sonal", "Vikas", "Neha", "Rahul"
    ];
    return (
        <div className='flex flex-wrap justify-between gap-4 p-4 '>
            {
                arr.map((name, index) => {
                    let randomColor = `#${Math.floor(Math.random() * 16777215).toString(16).padStart(6, '0')}`;
                    return <CardsRandomColor key={index} name={name} color={randomColor} />;
                })
            }
        </div>
    );
};

export default CardsColor;
