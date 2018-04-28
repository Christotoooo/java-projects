public class ISBN {

 public static void main(String[] args) {
 
  /*
  Chuanxi ZHENG 260760794
  */
 
  //Declaring the variable to represent the ISBN number
        int n = Integer.parseInt(args[0]);
        
        
     //Your code Starts here
        int d5 = n / 1000 ; //The leading number of the ISBN
        int d4 = (n - d5*1000) / 100 ; //The second number of the ISBN
        int d3 = (n - d5*1000 - d4*100) / 10 ; //The third number of the ISBN 
        int d2 = n - d5*1000 - d4*100 - d3*10 ; //The fourth number of the ISBN
        int sum = 5*d5 + 4*d4 + 3*d3 + 2*d2 ; //The sum of the input.
        int remainder = sum % 11 ; //The remainder of the sum dividing eleven.
        int d1 = 11 - remainder ; //The number we look for is the difference between 11 and the remainder.

        // Three conditions
        if (d1 == 11){
           d1 = 0; 
           System.out.println("The final number is 0.");
        }
        else if (d1 == 10){
           System.out.println("The final number is X.");
        }
        else{
           System.out.println("The final number is " + d1);
        }
     //Your code Ends here
 }

}
