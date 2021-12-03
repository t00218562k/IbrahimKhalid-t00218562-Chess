package chess;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class MovePiece
{
    public void move(Graphics g, Image[] imgs, ChessPieces[] CP)
    {//method for moving pieces

        boolean valid = true, turn = true, ruleValid = false;
        //boolean valid to check if user wants to continue the game
        //boolean turn decides who's turn it is
        //boolean rulevalid is to check if the piece moving made a right move
        int location = 0;
        //integer value of location in array
        String pieceName = "",loc = "";
        //two strings to determine piece to move and location to go to

        JOptionPane.showMessageDialog(null, "Standard chess names\npawn1-pawn8\nrook1-rook2" +
                "\nbishop1-bishop2\nknight1-knight2\nking\nqueen");
        outerloop:
        do {
            ruleValid = false;
            location = 0;
            pieceName = "";
            loc = "";

            while(!ruleValid)
            {//if the rule was illegal then user chooses a new piece and move

                pieceName = pieceNameValid(CP, turn);
                //Validation for user input for piece name to move

                if (pieceName.isEmpty())
                    //checks if user entered empthy string to end game
                    break outerloop;

                loc = locationValid(CP, pieceName);
                //validation for user input, location to move piece

                if(occupied(loc, CP, turn))
                    //occupied returns true if the user isn't moving into a block their other piece is in
                    ruleValid = rules(CP, loc, pieceName, turn);
                    //rulevalid checks if the user made a valid move based on the rules of that piece
            }

            for (int i = 0; i < CP.length; i++)
            {//loop to check user location
                if (CP[i].getLocation().equals(loc.toUpperCase()))
                {//if user location is valid
                    location = i;
                    //location equals index position of user location
                }
            }


            for (int y = 0; y < CP.length; y++)
            {//loop to get user input for piece in index of CP
                    if (turn)
                    {//turn represents white when true, white piece moves first
                        if (CP[y].getRank().equals(pieceName.toLowerCase()) && (CP[y].getpColor().equals("White")))
                        {//if user input for piece is valid with CP, and that piece is white

                            ChangePlaces(g, imgs, CP, y, location);
                            //method for changing pieces on board

                            turn = !turn;
                            //boolean will equal false

                            break;
                            //break out of for loop
                        }
                    }
                    else
                    {
                        if (CP[y].getRank().equals(pieceName.toLowerCase()) && (CP[y].getpColor().equals("Black")))
                        {//if user input for piece is valid with CP, and that piece is black

                            ChangePlaces(g, imgs, CP, y, location);
                            //method for changing pieces on board

                            turn = !turn;
                            //boolean will equal true

                            break;
                            //break out of for loop
                        }
                    }
            }//end of for loop

            if(gameOver(CP, turn))
            {
                JOptionPane.showMessageDialog(null, "Congrats you WON!!");
                break outerloop;
            }

        }while (valid) ;
    }

    public void ChangePlaces(Graphics g, Image[] imgs, ChessPieces[] CP, int currentLocation, int location)
    {//this method paints the piece to move to its new location

        colorTile(g,imgs, CP, currentLocation);
        colorTile(g,imgs, CP, location);
        //repaint the tiles

        g.drawImage(CP[currentLocation].getPiece(), CP[location].getX() * 100, CP[location].getY() * 100, null);
        //drawing the piece in its new location
        
        CP[location].setpColor(CP[currentLocation].getpColor());
        CP[location].setPiece(CP[currentLocation].getPiece());
        CP[location].setRank(CP[currentLocation].getRank());
        //Sorting the CP array

        CP[currentLocation].setpColor("");
        CP[currentLocation].setRank("");
    }

    public void colorTile(Graphics g, Image[] imgs, ChessPieces[] CP, int position)
    {//method for coloring tiles black or white
        if (CP[position].getTile().equals("White"))
            g.drawImage(imgs[1], CP[position].getX() * 100, CP[position].getY() * 100, null);
        else
            g.drawImage(imgs[0], CP[position].getX() * 100, CP[position].getY() * 100, null);
    }

    public String pieceNameValid(ChessPieces[] CP, boolean color)
    {//method to validate piece name
        
        String name = "", pieceColor;
        //strings for name of piece and color of piece
        
        boolean valid = true;

        if(color)
            pieceColor = "White";
        else
            pieceColor = "Black";
        //if else to get color of piece to check name

        outerloop:
        while(valid)
        {//loop keeps going tell a right name is entered or user enters empthy string
            
            name = JOptionPane.showInputDialog("Enter Piece name or \n press enter twice to end game");

            for(int i=0; i<CP.length; i++)
                if((CP[i].getRank().equals(name.toLowerCase()) && CP[i].getpColor().equals(pieceColor)) || name.isEmpty())
                    //breaks out of loop once valid
                    break outerloop;

            JOptionPane.showMessageDialog(null,"invalid input");
        }

        return name;
    }

    public String locationValid(ChessPieces[] CP, String name)
    {//method to check if location is valid
        
        String location="";
        //String to hold location to go to
        
        boolean valid = true;

        outerloop:
        while(valid)
        {//loop keeps going tell a right location is entered or user enters empthy string
            location = JOptionPane.showInputDialog("Enter Location for " + name + " e.g A5");

            for(int i=0; i<CP.length; i++)
                if(CP[i].getLocation().equals(location.toUpperCase()) || name.isEmpty())
                    //breaks out of loop once valid
                    break outerloop;

            JOptionPane.showMessageDialog(null,"invalid location");
        }

        return location;
    }

    public boolean rules(ChessPieces[] CP, String location, String name, boolean color)
    {
        String[] pieceNames = { "pawn", "knight", "bishop", "king", "queen", "rook"};
        //string array for piece names
        
        String pieceColor = "";
        //color of piece
        
        int pieceIndex = 0;
        //used for index position in piecenames array

        int y = 0;
        //y is the current column of piece to move

        int x = 0;
        //x is the current row of piece to move

        int locationX = 0, locationY = 0;
        //int for x and y of locations to move to

        if(color)
            pieceColor = "White";
        else
            pieceColor = "Black";
        //if else for color of piece

        for(int i=0; i<pieceNames.length; i++)
        //loop for finding name in array
            if(name.toLowerCase().startsWith(pieceNames[i]))
            {
                pieceIndex = i;
                break;
            }

        
        outerloop:
        for(int v=0; v<CP.length; v++)
            if(CP[v].getRank().equals(name.toLowerCase()) && CP[v].getpColor().equals(pieceColor))
            {
                y = CP[v].getY();
                //y is the current column of piece to move
                x = CP[v].getX();
                //x is the current row of piece to move
                
                for(int o=0; o<CP.length; o++)
                    if(CP[o].getLocation().equals(location.toUpperCase()))
                    {//gets the x and y cordinates for the location to move to
                        locationY=CP[o].getY();
                        locationX=CP[o].getX();
                        break outerloop;
                    }
            }


        if(pieceNames[pieceIndex].equals("pawn"))
            if((y+2 == locationY || y-2 == locationY || y+1 == locationY || y-1 == locationY)
                    || (y+1 == locationY || y-1 == locationY) && ( x+1 == locationX || x-1 == locationX))
                return true;

        if(pieceNames[pieceIndex].equals("rook"))
            if((locationY==y && locationX<9) || (locationX==x && locationY<9))
                return true;

        if(pieceNames[pieceIndex].equals("knight"))
            if((locationX==(x+2) || locationX==(x-2))  && (locationY==(y+1) || locationY==(y-1))
                    || ((locationY==(y-2) || locationY==(y+2)) && (locationX==(x+1) || locationX==(x-1))))
                return true;

        if(pieceNames[pieceIndex].equals("bishop"))
            for(int b=0; b<8; b++)
                if((locationX==(x+b) || locationX==(x-b)) && (locationY==(y-b) || locationY==(y+b)))
                    return true;

        if(pieceNames[pieceIndex].equals("queen"))
            for (int q = 0; q < 8; q++)
            {
                if ((locationX == (x + q) || locationX == (x - q)) && (locationY == (y - q) || locationY == (y + q)))
                    return true;

                if ((locationY==y && locationX<9) || (locationX==x && locationY<9))
                    return true;
            }

        if(pieceNames[pieceIndex].equals("king"))
            if ((locationX==(x+1) || locationX==(x-1) || locationX==x) && (locationY==y || locationY==(y+1) || locationY==(y-1)))
                return true;


        JOptionPane.showMessageDialog(null, "Cannot make that move");
        return false;
    }

    public boolean occupied(String location, ChessPieces[] CP, boolean color)
    {//method to see if square is occupied by friendly piece, returns true if piece is enemy

        String enemyColor="";

        if(color)
            enemyColor = "Black";
        else
            enemyColor = "White";

        for(int i=0; i<CP.length; i++)
            if(CP[i].getLocation().equals(location.toUpperCase()))
                if(CP[i].getRank().isEmpty() || CP[i].getpColor().equals(enemyColor))
                    return true;

        JOptionPane.showMessageDialog(null, "Peace Occupied");
        return false;
    }

    public boolean gameOver(ChessPieces[] CP, boolean color)
    {
        int alive = 0;

        String enemyColor="";

        if(color)
            enemyColor = "Black";
        else
            enemyColor = "White";

        for(int i=0; i<CP.length; i++)
            if(CP[i].getpColor().equals(enemyColor) && CP[i].getRank().equals("king"))
                alive++;

        if(alive==0)
            return true;

        return false;
    }
}
