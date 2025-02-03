# Java Swing and AWT Notes 

## Table of Contents
- [Basic Concepts](#basic-concepts)
- [Intermediate Level](#intermediate-level)
- [Advanced Concepts](#advanced-concepts)
- [Components and Usage](#components-and-usage)
- [Performance and Best Practices](#performance-and-best-practices)
- [Common Interview Scenarios](#common-interview-scenarios)

## Basic Concepts

### 1. AWT vs Swing
- AWT is the older framework that uses your operating system's components
- Swing is newer and draws its own components using Java
- Main differences:
  - AWT: Heavy, platform-dependent, basic components
  - Swing: Light, platform-independent, more components and features

### 2. Swing Component Hierarchy
- Everything starts from Object class
- Components follow this order:
  Object → Component → Container → Window → Frame → JFrame
- All Swing components (buttons, panels, etc.) come from JComponent

### 3. Containers in Swing
- Think of containers as boxes that hold other components
- Common containers:
  - JFrame: Main window of your application
  - JPanel: Group related components together
  - JDialog: Create pop-up windows
- Layout managers help arrange components inside containers

### 4. Swing Layouts
- FlowLayout: Places components in a line (like words in a paragraph)
- BorderLayout: Divides window into five parts (North, South, East, West, Center)
- GridLayout: Arranges components in rows and columns (like a table)
- GridBagLayout: Most flexible but complex (like a customizable grid)
- BoxLayout: Stacks components vertically or horizontally
- CardLayout: Shows one component at a time (like a stack of cards)

### 5. Event Handling
- When users interact (click, type), events are created
- Components send out events, listeners catch them
- Common listeners:
  - ActionListener: For buttons, menu items
  - MouseListener: For mouse actions
  - KeyListener: For keyboard input

## Intermediate Level

### 6. SwingUtilities.invokeLater()
- Ensures GUI updates happen safely
- Prevents threading issues
- Used when starting your application or updating the GUI
- Example:
```java
SwingUtilities.invokeLater(() -> {
    JFrame frame = new JFrame("My App");
    frame.setVisible(true);
});
```

### 7. Modal vs Non-Modal Dialogs
- Modal: User must deal with this window first
  - Like a "Save changes?" popup
- Non-Modal: User can interact with other windows
  - Like a find/replace window

### 8. Look and Feel
- Controls how your application looks
- Built-in options:
  - Metal: Java's default look
  - Nimbus: Modern Java look
  - System: Matches your operating system
- Can be changed with:
```java
UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
```

### 9. Custom Painting
- Override paintComponent() for custom drawing
- Use Graphics2D for better drawing options
- Double buffering happens automatically in Swing
- Example:
```java
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawLine(0, 0, 100, 100);
}
```

### 10. JLayer Usage
- Adds effects to existing components
- Can add validation markers
- Useful for adding overlays
- Example: Adding a loading overlay

## Advanced Concepts

### 11. Glass Pane
- Invisible layer over your application
- Can catch mouse events
- Useful for:
  - Blocking user input
  - Showing temporary information
  - Drawing over other components

### 12. RepaintManager
- Controls when and how components are repainted
- Optimizes painting operations
- Useful for custom painting needs
- Can be customized for special effects

### 13. Drag and Drop
- Use TransferHandler for basic drag and drop
- DragGestureListener for custom drag behavior
- DropTargetListener for custom drop behavior
- Example:
```java
component.setTransferHandler(new TransferHandler("text"));
```

### 14. Custom Components
- Extend existing Swing components
- Override necessary methods
- Handle your own events
- Implement custom painting if needed

### 15. Input/Action Maps
- Maps keys to actions
- Better than KeyListener for most cases
- Example:
```java
InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
inputMap.put(KeyStroke.getKeyStroke("SPACE"), "action.name");
```

## Components and Usage

### 16. JTable vs JTree
- JTable: Shows data in rows and columns
- JTree: Shows hierarchical data
- Different models and renderers
- Different event handling needs

### 17. Custom Cell Rendering
- Implement TableCellRenderer
- Control how cells look
- Can change colors, fonts, icons
- Example:
```java
class CustomRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(/*params*/) {
        // Custom rendering logic
    }
}
```

### 18. Text Component Documents
- Models for text components
- PlainDocument: Basic text
- StyledDocument: Formatted text
- Can filter and modify text input

### 19. Custom ComboBox Models
- Implement ComboBoxModel
- Control data and selection
- Update listeners when data changes
- Custom rendering options

### 20. Menu Implementation
- JMenuBar: Top menu bar
- JMenu: Drop-down menu
- JMenuItem: Individual menu items
- Support for shortcuts and icons

## Performance and Best Practices

### 21. Performance Optimization
- Use appropriate layout managers
- Minimize custom painting
- Reuse objects when possible
- Handle long operations in background

### 22. Common Pitfalls
- Avoid updating GUI outside EDT
- Clean up resources properly
- Don't block EDT with long operations
- Test with different look and feels

### 23. Resource Management
- Load images efficiently
- Dispose of heavy resources
- Use weak references when appropriate
- Cache frequently used resources

### 24. Long-Running Tasks
- Use SwingWorker for background tasks
- Show progress to users
- Allow task cancellation
- Example:
```java
SwingWorker<Result, Progress> worker = new SwingWorker<>() {
    @Override
    protected Result doInBackground() throws Exception {
        // Long running task
    }
};
worker.execute();
```

### 25. Error Handling
- Show user-friendly error messages
- Log errors for debugging
- Provide recovery options
- Don't crash on unexpected input

## Common Interview Scenarios

### 26. Text Editor Implementation
- Use JTextPane or JEditorPane
- Implement syntax highlighting
- Add search/replace
- Handle file operations

### 27. Drag and Drop Component
- Implement TransferHandler
- Handle drag gestures
- Process dropped data
- Provide visual feedback

### 28. Form Validation
- Validate input as user types
- Show error indicators
- Prevent invalid submission
- Clear validation on reset

### 29. Custom Tree Renderer
- Implement TreeCellRenderer
- Show custom icons
- Handle selection states
- Update efficiently

### 30. Dynamic Dialog
- Create resizable dialog
- Update content dynamically
- Handle dialog closure
- Clean up resources
