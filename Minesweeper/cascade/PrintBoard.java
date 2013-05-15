public class PrintBoard{
  // boolean method printboard
  // 2d char array static board
  // 1d int array choice
  // 2d char array dynamic
  public static boolean printboard(char[][] actualboard, char[][] blankboard, int row, int col, boolean flag){
    // boolean bomb to determine whether a bomb is in a specific location or not
    boolean bomb;
    // x and y can be in for loop, decided to initialize them outisde of it
    int x = 0;
    int y = 0;
    // blankboard represents what the board will become
    //obviously if the user is flagging then no actual will be taken EXCEPT flagging
    if(flag){
      bomb = false;
    }else{
    //check column and row they input
    //and reassign it to the new board!
    blankboard = RevealCell.revealCell(actualboard, blankboard, row, col);
      //must check to see if a bomb was located
      //if so pass through the boolean bomb as true
      if(actualboard[row][col] == 'B'){
        bomb  = true;
      }else{
        //else, nope
        bomb = false;
      }
    }
    //check to see if x is in bounds, where x is column
    for(x = 0; x < actualboard[0].length; x++){
      //if it is space it out!
      System.out.println(" ");
      //next check to see where y is, make sure it is in bounds
      for(y = 0; y < actualboard.length; y++){
        //print out the new board!
        //that is being passed through with revealboard
        System.out.print(blankboard[x][y]);
      }
    }
    // return bomb so that it can be passed through main method
    //also print out a space!
    System.out.println(" ");
    return bomb;
  }
}
