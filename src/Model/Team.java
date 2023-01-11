package Model;// package line

//Imports
import java.awt.*;//Used to set and hold colour status

/* Title: Team
 * Version: 1.0
 * Author: Adam Shariff
 * Last Modified: 19/12/2022
 * Description: This class creates a chess team. It allows interaction with the individual pieces.
 */
class Team {

    //Instance variables
    private final Color colour;      //Holds the colour of the team
    private final ChessPiece[] team; //The array hold the pieces of the team
    private int totalPawns=8;        //Holds total pawns
    private int pawns =0;            //Holds number of pawns
    private int bishops=0;           //Holds number of bishops
    private int queen=0;             //Holds number of queens
    private int knight=0;            //Holds number of knights
    private int rook=0;              //Holds number of rooks

    //@Constructor: Gets team colour and creates team
    //@param Colour: Holds teams colour
    public Team(Color colour){
        //Sets colour and initializes array
        this.colour=colour;
        team= new ChessPiece[16];

        //Creates chess team
        //createTeam();
    }//Constructor

    //@Constructor: Gets team colour and creates team
    //@param Colour: Holds teams colour
    public Team(Color colour, ChessPiece []team){
        //Sets colour and initializes array
        this.colour=colour;
        this.team= team;

        //Creates chess team
        //createTeam();
    }//Constructor

    //Title: createTeam
    //Description: Creates chess team from chess pieces based on colour
    public void createTeam(){
        //Creates chess team based on team colour and sets initial positions
        if(colour.equals(Color.BLACK)){//Creates team for black team
            team[0]= new ChessPiece("rook",colour,0,0);
            team[1]= new ChessPiece("knight",colour,1,0);
            team[2]= new ChessPiece("bishop",colour,2,0);
            team[3]= new ChessPiece("queen",colour,3,0);
            team[4]= new ChessPiece("king",colour,4,0);
            team[5]= new ChessPiece("bishop",colour,5,0);
            team[6]= new ChessPiece("knight",colour,6,0);
            team[7]= new ChessPiece("rook",colour,7,0);
            //Loop creates all the pawns
            for(int x=0; x<8; x++){
                team[x+8]= new ChessPiece("pawn",colour,x,1);
            }//loop
        }
        else{//Creates team for the white team and sets initial positions
            team[0]= new ChessPiece("rook",colour,0,7);
            team[1]= new ChessPiece("knight",colour,1,7);
            team[2]= new ChessPiece("bishop",colour,2,7);
            team[3]= new ChessPiece("king",colour,4,7);
            team[4]= new ChessPiece("queen",colour,3,7);
            team[5]= new ChessPiece("bishop",colour,5,7);
            team[6]= new ChessPiece("knight",colour,6,7);
            team[7]= new ChessPiece("rook",colour,7,7);
            //Loop creates all the pawns
            for(int x=0; x<8; x++){
                team[x+8]= new ChessPiece("pawn",colour,x,6);
            }//loop
        }
    }//createTeam

    public void createBlank(){
        createTeam();
        team[0].died();
        team[1].died();
        team[2].died();
        team[3].died();
        team[4].died();
        team[5].died();
        team[6].died();
        team[7].died();
        //Loop creates all the pawns
        for(int x=0; x<8; x++){
            team[x+8].died();
        }//loop
    }

    /* Title: isAlive
     * Description: Returns if individual piece is alive
     * @param i: Position in array for a particular piece
     * @return: Returns a boolean that is the status of the piece
     */
    public boolean isAlive(int i){
        return team[i].isAlive();
    }//isAlive

    /* Title: getPieceX
     * Description: Returns x position of individual piece
     * @param i: Position in array for a particular piece
     * @return: Returns an integer of the x position of a piece
     */
    public int getPieceX(int i){
        return team[i].getX();
    }//getPieceX


    /* Title: getPieceY
     * Description: Returns y position of individual piece
     * @param i: Position in array for a particular piece
     * @return: Returns an integer of the y position of a piece
     */
    public int getPieceY(int i){
        return team[i].getY();
    }//getPieceY

    /* Title: getPiece
     * Description: Returns a given piece based on given coordinates
     * @param x: x coordinate
     * @param y: y coordinate
     * @return: Returns piece based on given coordinate. If piece does not have such coordinate returns null
     */
    public ChessPiece getPiece(int x, int y){
        //Loops through team
        for(int i=0; i<team.length; i++){
            if(getPieceX(i)==x&&getPieceY(i)==y&&team[i].isAlive()){
                //Returns piece form coordinate
                return team[i];
            }
        }//loop
        //Returns if no piece exists at given coordinate
        return null;
    }//getPiece

    //Title: resetPassant
    //Description: Reset if pawns can be en passant
    public void resetPassant(){
        for(int x=0; x<8; x++) {
            team[x + 8].resetEnPassant();
        }//loop
    }//resetPassant

    /* Title: getPiece
     * Description: Returns a given piece index
     * @param i: Index of piece in array
     * @return: Returns piece based on index
     */
    public ChessPiece getPiece(int i){
        return team[i];
    }//getPiece

    /* Title: addPiece
     * Description: addsPiece to team. Allows promotion to be added during loading phase
     * @param x: x position of piece
     * @param y: y position of piece
     * @param i: Index of piece in array
     */
    public void addPiece(int x, int y,int i){
        int j;//Used for creating promotion pieces

        //Adds king
        if((colour.equals(Color.white)&&i==3)||(colour.equals(Color.black)&&i==4)) {
            team[i].alive();
            team[i].setNewPos(x, y);
            if((colour.equals(Color.white)&&x==4&&y==7)||(colour.equals(Color.black)&&x==4&&y==0)) {
                team[i].resetMoved();
            }
        }
        else if(i==8){    //Add pawns
            if(pawns<totalPawns){
                j=pawnAdd();
                System.out.println(j);
                team[j].alive();
                team[j].setNewPos(x, y);
                pawns++;
                if((colour.equals(Color.white)&&y==6)||(colour.equals(Color.black)&&y==1)){
                    team[j].resetMoved();
                }
            }
        }
        else if(i==2){ //Adds bishops
            if(bishops<2){
                if(!team[2].isAlive()){
                    team[2].alive();
                    team[2].setNewPos(x,y);
                }
                else{
                    team[5].alive();
                    team[5].setNewPos(x,y);
                }
                bishops++;
            }
            else if(pawns<totalPawns){
                j=pawnAdd();
                System.out.println(j);
                team[j].alive();
                team[j].setNewPos(x, y);
                team[j].setName("bishop");
                bishops++;
                totalPawns=totalPawns-1;

            }
        }
        else if((i==4&&colour.equals(Color.white))||i==3&&colour.equals(Color.black)){  //Adds queens
            if(queen<1){
                team[i].alive();
                team[i].setNewPos(x,y);
                queen++;
            }
            else if(pawns<totalPawns){
                j=pawnAdd();
                System.out.println(j);
                team[j].alive();
                team[j].setNewPos(x, y);
                team[j].setName("queen");
                queen++;
                totalPawns=totalPawns-1;

            }
        }
        else if(i==1){ //Adds knights
            if(knight<2){
                if(!team[1].isAlive()){
                    team[1].alive();
                    team[1].setNewPos(x,y);
                }
                else{
                    team[6].alive();
                    team[6].setNewPos(x,y);
                }
                knight++;
            }
            else if(pawns<totalPawns){
                j=pawnAdd();
                System.out.println(j);
                team[j].alive();
                team[j].setNewPos(x, y);
                team[j].setName("knight");
                knight++;
                totalPawns=totalPawns-1;

            }
        }
        else if(i==0){ //Adds rooks
            boolean b = (((x == 0 && y == 0) || (x == 7 && y == 0)) && colour.equals(Color.black)) || (((x == 0 && y == 7) || (x == 7 && y == 7)) && colour.equals(Color.white));
            if(rook<2){
                if(!team[0].isAlive()){
                    team[0].alive();
                    team[0].setNewPos(x,y);
                    if(b) {
                        team[0].resetMoved();
                    }
                }
                else{
                    team[7].alive();
                    team[7].setNewPos(x,y);
                    if(b) {
                        team[7].resetMoved();
                    }
                }
                
                rook++;
            }
            else if(pawns<totalPawns){
                j=pawnAdd();
                System.out.println(j);
                team[j].alive();
                team[j].setNewPos(x, y);
                team[j].setName("rook");
                rook++;
                totalPawns=totalPawns-1;
                if(b) {
                    team[j].resetMoved();
                }

            }
        }
    }//addPiece

    /* Title: killPiece
     * Description: Kills a piece based on coordinates. Used for loading
     * @param x: x position
     * @param y: y position
     */
    public void killPiece(int x,int y){
        ChessPiece p=getPiece(x,y);
        if(p.getName().equals("pawn")){
            pawns=pawns-1;
        }
        if(p.getName().equals("bishop")){
            if(bishops>2){
                totalPawns++;
                p.changeName("pawn");
            }
            bishops=bishops-1;
        }
        if(p.getName().equals("queen")){
            if(queen>1){
                totalPawns++;
                p.changeName("pawn");
            }
            queen=queen-1;
        }
        if(p.getName().equals("knight")){
            if(knight>2){
                totalPawns++;
                p.changeName("pawn");
            }
            knight=knight-1;
        }
        if(p.getName().equals("rook")){
            if(rook>2){
                totalPawns++;
                p.changeName("pawn");
            }
            rook=rook-1;
        }
        p.died();
    }//killPiece

    /* Title: pawnAdd
     * Description: Returns index first available pawn to be added back into the game
     * @return: Returns a dead pawns index
     */
    private int pawnAdd(){
        for(int i=8; i<16; i++){
            if(!team[i].isAlive()){
                return i;
            }
        }
        return -1;
    }//pawnAdd


    /* Title: copy
     * Description: Copies instance of team
     * @return: Returns new instance of current team
     */
    public Team copy(){
        Team t;
        ChessPiece[] te= new ChessPiece[16];

        for(int i=0; i<16; i++){
            te[i]= team[i].copy();
        }
        t= new Team(colour,te);
        return t;
    }//copy
}//Team
