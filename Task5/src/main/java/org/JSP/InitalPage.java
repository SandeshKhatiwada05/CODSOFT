package org.JSP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class InitalPage extends JFrame implements ActionListener {

    JButton btnAdd, btnEdit, btnView;
    Image backgroundImage;

    public InitalPage() {
        // Load background image from resources
        try {
            // Adjust path if needed
            backgroundImage = ImageIO.read(new File("src/main/resources/Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Background image not found!");
        }

        // Create buttons with styling
        btnAdd = createStyledButton("Add Students");
        btnEdit = createStyledButton("Edit Students");
        btnView = createStyledButton("View Students");

        btnAdd.addActionListener(this);
        btnView.addActionListener(this);
        btnEdit.addActionListener(this);

        // Create a panel with background image painted
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Draw scaled image to fill the panel
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new GridBagLayout()); // for centering buttons

        // Panel for buttons in a vertical BoxLayout
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false); // transparent so bg image is visible
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        // Add buttons with spacing
        buttonsPanel.add(btnAdd);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonsPanel.add(btnEdit);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonsPanel.add(btnView);

        // Add buttons panel to background panel centered
        backgroundPanel.add(buttonsPanel);

        // Add background panel to frame
        setContentPane(backgroundPanel);

        // Frame settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);  // reasonable fixed size, change as needed
        setLocationRelativeTo(null); // center frame on screen
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 120, 215));  // nice blue color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        button.setOpaque(true);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            new StudentAddition();
        } else if (e.getSource() == btnEdit) {
            System.out.println("Edit Students clicked");
        } else if (e.getSource() == btnView) {
            new ViewStudent();
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(() -> new InitalPage());
    }
}
