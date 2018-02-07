/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UndoRedoManager;

import java.util.Stack;



public class Manager {
    
    public static Stack<ScreenShot> undoStack = new Stack();
    public static Stack<ScreenShot> redoStack = new Stack(); 
    
    
}
