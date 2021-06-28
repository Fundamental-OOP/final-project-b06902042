package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import model.Gem;
import model.Gem.Colour;

public class GemView extends JButton {
    private Gem gem;
    private int x;
    private int y;

    public Gem getGem() {
        return this.gem;
    }

    public void setGem(Gem gem) {
        this.gem = gem;
        // setIcon(new ImageIcon(this.gem.getImagePath()));
    }

    public int getMyX() {
        return this.x;
    }

    public int getMyY() {
        return this.y;
    }

    public void setMyXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setGemColour() {
        this.gem.setColour();
        setIcon(new ImageIcon(this.gem.getImagePath()));
    }

    public Colour getGemColour() {
        return gem.getColour();
    }

    public String getGemImagePath() {
        return gem.getImagePath();
    }
}
