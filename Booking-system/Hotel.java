//Student Name:Chuanxi ZHENG
//Student Number:260760794

import java.util.*;

public class Hotel{
  private String name = "";
  private Room[] roomArr;
  private Reservation[] resArr = new Reservation[0];
  
  //constructor of Hotel
  public Hotel(String name, Room[] arr){
    this.name = name;
    roomArr = new Room[arr.length];
    roomArr = arr;
  }
  
  //add a new reservation
  private void addReservation(Reservation res){
    Reservation[] updatedRes = new Reservation[resArr.length+1];
    for(int i = 0; i<resArr.length; i++){
      updatedRes[i] = resArr[i];
    }
    updatedRes[resArr.length] = res;
    resArr = updatedRes;
  }
  
  //remove a reservation
  private void removeReservation(String name, String type)throws NoSuchElementException{
    if(resArr.length == 0){
      throw new NoSuchElementException("No such an element");
    }
    int temp = -1;
    for(int i =0;i<resArr.length;i++){
      if(resArr[i].getName().equals(name) && resArr[i].getRoom().getType().equals(type)){
        temp = i; //found the matched room
      }
    }
    if(temp == -1){//no matched room
      throw new NoSuchElementException("No such an element");
    }else{ //update the new array of reservation
      Reservation[] updated = new Reservation[resArr.length-1];
      for (int j = 0; j<temp;j++){
        updated[j] = resArr[j];
      }
      for (int k = 0; k<resArr.length-1-temp;k++){
        updated[temp+k] = resArr[temp+k+1];
      }
      resArr = updated;
    }
    
  }
  //to create a new reservation. Call the previous method of addReservation
  public void createReservation(String name, String type){
    Room temp = Room.findAvailableRoom(roomArr,type);
    if(temp == null){
      System.out.println("There is no available rooms of this type");
    }else{
      Reservation r = new Reservation(temp,name);
      r.getRoom().changeAvailability();
      addReservation(r);
      System.out.println("The reservation was successfully completed!");
    }
  }
  
  //cancel a reservation using the previously defined removeReservation method
  public void cancelReservation(String name, String type){
    try{
      removeReservation(name,type);
    }
    catch(NoSuchElementException e){
      System.out.println("No reservation under such name has been made for the given type of room.");
      return;
    }
    System.out.println("The cancellation operation was successful.");
  }
  
  //to print out the bill of one person
  public void printInvoice(String name){
    double invoice = 0.0;
    for(int i = 0; i<resArr.length;i++){
      if(resArr[i].getName().equals(name)){
        if(resArr[i].getRoom().getType().equals("double")){
          invoice = invoice + 90.0;
        }
        else if(resArr[i].getRoom().getType().equals("queen")){
          invoice = invoice + 110.0;
        }
        else if(resArr[i].getRoom().getType().equals("king")){
          invoice = invoice + 150.0;
        }
      }
    }
    System.out.println("The invoice of "+name+" is "+invoice);
  }
  
  public String toString(){
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    for(int i = 0; i<roomArr.length;i++){
      if(roomArr[i].getType().equals("double")&&roomArr[i].getAvailability()==true){
        count1 = count1 + 1;
      }
      else  if(roomArr[i].getType().equals("queen")&&roomArr[i].getAvailability()==true){
        count2 = count2 + 1;
      }
      else  if(roomArr[i].getType().equals("king")&&roomArr[i].getAvailability()==true){
        count3 = count3 + 1;
      }
    }
    return "Hotel name: "+ name + " Available rooms: "+count1+ " double "+ count2 + " queen " + count3 + " king";
  }
  
}