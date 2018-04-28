z//Name: Chuanxi Zheng
//ID: 260760794

import java.util.*;
import java.lang.*;
import java.io.*;

public class Twitter{
  
  private ArrayList<Tweet> tweets; //= new ArrayList<Tweet>();
  
  
  public static void main(String[] args) { 
Twitter example = new Twitter();
Tweet.loadStopWords("stopWords.txt");
example.loadDB("tweets.txt");
System.out.println(example.trendingTopic());
  }

  //constructor
  public Twitter(){
    tweets = new ArrayList<Tweet>();
  }
  
  public void loadDB(String fileName){
    try{
      boolean b = false;
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);
      String userAccount, date, time, message;
      Tweet dummy;
      String[] temp; 
      String temp2 = br.readLine();
      while(temp2!=null){
        temp = temp2.split("\t");
        userAccount = temp[0];
        date = temp[1];
        time = temp[2];
        message = temp[3];
        dummy = new Tweet(userAccount,date,time,message);
        //test: System.out.println(dummy.getDate());
        try{
        b = dummy.checkMessage();
        }
        catch(NullPointerException n){
          System.out.println("Error checking the stopWords database: The file of stopWords has not been loaded yet");
          return;
        }
        if(b){
            tweets.add(dummy);
        }
        temp2 = br.readLine();
      }
      br.close();
      fr.close();
    }
    catch(IOException e){
      System.out.println("IOException");
    }

    sortTwitter();
  }
  
  //helper method sway two tweets
  private void swapTweets(int i, int j){
    Tweet temp = tweets.get(i);
    tweets.set(i,tweets.get(j));
    tweets.set(j,temp);
  }
  
  //sort all the tweets in the arrayList
  public void sortTwitter(){
    int size = tweets.size();
    for(int i =0;i<size;i++){
      for(int j = size-1;j>i;j--){
        if(tweets.get(j).isBefore(tweets.get(i))){ //exchange positions if A is later than B
          this.swapTweets(i,j);
        }
      }
    }
    
  }
  
 
  public int getSizeTwitter(){
    int num = tweets.size(); 
    return num;
  }
  
  public Tweet getTweet(int index){
    if(index>=tweets.size()||index<0){
      System.out.println("Index out of bound");
      return null;
    }else{
      return tweets.get(index);
    }
  }
  
  public String printDB(){
    String temp = "";
    for(int i = 0; i<tweets.size();i++){
      temp = temp + tweets.get(i).toString() + "\n";
    }
    return temp;
  }
  
  //return the tweets in the given range
  public ArrayList<Tweet> rangeTweets(Tweet bound1, Tweet bound2){
    int index1=0;
    int index2=0;
   
    for(int i = 0; i<tweets.size();i++){
      if(sameDateTime(bound1,tweets.get(i))){
        index1 = i;
      }
      if(sameDateTime(bound2,tweets.get(i))){
        index2 = i;
      }
    }
    //if the upper and lower bounds are in wrong order, swap them
    if(index1>index2){
      int temp = index1;
      index1 = index2;
      index2 = temp;
    }
    
    ArrayList<Tweet> range = new ArrayList<Tweet>();
    for (int a = index1;a<index2+1;a++){
      range.add(tweets.get(a));
      System.out.println(a);
    } 
    return range;
  }
  
  public void saveDB(String fileName)throws IOException{
    FileWriter fw = new FileWriter(fileName);
    BufferedWriter bw = new BufferedWriter(fw);
    String message = this.printDB();
    bw.write(message);
    bw.close();
    fw.close();
  }
  
   
  //not including stop words
  public String trendingTopic(){
    String[] temp;
    HashMap<String, Integer> topic = new HashMap<String,Integer>();
    for(int i = 0; i<tweets.size();i++){
      temp = tweets.get(i).getMessage().split(" ");
      //delete duplicates
      temp = new HashSet<String>(Arrays.asList(temp)).toArray(new String[0]);
      for(int j =0;j<temp.length;j++){
        if(temp[j].endsWith(",")||temp[j].endsWith(".")||temp[j].endsWith(";")||temp[j].endsWith(":")){
          temp[j] = temp[j].substring(0,temp[j].length()-1);
        }
        if(!topic.containsKey(temp[j])){
          topic.put(temp[j],1);
        }else{
          topic.put(temp[j],topic.get(temp[j])+1);
        }
      }
    }
    String key = "";
    Integer currLargestTopicCount = 0;
    for(HashMap.Entry<String, Integer> entry: topic.entrySet()){
      if(currLargestTopicCount < entry.getValue()&&(!Tweet.stopWords.contains(entry.getKey()))){
        key = entry.getKey();
        currLargestTopicCount = entry.getValue();
      }
    }
    return key;
  }
  
  //helper method
   private boolean sameDateTime(Tweet tweet1, Tweet tweet2){
    String[] Date1 = tweet1.getDate().split("-");
    String[] Date2 = tweet2.getDate().split("-");
    String[] Time1 = tweet1.getTime().split(":");
    String[] Time2 = tweet2.getTime().split(":");
    //compare date first
    boolean b =true;
    boolean c = true;
    if(Integer.parseInt(Date1[0])==Integer.parseInt(Date2[0])&&Integer.parseInt(Date1[1])==Integer.parseInt(Date2[1])&&Integer.parseInt(Date1[2])==Integer.parseInt(Date2[2])){
        b = true;
    }else{
      b = false;
    }
    if(Integer.parseInt(Time1[0])==Integer.parseInt(Time2[0])&&Integer.parseInt(Time1[1])==Integer.parseInt(Time2[1])&&Integer.parseInt(Time1[2])==Integer.parseInt(Time2[2])){
      c = true;
    }else{
      c = false;
    }
    return b&&c;
  }
  
  
  
}
