/**
 * Auto Generated Java Class.
 */
public class CharAndStrings {
  
  
  public static void main(String[] args) { 
    System.out.println(charRightShift('$',5));
  }
  public static void myPassword(String s){
      if (s.equals( "prettyPlease")){
        System.out.println(true);
      }else {
        System.out.println(false);
      }
    }
    
  public static char charRightShift(char c, int n){
    //check if lower letter
    if(c <'a' || c>'z'){
      return c;
    }

    char shifted = (char)(c+(n%26));
    if( shifted> 'z'){
      shifted = (char)(shifted -26);
    }
    
    return shifted;
  }
}
