package Model;//package line

//Imports
import Graphics.Gui;       //Contains graphics for graphical interface
import javax.swing.JFrame; //Creates frame for graphical interface
import java.awt.Dimension; //Used to set minimum size of interface

/* Title: Main
 * Version: 1.0
 * Author: Adam Shariff
 * Last Modified: 02/10/2022
 * Description: This class is used to start the program.
 */
public class Main {
    public static void main(String[]args){
        //Initializes frame, model, and graphics
        JFrame frame = new JFrame();
        Model model = new Model();
        Gui graphics = new Gui(model);

        //Set frame properties
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500,400));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(graphics);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chess: By Adam Shariff");
    }//main
}//Main
