package org.JSP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class StudentAddition extends JFrame implements ActionListener {

    JLabel lblName, lblRoll, lblGrade, lblEmail;
    JTextField txtName, txtRoll, txtGrade, txtEmail;
    JButton btnSave;

    Image backgroundImage;

    public StudentAddition() {
        // Load background image (same as before)
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
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setOpaque(true);

        // Form panel with labels and fields
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);  // transparent to show bg
        formPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblName = new JLabel("Name:");
        lblRoll = new JLabel("Roll Number:");
        lblGrade = new JLabel("Grade:");
        lblEmail = new JLabel("Email:");

        txtName = new JTextField(20);
        txtRoll = new JTextField(20);
        txtGrade = new JTextField(20);
        txtEmail = new JTextField(20);

        // Row 0 - Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblName, gbc);
        gbc.gridx = 1;
        formPanel.add(txtName, gbc);

        // Row 1 - Roll Number
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblRoll, gbc);
        gbc.gridx = 1;
        formPanel.add(txtRoll, gbc);

        // Row 2 - Grade
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblGrade, gbc);
        gbc.gridx = 1;
        formPanel.add(txtGrade, gbc);

        // Row 3 - Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        formPanel.add(txtEmail, gbc);

        // Save button row
        btnSave = new JButton("Save Student");
        btnSave.setFont(new Font("Arial", Font.BOLD, 16));
        btnSave.setBackground(new Color(0, 120, 215));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnSave, gbc);

        // Add formPanel centered inside backgroundPanel
        backgroundPanel.add(formPanel);

        setContentPane(backgroundPanel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSave) {
            String name = txtName.getText().trim();
            String roll = txtRoll.getText().trim();
            String grade = txtGrade.getText().trim();
            String email = txtEmail.getText().trim();

            if(name.isEmpty() || roll.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Roll Number are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                DBConnection db = new DBConnection();
                String sql = "INSERT INTO Student (name, roll_number, grade, email) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = db.con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, roll);
                ps.setString(3, grade.isEmpty() ? null : grade);
                ps.setString(4, email.isEmpty() ? null : email);

                int rows = ps.executeUpdate();
                if(rows > 0) {
                    JOptionPane.showMessageDialog(this, "Student saved successfully!");
                    // Clear fields
                    txtName.setText("");
                    txtRoll.setText("");
                    txtGrade.setText("");
                    txtEmail.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to save student.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                ps.close();
                db.con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(() -> new StudentAddition());
    }
}
