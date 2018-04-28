//Name: Chuanxi Zheng
//ID: 260760794
import java.util.*;
import java.lang.*;
import java.io.*;

public class Tweet {
  private String userAccount = "";
  private String date = "";
  private String time = "";
  private String message = "";
  public static HashSet<String> stopWords = null; 
  
  //main method
  public static void main(String[] args) { 

  }
  
  //constructor Tweet
  public Tweet(String userAccount, String date, String time, String message){
    this.userAccount = userAccount;
    this.date = date;
    this.time = time;
    this.message = message;
  }
  
  //method to check if the message is valid
  public boolean checkMessage() throws NullPointerException{
    if(null == stopWords){
      throw new NullPointerException();

    }
    //delete appended signs
    String[] temp = this.getMessage().split(" ");
    for(int i = 0; i<temp.length;i++){
      if(temp[i].charAt(temp[i].length()-1)==','||temp[i].charAt(temp[i].length()-1)==':'||temp[i].charAt(temp[i].length()-1)==';'||temp[i].charAt(temp[i].length()-1)=='.'){
        temp[i] = temp[i].substring(0,temp[i].length()-2);
      }
    }
    //delete stop words by setting stop words into null
    for(int j = 0; j<temp.length;j++){
      if(stopWords.contains(temp[j].toLowerCase())){
        temp[j] = null;
      }
    }
    //count, do not count null(originally stop words)
    int count = 0;
    for(int k = 0; k<temp.length;k++){
      if(temp[k]!=null){
        count = count + 1;
      }
    }
    //check if in range
    if(count>1&&count<16){
      return true;
    }else{
      return false;
    }
  }

  public String getDate(){
    return this.date;
  }
  
  public String getTime(){
    return this.time;
  }
  
  public String getMessage(){
    return this.message;
  }
  
  public String getUserAccount(){
    return this.userAccount;
  }
  
  public String toString(){
    String temp = this.userAccount+'\t'+date+'\t'+time+'\t'+message;
    return temp;
  }
    
  //it returns true when this tweet
  //was posted at an earlier time than the input parameter
  public boolean isBefore(Tweet input){
    String[] Date1 = this.getDate().split("-");
    String[] Date2 = input.getDate().split("-");
    String[] Time1 = this.getTime().split(":");
    String[] Time2 = input.getTime().split(":");
    //compare date first
    for(int i =0;i<3;i++){
      if (Integer.parseInt(Date1[i])<Integer.parseInt(Date2[i])){
        return true;
      } else if(Integer.parseInt(Date1[i])>Integer.parseInt(Date2[i])){
        return false;
      }
    }
    //compare time then
    for(int j =0;j<3;j++){
      if (Integer.parseInt(Time1[j])<Integer.parseInt(Time2[j])){
        return true;
      } else if(Integer.parseInt(Time1[j])>Integer.parseInt(Time2[j])){
        return false;
      }
    }
    //if all elements are exactly the same
    return false;
  }
  
  public static void loadStopWords(String fileName){
    try{
      stopWords = new HashSet<String>();
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);
      String currentLine = br.readLine();
      while(currentLine != null){
        stopWords.add(currentLine);
        currentLine = br.readLine();
      }
      br.close();
      fr.close();
     // System.out.println(stopWords);
    }
    catch(IOException e){
      System.out.println("Something wrong with the file");
    }
  }
}
