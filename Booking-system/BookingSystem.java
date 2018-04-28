//Student Name:Chuanxi ZHENG
//Student Number:260760794
import java.util.Scanner;
import java.util.Random;

public class BookingSystem {
    
    private static String[] typeOfRooms = {"double","queen","king"};
    private static Random r = new Random(123);
    
    //returns a random String from the above array. 
    private static String getRandomType(){
        int index = r.nextInt(typeOfRooms.length);
        return typeOfRooms[index];
    }
    //returns a random number of rooms between 5 and 50.
    private static int getRandomNumberOfRooms(){
        return r.nextInt(50)+1;
    }
    //End of provided code. 
    
    public static void main(String[] args){
        //Student Name:Chuanxi ZHENG
        //Student Number:260760794
        //Your code goes here.    
      System.out.println("Please enter the name of your hotel:");
      Scanner in = new Scanner(System.in);
      String name = in.nextLine();
      Room[] roomArray = new Room[getRandomNumberOfRooms()];
      for(int i = 0; i<roomArray.length;i++){
        roomArray[i] = new Room(getRandomType());
      }
      Hotel h = new Hotel(name,roomArray);
      System.out.println("Welcome! Please choose one of the following options");
      System.out.println("1. Make a reservation");
      System.out.println("2. Cancel a reservation");
      System.out.println("3. See an invoice");
      System.out.println("4. See the hotel info");
      System.out.println("5. Exit the booking system");
      
      Scanner input = new Scanner(System.in);
      while(!input.hasNextInt()){
        input = new Scanner(System.in);
      }
      int choice = input.nextInt();
      
      while(choice!=5){
        //make a reservation
        if(choice == 1){
          System.out.println("Please enter your name:");
          input = new Scanner(System.in);
          String username = input.nextLine();
          System.out.println("What type of room would you like to reserve?");
          input = new Scanner(System.in);
          String usertype = input.nextLine();
          h.createReservation(username,usertype);
        }
        //cancel a reservation
        if(choice == 2){
          System.out.println("Please enter your name:");
          input = new Scanner(System.in);
          String username = input.nextLine();
          System.out.println("What type of room would you want to cancel?");
          input = new Scanner(System.in);
          String usertype = input.nextLine();
          h.cancelReservation(username,usertype);
        }
        //see an invoice
        if(choice == 3){
          System.out.println("Please enter your name:");
          input = new Scanner(System.in);
          String username = input.nextLine();
          h.printInvoice(username);
        }
        //see hotel information
        if(choice == 4){
          System.out.println(h.toString());
        }
        
        System.out.println("1. Make a reservation");
        System.out.println("2. Cancel a reservation");
        System.out.println("3. See an invoice");
        System.out.println("4. See the hotel info");
        System.out.println("5. Exit the booking system");
        
        input = new Scanner(System.in);
        while(!input.hasNextInt()){
          input = new Scanner(System.in);
        }
        choice = input.nextInt();
      }
      
      //Exit the booking system
      if(choice == 5){
        System.out.println("It was a pleasure doing business with you!");
      }
    }
}