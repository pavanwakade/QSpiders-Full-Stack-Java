// Store markdown content for each pages with submenus
let pages = {
    'Started': {
        content: `# Getting Started\n\nWelcome to the documentation! This is a sample markdown page.\n\n## Features\n\n- Easy to use\n- Supports markdown\n- Customizable\n- Dark mode support\n- Responsive design\n- Code syntax highlighting`,
        subPages: {}
    },
    'Installation': {
        content: `# Installation\n\n## Requirements\n\n- Node.js 14+\n- npm or yarn\n\n## Steps\n\n1. Clone the repository\n2. Run \`npm install\`\n3. Start the server with \`npm start\`\n\n## Code Example\n\n\`\`\`javascript\nconst app = require('express')();\napp.listen(3000, () => {\n    console.log('Server running on port 3000');\n});\n\`\`\``,
        subPages: {}
    }
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

    // Add event listeners
    setupEventListeners();
}

// Initialize theme
function initializeTheme() {
    const savedTheme = localStorage.getItem('theme') || 'light';
    document.body.classList.toggle('dark-theme', savedTheme === 'dark');
    updateThemeToggle();
}

// Setup event listeners
function setupEventListeners() {
    // Theme toggle
    document.getElementById('theme-toggle').addEventListener('click', toggleTheme);

    // Search functionality
    document.getElementById('search-docs').addEventListener('input', handleSearch);

    // Export PDF
    document.getElementById('export-pdf').addEventListener('click', exportToPDF);
    
    // Mobile menu toggle
    const mobileMenuBtn = document.getElementById('mobile-menu-btn');
    const sidebar = document.querySelector('.sidebar');
    const overlay = document.getElementById('sidebar-overlay');
    
    mobileMenuBtn.addEventListener('click', (e) => {
        e.stopPropagation();
        sidebar.classList.toggle('show');
        overlay.classList.toggle('show');
        document.body.style.overflow = sidebar.classList.contains('show') ? 'hidden' : '';
    });
    
    overlay.addEventListener('click', () => {
        closeMobileMenu();
    });

    // Close sidebar when clicking menu items on mobile
    document.querySelectorAll('.menu-item').forEach(item => {
        item.addEventListener('click', () => {
            if (window.innerWidth <= 768) {
                closeMobileMenu();
            }
        });
    });

    // Close sidebar when clicking outside
    document.addEventListener('click', (e) => {
        if (window.innerWidth <= 768 && 
            !sidebar.contains(e.target) && 
            !mobileMenuBtn.contains(e.target) && 
            sidebar.classList.contains('show')) {
            closeMobileMenu();
        }
    });

    // Add helper function to close mobile menu
    function closeMobileMenu() {
        sidebar.classList.remove('show');
        overlay.classList.remove('show');
        document.body.style.overflow = '';
    }

    // Handle window resize
    let resizeTimer;
    window.addEventListener('resize', () => {
        clearTimeout(resizeTimer);
        resizeTimer = setTimeout(() => {
            if (window.innerWidth > 768) {
                sidebar.classList.remove('show');
                overlay.classList.remove('show');
                document.body.style.overflow = '';
            }
        }, 250);
    });
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
    const contentElement = document.getElementById('markdown-content');
    const contentClone = contentElement.cloneNode(true);
    
    contentClone.querySelectorAll('.copy-btn').forEach(btn => btn.remove());
    
    const content = contentClone.innerHTML;
    const isDarkMode = document.body.classList.contains('dark-theme');
    const style = `
        <style>
            body { 
                font-family: Arial, sans-serif;
                ${isDarkMode ? `
                    background-color: #1f2937;
                    color: #e5e7eb;
                ` : ''}
            }
            .preview-content { 
                max-width: 800px; 
                margin: 0 auto; 
                padding: 2rem;
                ${isDarkMode ? `
                    background-color: #1f2937;
                    color: #e5e7eb;
                ` : ''}
            }
            .preview-content * {
                color: ${isDarkMode ? '#e5e7eb' : '#000000'} !important;
            }
            .preview-content pre {
                ${isDarkMode ? `
                    background-color: #111827;
                    border: 1px solid #374151;
                ` : ''}
            }
            .preview-content code {
                ${isDarkMode ? `
                    background-color: #111827;
                    color: #e5e7eb;
                ` : ''}
            }
            @media print {
                body {
                    -webkit-print-color-adjust: exact;
                    print-color-adjust: exact;
                    color-adjust: exact;
                }
                * {
                    -webkit-print-color-adjust: exact;
                    print-color-adjust: exact;
                    color-adjust: exact;
                }
            }
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

// Update the menu with current pages
function updateMenu() {
    const menu = document.getElementById('menu');
    menu.innerHTML = '';
    
    Object.entries(pages).forEach(([pageName, pageData]) => {
        const menuItem = document.createElement('div');
        menuItem.className = 'menu-item';
        menuItem.innerHTML = `
            <div class="menu-title">
                ${pageName}
                <button class="add-submenu-btn" onclick="addSubPage('${pageName}')">+</button>
            </div>
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

        // Add submenu if exists
        if (Object.keys(pageData.subPages).length > 0) {
            const subMenu = document.createElement('div');
            subMenu.className = 'submenu';
            Object.entries(pageData.subPages).forEach(([subPageName, subPageContent]) => {
                const subMenuItem = document.createElement('div');
                subMenuItem.className = 'submenu-item';
                subMenuItem.innerHTML = `
                    <div class="menu-title">${subPageName}</div>
                    <div class="menu-actions">
                        <button class="edit-btn" onclick="editSubPage('${pageName}', '${subPageName}')">✎</button>
                        <button class="delete-btn" onclick="deleteSubPage('${pageName}', '${subPageName}')">×</button>
                    </div>
                `;
                subMenuItem.onclick = (e) => {
                    if (!e.target.className.includes('btn')) {
                        showSubPage(pageName, subPageName);
                    }
                };
                subMenu.appendChild(subMenuItem);
            });
            menuItem.appendChild(subMenu);
        }
        
        menu.appendChild(menuItem);
    });
}

// Enhanced page transition
function showPage(pageName) {
    const content = document.getElementById('markdown-content');
    content.style.opacity = '0';
    content.style.transform = 'translateY(20px)';
    
    setTimeout(() => {
        // Update sidebar breadcrumb
        const sidebarBreadcrumb = document.querySelector('.sidebar-breadcrumb');
        sidebarBreadcrumb.innerHTML = `<span class="separator">/</span> <span>${pageName}</span>`;
        
        // Update active state in menu
        document.querySelectorAll('.menu-item').forEach(item => {
            item.classList.remove('active');
            if (item.textContent.includes(pageName)) {
                item.classList.add('active');
            }
        });
        
        // Show markdown content with animation and page title
        content.innerHTML = `
            <div class="page-title">
                <h1>${pageName}</h1>
            </div>
            <div class="preview-content">
                ${marked.parse(pages[pageName].content || '')}
            </div>
        `;
        
        // Add copy buttons to code blocks
        addCopyButtons();
        
        // Apply syntax highlighting
        Prism.highlightAll();
        
        // Fade in content
        content.style.opacity = '1';
        content.style.transform = 'translateY(0)';
    }, 300);
}

// Show subpage content
function showSubPage(parentPage, subPageName) {
    const content = document.getElementById('markdown-content');
    content.style.opacity = '0';
    content.style.transform = 'translateY(20px)';
    
    setTimeout(() => {
        const sidebarBreadcrumb = document.querySelector('.sidebar-breadcrumb');
        sidebarBreadcrumb.innerHTML = `
            <span class="separator">/</span> 
            <span>${parentPage}</span>
            <span class="separator">/</span> 
            <span>${subPageName}</span>
        `;
        
        content.innerHTML = `
            <div class="page-title">
                <h1>${subPageName}</h1>
                <div class="breadcrumb">${parentPage} / ${subPageName}</div>
            </div>
            <div class="preview-content">
                ${marked.parse(pages[parentPage].subPages[subPageName] || '')}
            </div>
        `;
        
        addCopyButtons();
        Prism.highlightAll();
        
        content.style.opacity = '1';
        content.style.transform = 'translateY(0)';
    }, 300);
}

// Add copy buttons to code blocks
function addCopyButtons() {
    document.querySelectorAll('pre').forEach(pre => {
        const button = document.createElement('button');
        button.className = 'copy-btn';
        button.innerHTML = `<svg width="19" height="19" fill="currentColor" viewBox="0 0 16 16"><path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z"/><path d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z"/></svg>`;
        
        button.addEventListener('click', () => {
            const code = pre.querySelector('code').innerText;
            navigator.clipboard.writeText(code).then(() => {
                button.classList.add('copied');
                button.innerHTML = `
                    <svg width="30" height="30" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
                    </svg>
                `;
                
                setTimeout(() => {
                    button.classList.remove('copied');
                    button.innerHTML = `
                        <svg width="30" height="30" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z"/>
                            <path d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z"/>
                        </svg>
                    `;
                }, 2000);
            });
        });
        
        pre.appendChild(button);
    });
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

// Enhanced notification system
function showNotification(message, type = 'success') {
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.innerHTML = `
        <div class="notification-content">
            <div class="notification-icon">${type === 'success' ? '✓' : '!'}</div>
            <div class="notification-message">${message}</div>
        </div>
    `;
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.classList.add('show');
        setTimeout(() => {
            notification.classList.remove('show');
            setTimeout(() => notification.remove(), 300);
        }, 3000);
    }, 100);
}

// Update save functions to use notifications
function saveNewPage() {
    const pageName = document.getElementById('page-name').value.trim();
    const content = document.getElementById('page-content').value.trim();
    
    if (pageName && content) {
        if (!pages[pageName]) {
            pages[pageName] = { content, subPages: {} };
            updateMenu();
            showPage(pageName);
            closeModal();
            showNotification('Page created successfully');
        } else {
            showNotification('A page with this name already exists!', 'error');
        }
    } else {
        showNotification('Please enter both page name and content!', 'error');
    }
}

// Add new subpage
function addSubPage(parentPage) {
    event.stopPropagation();
    const modal = document.createElement('div');
    modal.className = 'modal';
    modal.innerHTML = `
        <div class="modal-content">
            <h2>Create Sub Page</h2>
            <input type="text" id="subpage-name" placeholder="Sub Page Name" class="modal-input">
            <textarea id="subpage-content" placeholder="Enter markdown content..." class="modal-textarea"></textarea>
            <div class="modal-buttons">
                <button onclick="saveSubPage('${parentPage}')">Save</button>
                <button onclick="closeModal()">Cancel</button>
            </div>
        </div>
    `;
    document.body.appendChild(modal);
}

// Save new subpage
function saveSubPage(parentPage) {
    const subPageName = document.getElementById('subpage-name').value.trim();
    const content = document.getElementById('subpage-content').value.trim();
    
    if (subPageName && content) {
        if (!pages[parentPage].subPages[subPageName]) {
            pages[parentPage].subPages[subPageName] = content;
            updateMenu();
            showSubPage(parentPage, subPageName);
            closeModal();
            showNotification('Sub page created successfully');
        } else {
            showNotification('A sub page with this name already exists!', 'error');
        }
    } else {
        showNotification('Please enter both sub page name and content!', 'error');
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
            <textarea id="page-content" class="modal-textarea">${pages[pageName].content}</textarea>
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
        pages[pageName].content = content;
        showPage(pageName);
        closeModal();
    } else {
        alert('Content cannot be empty!');
    }
}

// Edit existing subpage
function editSubPage(parentPage, subPageName) {
    const modal = document.createElement('div');
    modal.className = 'modal';
    modal.innerHTML = `
        <div class="modal-content">
            <h2>Edit Sub Page</h2>
            <input type="text" id="subpage-name" value="${subPageName}" readonly class="modal-input">
            <textarea id="subpage-content" class="modal-textarea">${pages[parentPage].subPages[subPageName]}</textarea>
            <div class="modal-buttons">
                <button onclick="saveEditedSubPage('${parentPage}', '${subPageName}')">Save</button>
                <button onclick="closeModal()">Cancel</button>
            </div>
        </div>
    `;
    document.body.appendChild(modal);
}

// Save edited subpage
function saveEditedSubPage(parentPage, subPageName) {
    const content = document.getElementById('subpage-content').value.trim();
    
    if (content) {
        pages[parentPage].subPages[subPageName] = content;
        showSubPage(parentPage, subPageName);
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

// Delete a subpage
function deleteSubPage(parentPage, subPageName) {
    if (confirm(`Are you sure you want to delete "${subPageName}"?`)) {
        delete pages[parentPage].subPages[subPageName];
        updateMenu();
        
        // Show parent page or empty content
        showPage(parentPage);
    }
}

// Initialize when the page loads
document.addEventListener('DOMContentLoaded', initViewer);