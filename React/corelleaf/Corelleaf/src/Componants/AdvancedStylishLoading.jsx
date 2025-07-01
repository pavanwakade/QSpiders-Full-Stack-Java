import { useEffect, useState } from 'react';

const AdvancedStylishLoading = () => {
  const [activeIndex, setActiveIndex] = useState(0);
  const [rotationAngle, setRotationAngle] = useState(0);

  useEffect(() => {
    const dotInterval = setInterval(() => {
      setActiveIndex((prev) => (prev + 1) % 5);
    }, 400);

    

    const rotationInterval = setInterval(() => {
      setRotationAngle((prev) => (prev + 2) % 360);
    }, 50);

    return () => {
      clearInterval(dotInterval);
      clearInterval(rotationInterval);
    };
  }, []);

  const containerStyle = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    minHeight: '100vh',
    width: '100%',
    background: `
      radial-gradient(circle at 20% 80%, rgba(120, 119, 198, 0.3) 0%, transparent 50%),
      radial-gradient(circle at 80% 20%, rgba(255, 119, 198, 0.3) 0%, transparent 50%),
      radial-gradient(circle at 40% 40%, rgba(120, 219, 255, 0.2) 0%, transparent 50%),
      linear-gradient(135deg, #0f172a 0%, #1e293b 50%, #0f172a 100%)
    `,
    position: 'relative',
    overflow: 'hidden',
  };

  const backgroundElementsStyle = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: '400px',
    height: '400px',
    pointerEvents: 'none',
  };

  const orbitRingStyle = (size, speed, opacity) => ({
    position: 'absolute',
    top: '50%',
    left: '50%',
    width: `${size}px`,
    height: `${size}px`,
    border: `1px solid rgba(148, 163, 184, ${opacity})`,
    borderRadius: '50%',
    transform: `translate(-50%, -50%) rotate(${rotationAngle * speed}deg)`,
    transition: 'all 0.1s linear',
  });

  const mainLoaderStyle = {
    position: 'relative',
    zIndex: 2,
  };

  const dotContainerStyle = {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    gap: '20px',
    padding: '40px',
    position: 'relative',
  };

  const baseDotStyle = {
    width: '16px',
    height: '16px',
    borderRadius: '50%',
    position: 'relative',
    transition: 'all 0.6s cubic-bezier(0.68, -0.55, 0.265, 1.55)',
    backgroundColor: '#475569',
  };

  const getActiveDotStyle = (index) => {
    const isActive = index === activeIndex;
    const isPrevious = index === (activeIndex - 1 + 5) % 5;
    const isNext = index === (activeIndex + 1) % 5;

    if (isActive) {
      return {
        background: 'linear-gradient(135deg, #3b82f6, #8b5cf6, #ec4899)',
        transform: 'scale(1.8) translateY(-8px)',
        boxShadow: `
          0 0 20px rgba(59, 130, 246, 0.6),
          0 0 40px rgba(139, 92, 246, 0.4),
          0 0 60px rgba(236, 72, 153, 0.2)
        `,
      };
    } else if (isPrevious || isNext) {
      return {
        background: 'linear-gradient(135deg, #64748b, #475569)',
        transform: 'scale(1.2)',
        boxShadow: '0 0 10px rgba(100, 116, 139, 0.3)',
      };
    }
    return {};
  };

  const particleStyle = (delay) => ({
    position: 'absolute',
    width: '4px',
    height: '4px',
    background: 'linear-gradient(45deg, #06b6d4, #3b82f6)',
    borderRadius: '50%',
    animation: `float 3s infinite ease-in-out ${delay}s`,
    opacity: 0.7,
  });

  const loadingTextStyle = {
    marginTop: '50px',
    textAlign: 'center',
    position: 'relative',
  };

  const mainTextStyle = {
    background: 'linear-gradient(90deg, #64748b, #e2e8f0, #64748b)',
    backgroundSize: '200% 100%',
    WebkitBackgroundClip: 'text',
    WebkitTextFillColor: 'transparent',
    backgroundClip: 'text',
    fontSize: '1.25rem',
    fontFamily: 'system-ui, sans-serif',
    fontWeight: '600',
    letterSpacing: '0.3em',
    animation: 'shimmer 2s infinite linear',
    marginBottom: '8px',
  };

  const subTextStyle = {
    color: '#64748b',
    fontSize: '0.875rem',
    fontFamily: 'system-ui, sans-serif',
    fontWeight: '400',
    letterSpacing: '0.1em',
    opacity: 0.8,
  };

  const progressBarStyle = {
    width: '200px',
    height: '2px',
    background: 'rgba(71, 85, 105, 0.3)',
    borderRadius: '1px',
    overflow: 'hidden',
    marginTop: '20px',
  };

  const progressFillStyle = {
    height: '100%',
    background: 'linear-gradient(90deg, #3b82f6, #8b5cf6, #ec4899)',
    borderRadius: '1px',
    transform: `translateX(-${100 - ((activeIndex + 1) * 20)}%)`,
    transition: 'transform 0.6s cubic-bezier(0.4, 0, 0.2, 1)',
  };

  const keyframeStyles = `
    @keyframes shimmer {
      0% { background-position: -200% 0; }
      100% { background-position: 200% 0; }
    }
    @keyframes float {
      0%, 100% { transform: translateY(0px) rotate(0deg); opacity: 0.7; }
      50% { transform: translateY(-20px) rotate(180deg); opacity: 1; }
    }
    @keyframes pulse {
      0%, 100% { transform: translate(-50%, -50%) scale(1); opacity: 0.3; }
      50% { transform: translate(-50%, -50%) scale(1.5); opacity: 0.1; }
    }
  `;

  return (
    <>
      <style>{keyframeStyles}</style>
      <div style={containerStyle}>
        <div style={backgroundElementsStyle}>
          <div style={orbitRingStyle(300, 1, 0.1)} />
          <div style={orbitRingStyle(250, -0.8, 0.08)} />
          <div style={orbitRingStyle(200, 1.2, 0.06)} />
          {[...Array(6)].map((_, i) => (
            <div
              key={i}
              style={{
                ...particleStyle(i * 0.5),
                top: `${20 + (i * 60) % 80}%`,
                left: `${15 + (i * 70) % 70}%`,
              }}
            />
          ))}
        </div>
        <div style={mainLoaderStyle}>
          <div style={dotContainerStyle}>
            {[0, 1, 2, 3, 4].map((index) => (
              <div
                key={index}
                style={{
                  ...baseDotStyle,
                  ...getActiveDotStyle(index),
                }}
              />
            ))}
          </div>
          <div style={loadingTextStyle}>
            <div style={mainTextStyle}>
              LOADING
            </div>
            <div style={subTextStyle}>
              Please wait while we prepare everything
            </div>
            <div style={progressBarStyle}>
              <div style={progressFillStyle} />
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default AdvancedStylishLoading;
