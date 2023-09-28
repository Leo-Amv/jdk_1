package ru.gb.xo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {
    private static final Random RANDOM = new Random();
    private static final int DOT_PADDING = 5;
    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;
    private int fieldSizeY = 3;
    private int fieldSizeX = 3;
    private char[][] field;

    private int panelWidth;
    private int panelHeight;
    private int cellHeight;
    private int cellWidth;
    Map(){
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
    }
    private void update(MouseEvent e){
        int cellX = e.getX()/cellWidth;
        int cellY = e.getY()/cellHeight;
        if (!isValidCell(cellX,cellY) || !isEmptyCell(cellX,cellY)) return;
        field[cellY][cellX] = HUMAN_DOT;
        repaint();
    }

    void startNewGame(int mode, int fSzX,int fSzY,int wLen){
        System.out.printf("Mode: %d;\nSize: x= %d, y= %d;\nWin Length: %d",mode,fSzX,fSzY,wLen);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g){
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / 3;
        cellWidth = panelWidth / 3;

        g.setColor(Color.BLACK);
        for (int h = 0; h < 3; h++) {
            int y = h * cellHeight;
            g.drawLine(0,y,panelWidth,y);
        }
        for (int w = 0; w < 3; w++) {
            int x = w * cellWidth;
            g.drawLine(x,0,x,panelHeight);
        }

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT) continue;

                if (field[y][x] == HUMAN_DOT){
                    g.setColor(Color.WHITE);
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING,cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                } else if (field[y][x] == AI_DOT) {
                    g.setColor(Color.BLACK);
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING,cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("Unexpected value " + field[y][x] + " in cell: x=" + x + " y=" + y);
                }
            }
        }
    }

    /**
     * game logic
     */

    private void initMap(){
        fieldSizeY = 3;
        fieldSizeX = 3;
        field = new char[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    private boolean isValidCell(int x, int y) {
        return x>= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y){
        return field[x][y] == EMPTY_DOT;
    }

    private void aiTurn(){
        int x, y;
        do{
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x,y));
        field[y][x] = AI_DOT;
    }

    private boolean checkWIn(char c){
        /**
         * to do win check
         */
        return false;
    }

    private boolean isMapFull(){
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }
}
