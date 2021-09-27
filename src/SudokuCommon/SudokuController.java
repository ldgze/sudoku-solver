package SudokuCommon;

import SudokuModel.SudokuModel;
import SudokuView.SudokuCell;
import SudokuView.SudokuGUIView;

import java.awt.event.*;

public class SudokuController {

    public SudokuController(SudokuModelInterface model, SudokuGUIView view) {
        this.model = new SudokuModel();
        this.view = new SudokuGUIView();

        this.view.solveB.addActionListener(solveActionListener());
        this.view.clearB.addActionListener(resetActionListener());

        for(int r= 0; r< N; r++) {
            for (int c = 0; c< N; c++) {
                this.view.viewBoard[r][c].addKeyListener(BoardKeyListener(r, c));
            }
        }

        this.view.paint();

    }

    private void solveGame() {

        SudokuBoard solution= model.solve();

        if (solution!=null) {
            view.setViewBoard(solution);
            view.msgLabel.setText("Solved");
        }
        else {
            view.msgLabel.setText("Invalid configuration");
        }
    }

    private void resetGame() {
        view.clearViewBoard();
        view.msgLabel.setText("New game");

    }

    private KeyListener BoardKeyListener(int i, int j) {
        return new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {

                // do nothing
            }

            @Override
            public void keyReleased(KeyEvent ke)
            {
                System.out.println("You released a key!!");

                SudokuCell s = (SudokuCell) ke.getSource();
                int userVal=0;
                try
                {
                    userVal = Integer.parseInt(s.getText());
                }
                catch(NumberFormatException e)
                {
                    s.setVal(0);

                }

                int row = s.getRow();
                int col = s.getCol();

                if(model.isLegal(row, col, userVal))
                {
                    model.setBoardVal(row, col, userVal);
                    s.setVal(userVal);
                }
                else
                {
                    s.setVal(0);
                }
            }
        };
    }




    private ActionListener resetActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        };
    }


    private ActionListener solveActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                solveGame();
            }
        };
    }

    private SudokuModelInterface model;
    private SudokuGUIView view;
    private int N=9;
}
