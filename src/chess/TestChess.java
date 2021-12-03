package chess;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

public class TestChess
{
    public static void main(String args[]) throws IOException {
        JFrame chessframe = new JFrame();
        //creating jframe

        ChessBoard gg = new ChessBoard();
        //creating object for graphicsG class

        chessframe.setBounds(0,0,880,880);
        //setting the size of the Jframe and where jframe will appear on screen

        chessframe.add(gg.getChesspanel());
        //adding panel to the jframe using the graphics from gg object

        chessframe.setVisible(true);
        //making the jframe window visible

        chessframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //exit the application (https://chortle.ccsu.edu/java5/notes/chap56/ch56_9.html)

    }
}
