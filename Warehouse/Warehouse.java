package assignment2;

public class Warehouse{

 protected Shelf[] storage;
 protected int nbShelves;
 public Box toShip;
 public UrgentBox toShipUrgently;
 static String problem = "problem encountered while performing the operation";
 static String noProblem = "operation was successfully carried out";
 
 public Warehouse(int n, int[] heights, int[] lengths){
  this.nbShelves = n;
  this.storage = new Shelf[n];
  for (int i = 0; i < n; i++){
   this.storage[i]= new Shelf(heights[i], lengths[i]);
  }
  this.toShip = null;
  this.toShipUrgently = null;
 }
 
 public String printShipping(){
  Box b = toShip;
  String result = "not urgent : ";
  while(b != null){
   result += b.id + ", ";
   b = b.next;
  }
  result += "\n" + "should be already gone : ";
  b = toShipUrgently;
  while(b != null){
   result += b.id + ", ";
   b = b.next;
  }
  result += "\n";
  return result;
 }
 
  public String print(){
   String result = "";
  for (int i = 0; i < nbShelves; i++){
   result += i + "-th shelf " + storage[i].print();
  }
  return result;
 }
 
  public void clear(){
   toShip = null;
   toShipUrgently = null;
   for (int i = 0; i < nbShelves ; i++){
    storage[i].clear();
   }
  }
  
  /**
   * initiate the merge sort algorithm
   */
 public void sort(){
  mergeSort(0, nbShelves -1);
 }
 
 /**
  * performs the induction step of the merge sort algorithm
  * @param start
  * @param end
  */
 protected void mergeSort(int start, int end){
  //ADD YOUR CODE HERE
   int mid = 0;
   if(start<end){
     mid = (start+end)/2;     
     mergeSort(start,mid);
     mergeSort(mid+1,end);
     merge(start,mid,end);
   }
 }
 
 /**
  * performs the merge part of the merge sort algorithm
  * @param start
  * @param mid
  * @param end
  */
 protected void merge(int start, int mid, int end){
  //ADD YOUR CODE HERE
   Shelf[] helper =  new Shelf[storage.length];
   for(int i = start;i<=end;i++){
     helper[i] = storage[i];
   }
   int i = start;
   int j = mid +1;
   int k = start;
   while(i<=mid && j<= end){
     if(helper[i].height<=helper[j].height){
       storage[k] = helper[i];
       i++;
     }else{
       storage[k] = helper[j];
       j++;
     }
     k++;
   }
   while(i<=mid){
     storage[k] = helper[i];
     k++;
     i++;
   }
   while(j<=end){
     storage[k] = helper[j];
     k++;
     j++;
   }
   /* int n1=mid-start+1;
   int n2=end-mid;
   Shelf L[] = new Shelf[n1];
   Shelf R[] = new Shelf[n2];
   for(int i = 0; i<n1; ++i){
     L[i] = storage[i+start];
   }
   for(int j =0; j<n2; ++j){
     R[j] = storage[mid+1+j];
   }
   //initialise indexes for both temp arrays
   int i = 0; 
   int j =0;
   int k = start;
   while(i<n1 && j<n2){
     if(L[i].height<=R[j].height){
       storage[k] = L[i];
       i++;
     }else{
       storage[k] = R[j];
       j++;
     }
     k++;
   }
   //copy the remaining elements
   while(i<n1){
     storage[k] = L[i];
     i++;
     k++;
   }
   while(j<n2){
     storage[k] = R[j];
     j++;
     k++;
   }*/
 }
 
 /**
  * Adds a box is the smallest possible shelf where there is room available.
  * Here we assume that there is at least one shelf (i.e. nbShelves >0)
  * @param b
  * @return problem or noProblem
  */
 public String addBox (Box b){
  //ADD YOUR CODE HERE
   for(int i =0;i<storage.length;i++){
     if(storage[i].availableLength>=b.length && storage[i].height>=b.height){
       storage[i].addBox(b);
       //storage[i].availableLength -= b.length;//update length
       return noProblem;
     }
   }
   return problem;
 }
 
 /**
  * Adds a box to its corresponding shipping list and updates all the fields
  * @param b
  * @return problem or noProblem
  */
 public String addToShip (Box b){
  //ADD YOUR CODE HERE
  //urgent
  if( b instanceof UrgentBox){
    if(toShipUrgently==null){ //empty shelf
      toShipUrgently = (UrgentBox) b;
      toShipUrgently.previous = null;
      toShipUrgently.next = null;
      return noProblem;
    }else{
      toShipUrgently.previous = b;
      b.next = toShipUrgently;
      b.previous = null;
      toShipUrgently = (UrgentBox) b; 
      return noProblem;
    }
  }else if(b instanceof Box){
    if(toShip==null){//was empty
     toShip = b;
     toShip.next= null;
     toShip.previous = null;
     return noProblem;
    }else{
      toShip.previous = b;
      b.next = toShip;
      b.previous = null;
      toShip = b;
      return noProblem;
    }
  }
  return problem;
 }
 
 /**
  * Find a box with the identifier (if it exists)
  * Remove the box from its corresponding shelf
  * Add it to its corresponding shipping list
  * @param identifier
  * @return problem or noProblem
  */
 public String shipBox (String identifier){
  //ADD YOUR CODE HERE
   Box temp = null;
   String str = "";
   for(int i =0;i<storage.length;i++){
     temp = storage[i].removeBox(identifier);
     if(temp!= null){
       //System.out.println(temp.id +"!!!!!!!");
       str = addToShip(temp);
       //System.out.println(str);
       if(str.equals(noProblem)){
         return noProblem;
       }
     }
   }
  return problem;
 }
 
 /**
  * if there is a better shelf for the box, moves the box to the optimal shelf.
  * If there are none, do not do anything
  * @param b
  * @param position
  */
 public void moveOneBox (Box b, int position){
  //ADD YOUR CODE HERE

   for(int i =0;i<position;i++){
     if(storage[i].availableLength>= b.length && storage[i].height>=b.height){ 
    	 
    	 //System.out.println();
    	 
    	storage[position].removeBox(b.id);
    	//System.out.println("in the shelf"+i);
    	storage[i].addBox(b);
       //System.out.println(b.id+ " ehhh");
       break;
     }
   }
 } 
 /**
  * reorganize the entire warehouse : start with smaller shelves and first box on each shelf.
  */
 public void reorganize (){
  //ADD YOUR CODE HERE

   for(int i =0;i<storage.length;i++){
     Box temp = storage[i].firstBox;
     if (temp==null){
       continue;
     }
    // System.out.println("hahaha "+temp.id);
     
     //System.out.println("Reorganizing is happening");
   // System.out.println(storage[i].firstBox);
  /*   if(temp.next==null){
    	 this.moveOneBox(temp,i);
    	//temp = null;
     }else{
    	 while(temp.next!=null){
    		temp
    		 
    	 }
     }
     */

    // System.out.println(temp.id+" looking");
  //System.out.println(this.print());//test
  //System.out.println(storage[i].firstBox.id+ " wuwuwhaha" + i);
  while(temp!=null){
    // System.out.println(temp.id+" heloo");
	     Box temp2 = temp.next;
 //    System.out.println(temp.id + "this?");
    //System.out.println(this.print());//test
    
    //System.out.println(i+ " hahaha");
    
     this.moveOneBox(temp,i);
 //    System.out.println(temp.id+"!!!!!");
 //    System.out.println(temp.id+" here boy");
    //System.out.println(this.print());//test
     temp = temp2;
  }
     /*System.out.println(storage[4].firstBox.next.id+ " hahalalala");
     this.moveOneBox(temp,i);
        System.out.println(temp.id+" looking");
     System.out.println(this.print());//test
     System.out.println(storage[i].firstBox.id+ " wuwuw");
     while(temp.next!=null){
        System.out.println(temp.id+" heloo");
        temp = temp.next;
        System.out.println(temp.id + "this?");
        System.out.println(this.print());//test
        this.moveOneBox(temp,i);
        System.out.println(temp.id+" here boy");
        System.out.println(this.print());//test
     }*/
    // System.out.println(storage[4].firstBox.next.id+ " wuwuw");

   }
 }
}