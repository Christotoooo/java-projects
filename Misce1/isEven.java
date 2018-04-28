//is even or not

public class isEven {
  public static void main(String[] args){
    int x = Integer.parseInt(args[0]);
    boolean isEven = ( x%2 == 0);
    System.out.println(x + " is even: " + isEven);
  }
}