//Student Name:Chuanxi ZHENG
//Student Number:260760794

public class Room {
  private String type = "";
  private double price = 0.0;
  private boolean availability = false;
  
  //constructor of a room with one of three types
  public Room(String t){
    if(!(t.equals("double")||t.equals("queen")||t.equals("king"))){
      throw new IllegalArgumentException("No room of such type can be created");
    }else if(t.equals("double")){
      this.type = "double";
      this.price = 90.0;
      this.availability = true;
    }else if(t.equals("queen")){
      this.type = "queen";
      this.price = 110.0;
      this.availability = true;
    }else if(t.equals("king")){
      this.type = "king";
      this.price = 150.0;
      this.availability = true;
    }
  }
  
  public String getType(){
    return this.type;
  }
  
  public double getPrice(){
    return this.price;
  }
  
  public boolean getAvailability(){
    return this.availability;
  }
  
  public void changeAvailability(){
    this.availability = !this.availability;
  }
  
  public static Room findAvailableRoom(Room[] r,String roomType){
    for(int i = 0; i<r.length;i++){
      if(r[i].getType().equals(roomType)){ //match the room
        return r[i];
      }
    }
    return null; //found nothing
  }
  
  
}
