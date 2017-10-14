package ru.job4j.bomber;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for person BomberMan.
 * @author atrifonov.
 * @version 1.
 * @since 11.10.2017.
 */
public class BomberMan extends Thread {
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
     * Construct BomberMan with playing field - ReentrantLock[][]
     * @param board field for play.
     */
    public BomberMan(ReentrantLock[][] board) {
        this.board = board;
        this.LENGTH = board.length;
        this.WIDTH = board[0].length;
    }

    @Override
    public void run() {
        boolean successMove = false;

        int x = LENGTH / 2;
        int y = WIDTH / 2;

        board[x][y].lock();

        int tempX = x;
        int tempY = y;

        while (!Thread.currentThread().isInterrupted()) {
            boolean endLength = (x == LENGTH);
            boolean endWidth = (y == WIDTH);

            int[] boundsX = computeBounds(x, LENGTH, endLength);
            int[] boundsY = computeBounds(y, WIDTH, endWidth);

            for(int i = boundsX[0]; i <= boundsX[i]; i++) {
                for(int j = boundsY[0]; j <= boundsY[1]; j++) {
                    if(i != x && j != y) {
                        successMove = successLock(i, j);
                    }
                    if(successMove) {
                        x = i;
                        y = j;
                        i = tempX + 2;
                        j = tempY + 2;
                        board[x][y].unlock();
                    }
                }
            }
            if(!successMove) {
                Thread.currentThread().interrupt();
            }

            tempX = x;
            tempY = y;

            successMove = false;
        }
    }

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