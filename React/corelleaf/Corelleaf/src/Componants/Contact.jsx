import React from 'react';

const Contact = () => {
  return (
    <div className="page bg-primary text-textLight p-8 my-8 mx-auto max-w-4xl rounded-lg shadow-lg animate-fadeInUp">
      <h1 className="text-accent mb-6 text-center">Contact Us</h1>
      <p className="mb-8 text-center max-w-2xl mx-auto">
        We'd love to hear from you! Whether you have a project in mind, a question about our services, or just want to say hello, feel free to reach out. Our team is ready to assist you.
      </p>
      
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8 mb-8">
        <div className="contact-info bg-secondary rounded-lg shadow-md p-6 border border-borderLight">
          <h2 className="text-xl font-semibold text-accent mb-4">Get in Touch</h2>
          <p className="mb-2"><strong>Email:</strong> <a href="mailto:info@corelleaf.com" className="text-accent hover:underline">info@corelleaf.com</a></p>
          <p className="mb-2"><strong>Phone:</strong> <a href="tel:+1234567890" className="text-accent hover:underline">+1 (234) 567-890</a></p>
          <p className="mb-2"><strong>Address:</strong> Venus Garden Building, Office No- 10, behind Bank of Baroda, Thite Vasti, Kharadi, Maharashtra 411014.</p>
        </div>

        <div className="contact-form bg-secondary rounded-lg shadow-md p-6 border border-borderLight">
          <h2 className="text-xl font-semibold text-accent mb-4">Send Us a Message</h2>
          <form>
            <div className="mb-4">
              <label htmlFor="name" className="block text-textLight text-sm font-bold mb-2">Name:</label>
              <input type="text" id="name" name="name" className="shadow appearance-none border rounded w-full py-2 px-3 text-textDark leading-tight focus:outline-none focus:shadow-outline bg-primary border-borderLight" placeholder="Your Name" />
            </div>
            <div className="mb-4">
              <label htmlFor="email" className="block text-textLight text-sm font-bold mb-2">Email:</label>
              <input type="email" id="email" name="email" className="shadow appearance-none border rounded w-full py-2 px-3 text-textDark leading-tight focus:outline-none focus:shadow-outline bg-primary border-borderLight" placeholder="Your Email" />
            </div>
            <div className="mb-6">
              <label htmlFor="message" className="block text-textLight text-sm font-bold mb-2">Message:</label>
              <textarea id="message" name="message" rows="5" className="shadow appearance-none border rounded w-full py-2 px-3 text-textDark leading-tight focus:outline-none focus:shadow-outline bg-primary border-borderLight" placeholder="Your Message"></textarea>
            </div>
            <div className="flex items-center justify-between">
              <button type="submit" className="bg-accent hover:bg-blue-600 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                Send Message
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Contact;
