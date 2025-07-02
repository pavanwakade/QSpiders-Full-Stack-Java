function LandingPage() {
  return (
    <div className="min-h-[calc(100vh-80px)] flex flex-col items-center justify-center text-center bg-primary text-textLight px-4 py-10 animate-fadeInUp">
      <h1 className="text-4xl md:text-6xl font-extrabold mb-4 leading-tight animate-popIn">
        Innovate. Develop. Deliver.
      </h1>
      <p className="text-xl md:text-2xl mb-8 max-w-3xl opacity-0 animate-fadeInUp animation-delay-500">
        We are Corelleaf, a software development company crafting cutting-edge solutions that transform ideas into impactful digital products.
      </p>
      <div className="flex flex-col sm:flex-row gap-4 opacity-0 animate-fadeInUp animation-delay-1000">
        <a href="/contact" className="btn bg-accent hover:bg-blue-600 text-white font-semibold py-3 px-8 rounded-full shadow-lg transition-all duration-300 transform hover:scale-105">
          Get a Free Consultation
        </a>
        <a href="/portfolio" className="btn bg-transparent border-2 border-accent text-accent hover:bg-accent hover:text-white font-semibold py-3 px-8 rounded-full shadow-lg transition-all duration-300 transform hover:scale-105">
          View Our Work
        </a>
      </div>
    </div>
  );
}

export default LandingPage;
