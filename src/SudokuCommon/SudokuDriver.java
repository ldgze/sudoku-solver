package SudokuCommon;

import SudokuModel.SudokuModel;
import SudokuView.SudokuGUIView;


public class SudokuDriver {

    public static void main(String[] args) {
        SudokuGUIView view = new SudokuGUIView();
        SudokuModel model = new SudokuModel();
        new SudokuController(model, view);

    }
}
