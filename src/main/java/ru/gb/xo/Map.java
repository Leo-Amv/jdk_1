package ru.gb.xo;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    Map(){
        setBackground(Color.DARK_GRAY);
    }
    void startNewGame(int mode, int fSzX,int fSzY,int wLen){
        System.out.printf("Mode: %d;\nSize: x= %d, y= %d;\nWin Length: %d",mode,fSzX,fSzY,wLen);
    }
}
