package ru.job4j.ChessBoard;

/**
 * Class for chessboard.
 * @author atrifonov
 * @since 04.08.2017
 * @version 1
 */
public class Board {

    /**
     * Figures on board.
     */
    private Figure[] figures;

    /**
     * Move figure from source on dist.
     * @param source First position of figure.
     * @param dist End position of figure.
     * @return True if source has figure and if way from source to dist don't have figure and if figure can make this move.
     * @throws ImposibleMoveException
     * @throws OccupiedWayException
     * @throws FigureNotFoundException
     */
    public boolean move(Cell source, Cell dist) throws ImposibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean moved = false;

        Figure figure = null;
        int index = 0;
        boolean occupy = false;
        for (int i = 0 ; i < this.figures.length; i++) {
            if (this.figures[i] != null && this.figures[i].occupyCell(source)) {
                occupy = true;
                figure = this.figures[i];
                index = i;
                break;

            }

        }
        if(!occupy) {
            throw new FigureNotFoundException("Figure not found.");
        }

        Cell[] traversedPath = figure.way(dist);
        for (Cell cell : traversedPath) {
            for(Figure x : this.figures) {
                if (x != null && x != figure && x.occupyCell(cell)) {
                    throw new OccupiedWayException("Way occupy " + x.getClass().getName());
                }
            }
        }

        Figure moveFigure = figure.clone(dist);
        this.figures[index] = moveFigure;
        moved = true;

        return moved;
    }

    /**
     * Setter figures.
     * @param figures Figures of board.
     */
    public void setFigures(Figure[] figures) {
        this.figures = figures;
    }

    /**
     * Getter figures.
     * @return Figures of board.
     */
    public Figure[] getFigures() {
        return figures;
    }
}