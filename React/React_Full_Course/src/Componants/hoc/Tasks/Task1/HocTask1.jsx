import Cbc1 from './Cbc1';
import Hoc1 from './Hoc1';


let ReturnedFun = Hoc1(Cbc1); //! higher order function
const HocTask1 = () => {
  return (
    <>
      <ReturnedFun />
    </>
  )
}

export default HocTask1