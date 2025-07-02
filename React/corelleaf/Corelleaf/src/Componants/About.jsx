import React from 'react';

const About = () => {
  return (
    <div className="page bg-secondary text-textLight rounded-lg shadow-lg p-8 my-8 mx-auto max-w-4xl animate-fadeInUp">
      <h1 className="text-accent mb-6">About Us</h1>
      <p className="mb-4">Welcome to Corelleaf, a leading software development company dedicated to crafting innovative digital solutions. We specialize in creating robust, scalable, and user-friendly applications that drive business growth and enhance user experiences.</p>
      <p className="mb-6">Our team of experienced developers, designers, and strategists work collaboratively to bring your ideas to life. We are passionate about technology and committed to delivering excellence in every project we undertake.</p>
      <h2 className="text-accent mb-4">Our Mission</h2>
      <p className="mb-6">To empower businesses with cutting-edge software solutions that streamline operations, foster innovation, and provide a competitive edge in the digital landscape.</p>
      <h2 className="text-accent mb-4">Our Vision</h2>
      <p className="mb-6">To be a globally recognized leader in software development, renowned for our technical expertise, creative solutions, and unwavering commitment to client success.</p>
      <h2 className="text-accent mb-4">Why Choose Us?</h2>
      <ul className="list-disc list-inside space-y-2 mb-6">
        <li><strong className="text-accent">Expertise:</strong> Our team comprises highly skilled professionals with extensive experience in various technologies and industries.</li>
        <li><strong className="text-accent">Innovation:</strong> We embrace the latest technologies and methodologies to deliver forward-thinking solutions.</li>
        <li><strong className="text-accent">Client-Centric Approach:</strong> Your success is our priority. We work closely with you to understand your needs and deliver tailored solutions.</li>
        <li><strong className="text-accent">Quality:</strong> We adhere to the highest standards of quality, ensuring our products are reliable, secure, and performant.</li>
        <li><strong className="text-accent">Timely Delivery:</strong> We are committed to delivering projects on time and within budget, without compromising on quality.</li>
      </ul>
      <p>At Corelleaf, we believe in building long-term partnerships based on trust, transparency, and mutual success. Let's build something amazing together!</p>
    </div>
  );
};

export default About;
