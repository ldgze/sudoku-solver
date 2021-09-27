package SudokuModel;

import DLX.*;
import SudokuCommon.SudokuBoard;

import java.util.ArrayList;
import java.util.Iterator;

public class SudokuSolver {
    int SIZE, N;
    public SudokuBoard solution;
    DLXImpl dlx;
    ConstraintMatrix matrix;

    public SudokuSolver(SudokuBoard puzzle) {
        this.SIZE = puzzle.SIZE;
        this.N = SIZE*SIZE;
        this.matrix = new ConstraintMatrix(puzzle);
        this.dlx = new DLXImpl(matrix);
        this.solution = new SudokuBoard(SIZE);
    }

    public boolean solve() {
        ArrayList ans =dlx.solve();
        if (ans == null) {
            return false;
        }

        int[] result = new int[N*N];
        Iterator it = ans.iterator();
        while(it.hasNext()) {
            int number = -1;
            int cellNo = -1;
            Node element = (Node)it.next();
            Node next = element;
            do {
                if (next.columnHead.constraint == SudokuConstraint.ROW) {
                    number = next.columnHead.number;
                }
                else if (next.columnHead.constraint == SudokuConstraint.SLOT) {
                    cellNo = next.columnHead.position;
                }
                next = next.right;
            } while(element != next);
            result[cellNo] = number;
        }

        int resultCounter=0;
        for (int r=0; r<N; r++)
        {
            for (int c=0; c<N; c++)
            {
                solution.board[r][c]=result[resultCounter];
                resultCounter++;
            }
        }
        return true;
    }

}
