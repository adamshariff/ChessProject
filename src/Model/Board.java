package Model;//package line

//Import
import java.awt.Color;
import java.util.Arrays;

/* Title: Board
 * Version: 1.0
 * Author: Adam Shariff
 * Last Modified: 19/12/2022
 * Description: Creates char board version of chessboard
 */
class Board {

    //Instance variables
    private char[][]board; //Contains game board
    private Model m;       //Holds model

    //@Constructor: Creates board
    public Board(Model m){
        this.m=m;
        if(m.getPhase()!=-2) {
            fillBoard();
        }
    }//constructor

    //Title: fillBoard
    //Description: Creates and populates board
    private void fillBoard(){
        //Instantiates board
        board= new char[8][8];

        //Populates board with black spaces
        for(int j=0; j<8; j++){
            for(int i=0; i<8; i++){
                board[j][i]='x';
            }//loop
        }//loop
        addTeams();
        displayBoard();
    }//fillBoard

    //Title: addTeams
    //Description: Adds teams to the board
    private void addTeams(){
        //Add pieces to board
        if(m.isAlive(Color.black,0)) {
            board[m.getPieceY(Color.black, 0)][m.getPieceX(Color.black, 0)] = 'r';
        }
        if(m.isAlive(Color.black,1)) {
            board[m.getPieceY(Color.black, 1)][m.getPieceX(Color.black, 1)] = 'h';
        }
        if(m.isAlive(Color.black,2)) {
            board[m.getPieceY(Color.black, 2)][m.getPieceX(Color.black, 2)] = 'b';
        }
        if(m.isAlive(Color.black,3)) {
            board[m.getPieceY(Color.black, 3)][m.getPieceX(Color.black, 3)] = 'q';
        }
        if(m.isAlive(Color.black,4)) {
            board[m.getPieceY(Color.black, 4)][m.getPieceX(Color.black, 4)] = 'k';
        }
        if(m.isAlive(Color.black,5)) {
            board[m.getPieceY(Color.black, 5)][m.getPieceX(Color.black, 5)] = 'b';
        }
        if(m.isAlive(Color.black,6)) {
            board[m.getPieceY(Color.black, 6)][m.getPieceX(Color.black, 6)] = 'h';
        }
        if(m.isAlive(Color.black,7)) {
            board[m.getPieceY(Color.black, 7)][m.getPieceX(Color.black, 7)] = 'r';
        }

        if(m.isAlive(Color.white,0)) {
            board[m.getPieceY(Color.white, 0)][m.getPieceX(Color.white, 0)] = 'R';
        }
        if(m.isAlive(Color.white,1)) {
            board[m.getPieceY(Color.white, 1)][m.getPieceX(Color.white, 1)] = 'H';
        }
        if(m.isAlive(Color.white,2)) {
            board[m.getPieceY(Color.white, 2)][m.getPieceX(Color.white, 2)] = 'B';
        }
        if(m.isAlive(Color.white,3)) {
            board[m.getPieceY(Color.white, 3)][m.getPieceX(Color.white, 3)] = 'K';
        }
        if(m.isAlive(Color.white,4)) {
            board[m.getPieceY(Color.white, 4)][m.getPieceX(Color.white, 4)] = 'Q';
        }
        if(m.isAlive(Color.white,5)) {
            board[m.getPieceY(Color.white, 5)][m.getPieceX(Color.white, 5)] = 'B';
        }
        if(m.isAlive(Color.white,6)) {
            board[m.getPieceY(Color.white, 6)][m.getPieceX(Color.white, 6)] = 'H';
        }
        if(m.isAlive(Color.white,7)) {
            board[m.getPieceY(Color.white, 7)][m.getPieceX(Color.white, 7)] = 'R';
        }

        //Loop to add pawns to board
        for(int i=8; i<16; i++){
            if(m.isAlive(Color.black,i)) {
                if(m.blackName(i).equals("queen")) {
                    board[m.getPieceY(Color.black, i)][m.getPieceX(Color.black, i)] = 'q';
                }
                else if(m.blackName(i).equals("rook")) {
                    board[m.getPieceY(Color.black, i)][m.getPieceX(Color.black, i)] = 'r';
                }
                else if(m.blackName(i).equals("knight")) {
                    board[m.getPieceY(Color.black, i)][m.getPieceX(Color.black, i)] = 'h';
                }
                else if(m.blackName(i).equals("bishop")) {
                    board[m.getPieceY(Color.black, i)][m.getPieceX(Color.black, i)] = 'b';
                }
                else if(m.blackName(i).equals("pawn")) {
                    board[m.getPieceY(Color.black, i)][m.getPieceX(Color.black, i)] = 'p';
                }
            }
            if(m.isAlive(Color.white,i)) {
                if(m.whiteName(i).equals("queen")) {
                    board[m.getPieceY(Color.white, i)][m.getPieceX(Color.white, i)] = 'Q';
                }
                else if(m.whiteName(i).equals("rook")) {
                    board[m.getPieceY(Color.white, i)][m.getPieceX(Color.white, i)] = 'R';
                }
                else if(m.whiteName(i).equals("knight")) {
                    board[m.getPieceY(Color.white, i)][m.getPieceX(Color.white, i)] = 'H';
                }
                else if(m.whiteName(i).equals("bishop")) {
                    board[m.getPieceY(Color.white, i)][m.getPieceX(Color.white, i)] = 'B';
                }
                else if(m.whiteName(i).equals("pawn")) {
                    board[m.getPieceY(Color.white, i)][m.getPieceX(Color.white, i)] = 'P';
                }
            }
        }//loop
    }//addTeams

    //used for testing
    private void displayBoard(){
        for(int j=0; j<8; j++){
            for(int i=0; i<8; i++){
                System.out.print(board[j][i]);
            }//loop
            System.out.println("");
        }//loop
    }

    /* Title: updateBoard
     * Description: Updates game board
     * @param x: x value of position changed
     * @param y: y value of position changed
     */
    public void updateBoard(int x, int y){
        board[y][x]='x';
        addTeams();
        displayBoard();
    }//updateBoard

    /* Title: swap
     * Description: Swaps two positions on the board then updates board
     * @param x; Object 1 x position
     * @param y: Object 1 y position
     * @param x2: Object 2 x position
     * @param y2: Object 2 y position
     */
    public void swap(int x, int y, int x2, int y2){
        //Swap positions on board
        char t=board[y][x];
        board[y][x]=board[y2][x2];
        board[y2][x2]=t;

        //Updates board
        addTeams();
        displayBoard();
    }//swap

    /* Title getSpace
     * Description: Returns what is at a given position on the board
     * @param x: X position on board
     * @param y: Y position on board
     * @return: Returns a char of what is at a current space
     */
    public char getSpace(int x, int y){
        return board[y][x];
    }//getSpace

    //Title: update
    //Description: Updates board
    public void update(){
        addTeams();
        displayBoard();
    }//update

    /* Title: copy
     * Description: Copies character board
     * @return: Returns character board
     */
    public char[][] copy(){
        Arrays.toString(board[0]);
        char [][]board= new char[8][8];
        for(int j=0; j<8; j++){
            for(int i=0; i<8; i++){
                board[j][i]=this.board[j][i];
            }//loop
        }//loop
        return board;
    }//copy
}//Board
