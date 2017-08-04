package ru.job4j.ChessBoard;

/**
 * Class for figure bishop.
 * @author atrifonov
 * @since 04.08.2017
 * @version 1
 */
public class Bishop extends Figure {

    /**
     * Construct objec Bishop with position.
     * @param position Position of bishop.
     */
    public Bishop(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell dist) throws ImposibleMoveException{
        Cell[] traversedPath = new Cell[1];
        Cell[] defaultTraversedPath = super.way(dist);
        Cell firstPosition = defaultTraversedPath[0];
        traversedPath[0] = firstPosition;
        if (dist.getX() > 7 || dist.getY() > 7 || dist.getX() < 0 || dist.getY() < 0) {
            throw new ImposibleMoveException("The cell beyond the bounds");
        }
        if (dist.getX() != firstPosition.getX() && dist.getY() != firstPosition.getY()) {
            throw new ImposibleMoveException("Bishop don't can to walk bias");
        }

        if (dist.getY() == firstPosition.getY()) {
            int traversedPathLength = Math.abs(dist.getX() - firstPosition.getX() + 1);
            traversedPath = new Cell[traversedPathLength];
            if(dist.getX() > firstPosition.getX()) {
                for (int i = 0; i < traversedPathLength; i++) {
                    traversedPath[i] = new Cell(firstPosition.getX() + i, firstPosition.getY());
                }
            } else {
                for (int i = 0; i < traversedPathLength; i++) {
                    traversedPath[i] = new Cell(firstPosition.getX() - i, firstPosition.getY());
                }
            }
        }

        if (dist.getX() == firstPosition.getY()) {
            int traversedPathLength = Math.abs(dist.getY() - firstPosition.getY() + 1);
            traversedPath = new Cell[traversedPathLength];
            if(dist.getY() > firstPosition.getY()) {
                for (int i = 0; i < traversedPathLength; i++) {
                    traversedPath[i] = new Cell(firstPosition.getX(), firstPosition.getY() + i);
                }
            } else {
                for (int i = 0; i < traversedPathLength; i++) {
                    traversedPath[i] = new Cell(firstPosition.getX(), firstPosition.getY() - i);
                }
            }
        }

        return traversedPath;
    }

    @Override
    public Bishop clone(Cell dist) {
        return new Bishop(dist);
    }
}

