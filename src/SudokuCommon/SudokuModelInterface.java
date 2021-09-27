package SudokuCommon;

public interface SudokuModelInterface {


    SudokuBoard solve();

    boolean isLegal(int row, int col, int userVal);

    void setBoardVal(int row, int col, int userVal);
}
