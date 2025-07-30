import React, { useState } from 'react';

const ShoppingCart = () => {
  const [cartCount, setCartCount] = useState(0);

  const addToCart = () => {
    setCartCount(cartCount + 1);
  };

  return (
    <div>
      <p>Cart Count: {cartCount}</p>
      <button onClick={addToCart}>Add to Cart</button>
    </div>
  );
};

export default ShoppingCart;