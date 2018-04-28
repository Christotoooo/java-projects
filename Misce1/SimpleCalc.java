/**
 * Auto Generated Java Class.
 */
public class SimpleCalc {
  
  
  public static void main(String[] args) { 
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[2]);
    String operator = args[1];
    int result = 0;
    if (operator.equals("+")){
      result = x+y;
    }else if (operator.equals("-")){
      result = x - y;
    }else if (operator.equals("*")){
      result = x *y;
    }else if (operator.equals("/")){
      result = x / y;
    }else {
 
    }
  }
  

  
}
