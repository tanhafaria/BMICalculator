/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bmicalculatorapp;
import com.mycompany.bmicalculatorapp.view.BMIAppGUI;
import javax.swing.SwingUtilities;
/**
 *
 * @author Admin
 */
public class BMICalculatorApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BMIAppGUI().setVisible(true);
        });
    }
}
