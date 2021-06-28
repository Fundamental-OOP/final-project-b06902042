package model;

import java.util.*;

public abstract class Gem {
    public enum Colour {
        BLUE, GREEN, ORANGE, PURPLE, RED, WHITE, YELLOW
    }

    private int X;
    private int Y;
    private boolean removeFlag = false;
    private boolean setBorder;
    protected String imagePath;
    protected Colour colour;

    public void setMyXY(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int getMyX() {
        return this.X;
    }

    public int getMyY() {
        return this.Y;
    }

    public void setRemoveFlag(boolean flag) {
        this.removeFlag = flag;
    }

    public boolean getRemoveFlag() {
        return this.removeFlag;
    }

    public boolean getSetButtonBorder() {
        return this.setBorder;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public abstract void setColour();

    public abstract ArrayList<ArrayList<Integer>> useEffect();

    public Colour getColour() {
        return this.colour;
    }

    public void setButtonBorder(boolean setBorder) {
        this.setBorder = setBorder;
    }

    public boolean isNextTo(Gem gem) {
        // System.out.println(this.X + " " + this.Y + " " + gem.getMyX() + " " +
        // gem.getMyY());
        return ((this.X == gem.getMyX() && this.Y == gem.getMyY() - 1)
                || (this.X == gem.getMyX() && this.Y == gem.getMyY() + 1)
                || (this.X == gem.getMyX() + 1 && this.Y == gem.getMyY())
                || (this.X == gem.getMyX() - 1 && this.Y == gem.getMyY()));
    }
}
