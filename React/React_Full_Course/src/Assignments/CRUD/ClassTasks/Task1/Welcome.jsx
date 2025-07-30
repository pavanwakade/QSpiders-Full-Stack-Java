import React from 'react'
const Welcome = ({ name }) => {

  return (
    <div className="fixed top-10 right-[40%] bg-[#53df53] text-white px-6 py-3 rounded-lg shadow-lg">
      Welcome, {name}!
    </div>
  );

}
export default Welcome;
