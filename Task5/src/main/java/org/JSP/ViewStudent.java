package org.JSP;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewStudent extends JFrame {

    Image backgroundImage;
    JTable table;
    DefaultTableModel tableModel;

    public ViewStudent() {
        // Load background image
        try {
            backgroundImage = javax.imageio.ImageIO.read(new java.io.File("src/main/resources/Background.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Background image not found!");
        }

        // Background panel with image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setOpaque(false);  // Keep transparent so bg image visible

        // Table model with column names matching Student table
        String[] columns = {"ID", "Name", "Roll Number", "Grade", "Email"};
        tableModel = new DefaultTableModel(columns, 0);

        // JTable setup
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        // Set table background opaque white for readability
        table.setOpaque(true);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(25);

        // Cell renderer opaque with white background
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(Object.class);
        renderer.setOpaque(true);
        renderer.setBackground(Color.WHITE);
        renderer.setForeground(Color.BLACK);

        // JScrollPane setup
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);                     // scroll pane transparent
        scrollPane.getViewport().setOpaque(true);       // viewport opaque white
        scrollPane.getViewport().setBackground(Color.WHITE);

        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

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

            // Clear existing rows
            tableModel.setRowCount(0);

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("name");
                row[2] = rs.getString("roll_number");
                row[3] = rs.getString("grade");
                row[4] = rs.getString("email");
                tableModel.addRow(row);
            }

            rs.close();
            ps.close();
            db.con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(ViewStudent::new);
    }
}
    