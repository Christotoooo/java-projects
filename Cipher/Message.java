package assignment1;
public class Message {
 
 public String message;
 public int lengthOfMessage;

 public Message (String m){
  message = m;
  lengthOfMessage = m.length();
  this.makeValid();
 }
 
 public Message (String m, boolean b){
  message = m;
  lengthOfMessage = m.length();
 }
 
 /**
  * makeValid modifies message to remove any character that is not a letter and turn Upper Case into Lower Case
  */
 public void makeValid(){
  //clean all the non-alphabetic elements first

   this.message = message.replaceAll("[^a-zA-Z]","");
  //then convert all UPPERCASE to lowercase
  
   this.message = message.toLowerCase();

   lengthOfMessage = this.message.length();
 }
 
 /**
  * prints the string message
  */
 public void print(){
  System.out.println(message);
  System.out.println("OK");
 }
 
 /**
  * tests if two Messages are equal
  */
 public boolean equals(Message m){
  if (message.equals(m.message) && lengthOfMessage == m.lengthOfMessage){
   return true;
  }
  return false;
 }
 
 /**
  * caesarCipher implements the Caesar cipher : it shifts all letter by the number 'key' given as a parameter.
  * @param key
  */
 public void caesarCipher(int key){
  // INSERT YOUR CODE HERE
   String s = "";
   if(key>=26){
	   key = key%26;
   }
   if(key<=-26){
	   while(key<=-26){
		   key=key+26;
	   }
   }
   if(key>=0){
     for(int i = 0;i<this.lengthOfMessage;i++){
       if(this.message.charAt(i)==' '){
         s = s + ' ';
       }else{
         char c = (char)(this.message.charAt(i) + key);
         if(c>'z'){
           s = s + (char)(this.message.charAt(i)-(26-key));
         }else{
           s = s + (char)(this.message.charAt(i)+key);
         }
       }
     }
   }else{
     for(int i = 0 ;i<this.lengthOfMessage;i++){
       if(this.message.charAt(i)==' '){
         s = s +' ';
       }else{
         char c = (char)(this.message.charAt(i) + key);
         if(c<'a'){
           s = s + (char)(this.message.charAt(i) +(26+ key));
         }else{
           s = s + (char)(this.message.charAt(i) + key);
         }
       }
     }
   }
   this.message = s; // update
 }
 
 public void caesarDecipher(int key){
  this.caesarCipher(-key);
  //System.out.println(this.message);
 }
 
 /**
  * caesarAnalysis breaks the Caesar cipher
  * you will implement the following algorithm :
  * - compute how often each letter appear in the message
  * - compute a shift (key) such that the letter that happens the most was originally an 'e'
  * - decipher the message using the key you have just computed
  */
 public void caesarAnalysis(){
  // INSERT YOUR CODE HERE
   int[] temp = new int[26];
   for(int i =0;i<lengthOfMessage; i++){
     for(int j =97;j<=122;j++){
       if(this.message.charAt(i) == j){
         temp[j-97]++;
       }
     }
   }
   int most = 0;//initialisation
   for(int i = 0; i<26;i++){
     if(temp[i]>=temp[most]){
       most = i;
     }
   }
   //for(int i = 0;i<26;i++){
    // System.out.print(""+ temp[i]+" ");
   //}
   int key = most+97-101; //101 = 'e'
  //System.out.println(key);
 //System.out.println(temp[most]);
  // System.out.println(this.message);
   this.caesarDecipher(key);
   //lengthOfMessage = this.message.length();
   //System.out.println(this.message);
 }
 
 /**
  * vigenereCipher implements the Vigenere Cipher : it shifts all letter from message by the corresponding shift in the 'key'
  * @param key
  */
 public void vigenereCipher (int[] key){
  // INSERT YOUR CODE HERE
   int length = key.length;
   String temp = "";
   for (int i =0;i<lengthOfMessage;i++){
	   while(key[i%length]>=26){
		   key[i%length]=key[i%length]-26;
	   }
	   while(key[i%length]<=-26){
		   key[i%length]=key[i%length]+26;
	   }
     temp = temp + (char)(this.message.charAt(i) + key[i%length]);
   }
   this.message =temp;//update
 }

 /**
  * vigenereDecipher deciphers the message given the 'key' according to the Vigenere Cipher
  * @param key
  */
 public void vigenereDecipher (int[] key){
  // INSERT YOUR CODE HERE
   int length = key.length;
   String temp = "";
   for (int i =0;i<lengthOfMessage;i++){
	   while(key[i%length]>=26){
		   key[i%length]=key[i%length]-26;
	   }
	   while(key[i%length]<=-26){
		   key[i%length]=key[i%length]+26;
	   }
     temp = temp + (char)(this.message.charAt(i) - key[i%length]);
   }
   this.message =temp;//update
 }
 
 /**
  * transpositionCipher performs the transition cipher on the message by reorganizing the letters and eventually adding characters
  * @param key
  */
 public void transpositionCipher (int key){
  // INSERT YOUR CODE HERE
   int row = -1;
   if(lengthOfMessage % key == 0){
     row = lengthOfMessage / key;
   }else{
     row = lengthOfMessage/key +1;
   }
   char[][] temp = new char[row][key];
   String s = "";
   for(int i =0;i<row;i++){
     for(int j = 0;j<key;j++){
       if((key*i+j)>=lengthOfMessage){
         temp[i][j] = '*';
       }else{
         temp[i][j] = this.message.charAt(key*i + j);
       }
     }
   }
   for (int j = 0; j<key;j++){
     for(int i = 0;i<row;i++){
       s = s + temp[i][j];
     }
   }
 
   // System.out.println(s);
   this.message = s; //update
   lengthOfMessage = this.message.length();
 }
 
 /**
  * transpositionDecipher deciphers the message given the 'key'  according to the transition cipher.
  * @param key
  */
 public void transpositionDecipher (int key){
  // INSERT YOUR CODE HERE
   int row = lengthOfMessage / key;
   char[][] temp = new char[row][key];
   for (int j = 0;j<key;j++){
     for(int i = 0;i<row;i++){
       temp[i][j] = this.message.charAt(j*row + i);
     }
   }
   String s = "";
   for(int i = 0;i<row;i++){
     for(int j =0;j<key;j++){
       if(temp[i][j] == '*'){
         break;
       }
       s = s + temp[i][j];
     }
   }
   
   this.message = s;//update
   lengthOfMessage = this.message.length();
 }
 
}
