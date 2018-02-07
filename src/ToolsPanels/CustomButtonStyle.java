/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolsPanels;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JMenu;



public class CustomButtonStyle {
    
    JButton button = new JButton();
    JMenu menu = new JMenu();
    public CustomButtonStyle(JButton button)
    {
        this.button = button;
        button.setContentAreaFilled(false);
        button.setOpaque(true);
    }
    public void release(JButton button) {
        
        button.setBackground(new Color(83, 83, 83));
    }
    public void exit(JButton button) {
        button.setBackground(new Color(40, 40, 40));
    }
    public void press(JButton button){
        button.setBackground(new Color(200, 200, 200));
    }
     public CustomButtonStyle(JMenu button)
    {
        this.menu = button;
        menu.setContentAreaFilled(false);
        menu.setOpaque(true);
    }
    public void release(JMenu button) {
        
        menu.setBackground(new Color(83, 83, 83));
    }
    public void exit(JMenu button) {
        menu.setBackground(new Color(40, 40, 40));
    }
    public void press(JMenu button){
        menu.setBackground(new Color(200, 200, 200));
    }
    
}
