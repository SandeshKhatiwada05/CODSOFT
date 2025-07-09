package org.JSP;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.sql.*;

public class ViewStudent extends JFrame {

    Image backgroundImage;
    JTable table;
    DefaultTableModel tableModel;

    public ViewStudent() {
        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/Background.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Background image not found!");
        }

        // Background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setOpaque(true);

        // Table model
        String[] columns = {"ID", "Name", "Roll Number", "Grade", "Email"};
        tableModel = new DefaultTableModel(columns, 0);

        // JTable setup
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setForeground(Color.BLACK);
        table.setBackground(Color.WHITE);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  // Disable auto-resize

        // Set preferred widths
        table.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(150);  // Name
        table.getColumnModel().getColumn(2).setPreferredWidth(100);  // Roll
        table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Grade
        table.getColumnModel().getColumn(4).setPreferredWidth(250);  // Email

        // Renderer
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(Object.class);
        renderer.setOpaque(true);
        renderer.setBackground(Color.WHITE);
        renderer.setForeground(Color.BLACK);

        // Scroll pane setup (horizontal + vertical)
        JScrollPane scrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(750, 400));
        scrollPane.getViewport().setOpaque(true);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Card-style panel
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setOpaque(true);
        cardPanel.setBackground(new Color(255, 255, 255, 220));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cardPanel.add(scrollPane, BorderLayout.CENTER);

        // Add to background (centered)
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        backgroundPanel.add(cardPanel, gbc);

        // Frame setup
        setContentPane(backgroundPanel);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        loadStudentsFromDB();
    }

    private void loadStudentsFromDB() {
        try {
            DBConnection db = new DBConnection();
            String sql = "SELECT id, name, roll_number, grade, email FROM Student";
            PreparedStatement ps = db.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            tableModel.setRowCount(0); // Clear previous rows

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_number"),
                        rs.getString("grade"),
                        rs.getString("email")
                };
                tableModel.addRow(row);
            }

            rs.close();
            ps.close();
            db.con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading students: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
