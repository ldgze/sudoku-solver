package SudokuModel;

import SudokuCommon.SudokuBoard;

import java.util.ArrayList;

public class ConstraintMatrix {
    public int SIZE, N;
    public byte[][] matrix;

    // Python implementation: https://code.google.com/p/narorumo/wiki/SudokuDLX
    public ConstraintMatrix(SudokuBoard puzzle) {
        this.SIZE = puzzle.SIZE;
        this.N = SIZE*SIZE;
        this.matrix = new byte[N*N*N][4*N*N];

        int[][] clues = null;
        ArrayList cluesList = new ArrayList();
        int counter = 0;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(puzzle.board[r][c] > 0) {
                    cluesList.add(new int[]{puzzle.board[r][c],r,c}); // store the number, the row number and the column number
                    counter++;
                }
            }
        }
        clues = new int[counter][];
        for(int i = 0; i < counter; i++) {
            clues[i] = (int[])cluesList.get(i);
        }


        for(int v = 0; v < N; v++) {
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < N; c++) {
                    if(!filled(v,r,c,clues)) {
                        addRow(v,r,c);
                    }
                }
            }
        }
    }

    private void addRow(int v, int r, int c) {
        int rowIndex = c + (N * r) + (N * N * v);
        int blockIndex = ((c / SIZE) + ((r / SIZE) * SIZE));
        int colIndexRow = 3*N*v+r;
        int colIndexCol = 3*N*v+N+c;
        int colIndexSub = 3*N*v+2*N+blockIndex;
        int colIndexSlot = 3*N*N+(c+N*r);
        matrix[rowIndex][colIndexRow] = 1;
        matrix[rowIndex][colIndexCol] = 1;
        matrix[rowIndex][colIndexSub] = 1;
        matrix[rowIndex][colIndexSlot] = 1;
    }



    // credited to Alex Rudnick as cited above
    private boolean filled(int digit, int row, int col, int[][] prefill) {
        boolean filled = false;
        if(prefill != null) {
            for(int i = 0; i < prefill.length; i++) {
                int d = prefill[i][0]-1;
                int r = prefill[i][1];
                int c = prefill[i][2];
                // calculate the block indices
                int blockStartIndexCol = (c/SIZE)*SIZE;
                int blockEndIndexCol = blockStartIndexCol + SIZE;
                int blockStartIndexRow = (r/SIZE)*SIZE;
                int blockEndIndexRow = blockStartIndexRow + SIZE;
                if(d != digit && row == r && col == c) {
                    filled = true;
                } else if((d == digit) && (row == r || col == c) && !(row == r && col == c)) {
                    filled = true;
                } else if((d == digit) && (row > blockStartIndexRow) && (row < blockEndIndexRow) && (col > blockStartIndexCol) && (col < blockEndIndexCol) && !(row == r && col == c)) {
                    filled = true;
                }
            }
        }
        return filled;
    }
}
