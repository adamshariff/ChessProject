package Graphics;//package line

//Imports
import Model.Model;                     //Used as brains of the game
import javax.swing.JPanel;              //Used to be added to a JFrame
import javax.swing.JButton;             //Used for menu buttons
import java.awt.Color;                  //Used to hold colour values
import java.awt.event.ActionEvent;      //Used to capture events such as button press
import java.awt.event.ActionListener;   //Listens to actions
import java.awt.event.ComponentAdapter; //Used for component events
import java.awt.event.ComponentEvent;   //Used to capture component events such as resizing of a component
import Model.Mouse;                     //Used for mouse clicking

/* Title: Gui
 * Version: 1.0
 * Author: Adam Shariff
 * Last Modified: 19/12/2022
 * Description: Graphical hub of the graphics hub. Used to communicate graphics components with model.
 * Extends JPanel to be able to added to JFrame from a model class. This class also allows graphics to be resizable
 */
public class Gui extends JPanel {

    //Instance variables
    private JPanel menu;       //Holds menu component
    private Model model;       //Model to be interfaced with
    private Game game;         //Holds graphical game component which contains

    //@Constructor: Initializes values and add component listener
    //@param model: Holds model to be interfaced
    public Gui(Model model) {
        //Sets up communication with model
        this.model=model;
        model.setGui(this);

        //Sets upd menu and game
        menu();
        game = new Game(this);

        //Adds a component listener with will update graphics if the Gui has been resized
        this.addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent evt){
                //Updates gui
                updateGui();
            }
        });

        //Adds mouse listener
        this.addMouseListener(new Mouse(model,this));
    }//constructor

    //Title: menu
    //Description: Creates menu for the game
    private void menu(){
        //Creates new menu and buttons
        menu = new JPanel();
        JButton start = new JButton("Start Fresh Game");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setPhase(-5);
            }
        });

        JButton load = new JButton(("Premake Game"));
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setPhase(-1);
            }
        });

        //Adds buttons and menu to JPanel
        menu.add(start);
        menu.add(load);
        this.add(menu);
    }//menu

    //Title: menu2
    //Description: Creates submenu for the game
    public void menu2(){
        this.remove(menu);

        //Creates new menu and buttons
        menu = new JPanel();
        JButton black = new JButton("Start black");
        black.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.newBoard();
                model.setPhase(3);
            }
        });

        JButton white = new JButton(("Start white"));
        white.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.newBoard();
                model.setPhase(2);
            }
        });

        //Adds buttons and menu to JPanel
        menu.add(black);
        menu.add(white);
        this.add(menu);
    }//menu2

    //Title: menu3
    //Description: Creates submenu for the game
    public void menu3(){
        this.remove(menu);
        //Creates new menu and buttons
        menu = new JPanel();
        JButton pvp = new JButton("Player vs.Player");
        pvp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setPhase(1);
            }
        });

        JButton pva = new JButton(("Player vs. AI"));
        pva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setPhase(-6);
            }
        });

        //Adds buttons and menu to JPanel
        menu.add(pvp);
        menu.add(pva);
        this.add(menu);
    }//menu3

    //Title: menu3
    //Description: Creates submenu for the game
    public void menu4(){
        this.remove(menu);
        //Creates new menu and buttons
        menu = new JPanel();
        JButton aiBlack = new JButton("Black AI");
        aiBlack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.createAI(Color.black);
                model.aiBlack=true;
                model.setPhase(1);
            }
        });

        JButton aiWhite = new JButton(("White AI"));
        aiWhite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.createAI(Color.white);
                model.aiWhite=true;
                model.setPhase(1);
            }
        });

        //Adds buttons and menu to JPanel
        menu.add(aiBlack);
        menu.add(aiWhite);
        this.add(menu);
    }//menu4

    //Title: menu3
    //Description: Creates submenu for the game
    public void menu5(){
        this.remove(game);
        //Creates new menu and buttons
        menu = new JPanel();
        JButton pvp = new JButton("Player vs.Player");
        pvp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setPhase(1);
            }
        });

        JButton pva = new JButton(("Player vs. AI"));
        pva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setPhase(-8);
            }
        });

        //Adds buttons and menu to JPanel
        menu.add(pvp);
        menu.add(pva);
        this.add(menu);
    }//menu3

    //Title: menu3
    //Description: Creates submenu for the game
    public void menu6(){
        System.out.println("Here");
        this.remove(menu);
        //Creates new menu and buttons
        menu = new JPanel();
        JButton aiBlack = new JButton("Black AI");
        aiBlack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.createAI(Color.black);
                model.aiBlack=true;
                model.setPhase(-7);
            }
        });

        JButton aiWhite = new JButton(("White AI"));
        aiWhite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.createAI(Color.white);
                model.aiWhite=true;
                model.setPhase(-7);
            }
        });

        //Adds buttons and menu to JPanel
        menu.add(aiBlack);
        menu.add(aiWhite);
        this.add(menu);
    }//menu4

    //Title: toGame
    //Description: Transitions to form menu mode to game mode
    public void toGame(){
        //Transition
        this.remove(menu);
        this.repaint();
        this.add(game);
        updateGui();
        model.setPhase(2);
    }//toGame

    //Title: toLoad
    //Description: Transitions to form menu mode to load mode
    public void toLoad(){
        this.remove(menu);
        this.repaint();
        this.add(game);
        updateGui();
        model.setPhase(-2);
    }//toLoad

    //Title: updateGui
    //Description: Updates components of game
    public void updateGui(){
        //Updates components
        game.updateGame();
        this.updateUI();
    }//updateGui

    /* Title: isAlive
     * Description: Gets if a piece is alive form model
     * @param colour: Colour of piece
     * @param i: Integer of piece being looked at
     * @return: Returns boolean if piece is alive
     */
    public boolean isAlive(Color colour, int i){
        return model.isAlive(colour,i);
    }//isAlive

    /* Title: getPieceX
     * Description: Gets the x position of piece
     * @param colour: Colour of piece
     * @param i: Integer of piece being looked at
     * @return: Returns integer of the x value of piece
     */
    public int getPieceX(Color colour, int i) {
        return model.getPieceX(colour,i);
    }//getPieceX

    /* Title: getPieceY
     * Description: Gets the y position of piece
     * @param colour: Colour of piece
     * @param i: Integer of piece being looked at
     * @return: Returns integer of the y value of piece
     */
    public int getPieceY(Color colour, int i) {
        return model.getPieceY(colour,i);
    }//getPieceY

    //Title: reset
    //Description: Resets the graphics for a new game
    public void reset(){
        game = new Game(this);
        menu();
    }

    /* Title: getPhase
     * Description: Returns phase of program
     * @return: Returns program phase
     */
    public int getPhase(){
        return model.getPhase();
    }//getPhase

    /* Title: getWhite
     * Description: Returns name of white piece
     * @param i: Integer of piece being looked at
     * @return: Returns name of piece
     */
    public String getWhite(int i){
        return model.whiteName(i);
    }//getWhite

    /* Title: getBlack
     * Description: Returns name of black piece
     * @param i: Integer of piece being looked at
     * @return: Returns name of piece
     */
    public String getBlack(int i){
        return model.blackName(i);
    }//getBlack

    /* Title: prom
     * Description: Returns if there is a promotion
     * @return: Returns boolean of promotion
     */
    public boolean prom(){
        return model.getPromotion();
    }//promotion


    /* Title: blackNull
     * Description: Returns if black is null for loading game
     * @return: Returns boolean if black team is null
     */
    public boolean blackNull(){
        return model.getBlack()==null;
    }//blackNull

    /* Title: whiteNull
     * Description: Returns if white is null for loading game
     * @return: Returns boolean if white team is null
     */
    public boolean whiteNull(){
        return model.getWhite()==null;
    }//whiteNull


    /* Title: select
     * Description: Returns select for loading pieces
     * @return: Returns int select for spacial position
     */
    public int select(){
        return model.select;
    }//select

    /* Title: whiteWin
     * Description: Returns weather white has won
     * @return: Returns boolean of victory
     */
    public boolean whiteWin(){
        return model.whiteWin();
    }//whiteWin

    /* Title: blackWin
     * Description: Returns weather black has won
     * @return: Returns boolean of victory
     */
    public boolean blackWin(){
        return model.blackWin();
    }//blackWin

    /* Title: stale
     * Description: Returns weather there was stalemate
     * @return: Returns boolean of stalemate
     */
    public boolean stale(){
        return model.stale();
    }//stale
}//Gui
