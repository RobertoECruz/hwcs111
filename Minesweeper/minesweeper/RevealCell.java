public class RevealCell{
  public static char[][] revealCell(char[][]actualboard, char[][] blankboard, int row, int col){
    if(actualboard[row][col] == 'B'){
      System.out.println("Game Over :(");
        return blankboard;
    }else if(actualboard[row][col] != '0'){
      blankboard[row][col] = actualboard[row][col];
      return blankboard;
    }else{
      blankboard[row][col] = actualboard[row][col];
      for(int x = row - 1; x < row + 2; x++){
        for(int y = col - 1; y < col + 2; y++){
          if(!(x == row && y == col))
            if(x < actualboard.length && x >= 0 && y < actualboard[0].length && y >= 0 && blankboard[x][y] != '0'){
              System.out.println(x + " " + y + " ");
              if(actualboard[x][y] != '0')
                blankboard[x][y] = actualboard[x][y];
              else
                blankboard = revealCell(actualboard, blankboard, x, y);
            }
        }
      }
    }
    return blankboard;
  }
}
