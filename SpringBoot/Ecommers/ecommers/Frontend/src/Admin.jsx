import { useEffect, useState, useRef } from 'react';

const API_URL = 'http://localhost:8080/api';
const UPLOADS_URL = 'http://localhost:8080/uploads/';

export default function Admin() {
  const [products, setProducts] = useState([]);
  const [form, setForm] = useState({ name: '', price: '' });
  const [imageFile, setImageFile] = useState(null);
  const [editing, setEditing] = useState(null);
  const [msg, setMsg] = useState('');
  const fileInputRef = useRef();

  const fetchProducts = () => {
    fetch(`${API_URL}/products`)
      .then(res => res.json())
      .then(setProducts);
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  const handleChange = e => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleFileChange = e => {
    setImageFile(e.target.files[0]);
  };

  const handleSubmit = async e => {
    e.preventDefault();
    setMsg('');
    if (!form.name || !form.price || (!editing && !imageFile)) {
      setMsg('All fields required');
      return;
    }
    const data = new FormData();
    data.append('name', form.name);
    data.append('price', form.price);
    if (imageFile) data.append('image', imageFile);
    const method = editing ? 'PUT' : 'POST';
    const url = editing ? `${API_URL}/products/${editing}` : `${API_URL}/products`;
    const res = await fetch(url, {
      method,
      body: data
    });
    if (res.ok) {
      setMsg(editing ? 'Product updated' : 'Product created');
      setForm({ name: '', price: '' });
      setImageFile(null);
      setEditing(null);
      if (fileInputRef.current) fileInputRef.current.value = '';
      fetchProducts();
    } else {
      setMsg('Error saving product');
    }
  };

  const handleEdit = prod => {
    setForm({ name: prod.name, price: prod.price });
    setEditing(prod.id);
    setImageFile(null);
    if (fileInputRef.current) fileInputRef.current.value = '';
  };

  const handleDelete = async id => {
    if (!window.confirm('Delete this product?')) return;
    const res = await fetch(`${API_URL}/products/${id}`, { method: 'DELETE' });
    if (res.ok) {
      setMsg('Product deleted');
      fetchProducts();
    } else {
      setMsg('Error deleting');
    }
  };

  return (
    <div style={{ maxWidth: 600, margin: '40px auto', padding: 24, background: '#222', color: '#fff', borderRadius: 12 }}>
      <h2>Admin: Product Management</h2>
      <form onSubmit={handleSubmit} style={{ marginBottom: 24 }} encType="multipart/form-data">
        <input name="name" placeholder="Name" value={form.name} onChange={handleChange} style={{ marginRight: 8 }} />
        <input name="price" placeholder="Price" type="number" value={form.price} onChange={handleChange} style={{ marginRight: 8 }} />
        <input name="image" type="file" accept="image/*" onChange={handleFileChange} ref={fileInputRef} style={{ marginRight: 8, width: 180 }} />
        <button type="submit">{editing ? 'Update' : 'Add'}</button>
        {editing && <button type="button" onClick={() => { setEditing(null); setForm({ name: '', price: '' }); setImageFile(null); if (fileInputRef.current) fileInputRef.current.value = ''; }}>Cancel</button>}
      </form>
      {msg && <div style={{ marginBottom: 12, color: '#0f0' }}>{msg}</div>}
      <table style={{ width: '100%', background: '#333', borderRadius: 8 }}>
        <thead>
          <tr><th>Name</th><th>Price</th><th>Image</th><th>Actions</th></tr>
        </thead>
        <tbody>
          {products.map(prod => (
            <tr key={prod.id}>
              <td>{prod.name}</td>
              <td>${prod.price.toFixed(2)}</td>
              <td>{prod.imageFilename && <img src={UPLOADS_URL + prod.imageFilename} alt={prod.name} style={{ width: 50 }} />}</td>
              <td>
                <button onClick={() => handleEdit(prod)}>Edit</button>
                <button onClick={() => handleDelete(prod.id)} style={{ marginLeft: 8 }}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
