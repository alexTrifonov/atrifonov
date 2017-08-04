package ru.job4j.ChessBoard;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestBoard {
    /**
     * Test move.
     */
    @Test(expected = FigureNotFoundException.class)
    public void whenMoveAreNonExistentFigureThenThrowFigureNotFoundException() {
        Board board = new Board();
        Figure[] figures = {new Bishop(new Cell(0, 0))};
        board.setFigures(figures);
        board.move(new Cell(0, 1), new Cell(0, 2));
    }

    /**
     * Test move.
     */
    @Test(expected = OccupiedWayException.class)
    public void whenWayOccupyThenThrowOccupiedWayException() {
        Board board = new Board();
        Figure[] figures = {new Bishop(new Cell(0, 0)), new Bishop(new Cell(0, 1))};
        board.setFigures(figures);
        board.move(new Cell(0, 0), new Cell(0, 2));
    }

    /**
     * Test move.
     */
    @Test(expected = ImposibleMoveException.class)
    public void whenBiasMoveThenThrowImposibleMoveException() {
        Board board = new Board();
        Figure[] figures = {new Bishop(new Cell(0, 0))};
        board.setFigures(figures);
        board.move(new Cell(0, 0), new Cell(1, 1));
    }

    /**
     * Test move.
     */
    @Test
    public void whenGoodMoveThenHaveCloneSameIndex() {
        Board board = new Board();
        Bishop bishop = new Bishop(new Cell(0, 0));
        Figure[] figures = {bishop, null, null};
        board.setFigures(figures);
        board.move(new Cell(0, 0), new Cell(0, 2));
        assertThat(board.getFigures()[0].occupyCell(new Cell(0, 2)), is(true));
    }
}
