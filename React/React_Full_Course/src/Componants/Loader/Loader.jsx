
const Loader = () => {
  return (
    <div className="fixed inset-0 flex items-center justify-center bg-gradient-to-br from-slate-900 via-purple-900 to-slate-900 z-50">
      <div className="relative">
        {/* Outer rotating ring */}
        <div className="w-32 h-32 border-4 border-transparent border-t-cyan-400 border-r-purple-400 rounded-full animate-spin"></div>
        
        {/* Middle pulsing ring */}
        <div className="absolute top-2 left-2 w-28 h-28 border-4 border-transparent border-t-pink-400 border-l-blue-400 rounded-full animate-spin" style={{animationDirection: 'reverse', animationDuration: '1.5s'}}></div>
        
        {/* Inner bouncing dots */}
        <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 flex space-x-1">
          <div className="w-2 h-2 bg-cyan-400 rounded-full animate-bounce" style={{animationDelay: '0s'}}></div>
          <div className="w-2 h-2 bg-purple-400 rounded-full animate-bounce" style={{animationDelay: '0.1s'}}></div>
          <div className="w-2 h-2 bg-pink-400 rounded-full animate-bounce" style={{animationDelay: '0.2s'}}></div>
        </div>
        
        {/* Glowing center orb */}
        <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-8 h-8 bg-gradient-to-r from-cyan-400 to-purple-400 rounded-full animate-pulse shadow-lg shadow-cyan-400/50"></div>
        
        {/* Floating particles */}
        <div className="absolute -top-4 -left-4 w-2 h-2 bg-cyan-300 rounded-full animate-ping" style={{animationDelay: '0.5s'}}></div>
        <div className="absolute -top-2 -right-6 w-1 h-1 bg-purple-300 rounded-full animate-ping" style={{animationDelay: '1s'}}></div>
        <div className="absolute -bottom-4 -right-2 w-2 h-2 bg-pink-300 rounded-full animate-ping" style={{animationDelay: '1.5s'}}></div>
        <div className="absolute -bottom-2 -left-6 w-1 h-1 bg-blue-300 rounded-full animate-ping" style={{animationDelay: '2s'}}></div>
      </div>
      
      {/* Loading text with typewriter effect */}
      <div className="absolute mt-48 text-center">
        <div className="text-transparent bg-clip-text bg-gradient-to-r from-cyan-400 to-purple-400 text-xl font-bold tracking-wider animate-pulse">
          Loading...
        </div>
        <div className="mt-2 flex justify-center space-x-1">
          <div className="w-2 h-1 bg-cyan-400 rounded-full animate-pulse" style={{animationDelay: '0s'}}></div>
          <div className="w-2 h-1 bg-purple-400 rounded-full animate-pulse" style={{animationDelay: '0.2s'}}></div>
          <div className="w-2 h-1 bg-pink-400 rounded-full animate-pulse" style={{animationDelay: '0.4s'}}></div>
          <div className="w-2 h-1 bg-blue-400 rounded-full animate-pulse" style={{animationDelay: '0.6s'}}></div>
          <div className="w-2 h-1 bg-cyan-400 rounded-full animate-pulse" style={{animationDelay: '0.8s'}}></div>
        </div>
      </div>
      
      {/* Background ambient effects */}
      <div className="absolute inset-0 overflow-hidden pointer-events-none">
        <div className="absolute top-1/4 left-1/4 w-64 h-64 bg-cyan-400/10 rounded-full blur-3xl animate-pulse"></div>
        <div className="absolute bottom-1/4 right-1/4 w-96 h-96 bg-purple-400/10 rounded-full blur-3xl animate-pulse" style={{animationDelay: '1s'}}></div>
      </div>
    </div>
  );
};

export default Loader;