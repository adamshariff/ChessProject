package Model;//package line

//Import
import java.awt.*;//Used to hold colour status

/* Title: ChessPiece
 * Version: 1.0
 * Author: Adam Shariff
 * Last Modified: 19/12/2022
 * Description: This class represents a chess piece. It contains its status, name/type, and position.
 */
class ChessPiece {

    //Instance variables
    private String name;              //Holds name/type of piece
    private final Color colour;       //Holds the colour of the piece
    private int x;                    //Holds x position of piece
    private int y;                    //Holds y position of piece
    private boolean alive;            //Holds weather piece is alive or dead
    private boolean moved= false;     //States if piece has moved
    private boolean enPassant =false; //Holds weather en passant is possible

    /* @Constructor: Initializes variables
     * @param name: Holds name/type of piece
     * @param colour: Holds the colour of the piece
     * @param x: Holds starting x
     * @param y: Holds starting y
     */
    public ChessPiece(String name, Color colour, int x, int y){
        //Initializes variables
        this.name=name;
        this.colour=colour;
        this.x=x;
        this.y=y;
        alive=true;
    }//Constructor

    /* Title: getColour
     * Description: Returns colour of chess piece
     * @return: Returns colour of chess piece as a Color
     */
    public Color getColour(){
        return colour;
    }//gerColour

    /* Title: getName
     * Description: Returns name of chess piece
     * @return: Returns name of chess piece as a string
     */
    public String getName(){
        return name;
    }//getName

    /* Title: setName
     * Description: Changes name of piece if piece is a pawn
     * @param name: Holds new name of piece
     */
    public void setName(String name){
        if(this.name.equals("pawn")){
           this.name=name;
        }
    }//getName

    /* Title: getX
     * Description: Returns x position of chess piece
     * @return: Returns x position of chess piece as an int
     */
    public int getX(){
        return x;
    }//getX

    /* Title: getY
     * Description: Returns y position of chess piece
     * @return: Returns y position of chess piece as an int
     */
    public int getY(){
        return y;
    }//getY

    /* Title: setNewPos
     * Description: Sets a new valid position of the chess piece
     * @param x: Holds new x position
     * @param y: Holds new y position
     */
    public void setNewPos(int x,int y){
        //Checks if new position is valid
        if((x>=0&&x<8)&&(y>=0&&y<8)) {
            //Sets valid position
            this.x = x;
            this.y = y;
            moved=true;
        }
        else{
            //Outputs that position is invalid
            System.out.println("Position "+x+","+y+" is out of bounds");
        }
    }//setNewPos

    /* Title: isAlive
     * Description: Returns weather the piece is alive
     * @return: Returns status of piece as boolean
     */
    public boolean isAlive(){
        return alive;
    }//isAlive

    //Title: died
    //Description: Sets the status of the piece to dead
    public void died(){
        alive=false;
    }//died

    //Title: moved
    //Description: Returns if piece has moved
    public boolean moved(){
        return moved;
    }//moved
    
    public void setEnPassant(){
        enPassant =true;
    }

    //Title:resetEnPassant
    //Description: Resets en passant variable
    public void resetEnPassant(){
        enPassant =false;
    }//resetEnPassant

    /* Title: getEnPassant
     * Description: Returns if piece can en passant
     * @return: Returns boolean if en passant
     */
    public boolean getEnPassant(){
        return enPassant;
    }//getEnPassant


    //Title:changeName
    //Description: Allows changing of piece name
    public void changeName(String name){
        this.name=name;
    }//changName

    //Title:alive
    //Description: Puts piece alive again
    public void alive(){
        alive=true;
    }//alive

    //Title:resetMoved
    //Description: Resets if piece has moved
    public void resetMoved(){
        moved=false;
    }//resetMoved

    /* Title: copy
     * Description: Returns copy of piece
     * @return: Returns copy of piece
     */
    public ChessPiece copy(){
        ChessPiece p= new ChessPiece(name,colour,x,y);
        p.alive=alive;
        p.moved=moved;
        p.enPassant=enPassant;
        return p;
    }//copy
}//ChessPiece
