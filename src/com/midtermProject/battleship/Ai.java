package com.midtermProject.battleship;

import java.util.Random;

public class Ai {
	
	static final int EASY = 1;
	static final int MEDIUM = 2;
	static final int HARD = 3;
	
	static public int[][] previousMove = new int[2][2];
	
    private static Random random = new Random();

	@SuppressWarnings("unused")
	public static void enemyMove(boolean enemyTurn, Board playerBoard) {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }
	
	@SuppressWarnings("unused")
	public static void enemyMoveMedium(boolean enemyTurn, Board playerBoard) {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }
	
	@SuppressWarnings("unused")
	public static void enemyMoveHard(boolean enemyTurn, Board playerBoard) {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }
}
