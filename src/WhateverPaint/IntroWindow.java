/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhateverPaint;

import javax.swing.JLabel;
import javax.swing.JWindow;



public class IntroWindow  extends JWindow{

    public IntroWindow() {
        setSize(600, 300);
        setLocationRelativeTo(null);
        JLabel label = new JLabel();
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/intro.png")));
        add(label);
        setAlwaysOnTop(true);
    }
    
    
    
}
