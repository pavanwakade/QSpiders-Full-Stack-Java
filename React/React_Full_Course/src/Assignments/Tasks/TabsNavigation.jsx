import React, { useState } from 'react';

function TabsNavigation() {
  const [tab, setTab] = useState('Home');
  return (
    <div>
      <div>
        <button onClick={() => setTab('Home')} style={{ fontWeight: tab === 'Home' ? 'bold' : 'normal' }}>Home</button>
        <button onClick={() => setTab('About')} style={{ fontWeight: tab === 'About' ? 'bold' : 'normal' }}>About</button>
        <button onClick={() => setTab('Contact')} style={{ fontWeight: tab === 'Contact' ? 'bold' : 'normal' }}>Contact</button>
      </div>
      <div>
        {tab === 'Home' && <div>Home Content</div>}
        {tab === 'About' && <div>About Content</div>}
        {tab === 'Contact' && <div>Contact Content</div>}
      </div>
    </div>
  );
}
export default TabsNavigation;
