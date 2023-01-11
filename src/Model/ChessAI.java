package Model;

import java.awt.Color;

public class ChessAI {

    private final Model m;  //Holds model
    private final Color c;  //Holds AI color

    //Material and space values
    private final int QUEEN_VAL=900;
    private final int KING_VAL=20000;
    private final int ROOK_VAL=500;
    private final int KNIGHT_VAL=320;
    private final int BISHOP_VAL=330;
    private final int PAWN_VAL=100;
    public int DEPTH=2;
    private final int[][] pawnW= new int[][]{
            {  0,  0,  0,  0,  0,  0,  0,  0},
            { 50, 50, 50, 50, 50, 50, 50, 50},
            { 10, 10, 20, 30, 30, 20, 10, 10},
            {  5,  5, 10, 25, 25, 10,  5,  5},
            {  0,  0,  0, 20, 20,  0,  0,  0},
            {  5, -5,-10,  0,  0,-10, -5,  5},
            {  5, 10, 10,-20,-20, 10, 10,  5},
            {  0,  0,  0,  0,  0,  0,  0,  0}};

    private final int[][] pawnB= new int[][]{
            {  0,  0,  0,  0,  0,  0,  0,  0},
            {  5, 10, 10,-20,-20, 10, 10,  5},
            {  5, -5,-10,  0,  0,-10, -5,  5},
            {  0,  0,  0, 20, 20,  0,  0,  0},
            {  5,  5, 10, 25, 25, 10,  5,  5},
            { 10, 10, 20, 30, 30, 20, 10, 10},
            { 50, 50, 50, 50, 50, 50, 50, 50},
            {  0,  0,  0,  0,  0,  0,  0,  0}};

    private final int[][] knightW= new int[][] {
            {-50,-40,-30,-30,-30,-30,-40,-50},
            {-40,-20,  0,  0,  0,  0,-20,-40},
            {-30,  0, 10, 15, 15, 10,  0,-30},
            {-30,  5, 15, 20, 20, 15,  5,-30},
            {-30,  0, 15, 20, 20, 15,  0,-30},
            {-30,  5, 10, 15, 15, 10,  5,-30},
            {-40,-20,  0,  5,  5,  0,-20,-40},
            {-50,-40,-30,-30,-30,-30,-40,-50}};

    private final int[][] knightB= new int[][] {
            {-50,-40,-30,-30,-30,-30,-40,-50},
            {-40,-20,  0,  5,  5,  0,-20,-40},
            {-30,  5, 10, 15, 15, 10,  5,-30},
            {-30,  0, 15, 20, 20, 15,  0,-30},
            {-30,  5, 15, 20, 20, 15,  5,-30},
            {-30,  0, 10, 15, 15, 10,  0,-30},
            {-40,-20,  0,  0,  0,  0,-20,-40},
            {-50,-40,-30,-30,-30,-30,-40,-50}};

    private final int[][] bishopW= new int[][]{
            {-20,-10,-10,-10,-10,-10,-10,-20},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-10,  0,  5, 10, 10,  5,  0,-10},
            {-10,  5,  5, 10, 10,  5,  5,-10},
            {-10,  0, 10, 10, 10, 10,  0,-10},
            {-10, 10, 10, 10, 10, 10, 10,-10},
            {-10,  5,  0,  0,  0,  0,  5,-10},
            {-20,-10,-10,-10,-10,-10,-10,-20}};

    private final int[][] bishopB= new int[][]{
            {-20,-10,-10,-10,-10,-10,-10,-20},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-10, 10, 10, 10, 10, 10, 10,-10},
            {-10,  0, 10, 10, 10, 10,  0,-10},
            {-10,  5,  5, 10, 10,  5,  5,-10},
            {-10,  0,  5, 10, 10,  5,  0,-10},
            {-10,  5,  0,  0,  0,  0,  5,-10},
            {-20,-10,-10,-10,-10,-10,-10,-20}};

    private final int[][] rookW= new int[][]{
            { 0,  0,  0,  0,  0,  0,  0,  0},
            { 5, 10, 10, 10, 10, 10, 10,  5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            { 0,  0,  0,  5,  5,  0,  0,  0}};

    private final int[][] rookB= new int[][]{
            { 0,  0,  0,  5,  5,  0,  0,  0},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            { 5, 10, 10, 10, 10, 10, 10,  5},
            { 0,  0,  0,  0,  0,  0,  0,  0}};


    private final int[][] queenW= new int[][]{
            {-20,-10,-10, -5, -5,-10,-10,-20},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-10,  0,  5,  5,  5,  5,  0,-10},
            { -5,  0,  5,  5,  5,  5,  0, -5},
            {  0,  0,  5,  5,  5,  5,  0, -5},
            {-10,  5,  5,  5,  5,  5,  0,-10},
            {-10,  0,  5,  0,  0,  0,  0,-10},
            {-20,-10,-10, -5, -5,-10,-10,-20}};

    private final int[][] queenB= new int[][]{
            {-20,-10,-10, -5, -5,-10,-10,-20},
            {-10,  0,  5,  0,  0,  0,  0,-10},
            {-10,  5,  5,  5,  5,  5,  0,-10},
            {-10,  0,  5,  5,  5,  5,  0,-10},
            { -5,  0,  5,  5,  5,  5,  0, -5},
            {  0,  0,  5,  5,  5,  5,  0, -5},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-20,-10,-10, -5, -5,-10,-10,-20}};

    private final int[][] kingWB= new int[][]{
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-20,-30,-30,-40,-40,-30,-30,-20},
            {-10,-20,-20,-20,-20,-20,-20,-10},
            { 20, 20,  0,  0,  0,  0, 20, 20},
            { 20, 30, 10,  0,  0, 10, 30, 20}};

    private final int[][] kingBB= new int[][]{
            { 20, 30, 10,  0,  0, 10, 30, 20},
            { 20, 20,  0,  0,  0,  0, 20, 20},
            {-10,-20,-20,-20,-20,-20,-20,-10},
            {-20,-30,-30,-40,-40,-30,-30,-20},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30}};

    private final int[][] kingWE= new int[][]{
            {-50,-40,-30,-20,-20,-30,-40,-50},
            {-30,-20,-10,  0,  0,-10,-20,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-30,  0,  0,  0,  0,-30,-30},
            {-50,-30,-30,-30,-30,-30,-30,-50}};

    private final int[][] kingBE= new int[][]{
            {-50,-40,-30,-20,-20,-30,-40,-50},
            {-30,-20,-10,  0,  0,-10,-20,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-30,  0,  0,  0,  0,-30,-30},
            {-50,-30,-30,-30,-30,-30,-30,-50}};

    public ChessAI(Color color, Model m){
        this.c=color;
        this.m=m;
    }//constructor


    //For testing
    public void displayBoard(char[][]b){
        for(int j=0; j<8; j++){
            for(int i=0; i<8; i++){
                System.out.print(b[j][i]);
            }//loop
            System.out.println("");
        }//loop
    }

    //AI makes move based on min max
    public void makeMove(char[][]b, Team black, Team white){
        int alpha=Integer.MIN_VALUE;
        int beta=Integer.MAX_VALUE;
        int depth=0;
        Move move= new Move();
        move.eval=Integer.MIN_VALUE;
        move.y2=-1;
        Move move2;

        //min/max
        if(c.equals(Color.white)){
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(Character.isUpperCase(b[i][j])){
                        if(b[i][j]=='K'){
                            move2=king(j,i,b,alpha,beta,depth+1,white,black,0,Color.white);
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='B'){
                            move2=bishop(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='Q'){
                            move2=queen(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='R'){
                            move2=rook(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='H'){
                            move2=knight(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='P'){
                            move2=pawn(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                    }
                }
            }
        }
        else if(c.equals(Color.black)){
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(Character.isLowerCase(b[i][j])){
                        if(b[i][j]=='k'){
                            move2=king(j,i,b,alpha,beta,depth+1,white,black,0,Color.black);
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='b'){
                            move2=bishop(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='q'){
                            move2=queen(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='r'){
                            move2=rook(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='h'){
                            move2=knight(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='p'){
                            move2=pawn(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                    }
                }
            }
        }
        if(move.y2!=-1){
            m.setPiece(move.x1,move.y1);
            m.capture(move.x2,move.y2);
            m.movePiece(move.x2,move.y2);
            m.promotion();
            if(c.equals(Color.white)){
                m.setPhase(3);
            }
            else{
                m.setPhase(2);
            }
            if(c.equals(Color.black)){
                m.setPhase(2);
            }
            else{
                m.setPhase(3);
            }
        }
    }//makeMove


    //Min of min max
    public int min(int alpha, int beta, char[][]b, int depth, Team white, Team black){
        Move move= new Move();
        move.eval=Integer.MAX_VALUE;
        move.y2=-1;
        Move move2;
        if(depth==DEPTH){
            return evaluate(white,black,copy(b));
        }
        else if(c.equals(Color.white)){
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(Character.isLowerCase(b[i][j])){
                        if(b[i][j]=='k'){
                            move2=king(j,i,b,alpha,beta,depth+1,white,black,0,Color.black);
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='b'){
                            move2=bishop(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,1,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='q'){
                            move2=queen(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,1,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='r'){
                            move2=rook(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,1,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='h'){
                            move2=knight(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,1,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='p'){
                            move2=pawn(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,1,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                    }
                }
            }
        }
        else if(c.equals(Color.black)){
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(Character.isUpperCase(b[i][j])){
                        if(b[i][j]=='K'){
                            move2=king(j,i,b,alpha,beta,depth+1,white,black,0,Color.white);
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='B'){
                            move2=bishop(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,1,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='Q'){
                            move2=queen(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,1,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='R'){
                            move2=rook(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,1,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='H'){
                            move2=knight(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,1,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='P'){
                            move2=pawn(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,1,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval< move.eval)){
                                move=move2;
                            }
                        }
                    }
                }
            }
        }
        return move.eval;
    }


    //Max value of min max
    public int max(int alpha, int beta, char[][]b, int depth, Team white, Team black){
        Move move= new Move();
        move.eval=Integer.MIN_VALUE;
        move.y2=-1;
        Move move2;
        if(depth==DEPTH){
            return evaluate(white,black,copy(b));
        }
        else if(c.equals(Color.white)){
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(Character.isUpperCase(b[i][j])){
                        if(b[i][j]=='K'){
                            move2=king(j,i,b,alpha,beta,depth+1,white,black,0,Color.white);
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='B'){
                            move2=bishop(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='Q'){
                            move2=queen(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='R'){
                            move2=rook(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='H'){
                            move2=knight(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='P'){
                            move2=pawn(Color.white,white.getPieceX(3), white.getPieceY(3),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                    }
                }
            }
        }
        else if(c.equals(Color.black)){
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(Character.isLowerCase(b[i][j])){
                        if(b[i][j]=='k'){
                            move2=king(j,i,b,alpha,beta,depth+1,white,black,0,Color.black);
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='b'){
                            move2=bishop(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='q'){
                            move2=queen(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='r'){
                            move2=rook(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='h'){
                            move2=knight(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                        else if(b[i][j]=='p'){
                            move2=pawn(Color.black,black.getPieceX(4), black.getPieceY(4),copy(b),j,i,white,black,0,alpha,beta,depth+1 );
                            if(move2!=null&&(move2.eval> move.eval)){
                                move=move2;
                            }
                        }
                    }
                }
            }
        }
        return move.eval;
    }

    //Does all king moves
    private Move king( int x, int y, char[][] board, int alpha, int beta,int depth, Team white, Team black, int mm, Color col){
        char[][] b;
        Team cw,cb;
        int eval;
        Move move= new Move();
        if(mm==0){
            move.eval=Integer.MIN_VALUE;
        }
        else{
            move.eval=Integer.MAX_VALUE;
        }
        move.x1=-1;
        if (x > 0) {
            b=copy(board);
            if(Condition.moveKing(x-1,y,x,y,col,b)){
                cw=white.copy();
                cb=black.copy();
                b[y][x-1]=b[y][x];
                b[y][x]='x';
                if(col.equals(Color.white)){
                    cw.getPiece(x,y).setNewPos(x-1,y);
                    if(cb.getPiece(x-1,y)!=null){
                        cb.getPiece(x-1,y).died();
                    }
                }
                else{
                    cb.getPiece(x,y).setNewPos(x-1,y);
                    if(cw.getPiece(x-1,y)!=null){
                        cw.getPiece(x-1,y).died();
                    }
                }
                if(mm==0) {
                    eval = min(alpha, beta, b, depth, cw, cb);
                    if(eval> move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x-1;
                        move.y2=y;
                    }
                }
                else if(mm==1) {
                    eval = max(alpha, beta, b, depth, cw, cb);
                    if(eval< move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x-1;
                        move.y2=y;
                    }
                }
            }
        }
        if (x < 7) {
            b=copy(board);
            if(Condition.moveKing(x+1,y,x,y,col,b)){
                cw=white.copy();
                cb=black.copy();
                b[y][x+1]=b[y][x];
                b[y][x]='x';
                if(col.equals(Color.white)){
                    cw.getPiece(x,y).setNewPos(x+1,y);
                    if(cb.getPiece(x+1,y)!=null){
                        cb.getPiece(x+1,y).died();
                    }
                }
                else{
                    cb.getPiece(x,y).setNewPos(x+1,y);
                    if(cw.getPiece(x+1,y)!=null){
                        cw.getPiece(x+1,y).died();
                    }
                }
                if(mm==0) {
                    eval = min(alpha, beta, b, depth, cw, cb);
                    if(eval> move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x+1;
                        move.y2=y;
                    }
                }
                else if(mm==1) {
                    eval = max(alpha, beta, b, depth, cw, cb);
                    if(eval< move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x+1;
                        move.y2=y;
                    }
                }
            }
        }
        if (y > 0) {
            b=copy(board);
            if(Condition.moveKing(x,y-1,x,y,col,b)){
                cw=white.copy();
                cb=black.copy();
                b[y-1][x]=b[y][x];
                b[y][x]='x';
                if(col.equals(Color.white)){
                    cw.getPiece(x,y).setNewPos(x,y-1);
                    if(cb.getPiece(x,y-1)!=null){
                        cb.getPiece(x,y-1).died();
                    }
                }
                else{
                    cb.getPiece(x,y).setNewPos(x,y-1);
                    if(cw.getPiece(x,y-1)!=null){
                        cw.getPiece(x,y-1).died();
                    }
                }
                if(mm==0) {
                    eval = min(alpha, beta, b, depth, cw, cb);
                    if(eval> move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x;
                        move.y2=y-1;
                    }
                }
                else if(mm==1) {
                    eval = max(alpha, beta, b, depth, cw, cb);
                    if(eval< move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x;
                        move.y2=y-1;
                    }
                }
            }
        }
        if (y < 7) {
            b=copy(board);
            if(Condition.moveKing(x,y+1,x,y,col,b)){
                cw=white.copy();
                cb=black.copy();
                b[y+1][x]=b[y][x];
                b[y][x]='x';
                if(col.equals(Color.white)){
                    cw.getPiece(x,y).setNewPos(x,y+1);
                    if(cb.getPiece(x,y+1)!=null){
                        cb.getPiece(x,y+1).died();
                    }
                }
                else{
                    cb.getPiece(x,y).setNewPos(x,y+1);
                    if(cw.getPiece(x,y+1)!=null){
                        cw.getPiece(x,y+1).died();
                    }
                }
                if(mm==0) {
                    eval = min(alpha, beta, b, depth, cw, cb);
                    if(eval> move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x;
                        move.y2=y+1;
                    }
                }
                else if(mm==1) {
                    eval = max(alpha, beta, b, depth, cw, cb);
                    if(eval< move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x;
                        move.y2=y+1;
                    }
                }
            }
        }
        if (y < 7&&x<7) {
            b=copy(board);
            if(Condition.moveKing(x+1,y+1,x,y,col,b)){
                cw=white.copy();
                cb=black.copy();
                b[y+1][x+1]=b[y][x];
                b[y][x]='x';
                if(col.equals(Color.white)){
                    cw.getPiece(x,y).setNewPos(x+1,y+1);
                    if(cb.getPiece(x+1,y+1)!=null){
                        cb.getPiece(x+1,y+1).died();
                    }
                }
                else{
                    cb.getPiece(x,y).setNewPos(x+1,y+1);
                    if(cw.getPiece(x+1,y+1)!=null){
                        cw.getPiece(x+1,y+1).died();
                    }
                }
                if(mm==0) {
                    eval = min(alpha, beta, b, depth, cw, cb);
                    if(eval> move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x+1;
                        move.y2=y+1;
                    }
                }
                else if(mm==1) {
                    eval = max(alpha, beta, b, depth, cw, cb);
                    if(eval< move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x+1;
                        move.y2=y+1;
                    }
                }
            }
        }
        if (y < 7&&x>0) {
            b=copy(board);
            if(Condition.moveKing(x-1,y+1,x,y,c,b)){
                cw=white.copy();
                cb=black.copy();
                b[y+1][x-1]=b[y][x];
                b[y][x]='x';
                if(col.equals(Color.white)){
                    cw.getPiece(x,y).setNewPos(x-1,y+1);
                    if(cb.getPiece(x-1,y+1)!=null){
                        cb.getPiece(x-1,y+1).died();
                    }
                }
                else{
                    cb.getPiece(x,y).setNewPos(x-1,y+1);
                    if(cw.getPiece(x-1,y+1)!=null){
                        cw.getPiece(x-1,y+1).died();
                    }
                }
                if(mm==0) {
                    eval = min(alpha, beta, b, depth, cw, cb);
                    if(eval> move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x-1;
                        move.y2=y+1;
                    }
                }
                if(mm==1) {
                    eval = max(alpha, beta, b, depth, cw, cb);
                    if(eval< move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x-1;
                        move.y2=y+1;
                    }
                }
            }
        }
        if (y > 0&&x<7) {
            b=copy(board);
            if(Condition.moveKing(x+1,y-1,x,y,c,b)){
                cw=white.copy();
                cb=black.copy();
                b[y-1][x+1]=b[y][x];
                b[y][x]='x';
                if(col.equals(Color.white)){
                    cw.getPiece(x,y).setNewPos(x+1,y-1);
                    if(cb.getPiece(x+1,y-1)!=null){
                        cb.getPiece(x+1,y-1).died();
                    }
                }
                else{
                    cb.getPiece(x,y).setNewPos(x+1,y-1);
                    if(cw.getPiece(x+1,y-1)!=null){
                        cw.getPiece(x+1,y-1).died();
                    }
                }
                if(mm==0) {
                    eval = min(alpha, beta, b, depth, cw, cb);
                    if(eval> move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x+1;
                        move.y2=y-1;
                    }
                }
                else if(mm==1) {
                    eval = max(alpha, beta, b, depth, cw, cb);
                    if(eval< move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x+1;
                        move.y2=y-1;
                    }
                }
            }
        }
        if (y > 0&&x>0) {
            b=copy(board);
            if(Condition.moveKing(x-1,y-1,x,y,c,b)){
                cw=white.copy();
                cb=black.copy();
                b[y-1][x-1]=b[y][x];
                b[y][x]='x';
                if(col.equals(Color.white)){
                    cw.getPiece(x,y).setNewPos(x-1,y-1);
                    if(cb.getPiece(x-1,y-1)!=null){
                        cb.getPiece(x-1,y-1).died();
                    }
                }
                else{
                    cb.getPiece(x,y).setNewPos(x-1,y-1);
                    if(cw.getPiece(x-1,y-1)!=null){
                        cw.getPiece(x-1,y-1).died();
                    }
                }
                if(mm==0) {
                    eval = min(alpha, beta, b, depth, cw, cb);
                    if(eval> move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x-1;
                        move.y2=y-1;
                    }
                }
                else if(mm==1) {
                    eval = max(alpha, beta, b, depth, cw, cb);
                    if(eval< move.eval){
                        move.eval=eval;
                        move.x1=x;
                        move.y1=y;
                        move.x2=x-1;
                        move.y2=y-1;
                    }
                }
            }
        }
        if(move.x1!=-1) {
            return move;
        }
        else{
            return null;
        }
    }

    //Copies 2D array
    private char[][] copy(char[][] b){
        char [][]board = new char[8][8];
        for(int j=0; j<8; j++){
            for(int i=0; i<8; i++){
                board[j][i]=b[j][i];
            }
        }
        return board;
    }


    //Heuristic eval
    private int evaluate(Team white, Team black,char[][]board){
        int wP=0,bP=0;
        int wB=0,bB=0;
        int wR=0,bR=0;
        int wKN=0,bKN=0;
        int wQ=0,bQ=0;
        int wK=0,bK=0;
        int wM=0,bM=0;
        for(int i=0; i<16; i++){
            if((i==0||i==7)&&white.isAlive(i)){
                wR++;
                wM=wM+rookW[white.getPieceY(i)][white.getPieceX(i)];
            }
            else if((i==1||i==6)&&white.isAlive(i)){
                wKN++;
                wM=wM+knightW[white.getPieceY(i)][white.getPieceX(i)];
            }
            else if((i==2||i==5)&&white.isAlive(i)){
                wB++;
                wM=wM+bishopW[white.getPieceY(i)][white.getPieceX(i)];
            }
            else if((i==3)&&white.isAlive(i)&&!Condition.checkmate(Color.white,white.getPieceX(3),white.getPieceY(3),copy(board),white,black)){
                wK++;
                wM=wM+kingWB[white.getPieceY(i)][white.getPieceX(i)];
            }
            else if((i==4)&&white.isAlive(i)){
                wQ++;
                wM=wM+queenW[white.getPieceY(i)][white.getPieceX(i)];
            }
            else if(i>7&&white.isAlive(i)){
                wP++;
                wM=wM+pawnW[white.getPieceY(i)][white.getPieceX(i)];
            }

            if((i==0||i==7)&&black.isAlive(i)){
                bR++;
                bM=bM+rookB[black.getPieceY(i)][black.getPieceX(i)];
            }
            else if((i==1||i==6)&&black.isAlive(i)){
                bKN++;
                bM=bM+knightB[black.getPieceY(i)][black.getPieceX(i)];
            }
            else if((i==2||i==5)&&black.isAlive(i)){
                bB++;
                bM=bM+bishopB[black.getPieceY(i)][black.getPieceX(i)];
            }
            else if((i==4)&&black.isAlive(i)&&!Condition.checkmate(Color.black,black.getPieceX(4),black.getPieceY(4),copy(board),white,black)){
                bK++;
                bM=bM+kingWB[black.getPieceY(i)][black.getPieceX(i)];
            }
            else if((i==3)&&black.isAlive(i)){
                bQ++;
                bM=bM+queenB[black.getPieceY(i)][black.getPieceX(i)];
            }
            else if(i>7&&black.isAlive(i)){
                bP++;
                bM=bM+pawnB[black.getPieceY(i)][black.getPieceX(i)];
            }

        }
        if(c.equals(Color.white)){
            return KING_VAL*(wK-bK)+QUEEN_VAL*(wQ-bQ)+ROOK_VAL*(wR-bR)+BISHOP_VAL*(wB-bB)+KNIGHT_VAL*(wKN-bKN)+PAWN_VAL*(wP-bP)+(wM-bM);
        }
        else{
            return KING_VAL*(bK-wK)+QUEEN_VAL*(bQ-wQ)+ROOK_VAL*(bR-wR)+BISHOP_VAL*(bB-wB)+KNIGHT_VAL*(bKN-wKN)+PAWN_VAL*(bP-wP)+(bM-wM);
        }

    }

    //Checks if bishop can move
    private Move bishop(Color col, int x, int y, char[][] board,int x2, int y2, Team white, Team black,int mm,int alpha, int beta, int depth){
        ChessPiece p;
        char[][] b;
        Team cw,cb;
        int eval;
        Move move= new Move();
        if(mm==0){
            move.eval=Integer.MIN_VALUE;
        }
        else{
            move.eval=Integer.MAX_VALUE;
        }
        move.x1=-1;
        if(col.equals(Color.white)) {
            p= white.getPiece(x2,y2);
            if (x2 > 0 && y2 > 0) {
                for (int i = 1; x2 - i >= 0 && y2 - i >= 0; i++) {
                    if(Character.isUpperCase(board[y2-i][x2-i])||(Character.isLowerCase(board[y2-i+1][x2-i+1])&&board[y2-i+1][x2-i+1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2-i,y2-i,p,b,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2-i]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2-i,y2-i);
                        if(cb.getPiece(x2-i,y2-i)!=null){
                                cb.getPiece(x2-i,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2-i;
                            }
                        }
                    }
                }

            }
            if(x2<7&&y2<7){
                for(int i=1; x2+i<8&&y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2+i])||(Character.isLowerCase(board[y2+i-1][x2+i-1])&&board[y2+i-1][x2+i-1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2+i,y2+i,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2+i,y2+i);
                        if(cb.getPiece(x2+i,y2+i)!=null){
                            cb.getPiece(x2+i,y2+i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2+i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(x2>0&&y2<7){
                for(int i=1; x2-i>=0&&y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2-i])||(Character.isLowerCase(board[y2+i-1][x2-i+1])&&board[y2+i-1][x2-i+1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2-i,y2+i,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2-i]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2+i,y2-i);
                        if(cb.getPiece(x2-i,y2+i)!=null){
                            cb.getPiece(x2-i,y2+i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2+i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(x2<7&&y2>0){
                p= white.getPiece(x2,y2);
                for(int i=1; x2+i<8&&y2-i>=0; i++){
                    if(Character.isUpperCase(board[y2-i][x2+i])||(Character.isLowerCase(board[y2-i+1][x2+i-1])&&board[y2-i+1][x2+i-1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2+i,y2-i,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2+i,y2-i);
                        if(cb.getPiece(x2+i,y2-i)!=null){
                            cb.getPiece(x2+i,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2-i;
                            }
                        }
                    }
                }
            }
        }
        else if(col.equals(Color.black)) {
            p= black.getPiece(x2,y2);
            if (x2 > 0 && y2 > 0) {
                for (int i = 1; x2 - i >= 0 && y2 - i >= 0; i++) {
                    if((Character.isLowerCase(board[y2-i][x2-i])&&board[y2-i][x2-i]!='x')||(Character.isUpperCase(board[y2-i+1][x2-i+1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2-i,y2-i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2-i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2-i,y2-i);
                        if(cw.getPiece(x2-i,y2-i)!=null){
                            cw.getPiece(x2-i,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2-i;
                            }
                        }
                    }
                }

            }
            if(x2<7&&y2<7){
                for(int i=1; x2+i<8&&y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2+i])&&board[y2+i][x2+i]!='x')||(Character.isUpperCase(board[y2+i-1][x2+i-1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2+i,y2+i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2+i,y2+i);
                        if(cw.getPiece(x2+i,y2+i)!=null){
                            cw.getPiece(x2+i,y2+i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2+i;
                            }
                        }
                        if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(x2>0&&y2<7){
                for(int i=1; x2-i>=0&&y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2-i])&&board[y2+i][x2-i]!='x')||(Character.isUpperCase(board[y2+i-1][x2-i+1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2-i,y2+i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2-i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2-i,y2+i);
                        if(cw.getPiece(x2-i,y2+i)!=null){
                            cw.getPiece(x2-i,y2+i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2+i;
                            }
                        }
                        else if(mm==1) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(x2<7&&y2>0){
                for(int i=1; x2+i<8&&y2-i>=0; i++){
                    if((Character.isLowerCase(board[y2-i][x2+i])&&board[y2-i][x2+i]!='x')||(Character.isUpperCase(board[y2-i+1][x2+i-1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2+i,y2-i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2+i,y2-i);
                        if(cw.getPiece(x2+i,y2-i)!=null){
                            cw.getPiece(x2+i,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2-i;
                            }
                        }
                    }
                }
            }
        }

        if(move.x1!=-1) {
            return move;
        }
        else{
            return null;
        }
    }

    //Computes all queen moves
    private Move queen(Color c, int x, int y, char[][] board,int x2, int y2, Team white, Team black,int mm,int alpha, int beta, int depth){
        ChessPiece p;
        char[][] b;
        Team cw,cb;
        int eval;
        Move move= new Move();
        if(mm==0){
            move.eval=Integer.MIN_VALUE;
        }
        else{
            move.eval=Integer.MAX_VALUE;
        }
        move.x1=-1;
        if(c.equals(Color.white)) {
            p= white.getPiece(x2,y2);
            if (x2 > 0 && y2 > 0) {
                for (int i = 1; x2 - i >= 0 && y2 - i >= 0; i++) {
                    if(Character.isUpperCase(board[y2-i][x2-i])||(Character.isLowerCase(board[y2-i+1][x2-i+1])&&board[y2-i+1][x2-i+1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2-i,y2-i,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2-i]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2-i,y2-i);
                        if(cb.getPiece(x2-i,y2-i)!=null){
                            cb.getPiece(x2-i,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2-i;
                            }
                        }
                    }
                }
            }
            if(x2<7&&y2<7){
                for(int i=1; x2+i<8&&y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2+i])||(Character.isLowerCase(board[y2+i-1][x2+i-1])&&board[y2+i-1][x2+i-1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2+i,y2+i,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2+i,y2+i);
                        if(cb.getPiece(x2+i,y2+i)!=null){
                            cb.getPiece(x2+i,y2+i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2+i;
                            }
                        }
                        if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(x2>0&&y2<7){
                for(int i=1; x2-i>=0&&y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2-i])||(Character.isLowerCase(board[y2+i-1][x2-i+1])&&board[y2+i-1][x2-i+1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2-i,y2+i,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2-i]='Q';
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2-i,y2+i);
                        if(cb.getPiece(x2-i,y2+i)!=null){
                            cb.getPiece(x2-i,y2+i).died();
                        }
                        if(mm==0) {
                            displayBoard(b);
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2+i;
                            }
                        }
                        else if(mm==1) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(x2<7&&y2>0){
                for(int i=1; x2+i<8&&y2-i>=0; i++){
                    if(Character.isUpperCase(board[y2-i][x2+i])||(Character.isLowerCase(board[y2-i+1][x2+i-1])&&board[y2-i+1][x2+i-1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2+i,y2-i,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2+i,y2-i);
                        if(cb.getPiece(x2+i,y2-i)!=null){
                            cb.getPiece(x2+i,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2-i;
                            }
                        }
                    }
                }
            }
            if (x2 > 0) {
                for (int i = 1; x2 - i >= 0; i++) {
                    if(Character.isUpperCase(board[y2][x2-i])||(Character.isLowerCase(board[y2][x2-i+1])&&board[y2][x2-i+1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2-i,y2,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2][x2-i]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2-i,y2);
                        if(cb.getPiece(x2-i,y2)!=null){
                            cb.getPiece(x2-i,y2).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2;
                            }
                        }
                    }
                }

            }
            if(x2<7){
                for(int i=1; x2+i<8; i++){
                    if(Character.isUpperCase(board[y2][x2+i])||(Character.isLowerCase(board[y2][x2+i-1])&&board[y2][x2+i-1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2+i,y2,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2+i,y2);
                        if(cb.getPiece(x2+i,y2)!=null){
                            cb.getPiece(x2+i,y2).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2;
                            }
                        }
                    }
                }
            }
            if(y2<7){
                for(int i=1; y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2])||(Character.isLowerCase(board[y2+i-1][x2])&&board[y2+i-1][x2]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2,y2+i,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2,y2+i);
                        if(cb.getPiece(x2,y2+i)!=null){
                            cb.getPiece(x2,y2+i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2+i;
                            }
                        }
                        else if(mm==1) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(y2>0){
                for(int i=1; y2-i>=0; i++){
                    if(Character.isUpperCase(board[y2-i][x2])||(Character.isLowerCase(board[y2-i+1][x2])&&board[y2-i+1][x2]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2,y2-i,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2,y2-i);
                        if(cb.getPiece(x2,y2-i)!=null){
                            cb.getPiece(x2,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2-i;
                            }
                        }
                    }

                }
            }
        }
        else if(c.equals(Color.black)) {
            p= black.getPiece(x2,y2);
            if (x2 > 0 && y2 > 0) {
                for (int i = 1; x2 - i >= 0 && y2 - i >= 0; i++) {
                    if((Character.isLowerCase(board[y2-i][x2-i])&&board[y2-i][x2-i]!='x')||(Character.isUpperCase(board[y2-i+1][x2-i+1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2-i,y2-i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2-i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2-i,y2-i);
                        if(cw.getPiece(x2-i,y2-i)!=null){
                            cw.getPiece(x2-i,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2-i;
                            }
                        }
                    }
                }

            }
            if(x2<7&&y2<7){
                for(int i=1; x2+i<8&&y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2+i])&&board[y2+i][x2+i]!='x')||(Character.isUpperCase(board[y2+i-1][x2+i-1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2+i,y2+i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2+i,y2+i);
                        if(cw.getPiece(x2+i,y2+i)!=null){
                            cw.getPiece(x2+i,y2+i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2+i;
                            }
                        }
                        if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(x2>0&&y2<7){
                for(int i=1; x2-i>=0&&y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2-i])&&board[y2+i][x2-i]!='x')||(Character.isUpperCase(board[y2+i-1][x2-i+1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2-i,y2+i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2-i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2-i,y2+i);
                        if(cw.getPiece(x2-i,y2+i)!=null){
                            cw.getPiece(x2-i,y2+i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2+i;
                            }
                        }
                        else if(mm==1) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(x2<7&&y2>0){
                for(int i=1; x2+i<8&&y2-i>=0; i++){
                    if((Character.isLowerCase(board[y2-i][x2+i])&&board[y2-i][x2+i]!='x')||(Character.isUpperCase(board[y2-i+1][x2+i-1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.diagonalCheck(x2+i,y2-i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2+i,y2-i);
                        if(cw.getPiece(x2+i,y2-i)!=null){
                            cw.getPiece(x2+i,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2-i;
                            }
                        }
                    }
                }
            }
            if (x2 > 0) {
                for (int i = 1; x2 - i >= 0; i++) {
                    if((Character.isLowerCase(board[y2][x2-i])&&board[y2][x2-i]!='x')||(Character.isUpperCase(board[y2][x2-i+1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2-i,y2,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2][x2-i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2-i,y2);
                        if(cw.getPiece(x2-i,y2)!=null){
                            cw.getPiece(x2-i,y2).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2;
                            }
                        }
                    }
                }

            }
            if(x2<7){
                for(int i=1; x2+i<8; i++){
                    if((Character.isLowerCase(board[y2][x2+i])&&board[y2][x2+i]!='x')||(Character.isUpperCase(board[y2][x2+i-1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2+i,y2,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2+i,y2);
                        if(cw.getPiece(x2+i,y2)!=null){
                            cw.getPiece(x2+i,y2).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2;
                            }
                        }
                    }
                }
            }
            if(y2<7){
                for(int i=1; y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2])&&board[y2+i][x2]!='x')||(Character.isUpperCase(board[y2+i-1][x2]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2,y2+i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2,y2+i);
                        if(cw.getPiece(x2,y2+i)!=null){
                            cw.getPiece(x2,y2+i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2+i;
                            }
                        }
                        else if(mm==1) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(y2>0){
                for(int i=1; y2-i>=0; i++){
                    if((Character.isLowerCase(board[y2-i][x2])&&board[y2-i][x2]!='x')||(Character.isUpperCase(board[y2-i+1][x2]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2,y2-i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2,y2-i);
                        if(cw.getPiece(x2,y2-i)!=null){
                            cw.getPiece(x2,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2-i;
                            }
                        }
                    }
                }
            }
        }
        if(move.x1!=-1) {
            return move;
        }
        else{
            return null;
        }
    }

    //Computes all rook moves
    private Move rook(Color c, int x, int y, char[][] board,int x2, int y2, Team white, Team black,int mm,int alpha, int beta, int depth){
        ChessPiece p,p2;
        char[][] b;
        Team cw,cb;
        int eval;
        Move move= new Move();
        if(mm==0){
            move.eval=Integer.MIN_VALUE;
        }
        else{
            move.eval=Integer.MAX_VALUE;
        }
        move.x1=-1;
        if(c.equals(Color.white)) {
            p= white.getPiece(x2,y2);
            p2=white.getPiece(x,y);
            if (x2 > 0) {
                for (int i = 1; x2 - i >= 0; i++) {
                    if(Character.isUpperCase(board[y2][x2-i])||(Character.isLowerCase(board[y2][x2-i+1])&&board[y2][x2-i+1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2-i,y2,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2][x2-i]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2-i,y2);
                        if(cb.getPiece(x2-i,y2)!=null){
                            cb.getPiece(x2-i,y2).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2;
                            }
                        }
                    }
                }

            }
            if(x2<7){
                for(int i=1; x2+i<8; i++){
                    if(Character.isUpperCase(board[y2][x2+i])||(Character.isLowerCase(board[y2][x2+i-1])&&board[y2][x2+i-1]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2+i,y2,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2+i,y2);
                        if(cb.getPiece(x2+i,y2)!=null){
                            cb.getPiece(x2+i,y2).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2;
                            }
                        }
                    }
                }
            }
            if(y2<7){
                for(int i=1; y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2])||(Character.isLowerCase(board[y2+i-1][x2])&&board[y2+i-1][x2]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2,y2+i,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2,y2+i);
                        if(cb.getPiece(x2,y2+i)!=null){
                            cb.getPiece(x2,y2+i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2+i;
                            }
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2+i;
                            }
                        }
                        else if(mm==1) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(y2>0){
                for(int i=1; y2-i>=0; i++){
                    if(Character.isUpperCase(board[y2-i][x2])||(Character.isLowerCase(board[y2-i+1][x2])&&board[y2-i+1][x2]!='x')){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2,y2-i,p,board,x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2]=b[y2][x2];
                        b[y2][x2]='x';
                        cw.getPiece(x2,y2).setNewPos(x2,y2-i);
                        if(cb.getPiece(x2,y2-i)!=null){
                            cb.getPiece(x2,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2-i;
                            }
                        }
                    }
                }
            }
            b=copy(board);
            if(Condition.canCastle(x,y,copy(board),p,p2)){ //fix  also cw.getPiece(x2,y2).setNewPos(x2,y2);
                cw=white.copy();
                cb=black.copy();
                b[y][x]=b[y2][x2];
                b[y2][x2]='K';
                if(mm==0) {
                    eval = min(alpha, beta, b, depth, cw, cb);
                    if(eval> move.eval){
                        move.eval=eval;
                        move.x1=x2;
                        move.y1=y2;
                        move.x2=x;
                        move.y2=y;
                    }
                }
                else if(mm==1) {
                    eval = max(alpha, beta, b, depth, cw, cb);
                    if(eval< move.eval){
                        move.eval=eval;
                        move.x1=x2;
                        move.y1=y2;
                        move.x2=x;
                        move.y2=y;
                    }
                }
            }
        }
        else if(c.equals(Color.black)) {
            p= black.getPiece(x2,y2);
            p2= black.getPiece(x,y);
            if (x2 > 0) {
                for (int i = 1; x2 - i >= 0; i++) {
                    if((Character.isLowerCase(board[y2][x2-i])&&board[y2][x2-i]!='x')||(Character.isUpperCase(board[y2][x2-i+1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2-i,y2,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2][x2-i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2-i,y2);
                        if(cw.getPiece(x2-i,y2)!=null){
                            cw.getPiece(x2-i,y2).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2-i;
                                move.y2=y2;
                            }
                        }
                    }
                }

            }
            if(x2<7){
                for(int i=1; x2+i<8; i++){
                    if((Character.isLowerCase(board[y2][x2+i])&&board[y2][x2+i]!='x')||(Character.isUpperCase(board[y2][x2+i-1]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2+i,y2,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2][x2+i]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2+i,y2);
                        if(cw.getPiece(x2+i,y2)!=null){
                            cw.getPiece(x2+i,y2).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2+i;
                                move.y2=y2;
                            }
                        }
                    }
                }
            }
            if(y2<7){
                for(int i=1; y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2])&&board[y2+i][x2]!='x')||(Character.isUpperCase(board[y2+i-1][x2]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2,y2+i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2+i][x2]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2,y2+i);
                        if(cw.getPiece(x2,y2+i)!=null){
                            cw.getPiece(x2,y2+i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2+i;
                            }
                        }
                        else if(mm==1) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2+i;
                            }
                        }
                    }
                }
            }
            if(y2>0){
                for(int i=1; y2-i>=0; i++){
                    if((Character.isLowerCase(board[y2-i][x2])&&board[y2-i][x2]!='x')||(Character.isUpperCase(board[y2-i+1][x2]))){
                        break;
                    }
                    b=copy(board);
                    if(Condition.crossCheck(x2,y2-i,p,copy(board),x,y)){
                        cw=white.copy();
                        cb=black.copy();
                        b[y2-i][x2]=b[y2][x2];
                        b[y2][x2]='x';
                        cb.getPiece(x2,y2).setNewPos(x2,y2-i);
                        if(cw.getPiece(x2,y2-i)!=null){
                            cw.getPiece(x2,y2-i).died();
                        }
                        if(mm==0) {
                            eval = min(alpha, beta, b, depth, cw, cb);
                            if(eval> move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2-i;
                            }
                        }
                        else if(mm==1) {
                            eval = max(alpha, beta, b, depth, cw, cb);
                            if(eval< move.eval){
                                move.eval=eval;
                                move.x1=x2;
                                move.y1=y2;
                                move.x2=x2;
                                move.y2=y2-i;
                            }
                        }
                    }
                }
            }
            b=copy(board);
            if(Condition.canCastle(x,y,copy(board),p,p2)){// fix castling cb.getPiece(x2,y2).setNewPos(x2,y2);
                cw=white.copy();
                cb=black.copy();
                b[y][x]=b[y2][x2];
                b[y2][x2]='K';

                if(mm==0) {
                    eval = min(alpha, beta, b, depth, cw, cb);
                    if(eval> move.eval){
                        move.eval=eval;
                        move.x1=x2;
                        move.y1=y2;
                        move.x2=x;
                        move.y2=y;
                    }
                }
                else if(mm==1) {
                    eval = max(alpha, beta, b, depth, cw, cb);
                    if(eval< move.eval){
                        move.eval=eval;
                        move.x1=x2;
                        move.y1=y2;
                        move.x2=x;
                        move.y2=y;
                    }
                }
            }
        }
        if(move.x1!=-1) {
            return move;
        }
        else{
            return null;
        }
    }//rook

    //Compute all knight moves
    private Move knight(Color c, int x, int y, char[][] board,int x2, int y2, Team white, Team black,int mm,int alpha, int beta, int depth){
        ChessPiece p;
        char[][] b;
        Team cw,cb;
        int eval;
        Move move= new Move();
        if(mm==0){
            move.eval=Integer.MIN_VALUE;
        }
        else{
            move.eval=Integer.MAX_VALUE;
        }
        move.x1=-1;
        if(c.equals(Color.white)){
            p=white.getPiece(x2,y2);
            b=copy(board);
            if(x2>1&&y2>0&&!Character.isUpperCase(board[y2-1][x2-2])){
                if(Condition.moveKnight(x2-2,y2-1,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-1][x2-2]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2-2,y2-1);
                    if(cb.getPiece(x2-2,y2-1)!=null){
                        cb.getPiece(x2-2,y2-1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-2;
                            move.y2=y2-1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-2;
                            move.y2=y2-1;
                        }
                    }

                }
            }
            b=copy(board);
            if(x2<6&&y2>0&&!Character.isUpperCase(board[y2-1][x2+2])){
                if(Condition.moveKnight(x2+2,y2-1,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-1][x2+2]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2+2,y2-1);
                    if(cb.getPiece(x2+2,y2-1)!=null){
                        cb.getPiece(x2+2,y2-1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+2;
                            move.y2=y2-1;
                        }
                    }
                    else if(mm==1) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+2;
                            move.y2=y2-1;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2>1&&y2<7&&!Character.isUpperCase(board[y2+1][x2-2])){
                if(Condition.moveKnight(x2-2,y2+1,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+1][x2-2]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2-2,y2+1);
                    if(cb.getPiece(x2-2,y2+1)!=null){
                        cb.getPiece(x2-2,y2+1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-2;
                            move.y2=y2+1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-2;
                            move.y2=y2+1;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2<6&&y2<7&&!Character.isUpperCase(board[y2+1][x2+2])){
                if(Condition.moveKnight(x2+2,y2+1,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+1][x2+2]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2+2,y2+1);
                    if(cb.getPiece(x2+2,y2+1)!=null){
                        cb.getPiece(x2+2,y2+1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+2;
                            move.y2=y2+1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+2;
                            move.y2=y2+1;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2>0&&y2>1&&!Character.isUpperCase(board[y2-2][x2-1])){
                if(Condition.moveKnight(x2-1,y2-2,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-2][x2-1]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2-1,y2-2);
                    if(cb.getPiece(x2-1,y2-2)!=null){
                        cb.getPiece(x2-1,y2-2).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2-2;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2-2;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2<7&&y2>1&&!Character.isUpperCase(board[y2-2][x2+1])){
                if(Condition.moveKnight(x2+1,y2-2,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-2][x2+1]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2+1,y2-2);
                    if(cb.getPiece(x2+1,y2-2)!=null){
                        cb.getPiece(x2+1,y2-2).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2-2;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2-2;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2>0&&y2<6&&!Character.isUpperCase(board[y2+2][x2-1])){
                if(Condition.moveKnight(x2-1,y2+2,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+2][x2-1]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2-1,y2+2);
                    if(cb.getPiece(x2-1,y2+2)!=null){
                        cb.getPiece(x2-1,y2+2).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2+2;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2+2;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2<7&&y2<6&&!Character.isUpperCase(board[y2+2][x2+1])){
                if(Condition.moveKnight(x2+1,y2+2,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+2][x2+1]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2+1,y2+2);
                    if(cb.getPiece(x2+1,y2+2)!=null){
                        cb.getPiece(x2+1,y2+2).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2+2;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2+2;
                        }
                    }
                }
            }
        }
        else if(c.equals(Color.black)){
            p=black.getPiece(x2,y2);
            b=copy(board);
            if(x2>1&&y2>0&&((!Character.isLowerCase(board[y2-1][x2-2]))||board[y2-1][x2-2]=='x')){
                if(Condition.moveKnight(x2-2,y2-1,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-1][x2-2]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2-2,y2-1);
                    if(cw.getPiece(x2-2,y2-1)!=null){
                        cw.getPiece(x2-2,y2-1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-2;
                            move.y2=y2-1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-2;
                            move.y2=y2-1;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2<6&&y2>0&&((!Character.isLowerCase(board[y2-1][x2+2]))||board[y2-1][x2+2]=='x')){
                if(Condition.moveKnight(x2+2,y2-1,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-1][x2+2]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2+2,y2-1);
                    if(cw.getPiece(x2+2,y2-1)!=null){
                        cw.getPiece(x2+2,y2-1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+2;
                            move.y2=y2-1;
                        }
                    }
                    else if(mm==1) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+2;
                            move.y2=y2-1;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2>1&&y2<7&&((!Character.isLowerCase(board[y2+1][x2-2]))||board[y2+1][x2-2]=='x')){
                if(Condition.moveKnight(x2-2,y2+1,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+1][x2-2]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2-2,y2+1);
                    if(cw.getPiece(x2-2,y2+1)!=null){
                        cw.getPiece(x2-2,y2+1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-2;
                            move.y2=y2+1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-2;
                            move.y2=y2+1;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2<6&&y2<7&&((!Character.isLowerCase(board[y2+1][x2+2]))||board[y2+1][x2+2]=='x')){
                if(Condition.moveKnight(x2+2,y2+1,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+1][x2+2]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2+2,y2+1);
                    if(cw.getPiece(x2+2,y2+1)!=null){
                        cw.getPiece(x2+2,y2+1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+2;
                            move.y2=y2+1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+2;
                            move.y2=y2+1;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2>0&&y2>1&&((!Character.isLowerCase(board[y2-2][x2-1]))||board[y2-2][x2-1]=='x')){
                if(Condition.moveKnight(x2-1,y2-2,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-2][x2-1]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2-1,y2-2);
                    if(cw.getPiece(x2-1,y2-2)!=null){
                        cw.getPiece(x2-1,y2-2).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2-2;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2-2;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2<7&&y2>1&&((!Character.isLowerCase(board[y2-2][x2+1]))||board[y2-2][x2+1]=='x')){
                if(Condition.moveKnight(x2+1,y2-2,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-2][x2+1]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2+1,y2-2);
                    if(cw.getPiece(x2+1,y2-2)!=null){
                        cw.getPiece(x2+1,y2-2).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2-2;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2-2;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2>0&&y2<6&&((!Character.isLowerCase(board[y2+2][x2-1]))||board[y2+2][x2-1]=='x')){
                if(Condition.moveKnight(x2-1,y2+2,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+2][x2-1]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2-1,y2+2);
                    if(cw.getPiece(x2-1,y2+2)!=null){
                        cw.getPiece(x2-1,y2+2).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2+2;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2+2;
                        }
                    }
                }
            }
            b=copy(board);
            if(x2<7&&y2<6&&((!Character.isLowerCase(board[y2+2][x2+1]))||board[y2+2][x2+1]=='x')){
                if(Condition.moveKnight(x2+1,y2+2,p,copy(board),x,y)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+2][x2+1]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2+1,y2+2);
                    if(cw.getPiece(x2+1,y2+2)!=null){
                        cw.getPiece(x2+1,y2+2).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2+2;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2+2;
                        }
                    }
                }
            }
        }
        if(move.x1!=-1) {
            return move;
        }
        else{
            return null;
        }
    }

    //Compute all pawn moves
    private Move pawn(Color c, int x, int y, char[][] board,int x2, int y2,Team white, Team black,int mm,int alpha, int beta, int depth){

        char[][] b;
        Team cw,cb;
        int eval;
        Move move= new Move();
        if(mm==0){
            move.eval=Integer.MIN_VALUE;
        }
        else{
            move.eval=Integer.MAX_VALUE;
        }
        move.x1=-1;
        if(c.equals(Color.white)) {
            ChessPiece p= white.getPiece(x2,y2);
            b=copy(board);
            if (y2==6&&Character.isLowerCase(board[y2-2][x2])) {
                if (Condition.movePawn(copy(board), p, white, black, x, y, x2 , y2-2)) {
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-2][x2]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2,y2-2);
                    if(cb.getPiece(x2,y2-2)!=null){
                        cb.getPiece(x2,y2-2).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2;
                            move.y2=y2-2;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2;
                            move.y2=y2-2;
                        }
                    }
                }
            }
            b=copy(board);
            if((x2-1>-1&&y2-1>-1&&Character.isLowerCase(board[y2-1][x2-1]))){
                if(Condition.movePawn(copy(board), p, white, black, x, y, x2 - 1, y2-1)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-1][x2-1]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2-1,y2-1);
                    if(cb.getPiece(x2-1,y2-1)!=null){
                        cb.getPiece(x2-1,y2-1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2-1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2-1;
                        }
                    }
                }
            }
            b=copy(board);
            if((x2+1<8&&y2-1>-1&&Character.isLowerCase(board[y2-1][x2+1]))){
                if(Condition.movePawn(copy(board), p, white, black, x, y, x2 + 1, y2-1)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-1][x2+1]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2+1,y2-1);
                    if(cb.getPiece(x2+1,y2-1)!=null){
                        cb.getPiece(x2+1,y2-1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2-1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2-1;
                        }
                    }
                }
            }
            b=copy(board);
            if((y2-1>-1&&board[y2-1][x2]=='x')){
                if(Condition.movePawn(copy(board), p, white, black, x, y, x2, y2-1)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2-1][x2]=b[y2][x2];
                    b[y2][x2]='x';
                    cw.getPiece(x2,y2).setNewPos(x2,y2-1);
                    if(cb.getPiece(x2,y2-1)!=null){
                        cb.getPiece(x2,y2-1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2;
                            move.y2=y2-1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2;
                            move.y2=y2-1;
                        }
                    }
                }
            }
        }
        else if(c.equals(Color.black)) {
            ChessPiece p= black.getPiece(x2,y2);
            b=copy(board);
            if (y2==1&&(Character.isUpperCase(board[y2+2][x2])||board[y2+2][x2]=='x')) {
                if (Condition.movePawn(copy(board), p, white, black, x, y, x2, y2+2)) {
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+2][x2]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2,y2+2);
                    if(cw.getPiece(x2,y2+2)!=null){
                        cw.getPiece(x2,y2+2).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2;
                            move.y2=y2+2;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2;
                            move.y2=y2+2;
                        }
                    }
                }
            }
            b=copy(board);
            if((x2-1>-1&&y2+1<8&&(Character.isUpperCase(board[y2+1][x2-1])||board[y2+1][x2-1]=='x'))){
                if(Condition.movePawn(copy(board), p, white, black, x, y, x2 - 1, y2+1)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+1][x2-1]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2-1,y2+1);
                    if(cw.getPiece(x2-1,y2+1)!=null){
                        cw.getPiece(x2-1,y2+1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2+1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2-1;
                            move.y2=y2+1;
                        }
                    }
                }
            }
            b=copy(board);
            if((x2+1<8&&y2+1<8&&(Character.isUpperCase(board[y2+1][x2+1])||board[y2+1][x2+1]=='x'))){
                if(Condition.movePawn(copy(board), p, white, black, x, y, x2 + 1, y2+1)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+1][x2+1]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2+1,y2+1);
                    if(cw.getPiece(x2+1,y2+1)!=null){
                        cw.getPiece(x2+1,y2+1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2+1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2+1;
                            move.y2=y2+1;
                        }
                    }
                }
            }
            b=copy(board);
            if((y2-1>-1&&board[y2+1][x2]=='x')){
                if(Condition.movePawn(copy(board), p, white, black, x, y, x2, y2+1)){
                    cw=white.copy();
                    cb=black.copy();
                    b[y2+1][x2]=b[y2][x2];
                    b[y2][x2]='x';
                    cb.getPiece(x2,y2).setNewPos(x2,y2+1);
                    if(cw.getPiece(x2,y2+1)!=null){
                        cw.getPiece(x2,y2+1).died();
                    }
                    if(mm==0) {
                        eval = min(alpha, beta, b, depth, cw, cb);
                        if(eval> move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2;
                            move.y2=y2+1;
                        }
                    }
                    else if(mm==1) {
                        eval = max(alpha, beta, b, depth, cw, cb);
                        if(eval< move.eval){
                            move.eval=eval;
                            move.x1=x2;
                            move.y1=y2;
                            move.x2=x2;
                            move.y2=y2+1;
                        }
                    }
                }
            }
        }
        if(move.x1!=-1) {
            return move;
        }
        else{
            return null;
        }
    }
}//ChessAI
