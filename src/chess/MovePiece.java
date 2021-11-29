package chess;

import javax.swing.*;
import java.awt.*;

public class MovePiece
{
    public void move(Graphics g, Image[] imgs, ChessPieces[] CP)
    {
        boolean valid = true, turn = true, ruleValid = false;
        //boolean valid to check if user wants to continue the game
        //boolean turn decides who's turn it is

        String pieceName = "",loc = "";
        //two strings to determine piece to move and location to go to

        int location = 0;
        //integer value of location in array

        do {
            ruleValid = false;

            pieceName = pieceNameValid(CP);

            if (pieceName.isEmpty())
                //checks if user entered empthy string
                break;
            else
            {
                while(!ruleValid)
                {
                    loc = locationValid(CP);
                    //user input, location to move piece
                    ruleValid = rules(CP, loc, pieceName, turn);
                }

                for (int i = 0; i < CP.length; i++)
                {//loop to check user location
                    if (CP[i].getLocation().equals(loc.toUpperCase()))
                    {//if user location is valid
                        location = i;
                        //location equals index position of user location
                    }
                }
                JOptionPane.showMessageDialog(null, rules(CP,loc, pieceName, turn));
                for (int y = 0; y < CP.length; y++)
                {//loop to get user input for piece in index of CP
                        if (turn) {//turn represents white when true, white piece moves first
                            if (CP[y].getRank().equals(pieceName.toLowerCase()) && (CP[y].getpColor().equals("White"))) {//if user input for piece is valid with CP, and that piece is white

                                ChangePlaces(g, imgs, CP, y, location);
                                //method for changing pieces on board

                                turn = !turn;
                                //boolean will equal false

                                break;
                                //break out of for loop
                            }
                        } else {
                            if (CP[y].getRank().equals(pieceName.toLowerCase()) && (CP[y].getpColor().equals("Black"))) {//if user input for piece is valid with CP, and that piece is black

                                ChangePlaces(g, imgs, CP, y, location);
                                //method for changing pieces on board

                                turn = !turn;
                                //boolean will equal true

                                break;
                                //break out of for loop
                            }
                        }
                }//end of second for loop
            }
        }while (valid) ;
    }

    public void ChangePlaces(Graphics g, Image[] imgs, ChessPieces[] CP, int y, int location)
    {
        colorTile(g,imgs, CP, y);
        colorTile(g,imgs, CP, location);

        g.drawImage(CP[y].getPiece(), CP[location].getX() * 100, CP[location].getY() * 100, null);

        Image holder = CP[location].getPiece();

        CP[location].setpColor(CP[y].getpColor());
        CP[location].setPiece(CP[y].getPiece());
        CP[location].setRank(CP[y].getRank());

        CP[y].setpColor("");
        CP[y].setPiece(holder);
        CP[y].setRank("");
    }

    public void colorTile(Graphics g, Image[] imgs, ChessPieces[] CP, int position)
    {
        if (CP[position].getTile().equals("White"))
            g.drawImage(imgs[1], CP[position].getX() * 100, CP[position].getY() * 100, null);
        else
            g.drawImage(imgs[0], CP[position].getX() * 100, CP[position].getY() * 100, null);
    }

    public String pieceNameValid(ChessPieces[] CP)
    {
        String name="";
        boolean valid = true;

        outerloop:
        while(valid)
        {
            name = JOptionPane.showInputDialog("Enter Piece name");

            for(int i=0; i<CP.length; i++)
                if(CP[i].getRank().equals(name.toLowerCase()) || name.isEmpty())
                    break outerloop;

           JOptionPane.showMessageDialog(null,"invalid input");
        }

        return name;
    }

    public String locationValid(ChessPieces[] CP)
    {
        String location="";
        boolean valid = true;

        outerloop:
        while(valid)
        {
            location = JOptionPane.showInputDialog("Enter Location");

            for(int i=0; i<CP.length; i++)
                if(CP[i].getLocation().equals(location.toUpperCase()))
                    break outerloop;

            JOptionPane.showMessageDialog(null,"invalid location");
        }

        return location;
    }

    public boolean rules(ChessPieces[] CP, String location, String name, boolean color)
    {
        String[] pieceNames = { "pawn", "knight", "bishop", "king", "queen", "rook"};
        String pieceColor = "";
        boolean pieceValid = false;
        int pieceIndex = 0;

        if(color)
            pieceColor = "White";
        else
            pieceColor = "Black";

        for(int i=0; i<pieceNames.length; i++)
        {
            if(name.toLowerCase().startsWith(pieceNames[i]))
            {
                pieceValid = true;
                pieceIndex = i;
                break;
            }
        }

        if(pieceValid)
        {

            if(pieceNames[pieceIndex].equals("pawn"))
            {
                outerloop:
                for(int V=0; V<CP.length; V++)
                {
                    if(CP[V].getRank().equals(name.toLowerCase()) && CP[V].getpColor().equals(pieceColor))
                    {
                        int y = CP[V].getY();
                        int nextY = 0;
                        for(int o=0; o<CP.length; o++)
                            if(CP[o].getLocation().equals(location.toUpperCase()))
                            {
                                nextY=CP[o].getY();
                                break;
                            }

                        if(y+2 == nextY || y-2 == nextY || y+1 == nextY || y-1 == nextY)
                            return true;
                    }
                }
            }//end of pawn

            else if(pieceNames[pieceIndex].equals("rook"))
            {
                outerloop:
                for(int v=0; v<CP.length; v++)
                {
                    if(CP[v].getRank().equals(name.toLowerCase()) && CP[v].getpColor().equals(pieceColor))
                    {
                        int Y = CP[v].getY();
                        int x = CP[v].getX();
                        int locationX = 0, locationY = 0;

                        for(int o=0; o<CP.length; o++)
                            if(CP[o].getLocation().equals(location.toUpperCase()))
                            {
                                locationY=CP[o].getY();
                                locationX=CP[o].getY();
                                break;
                            }

                        for(int u=0; u<8; u++)
                        {
                            if(locationY==Y && locationX==u)
                                return true;

                            if(locationX==x && locationY==u)
                                return true;
                        }
                    }
                }
            }//end of rook

            else if(pieceNames[pieceIndex].equals("knight"))
            {
                outerloop:
                for(int v=0; v<CP.length; v++)
                {
                    if(CP[v].getRank().equals(name.toLowerCase()) && CP[v].getpColor().equals(pieceColor))
                    {
                        int y = CP[v].getY();
                        int x = CP[v].getX();
                        int locationX = 0, locationY = 0;

                        for(int o=0; o<CP.length; o++)
                            if(CP[o].getLocation().equals(location.toUpperCase()))
                            {
                                locationY=CP[o].getY();
                                locationX=CP[o].getY();
                                break;
                            }

                        if((locationX==(x+2)  && (locationY==(y+1) || locationY==(y-1))) || ((locationX==(y+2)) || (locationX==(y-2)) && (locationY==(x+1) || locationY==(x-1))))
                            return true;
                    }
                }
            }

            else if(pieceNames[pieceIndex].equals("bishop"))
            {
                outerloop:
                for(int v=0; v<CP.length; v++)
                {
                    if(CP[v].getRank().equals(name.toLowerCase())  && CP[v].getpColor().equals(pieceColor))
                    {
                        int y = CP[v].getY();
                        int x = CP[v].getX();
                        int locationX = 0, locationY = 0;

                        for(int o=0; o<CP.length; o++)
                            if(CP[o].getLocation().equals(location.toUpperCase()))
                            {
                                locationY=CP[o].getY();
                                locationX=CP[o].getY();
                                break;
                            }

                        for(int u=0; u<8; u++)
                        {
                            if(((x+u)==locationX) || ((x-u)==locationX) && ((y+u)==locationY) || ((y-u)==locationY))
                                return true;
                        }
                    }
                }
            }//end of bishop

        }//end of valid piece

        return false;
    }


}
