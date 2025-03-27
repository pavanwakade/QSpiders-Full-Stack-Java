package automate;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.util.List;

public class GitAdminPanel {
    private JFrame frame;
    private JList<String> repoList;
    private DefaultListModel<String> listModel;

    public GitAdminPanel() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Git Auto Commit Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        updateRepoList();
        repoList = new JList<>(listModel);
        repoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(repoList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Repository");
        JButton removeButton = new JButton("Remove Selected");

        addButton.addActionListener(e -> addRepository());
        removeButton.addActionListener(e -> removeRepository());

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addRepository() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select Git Repository Folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            String repoPath = fileChooser.getSelectedFile().getAbsolutePath();
            GitAutoCommit.addRepository(repoPath);
            updateRepoList();
            JOptionPane.showMessageDialog(frame, "Repository added: " + repoPath, 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void removeRepository() {
        int selectedIndex = repoList.getSelectedIndex();
        if (selectedIndex != -1) {
            String repoPath = listModel.getElementAt(selectedIndex);
            GitAutoCommit.removeRepository(repoPath);
            updateRepoList();
            JOptionPane.showMessageDialog(frame, "Repository removed: " + repoPath, 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a repository to remove.", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateRepoList() {
        listModel.clear();
        List<String> repos = GitAutoCommit.getRepositories();
        for (String repo : repos) {
            listModel.addElement(repo);
        }
    }

    public static void main(String[] args) {
        new Thread(() -> GitAutoCommit.startAutoCommit()).start();
        SwingUtilities.invokeLater(() -> new GitAdminPanel());
    }
}