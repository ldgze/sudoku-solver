import SudokuCommon.SudokuBoard;
import SudokuModel.SudokuSolver;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuSolverTest {

    @Test
    public void solverTest() {
        SudokuBoard puzzel = new SudokuBoard(3);
        puzzel.board = new int[][]{
                {0, 0, 0, 0, 0, 9, 0, 0, 4},
                {0, 0, 0, 4, 0, 0, 6, 9, 0},
                {0, 0, 0, 7, 5, 0, 1, 0, 0},
                {0, 0, 0, 0, 3, 0, 7, 0, 0},
                {0, 1, 0, 0, 0, 0, 8, 4, 6},
                {0, 8, 0, 0, 4, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 3, 0, 0, 1},
                {0, 0, 0, 9, 7, 6, 0, 0, 0},
                {3, 0, 5, 0, 0, 0, 0, 0, 0}
        };
        SudokuSolver solver = new SudokuSolver(puzzel);
        boolean res = solver.solve();
        assertTrue(res);
        int[][] expected = new int[][]{
                {1, 7, 2, 3, 6, 9, 5, 8, 4},
                {5, 3, 8, 4, 1, 2, 6, 9, 7},
                {9, 4, 6, 7, 5, 8, 1, 3, 2},
                {6, 5, 4, 8, 3, 1, 7, 2, 9},
                {7, 1, 3, 2, 9, 5, 8, 4, 6},
                {2, 8, 9, 6, 4, 7, 3, 1, 5},
                {4, 9, 7, 5, 8, 3, 2, 6, 1},
                {8, 2, 1, 9, 7, 6, 4, 5, 3},
                {3, 6, 5, 1, 2, 4, 9, 7, 8}
        };
        assertEquals(expected, solver.solution.board);
    }
}
