
import { useEffect, useState } from 'react';
import './App.css';
import Admin from './Admin';

const API_URL = 'http://localhost:8080/api';

function Shop() {
  const [products, setProducts] = useState([]);
  const [cart, setCart] = useState(() => {
    const saved = localStorage.getItem('cart');
    return saved ? JSON.parse(saved) : [];
  });
  const [cartOpen, setCartOpen] = useState(false);
  const [checkoutMsg, setCheckoutMsg] = useState('');

  useEffect(() => {
    fetch(`${API_URL}/products`)
      .then(res => res.json())
      .then(setProducts);
  }, []);

  useEffect(() => {
    localStorage.setItem('cart', JSON.stringify(cart));
  }, [cart]);

  const addToCart = (product) => {
    setCart(prev => {
      const found = prev.find(item => item.id === product.id);
      if (found) {
        return prev.map(item => item.id === product.id ? { ...item, quantity: item.quantity + 1 } : item);
      }
      return [...prev, { ...product, quantity: 1 }];
    });
  };

  const changeQuantity = (id, qty) => {
    setCart(prev => prev.map(item => item.id === id ? { ...item, quantity: Math.max(1, qty) } : item));
  };

  const removeFromCart = (id) => {
    setCart(prev => prev.filter(item => item.id !== id));
  };

  const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);

  const checkout = async () => {
    setCheckoutMsg('');
    const order = cart.map(({ id, quantity }) => ({ id, quantity }));
    const res = await fetch(`${API_URL}/checkout`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(order)
    });
    const data = await res.json();
    setCheckoutMsg(data.message || 'Order placed!');
    setCart([]);
    localStorage.removeItem('cart');
  };

  return (
    <div>
      <h1>🛒 E-Commerce Demo</h1>
      <a href="#admin" style={{ position: 'absolute', left: 20, top: 20 }}>Admin</a>
      <button onClick={() => setCartOpen(true)} style={{ float: 'right', margin: 10 }}>
        Cart ({cart.reduce((sum, item) => sum + item.quantity, 0)})
      </button>
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: 24, justifyContent: 'center' }}>
        {products.map(product => (
          <div key={product.id} style={{ border: '1px solid #ccc', borderRadius: 8, padding: 16, width: 200 }}>
            <img
              src={product.image ? `data:image/jpeg;base64,${product.image}` : ''}
              alt={product.name}
              style={{ width: '100%', height: 120, objectFit: 'cover' }}
            />
            <h3>{product.name}</h3>
            <p>${product.price.toFixed(2)}</p>
            <button onClick={() => addToCart(product)}>Add to Cart</button>
          </div>
        ))}
      </div>

      {/* Cart Modal */}
      {cartOpen && (
        <div style={{ position: 'fixed', top: 0, left: 0, width: '100vw', height: '100vh', background: '#0008', zIndex: 10, display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
          <div style={{ background: '#fff', padding: 32, borderRadius: 12, minWidth: 320, maxWidth: 400 }}>
            <h2>Your Cart</h2>
            {cart.length === 0 ? <p>No items in cart.</p> : (
              <ul style={{ listStyle: 'none', padding: 0 }}>
                {cart.map(item => (
                  <li key={item.id} style={{ marginBottom: 12 }}>
                    <b>{item.name}</b> (${item.price.toFixed(2)})<br />
                    <input type="number" min={1} value={item.quantity} style={{ width: 50 }}
                      onChange={e => changeQuantity(item.id, parseInt(e.target.value) || 1)} />
                    <button onClick={() => removeFromCart(item.id)} style={{ marginLeft: 8 }}>Remove</button>
                  </li>
                ))}
              </ul>
            )}
            <hr />
            <div>Total: <b>${total.toFixed(2)}</b></div>
            <button onClick={checkout} disabled={cart.length === 0} style={{ marginTop: 12 }}>Checkout</button>
            <button onClick={() => setCartOpen(false)} style={{ marginLeft: 8 }}>Close</button>
            {checkoutMsg && <div style={{ marginTop: 10, color: 'green' }}>{checkoutMsg}</div>}
          </div>
        </div>
      )}
    </div>
  );
}

function App() {
  const [route, setRoute] = useState(window.location.hash);
  useEffect(() => {
    const onHash = () => setRoute(window.location.hash);
    window.addEventListener('hashchange', onHash);
    return () => window.removeEventListener('hashchange', onHash);
  }, []);
  if (route === '#admin') return <Admin />;
  return <Shop />;
}

export default App;
