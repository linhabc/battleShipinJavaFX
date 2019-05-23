package com.midtermProject.battleship;

import java.util.concurrent.TimeUnit;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    public int x, y;
    public Ship ship = null;
    public boolean wasShot = false;

    private Board board;

    public Cell(int x, int y, Board board) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    public boolean shoot() {
        wasShot = true;
        setFill(Color.BLACK);
        
        if (ship != null) {
        	try {
				TimeUnit.SECONDS.sleep((long) 0.3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ship.hit();
            setFill(Color.RED);
            if (!ship.isAlive()) {
                board.ships--;
            }
            return true;
        }

        return false;
    }
}
