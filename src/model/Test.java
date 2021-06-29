package model;

import java.util.*;

public class Test extends Gem {
    public Test() {
        setColour();
    }
    public void setColour(Colour color){
        
    }
    public void setColour() {
        this.colour = Colour.values()[new Random().nextInt(7)];
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
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>(8);
        int dx[] = { -1, 0, 1, 1, 1, 0, -1, -1 };
        int dy[] = { -1, -1, -1, 0, 1, 1, 1, 0 };
        for (int i = 0; i < 8; i++) {
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            tempList.add(dx[i]);
            tempList.add(dy[i]);
            list.add(tempList);
        }
        return list;
    }
}
