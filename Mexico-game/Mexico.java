//Chuanxi ZHENG 260760794

public class Mexico{
  
  public static void main(String[] args){
    
    playMexico(Double.parseDouble(args[0]),Double.parseDouble(args[1]));   //call from the main method.
  }
  
  //method of generating a random number
  public static int diceRoll(){
    int x = (int) ( 6 * Math.random() ) + 1;
    return x;
  }
  //combining two integers into a new integer
  public static int getScore( int a, int b ){
    int number = 0;
    if (a >= b){
      number = a * 10 + b;
    } else {
      number = b * 10 + a;
    }
    return number;
  }

  public static int playOneRound(String playerName){
    int a = diceRoll();                     //generating two random numbers
    int b = diceRoll();                     //
    int outputScore = getScore(a ,b);       //getting the output number
    System.out.println(playerName + " rolled: "+ a + " " + b);
    System.out.println(playerName + "'s score is: " + outputScore);
    return outputScore;
  }

  public static String getWinner(int x, int y){
    String resultOfOneRound = " ";          //initialisation. Waiting for another value
    if (x > y){
      resultOfOneRound = "Giulia wins this round";

    }  if (x < y){
      resultOfOneRound = "David wins this round"; // Dummy value. Waiting for another value. 
                                                  // Highly likely to be replaced

    }  if (x % 11 == 0 && y % 11 != 0){
      resultOfOneRound = "Giulia wins this round";

    }  if (x % 11 != 0 && y % 11 == 0){            //Checking if is multiple of eleven
      resultOfOneRound = "David wins this round";

    }  if (x == y) {
      resultOfOneRound = "It's a tie. Roll again";

    }  if (x == 21 && y != 21){
      resultOfOneRound = "Giulia wins this round";

    }  if (x != 21 && y == 21){                     //Checking if is "Mexico 21"
      resultOfOneRound = "David wins this round";

    } 
    return resultOfOneRound;
  }
  
 // method of advanced condition checking
  public static boolean canPlay(double buyIn, double pay){
    boolean temp = false;                         // dummy value
    if (buyIn > 0 && pay > 0 && buyIn >= pay && buyIn % pay == 0){
      temp = true;
    }
    return temp;
  }
  
  public static void playMexico(double buyIn, double payPerTime){
    if (canPlay(buyIn,payPerTime)==false){                         //Condition1: unable to play
       System.out.println("The fund is insufficient. Unable to play.");
       return;
    } else {                                                       //Condition2: able to play, then proceed
       int roundNumber = 1;
       double GiuliaBudget = buyIn;                                
       double DavidBudget = buyIn;                                 //Initialising budgets
       
       while(GiuliaBudget>=payPerTime && DavidBudget>=payPerTime){
         System.out.println();
         System.out.println("Round " + roundNumber);
         System.out.println();
         int a = playOneRound("Giulia");
         int b = playOneRound("David");                             //call method of random number
         getWinner(a,b);                                            //call method of judging
         System.out.println(getWinner(a,b));                        //print out the one-round result

         //Three conditions to check to ensure the change of players' budgets
         if (getWinner(a,b).equals("Giulia wins this round")){
           DavidBudget -= payPerTime;
           roundNumber++;
        }  
         else if (getWinner(a,b).equals("David wins this round")){
           GiuliaBudget -= payPerTime;
           roundNumber++;
        }  
         else if (getWinner(a,b).equals("It's a tie. Roll again")){
           GiuliaBudget = GiuliaBudget;
           DavidBudget = DavidBudget;
        }
       }
       //End game conditions
       if (GiuliaBudget<payPerTime){
         System.out.println();
         System.out.println("David won the game!");
       } else if (DavidBudget<payPerTime){
         System.out.println();
         System.out.println("Giulia won the game!");
       }
     }
  }
  
}