package SudokuModel;

public enum SudokuConstraint {
    // Row(a, b) 表示第 a 行要有字母 b
    ROW(0),

    // Col(a, b) 表示第 a 列要有字母 b
    COL(1),

    // Sub(a, b) 表示第 a 个子方阵要有字母 b
    SUB(2),

    // Slot(a, b) 表示第 a 行和第 b 列的格子上要有字母
    SLOT(3);

    private int value;

    SudokuConstraint(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
