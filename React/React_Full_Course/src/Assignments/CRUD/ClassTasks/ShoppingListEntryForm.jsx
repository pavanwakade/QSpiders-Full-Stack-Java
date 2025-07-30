import React, { useState } from 'react';

const ShoppingListEntryForm = () => {
  const [item, setItem] = useState('');
  const [shoppingList, setShoppingList] = useState([]);

  const handleItemChange = (event) => {
    setItem(event.target.value);
  };

  const addItem = () => {
    if (item.trim() !== '') {
      setShoppingList([...shoppingList, item]);
      setItem('');
    }
  };

  return (
    <div>
      <input type="text" value={item} onChange={handleItemChange} />
      <button onClick={addItem}>Add Item</button>
      <ul>
        {shoppingList.map((listItem, index) => (
          <li key={index}>{listItem}</li>
        ))}
      </ul>
    </div>
  );
};

export default ShoppingListEntryForm;