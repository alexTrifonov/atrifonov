package ru.job4j.ChessBoard;

/**
 * Class for figures chessboard.
 * @author atrifonov
 * @since 04.08.2017
 * @version 1
 */
public abstract class Figure {
    /**
     * Position figure on board.
     */
    private final Cell position;

    /**
     * Construct figure with position on board.
     * @param position Objec of figure coordinates.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Move figure on dist.
     * @param dist New position of figure.
     * @return Array of traversed cells.
     * @throws ImposibleMoveException
     */
    public Cell[] way(Cell dist) throws ImposibleMoveException {
        if(dist.getX() == this.position.getX() && dist.getY() == this.position.getY()) {
            throw new ImposibleMoveException("Dist equal position");
        }
        Cell[] firstCell = new Cell[2];
        firstCell[0] = this.position;
        firstCell[1] = dist;

        return firstCell;
    }

    /**
     * Make same figure on new position.
     * @param dist New position of figure.
     * @return Same figure with new coordinates.
     */
    public abstract Figure clone(Cell dist);

    /**
     * Verify position on some cell.
     * @param aCell Cell that verify.
     * @return True if postiton of figure equal cell.
     */
    public boolean occupyCell(Cell aCell) {
        return this.position.getY() == aCell.getY() && this.position.getX() == aCell.getX();
    }
}