package chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ChessBoard extends JPanel
{
    Image imgs[]=new Image[30];
    //array for storing images
    public static ChessPieces[] CP = new ChessPieces[64];

    private JPanel chesspanel = new JPanel()
    {//creating a panel
        @Override
        public void paint(Graphics g)
        {//(https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html)

            PopulatePieces();

            boolean color = true;
            //used to switch between two colors, black and white

            for(int i=0; i<8; i++)
            {//for loops to make an 8*8 chess board, their will be 8 squares on 8 rows
                for(int j=0; j<8; j++)
                {
                    if(color)
                    //if true square piece will be white
                        g.drawImage(imgs[1], j * 100, i* 100, this);
                    else
                    //else if false it will be black
                        g.drawImage(imgs[0], j * 100, i* 100, this);

                    color=!color;
                }
                color=!color;
                //sets the boolean to the opposite value of what it is, so the next row won't start with the same color as the previous row
            }


            for(int n=0; n<CP.length; n++)
            {
                try {
                    if (!Objects.isNull(CP[n].getPiece()))
                        g.drawImage(CP[n].getPiece(), CP[n].getX() * 100, CP[n].getY() * 100, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

                for(int z=0; z<8; z++) {
                        //if true square piece will be white
                        g.drawImage(imgs[14+z], z * 107, 8 * 100, this);
                        //else if false it will be black
                        g.drawImage(imgs[22+z], 8 * 100, z * 107, this);
                }

            MovePiece mp = new MovePiece();

            mp.move(g, imgs, CP);
        }
    };

    public void PopulatePieces()
    {
        String[] imageName = {"BlackTile.png", "WhiteTile.png", "BlackBishop.png", "BlackHorse.png", "BlackKing.png", "BlackPawn.png", "BlackQueen.png",
                              "BlackRook.png", "WhiteBishop.png", "WhiteHorse.png", "WhiteKing.png", "WhitePawn.png", "WhiteQueen.png", "WhiteRook.png",
                              "1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png", "A.png", "B.png", "C.png", "D.png", "E.png",
                              "F.png", "G.png", "H.png"};
        try
        {
            for(int x=0; x< imageName.length; x++)
                if(x<14)
                    imgs[x] = ImageIO.read(new File("ChessPng\\"+imageName[x]))
                        .getScaledInstance(100, 100, BufferedImage.SCALE_SMOOTH);
                else
                    imgs[x] = ImageIO.read(new File("ChessPng\\"+imageName[x]))
                            .getScaledInstance(40, 40, BufferedImage.SCALE_SMOOTH);
            //importing image from local hardrive and storing it in the array
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ChessPieces A1 = new ChessPieces(0, 0,  "Black", imgs[7], "rook1", "White", "A1");
        ChessPieces A2 = new ChessPieces(1, 0,  "Black", imgs[3], "knight1", "Black",  "A2");
        ChessPieces A3 = new ChessPieces(2, 0,  "Black", imgs[2], "bishop1", "White", "A3");
        ChessPieces A4 = new ChessPieces(3, 0,  "Black", imgs[4], "king", "Black", "A4");
        ChessPieces A5 = new ChessPieces(4, 0,  "Black", imgs[6], "queen", "White", "A5");
        ChessPieces A6 = new ChessPieces(5, 0,  "Black", imgs[2], "bishop2", "Black", "A6");
        ChessPieces A7 = new ChessPieces(6, 0,  "Black", imgs[3], "knight2", "White", "A7");
        ChessPieces A8 = new ChessPieces(7, 0,  "Black", imgs[7], "rook2", "Black", "A8");
        ChessPieces B1 = new ChessPieces(0, 1,  "Black", imgs[5], "pawn1", "Black", "B1");
        ChessPieces B2 = new ChessPieces(1, 1,  "Black", imgs[5], "pawn2", "White", "B2");
        ChessPieces B3 = new ChessPieces(2, 1,  "Black", imgs[5], "pawn3", "Black", "B3");
        ChessPieces B4 = new ChessPieces(3, 1,  "Black", imgs[5], "pawn4", "White", "B4");
        ChessPieces B5 = new ChessPieces(4, 1,  "Black", imgs[5], "pawn5", "Black", "B5");
        ChessPieces B6 = new ChessPieces(5, 1,  "Black", imgs[5], "pawn6", "White", "B6");
        ChessPieces B7 = new ChessPieces(6, 1,  "Black", imgs[5], "pawn7", "Black", "B7");
        ChessPieces B8 = new ChessPieces(7, 1,  "Black", imgs[5], "pawn8", "White", "B8");
        ChessPieces C1 = new ChessPieces(0, 2,  "", imgs[1], "", "White", "C1");
        ChessPieces C2 = new ChessPieces(1, 2,  "", imgs[0], "", "Black", "C2");
        ChessPieces C3 = new ChessPieces(2, 2,  "", imgs[1], "", "White", "C3");
        ChessPieces C4 = new ChessPieces(3, 2,  "", imgs[0], "", "Black", "C4");
        ChessPieces C5 = new ChessPieces(4, 2,  "", imgs[1], "", "White", "C5");
        ChessPieces C6 = new ChessPieces(5, 2,  "", imgs[0], "", "Black", "C6");
        ChessPieces C7 = new ChessPieces(6, 2,  "", imgs[1], "", "White", "C7");
        ChessPieces C8 = new ChessPieces(7, 2,  "", imgs[0], "", "Black", "C8");
        ChessPieces D1 = new ChessPieces(0, 3,  "", imgs[0], "", "Black", "D1");
        ChessPieces D2 = new ChessPieces(1, 3,  "", imgs[1], "", "White", "D2");
        ChessPieces D3 = new ChessPieces(2, 3,  "", imgs[0], "", "Black", "D3");
        ChessPieces D4 = new ChessPieces(3, 3,  "", imgs[1], "", "White", "D4");
        ChessPieces D5 = new ChessPieces(4, 3,  "", imgs[0], "", "Black", "D5");
        ChessPieces D6 = new ChessPieces(5, 3,  "", imgs[1], "", "White", "D6");
        ChessPieces D7 = new ChessPieces(6, 3,  "", imgs[0], "", "Black", "D7");
        ChessPieces D8 = new ChessPieces(7, 3,  "", imgs[1], "", "White", "D8");
        ChessPieces E1 = new ChessPieces(0, 4,  "", imgs[1], "", "White", "E1");
        ChessPieces E2 = new ChessPieces(1, 4,  "", imgs[0], "", "Black", "E2");
        ChessPieces E3 = new ChessPieces(2, 4,  "", imgs[1], "", "White", "E3");
        ChessPieces E4 = new ChessPieces(3, 4,  "", imgs[0], "", "Black", "E4");
        ChessPieces E5 = new ChessPieces(4, 4,  "", imgs[1], "", "White", "E5");
        ChessPieces E6 = new ChessPieces(5, 4,  "", imgs[0], "", "Black", "E6");
        ChessPieces E7 = new ChessPieces(6, 4,  "", imgs[1], "", "White", "E7");
        ChessPieces E8 = new ChessPieces(7, 4,  "", imgs[0], "", "Black", "E8");
        ChessPieces F1 = new ChessPieces(0, 5,  "", imgs[0], "", "Black", "F1");
        ChessPieces F2 = new ChessPieces(1, 5,  "", imgs[1], "", "White", "F2");
        ChessPieces F3 = new ChessPieces(2, 5,  "", imgs[0], "", "Black", "F3");
        ChessPieces F4 = new ChessPieces(3, 5,  "", imgs[1], "", "White", "F4");
        ChessPieces F5 = new ChessPieces(4, 5,  "", imgs[0], "", "Black", "F5");
        ChessPieces F6 = new ChessPieces(5, 5,  "", imgs[1], "", "White", "F6");
        ChessPieces F7 = new ChessPieces(6, 5,  "", imgs[0], "", "Black", "F7");
        ChessPieces F8 = new ChessPieces(7, 5,  "", imgs[1], "", "White", "F8");
        ChessPieces G1 = new ChessPieces(0, 6,  "White", imgs[11], "pawn1", "White", "G1");
        ChessPieces G2 = new ChessPieces(1, 6,  "White", imgs[11], "pawn2", "Black",  "G2");
        ChessPieces G3 = new ChessPieces(2, 6,  "White", imgs[11], "pawn3", "White", "G3");
        ChessPieces G4 = new ChessPieces(3, 6,  "White", imgs[11], "pawn4", "Black", "G4");
        ChessPieces G5 = new ChessPieces(4, 6,  "White", imgs[11], "pawn5", "White", "G5");
        ChessPieces G6 = new ChessPieces(5, 6,  "White", imgs[11], "pawn6", "Black", "G6");
        ChessPieces G7 = new ChessPieces(6, 6,  "White", imgs[11], "pawn7", "White", "G7");
        ChessPieces G8 = new ChessPieces(7, 6,  "White", imgs[11], "pawn8", "Black", "G8");
        ChessPieces H1 = new ChessPieces(0, 7,  "White", imgs[13], "rook1", "Black", "H1");
        ChessPieces H2 = new ChessPieces(1, 7,  "White", imgs[9], "knight1", "White",  "H2");
        ChessPieces H3 = new ChessPieces(2, 7,  "White", imgs[8], "bishop1", "Black", "H3");
        ChessPieces H4 = new ChessPieces(3, 7,  "White", imgs[10], "king", "White", "H4");
        ChessPieces H5 = new ChessPieces(4, 7,  "White", imgs[12], "queen", "Black", "H5");
        ChessPieces H6 = new ChessPieces(5, 7,  "White", imgs[8], "bishop2", "White", "H6");
        ChessPieces H7 = new ChessPieces(6, 7,  "White", imgs[9], "knight2", "Black", "H7");
        ChessPieces H8 = new ChessPieces(7, 7,  "White", imgs[13], "rook2", "White", "H8");

        CP = new ChessPieces[]{A1, A2, A3, A4, A5, A6, A7, A8, B1, B2, B3, B4, B5, B6, B7, B8, C1, C2, C3, C4, C5, C6, C7, C8, D1, D2, D3, D4, D5, D6, D7, D8,
                E1, E2, E3, E4, E5, E6, E7, E8, F1, F2, F3, F4, F5, F6, F7, F8, G1, G2, G3, G4, G5, G6, G7, G8, H1, H2, H3, H4, H5, H6, H7, H8};
    }

    public JPanel getChesspanel() {
        return chesspanel;
    }
}
