/**
 * Auto Generated Java Class.
 */
public class InBetween {
  public static void main(String[] args){
    int x, y, z ;
    x = Integer.parseInt(args[0]);
    y = Integer.parseInt(args[1]);
    z = Integer.parseInt(args[2]);
    //x <= y <=z
    boolean xyz = (x <= y) && (y <= z );
    //z <= y <=x
    boolean zyx = (z <= y) && (y <= x );
    System.out.println(y + " is in between " + x + " and " + z);
    System.out.println(": " + (xyz || zyx));
  }
}
