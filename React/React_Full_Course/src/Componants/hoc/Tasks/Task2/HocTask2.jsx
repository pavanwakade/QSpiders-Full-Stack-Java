import Cbc2 from './Cbf2';
import Hoc2 from './Hob2';




let ReturnedFun = Hoc2(Cbc2); //! higher order function
const HocTask2 = () => {
  return (
    <>
      <ReturnedFun />
    </>
  )
}

export default HocTask2