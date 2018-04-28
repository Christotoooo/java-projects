//Student Name:Chuanxi ZHENG
//Student Number:260760794
public class Reservation {
  private String name = "";
  private Room roomReserved;

  //constructor of reservation
  public Reservation(Room r, String name){
    this.roomReserved = r;
    this.name = name;

  }
  
  public String getName(){
    return this.name;
  }
  
  public Room getRoom(){
    return this.roomReserved;
  }
}
