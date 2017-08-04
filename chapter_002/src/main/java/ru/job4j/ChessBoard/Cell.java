package ru.job4j.ChessBoard;

/**
 * Class for cell of figure.
 * @author atrifonov
 * @since 04.08.2017
 * @version 1
 */
public class Cell {
    /**
     * X coordinate of cell.
     */
    private int x;

    /**
     * Y coordinate of cell.
     */
    private int y;

    /**
     * Construct Cell with x and y.
     * @param x X coordinate of cell.
     * @param y Y coordinate of cell.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter x coordinate.
     * @return X coordinate of cell
     */
    public int getX() {
        return x;
    }

    /**
     * Getter x coordinate.
     * @return Y coordinate of cell
     */
    public int getY() {
        return y;
    }
}
