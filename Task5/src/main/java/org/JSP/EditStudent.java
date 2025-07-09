package org.JSP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditStudent extends JFrame implements ActionListener {

    JLabel lblRoll, lblName, lblGrade, lblEmail;
    JComboBox<String> comboRoll;
    JTextField txtName, txtGrade, txtEmail;
    JButton btnUpdate;

    Image backgroundImage;

    public EditStudent() {
        // Load background image
        try {
            backgroundImage = javax.imageio.ImageIO.read(new java.io.File("src/main/resources/Background.png"));
        } catch (Exception e) {
            e.printStackTrace();
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

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblRoll = new JLabel("Select Roll No:");
        lblName = new JLabel("Name:");
        lblGrade = new JLabel("Grade:");
        lblEmail = new JLabel("Email:");

        comboRoll = new JComboBox<>();
        loadRollNumbers();

        txtName = new JTextField(20);
        txtGrade = new JTextField(20);
        txtEmail = new JTextField(20);

        comboRoll.addActionListener(this);

        // Row 0
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblRoll, gbc);
        gbc.gridx = 1;
        formPanel.add(comboRoll, gbc);

        // Row 1
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(lblName, gbc);
        gbc.gridx = 1;
        formPanel.add(txtName, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(lblGrade, gbc);
        gbc.gridx = 1;
        formPanel.add(txtGrade, gbc);

        // Row 3
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        formPanel.add(txtEmail, gbc);

        // Row 4 - Button
        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 16));
        btnUpdate.setBackground(new Color(0, 120, 215));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnUpdate.addActionListener(this);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnUpdate, gbc);

        backgroundPanel.add(formPanel);

        setContentPane(backgroundPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadRollNumbers() {
        try {
            DBConnection db = new DBConnection();
            Statement stmt = db.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT roll_number FROM Student");

            while (rs.next()) {
                comboRoll.addItem(rs.getString(1));
            }

            rs.close();
            stmt.close();
            db.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudentData(String roll) {
        try {
            DBConnection db = new DBConnection();
            PreparedStatement ps = db.con.prepareStatement("SELECT * FROM Student WHERE roll_number = ?");
            ps.setString(1, roll);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtName.setText(rs.getString("name"));
                txtGrade.setText(rs.getString("grade"));
                txtEmail.setText(rs.getString("email"));
            }

            rs.close();
            ps.close();
            db.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStudentData() {
        String name = txtName.getText().trim();
        String grade = txtGrade.getText().trim();
        String email = txtEmail.getText().trim();
        String roll = (String) comboRoll.getSelectedItem();

        if (name.isEmpty() || roll == null) {
            JOptionPane.showMessageDialog(this, "Name and Roll Number cannot be empty.");
            return;
        }

        try {
            DBConnection db = new DBConnection();
            String sql = "UPDATE Student SET name=?, grade=?, email=? WHERE roll_number=?";
            PreparedStatement ps = db.con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, grade.isEmpty() ? null : grade);
            ps.setString(3, email.isEmpty() ? null : email);
            ps.setString(4, roll);

            int updated = ps.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No record was updated.");
            }

            ps.close();
            db.con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Update failed: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboRoll) {
            String roll = (String) comboRoll.getSelectedItem();
            if (roll != null) {
                loadStudentData(roll);
            }
        } else if (e.getSource() == btnUpdate) {
            updateStudentData();
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(EditStudent::new);
    }
}
