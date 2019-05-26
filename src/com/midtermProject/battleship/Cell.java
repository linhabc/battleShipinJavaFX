package com.midtermProject.battleship;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    public int x, y;
    public Ship ship = null;
    public boolean wasShot = false;
    public Image image;
    private Board board;

    public Cell(int x, int y, Board board) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
//        setFill(Color.CYAN);
        image = new Image(new File("src/resources/ocean.png").toURI().toString());
        setFill(new ImagePattern(image));
        setStroke(Color.BLACK);
    }

    public boolean shoot() {
    	AudioClip shootSound = new AudioClip(this.getClass().getResource("/resources/miss.wav").toString());
        wasShot = true;
        image = new Image(new File("src/resources/miss.png").toURI().toString());
        setFill(new ImagePattern(image));
//        setFill(Color.BLACK);

        if (ship != null) {
//        	System.out.println("shoot!");
        	shootSound = new AudioClip(this.getClass().getResource("/resources/hit.wav").toString());
            if (BattleshipMain.tickAnimationSoundButton == true) {
            	shootSound.play();
            }
        	ship.hit();
//            setFill(Color.RED);
            image = new Image(new File("src/resources/hit.png").toURI().toString());
            setFill(new ImagePattern(image));
            if (!ship.isAlive()) {
            	shootSound = new AudioClip(this.getClass().getResource("/resources/hit_sunk.wav").toString());
                if (BattleshipMain.tickAnimationSoundButton == true) {
                	shootSound.play();
                }
                board.ships--;
            }
            return true;
        }
        if (BattleshipMain.tickAnimationSoundButton == true) {
            shootSound.play();
        }
        return false;
    }
}
