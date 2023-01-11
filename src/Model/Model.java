package Model;//package line

//Imports
import Graphics.Gui;   //Gui is used to interface with
import java.awt.Color; //Used to represent colours

/* Title: Model
 * Version: 1.0
 * Author: Adam Shariff
 * Last Modified: 19/12/2022
 * Description: This class represents the brain of the actual chess game. It is meant to allow interaction between the graphics
 * package and Model classes and objects. It also tracks what state the program is in i.e. game state or menu state.
 */
public class Model {

    //Instance variables
    private int phase;   //Holds what phase the program is currently in
    private Gui gui;     //Allows interaction with the graphics package
    private Team black;  //Creates the black team
    private Team white;  //Creates the white team
    private Board board; //Creates a game board
    private ChessPiece piece; //Holds piece to be moved
    private ChessPiece piece2; //Used to hold second chess piece for castling
    private boolean promotion=false; //Holds weather there is promotion
    private ChessPiece pro;          //holds promotion piece
    private boolean whiteWin=false;  //Holds weather white won
    private boolean blackWin=false;  //Holds weather black won
    private boolean stale=false;     //Holds weather nobody won
    public int select=0;             //Holds selected piece for loading
    public boolean aiWhite =false;   //Holds if AI is white
    public boolean aiBlack=false;    //Holds if AI is black
    public ChessAI chessAI;          //Holds chess AI

    //@Constructor: Initiates phase
    public Model(){
        //Set program to menu phase
        phase=0;

    }//Constructor

    /* Title: setGui
     * Description: This method sets the gui to interact with graphics
     * @param gui: Holds the graphics package to interface
     */
    public void setGui(Gui gui){
        this.gui=gui;
    }//setGui

    /* Title: setPhase
     * Description: Sets the current phase the program is in
     * @param p: Holds new phase
     */
    public void setPhase(int p){
        //Sets new phase
        phase=p;

        //Validates new phase and performs actions based of phase
        if(phase==0){      //Menu phase
            System.out.println("Not yet implemented");
        }
        else if(phase==1){ //Game phase
            //Create teams and board
            black= new Team(Color.black);
            black.createTeam();
            white= new Team(Color.white);
            white.createTeam();
            board = new Board(this);
            gui.toGame();
        }
        else if(phase==2){
            piece=null;
            System.out.println("White turn");
            white.resetPassant();
            if(aiWhite ==true){
                chessAI.makeMove(board.copy(),black.copy(),white.copy());
            }
        }
        else if(phase==3){
            piece=null;
            System.out.println("Black turn");
            black.resetPassant();
            if(aiBlack ==true){
                chessAI.makeMove(board.copy(),black.copy(),white.copy());
            }
        }
        else if(phase==4){

        }
        else if(phase==-1){
            gui.toLoad();
        }
        else if(phase==-2){
            white= new Team(Color.white);
            white.createBlank();
        }
        else if(phase==-3){
            black= new Team(Color.black);
            black.createBlank();
            gui.updateGui();
        }
        else if(phase==-4){
            gui.menu5();
            gui.updateUI();
        }
        else if(phase==-5){
            gui.menu3();
            gui.updateUI();
        }
        else if(phase==-6){
            gui.menu4();
            gui.updateUI();
        }
        else if(phase==-7){
            gui.menu2();
            gui.updateUI();
        }
        else if(phase==-8){
            gui.menu6();
            gui.updateUI();
        }
        else{              //Phase is invalid
           System.out.println("Invalid phase");
        }
    }//setPhase


    public int getPhase(){
        return phase;
    }

    /* Title: isAlive
     * Description: Returns if a piece from a particular team is alive
     * @param colour: Holds colour of team that the piece is from
     * @param i: Contains which piece is being checked
     * @return: Returns a boolean weather a piece is alive or not from a particular team
     */
    public boolean isAlive(Color colour, int i){
        //Returns alive status of a piece from black team
        if(colour.equals(Color.black)){
            return black.isAlive(i);
        }
        //Returns alive status of a piece from white team
        return white.isAlive(i);
    }//isAlive

    /* Title: getPieceX
     * Description: Gets x position of a piece from a particular team is alive
     * @param colour: Holds colour of team that the piece is from
     * @param i: Contains which piece is being checked
     * @return: Returns an integer of the x position of a particular piece from either the white or black team
     */
    public int getPieceX(Color colour, int i){
        //Returns x position of a piece from black team
        if(colour.equals(Color.black)){
            return black.getPieceX(i);
        }
        //Returns x position of a piece from white team
        return white.getPieceX(i);
    }//getPieceX

    /* Title: getPieceY
     * Description: Gets y position of a piece from a particular team is alive
     * @param colour: Holds colour of team that the piece is from
     * @param i: Contains which piece is being checked
     * @return: Returns an integer of the y position of a particular piece from either the white or black team
     */
    public int getPieceY(Color colour, int i){
        //Returns y position of a piece from black team
        if(colour.equals(Color.black)){
            return black.getPieceY(i);
        }
        //Returns x position of a piece from white team
        return white.getPieceY(i);
    }//getPieceY

    //Title: reset
    //Description: Used to reset the game
    public void reset(){
        white = new Team(Color.white);
        black = new Team(Color.black);
    }//reset

    /* Title: getPiece
     * Description: Returns what is at a space on the board from given position
     * @param x: X position on the board
     * @param y: Y position on the board
     * @return: Returns char of what is at the position
     */
    public char getSpace(int x, int y){
        return board.getSpace(x,y);
    }//getPiece

    /* Title: setPiece
     * Description: Sets the current piece being selected
     * @param x: x position of piece to be selected
     * @param y: y position of piece to be selected
     */
    public void setPiece(int x, int y){
        //Local variable
        ChessPiece p; //Temp chess piece

        //Adds piece based on turn
        if(phase==2){ //White turn
            //Gets piece from team. If piece does not exist at xy not added
            p=white.getPiece(x,y);
            if(p!=null){
                piece=p;
            }
        }
        if(phase==3){ //Black turn
            //Gets piece from team. If piece does not exist at xy not added
            p=black.getPiece(x,y);
            if(p!=null){
                piece=p;
            }
        }
    }//setPiece

    /* Title: setPiece2
     * Description: Sets piece for castling
     * @param x: x position of castle piece to be selected
     * @param y: y position of castle piece to be selected
     */
    public void setPiece2(int x, int y){
        //Local variable
        ChessPiece p; //Temp chess piece

        //Adds piece based on turn
        if(phase==2){ //White turn
            p=white.getPiece(x,y);
            if(p!=null){
                piece2=p;
            }
        }
        if(phase==3){ //Black turn
            //Gets piece from team. If piece does not exist at xy not added
            p=black.getPiece(x,y);
            if(p!=null){
                piece2=p;
            }
        }
    }//setPiece2

    /* Title: getPiece
     * Description: Returns current chess piece being looked at
     * @return: Returns a chess piece
     */
    public ChessPiece getPiece(){
        return piece;
    }//getPiece

    /* Title: movePiece
     * Description: Moves piece to new position and update board and graphics. Does not do castling
     * @param x: New x position
     * @param y: New y position
     */
    public void movePiece(int x, int y){
        //Moves pieces
        int px,py;
        px=piece.getX();
        py=piece.getY();
        piece.setNewPos(x,y);

        //Update board and graphics
        board.updateBoard(px,py);
        gui.updateGui();
    }//movePiece

    /* Title: canMove
     * Description: Checks is piece can move
     * @param x: New x position
     * @param y: New y position
     * @returns if it can move
     */
    public boolean canMove(int x, int y){
        if(piece.getName().equals("pawn")){
            return movePawn(x,y);
        }
        else if(piece.getName().equals("bishop")){
            return moveBishop(x,y);
        }
        else if(piece.getName().equals("knight")){
            return moveKnight(x,y);
        }
        else if(piece.getName().equals("rook")){
            return moveRook(x,y);
        }
        else if(piece.getName().equals("queen")){
            return moveQueen(x,y);
        }
        else if(piece.getName().equals("king")){
            return moveKing(x,y);
        }
        return false;
    }

    /* Title: movePawn
     * Description: Check if pawn can move
     * @param x: New x position
     * @param y: New y position
     * @return: returns if pawn can move
     */
    private boolean movePawn(int x, int y){
        ChessPiece p;
        char[][]b= board.copy();
        if(piece.getColour().equals(Color.white)){
            if((x==piece.getX()&&y==piece.getY()-1&&board.getSpace(x,y)=='x')||(x==piece.getX()&&y==piece.getY()-2&&board.getSpace(x,y)=='x'&& !piece.moved()&&board.getSpace(x,y+1)=='x')
                    ||((x==piece.getX()-1&&y==piece.getY()-1&&board.getSpace(x,y)!='x'||x==piece.getX()+1&&y==piece.getY()-1&&board.getSpace(x,y)!='x') &&Character.isLowerCase(board.getSpace(x,y)))
                    ||board.getSpace(x,y+1)=='p'){
                if(y==piece.getY()-2){
                    piece.setEnPassant();
                }
                p=black.getPiece(x,y+1);
                if(p!=null&&board.getSpace(x,y)=='x') {
                    if (board.getSpace(x, y + 1) == 'p' && !p.getEnPassant()) {
                        return false;
                    }
                }
                b[piece.getY()][piece.getX()]='x';
                b[y][x]='P';
                if(Condition.check(Color.white,getPieceX(Color.white,3),getPieceY(Color.white,3),b)){
                    return false;
                }
                return true;
            }
        }
        else if(piece.getColour().equals(Color.black)){
            if((x==piece.getX()&&y==piece.getY()+1&&board.getSpace(x,y)=='x')||(x==piece.getX()&&y==piece.getY()+2&&board.getSpace(x,y)=='x'&& !piece.moved()&&board.getSpace(x,y-1)=='x')
                    ||((x==piece.getX()-1&&y==piece.getY()+1&&board.getSpace(x,y)!='x'||x==piece.getX()+1&&y==piece.getY()+1&&board.getSpace(x,y)!='x') &&Character.isUpperCase(board.getSpace(x,y)))
                    ||board.getSpace(x,y-1)=='P'){
                if(y==piece.getY()+2){
                    piece.setEnPassant();
                }
                p=white.getPiece(x,y-1);
                if(p!=null&&board.getSpace(x,y)=='x') {
                    if (board.getSpace(x, y - 1) == 'P' && !p.getEnPassant()) {
                        return false;
                    }
                }
                b[piece.getY()][piece.getX()]='x';
                b[y][x]='p';
                if(Condition.check(Color.black,getPieceX(Color.black,4),getPieceY(Color.black,4),b)){
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /* Title: moveBishop
     * Description: Check if bishop can move
     * @param x: New x position
     * @param y: New y position
     * @return: returns if bishop can move
     */
    private boolean moveBishop(int x, int y){
        if(y-piece.getY()==x-piece.getX()||y-piece.getY()==(x-piece.getX())*-1){
            if(diagonalCheck(x,y)) {
                return true;
            }
        }

        return false;
    }

    /* Title: diagonalCheck
     * Description: Check if diagonal can move
     * @param x: New x position
     * @param y: New y position
     * @return: returns if diagonal can move
     */
    private boolean diagonalCheck(int x, int y){
        char[][]b= board.copy();
        if(y-piece.getY()<0&&x-piece.getX()<0){
            for(int i=1; i+x<piece.getX(); i++){
                if(board.getSpace(x+i,y+i)!='x'){
                    return false;
                }
            }
        }
        else if(y-piece.getY()>0&&x-piece.getX()>0){
            for(int i=1; x-i>piece.getX(); i++){
                if(board.getSpace(x-i,y-i)!='x'){
                    return false;
                }
            }
        }
        else if(y-piece.getY()<0&&x-piece.getX()>0){
            for(int i=1; x-i>piece.getX(); i++){
                if(board.getSpace(x-i,y+i)!='x'){
                    return false;
                }
            }
        }
        else if(y-piece.getY()>0&&x-piece.getX()<0){
            for(int i=1; i+x<piece.getX(); i++){
                if(board.getSpace(x+i,y-i)!='x'){
                    return false;
                }
            }
        }
        b[piece.getY()][piece.getX()]='x';
        if(piece.getColour().equals(Color.white)){
            b[y][x]='B';
            if(Condition.check(Color.white,getPieceX(Color.white,3),getPieceY(Color.white,3),b)){
                return false;
            }
        }
        else{
            b[y][x]='b';
            if(Condition.check(Color.black,getPieceX(Color.black,4),getPieceY(Color.black,4),b)){
                return false;
            }
        }
        return true;
    }

    /* Title: moveKnight
     * Description: Check if knight can move
     * @param x:  x position
     * @param y:  y position
     * @return: returns if knight can move
     */
    private boolean moveKnight(int x, int y){
        char[][]b= board.copy();
        if(((y-piece.getY()==-2||y-piece.getY()==2)&&(x-piece.getX()==1||x-piece.getX()==-1))||((y-piece.getY()==-1||y-piece.getY()==1)&&(x-piece.getX()==2||x-piece.getX()==-2))){
           b[piece.getY()][piece.getX()]='x';
            if(piece.getColour().equals(Color.white)) {
                b[y][x]='H';
                if (Condition.check(piece.getColour(), getPieceX(Color.white,3), getPieceY(Color.white,3), b)) {
                    return false;
                }
            }
            else if(piece.getColour().equals(Color.black)) {
                b[y][x]='h';
                if (Condition.check(piece.getColour(), getPieceX(Color.black,4), getPieceY(Color.black,4), b)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* Title: moveRook
     * Description: Check if rook can move
     * @param x:  x position
     * @param y:  y position
     * @return: returns if rook can move
     */
    private boolean moveRook(int x, int y){
        if((y!=piece.getY()&&x==piece.getX())||y==piece.getY()&&x!=piece.getX()){
            if(crossCheck(x,y)) {
                return true;
            }
        }
        return false;
    }

    /* Title: crossCheck
     * Description: Check if can move hori or verti
     * @param x:  x position
     * @param y:  y position
     * @return: returns if it can move
     */
    private boolean crossCheck(int x, int y){
        char[][]b= board.copy();
        if(y!=piece.getY()&&x==piece.getX()&&y-piece.getY()<0){
            for(int i=1; i+y<piece.getY(); i++){
                if(board.getSpace(x,y+i)!='x'){
                    return false;
                }
            }
        }
        else if(y!=piece.getY()&&x==piece.getX()&&y-piece.getY()>0){
            for(int i=1; y-i>piece.getY(); i++){
                if(board.getSpace(x,y-i)!='x'){
                    return false;
                }
            }
        }
        else if(x!=piece.getX()&&y==piece.getY()&&x-piece.getX()>0){
            for(int i=1; x-i>piece.getX(); i++){
                if(board.getSpace(x-i,y)!='x'){
                    return false;
                }
            }
        }
        else if(x!=piece.getX()&&y==piece.getY()&&x-piece.getX()<0){
            for(int i=1; x+i<piece.getX(); i++){
                if(board.getSpace(x+i,y)!='x'){
                    return false;
                }
            }
        }
        b[piece.getY()][piece.getX()]='x';
        if(piece.getColour().equals(Color.white)){
            b[y][x]='B';
            if(Condition.check(Color.white,getPieceX(Color.white,3),getPieceY(Color.white,3),b)){
                return false;
            }
        }
        else{
            b[y][x]='b';
            if(Condition.check(Color.black,getPieceX(Color.black,4),getPieceY(Color.black,4),b)){
                return false;
            }
        }
        return true;
    }

    /* Title: moveQueen
     * Description: Check if Queen can move
     * @param x:  x position
     * @param y:  y position
     * @return: returns if Queen can move
     */
    private boolean moveQueen(int x, int y){
        if(y-piece.getY()==x-piece.getX()||y-piece.getY()==(x-piece.getX())*-1){
            if(diagonalCheck(x,y)){
                return true;
            }
        }
        else if((y!=piece.getY()&&x==piece.getX())||y==piece.getY()&&x!=piece.getX()){
            if(crossCheck(x,y)){
                return true;
            }
        }
        return false;
    }

    /* Title: moveKing
     * Description: Check if king can move
     * @param x:  x position
     * @param y:  y position
     * @return: returns if king can move
     */
    private boolean moveKing(int x,int y){
        char[][]b= board.copy();
        if(piece.getColour().equals(Color.white)){
            if(Character.isUpperCase(board.getSpace(x,y))){
                return false;
            }
            b[y][x]='K';
        }
        else{
            if(Character.isLowerCase(board.getSpace(x,y))&&board.getSpace(x,y)!='x'){
                return false;
            }
            b[y][x]='k';
        }
        b[piece.getY()][piece.getX()]='x';
        if(Condition.check(piece.getColour(),x,y,b)){
            return false;
        }
        return true;
    }

    /* Title: capture
     * Description: Check to capture
     * @param x:  x position
     * @param y:  y position
     */
    public void capture(int x, int y){
        ChessPiece p;
        if(phase==2) {
            if (board.getSpace(x, y) != 'x') {
                p= black.getPiece(x,y);
                p.died();
                return;
            }
            p= black.getPiece(x,y+1);
            if(piece.getName().equals("pawn")&& p!=null){
                if(p.getName().equals("pawn")&&p.getEnPassant()) {
                    System.out.println(p.getName());
                    p.died();
                }
            }
        }
        else if(phase==3) {
            if (board.getSpace(x, y) != 'x') {
                p= white.getPiece(x,y);
                p.died();
                return;
            }
            p= white.getPiece(x,y-1);
            if(piece.getName().equals("pawn")&& p!=null){
                if(p.getName().equals("pawn")&&p.getEnPassant()) {
                    System.out.println(p.getName());
                    p.died();
                }
            }
        }
    }

    /* Title: canCastle
     * Description: Check if can castle
     * @param x:  x position
     * @param y:  y position
     * @return: returns if pawn can castle
     */
    public boolean canCastle( int ox, int oy){
        setPiece2(ox,oy);
        char[][]b=board.copy();
        char t;
        if(piece.getColour().equals(Color.white)){
            if(!piece.moved()&& !piece2.moved()){
                if((piece2.getX()>piece.getX()&&piece.getName().equals("king"))||(piece.getX()>piece2.getX()&&piece2.getName().equals("king"))){
                    if(getSpace(5,7)=='x'&&getSpace(6,7)=='x'){
                        t=b[7][4];
                        b[7][4]=b[7][7];
                        b[7][7]=t;
                        if(Condition.check(Color.white,3,7,b)){
                            return false;
                        }
                        return true;
                    }
                }
                else{
                    if(getSpace(1,7)=='x'&&getSpace(2,7)=='x'&&getSpace(3,7)=='x'){
                        t=b[7][4];
                        b[7][4]=b[7][0];
                        b[7][0]=t;
                        if(Condition.check(Color.white,3,7,b)){
                            return false;
                        }
                        return true;
                    }
                }
            }
        }
        else if(piece.getColour().equals(Color.black)){
            if(!piece.moved()&& !piece2.moved()){
                if((piece2.getX()>piece.getX()&&piece.getName().equals("king"))||(piece.getX()>piece2.getX()&&piece2.getName().equals("king"))){
                    if(getSpace(5,0)=='x'&&getSpace(6,0)=='x'){
                        t=b[0][4];
                        b[0][4]=b[0][7];
                        b[0][7]=t;
                        if(Condition.check(Color.white,0,4,b)){
                            return false;
                        }
                        return true;
                    }
                }
                else{
                    if(getSpace(1,0)=='x'&&getSpace(2,0)=='x'&&getSpace(3,0)=='x'){
                        t = b[0][4];
                        b[0][4] = b[0][0];
                        b[0][0] = t;
                        if (Condition.check(Color.white, 0, 4, b)) {
                            return false;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* Title: castle
     * Description: Performs castle
     */
    public void castle(){
        int x,y;
        x=piece.getX();
        y=piece.getY();
        piece.setNewPos(piece2.getX(),piece2.getY());
        piece2.setNewPos(x,y);
        board.swap(piece.getX(),piece.getY(),piece2.getX(),piece2.getY());
        gui.updateGui();
    }

    /* Title: castle
     * Description: Performs promotion
     */
    public void promotion(){
        if(piece.getName().equals("pawn")&&piece.getColour().equals(Color.white)&&piece.getY()==0){
            promotion=true;
            pro=piece;

        }
        else if(piece.getName().equals("pawn")&&piece.getColour().equals(Color.black)&&piece.getY()==7){
            promotion=true;
            pro=piece;
        }
    }//promotion

    /* Title: getPromotion
     * Description: Returns if promotion
     * @return: Returns promotion
     */
    public boolean getPromotion(){
        return promotion;
    }

    /* Title: promote
     * Description: Sets promotion piece
     */
    public void promote(char q){
        if(q=='q'){
            pro.setName("queen");
            board.update();
        }
        if(q=='r'){
            pro.setName("rook");
            board.update();
        }
        if(q=='k'){
            pro.setName("knight");
            board.update();
        }
        if(q=='b'){
            pro.setName("bishop");
            board.update();
        }
        promotion=false;
        gui.updateGui();
    }

    /* Title: whiteName
     * Description: Returns white piece name
     */
    public String whiteName(int i){
        return white.getPiece(i).getName();
    }

    /* Title: blackName
     * Description: Returns black piece name
     */
    public String blackName(int i){
        return black.getPiece(i).getName();
    }

    /* Title: copy
     * Description: Returns copy of Board
     */
    public char[][] copy(){
        return board.copy();
    }

    /* Title: getWhite
     * Description: Returns white team
     */
    public Team getWhite(){
        return white;
    }

    /* Title: getBlack
     * Description: Returns black team
     */
    public Team getBlack(){
        return black;
    }

    /* Title: loadsPiece
     * Description: Loads piece from loading
     */
    public void loadPiece(int x, int y, String p){
        if(phase==-2){
            if(p.equals("king")){
                if(white.getPiece(x,y)!=null&&white.getPiece(x,y).getName().equals(p)){
                    white.killPiece(x,y);
                }
                else if(white.getPiece(x,y)==null){
                    white.addPiece(x, y, 3);
                }
                gui.updateGui();
            }
            else if(p.equals("pawn")){
                if(white.getPiece(x,y)!=null&&white.getPiece(x,y).getName().equals(p)){
                    white.killPiece(x,y);
                }
                else if(white.getPiece(x,y)==null){
                    white.addPiece(x, y, 8);
                }
                gui.updateGui();
            }
            else if(p.equals("bishop")){
                if(white.getPiece(x,y)!=null&&white.getPiece(x,y).getName().equals(p)){
                    white.killPiece(x,y);
                }
                else if(white.getPiece(x,y)==null){
                    white.addPiece(x, y, 2);
                }
                gui.updateGui();
            }
            else if(p.equals("queen")){
                if(white.getPiece(x,y)!=null&&white.getPiece(x,y).getName().equals(p)){
                    white.killPiece(x,y);
                }
                else if(white.getPiece(x,y)==null){
                    white.addPiece(x, y, 4);
                }
                gui.updateGui();
            }
            else if(p.equals("knight")){
                if(white.getPiece(x,y)!=null&&white.getPiece(x,y).getName().equals(p)){
                    white.killPiece(x,y);
                }
                else if(white.getPiece(x,y)==null){
                    white.addPiece(x, y, 1);
                }
                gui.updateGui();
            }
            else if(p.equals("rook")){
                if(white.getPiece(x,y)!=null&&white.getPiece(x,y).getName().equals(p)){
                    white.killPiece(x,y);
                }
                else if(white.getPiece(x,y)==null){
                    white.addPiece(x, y, 0);
                }
                gui.updateGui();
            }
        }
        else if(phase==-3){
            if(p.equals("king")){
                if(black.getPiece(x,y)!=null&&black.getPiece(x,y).getName().equals(p)&&white.getPiece(x,y)==null){
                    black.killPiece(x,y);
                }
                else if(black.getPiece(x,y)==null){
                    black.addPiece(x, y, 4);
                }
                gui.updateGui();
            }
            else if(p.equals("pawn")){
                if(black.getPiece(x,y)!=null&&black.getPiece(x,y).getName().equals(p)&&white.getPiece(x,y)==null){
                    black.killPiece(x,y);
                }
                else if(black.getPiece(x,y)==null){
                    black.addPiece(x, y, 8);
                }
                gui.updateGui();
            }
            else if(p.equals("bishop")){
                if(black.getPiece(x,y)!=null&&black.getPiece(x,y).getName().equals(p)&&white.getPiece(x,y)==null){
                    black.killPiece(x,y);
                }
                else if(black.getPiece(x,y)==null){
                    black.addPiece(x, y, 2);
                }
                gui.updateGui();
            }
            else if(p.equals("queen")){
                if(black.getPiece(x,y)!=null&&black.getPiece(x,y).getName().equals(p)&&white.getPiece(x,y)==null){
                    black.killPiece(x,y);
                }
                else if(black.getPiece(x,y)==null){
                    black.addPiece(x, y, 3);
                }
                gui.updateGui();
            }
            else if(p.equals("knight")){
                if(black.getPiece(x,y)!=null&&black.getPiece(x,y).getName().equals(p)&&white.getPiece(x,y)==null){
                    black.killPiece(x,y);
                }
                else if(black.getPiece(x,y)==null){
                    black.addPiece(x, y, 1);
                }
                gui.updateGui();
            }
            else if(p.equals("rook")){
                if(black.getPiece(x,y)!=null&&black.getPiece(x,y).getName().equals(p)&&white.getPiece(x,y)==null){
                    black.killPiece(x,y);
                }
                else if(black.getPiece(x,y)==null){
                    black.addPiece(x, y, 0);
                }
                gui.updateGui();
            }
        }
    }

    /* Title: update
     * Description: Updates graphics
     */
    public void update(){
        gui.updateGui();
    }

    /* Title: newBoard
     * Description: Creates new board
     */
    public void newBoard(){
        gui.toGame();
        board= new Board(this);
    }

    /* Title: whiteWin
     * Description: Returns weather white won
     */
    public boolean whiteWin(){
        return whiteWin;
    }

    /* Title: whiteWins
     * Description: Set white to win
     */
    public void whiteWins(){
        whiteWin=true;
    }

    /* Title: blackWin
     * Description: Returns weather black won
     */
    public boolean blackWin(){
        return blackWin;
    }

    /* Title: blackWins
     * Description: Set black to win
     */
    public void blackWins(){
        blackWin=true;
    }

    /* Title: stale
     * Description: Returns if stalemate
     */
    public boolean stale(){
        return stale;
    }

    /* Title: stalemate
     * Description: Set stalemate to true
     */
    public void stalemate(){
        stale=true;
    }

    /* Title: createAI
     * Description: Creates AI based on colour
     */
    public void createAI(Color c){
        chessAI= new ChessAI(c,this);
    }
}//Model
