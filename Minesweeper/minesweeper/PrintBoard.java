public class PrintBoard{
  // boolean method printboard
  // 2d char array static board
  // 1d int array choice
  // 2d char array dynamic
  public static boolean printboard(char[][] actualboard, char[][] blankboard, int row, int col, boolean flag){
    // boolean bomb to determine whether a bomb is in a specific location or not
    boolean bomb;
    // i and j can be in for loop, decided to initialize them outisde of it
    int x = 0;
    int y = 0;
    // dynamicboard represents what the board will become
    // this is the first for loop to set the condition of being less than row length
    if(flag){
      bomb = false;
    }else{
    //check column and row they input
    blankboard = RevealCell.revealCell(actualboard, blankboard, row, col);
      if(actualboard[row][col] == 'B'){
        bomb  = true;
      }else{
        bomb = false;
      }
    }
    for(x = 0; x < actualboard[0].length; x++){
      System.out.println(" ");
      for(y = 0; y < actualboard.length; y++){
        System.out.print(blankboard[x][y]);
      }
    }
    // return bomb so that it can be passed through main method
    System.out.println(" ");
    return bomb;
  }
}
