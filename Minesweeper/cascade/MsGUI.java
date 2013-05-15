// 111 Intro to Computer Science
// Spring 2013
// Minesweeper Project


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * This is a graphical user interface you can use to play Minesweeper. <br>
 * Knowledge of the code in here is not required for the class or tests.
 */
public class MsGUI extends JFrame {
  /**
   * Version 2 of the GUI.
   */
  private static final long serialVersionUID = 2L;

  /**
   * The size of the buttons displayed. You can change this constant to make
   * the buttons larger or smaller.
   */
  private static final int BUTTON_SIZE = 80;

  private Board board;
  private JButton[][] buttons;
  private JPanel gamePanel;
  private boolean isEnabled;

  /**
   * Constructs a new GUI minesweeper board. This constructor requires your
   * own Board class that you've been working on, and it will render the
   * arrays in that class on the screen.
   * @param board A board object to display.
   */
  public MsGUI(Board board) {
    super("Minesweeper");
    this.board = board;
    this.isEnabled = true;

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(board.getWidth() * BUTTON_SIZE, board.getHeight() * BUTTON_SIZE);

    gamePanel = new JPanel(new GridLayout(board.getHeight(), board.getWidth()));
    buttons = new JButton[board.getHeight()][board.getWidth()];

    for (int col = 0; col < board.getHeight(); col++) {
      for (int row = 0; row < board.getWidth(); row++) {
        JButton b = new JButton("" + board.getValue(row, col));
        b.setBackground(new Color(200, 200, 200));
        b.addMouseListener(new MineSquareListener(row, col));
        buttons[row][col] = b;
        gamePanel.add(b);

      }

    }

    this.add(gamePanel);
    this.setVisible(true);

  }

  /**
   * Shows a pop-up dialog indicating that the user lost the game. You can set
   * what message shows inside the dialog.
   * @param s The String message displayed in the message box.
   */
  public void lose(String s) {
    JOptionPane.showMessageDialog(this, s, "You Lose!",
        JOptionPane.ERROR_MESSAGE);
    setGridEnabled(false);

  }

  /**
   * Shows a pop-up dialog indicating that the user won the game. You can set
   * what message shows inside the dialog.
   * @param s The String message displayed in the message box.
   */
  public void win(String s) {
    JOptionPane.showMessageDialog(this, s, "You Win!",
        JOptionPane.INFORMATION_MESSAGE);
    setGridEnabled(false);

  }

  /**
   * Enables or disables clicking of the grid.
   * @param option If set to true, allows the user to click buttons; if set
   * to false, clicking events are disabled.
   */
  private void setGridEnabled(boolean option) {
    isEnabled = option;

  }

  /**
   * This inner class listens for click events on the buttons. <br>
   * Knowledge of the implementation of this code is not required for
   * class or exams.
   */
  private class MineSquareListener extends MouseAdapter {
    private int x, y;

    /**
     * Sets up a new click listener for the specified cell. 
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     */
    public MineSquareListener(int x, int y) {
      super();
      this.x = x;
      this.y = y;

    }

    @Override
      public void mousePressed(MouseEvent e) {
        if(!isEnabled)
          return;
        if (e.getButton() == MouseEvent.BUTTON1)
          board.revealCell(x, y);
        else {
          if(board.isFlagged(x, y))
            board.unflagCell(x, y);
          else
            board.flagCell(x, y);

        }

        JButton b;
        for (int i = 0; i < board.getHeight(); i++) {
          for (int j = 0; j < board.getWidth(); j++) {
            b = buttons[i][j];
            char c = board.getValue(i, j);
            b.setText("" + c);
            if(c == ' ' || Character.isDigit(c))
              b.setBackground(Color.WHITE);

          }

        }
        repaint();

      }

  }

}
