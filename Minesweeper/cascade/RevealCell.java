// class RevealCell, RevealCell is name of file and class.
public class RevealCell{
  //the method is revealCell, instantiate actualboard, blankboard, row, col
  //these array serve the purpose of filling the board with the answers and the board that is chaning
  //row and col represent the users inputs that are being pulled from the Main method.
  public static char[][] revealCell(char[][]actualboard, char[][] blankboard, int row, int col){
    //make sure that where the user picked wasn't actually a bomb, if it was end the game
    if(actualboard[row][col] == 'B'){
      gui.lose("Game Over :(");
      //return the board and show them where they failed.
      return blankboard;
    }else if(actualboard[row][col] != '0'){
      //well if its not a bomb, common sense would say to check whether it is 0 or greater!
      //and if it is, it is important to replace the board that is changing with the answer board
      //in other words change blankboard with actualboard's row/cols
      blankboard[row][col] = actualboard[row][col];
      //return the new changed board so that the user can continue to play!
      return blankboard;
    }else{
      //now we know that the user actually did hit 0 so we have to act accordingly
      //first step is to fill in blankboard
      blankboard[row][col] = actualboard[row][col];
      //two nested for loops and a nested if statement with an else is required for casacading reveal
      //condition of first loop is to make sure user is in bounds!
      //we do this by setting x to equal the input - 1 since we already subtracted 1 from the original input to account for
      //user's lack of knowledge on indexes of arrays
      //condition also set for row + 2 so that when x is reassigned you make sure it is in bounds!
      for(int x = row - 1; x < row + 2; x++){
        //now the nested for loop, which requires the same information as x, but just for y
        //obviously x represents rows and y represents columns
        for(int y = col - 1; y < col + 2; y++){
          //first if statement check to see user's input is where you are currently located
          //if you check the same spot that the user just put, then it will result in an infinite loop
          if(!(x == row && y == col))
            //next is the tricky part check to see if x is in bounds of the board
            // then to check if x is positive and same thing for y
            // x is rows and y is columns thats why x is attributed to actualboard.length and y is attributed to actualboard[0].length
            // also check to see if the spot where we are located is not 0, so that we just reveal it and not everything around it
            if(x < actualboard.length && x >= 0 && y < actualboard[0].length && y >= 0 && blankboard[x][y] != '0'){
              //a lot of issues here, so this is a debugging statement to see where the program stops checking/where it checks
              System.out.println(x + " " + y + " ");
              //now we check to see if it is actually a 0
              if(actualboard[x][y] != '0')
                //if not then reassign the value to 1/2/3/4 etc...
                blankboard[x][y] = actualboard[x][y];
              else
                //if not then we know it is a 0 so we call revealcell and replace blankboard with the new revealed board at x and y where x and y are rows and columns
                blankboard = revealCell(actualboard, blankboard, x, y);
            }
        }
      }
    }
    //obviously have to return the board so that the user can see it/so i can manipulate it!
    return blankboard;
  }
}
