// Chuanxi ZHENG
// ID: 260760794

import java.util.*;
import java.lang.String;
public class TicTacToe {  
  public static void main(String[] args) { 
    play();
  }
  
  //method for board creation
  public static char[][] createBoard(int n){
    char[][] a = new char[n][n];
    for(int i = 0; i<n; i++){
      for(int j = 0; j<n; j++){
        a[i][j] = ' ';
      }
    }
    return a;
  }
  
  //method for printling a single row line
  public static void rowLine(int n){
    for (int i = 0; i<n; i++){
      System.out.print("+-");
    }
    System.out.println('+');
  }
  
  //method for board display
  public static void displayBoard(char[][] a){
    int rowNumber = a.length;
    int colNumber = a[0].length;
    for (int i = 0; i<rowNumber;i++){
      rowLine(colNumber);
      for (int j = 0; j<colNumber;j++){
        System.out.print('|');
        System.out.print(a[i][j]);
      }
      System.out.println('|');
    }
    rowLine(colNumber);
  }
  
  //method for checkking whether a place is empty or not
  public static boolean spotIsEmpty(char n){
    boolean dummy = false;
    if (n == ' '){
      dummy = true;
      return dummy;
    }
    return dummy;
  }
  
  //method for writing on the board
  public static void writeOnBoard(char[][] a, char letter, int x, int y){
    //first check if valid
    int rowNumber = a.length;
    int colNumber = a[0].length;
    if (colNumber<=x || rowNumber<=y || x<0 || y<0){
      throw new IllegalArgumentException("Point out of the span. Please re-enter a new point.");
    }
    if (!spotIsEmpty(a[x][y])){
      throw new IllegalArgumentException("This spot has already been taken! Please re-enter a new point.");
    }
    a[x][y] = letter;
    
  }
  
  //method for getting and implementing user's input
  public static void getUserMove(char[][] a){
    //first check if valid
    int rowNumber = a.length;
    int colNumber = a[0].length;
    Scanner in = new Scanner(System.in);
    int row = in.nextInt();
    int col = in.nextInt();
    
    while (!(colNumber>=row && rowNumber>=row && spotIsEmpty(a[row][col]))){
      in.nextLine();//to clean buffer
      System.out.println("Please re-enter a new point.");
      in = new Scanner (System.in);
      row = in.nextInt();
      col = in.nextInt();
    }
    writeOnBoard(a,'x',row,col);
    
  }
  
  //A method that takes a 1 dimensional array of characters and a char c as input. The method counts
  //how many c's are in the array.
  public static int countRow(char[] a, char c){
    int count = 0;
    for (int i= 0; i<a.length;i++){
      if (a[i] == c){
        count = count + 1;
      }
    }
    return count;
  }
  //A method that counts the number of same characters on a frist diagonal
  public static int countDiag1(char[][] a, char c){
    int count = 0;
    for (int i = 0; i<a.length;i++){
      if (a[i][i] == c){
        count = count + 1;
      }
    }
    return count;
  }
  
  //A method that counts the number of same characters on a second diagonal
  public static int countDiag2(char[][] a, char c){
    int count = 0;
    for (int i = 0; i<a.length;i++){
      if (a[i][a.length-i-1] == c){
        count = count + 1;
      }
    }
    return count;
  }
  
  //A method that counts the number of same characters in a column
  public static int countCol(char[][] a, int col, char c){
    int count = 0;
    int rowNumber = a.length;
    for (int i = 0; i< rowNumber; i++){
      if (a[i][col] == c){
        count = count + 1;
      }
    }
    return count;
  }
  
  //method for a basic AI movement
  public static boolean checkForObviousMove(char[][] a){
    int rowNumber = a.length;
    int colNumber = a[0].length;
    //AI obvious to win
    for (int i = 0; i<rowNumber; i++){
      for (int j = 0; j<colNumber; j++){
        //check if on diagnol
        if (i == j && countDiag1(a, 'o')== (rowNumber-1) && spotIsEmpty(a[i][j])){
          writeOnBoard(a,'o',i,j);
          return true;
        }
        if (i+j == rowNumber-1 && countDiag2(a,'o')==(rowNumber-1) && spotIsEmpty(a[i][j])){
          writeOnBoard(a,'o',i,j);
          return true;
        }
        //check on a row
        if(countRow(a[i], 'o') == (colNumber-1) && spotIsEmpty(a[i][j])){
          writeOnBoard(a,'o',i,j);
          return true;
        }
        //check on a column
        if(countCol(a,j,'o') == (rowNumber-1) && spotIsEmpty(a[i][j])){
          writeOnBoard(a,'o',i,j);
          return true;
        }
      }
    }
    //when user obvious to win
    for (int i = 0; i<rowNumber; i++){
      for (int j = 0; j<colNumber; j++){
        //check if on diagnol
        if (i == j && countDiag1(a, 'x')== (rowNumber-1) && spotIsEmpty(a[i][j])){
          writeOnBoard(a,'o',i,j);
          return true;
        }
        if (i+j == rowNumber-1 && countDiag2(a,'x')==(rowNumber-1) && spotIsEmpty(a[i][j])){
          writeOnBoard(a,'o',i,j);
          return true;
        }
        //check on a row
        if(countRow(a[i], 'x') == (colNumber-1) && spotIsEmpty(a[i][j])){
          writeOnBoard(a,'o',i,j);
          return true;
        }
        //check on a column
        if(countCol(a,j,'x') == (rowNumber-1) && spotIsEmpty(a[i][j])){
          writeOnBoard(a,'o',i,j);
          return true;
        }
      }
    }
    return false;
  }
  
  //method for checking whether the input is in bound
  public static boolean isValid(char[][] a,int x, int y){
    if(x<a[0].length && y<a.length && x >= 0 && y>= 0){
      return true;
    }
    return false;
  }
  
  //when there's no obvious move
  public static void getAIMove(char[][] a){
    if(checkForObviousMove(a)){
      //  checkForObviousMove(a);
    }else{
      Random random = new Random();
      int x = random.nextInt(a.length);
      int y = random.nextInt(a.length);
      while(!(isValid(a,x,y)&& spotIsEmpty(a[x][y]))){
        random = new Random();
        x = random.nextInt(a.length);
        y = random.nextInt(a.length);
      } 
      writeOnBoard(a, 'o', x, y);
    }
  }
  
  //a method that checks for winner
  public static char checkForWinner(char[][] a){
    //check AI 'o' first
    for (int b = 0; b<a.length; b++){
      for (int j = 0; j<a[b].length; j++){
        if (b == j && countDiag1(a,'o')==a.length){
          return 'o';
        }
        if (b+j== a.length-1 && countDiag2(a,'o')==a.length){
          return 'o';
        }     
        if (countRow(a[b],'o')==a[b].length || countCol(a, b, 'o')==a.length){
          return 'o';
        } 
      }
    }
    //then check user 'x'
    for (int p = 0; p<a.length; p++){
      for (int q = 0; q<a[p].length; q++){
        if (p == q && countDiag1(a,'x')==a.length){
          return 'x';
        }
        if (p+q== a.length-1 && countDiag2(a,'x')==a.length){
          return 'x';
        }
        if (countRow(a[p],'x')==a[p].length || countCol(a, p, 'x')==a.length){
          return 'x';
        }  
      }
    }    
    return ' ';
  }
  
  //a method that checks if there is still space
  public static boolean stillSpace(char[][] a){
    boolean temp = false;
    for (int i = 0; i<a.length;i++){
      for (int j = 0; j<a[0].length;j++){
        if (a[i][j]==' '){
          temp = true;
        }
      }
    }
    return temp;
  }
  
  //the method to stimulate the play
  public static void play(){
    System.out.println("Please enter your name:");
    Scanner in = new Scanner(System.in);
    String userName = in.nextLine();
    System.out.println("Hello "+userName+"! Are you ready to play?");
    System.out.println("Please enter the dimension of your board:");
    while(!in.hasNextInt()){
      System.out.println("Please enter the dimension of your board:");
      in = new Scanner(System.in);
    }
    int n = in.nextInt();
    char[][] a = createBoard(n);
    int toss = (int)(2*Math.random());
    if (toss == 0){
      System.out.println("The result of the coint toss is: "+ toss);
      System.out.println("You have the first move");
    }
    else if (toss == 1){
      System.out.println("The result of the coint toss is: "+ toss);
      System.out.println("The AI has the first move");
    }
    //user first
    if (toss==0){
      while(checkForWinner(a)==' '&& stillSpace(a) == true){
        System.out.println("Please enter your move:");
        getUserMove(a);
        displayBoard(a);
        if(checkForWinner(a)!= ' '){
          System.out.println("GAME OVER!");
          System.out.println("You won");
          return;
        }
        if (stillSpace(a)){
          System.out.println("The AI made its move:");
          getAIMove(a);
          displayBoard(a);
          if(checkForWinner(a)!= ' '){
            System.out.println("GAME OVER!");
            System.out.println("You lost");
            return;
          }
        }
      }
      System.out.println("No one wins");
      return;
    }
    //AI first
    if (toss == 1){
      System.out.println("The AI made its move:");
      getAIMove(a);
      displayBoard(a);
      while(checkForWinner(a)==' '&& stillSpace(a) == true){
        System.out.println("Please enter your move:");
        getUserMove(a);
        displayBoard(a);
        if(checkForWinner(a)!= ' '){
          System.out.println("GAME OVER!");
          System.out.println("You won");
          return;
        }
        if (stillSpace(a)){
          System.out.println("The AI made its move:");
          getAIMove(a);
          displayBoard(a);
          if(checkForWinner(a)!= ' '){
            System.out.println("GAME OVER!");
            System.out.println("You lost");
            return;
          }
        }
      }
      System.out.println("No one wins");
      return;
    } 
  }
}
