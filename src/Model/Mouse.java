package Model;//package line

//imports
import Graphics.Gui;                 //Graphics classes
import java.awt.*;                   //Colour components
import java.awt.event.MouseEvent;    //Mouse clicking
import java.awt.event.MouseListener; //Mouse listening

/* Title: Mouse
 * Version: 1.0
 * Author: Adam Shariff
 * Last Modified: 19/12/2022
 * Description: This class track mouse clicks and locations. Implements mouse listener
 */

public class Mouse implements MouseListener {

    private final Model m;   //Model interfacing
    private final Gui g;     //Graphic interfacing
    private int w;           //Holds width of screen
    private int h;           //Holds height of screen
    private int px;          //Holds piece x
    private int py;          //Holds piece y
    private char prev=' ';   //Holds previous piece char
    private int ppx;         //Holds previous piece x
    private int ppy;         //Holds previous piece y
    private char p=' ';      //Holds piece char
    private String l="King"; //Used for loading

    //Constructor: Sets up values
    public Mouse(Model m, Gui g){
        this.m=m;
        this.g=g;
    }//constructor


    //Reacts to mouse clicked events
    @Override
    public void mouseClicked(MouseEvent e) {

        int x=e.getX();
        int y=e.getY();
        w=g.getWidth();
        h=g.getHeight();

        //Actions based on phase
        if(m.getPhase()==2){ //game phase
            whiteSelect(x,y);
        }
        else if(m.getPhase()==3){ //game phase
            blackSelect(x,y);
        }
        else if(m.getPhase()==-2||m.getPhase()==-3){ //loading phase

            if(8*w/9<=x&&x<w) {
                loadSelect(x, y);
                System.out.println(l);
            }
            else{
                getSpace2(x,y);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /* Title: blackSelect
     * Description: Sees is mouse selected black piece
     * @param x: x space
     * @param y: y space
     */
    private void blackSelect(int x, int y){
        prev=p;
        ppx=px;
        ppy=py;
        p=getSpace(x,y);

        //Checks promotion event occurred
        if(m.getPromotion()){
            proSel(x,y);
            if(Condition.checkmate(Color.black,m.getPieceX(Color.black,4), m.getPieceY(Color.black,4), m.copy(), m.getWhite(), m.getBlack())){
                m.whiteWins();
                m.setPhase(4);
            }
            else if(Condition.stalemate(Color.black,m.getPieceX(Color.black,4), m.getPieceY(Color.black,4), m.copy(), m.getWhite(), m.getBlack())){
                m.stalemate();
                m.setPhase(4);
            }
            return;
        }
        //Checking for castling
        if(p!='i'&&Character.isLowerCase(p)&&((p=='k'&&prev=='r')||(p=='r'&&prev=='k'))){
            if(m.canCastle(ppx,ppy)){
                m.castle();
                m.setPhase(2);
                if(Condition.checkmate(Color.white,m.getPieceX(Color.white,3), m.getPieceY(Color.white,3), m.copy(), m.getWhite(), m.getBlack())){
                    m.blackWins();
                    m.setPhase(4);
                }
                else if(Condition.stalemate(Color.white,m.getPieceX(Color.white,3), m.getPieceY(Color.white,3), m.copy(), m.getWhite(), m.getBlack())){
                    m.stalemate();
                    m.setPhase(4);
                }
                return;
            }
        }
        //Check if selected black piece
        if(p!='i'&&Character.isLowerCase(p)&&p!='x'){
        }
        else if(p!='i'&&m.getPiece()!=null){
            if(m.canMove(px,py)) {
                m.capture(px,py);
                m.movePiece(px, py);
                m.promotion();
                if(Condition.checkmate(Color.white,m.getPieceX(Color.white,3), m.getPieceY(Color.white,3), m.copy(), m.getWhite(), m.getBlack())){
                    m.blackWins();
                    m.setPhase(4);
                }
                else if(Condition.stalemate(Color.white,m.getPieceX(Color.white,3), m.getPieceY(Color.white,3), m.copy(), m.getWhite(), m.getBlack())){
                    m.stalemate();
                    m.setPhase(4);
                }
                m.setPhase(2);
            }
        }
    }

    /* Title: whiteSelect
     * Description: Sees is mouse selected white piece
     * @param x: x space
     * @param y: y space
     */
    private void whiteSelect(int x, int y){
        prev=p;
        ppx=px;
        ppy=py;
        p=getSpace(x,y);

        //Checks promotion event occurred
        if(m.getPromotion()){
            proSel(x,y);
            if(Condition.checkmate(Color.white,m.getPieceX(Color.white,3), m.getPieceY(Color.white,3), m.copy(), m.getWhite(), m.getBlack())){
                m.blackWins();
                m.setPhase(4);
            }
            else if(Condition.stalemate(Color.white,m.getPieceX(Color.white,3), m.getPieceY(Color.white,3), m.copy(), m.getWhite(), m.getBlack())){
                m.stalemate();
                m.setPhase(4);
            }
            return;
        }

        //Checking for castling
        if(p!='i'&&Character.isUpperCase(p)&&((p=='K'&&prev=='R')||(p=='R'&&prev=='K'))){
            if(m.canCastle(ppx,ppy)){
                m.castle();
                m.setPhase(3);
                if(Condition.checkmate(Color.black,m.getPieceX(Color.black,4), m.getPieceY(Color.black,4), m.copy(), m.getWhite(), m.getBlack())){
                    m.whiteWins();
                    m.setPhase(4);
                }
                else if(Condition.stalemate(Color.black,m.getPieceX(Color.black,4), m.getPieceY(Color.black,4), m.copy(), m.getWhite(), m.getBlack())){
                    m.stalemate();
                    m.setPhase(4);
                }
                return;
            }
        }
        //Check if selected white piece
        if(p!='i'&&Character.isUpperCase(p)){
        }
        else if(p!='i'&&m.getPiece()!=null){
            if(m.canMove(px,py)) {
                m.capture(px,py);
                m.movePiece(px, py);
                m.promotion();
                m.copy();
                m.getPieceX(Color.white,3);
                if(Condition.checkmate(Color.black,m.getPieceX(Color.black,4), m.getPieceY(Color.black,4), m.copy(), m.getWhite(), m.getBlack())){
                    m.whiteWins();
                    m.setPhase(4);
                }
                else if(Condition.stalemate(Color.black,m.getPieceX(Color.black,4), m.getPieceY(Color.black,4), m.copy(), m.getWhite(), m.getBlack())){
                    m.stalemate();
                    m.setPhase(4);
                }
                m.setPhase(3);
            }
        }
    }//whiteSelect

    /* Title: getSpace
     * Description: gets what is at space on game board and returns it
     * @param x: x space
     * @param y: y space
     * @return: Returns char of piece selected
     */
    private char getSpace(int x,int y){
        char p;
        if((x>=0&&x<w/9)&&(y>=0&&y<h/8)){              //row 1
            p= m.getSpace(0,0);
            m.setPiece(0,0);
            System.out.println(p);
            px=0;
            py=0;
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=0&&y<h/8)){
            p= m.getSpace(1,0);
            m.setPiece(1,0);
            System.out.println(p);
            px=1;
            py=0;
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=0&&y<h/8)){
            p= m.getSpace(2,0);
            m.setPiece(2,0);
            System.out.println(p);
            px=2;
            py=0;
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=0&&y<h/8)){
            p= m.getSpace(3,0);
            m.setPiece(3,0);
            System.out.println(p);
            px=3;
            py=0;
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=0&&y<h/8)){
            p= m.getSpace(4,0);
            m.setPiece(4,0);
            System.out.println(p);
            px=4;
            py=0;
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=0&&y<h/8)){
            p= m.getSpace(5,0);
            m.setPiece(5,0);
            System.out.println(p);
            px=5;
            py=0;
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=0&&y<h/8)){
            p= m.getSpace(6,0);
            m.setPiece(6,0);
            System.out.println(p);
            px=6;
            py=0;
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=0&&y<h/8)){
            p= m.getSpace(7,0);
            m.setPiece(7,0);
            System.out.println(p);
            px=7;
            py=0;
        }
        else if((x>=0&&x<w/9)&&(y>=h/8&&y<2*h/8)){          //row 2
            p= m.getSpace(0,1);
            m.setPiece(0,1);
            System.out.println(p);
            px=0;
            py=1;
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=h/8&&y<2*h/8)){
            p= m.getSpace(1,1);
            m.setPiece(1,1);
            System.out.println(p);
            px=1;
            py=1;
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=h/8&&y<2*h/8)){
            p= m.getSpace(2,1);
            m.setPiece(2,1);
            System.out.println(p);
            px=2;
            py=1;
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=h/8&&y<2*h/8)){
            p= m.getSpace(3,1);
            m.setPiece(3,1);
            System.out.println(p);
            px=3;
            py=1;
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=h/8&&y<2*h/8)){
            p= m.getSpace(4,1);
            m.setPiece(4,1);
            System.out.println(p);
            px=4;
            py=1;
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=h/8&&y<2*h/8)){
            p= m.getSpace(5,1);
            m.setPiece(5,1);
            System.out.println(p);
            px=5;
            py=1;
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=h/8&&y<2*h/8)){
            p= m.getSpace(6,1);
            m.setPiece(6,1);
            System.out.println(p);
            px=6;
            py=1;
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=h/8&&y<2*h/8)){
            p= m.getSpace(7,1);
            m.setPiece(7,1);
            System.out.println(p);
            px=7;
            py=1;
        }
        else if((x>=0&&x<w/9)&&(y>=2*h/8&&y<3*h/8)){           //row 3
            p= m.getSpace(0,2);
            m.setPiece(0,2);
            System.out.println(p);
            px=0;
            py=2;
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=2*h/8&&y<3*h/8)){
            p= m.getSpace(1,2);
            m.setPiece(1,2);
            System.out.println(p);
            px=1;
            py=2;
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=2*h/8&&y<3*h/8)){
            p= m.getSpace(2,2);
            m.setPiece(2,2);
            System.out.println(p);
            px=2;
            py=2;
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=2*h/8&&y<3*h/8)){
            p= m.getSpace(3,2);
            m.setPiece(3,2);
            System.out.println(p);
            px=3;
            py=2;
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=2*h/8&&y<3*h/8)){
            p= m.getSpace(4,2);
            m.setPiece(4,2);
            System.out.println(p);
            px=4;
            py=2;
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=2*h/8&&y<3*h/8)){
            p= m.getSpace(5,2);
            m.setPiece(5,2);
            System.out.println(p);
            px=5;
            py=2;
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=2*h/8&&y<3*h/8)){
            p= m.getSpace(6,2);
            m.setPiece(6,2);
            System.out.println(p);
            px=6;
            py=2;
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=2*h/8&&y<3*h/8)){
            p= m.getSpace(7,2);
            m.setPiece(7,2);
            System.out.println(p);
            px=7;
            py=2;
        }
        else if((x>=0&&x<w/9)&&(y>=3*h/8&&y<4*h/8)){          //row 4
            p= m.getSpace(0,3);
            m.setPiece(0,3);
            System.out.println(p);
            px=0;
            py=3;
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=3*h/8&&y<4*h/8)){
            p= m.getSpace(1,3);
            m.setPiece(1,3);
            System.out.println(p);
            px=1;
            py=3;
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=3*h/8&&y<4*h/8)){
            p= m.getSpace(2,3);
            m.setPiece(2,3);
            System.out.println(p);
            px=2;
            py=3;
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=3*h/8&&y<4*h/8)){
            p= m.getSpace(3,3);
            m.setPiece(3,3);
            System.out.println(p);
            px=3;
            py=3;
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=3*h/8&&y<4*h/8)){
            p= m.getSpace(4,3);
            m.setPiece(4,3);
            System.out.println(p);
            px=4;
            py=3;
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=3*h/8&&y<4*h/8)){
            p= m.getSpace(5,3);
            m.setPiece(5,3);
            System.out.println(p);
            px=5;
            py=3;
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=3*h/8&&y<4*h/8)){
            p= m.getSpace(6,3);
            m.setPiece(6,3);
            System.out.println(p);
            px=6;
            py=3;
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=3*h/8&&y<4*h/8)){
            p= m.getSpace(7,3);
            m.setPiece(7,3);
            System.out.println(p);
            px=7;
            py=3;
        }
        else if((x>=0&&x<w/9)&&(y>=4*h/8&&y<5*h/8)){          //row 5
            p= m.getSpace(0,4);
            m.setPiece(0,4);
            System.out.println(p);
            px=0;
            py=4;
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=4*h/8&&y<5*h/8)){
            p= m.getSpace(1,4);
            m.setPiece(1,4);
            System.out.println(p);
            px=1;
            py=4;
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=4*h/8&&y<5*h/8)){
            p= m.getSpace(2,4);
            m.setPiece(2,4);
            System.out.println(p);
            px=2;
            py=4;
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=4*h/8&&y<5*h/8)){
            p= m.getSpace(3,4);
            m.setPiece(3,4);
            System.out.println(p);
            px=3;
            py=4;
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=4*h/8&&y<5*h/8)){
            p= m.getSpace(4,4);
            m.setPiece(4,4);
            System.out.println(p);
            px=4;
            py=4;
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=4*h/8&&y<5*h/8)){
            p= m.getSpace(5,4);
            m.setPiece(5,4);
            System.out.println(p);
            px=5;
            py=4;
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=4*h/8&&y<5*h/8)){
            p= m.getSpace(6,4);
            m.setPiece(6,4);
            System.out.println(p);
            px=6;
            py=4;
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=4*h/8&&y<5*h/8)){
            p= m.getSpace(7,4);
            m.setPiece(7,4);
            System.out.println(p);
            px=7;
            py=4;
        }
        else if((x>=0&&x<w/9)&&(y>=5*h/8&&y<6*h/8)){          //row 6
            p= m.getSpace(0,5);
            m.setPiece(0,5);
            System.out.println(p);
            px=0;
            py=5;
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=5*h/8&&y<6*h/8)){
            p= m.getSpace(1,5);
            m.setPiece(1,5);
            System.out.println(p);
            px=1;
            py=5;
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=5*h/8&&y<6*h/8)){
            p= m.getSpace(2,5);
            m.setPiece(2,5);
            System.out.println(p);
            px=2;
            py=5;
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=5*h/8&&y<6*h/8)){
            p= m.getSpace(3,5);
            m.setPiece(3,5);
            System.out.println(p);
            px=3;
            py=5;
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=5*h/8&&y<6*h/8)){
            p= m.getSpace(4,5);
            m.setPiece(4,5);
            System.out.println(p);
            px=4;
            py=5;
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=5*h/8&&y<6*h/8)){
            p= m.getSpace(5,5);
            m.setPiece(5,5);
            System.out.println(p);
            px=5;
            py=5;
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=5*h/8&&y<6*h/8)){
            p= m.getSpace(6,5);
            m.setPiece(6,5);
            System.out.println(p);
            px=6;
            py=5;
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=5*h/8&&y<6*h/8)){
            p= m.getSpace(7,5);
            m.setPiece(7,5);
            System.out.println(p);
            px=7;
            py=5;
        }
        else if((x>=0&&x<w/9)&&(y>=6*h/8&&y<7*h/8)){          //row 7
            p= m.getSpace(0,6);
            m.setPiece(0,6);
            System.out.println(p);
            px=0;
            py=6;
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=6*h/8&&y<7*h/8)){
            p= m.getSpace(1,6);
            m.setPiece(1,6);
            System.out.println(p);
            px=1;
            py=6;
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=6*h/8&&y<7*h/8)){
            p= m.getSpace(2,6);
            m.setPiece(2,6);
            System.out.println(p);
            px=2;
            py=6;
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=6*h/8&&y<7*h/8)){
            p= m.getSpace(3,6);
            m.setPiece(3,6);
            System.out.println(p);
            px=3;
            py=6;
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=6*h/8&&y<7*h/8)){
            p= m.getSpace(4,6);
            m.setPiece(4,6);
            System.out.println(p);
            px=4;
            py=6;
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=6*h/8&&y<7*h/8)){
            p= m.getSpace(5,6);
            m.setPiece(5,6);
            System.out.println(p);
            px=5;
            py=6;
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=6*h/8&&y<7*h/8)){
            p= m.getSpace(6,6);
            m.setPiece(6,6);
            System.out.println(p);
            px=6;
            py=6;
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=6*h/8&&y<7*h/8)){
            p= m.getSpace(7,6);
            m.setPiece(7,6);
            System.out.println(p);
            px=7;
            py=6;
        }
        else if((x>=0&&x<w/9)&&(y>=7*h/8&&y<8*h/8)){          //row 8
            p= m.getSpace(0,7);
            m.setPiece(0,7);
            System.out.println(p);
            px=0;
            py=7;
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=7*h/8&&y<8*h/8)){
            p= m.getSpace(1,7);
            m.setPiece(1,7);
            System.out.println(p);
            px=1;
            py=7;
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=7*h/8&&y<8*h/8)){
            p= m.getSpace(2,7);
            m.setPiece(2,7);
            System.out.println(p);
            px=2;
            py=7;
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=7*h/8&&y<8*h/8)){
            p= m.getSpace(3,7);
            m.setPiece(3,7);
            System.out.println(p);
            px=3;
            py=7;
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=7*h/8&&y<8*h/8)){
            p= m.getSpace(4,7);
            m.setPiece(4,7);
            System.out.println(p);
            px=4;
            py=7;
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=7*h/8&&y<8*h/8)){
            p= m.getSpace(5,7);
            m.setPiece(5,7);
            System.out.println(p);
            px=5;
            py=7;
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=7*h/8&&y<8*h/8)){
            p= m.getSpace(6,7);
            m.setPiece(6,7);
            System.out.println(p);
            px=6;
            py=7;
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=7*h/8&&y<8*h/8)){
            p= m.getSpace(7,7);
            m.setPiece(7,7);
            System.out.println(p);
            px=7;
            py=7;
        }
        else{
            p='i';
        }
        return p;
    }//getSpace

    /* Title: proSel
     * Description: Select piece for promotion
     * @param x: x space
     * @param y: y space
     */
    public void proSel(int x, int y){
        if((8*w/9<=x&&x<9*w/9)&&(y>=h/8&&y<2*h/8)){
            m.promote('q');

        }
        else if((8*w/9<=x&&x<9*w/9)&&(y>=2*h/8&&y<3*h/8)){
            m.promote('r');

        }
        else if((8*w/9<=x&&x<9*w/9)&&(y>=3*h/8&&y<4*h/8)){
            m.promote('b');

        }
        else if((8*w/9<=x&&x<9*w/9)&&(y>=4*h/8&&y<5*h/8)){
            m.promote('k');

        }

    }//proSel

    /* Title: loadSelect
     * Description: Piece for placement
     * @param x: x space
     * @param y: y space
     */
    private void loadSelect(int x, int y){
        if((8*w/9<=x&&x<9*w/9)&&(y>=0/8&&y<h/8)){
           l="king";
           m.select=0;
           m.update();
        }
        else if((8*w/9<=x&&x<9*w/9)&&(y>=h/8&&y<2*h/8)){
            l="queen";
            m.select=1;
            m.update();
        }
        else if((8*w/9<=x&&x<9*w/9)&&(y>=2*h/8&&y<3*h/8)){
            l="rook";
            m.select=2;
            m.update();
        }
        else if((8*w/9<=x&&x<9*w/9)&&(y>=3*h/8&&y<4*h/8)){
            l="bishop";
            m.select=3;
            m.update();
        }
        else if((8*w/9<=x&&x<9*w/9)&&(y>=4*h/8&&y<5*h/8)){
            l="knight";
            m.select=4;
            m.update();
        }
        else if((8*w/9<=x&&x<9*w/9)&&(y>=5*h/8&&y<6*h/8)){
            l="pawn";
            m.select=5;
            m.update();
        }
        else if((8*w/9<=x&&x<9*w/9)&&(y>=7*h/8)&&(m.getPhase()==-2&&m.isAlive(Color.white,3))){
            m.setPhase(-3);
        }
        else if((8*w/9<=x&&x<9*w/9)&&(y>=7*h/8)&&(m.getPhase()==-3&&m.isAlive(Color.black,4))){
            System.out.println("plam");
            m.setPhase(-4);
        }
    }//loadSelect

    /* Title: getSpace2
     * Description: Gets location of space to place piece in loading
     * @param x: x space
     * @param y: y space
     */
    private void getSpace2(int x,int y){
        if((x>=0&&x<w/9)&&(y>=0&&y<h/8)){              //row 1
            m.loadPiece(0,0,l);
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=0&&y<h/8)){
            m.loadPiece(1,0,l);
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=0&&y<h/8)){
            m.loadPiece(2,0,l);
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=0&&y<h/8)){
            m.loadPiece(3,0,l);
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=0&&y<h/8)){
            m.loadPiece(4,0,l);
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=0&&y<h/8)){
            m.loadPiece(5,0,l);
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=0&&y<h/8)){
            m.loadPiece(6,0,l);
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=0&&y<h/8)){
            m.loadPiece(7,0,l);
        }
        else if((x>=0&&x<w/9)&&(y>=h/8&&y<2*h/8)){          //row 2
            m.loadPiece(0,1,l);
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=h/8&&y<2*h/8)){
            m.loadPiece(1,1,l);
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=h/8&&y<2*h/8)){
            m.loadPiece(2,1,l);
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=h/8&&y<2*h/8)){
            m.loadPiece(3,1,l);
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=h/8&&y<2*h/8)){
            m.loadPiece(4,1,l);
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=h/8&&y<2*h/8)){
            m.loadPiece(5,1,l);
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=h/8&&y<2*h/8)){
            m.loadPiece(6,1,l);
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=h/8&&y<2*h/8)){
            m.loadPiece(7,1,l);
        }
        else if((x>=0&&x<w/9)&&(y>=2*h/8&&y<3*h/8)){           //row 3
            m.loadPiece(0,2,l);
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=2*h/8&&y<3*h/8)){
            m.loadPiece(1,2,l);
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=2*h/8&&y<3*h/8)){
            m.loadPiece(2,2,l);
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=2*h/8&&y<3*h/8)){
            m.loadPiece(3,2,l);
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=2*h/8&&y<3*h/8)){
            m.loadPiece(4,2,l);
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=2*h/8&&y<3*h/8)){
            m.loadPiece(5,2,l);
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=2*h/8&&y<3*h/8)){
            m.loadPiece(6,2,l);
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=2*h/8&&y<3*h/8)){
            m.loadPiece(7,2,l);
        }
        else if((x>=0&&x<w/9)&&(y>=3*h/8&&y<4*h/8)){          //row 4
            m.loadPiece(0,3,l);
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=3*h/8&&y<4*h/8)){
            m.loadPiece(1,3,l);
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=3*h/8&&y<4*h/8)){
            m.loadPiece(2,3,l);
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=3*h/8&&y<4*h/8)){
            m.loadPiece(3,3,l);
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=3*h/8&&y<4*h/8)){
            m.loadPiece(4,3,l);
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=3*h/8&&y<4*h/8)){
            m.loadPiece(5,3,l);
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=3*h/8&&y<4*h/8)){
            m.loadPiece(6,3,l);
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=3*h/8&&y<4*h/8)){
            m.loadPiece(7,3,l);
        }
        else if((x>=0&&x<w/9)&&(y>=4*h/8&&y<5*h/8)){          //row 5
            m.loadPiece(0,4,l);
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=4*h/8&&y<5*h/8)){
            m.loadPiece(1,4,l);
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=4*h/8&&y<5*h/8)){
            m.loadPiece(2,4,l);
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=4*h/8&&y<5*h/8)){
            m.loadPiece(3,4,l);
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=4*h/8&&y<5*h/8)){
            m.loadPiece(4,4,l);
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=4*h/8&&y<5*h/8)){
            m.loadPiece(5,4,l);
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=4*h/8&&y<5*h/8)){
            m.loadPiece(6,4,l);
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=4*h/8&&y<5*h/8)){
            m.loadPiece(7,4,l);
        }
        else if((x>=0&&x<w/9)&&(y>=5*h/8&&y<6*h/8)){          //row 6
            m.loadPiece(0,5,l);
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=5*h/8&&y<6*h/8)){
            m.loadPiece(1,5,l);
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=5*h/8&&y<6*h/8)){
            m.loadPiece(2,5,l);
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=5*h/8&&y<6*h/8)){
            m.loadPiece(3,5,l);
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=5*h/8&&y<6*h/8)){
            m.loadPiece(4,5,l);
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=5*h/8&&y<6*h/8)){
            m.loadPiece(5,5,l);
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=5*h/8&&y<6*h/8)){
            m.loadPiece(6,5,l);
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=5*h/8&&y<6*h/8)){
            m.loadPiece(7,5,l);
        }
        else if((x>=0&&x<w/9)&&(y>=6*h/8&&y<7*h/8)){          //row 7
            m.loadPiece(0,6,l);
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=6*h/8&&y<7*h/8)){
            m.loadPiece(1,6,l);
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=6*h/8&&y<7*h/8)){
            m.loadPiece(2,6,l);
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=6*h/8&&y<7*h/8)){
            m.loadPiece(3,6,l);
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=6*h/8&&y<7*h/8)){
            m.loadPiece(4,6,l);
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=6*h/8&&y<7*h/8)){
            m.loadPiece(5,6,l);
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=6*h/8&&y<7*h/8)){
            m.loadPiece(6,6,l);
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=6*h/8&&y<7*h/8)){
            m.loadPiece(7,6,l);
        }
        else if((x>=0&&x<w/9)&&(y>=7*h/8&&y<8*h/8)){          //row 8
            m.loadPiece(0,7,l);
        }
        else if((w/9<=x&&x<2*w/9)&&(y>=7*h/8&&y<8*h/8)){
            m.loadPiece(1,7,l);
        }
        else if((2*w/9<=x&&x<3*w/9)&&(y>=7*h/8&&y<8*h/8)){
            m.loadPiece(2,7,l);
        }
        else if((3*w/9<=x&&x<4*w/9)&&(y>=7*h/8&&y<8*h/8)){
            m.loadPiece(3,7,l);
        }
        else if((4*w/9<=x&&x<5*w/9)&&(y>=7*h/8&&y<8*h/8)){
            m.loadPiece(4,7,l);
        }
        else if((5*w/9<=x&&x<6*w/9)&&(y>=7*h/8&&y<8*h/8)){
            m.loadPiece(5,7,l);
        }
        else if((6*w/9<=x&&x<7*w/9)&&(y>=7*h/8&&y<8*h/8)){
            m.loadPiece(6,7,l);
        }
        else if((7*w/9<=x&&x<8*w/9)&&(y>=7*h/8&&y<8*h/8)){
            m.loadPiece(7,7,l);
        }
    }//getSpace2

}//Mouse
