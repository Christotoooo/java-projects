public class JudgeScore {
 public static void main(String[] args) {
 
  /*
  Chuanxi ZHENG 260760794
  */ 
 
  //Declaring the variables for storing the judges scores.
  int judge1, judge2, judge3, judge4;
  judge1 = Integer.valueOf(args[0]);
  judge2 = Integer.valueOf(args[1]);
  judge3 = Integer.valueOf(args[2]);
  judge4 = Integer.valueOf(args[3]);
  
  //Your code Starts here
  int high = 0, low=0;
  //finding the highest score
  if (judge1 >= judge2 && judge1 >= judge3 && judge1 >= judge4){
  high = judge1;
  }
  if (judge2 >= judge1 && judge2 >= judge3 && judge2 >= judge4){
  high = judge2;
  }
  if (judge3 >= judge1 && judge3 >= judge2 && judge3 >= judge4){
  high = judge1;
  } 
  if (judge4 >= judge1 && judge4 >= judge2 && judge4 >= judge1){
  high = judge4;
  }
  //finding the lowest score
  if (judge1 <= judge2 && judge1 <= judge3 && judge1 <= judge4){
  low = judge1;
  }
  if (judge2 <= judge1 && judge2 <= judge3 && judge2 <= judge4){
  low = judge2;
  }
  if (judge3 <= judge1 && judge3 <= judge2 && judge3 <= judge4){
  low = judge3;
  }
  if (judge4 <= judge1 && judge4 <= judge2 && judge4 <= judge3){
  low = judge4;
  }
  
  double doubleAverage = judge1 + judge2 + judge3+ judge4 - low - high; //finding the sum of the two average numbers
  double average = doubleAverage / 2; //half the sum of the two average numbers
  System.out.println(average);
  
  //Your code Ends here
  
 }
}
