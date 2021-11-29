package chess;

import java.awt.*;

public class ChessPieces {

    private int x, y;
    private String pColor, rank, tile, location;
    private Image piece;

    public ChessPieces(int x, int y, String pColor, Image piece, String rank, String tile, String location) {
        this.x = x;
        this.y = y;
        this.pColor = pColor;
        this.piece = piece;
        this.rank = rank;
        this.tile = tile;
        this.location = location;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setpColor(String pColor) {
        this.pColor = pColor;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPiece(Image piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getpColor() {
        return pColor;
    }

    public String getRank() {
        return rank;
    }

    public String getTile() {
        return tile;
    }

    public String getLocation() {
        return location;
    }

    public Image getPiece() {
        return piece;
    }
}