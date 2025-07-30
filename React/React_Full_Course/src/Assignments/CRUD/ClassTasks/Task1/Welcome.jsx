import React from 'react'
const Welcome = ({ name }) => {

  return (
   <div class="fixed top-10 right-10 bg-[#53df53] text-white px-6 py-3 rounded-lg shadow-lg opacity-0 animate-[fadeout_3s_ease-in-out_forwards]">
  Welcome, {name}!
</div>


  );

}
export default Welcome;
