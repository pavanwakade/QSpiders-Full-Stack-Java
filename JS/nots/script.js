// Store markdown content for each pages
let pages = {
    'Getting Started': `# Getting Started\n\nWelcome to the documentation! This is a sample markdown page.\n\n## Features\n\n- Easy to use\n- Supports markdown\n- Customizable\n- Dark mode support\n- Responsive design\n- Code syntax highlighting`,
    'Installation': `# Installation\n\n## Requirements\n\n- Node.js 14+\n- npm or yarn\n\n## Steps\n\n1. Clone the repository\n2. Run \`npm install\`\n3. Start the server with \`npm start\`\n\n## Code Example\n\n\`\`\`javascript\nconst app = require('express')();\napp.listen(3000, () => {\n    console.log('Server running on port 3000');\n});\n\`\`\``,
};

// Initialize the viewer
function initViewer() {
    // Add initial menu items
    updateMenu();
    
    // Show first page by default
    const firstPage = Object.keys(pages)[0];
    showPage(firstPage);

    // Initialize theme
    initializeTheme();

    // Initialize sidebar state
    initializeSidebar();

    // Update statistics
    updateStatistics();

    // Add event listeners
    setupEventListeners();
}

// Initialize theme
function initializeTheme() {
    const savedTheme = localStorage.getItem('theme') || 'light';
    document.body.classList.toggle('dark-theme', savedTheme === 'dark');
    updateThemeToggle();
}

// Initialize sidebar
function initializeSidebar() {
    const savedState = localStorage.getItem('sidebarCollapsed') === 'true';
    const sidebar = document.querySelector('.sidebar');
    if (savedState) {
        sidebar.classList.add('collapsed');
    }
}

// Setup event listeners
function setupEventListeners() {
    // Theme toggle
    document.getElementById('theme-toggle').addEventListener('click', toggleTheme);

    // Sidebar collapse
    document.getElementById('collapse-sidebar').addEventListener('click', toggleSidebar);

    // Search functionality
    document.getElementById('search-docs').addEventListener('input', handleSearch);

    // Export PDF
    document.getElementById('export-pdf').addEventListener('click', exportToPDF);
}

// Toggle theme
function toggleTheme() {
    const isDark = document.body.classList.toggle('dark-theme');
    localStorage.setItem('theme', isDark ? 'dark' : 'light');
    updateThemeToggle();
}

// Update theme toggle button
function updateThemeToggle() {
    const button = document.getElementById('theme-toggle');
    button.textContent = document.body.classList.contains('dark-theme') ? '☀️' : '🌙';
}

// Toggle sidebar
function toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    const isCollapsed = sidebar.classList.toggle('collapsed');
    localStorage.setItem('sidebarCollapsed', isCollapsed);
}

// Handle search
function handleSearch(e) {
    const searchTerm = e.target.value.toLowerCase();
    const menuItems = document.querySelectorAll('.menu-item');

    menuItems.forEach(item => {
        const text = item.textContent.toLowerCase();
        item.style.display = text.includes(searchTerm) ? 'flex' : 'none';
    });
}

// Export to PDF
function exportToPDF() {
    const content = document.getElementById('markdown-content').innerHTML;
    const style = `
        <style>
            body { font-family: Arial, sans-serif; }
            .preview-content { max-width: 800px; margin: 0 auto; padding: 2rem; }
        </style>
    `;
    const win = window.open('', '_blank');
    win.document.write(`
        <html>
            <head>${style}</head>
            <body>${content}</body>
        </html>
    `);
    win.document.close();
    win.print();
}

// Update statistics
function updateStatistics() {
    const pageCount = Object.keys(pages).length;
    const lastUpdated = new Date().toLocaleString();
    
    document.getElementById('page-count').textContent = pageCount;
    document.getElementById('last-updated').textContent = lastUpdated;
}

// Update the menu with current pages
function updateMenu() {
    const menu = document.getElementById('menu');
    menu.innerHTML = '';
    
    Object.keys(pages).forEach(pageName => {
        const menuItem = document.createElement('div');
        menuItem.className = 'menu-item';
        menuItem.innerHTML = `
            ${pageName}
            <div class="menu-actions">
                <button class="edit-btn" onclick="editPage('${pageName}')">✎</button>
                <button class="delete-btn" onclick="deletePage('${pageName}')">×</button>
            </div>
        `;
        menuItem.onclick = (e) => {
            if (!e.target.className.includes('btn')) {
                showPage(pageName);
            }
        };
        menu.appendChild(menuItem);
    });
}

// Show a specific page
function showPage(pageName) {
    // Update active state in menu
    document.querySelectorAll('.menu-item').forEach(item => {
        item.classList.remove('active');
        if (item.textContent.includes(pageName)) {
            item.classList.add('active');
        }
    });
    
    // Update breadcrumb
    document.getElementById('breadcrumb').innerHTML = `
        Documentation / <span>${pageName}</span>
    `;
    
    // Show markdown content
    const content = document.getElementById('markdown-content');
    content.innerHTML = `
        <div class="preview-content">
            ${marked.parse(pages[pageName] || '')}
        </div>
    `;

    // Apply syntax highlighting
    Prism.highlightAll();
}

// Add a new page
function addNewPage() {
    const modal = document.createElement('div');
    modal.className = 'modal';
    modal.innerHTML = `
        <div class="modal-content">
            <h2>Create New Page</h2>
            <input type="text" id="page-name" placeholder="Page Name" class="modal-input">
            <textarea id="page-content" placeholder="Enter markdown content..." class="modal-textarea"></textarea>
            <div class="modal-buttons">
                <button onclick="saveNewPage()">Save</button>
                <button onclick="closeModal()">Cancel</button>
            </div>
        </div>
    `;
    document.body.appendChild(modal);
}

// Save new page
function saveNewPage() {
    const pageName = document.getElementById('page-name').value.trim();
    const content = document.getElementById('page-content').value.trim();
    
    if (pageName && content) {
        if (!pages[pageName]) {
            pages[pageName] = content;
            updateMenu();
            showPage(pageName);
            updateStatistics();
            closeModal();
        } else {
            alert('A page with this name already exists!');
        }
    } else {
        alert('Please enter both page name and content!');
    }
}

// Edit existing page
function editPage(pageName) {
    const modal = document.createElement('div');
    modal.className = 'modal';
    modal.innerHTML = `
        <div class="modal-content">
            <h2>Edit Page</h2>
            <input type="text" id="page-name" value="${pageName}" readonly class="modal-input">
            <textarea id="page-content" class="modal-textarea">${pages[pageName]}</textarea>
            <div class="modal-buttons">
                <button onclick="saveEditedPage('${pageName}')">Save</button>
                <button onclick="closeModal()">Cancel</button>
            </div>
        </div>
    `;
    document.body.appendChild(modal);
}

// Save edited page
function saveEditedPage(pageName) {
    const content = document.getElementById('page-content').value.trim();
    
    if (content) {
        pages[pageName] = content;
        showPage(pageName);
        updateStatistics();
        closeModal();
    } else {
        alert('Content cannot be empty!');
    }
}

// Close modal
function closeModal() {
    const modal = document.querySelector('.modal');
    if (modal) {
        modal.remove();
    }
}

// Delete a page
function deletePage(pageName) {
    if (confirm(`Are you sure you want to delete "${pageName}"?`)) {
        delete pages[pageName];
        updateMenu();
        updateStatistics();
        
        // Show first available page or empty content
        const firstPage = Object.keys(pages)[0];
        if (firstPage) {
            showPage(firstPage);
        } else {
            document.getElementById('markdown-content').innerHTML = '';
            document.getElementById('breadcrumb').innerHTML = '';
        }
    }
}

// Initialize when the page loads
document.addEventListener('DOMContentLoaded', initViewer);