public class Main{
  // initialize static board
  // static board = what the board is(all squares revealed)

  public static void main(String[] args){
    // obviously naming the method(main) and declaring the array string which contains all of the below information
    boolean answer;
    boolean l00p;
    // loop required to run each condition
    boolean flag;
    // make sure flagging a specific square is taken care of
    // to represent the row of the array
    int row;
    // to represent the col of the array
    int col;
      // this array below will serve as the answer for the entire array
    char[][] actualboard =
    {
      {'1', 'B', '2', '2', 'B'},
      {'2', '3', 'B', '2', '1'},
      {'1', 'B', '3', '2', '1'},
      {'1', '1', '2', 'B', '1'},
      {'0', '0', '1', '1', '1'},
    };
    //this serves as the user's playboard
    char[][] blankboard =
      // this array below will serve as the blankboard, where it will be changed as the user checks each row/column
    {
      {'x', 'x', 'x', 'x', 'x'},
      {'x', 'x', 'x', 'x', 'x'},
      {'x', 'x', 'x', 'x', 'x'},
      {'x', 'x', 'x', 'x', 'x'},
      {'x', 'x', 'x', 'x', 'x'},
    };
    //two nested do loops to check where the user is choosing
    do{
      //ask them if they want to flag a square!
      System.out.println("Do you want to flag a square?");
      //then set variable boolean flag to point to that reference of true or false
      // initialized flag initially for this reason exactly
      // variable flag, as a boolean, allows for us to manipulate it 
      // in such a way that it makes it easier to see if we flag it or not
      // we flag by replacing an x with an F
      flag = IO.readBoolean();
      // decide which row the user wants to check
      System.out.println("Please choose a row you wish to check:");
      // decides where to flag/reveal
      System.out.println("Please choose a point you want to flag or reveal:");
      do{
        // again, find out where the user wants to go
        System.out.println("Select a row from 1 to 5:");
        //read that row input!
        row = IO.readInt();
        // read that int
        // this do while loop runs with the condition of checking whether or not the row is in bounds
      }while(row < 1 || row > 5);
      // a do while loop to make sure the user didn't mess up and go out of bounds
      do{
        // same thing as done before for row
        System.out.println("Select a col from 1 to 5");
        col = IO.readInt();
      // same concept as row
      }while(col < 1 || col > 5);
      // the index of an arr is not what the user would think, but is instead 1 less
      // to represent the col of the array
      // fill in the arr with the users newly manipulated row and col variables
      // row = row - 1
      row -= 1;
      // col = col -1
      col -= 1;
        // if it is true that user wanted to F a certain point, flag it!
        //this part is important so that we know where to flag and to set a char in the char array
      if(flag){
        blankboard[row][col] = 'F';
      }
      // this boolean is set to determine whether the game has been lost or it has been won
      boolean loss_survival = PrintBoard.printboard(actualboard, blankboard, row, col, flag);
        // if its true that there was a bomb, then the game is over
      if(loss_survival){
        System.out.println("You lost :(");
        System.out.println("You can always try again :)");
        return;
      }
      // if its not, the loop keeps going
      if(!loss_survival){
        System.out.println("Keep it up!");
      }
      //just like while(true) just bein fanceh with the zeroes and such
    }while(l00p = true);
  }
}
