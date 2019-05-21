package com.midtermProject.battleship;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Ai {
	
	static final int EASY = 1;
	static final int MEDIUM = 2;
	static final int HARD = 3;
	static public int[] oneHittedPreviousMove = {-1,-1}; 	//{x,y}
    private static Random random = new Random();

	public static void enemyMoveEasy(boolean enemyTurn, Board playerBoard) {
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
	
	public static void enemyMoveMedium(boolean enemyTurn, Board playerBoard) {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            int direction = random.nextInt(4);
            int count = 0;
            //if direction == 0, new cell will be (x,y+1)
            //if direction == 1, new cell will be (x,y-1)
            //if direction == 2, new cell will be (x+1,y)
            //if direction == 3, new cell will be (x-1,y)
            Cell cell = playerBoard.getCell(x, y);
            //if chosen cell is ship and was hit, search for random cells nearby it 
            if (cell.wasShot && cell.ship!=null) {
            	{
            		direction = random.nextInt(4);
	            	switch (direction) {
	            		case 0: 
	            			if(y+1<10) {
	            				cell = playerBoard.getCell(x, y+1);
	            			}else {
	            				y=y++; // for breaking the while loop
	            			}
	            			break;
	            		case 1:
	            			if(y-1>=0) {
	            				cell = playerBoard.getCell(x, y-1);
	            			}else {
	            				y=y--;
	            			}
	            			break;
	            		case 2: 
	            			if(x+1<10) {
	            				cell = playerBoard.getCell(x+1, y);
	            			}else {
	            				x=x++;
	            			}
	            			break;
	            		case 3:
	            			if(x-1>=0) {
	            				cell = playerBoard.getCell(x-1, y);
	            			}else {
	            				x=x--;
	            			}
	            			break;
	            	}
	            	count++;
	            	if(count==4) {
	            		//if 4 direction around chosen cell is filled, re pick cell
	            		x = random.nextInt(10);
	            		y = random.nextInt(10);
	            		count=0;
	            	};
            	}while( y<0 || y==10 || x<0 || x==10 );
            }
            else if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }
	
	public static void enemyMoveHard(boolean enemyTurn, Board playerBoard){
		/***
		 * randomly choose x and y
		 * if cell is not hit, shoot it
		 * if cell is ship-part, and the ship is alive save it to previous move 
		 * search randomly for surrounding cell of the previous cell
		 */
		
		int x = 0;
		int y = 0;
		int count = 0;
		while (enemyTurn) {
    		System.out.println(oneHittedPreviousMove[0]+ "pd" + oneHittedPreviousMove[1]);
			 //check if previous move is hit a ship or not
			 if(oneHittedPreviousMove[1] == -1) {
	             x = random.nextInt(10);
	             y = random.nextInt(10);
			 }else {
				 do {
					 int direction = random.nextInt(4);
					 switch (direction) {
		         		case 0: 
		         			if(y+1<10) {
		         				x = oneHittedPreviousMove[0];
		         				y = oneHittedPreviousMove[1]++;
		         			}else {
		         				y=y++;
		         			}
		         			break;
		         		case 1:
		         			if(y-1>=0) {
		         				x = oneHittedPreviousMove[0];
		         				y = oneHittedPreviousMove[1]--;
		         			}else {
		         				y=y--;
		         			}
		         			break;
		         		case 2: 
		         			if(x+1<10) {
		         				x = oneHittedPreviousMove[0]++;
		         				y = oneHittedPreviousMove[1];
		         			}else {
		         				x=x++;
		         			}
		         			break;
		         		case 3:
		         			if(x-1>=0) {
		         				x = oneHittedPreviousMove[0]--;
		         				y = oneHittedPreviousMove[1];
		         			}else {
		         				x=x--;
		         			}
		         			break;
						 }
						 count++;
		            	if(count==4) {
		            		//if 4 direction around chosen cell is filled, re pick cell
		            		x = random.nextInt(10);
		            		y = random.nextInt(10);
		            		count=0;
		            	};
				 }while( y<0 || y==10 || x<0 || x==10 );
			}
			// after this step, we will have x and y

            Cell cell = playerBoard.getCell(x, y);
            //if chosen cell was hit, continue loop
            if (cell.wasShot)
                continue;
            
            
           //if hit a ship
           if(cell.ship != null) {
        	   System.out.println(cell.ship);
            	if(cell.ship.isAlive()) {
                    //if ship is still alive, save the previous hit shoot
            		oneHittedPreviousMove[0] = x;
            		oneHittedPreviousMove[1] = y;
            		System.out.println(x+ "d" + y);
//            		System.out.println(oneHittedPreviousMove[0]+ "pd" + oneHittedPreviousMove[1]);
            	} else {
            		//if ship is dead, reset previous move
            		oneHittedPreviousMove[0] = -1;
            		oneHittedPreviousMove[1] = -1;
            		System.out.println(x+ "n" + y);
            	} 
            }

           enemyTurn = cell.shoot();

	        if (playerBoard.ships == 0) {
	            System.out.println("YOU LOSE");
//	            System.exit(0);
	        }
        }
	}
}

