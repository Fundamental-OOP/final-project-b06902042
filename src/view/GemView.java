package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class GemView extends JButton {
    private int x;
    private int y;

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

    public void setButtonBorder(boolean isSet) {
        if (isSet) {
            setBorder(new LineBorder(Color.orange));
        } else {
            setBorder(null);
        }

    }
}
