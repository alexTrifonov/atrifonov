package ru.job4j.ChessBoard;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class TestBishop {
    /**
     * Test way.
     */
    @Test(expected = ImposibleMoveException.class)
    public void whenCellBeyondTheBoundsYThenImposibleMoveException() {
        Bishop bishop = new Bishop(new Cell(0, 0));
        bishop.way(new Cell(0, 8));

    }

    /**
     * Test way.
     */
    @Test(expected = ImposibleMoveException.class)
    public void whenCellBeyondTheBoundsZeroYThenImposibleMoveException() {
        Bishop bishop = new Bishop(new Cell(0, 0));
        bishop.way(new Cell(0, -8));

    }

    /**
     * Test way.
     */
    @Test(expected = ImposibleMoveException.class)
    public void whenCellBeyondTheBoundsXThenImposibleMoveException() {
        Bishop bishop = new Bishop(new Cell(0, 0));
        bishop.way(new Cell(8, 0));

    }

    /**
     * Test way.
     */
    @Test(expected = ImposibleMoveException.class)
    public void whenCellBeyondTheBoundsZeroXThenImposibleMoveException() {
        Bishop bishop = new Bishop(new Cell(0, 0));
        bishop.way(new Cell(-1, 0));

    }

    /**
     * Test way.
     */
    @Test(expected = ImposibleMoveException.class)
    public void whenMoveBiasThenImposibleMoveException() {
        Bishop bishop = new Bishop(new Cell(0, 0));
        bishop.way(new Cell(1, 1));

    }

    /**
     * Test way.
     */
    @Test(expected = ImposibleMoveException.class)
    public void whenDistEqualPositionThenImposibleMoveException() {
        Bishop bishop = new Bishop(new Cell(0, 0));
        bishop.way(new Cell(0, 0));

    }

    /**
     * Test way.
     */
    @Test
    public void whenMoveTwoCellsYThenArrayCellWithSameX() {
        Bishop bishop = new Bishop(new Cell(0, 0));
        Cell[] result = bishop.way(new Cell(0, 2));

        Cell[] expected = {new Cell(0, 0), new Cell(0, 1), new Cell(0, 2)};
        for (int i = 0; i < result.length; i++) {
            assertThat(result[i].getX(), is(expected[i].getX()));
            assertThat(result[i].getY(), is(expected[i].getY()));
        }
    }

    /**
     * Test way.
     */
    @Test
    public void whenMoveTwoCellsXThenArrayCellWithSameY() {
        Bishop bishop = new Bishop(new Cell(0, 2));
        Cell[] result = bishop.way(new Cell(2, 2));

        Cell[] expected = {new Cell(0, 2), new Cell(1, 2), new Cell(2, 2)};
        for (int i = 0; i < result.length; i++) {
            assertThat(result[i].getX(), is(expected[i].getX()));
            assertThat(result[i].getY(), is(expected[i].getY()));
        }
    }

    /**
     * Test clone
     */
    @Test
    public void whenCloneFigureToNewDistThenSameKindFigureInNewDist() {
        Bishop bishop = new Bishop(new Cell(0, 0));
        Bishop cloneBishop = (Bishop) bishop.clone(new Cell(0, 2));
        assertThat(cloneBishop.occupyCell(new Cell(0, 2)), is(true));
    }
}
