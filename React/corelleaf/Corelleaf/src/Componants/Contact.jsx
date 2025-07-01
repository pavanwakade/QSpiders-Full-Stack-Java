
function Contact() {
  return (
    <div className="page contact">
      <h1>Contact Us</h1>
      <p>
        Ready to start your project or have questions? Reach out to us!
      </p>
      <p>
        <strong>Email:</strong> hello@corelleaf.com<br />
        <strong>Phone:</strong> +91 98765 43210<br />
        <strong>Location:</strong> Bengaluru, India
      </p>
      <form>
        <input type="text" placeholder="Your Name" required /><br />
        <input type="email" placeholder="Your Email" required /><br />
        <textarea placeholder="Your Message" required></textarea><br />
        <button type="submit">Send Message</button>
      </form>
    </div>
  );
}

export default Contact;
