public class DayOfTheWeek {

 public static void main(String[] args) {
 
  /*
  Chuanxi ZHENG 260760794
  */
 
  //Declaring the variables for year(y), month(m), day(d)
  int y,m,d;
  //Initialisation of the variables y,m and d with the input arguments 
  y = Integer.valueOf(args[0]);
  m = Integer.valueOf(args[1]);
  d = Integer.valueOf(args[2]);
  //Declaring the auxiliary variables used in the formula.
  int y0, m0, d0, x;
  
  
  //Your code Starts here
  
  double tempY, tempX, tempM,tempD; //use temporary doubles to replace int counterparts so that the data is more accurate.
  
  //applying the formulas using temporaty varibles.
  tempY = y - (14.0-m)/12.0;
  tempX = (int) (tempY + tempY/4.0 - tempY/100.0 + tempY/400.0);
  m0 = m + (12 * ((14 - m) /12) ) - 2 ;
  tempD = (d + tempX + 31.0*m0 / 12.0) % 7.0;
  y0 = (int) tempY;
 
 //the value of y will influence the value of following varibles, so we need to discuss it with two conditions 
  if (y % 400 ==0){
     x = (int) tempX;
     d0 = (int) tempD;
  }
  else{
     x = (int) tempX + 1; 
     d0 = (int) tempD +1;
     if (d0 == 7){
         d0 = 0;
     }
  }
  //associating numerical output with capital letter output
  if (d0 == 0){
      System.out.println("SUNDAY");
  }
  else if (d0 == 1){
      System.out.println("MONDAY");
  }
  else if (d0 == 2){
      System.out.println("TUESDAY");
  }
  else if (d0 == 3){
      System.out.println("WEDNESDAY");
  }
  else if (d0 == 4){
      System.out.println("THURSDAY");
  }
  else if (d0 == 5){
      System.out.println("FRIDAY");
  }
  else if (d0 == 6){
      System.out.println("SATURDAY");
  }
  //Your code Ends here
 }

}
