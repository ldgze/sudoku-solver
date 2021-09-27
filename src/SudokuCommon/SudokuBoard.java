package SudokuCommon;

public class SudokuBoard {
    public int SIZE;
    public int N;
    public int[][] board;


    @Override
    public String toString() {
        String res = new String();
        for (int i =  0; i < N;i++ ) {
            for (int j = 0; j< N; j++) {
                res = res + " " + board[i][j];
            }
            res = res + "\n";
        }
        return res;
    }

    public SudokuBoard(int size) {
        this.SIZE = size;
        this.N = size * size;
        this.board = new int[N][N];
    }
}
