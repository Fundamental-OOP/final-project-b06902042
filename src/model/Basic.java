package model;

import java.util.*;

public class Basic extends Gem {
    public Basic() {
        setColour();
    }

    public void setColour(Colour color) {
        this.colour = color;
        setImagePath();
    }

    public void setColour() {
        this.colour = Colour.values()[new Random().nextInt(7)];
        setImagePath();
    }

    private void setImagePath() {
        switch (this.colour) {
            case BLUE:
                this.imagePath = "Images/Blue.gif";
                break;
            case GREEN:
                this.imagePath = "Images/Green.gif";
                break;
            case ORANGE:
                this.imagePath = "Images/Orange.gif";
                break;
            case PURPLE:
                this.imagePath = "Images/Purple.gif";
                break;
            case RED:
                this.imagePath = "Images/Red.gif";
                break;
            case WHITE:
                this.imagePath = "Images/White.gif";
                break;
            case YELLOW:
                this.imagePath = "Images/Yellow.gif";
                break;
            case CROSS:
                this.imagePath = "Images/Cross.png";
                break;
        }
    }

    @Override
    public ArrayList<ArrayList<Integer>> useEffect() {
        return new ArrayList<ArrayList<Integer>>();
    }

}
