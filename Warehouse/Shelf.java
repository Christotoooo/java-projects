package assignment2;

public class Shelf {
 
 protected int height;
 protected int availableLength;
 protected int totalLength;
 protected Box firstBox;
 protected Box lastBox;

 public Shelf(int height, int totalLength){
  this.height = height;
  this.availableLength = totalLength;
  this.totalLength = totalLength;
  this.firstBox = null;
  this.lastBox = null;
 }
 
 protected void clear(){
  availableLength = totalLength;
  firstBox = null;
  lastBox = null;
 }
 
 public String print(){
  String result = "( " + height + " - " + availableLength + " ) : ";
  Box b = firstBox;
  while(b != null){
   result += b.id + ", ";
   b = b.next;
  }
  result += "\n";
  return result;
 }
 
 /**
  * Adds a box on the shelf. Here we assume that the box fits in height and length on the shelf.
  * @param b
  */
 public void addBox(Box b){
  //ADD YOUR CODE HERE
   //if a shelf is null
   if(firstBox == null && lastBox == null){
	// System.out.println("+++");
     firstBox = b;
     lastBox = b;
     b.previous = null;
     b.next = null;
   }else{
     //find the originally last element
	// System.out.println("hi");
	 //System.out.println(lastBox.id);
     lastBox.next = b;
     b.previous = lastBox;
     lastBox = b;
     lastBox.next = null;
   }
   this.availableLength = this.availableLength - b.length;//update the available length
 }

 /**
  * If the box with the identifier is on the shelf, remove the box from the shelf and return that box.
  * If not, do not do anything to the Shelf and return null.
  * @param identifier
  * @return
  */
 public Box removeBox(String identifier){
  //ADD YOUR CODE HERE
  Box temp = firstBox;
  if(temp == null){  //if shelf is already empty
    return null;
  }
  while(temp!=null){
    if(temp.id==identifier){
      if(temp.next!=null){
       temp.next.previous = temp.previous;
       //System.out.println(this.print());
      }else{
        lastBox = temp.previous;
      }
      if(temp.previous!=null){
        temp.previous.next = temp.next;
       //System.out.println(this.print());
      }else{
        firstBox = temp.next;
      }
      temp.next=null;
      temp.previous=null;
      this.availableLength = this.availableLength + temp.length;//update the available 
     return temp;
    }
    temp = temp.next;
  }
  return null;
 /*if(temp.id.equals(identifier)){
    if(temp.next==null){//only one element on the shelf
      firstBox = null;
      lastBox = null;
      temp.previous = null;
      temp.next = null;
    }else{
      firstBox = temp.next;
      firstBox.previous = null;
      temp.next=null;
      temp.previous=null;
    }
    this.availableLength = this.availableLength + temp.length;//update the available length
    return temp;
  }
  while(temp.next != null){
    if(temp.id.equals(identifier)){
      temp.previous.next = temp.next;
      temp.next.previous = temp.previous;
      this.availableLength = this.availableLength + temp.length;//update the available length
      temp.next=null;
      temp.previous=null;
      return temp;
    }
    temp = temp.next;
  }
  if(temp.id.equals(identifier)&&temp.previous!=null){
    System.out.println(temp.id);
    System.out.println(temp.previous);
    temp.previous.next=null;
    temp.previous = null;
    //lastBox = temp.previous;
    this.availableLength = this.availableLength + temp.length;//update the available length
    return temp;
  }else if(temp.id.equals(identifier)&&temp.previous==null){
    System.out.println("!!!!!!!!!!");//test
    System.out.println();
    temp.previous =null;
    temp.next=null;
    firstBox=null;
    lastBox = null;  
    this.availableLength = this.availableLength + temp.length;//update the available length
    return temp;
  }
//  temp.next=null;
  //temp.previous=null;
  return null;*/
 }
 
}
