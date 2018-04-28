
public class PracticingLoops {
  
  
  public static void main(String[] args) { 
    System.out.println(isPrime(9));
  }
  //first n primes
  public static void firstNPrimes(int n ){
    int i = 0;
    int number = 2;
    while(i<n){
      if (isPrime(number)){
        System.out.println(number);
        i++;
      }
      number++;
    }
  }
  
  
  //first m multiples of n
  public static void firstMMultiples(int m, int n){
    for (int i = 1; i<=m; i++){
      System.out.print(i*n + "\t");
    }
    System.out.println();
  }
  public static void MultiTable( int rows, int cols){
    for(int i = 1; i<=rows;i++){
        firstMMultiples(i, cols);
  
    }
  }
  
  public static void printAlphabet(){
      char letter = 'a';
      while (letter <= 'z'){
      System.out.print(letter + " ");
      letter++;
    }
    }
     public static void printMultiples(int m, int n){
      int mult = m;
      while(mult < n){
        System.out.print(mult);
        mult += m;
      }    
     }
     
     public static boolean isPrime(int n) {
       if (n<2){
         return false;
       }

       int d = 2;
       while (d<n){
         if (n%d == 0){
            return false;    
         } 
         d++;
       }
       return true;
     
     }
     
     
     
  }
  
  

