import { useState } from 'react'

const CRUD1 = () => {
    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [user, setUser] = useState("");
    const [pass, setPass] = useState("");
    const [email, setEmail] = useState("");
    const [users, setUsers] = useState([]);
    const [editingId, setEditingId] = useState(null);

    const handleSubmit = (event) => {
        event.preventDefault();
        
        if (!id || !name || !user || !pass || !email) {
            alert("Please fill in all fields");
            return;
        }

        const userData = {
            id: id,
            name: name,
            username: user,
            password: pass,
            email: email
        };

        if (editingId) {
            // Update existing user
            setUsers(users.map(u => u.id === editingId ? userData : u));
            setEditingId(null);
        } else {
            // Add new user
            if (users.find(u => u.id === id)) {
                alert("User with this ID already exists");
                return;
            }
            setUsers([...users, userData]);
        }

        // Clear form
        clearForm();
    };

    const clearForm = () => {
        setId("");
        setName("");
        setUser("");
        setPass("");
        setEmail("");
    };

    const handleEdit = (userData) => {
        setId(userData.id);
        setName(userData.name);
        setUser(userData.username);
        setPass(userData.password);
        setEmail(userData.email);
        setEditingId(userData.id);
    };

    const handleDelete = (userId) => {
        if (window.confirm("Are you sure you want to delete this user?")) {
            setUsers(users.filter(u => u.id !== userId));
        }
    };

    const handleCancel = () => {
        clearForm();
        setEditingId(null);
    };

    return (
        <div className="max-w-4xl mx-auto p-6 bg-white">
            <h1 className="text-2xl font-bold mb-6 text-center text-gray-800">
                User Management System
            </h1>
            
            <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
                {/* Form Section */}
                <div className="bg-gray-50 p-6 rounded-lg">
                    <h2 className="text-xl font-semibold mb-4 text-gray-700">
                        {editingId ? "Edit User" : "Add New User"}
                    </h2>
                    
                    <div className="space-y-4">
                        <input
                            type="text"
                            name="id"
                            id="id"
                            value={id}
                            placeholder="Enter your ID"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                            onChange={(e) => setId(e.target.value)}
                            disabled={editingId !== null}
                        />

                        <input
                            type="text"
                            name="name"
                            id="name"
                            value={name}
                            placeholder="Enter your name"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                            onChange={(e) => setName(e.target.value)}
                        />

                        <input
                            type="text"
                            name="username"
                            id="username"
                            value={user}
                            placeholder="Enter your username"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                            onChange={(e) => setUser(e.target.value)}
                        />

                        <input
                            type="password"
                            name="password"
                            id="password"
                            value={pass}
                            placeholder="Enter your password"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                            onChange={(e) => setPass(e.target.value)}
                        />

                        <input
                            type="email"
                            name="email"
                            id="email"
                            value={email}
                            placeholder="Enter your email"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                            onChange={(e) => setEmail(e.target.value)}
                        />

                        <div className="flex gap-2">
                            <button 
                                onClick={handleSubmit}
                                className="flex-1 bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 transition-colors"
                            >
                                {editingId ? "Update User" : "Add User"}
                            </button>
                            
                            {editingId && (
                                <button 
                                    onClick={handleCancel}
                                    className="flex-1 bg-gray-500 text-white py-2 px-4 rounded-md hover:bg-gray-600 transition-colors"
                                >
                                    Cancel
                                </button>
                            )}
                        </div>
                    </div>
                </div>

                {/* Users List Section */}
                <div className="bg-gray-50 p-6 rounded-lg">
                    <h2 className="text-xl font-semibold mb-4 text-gray-700">
                        Users List ({users.length})
                    </h2>
                    
                    {users.length === 0 ? (
                        <p className="text-gray-500 text-center py-8">
                            No users added yet. Add your first user using the form.
                        </p>
                    ) : (
                        <div className="space-y-3 max-h-96 overflow-y-auto">
                            {users.map((userData) => (
                                <div 
                                    key={userData.id} 
                                    className="bg-white p-4 rounded-md border border-gray-200 shadow-sm"
                                >
                                    <div className="flex justify-between items-start">
                                        <div className="flex-1">
                                            <h3 className="font-semibold text-gray-800">
                                                {userData.name}
                                            </h3>
                                            <p className="text-sm text-gray-600">
                                                ID: {userData.id}
                                            </p>
                                            <p className="text-sm text-gray-600">
                                                Username: {userData.username}
                                            </p>
                                            <p className="text-sm text-gray-600">
                                                Email: {userData.email}
                                            </p>
                                        </div>
                                        
                                        <div className="flex gap-2">
                                            <button
                                                onClick={() => handleEdit(userData)}
                                                className="text-blue-500 hover:text-blue-700 text-sm font-medium"
                                            >
                                                Edit
                                            </button>
                                            <button
                                                onClick={() => handleDelete(userData.id)}
                                                className="text-red-500 hover:text-red-700 text-sm font-medium"
                                            >
                                                Delete
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default CRUD1;