import { useEffect, useState } from 'react';
import './Style/Loading.css';

const TARGET_TEXT = "Corelleaf";
const CHARS = "!<>-_\\/[]{}—=+*^?#________";

const Loading = ({ onFinished }) => {
  const [isHiding, setIsHiding] = useState(false);

  useEffect(() => {
    let frameRequest;
    let frame = 0;
    let queue = [];

    for (let i = 0; i < TARGET_TEXT.length; i++) {
      const from = TARGET_TEXT[i];
      const to = TARGET_TEXT[i];
      const start = Math.floor(Math.random() * 40);
      const end = start + Math.floor(Math.random() * 40);
      queue.push({ from, to, start, end, char: '' });
    }

    const update = () => {
      let output = '';
      let complete = 0;
      for (let i = 0, n = queue.length; i < n; i++) {
        let { from, to, start, end, char } = queue[i];
        if (frame >= end) {
          complete++;
          output += to;
        } else if (frame >= start) {
          if (!char || Math.random() < 0.28) {
            char = CHARS[Math.floor(Math.random() * CHARS.length)];
            queue[i].char = char;
          }
          output += `<span class="scramble-char">${char}</span>`;
        } else {
          output += from;
        }
      }
      document.getElementById('scramble-text').innerHTML = output;

      if (complete === queue.length) {
        setTimeout(() => {
          setIsHiding(true);
          setTimeout(onFinished, 500);
        }, 800);
      } else {
        frame++;
        frameRequest = requestAnimationFrame(update);
      }
    };

    update();

    return () => {
      cancelAnimationFrame(frameRequest);
    };
  }, [onFinished]);

  return (
    <div className={`loading-container ${isHiding ? 'is-hiding' : ''}`}>
      <div id="scramble-text" className="scramble-text-display" aria-label="Loading">
        corelleaf
      </div>
    </div>
  );
};

export default Loading;