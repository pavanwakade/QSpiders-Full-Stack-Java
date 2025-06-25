import { useState } from 'react';
import Carousel from 'react-bootstrap/Carousel';
import bg3 from './../../assets/Avengers/download (1).jpeg';
import bg1 from './../../assets/Avengers/GamingWorld.jpeg';
import bg2 from './../../assets/Avengers/spiderman edits.jpeg';
// import bg4 from './../../assets/Avengers/spiderman edits.jpeg';
// import bg5 from './../../assets/Avengers/download.jpeg';

function ControlledCarousel() {
  const [index, setIndex] = useState(0);

  const handleSelect = (selectedIndex) => {
    setIndex(selectedIndex);
  };

  return (
    <Carousel activeIndex={index} onSelect={handleSelect}>
      <Carousel.Item>
        <img src={bg1} alt="" className='w-100' style={{ objectFit: 'cover', height: '450px' }} />
        <Carousel.Caption>
          <h3>First slide label</h3>
          <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img src={bg2} alt="" className='w-100' style={{ objectFit: 'cover', height: '450px' }} />
        <Carousel.Caption>
          <h3>Second slide label</h3>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img src={bg3} alt="" className='w-100' style={{ objectFit: 'cover', height: '450px' }} />
        <Carousel.Caption>
          <h3>Third slide label</h3>
          <p>
            Praesent commodo cursus magna, vel scelerisque nisl consectetur.
          </p>
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
  );
}

export default ControlledCarousel;