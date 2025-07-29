import React, { createContext, useContext, useState } from 'react';

const CartContext = createContext();

function CartProvider({ children }) {
  const [cart, setCart] = useState([]);
  return (
    <CartContext.Provider value={{ cart, setCart }}>
      {children}
    </CartContext.Provider>
  );
}

const products = [
  { id: 1, name: 'Product 1' },
  { id: 2, name: 'Product 2' },
  { id: 3, name: 'Product 3' },
];

function ProductList() {
  const { setCart } = useContext(CartContext);
  return (
    <div>
      {products.map(p => (
        <div key={p.id}>
          {p.name}
          <button onClick={() => setCart(c => [...c, p])}>Add to Cart</button>
        </div>
      ))}
    </div>
  );
}

function CartHeader() {
  const { cart } = useContext(CartContext);
  return <div>Total items: {cart.length}</div>;
}

function ProductListingWithAddToCartUsingContext() {
  return (
    <CartProvider>
      <CartHeader />
      <ProductList />
    </CartProvider>
  );
}
export default ProductListingWithAddToCartUsingContext;
