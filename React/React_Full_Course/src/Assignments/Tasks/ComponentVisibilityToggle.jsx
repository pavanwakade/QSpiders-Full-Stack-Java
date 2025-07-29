import React, { useState } from 'react';

function Profile() {
  return <div>Profile Card</div>;
}
function MessageBoard() {
  return <div>Message Board</div>;
}
function ComponentVisibilityToggle() {
  const [showProfile, setShowProfile] = useState(true);
  return (
    <div>
      <div>Active: {showProfile ? 'Profile' : 'Message Board'}</div>
      <button onClick={() => setShowProfile(s => !s)}>
        Toggle
      </button>
      {showProfile ? <Profile /> : <MessageBoard />}
    </div>
  );
}
export default ComponentVisibilityToggle;
