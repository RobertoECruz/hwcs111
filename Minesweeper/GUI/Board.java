public class Board{

  int arb_row = IO.readInt();
  int arb_col = arb_row, count = 0;
  int num_bombs = arb_row;
  char[][] blank_board = new char[arb_row][arb_col];
  char[][] arbitrary_board = new char[arb_row][arb_col];

  public MsGUI gui; //this is a field needed to be able to call win/lose

  public static void main(String[] args){
    System.out.println("How many arb_rows would you like?");
    Board b = new Board();
    b.gui = new MsGUI(b);
    b.gui.setVisible(true);
  }

  public Board(){
    blank_board = new char[arb_row][arb_col];
    for(int z = 0; z < blank_board.length; z++){
      for(int y = 0; y < blank_board[0].length; y++){
        blank_board[z][y] = 'x';
      }
    }

    arbitrary_board = new char[arb_row][arb_col];
    for(int a = 0; a < num_bombs; a++){
      int rx = (int)(Math.random()*num_bombs);
      int ry= (int)(Math.random()*num_bombs);
      if(arbitrary_board[rx][ry] == 'B'){
        a--;
        continue;
      }else{
        arbitrary_board[rx][ry] = 'B';
      }
    }
    for(int b = 0; b < arbitrary_board.length; b++){
      for(int c = 0; c < arbitrary_board[0].length; c++){
        this.count = 0;
        if(arbitrary_board[b][c] != 'B'){
          if(b-1 >= 0){
            if(arbitrary_board[b-1][c] == 'B'){
              this.count++;
            }
          }
          if(b-1 >= 0 && c-1 >= 0){
            if(arbitrary_board[b-1][c-1] == 'B'){
              this.count++;
            }
          }
          if(b+1 < arb_col && c+1 < arb_col){
            if(arbitrary_board[b+1][c+1] == 'B'){
              this.count++;
            }
          }
          if(b+1 < arb_col && c-1 >= 0){
            if(arbitrary_board[b+1][c-1] == 'B'){
              this.count++;
            }
          }
          if(b+1 < arb_col){
            if(arbitrary_board[b+1][c] == 'B'){
              this.count++;
            }
          }
          if(c+1 < arb_row){
            if(arbitrary_board[b][c+1] == 'B'){
              this.count++;
            }
          }
          if(c-1 >= 0){
            if(arbitrary_board[b][c-1] == 'B'){
              this.count++;
            }
          }
          if(b-1 >= 0 && c+1 < arb_row){
            if(arbitrary_board[b-1][c+1] == 'B'){
              this.count++;
            }
          }
          //convert int to char to be placed into arbitrary_board
          String i_toS = Integer.toString(count);
          char fill_arbitrary = i_toS.charAt(0);
          arbitrary_board[b][c] = fill_arbitrary;
        }
      }
    }
  }

  public void flagCell(int arb_row, int arb_col){
    if(blank_board[arb_row][arb_col] == 'x' || blank_board[arb_row][arb_col] == 'F'){
      blank_board[arb_row][arb_col] = 'F';
    }
    return;
  }

  public boolean isFlagged(int arb_row, int arb_col){
    if(blank_board[arb_row][arb_col] == 'F'){
      return true;
    }
    return false;
  }

  public int getHeight(){
    return arbitrary_board[0].length;
  }

  public int getWidth(){
    return arbitrary_board.length;
  }

  public char getValue(int arb_row, int arb_col){
    return blank_board[arb_row][arb_col];
  }

  public void revealCell(int arb_row, int arb_col){
    if(blank_board[arb_row][arb_col] == arbitrary_board[arb_row][arb_col] || blank_board[arb_row][arb_col] == 'F'){
    }
    if(arbitrary_board[arb_row][arb_col] == 'B'){
      gui.lose("Game Over :(");
      return;
    }else if(arbitrary_board[arb_row][arb_col] != '0'){
      //in other words change blank_board with actual_board's arb_row/arb_cols
      blank_board[arb_row][arb_col] = arbitrary_board[arb_row][arb_col];
    }
    else{
      //now we know that the user actually did hit 0 so we have to act accordingly
      //first step is to fill in blank_board
      blank_board[arb_row][arb_col] = arbitrary_board[arb_row][arb_col];
      for(int x = arb_row - 1; x < arb_row + 2; x++){
        //now the nested for loop, which requires the same information as x, but just for y
        //obviously x represents arb_rows and y represents arb_columns
        for(int y = arb_col - 1; y < arb_col + 2; y++){
          //first if statement check to see user's input is where you are currently located
          //if you check the same spot that the user just put, then it will result in an infinite loop
          if(!(x == arb_row && y == arb_col))
            // x is arb_rows and y is arb_columns thats why x is attributed to actual_board.length and y is attributed to actual_board[0].length
            // also check to see if the spot where we are located is not 0, so that we just reveal it and not everything around it
            if(x < arbitrary_board.length && x >= 0 && y < arbitrary_board[0].length && y >= 0 && blank_board[x][y] != '0'){
              //a lot of issues here, so this is a debugging statement to see where the program stops checking/where it checks
              System.out.println(x + " " + y + " ");
              if(arbitrary_board[x][y] != '0'){
                blank_board[x][y] = arbitrary_board[x][y];
              }else{
                revealCell(x, y);
              }
            }
        }
      }
    }
      int new_count = 0;
      for(int g = 0; g < arbitrary_board.length; g++){
        for(int h = 0; h < arbitrary_board[0].length; h++){
          //System.out.print(" " + blank_board[g][h] + " ");
          if(blank_board[g][h] != 'x' || blank_board[g][h] == 'F'){
            new_count++;
          }
        }
        //System.out.println();
      }
      System.out.println(new_count);
      if(new_count >= ((this.arb_row*this.arb_col)-num_bombs)){
        gui.win("You won!");
        return;
      }
  }

  public void unflagCell(int arb_row, int arb_col){
    if(blank_board[arb_row][arb_col] == 'F'){
      blank_board[arb_row][arb_col] = 'x';
    }
    return;
  }
}
