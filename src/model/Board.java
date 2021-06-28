package model;

import java.util.*;
import controller.*;
import model.Gem.Colour;

public class Board {
    private final int boardSize = 10;
    private Gem grid[][] = new Gem[boardSize][boardSize];
    private String imagePath[][] = new String[boardSize][boardSize];
    private Gem selected;
    private ArrayList<Gem> clearList = new ArrayList<>();
    private Queue<Gem> effectList = new LinkedList<>();
    private boolean firstPressed = true;
    private GameController gameController;

    public Board(GameController gameController) {
        this.gameController = gameController;
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                if (x == 5 && y == 5)
                    grid[x][y] = new Test();
                else
                    grid[x][y] = new Basic();
                grid[x][y].setMyXY(x, y);
            }
        }

        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                while (checkMatch(x, y)) {
                    grid[x][y].setColour();
                }
                imagePath[x][y] = grid[x][y].getImagePath();
            }
        }
    }

    public boolean getSetButtonBorder(int x, int y) {
        return grid[x][y].getSetButtonBorder();
    }

    public boolean getRemoveFlag(int x, int y) {
        return grid[x][y].getRemoveFlag();
    }

    public Gem getGem(int x, int y) {
        return grid[x][y];
    }

    public String getGridImagePath(int x, int y) {
        return imagePath[x][y];
    }

    public void performClick(Gem source) {
        if (firstPressed) {
            selected = source;
            selected.setButtonBorder(true);
            firstPressed = false;
        } else {
            selected.setButtonBorder(false);
            if (selected.isNextTo(source)) {
                swap(selected, source);
                this.gameController.checkIfMatch(selected, source);
                firstPressed = true;
            } else if (source == selected) {
                firstPressed = true;
            } else {
                selected = source;
                selected.setButtonBorder(true);
            }
        }
    }

    public void clearGrid(Gem a, Gem b) {
        boolean aMatch = clearMatch(a.getMyX(), a.getMyY());
        boolean bMatch = clearMatch(b.getMyX(), b.getMyY());
        if (!aMatch && !bMatch) {
            swap(a, b);
        }
        gameController.repaintBoard();
    }

    public void refillGrid() {
        // for (Gem gem : clearList) {
        // gem.setRemoveFlag(false);
        // }
        clearList.removeAll(clearList);
    }

    private boolean clearMatch(int x, int y) {
        Colour colour = grid[x][y].getColour();
        System.out.println(colour);
        int dx[] = { 0, 1, 0, -1 };
        int dy[] = { -1, 0, 1, 0 };
        int cnt[] = { 0, 0, 0, 0 };
        ArrayList<ArrayList<Gem>> gemList = new ArrayList<ArrayList<Gem>>(4);
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i], newY = y + dy[i];
            ArrayList<Gem> tempList = new ArrayList<Gem>();
            while (isValid(newX, newY)) {
                if (grid[newX][newY].getColour() == colour) {
                    cnt[i]++;
                    tempList.add(grid[newX][newY]);
                    newX += dx[i];
                    newY += dy[i];
                } else {
                    break;
                }
            }
            gemList.add(tempList);
        }
        ArrayList<Gem> clearListTmp = new ArrayList<>();
        Queue<Gem> effectListTmp = new LinkedList<>();
        if (cnt[0] + cnt[2] >= 2) {
            clearListTmp.add(grid[x][y]);
            clearListTmp.addAll(gemList.get(0));
            clearListTmp.addAll(gemList.get(2));
        } else if (cnt[1] + cnt[3] >= 2) {
            clearListTmp.add(grid[x][y]);
            clearListTmp.addAll(gemList.get(1));
            clearListTmp.addAll(gemList.get(3));
        } else if (cnt[0] + cnt[1] >= 4) {
            clearListTmp.add(grid[x][y]);
            clearListTmp.addAll(gemList.get(0));
            clearListTmp.addAll(gemList.get(1));
        } else if (cnt[0] + cnt[3] >= 4) {
            clearListTmp.add(grid[x][y]);
            clearListTmp.addAll(gemList.get(0));
            clearListTmp.addAll(gemList.get(3));
        } else if (cnt[1] + cnt[2] >= 4) {
            clearListTmp.add(grid[x][y]);
            clearListTmp.addAll(gemList.get(1));
            clearListTmp.addAll(gemList.get(2));
        } else if (cnt[2] + cnt[3] >= 4) {
            clearListTmp.add(grid[x][y]);
            clearListTmp.addAll(gemList.get(2));
            clearListTmp.addAll(gemList.get(3));
        }
        if (clearListTmp.size() > 0) {
            effectListTmp.addAll(clearListTmp);
            while (!effectListTmp.isEmpty()) {
                Gem gemPop = effectListTmp.poll();
                ArrayList<ArrayList<Integer>> effectGems = gemPop.useEffect();
                for (ArrayList<Integer> point : effectGems) {
                    int popGemX = gemPop.getMyX() + point.get(0);
                    int popGemY = gemPop.getMyY() + point.get(1);
                    if (isValid(popGemX, popGemY)) {
                        Gem effectGem = grid[popGemX][popGemY];
                        if (!clearListTmp.contains(effectGem) && !clearList.contains(effectGem)) {
                            effectListTmp.add(effectGem);
                            clearListTmp.add(effectGem);
                        }
                    }
                }
            }
            for (Gem gem : clearListTmp) {
                gem.setRemoveFlag(true);
            }
            clearList.addAll(clearListTmp);
            effectList.addAll(effectListTmp);
        } else {
            return false;
        }
        return true;
    }

    public boolean checkMatch(int x, int y) {
        Colour colour = grid[x][y].getColour();
        // System.out.println(x + " " + y + " " + colour);
        int dx[] = { 0, 1, 0, -1 };
        int dy[] = { -1, 0, 1, 0 };
        int cnt[] = { 0, 0, 0, 0 };
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i], newY = y + dy[i];
            while (isValid(newX, newY)) {
                // System.out.println(" " + newX + " " + newY + " " +
                // grid[newX][newY].getGemColour());
                if (grid[newX][newY].getColour() == colour) {
                    cnt[i]++;
                    newX += dx[i];
                    newY += dy[i];
                } else {
                    break;
                }
            }
        }
        if ((cnt[0] + cnt[2] >= 2) || (cnt[1] + cnt[3] >= 2) || (cnt[0] + cnt[1] >= 4) || (cnt[0] + cnt[3] >= 4)
                || (cnt[1] + cnt[2] >= 4) || (cnt[2] + cnt[3] >= 4)) {
            return true;
        }

        return false;
    }

    private boolean isValid(int x, int y) {
        return (x >= 0 && x < this.boardSize && y >= 0 && y < this.boardSize);
    }

    private void swap(Gem first, Gem second) {
        int fX = first.getMyX(), fY = first.getMyY(), sX = second.getMyX(), sY = second.getMyY();
        String temp = imagePath[fX][fY];
        imagePath[fX][fY] = imagePath[sX][sY];
        imagePath[sX][sY] = temp;
        grid[fX][fY] = second;
        grid[sX][sY] = first;
        grid[fX][fY].setMyXY(fX, fY);
        grid[sX][sY].setMyXY(sX, sY);
    }
}
