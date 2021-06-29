package model;

import java.util.*;

public class Basic extends Gem {
    public Basic() {
        setColour();
    }
    public void setColour(Colour color){
        this.colour = color;
        setImagePath();
    }
    public void setColour() {
        this.colour = Colour.values()[new Random().nextInt(7)];
        setImagePath();
    }

    private void setImagePath(){
        switch (this.colour) {
            case BLUE:
                this.imagePath = "Images/Blue/jewel.gif";
                break;
            case GREEN:
                this.imagePath = "Images/Green/jewel.gif";
                break;
            case ORANGE:
                this.imagePath = "Images/Orange/jewel.gif";
                break;
            case PURPLE:
                this.imagePath = "Images/Purple/jewel.gif";
                break;
            case RED:
                this.imagePath = "Images/Red/jewel.gif";
                break;
            case WHITE:
                this.imagePath = "Images/White/jewel.gif";
                break;
            case YELLOW:
                this.imagePath = "Images/Yellow/jewel.gif";
                break;
        }
    }
    @Override
    public ArrayList<ArrayList<Integer>> useEffect() {
        return new ArrayList<ArrayList<Integer>>();
    }
}
