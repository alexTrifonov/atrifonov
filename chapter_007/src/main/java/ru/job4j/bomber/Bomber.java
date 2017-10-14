package ru.job4j.bomber;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for person BomberMan.
 * @author atrifonov.
 * @version 1.
 * @since 13.10.2017.
 */
public class Bomber implements Runnable {
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
     * Destination coordinate on length of playing field.
     */
    private int  destX;

    /**
     * Destination coordinate on width of playing field.
     */
    private int  destY;

    /**
     * If one of methods move invoke tryMove = true. tryMove = false after try lock next cell.
     */
    private boolean tryMove = true;

    /**
     * Construct Bomber with playing field - ReentrantLock[][]
     * @param board field for play.
     */
    public Bomber(ReentrantLock[][] board) {
        this.board = board;
        this.LENGTH = board.length;
        this.WIDTH = board[0].length;

    }

    /**
     * User attempt move Bomber on right cell.
     */
    public void moveRight() {
        if(!this.tryMove && this.destX != this.LENGTH) {
            this.tryMove = true;
            this.destX++;
        }

    }

    /**
     * User attempt move Bomber on left cell.
     */
    public void moveLeft() {
        if(!this.tryMove && this.destX != 0){
            this.tryMove = true;
            this.destX--;
        }
    }

    /**
     * User attempt move Bomber on top cell.
     */
    public void moveUp() {
        if(!this.tryMove && this.destY != 0) {
            this.tryMove = true;
            this.destY--;
        }
    }

    /**
     * User attempt move Bomber on bottom cell.
     */
    public void moveDown() {
        if(!this.tryMove && this.destY != this.WIDTH) {
            this.tryMove = true;
            this.destY++;
        }
    }

    @Override
    public void run() {
        boolean successMove = false;
        int countAttempts = 0;

        int x = this.LENGTH / 2;
        int y = this.WIDTH / 2;

        this.board[x][y].lock();

        this.destX = x;
        this.destY = y;
        this.tryMove = false;

        while (!Thread.currentThread().isInterrupted()) {

            if(this.destX != x || this.destY != y) {
                successMove = successLock(this.destX, y);
                countAttempts++;
                this.tryMove = false;
            }
            if(successMove) {
                this.board[x][y].unlock();
                countAttempts = 0;
                x = this.destX;
                y = this.destY;
            } else {
                this.destX = x;
                this.destY = y;
            }


            if(countAttempts == 10) {
                Thread.currentThread().interrupt();
            }

            successMove = false;
        }
    }



    /**
     * Bomber attempt to move on cell(x, y).
     * @param x coordinate x.
     * @param y coordinate y.
     * @return true, if bomber locked cell(x, y).
     */
    private boolean successLock(int x , int y){
        boolean success = false;
        try {
            success = board[x][y].tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return success;
    }
}
