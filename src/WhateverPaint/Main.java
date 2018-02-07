/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhateverPaint;





public class Main {
    public static void main(String[] args) {
       

        IntroWindow intro = new IntroWindow();
        intro.setVisible(true);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
        }

        intro.dispose();

        MainWindow mainWindow = new MainWindow();
        mainWindow.show();
    }
}
