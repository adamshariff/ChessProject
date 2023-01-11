package Model;//package line

import java.awt.*; //Used for colours

/* Title: Conditions
 * Version: 1.0
 * Author: Adam Shariff
 * Last Modified: 19/12/2022
 * Description: This class checks conditions of check checkmate and stalemate
 */
public class Condition {

    /* Title: checkmate
     * Description: Checks for checkmate
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * @param white: holds white team
     * @param black: holds black team
     * @returns weather there is checkmate
     */
    public static boolean checkmate(Color c, int x, int y, char[][]board, Team white, Team black){
        //If king not in check
        if(!check(c,x,y,board)){
            return false;
        }

        //Check is and piece can stop check for white
        if(c.equals(Color.white)){
            for(int j=0; j<8; j++){
                for(int i=0; i<8; i++){
                    if(board[j][i]=='P'){
                        if(pawn(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='B'){
                        if(bishop(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='H'){
                        if(knight(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='R'){
                        if(rook(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='Q'){
                        if(queen(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='K'){
                        if(king(c,x,y,board)){
                            return false;
                        }
                    }
                }
            }
        }
        //Checks if any piece can stop check for black
        else if(c.equals(Color.black)){
            for(int j=0; j<8; j++){
                for(int i=0; i<8; i++){
                    if(board[j][i]=='p'){
                        if(pawn(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='b'){
                        if(bishop(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='h'){
                        if(knight(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='r'){
                        if(rook(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='q'){
                        if(queen(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='k'){
                        if(king(c,x,y,board)){
                            return false;
                        }
                    }
                }
            }
        }

        //Returns checkmate
        return true;
    }//checkmate

    /* Title: stalemate
     * Description: Checks for stalemate
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * @param white: holds white team
     * @param black: holds black team
     * @returns weather there is stalemate
     */
    public static boolean stalemate(Color c, int x, int y, char[][]board, Team white, Team black){

        //Checks if white is in stalemate
        if(c.equals(Color.white)){
            for(int j=0; j<8; j++){
                for(int i=0; i<8; i++){
                    if(board[j][i]=='P'){
                        if(pawn(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='B'){
                        if(bishop(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='H'){
                        if(knight(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='R'){
                        if(rook(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='Q'){
                        if(queen(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='K'){
                        if(king(c,x,y,board)){
                            return false;
                        }
                    }
                }
            }
        }
        //Checks if black is in stalemate
        else if(c.equals(Color.black)){
            for(int j=0; j<8; j++){
                for(int i=0; i<8; i++){
                    if(board[j][i]=='p'){
                        if(pawn(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='b'){
                        if(bishop(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='h'){
                        if(knight(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='r'){
                        if(rook(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='q'){
                        if(queen(c,x,y,board,i,j,white,black)){
                            return false;
                        }
                    }
                    if(board[j][i]=='k'){
                        if(king(c,x,y,board)){
                            return false;
                        }
                    }
                }
            }
        }

        //Returns that there is stalemate
        return true;
    }//stalemate

    /* Title: check
     * Description: Checks for check
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * @returns weather there is check
     */
    public static boolean check(Color c,int x, int y,char[][]board){
        if(pawnCheck(c,x,y,board)){
            return true;
        }
        if(knightCheck(c,x,y,board)){
            return true;
        }
        if(diagonalCheck(c,x,y,board)){
            return true;
        }
        if(crossCheck(c,x,y,board)){
            return true;
        }
        return false;
    }

    /* Title: pawnCheck
     * Description: Checks for pawn check
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * @returns: weather there is pawn check
     */
    private static boolean pawnCheck(Color c,int x, int y,char[][]board){

        if(c.equals(Color.white)){
            if(y>0&&x>0){
                if(board[y-1][x-1]=='p'){
                    return true;
                }
            }
            if(y>0&&x<7){
                if(board[y-1][x+1]=='p'){
                    return true;
                }
            }
        }
        else if(c.equals(Color.black)){
            if(y<8&&x>0){
                if(board[y+1][x-1]=='P'){
                    return true;
                }
            }
            if(y<8&&x<7){
                if(board[y+1][x+1]=='P'){
                    return true;
                }
            }
        }
        return false;
    }//pawnCheck

    /* Title: knightCheck
     * Description: Checks for knight check
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * @returns: weather there is knight check
     */
    private static boolean knightCheck(Color c,int x, int y,char[][]board){
        if(c.equals(Color.white)){
            if(x>1&&y>0){
                if(board[y-1][x-2]=='h'){
                    return true;
                }
            }
            if(x<6&&y>0){
                if(board[y-1][x+2]=='h'){
                    return true;
                }
            }
            if(x>1&&y<7){
                if(board[y+1][x-2]=='h'){
                    return true;
                }
            }
            if(x<6&&y<7){
                if(board[y+1][x+2]=='h'){
                    return true;
                }
            }
            if(x<7&&y>1){
                if(board[y-2][x+1]=='h'){
                    return true;
                }
            }
            if(x>0&&y>1){
                if(board[y-2][x-1]=='h'){
                    return true;
                }
            }
            if(x<7&&y<6){
                if(board[y+2][x+1]=='h'){
                    return true;
                }
            }
            if(x>0&&y<6){
                if(board[y+2][x-1]=='h'){
                    return true;
                }
            }
        }
        else if(c.equals(Color.white)){
            if(x>1&&y>0){
                if(board[y-1][x-2]=='H'){
                    return true;
                }
            }
            if(x<6&&y>0){
                if(board[y-1][x+2]=='H'){
                    return true;
                }
            }
            if(x>1&&y<7){
                if(board[y+1][x-2]=='H'){
                    return true;
                }
            }
            if(x<6&&y<7){
                if(board[y+1][x+2]=='H'){
                    return true;
                }
            }
            if(x<7&&y>1){
                if(board[y-2][x+1]=='H'){
                    return true;
                }
            }
            if(x>0&&y>1){
                if(board[y-2][x-1]=='H'){
                    return true;
                }
            }
            if(x<7&&y<6){
                if(board[y+2][x+1]=='H'){
                    return true;
                }
            }
            if(x>0&&y<6){
                if(board[y+2][x-1]=='H'){
                    return true;
                }
            }
        }
        return false;
    }//knightCheck

    /* Title: diagonalCheck
     * Description: Checks for diagonal check of bishop and queen
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * @returns: weather there is diagonal check
     */
    private static boolean diagonalCheck(Color c,int x, int y,char[][]board){
        int i=1;
        if(c.equals(Color.white)) {
            while (x + i < 8 && y + i < 8) {
                if (board[y + i][x + i] == 'q' || board[y + i][x + i] == 'b') {
                    return true;
                }
                if (board[y + i][x + i] != 'x'&&board[y+i][x+i]!='K') {
                    break;
                }
                i++;
            }
            i = 1;
            while (x - i >= 0 && y - i >= 0) {
                if (board[y - i][x - i] == 'q' || board[y - i][x-i] == 'b') {
                    return true;
                }
                if (board[y - i][x - i] != 'x'&&board[y-i][x-i]!='K') {
                    break;
                }
                i++;
            }
            i = 1;
            while (x - i >= 0 && y + i < 8) {
                if (board[y + i][x - i] == 'q' || board[y + i][x - i] == 'b') {
                    return true;
                }
                if (board[y + i][x - i] != 'x'&&board[y+i][x-i]!='K') {
                    break;
                }
                i++;
            }
            i = 1;
            while (x + i < 8 && y - i >= 0) {
                if (board[y - i][x + i] == 'q' || board[y - i][x + i] == 'b') {
                    return true;
                }
                if (board[y - i][x + i] != 'x'&&board[y-i][x+i]!='K') {
                    break;
                }
                i++;
            }
        }
        else if(c.equals(Color.black)) {
            while (x + i < 8 && y + i < 8) {
                if (board[y + i][x + i] == 'Q' || board[y + i][x + i] == 'B') {
                    return true;
                }
                if (board[y + i][x + i] != 'x'&&board[y+i][x+i]!='k') {
                    break;
                }
                i++;
            }
            i = 1;
            while (x - i >= 0 && y - i >= 0) {
                if (board[y - i][x - i] == 'Q' || board[y - i][x-i] == 'B') {
                    return true;
                }
                if (board[y - i][x - i] != 'x'&&board[y-i][x-i]!='k') {
                    break;
                }
                i++;
            }
            i = 1;
            while (x - i >= 0 && y + i < 8) {
                if (board[y + i][x - i] == 'Q' || board[y + i][x - i] == 'B') {
                    return true;
                }
                if (board[y + i][x - i] != 'x'&&board[y+i][x-i]!='k') {
                    break;
                }
                i++;
            }
            i = 1;
            while (x + i < 8 && y - i >= 0) {
                if (board[y - i][x + i] == 'Q' || board[y - i][x + i] == 'B') {
                    return true;
                }
                if (board[y - i][x + i] != 'x'&&board[y-i][x+i]!='k') {
                    break;
                }
                i++;
            }
        }
        return false;
    }//diagonalCheck

    /* Title: crossCheck
     * Description: Checks for cross check
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * @returns: weather there is cross check
     */
    private static boolean crossCheck(Color c,int x, int y,char[][]board){
        if(c.equals(Color.white)){
            if(x<7){
                for(int i=x; i<8; i++){
                    if(board[y][i]=='q'||board[y][i]=='r'){
                        return true;
                    }
                    if(board[y][i]!='x'&&board[y][i]!='K'){
                        break;
                    }
                }
            }
            if(x>0){
                for(int i=x; i>=0; i--){
                    if(board[y][i]=='q'||board[y][i]=='r'){
                        return true;
                    }
                    if(board[y][i]!='x'&&board[y][i]!='K'){
                        break;
                    }
                }
            }
            if(y<7){
                for(int i=y; i<8; i++){
                    if(board[i][x]=='q'||board[i][x]=='r'){
                        return true;
                    }
                    if(board[i][x]!='x'&&board[i][x]!='K'){
                        break;
                    }
                }
            }
            if(y>0){
                for(int i=y; i>=0; i--){
                    if(board[i][x]=='q'||board[i][x]=='r'){
                        return true;
                    }
                    if(board[i][x]!='x'&&board[i][x]!='K'){
                        break;
                    }
                }
            }
        }
        else if(c.equals(Color.black)){
            if(x<7){
                for(int i=x; i<8; i++){
                    if(board[y][i]=='Q'||board[y][i]=='R'){
                        return true;
                    }
                    if(board[y][i]!='x'&&board[y][i]!='k'){
                        break;
                    }
                }
            }
            if(x>0){
                for(int i=x; i>=0; i--){
                    if(board[y][i]=='Q'||board[y][i]=='R'){
                        return true;
                    }
                    if(board[y][i]!='x'&&board[y][i]!='k'){
                        break;
                    }
                }
            }
            if(y<7){
                for(int i=y; i<8; i++){
                    if(board[i][x]=='Q'||board[i][x]=='R'){
                        return true;
                    }
                    if(board[i][x]!='x'&&board[i][x]!='k'){
                        break;
                    }
                }
            }
            if(y>0){
                for(int i=y; i>=0; i--){
                    if(board[i][x]=='Q'||board[i][x]=='R'){
                        return true;
                    }
                    if(board[i][x]!='x'&&board[i][x]!='k'){
                        break;
                    }
                }
            }
        }
        return false;
    }

    /* Title: pawn
     * Description: Checks if pawn can move to stop checkmate
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * x2: pawn x
     * y2: pawn y
     * @param white: holds white team
     * @param black: holds black team
     * @returns weather there is checkmate
     */
    private static boolean pawn(Color c, int x, int y, char[][] board,int x2, int y2,Team white, Team black){

        if(c.equals(Color.white)) {
            ChessPiece p= white.getPiece(x2,y2);
            if (y2==6&&Character.isLowerCase(board[y2-2][x2])) {
                if (movePawn(copy(board), p, white, black, x, y, x2 , y2-2)) {
                    return true;
                }
            }
            if((x2-1>-1&&y2-1>-1&&Character.isLowerCase(board[y2-1][x2-1]))){
                if(movePawn(copy(board), p, white, black, x, y, x2 - 1, y2-1)){
                    return true;
                }
            }
            if((x2+1<8&&y2-1>-1&&Character.isLowerCase(board[y2-1][x2+1]))){
                if(movePawn(copy(board), p, white, black, x, y, x2 + 1, y2-1)){
                    return true;
                }
            }
            if((y2-1>-1&&board[y2-1][x2]=='x')){
                if(movePawn(copy(board), p, white, black, x, y, x2, y2-1)){
                    return true;
                }
            }
        }
        else if(c.equals(Color.black)) {
            ChessPiece p= black.getPiece(x2,y2);
            if (y2==1&&(Character.isUpperCase(board[y2+2][x2])||board[y2+2][x2]=='x')) {
                if (movePawn(copy(board), p, white, black, x, y, x2, y2+2)) {
                    return true;
                }
            }
            if((x2-1>-1&&y2+1<8&&(Character.isUpperCase(board[y2+1][x2-1])||board[y2+1][x2-1]=='x'))){
                if(movePawn(copy(board), p, white, black, x, y, x2 - 1, y2+1)){
                    return true;
                }
            }
            if((x2+1<8&&y2+1<8&&(Character.isUpperCase(board[y2+1][x2+1])||board[y2+1][x2+1]=='x'))){
                if(movePawn(copy(board), p, white, black, x, y, x2 + 1, y2+1)){
                    return true;
                }
            }
            if((y2-1>-1&&board[y2+1][x2]=='x')){
                if(movePawn(copy(board), p, white, black, x, y, x2, y2+1)){
                    return true;
                }
            }
        }
        return false;
    }

    private static char[][] copy(char[][] b){
        char [][]board = new char[8][8];
        for(int j=0; j<8; j++){
            for(int i=0; i<8; i++){
                board[j][i]=b[j][i];
            }
        }
        return board;
    }


    public static boolean movePawn(char[][] board,ChessPiece piece,Team white, Team black,int x2, int y2,int x, int y){
        ChessPiece p;
        if(piece.getColour().equals(Color.white)){
            if((x==piece.getX()&&y==piece.getY()-1&&board[y][x]=='x')||(x==piece.getX()&&y==piece.getY()-2&&board[y][x]=='x'&& !piece.moved()&&board[y+1][x]=='x')
                    ||((x==piece.getX()-1&&y==piece.getY()-1&&board[y][x]!='x'||x==piece.getX()+1&&y==piece.getY()-1&&board[y][x]!='x') &&Character.isLowerCase(board[y][x]))
                    ||board[y+1][x]=='p'){
                if(y==piece.getY()-2){
                    piece.setEnPassant();
                }
                p=black.getPiece(x,y+1);
                if(p!=null&&board[y][x]=='x') {
                    if (board[y+1][x]== 'p' && !p.getEnPassant()) {
                        return false;
                    }
                }
                board[piece.getY()][piece.getX()]='x';
                board[y][x]='P';
                if(check(Color.white,x2,y2,board)){
                    return false;
                }
                return true;
            }
        }
        else if(piece.getColour().equals(Color.black)){
            if((x==piece.getX()&&y==piece.getY()+1&&board[y][x]=='x')||(x==piece.getX()&&y==piece.getY()+2&&board[y][x]=='x'&& !piece.moved()&&board[y-1][x]=='x')
                    ||((x==piece.getX()-1&&y==piece.getY()+1&&board[y][x]!='x'||x==piece.getX()+1&&y==piece.getY()+1&&board[y][x]!='x') &&Character.isUpperCase(board[y][x]))
                    ||board[y-1][x]=='P'){
                if(y==piece.getY()+2){
                    piece.setEnPassant();
                }
                p=white.getPiece(x,y-1);
                if(p!=null&&board[y][x]=='x') {
                    if (board[y-1][x] == 'P' && !p.getEnPassant()) {
                        return false;
                    }
                }
                board[piece.getY()][piece.getX()]='x';
                board[y][x]='p';
                if(check(Color.black,x2,y2,board)){
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    //For testing
    public static void displayBoard(char[][]b){
        for(int j=0; j<8; j++){
            for(int i=0; i<8; i++){
                System.out.print(b[j][i]);
            }//loop
            System.out.println("");
        }//loop
    }

    /* Title: bishop
     * Description: Checks if bishop can move to stop checkmate
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * x2: piece x
     * y2: piece y
     * @param white: holds white team
     * @param black: holds black team
     * @returns weather can stop check mate
     */
    private static boolean bishop(Color c, int x, int y, char[][] board,int x2, int y2, Team white, Team black){
        ChessPiece p;
        if(c.equals(Color.white)) {
            p=white.getPiece(x2,y2);
            if (x2 > 0 && y2 > 0) {
                for (int i = 1; x2 - i >= 0 && y2 - i >= 0; i++) {
                    if(Character.isUpperCase(board[y2-i][x2-i])||(Character.isLowerCase(board[y2-i+1][x2-i+1])&&board[y2-i+1][x2-i+1]!='x')){
                        break;
                    }
                    if(diagonalCheck(x2-i,y2-i,p,board,x,y)){
                        return true;
                    }
                }

            }
            if(x2<7&&y2<7){
                for(int i=1; x2+i<8&&y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2+i])||(Character.isLowerCase(board[y2+i-1][x2+i-1])&&board[y2+i-1][x2+i-1]!='x')){
                        break;
                    }
                    if(diagonalCheck(x2+i,y2+i,p,board,x,y)){
                        return true;
                    }
                }
            }
            if(x2>0&&y2<7){
                for(int i=1; x2-i>=0&&y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2-i])||(Character.isLowerCase(board[y2+i-1][x2-i+1])&&board[y2+i-1][x2-i+1]!='x')){
                        break;
                    }
                    if(diagonalCheck(x2-i,y2+i,p,board,x,y)){
                        return true;
                    }
                }
            }
            if(x2<7&&y2>0){
                for(int i=1; x2+i<8&&y2-i>=0; i++){
                    if(Character.isUpperCase(board[y2-i][x2+i])||(Character.isLowerCase(board[y2-i+1][x2+i-1])&&board[y2-i+1][x2+i-1]!='x')){
                        break;
                    }
                    if(diagonalCheck(x2+i,y2-i,p,board,x,y)){
                        return true;
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
                    if(diagonalCheck(x2-i,y2-i,p,copy(board),x,y)){
                        return true;
                    }
                }

            }
            if(x2<7&&y2<7){
                for(int i=1; x2+i<8&&y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2+i])&&board[y2+i][x2+i]!='x')||(Character.isUpperCase(board[y2+i-1][x2+i-1]))){
                        break;
                    }
                    if(diagonalCheck(x2+i,y2+i,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
            if(x2>0&&y2<7){
                for(int i=1; x2-i>=0&&y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2-i])&&board[y2+i][x2-i]!='x')||(Character.isUpperCase(board[y2+i-1][x2-i+1]))){
                        break;
                    }
                    if(diagonalCheck(x2-i,y2+i,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
            if(x2<7&&y2>0){
                for(int i=1; x2+i<8&&y2-i>=0; i++){
                    if((Character.isLowerCase(board[y2-i][x2+i])&&board[y2-i][x2+i]!='x')||(Character.isUpperCase(board[y2-i+1][x2+i-1]))){
                        break;
                    }
                    if(diagonalCheck(x2+i,y2-i,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean diagonalCheck(int x, int y,ChessPiece piece, char board[][],int x2,int y2){
        if(y-piece.getY()<0&&x-piece.getX()<0){
            for(int i=1; i+x<piece.getX(); i++){
                if(board[y+i][x+i]!='x'){
                    return false;
                }
            }
        }
        else if(y-piece.getY()>0&&x-piece.getX()>0){
            for(int i=1; x-i>piece.getX(); i++){
                if(board[y-i][x-i]!='x'){
                    return false;
                }
            }
        }
        else if(y-piece.getY()<0&&x-piece.getX()>0){
            System.out.println("i wanna");
            for(int i=1; x-i>piece.getX(); i++){
                System.out.println(board[y+i][x-i]);
                if(board[y+i][x-i]!='x'){
                    return false;
                }
            }
        }
        else if(y-piece.getY()>0&&x-piece.getX()<0){
            for(int i=1; i+x<piece.getX(); i++){
                if(board[y-i][x+i]!='x'){
                    return false;
                }
            }
        }
        board[piece.getY()][piece.getX()]='x';
        if(piece.getColour().equals(Color.white)){
            board[y][x]='B';
            if(check(Color.white,x2,y2,board)){
                return false;
            }
        }
        else{
            board[y][x]='b';
            if(check(Color.black,x2,y2,board)){
                return false;
            }
        }
        return true;
    }

    /* Title: knight
     * Description: Checks if kniht can move to stop checkmate
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * x2: piece x
     * y2: piece y
     * @param white: holds white team
     * @param black: holds black team
     * @returns weather can stop check mate
     */
    private static boolean knight(Color c, int x, int y, char[][] board,int x2, int y2, Team white, Team black){
        ChessPiece p;
        if(c.equals(Color.white)){
            p=white.getPiece(x2,y2);
            if(x2>1&&y2>0&&!Character.isUpperCase(board[y2-1][x2-2])){
                if(moveKnight(x2-2,y2-1,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2<6&&y2>0&&!Character.isUpperCase(board[y2-1][x2+2])){
                if(moveKnight(x2+2,y2-1,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2>1&&y2<7&&!Character.isUpperCase(board[y2+1][x2-2])){
                if(moveKnight(x2-2,y2+1,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2<6&&y2<7&&!Character.isUpperCase(board[y2+1][x2+2])){
                if(moveKnight(x2+2,y2+1,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2>0&&y2>1&&!Character.isUpperCase(board[y2-2][x2-1])){
                if(moveKnight(x2-1,y2-2,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2<7&&y2>1&&!Character.isUpperCase(board[y2-2][x2+1])){
                if(moveKnight(x2+1,y2-2,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2>0&&y2<6&&!Character.isUpperCase(board[y2+2][x2-1])){
                if(moveKnight(x2-1,y2+2,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2<7&&y2<6&&!Character.isUpperCase(board[y2+2][x2+1])){
                if(moveKnight(x2+1,y2+2,p,copy(board),x,y)){
                    return true;
                }
            }
        }
        else if(c.equals(Color.black)){
            p=black.getPiece(x2,y2);
            if(x2>1&&y2>0&&((!Character.isLowerCase(board[y2-1][x2-2]))||board[y2-1][x2-2]=='x')){
                if(moveKnight(x2-2,y2-1,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2<6&&y2>0&&((!Character.isLowerCase(board[y2-1][x2+2]))||board[y2-1][x2+2]=='x')){
                if(moveKnight(x2+2,y2-1,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2>1&&y2<7&&((!Character.isLowerCase(board[y2+1][x2-2]))||board[y2+1][x2-2]=='x')){
                if(moveKnight(x2-2,y2+1,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2<6&&y2<7&&((!Character.isLowerCase(board[y2+1][x2+2]))||board[y2+1][x2+2]=='x')){
                if(moveKnight(x2+2,y2+1,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2>0&&y2>1&&((!Character.isLowerCase(board[y2-2][x2-1]))||board[y2-2][x2-1]=='x')){
                if(moveKnight(x2-1,y2-2,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2<7&&y2>1&&((!Character.isLowerCase(board[y2-2][x2+1]))||board[y2-2][x2+1]=='x')){
                if(moveKnight(x2+1,y2-2,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2>0&&y2<6&&((!Character.isLowerCase(board[y2+2][x2-1]))||board[y2+2][x2-1]=='x')){
                if(moveKnight(x2-1,y2+2,p,copy(board),x,y)){
                    return true;
                }
            }
            if(x2<7&&y2<6&&((!Character.isLowerCase(board[y2+2][x2+1]))||board[y2+2][x2+1]=='x')){
                if(moveKnight(x2+1,y2+2,p,copy(board),x,y)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean moveKnight(int x, int y, ChessPiece piece, char board[][], int x2, int y2){
        if(((y-piece.getY()==-2||y-piece.getY()==2)&&(x-piece.getX()==1||x-piece.getX()==-1))||((y-piece.getY()==-1||y-piece.getY()==1)&&(x-piece.getX()==2||x-piece.getX()==-2))){
            if(check(piece.getColour(),x2,y2,board)){
                return false;
            }
            return true;
        }
        return false;
    }

    /* Title: rook
     * Description: Checks if rook can move to stop checkmate
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * x2: piece x
     * y2: piece y
     * @param white: holds white team
     * @param black: holds black team
     * @returns weather can stop check mate
     */
    private static boolean rook(Color c, int x, int y, char[][] board,int x2, int y2, Team white, Team black){
        ChessPiece p,p2;
        if(c.equals(Color.white)) {
            p= white.getPiece(x2,y2);
            p2=white.getPiece(x,y);
            if (x2 > 0) {
                for (int i = 1; x2 - i >= 0; i++) {
                    if(Character.isUpperCase(board[y2][x2-i])||(Character.isLowerCase(board[y2][x2-i+1])&&board[y2][x2-i+1]!='x')){
                        break;
                    }
                    if(crossCheck(x2-i,y2,p,board,x,y)){
                        return true;
                    }
                }

            }
            if(x2<7){
                for(int i=1; x2+i<8; i++){
                    if(Character.isUpperCase(board[y2][x2+i])||(Character.isLowerCase(board[y2][x2+i-1])&&board[y2][x2+i-1]!='x')){
                        break;
                    }
                    if(crossCheck(x2+i,y2,p,board,x,y)){
                        return true;
                    }
                }
            }
            if(y2<7){
                for(int i=1; y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2])||(Character.isLowerCase(board[y2+i-1][x2])&&board[y2+i-1][x2]!='x')){
                        break;
                    }
                    if(crossCheck(x2,y2+i,p,board,x,y)){
                        return true;
                    }
                }
            }
            if(y2>0){
                for(int i=1; y2-i>=0; i++){
                    if(Character.isUpperCase(board[y2-i][x2])||(Character.isLowerCase(board[y2-i+1][x2])&&board[y2-i+1][x2]!='x')){
                        break;
                    }
                    if(crossCheck(x2,y2-i,p,board,x,y)){
                        return true;
                    }
                }
            }
            if(canCastle(x,y,copy(board),p,p2)){
                return true;
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
                    if(crossCheck(x2-i,y2,p,copy(board),x,y)){
                        return true;
                    }
                }

            }
            if(x2<7){
                for(int i=1; x2+i<8; i++){
                    if((Character.isLowerCase(board[y2][x2+i])&&board[y2][x2+i]!='x')||(Character.isUpperCase(board[y2][x2+i-1]))){
                        break;
                    }
                    if(crossCheck(x2+i,y2,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
            if(y2<7){
                for(int i=1; y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2])&&board[y2+i][x2]!='x')||(Character.isUpperCase(board[y2+i-1][x2]))){
                        break;
                    }
                    if(crossCheck(x2,y2+i,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
            if(y2>0){
                for(int i=1; y2-i>=0; i++){
                    if((Character.isLowerCase(board[y2-i][x2])&&board[y2-i][x2]!='x')||(Character.isUpperCase(board[y2-i+1][x2]))){
                        break;
                    }
                    if(crossCheck(x2,y2-i,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
            if(canCastle(x,y,copy(board),p,p2)){
                return true;
            }
        }
        return false;
    }

    public static boolean crossCheck(int x, int y,ChessPiece piece, char board[][],int x2,int y2){
        if(y!=piece.getY()&&x==piece.getX()&&y-piece.getY()<0){
            for(int i=1; i+y<piece.getY(); i++){
                if(board[y+i][x]!='x'){
                    return false;
                }
            }
        }
        else if(y!=piece.getY()&&x==piece.getX()&&y-piece.getY()>0){
            for(int i=1; y-i>piece.getY(); i++){
                if(board[y-i][x]!='x'){
                    return false;
                }
            }
        }
        else if(x!=piece.getX()&&y==piece.getY()&&x-piece.getX()>0){
            for(int i=1; x-i>piece.getX(); i++){
                if(board[y][x-i]!='x'){
                    return false;
                }
            }
        }
        else if(x!=piece.getX()&&y==piece.getY()&&x-piece.getX()<0){
            for(int i=1; x+i<piece.getX(); i++){
                if(board[y][x+i]!='x'){
                    return false;
                }
            }
        }
        board[piece.getY()][piece.getX()]='x';
        if(piece.getColour().equals(Color.white)){
            board[y][x]='B';
            if(check(Color.white,x2,y2,board)){
                return false;
            }
        }
        else{
            board[y][x]='b';
            if(check(Color.black,x2,y2,board)){
                return false;
            }
        }
        return true;
    }

    /* Title: queen
     * Description: Checks if queen can move to stop checkmate
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * x2: piece x
     * y2: piece y
     * @param white: holds white team
     * @param black: holds black team
     * @returns weather can stop check mate
     */
    private static boolean queen(Color c, int x, int y, char[][] board,int x2, int y2, Team white, Team black){
        ChessPiece p;
        if(c.equals(Color.white)) {
            p= white.getPiece(x2,y2);
            if (x2 > 0 && y2 > 0) {
                for (int i = 1; x2 - i >= 0 && y2 - i >= 0; i++) {
                    if(Character.isUpperCase(board[y2-i][x2-i])||(Character.isLowerCase(board[y2-i+1][x2-i+1])&&board[y2-i+1][x2-i+1]!='x')){
                        break;
                    }
                    if(diagonalCheck(x2-i,y2-i,p,board,x,y)){
                        return true;
                    }
                }

            }
            if(x2<7&&y2<7){
                for(int i=1; x2+i<8&&y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2+i])||(Character.isLowerCase(board[y2+i-1][x2+i-1])&&board[y2+i-1][x2+i-1]!='x')){
                        break;
                    }
                    if(diagonalCheck(x2+i,y2+i,p,board,x,y)){
                        return true;
                    }
                }
            }
            if(x2>0&&y2<7){
                for(int i=1; x2-i>=0&&y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2-i])||(Character.isLowerCase(board[y2+i-1][x2-i+1])&&board[y2+i-1][x2-i+1]!='x')){
                        break;
                    }
                    if(diagonalCheck(x2-i,y2+i,p,board,x,y)){
                        return true;
                    }
                }
            }
            if(x2<7&&y2>0){
                for(int i=1; x2+i<8&&y2-i>=0; i++){
                    if(Character.isUpperCase(board[y2-i][x2+i])||(Character.isLowerCase(board[y2-i+1][x2+i-1])&&board[y2-i+1][x2+i-1]!='x')){
                        break;
                    }
                    if(diagonalCheck(x2+i,y2-i,p,board,x,y)){
                        return true;
                    }
                }
            }
            if (x2 > 0) {
                for (int i = 1; x2 - i >= 0; i++) {
                    if(Character.isUpperCase(board[y2][x2-i])||(Character.isLowerCase(board[y2][x2-i+1])&&board[y2][x2-i+1]!='x')){
                        break;
                    }
                    if(crossCheck(x2-i,y2,p,board,x,y)){
                        return true;
                    }
                }

            }
            if(x2<7){
                for(int i=1; x2+i<8; i++){
                    if(Character.isUpperCase(board[y2][x2+i])||(Character.isLowerCase(board[y2][x2+i-1])&&board[y2][x2+i-1]!='x')){
                        break;
                    }
                    if(crossCheck(x2+i,y2,p,board,x,y)){
                        return true;
                    }
                }
            }
            if(y2<7){
                for(int i=1; y2+i<8; i++){
                    if(Character.isUpperCase(board[y2+i][x2])||(Character.isLowerCase(board[y2+i-1][x2])&&board[y2+i-1][x2]!='x')){
                        break;
                    }
                    if(crossCheck(x2,y2+i,p,board,x,y)){
                        return true;
                    }
                }
            }
            if(y2>0){
                for(int i=1; y2-i>=0; i++){
                    if(Character.isUpperCase(board[y2-i][x2])||(Character.isLowerCase(board[y2-i+1][x2])&&board[y2-i+1][x2]!='x')){
                        break;
                    }
                    if(crossCheck(x2,y2-i,p,board,x,y)){
                        return true;
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
                    if(diagonalCheck(x2-i,y2-i,p,copy(board),x,y)){
                        return true;
                    }
                }

            }
            if(x2<7&&y2<7){
                for(int i=1; x2+i<8&&y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2+i])&&board[y2+i][x2+i]!='x')||(Character.isUpperCase(board[y2+i-1][x2+i-1]))){
                        break;
                    }
                    if(diagonalCheck(x2+i,y2+i,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
            if(x2>0&&y2<7){
                for(int i=1; x2-i>=0&&y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2-i])&&board[y2+i][x2-i]!='x')||(Character.isUpperCase(board[y2+i-1][x2-i+1]))){
                        break;
                    }
                    if(diagonalCheck(x2-i,y2+i,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
            if(x2<7&&y2>0){
                for(int i=1; x2+i<8&&y2-i>=0; i++){
                    if((Character.isLowerCase(board[y2-i][x2+i])&&board[y2-i][x2+i]!='x')||(Character.isUpperCase(board[y2-i+1][x2+i-1]))){
                        break;
                    }
                    if(diagonalCheck(x2+i,y2-i,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
            if (x2 > 0) {
                for (int i = 1; x2 - i >= 0; i++) {
                    if((Character.isLowerCase(board[y2][x2-i])&&board[y2][x2-i]!='x')||(Character.isUpperCase(board[y2][x2-i+1]))){
                        break;
                    }
                    if(crossCheck(x2-i,y2,p,copy(board),x,y)){
                        return true;
                    }
                }

            }
            if(x2<7){
                for(int i=1; x2+i<8; i++){
                    if((Character.isLowerCase(board[y2][x2+i])&&board[y2][x2+i]!='x')||(Character.isUpperCase(board[y2][x2+i-1]))){
                        break;
                    }
                    if(crossCheck(x2+i,y2,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
            if(y2<7){
                for(int i=1; y2+i<8; i++){
                    if((Character.isLowerCase(board[y2+i][x2])&&board[y2+i][x2]!='x')||(Character.isUpperCase(board[y2+i-1][x2]))){
                        break;
                    }
                    if(crossCheck(x2,y2+i,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
            if(y2>0){
                for(int i=1; y2-i>=0; i++){
                    if((Character.isLowerCase(board[y2-i][x2])&&board[y2-i][x2]!='x')||(Character.isUpperCase(board[y2-i+1][x2]))){
                        break;
                    }
                    if(crossCheck(x2,y2-i,p,copy(board),x,y)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* Title: castle
     * Description: Checks if castle can move to stop checkmate
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * x2: piece x
     * y2: piece y
     * @param piece: holds one castle piece
     * @param black: holds another castle piece
     * @returns weather can stop check mate
     */
    public static boolean canCastle(int x,int y, char[][] board, ChessPiece piece, ChessPiece piece2){
        char t;
        if(piece.getColour().equals(Color.white)){
            if(!piece.moved()&& !piece2.moved()){
                if((piece2.getX()>piece.getX()&&piece.getName().equals("king"))||(piece.getX()>piece2.getX()&&piece2.getName().equals("king"))){
                    if(board[7][5]=='x'&&board[7][6]=='x'){
                        t=board[7][4];
                        board[7][4]=board[7][7];
                        board[7][7]=t;
                        if(check(Color.white,x,y,board)){
                            return false;
                        }
                        return true;
                    }
                }
                else{
                    if(board[7][1]=='x'&&board[7][2]=='x'&&board[7][3]=='x'){
                        t=board[7][4];
                        board[7][4]=board[7][0];
                        board[7][0]=t;
                        if(check(Color.white,x,y,board)){
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
                    if(board[0][5]=='x'&&board[0][6]=='x'){
                        t=board[0][4];
                        board[0][4]=board[0][7];
                        board[0][7]=t;
                        if(check(Color.white,x,y,board)){
                            return false;
                        }
                        return true;
                    }
                }
                else{
                    if(board[0][1]=='x'&&board[0][2]=='x'&&board[0][3]=='x'){
                        if(board[7][1]=='x'&&board[7][2]=='x') {
                            t = board[0][4];
                            board[0][4] = board[0][0];
                            board[0][0] = t;
                            if (check(Color.white, x, y, board)) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean moveKing(int x,int y,int x2, int y2,Color c,char[][]board){
        if(c.equals(Color.white)){
            if(Character.isUpperCase(board[y][x])){
                return false;
            }
            board[y][x]='K';
        }
        else{
            if(Character.isLowerCase(board[y][x])&&board[y][x]!='x'){
                return false;
            }
            board[y][x]='k';
        }
        board[x2][y2]='x';
        if(check(c,x,y,board)){
            return false;
        }
        return true;
    }

    /* Title: bishop
     * Description: Checks if king can move to stop checkmate
     * @param c: Color to look at
     * @param x: x of king
     * @param y: y of king
     * @param board: holds board to check
     * @returns weather can stop check mate
     */
    private static boolean king(Color c, int x, int y, char[][] board){

        if (x > 0) {
            if(moveKing(x-1,y,x,y,c,copy(board))){
                return true;
            }
        }
        if (x < 7) {
            if(moveKing(x+1,y,x,y,c,copy(board))){
                return true;
            }
        }
        if (y > 0) {
            if(moveKing(x,y-1,x,y,c,copy(board))){
                return true;
            }
        }
        if (y < 7) {
            if(moveKing(x,y+1,x,y,c,copy(board))){
                return true;
            }
        }
        if (y < 7&&x<7) {
            if(moveKing(x+1,y+1,x,y,c,copy(board))){
                return true;
            }
        }
        if (y < 7&&x>0) {
            if(moveKing(x-1,y+1,x,y,c,copy(board))){
                return true;
            }
        }
        if (y > 0&&x<7) {
            if(moveKing(x+1,y-1,x,y,c,copy(board))){
                return true;
            }
        }
        if (y > 0&&x>0) {
            if(moveKing(x-1,y-1,x,y,c,copy(board))){
                return true;
            }
        }
        return false;
    }
}//Condition
