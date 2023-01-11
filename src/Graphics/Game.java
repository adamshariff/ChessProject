package Graphics;//package line

//Imports
import javax.swing.JComponent; //Used for creating game graphics
import javax.swing.ImageIcon;  //Used for getting images
import java.awt.Color;         //Used for colour attributes when creating graphics
import java.awt.Dimension;     //Used for set graphics size
import java.awt.Graphics;      //Used to actually creating graphics

/* Title: Game
 * Version: 1.0
 * Author: Adam Shariff
 * Last Modified: 19/12/2022
 * Description: Creates graphics for chess game. Extends JComponent to be compatible with JFrame and JPanel for display.
 * All chess piece images are from a creative commons:https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent
 */
class Game extends JComponent {

    //Instance variables
    private int sizeX;   //Holds width of graphics
    private int sizeY;   //Holds height of graphics
    private final Gui p; //Used to access model information such as location of pieces

    //@Constructor: Sets default size of graphics and gets gui
    //@param p: Holds gui to interface
    public Game(Gui p){
        this.p=p;
        this.setPreferredSize(new Dimension(p.getWidth(),p.getHeight()));
    }//constructor

    //Title: updateGame
    //Description: Method is used to get new size of graphics and repaint all graphics accordingly
    public void updateGame(){
        this.setPreferredSize(new Dimension(p.getWidth(),p.getHeight()));
        this.repaint();
    }//updateGame

    /* Title:paintComponent
     * Description: Used to paint images for the chess game
     * @param g: USed to create graphics on a component
     */
    public void paintComponent(Graphics g) {
        //Update graphics dimensions and store dimension values
        this.setPreferredSize(new Dimension(p.getWidth(),p.getHeight()));
        sizeX=this.getWidth();
        sizeY= this.getHeight();

        //Calls super of paintComponent
        super.paintComponent(g);

        //Loops are used to chess board
        for(int j=0; j<8; j++){ //Loops y values of board
            for (int i = 0; i < 8; i++) {  //Loops x values of board

                //If statement is used to create checkerboard colour pattern
                if(j%2==0) {
                    if ((i % 2 == 0)) {
                        g.setColor(new Color(107, 184, 255));
                    }
                    else {
                        g.setColor(new Color(25, 83, 138));
                    }
                }
                else{
                    if ((i % 2 == 0)) {
                        g.setColor(new Color(25, 83, 138));
                    }
                    else {
                        g.setColor(new Color(107, 184, 255));
                    }
                }

                //Paints checkerboard
                g.fillRect(sizeX * i / 9, sizeY * j / 8, sizeX / 9, sizeY / 8);
                g.setColor(Color.black);
                g.drawRect(sizeX * i / 9, sizeY * j / 8, sizeX / 9, sizeY / 8);
            }//loop
        }//loop

        //Draws all Chess pieces for the black team if they are alive
        if(!p.blackNull()&&p.isAlive(Color.black,0)){
            drawRook(g,p.getPieceX(Color.black,0),p.getPieceY(Color.black,0),Color.black);
        }
        if(!p.blackNull()&&p.isAlive(Color.black,1)){
            drawKnight(g,p.getPieceX(Color.black,1),p.getPieceY(Color.black,1),Color.black);
        }
        if(!p.blackNull()&&p.isAlive(Color.black,2)){
            drawBishop(g,p.getPieceX(Color.black,2),p.getPieceY(Color.black,2),Color.black);
        }
        if(!p.blackNull()&&p.isAlive(Color.black,3)){
            drawQueen(g,p.getPieceX(Color.black,3),p.getPieceY(Color.black,3),Color.black);
        }
        if(!p.blackNull()&&p.isAlive(Color.black,4)){
            drawKing(g,p.getPieceX(Color.black,4),p.getPieceY(Color.black,4),Color.black);
        }
        if(!p.blackNull()&&p.isAlive(Color.black,5)){
            drawBishop(g,p.getPieceX(Color.black,5),p.getPieceY(Color.black,5),Color.black);
        }
        if(!p.blackNull()&&p.isAlive(Color.black,6)){
            drawKnight(g,p.getPieceX(Color.black,6),p.getPieceY(Color.black,6),Color.black);
        }
        if(!p.blackNull()&&p.isAlive(Color.black,7)){
            drawRook(g,p.getPieceX(Color.black,7),p.getPieceY(Color.black,7),Color.black);
        }
        //Loop draws all pawns
        for(int i=8; i<16; i++){
            if(!p.blackNull()&&p.isAlive(Color.black,i)){
                if(p.getBlack(i).equals("pawn")) {
                    drawPawn(g, p.getPieceX(Color.black, i), p.getPieceY(Color.black, i), Color.black);
                }
                else if(p.getBlack(i).equals("queen")){
                    drawQueen(g, p.getPieceX(Color.black, i), p.getPieceY(Color.black, i), Color.black);
                }
                else if(p.getBlack(i).equals("bishop")){
                    drawBishop(g, p.getPieceX(Color.black, i), p.getPieceY(Color.black, i), Color.black);
                }
                else if(p.getBlack(i).equals("rook")){
                    drawRook(g, p.getPieceX(Color.black, i), p.getPieceY(Color.black, i), Color.black);
                }
                else if(p.getBlack(i).equals("knight")){
                    drawKnight(g, p.getPieceX(Color.black, i), p.getPieceY(Color.black, i), Color.black);
                }
            }
        }//loop


        //Draws all Chess pieces for the white team if they are alive
        if(!p.whiteNull()&&p.isAlive(Color.white,0)){
            drawRook(g,p.getPieceX(Color.white,0),p.getPieceY(Color.white,0),Color.white);
        }
        if(!p.whiteNull()&&p.isAlive(Color.white,1)){
            drawKnight(g,p.getPieceX(Color.white,1),p.getPieceY(Color.white,1),Color.white);
        }
        if(!p.whiteNull()&&p.isAlive(Color.white,2)){
            drawBishop(g,p.getPieceX(Color.white,2),p.getPieceY(Color.white,2),Color.white);
        }
        if(!p.whiteNull()&&p.isAlive(Color.white,3)){
            drawKing(g,p.getPieceX(Color.white,3),p.getPieceY(Color.white,3),Color.white);
        }
        if(!p.whiteNull()&&p.isAlive(Color.white,4)){
            drawQueen(g,p.getPieceX(Color.white,4),p.getPieceY(Color.white,4),Color.white);
        }
        if(!p.whiteNull()&&p.isAlive(Color.white,5)){
            drawBishop(g,p.getPieceX(Color.white,5),p.getPieceY(Color.white,5),Color.white);
        }
        if(!p.whiteNull()&&p.isAlive(Color.white,6)){
            drawKnight(g,p.getPieceX(Color.white,6),p.getPieceY(Color.white,6),Color.white);
        }
        if(!p.whiteNull()&&p.isAlive(Color.white,7)){
            drawRook(g,p.getPieceX(Color.white,7),p.getPieceY(Color.white,7),Color.white);
        }
        //Loop draws all pawns
        for(int i=8; i<16; i++){
            if(!p.whiteNull()&&p.isAlive(Color.white,i)){
                if(p.getWhite(i).equals("pawn")) {
                    drawPawn(g, p.getPieceX(Color.white, i), p.getPieceY(Color.white, i), Color.white);
                }
                else if(p.getWhite(i).equals("queen")){
                    drawQueen(g, p.getPieceX(Color.white, i), p.getPieceY(Color.white, i), Color.white);
                }
                else if(p.getWhite(i).equals("bishop")){
                    drawBishop(g, p.getPieceX(Color.white, i), p.getPieceY(Color.white, i), Color.white);
                }
                else if(p.getWhite(i).equals("rook")){
                    drawRook(g, p.getPieceX(Color.white, i), p.getPieceY(Color.white, i), Color.white);
                }
                else if(p.getWhite(i).equals("knight")){
                    drawKnight(g, p.getPieceX(Color.white, i), p.getPieceY(Color.white, i), Color.white);
                }
            }
        }//loop

        //Draws for loading a custom game
        if(p.getPhase()==-2){
            loadWhite(g);
        }
        else if(p.getPhase()==-3){
            loadBlack(g);
        }

        //Used for promotion
        if(p.prom()) {
            drawPromotion(g);
        }

        //Outputs win message
        if(p.whiteWin()){
            g.drawString("White Wins",25*sizeX/27,sizeY/16);
        }
        else if(p.blackWin()){
            g.drawString("Black Wins",25*sizeX/27,sizeY/16);
        }
        else if(p.stale()){
            g.drawString("Stalemate",25*sizeX/27,sizeY/16);
        }

    }//painComponent


    /* Title: drawBishop
     * Description: Method draws a bishop image
     * @param g: Used to draw bishop
     * @param x: X position of bishop
     * @param y: Y position of bishop
     * @param colour: Colour of bishop
     */
    private void drawBishop(Graphics g, int x, int y,Color colour){
        //Draws bishop
        //If statement determines colour of bishop drawn
        if(colour.equals(Color.black)){
            g.drawImage(new ImageIcon("Chess_bdt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
        else{
            g.drawImage(new ImageIcon("Chess_blt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
    }//drawBishop

    /* Title: drawKing
     * Description: Method draws a king image
     * @param g: Used to draw king
     * @param x: X position of king
     * @param y: Y position of king
     * @param colour: Colour of king
     */
    private void drawKing(Graphics g, int x, int y, Color colour){
        //Draws king
        //If statement determines colour of king drawn
        if(colour.equals(Color.black)){
            g.drawImage(new ImageIcon("Chess_kdt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
        else{
            g.drawImage(new ImageIcon("Chess_klt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
    }//drawKing

    /* Title: drawKnight
     * Description: Method draws a knight image
     * @param g: Used to draw knight
     * @param x: X position of knight
     * @param y: Y position of knight
     * @param colour: Colour of knight
     */
    private void drawKnight(Graphics g, int x, int y, Color colour){
        //Draws knight
        //If statement determines colour of knight drawn
        if(colour.equals(Color.black)){
            g.drawImage(new ImageIcon("Chess_ndt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
        else{
            g.drawImage(new ImageIcon("Chess_nlt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
    }//drawKnight

    /* Title: drawQueen
     * Description: Method draws a queen image
     * @param g: Used to draw queen
     * @param x: X position of queen
     * @param y: Y position of queen
     * @param colour: Colour of queen
     */
    private void drawQueen(Graphics g, int x , int y, Color colour){
        //Draws queen
        //If statement determines colour of queen drawn
        if(colour.equals(Color.black)){
            g.drawImage(new ImageIcon("Chess_qdt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
        else{
            g.drawImage(new ImageIcon("Chess_qlt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
    }//drawQueen

    /* Title: drawRook
     * Description: Method draws a rook image
     * @param g: Used to draw rook
     * @param x: X position of rook
     * @param y: Y position of rook
     * @param colour: Colour of rook
     */
    private void drawRook(Graphics g, int x, int y, Color colour){
        //Draws rook
        //If statement determines colour of rook drawn
        if(colour.equals(Color.black)){
            g.drawImage(new ImageIcon("Chess_rdt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
        else{
            g.drawImage(new ImageIcon("Chess_rlt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
    }//drawRook

    /* Title: drawPawn
     * Description: Method draws a pawn image
     * @param g: Used to draw pawn
     * @param x: X position of pawn
     * @param y: Y position of pawn
     * @param colour: Colour of pawn
     */
    private void drawPawn(Graphics g, int x, int y, Color colour){
        //Draws pawn
        //If statement determines colour of pawn drawn
        if(colour.equals(Color.black)){
            g.drawImage(new ImageIcon("Chess_pdt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
        else{
            g.drawImage(new ImageIcon("Chess_plt60.png").getImage(),x*sizeX/9,y*sizeY/8,sizeX/9,sizeY/8,null);
        }
    }//drawPawn

    /* Title: drawPromotion
     * Description: Method draws a promotion menu
     * @param g: Used to draw promotion
     */
    private void drawPromotion(Graphics g){
        System.out.println(p.getPhase());
        if(p.getPhase()==3){
            g.drawString("White Promote",25*sizeX/27,sizeY/16);
            drawQueen(g,8,1,Color.white);
            drawRook(g,8,2,Color.white);
            drawBishop(g,8,3,Color.white);
            drawKnight(g,8,4,Color.white);
        }
        else{
            g.drawString("Black Promote",25*sizeX/27,sizeY/16);
            drawQueen(g,8,1,Color.black);
            drawRook(g,8,2,Color.black);
            drawBishop(g,8,3,Color.black);
            drawKnight(g,8,4,Color.black);
        }
    }//drawPromotion

    /* Title: loadWhite
     * Description: Method draws a white load menu
     * @param g: Used to draw promotion
     */
    private void loadWhite(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(sizeX * 8 / 9, sizeY * p.select() / 8, sizeX / 9, sizeY / 8);
        drawKing(g,8,0,Color.white);
        drawQueen(g,8,1,Color.white);
        drawRook(g,8,2,Color.white);
        drawBishop(g,8,3,Color.white);
        drawKnight(g,8,4,Color.white);
        drawPawn(g,8,5,Color.white);

        //Only continues if king has been placed
        if(p.getPhase()==-2&&p.isAlive(Color.white,3)){
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(sizeX * 8 / 9, sizeY * 7/ 8, sizeX / 9, sizeY / 8);
            g.setColor(Color.black);

            g.drawString("Set Black Team",25*sizeX/27,15*sizeY/16);
        }
    }//loadWhite

    /* Title: loadWhite
     * Description: Method draws a white load menu
     * @param g: Used to draw promotion
     */
    private void loadBlack(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(sizeX * 8 / 9, sizeY * p.select() / 8, sizeX / 9, sizeY / 8);
        drawKing(g,8,0,Color.black);
        drawQueen(g,8,1,Color.black);
        drawRook(g,8,2,Color.black);
        drawBishop(g,8,3,Color.black);
        drawKnight(g,8,4,Color.black);
        drawPawn(g,8,5,Color.black);

        //Only continues if king has been placed
        if(p.getPhase()==-3&&p.isAlive(Color.black,4)){
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(sizeX * 8 / 9, sizeY * 7/ 8, sizeX / 9, sizeY / 8);
            g.setColor(Color.black);
            g.drawString("Start Game",25*sizeX/27,15*sizeY/16);
        }
    }//loadBlack
}//Game
