package SudokuModel;

import SudokuCommon.SudokuBoard;
import SudokuCommon.SudokuModelInterface;

public class SudokuModel implements SudokuModelInterface {

    public SudokuBoard puzzle = new SudokuBoard(3);


    @Override
    public SudokuBoard solve() {
        SudokuSolver solver = new SudokuSolver(puzzle);
        if(solver.solve()) {
            return solver.solution;
        }
        return null;
    }

    @Override
    public boolean isLegal(int row, int col, int userVal) {
        /**int sec = (row /3) * 3 + col /3;
        //int pos =;
        for (int i = 0; i <9; i++) {
            if (puzzle.board[i][col] == userVal && i != row) {
                return false;
            }
            if (puzzle.board[col][i] == userVal && i != col) {
                return false;
            }
        }**/
        return true;
    }

    @Override
    public void setBoardVal(int row, int col, int userVal) {

        puzzle.board[row][col] = userVal;
    }
}
