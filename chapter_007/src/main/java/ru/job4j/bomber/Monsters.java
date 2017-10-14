package ru.job4j.bomber;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for person Monsters.
 * @author atrifonov.
 * @version 1.
 * @since 14.10.2017.
 */
public class Monsters {
    /**
     * Playing field.
     */
    private final ReentrantLock[][] board;
    /**
     * Length of playing field.
     */
    private final int LENGTH;
    /**
     * Width of playing field.
     */
    private final int WIDTH;

    /**
     * Construct Monsters with playing field - ReentrantLock[][].
     * @param board field for play.
     */
    public Monsters(ReentrantLock[][] board) {
        this.board = board;
        this.LENGTH = board.length;
        this.WIDTH = board[0].length;

        if(this.LENGTH >= this.WIDTH) {
            for(int i = 0; i < (this.LENGTH * this.WIDTH) / 4; i++) {
                new Monster(i, 0).start();
            }
        } else {
            for(int i = 0; i < (this.LENGTH * this.WIDTH) / 4; i++) {
                new Monster(0, i).start();
            }
        }


    }

    private class Monster extends Thread{
        /**
         * Current coordinate x of Monster.
         */
        private int x;
        /**
         * Current coordinate y of Monster
         */
        private int y;

        /**
         * Construct Monster with started coordinate.
         * @param x coordinate x.
         * @param y coordinate y.
         */
        private Monster(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            boolean successMove = false;

            board[this.x][this.y].lock();

            int tempX = this.x;
            int tempY = this.y;

            while (!Thread.currentThread().isInterrupted()) {
                boolean endLength = (this.x == LENGTH);
                boolean endWidth = (this.y == WIDTH);

                int[] boundsX = computeBounds(this.x, LENGTH, endLength);
                int[] boundsY = computeBounds(this.y, WIDTH, endWidth);

                for(int i = boundsX[0]; i <= boundsX[i]; i++) {
                    for(int j = boundsY[0]; j <= boundsY[1]; j++) {
                        if(i != this.x && j != this.y) {
                            successMove = successLock(i, j);
                        }
                        if(successMove) {
                            this.x = i;
                            this.y = j;
                            i = tempX + 2;
                            j = tempY + 2;
                            board[this.x][this.y].unlock();
                        }
                    }
                }
                if(!successMove) {
                    Thread.currentThread().interrupt(); //monster dies from despair.
                }

                tempX = this.x;
                tempY = this.y;

                successMove = false;
            }
        }
    }



    /**
     * Compute new coordinate for move.
     * @param a current coordinate.
     * @param side maximum of coordinates.
     * @param finishCell current coordinate equal maximum of coordinates.
     * @return bounds for move of monster.
     */
    private int[] computeBounds(int a, int side, boolean finishCell) {
        int[] ar = new int[2];
        if(a % side != 0) {
            ar[0] = a - 1;
            ar[1] = a + 1;
        } else if(finishCell) {
            ar[0] = a - 1;
            ar[1] = a;
        } else {
            ar[0] = a;
            ar[1] = a + 1;
        }
        return ar;
    }

    /**
     * Monsters attempt to move on cell(x, y).
     * @param x coordinate x.
     * @param y coordinate y.
     * @return true, if monster locked cell(x, y).
     */
    private boolean successLock(int x , int y){
        boolean success = false;
        try {
           success = board[x][y].tryLock(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return success;
    }
}
