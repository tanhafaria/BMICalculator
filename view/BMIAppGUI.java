/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bmicalculatorapp.view;
import com.mycompany.bmicalculatorapp.controller.UserController;
import com.mycompany.bmicalculatorapp.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Admin
 */
public class BMIAppGUI extends JFrame {
    private final UserController userController;
    
    private JTextField txtName, txtHeight, txtWeight, txtId, txtBMI;
    private JTextArea userDisplay;
    
    public BMIAppGUI() {
        userController = new UserController();
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("BMI Calculator");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Main panel with padding
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and Text Fields
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("ID:"), gbc);
        txtId = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Name:"), gbc);
        txtName = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Height (m):"), gbc);
        txtHeight = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(txtHeight, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Weight (kg):"), gbc);
        txtWeight = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(txtWeight, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(new JLabel("BMI:"), gbc);
        txtBMI = new JTextField();
        txtBMI.setEditable(false);
        gbc.gridx = 1;
        mainPanel.add(txtBMI, gbc);

        // Buttons
        JButton btnAdd = new JButton("Add User");
        JButton btnUpdate = new JButton("Update User");
        JButton btnDelete = new JButton("Delete User");
        JButton btnView = new JButton("View Users");
        JButton btnCalculate = new JButton("Calculate BMI");

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnView);
        buttonPanel.add(btnCalculate);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel, BorderLayout.NORTH);

        // Display Area for Users
        userDisplay = new JTextArea();
        userDisplay.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        userDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(userDisplay);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        add(scrollPane, BorderLayout.CENTER);

        // Button Action Listeners
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewUsers();
            }
        });

        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateBMI();
            }
        });
    }
    
    private void addUser() {
        String name = txtName.getText();
        double height = Double.parseDouble(txtHeight.getText());
        double weight = Double.parseDouble(txtWeight.getText());

        userController.addUser(name, height, weight);
        JOptionPane.showMessageDialog(this, "User added successfully!");
        viewUsers();
        clearFields();
    }

    private void updateUser() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please provide an ID to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        double height = Double.parseDouble(txtHeight.getText());
        double weight = Double.parseDouble(txtWeight.getText());
        
        if (txtHeight.getText().isEmpty() && txtWeight.getText().isEmpty() && txtName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please provide at least one value to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for(User user :userController.getUsers()){
            if(user.getId() == id){
                if(name != null) user.setName(name);
                if(height != 0.0) user.setHeight(height);
                if(weight != 0.0) user.setWeight(weight);
                JOptionPane.showMessageDialog(this, "User updated successfully.");
                clearFields();
                viewUsers();
                return;
            }
        }
        
        JOptionPane.showMessageDialog(this, "User not found.");
        
        viewUsers();
        clearFields();
    }

    private void deleteUser() {
        if(txtId.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please provide an ID for deletion.!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        int id = Integer.parseInt(txtId.getText());
        if (userController.deleteUser(id)) {
        JOptionPane.showMessageDialog(this, "User deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "User not found.");
        }
        viewUsers();
        clearFields();     
    }

    private void viewUsers() {
        userDisplay.setText("");
        for(User user : userController.getUsers()) {
            userDisplay.append(user + "\n");
        }
    }
    
    private void calculateBMI() {
        try {
            double height = Double.parseDouble(txtHeight.getText());
            double weight = Double.parseDouble(txtWeight.getText());
            double bmi = weight / (height * height);
            txtBMI.setText(String.format("%.2f", bmi));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid height and weight values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtHeight.setText("");
        txtWeight.setText("");
        txtBMI.setText("");
    }
}
    
