package assignment4Graph;

public class Graph {
	
	boolean[][] adjacency;
	int nbNodes;
	
	public Graph (int nb){
		this.nbNodes = nb;
		this.adjacency = new boolean [nb][nb];
		for (int i = 0; i < nb; i++){
			for (int j = 0; j < nb; j++){
				this.adjacency[i][j] = false;
			}
		}
	}
	
	public void addEdge (int i, int j){
		// ADD YOUR CODE HERE
		this.adjacency[i][j]=true;
		this.adjacency[j][i]=true;
		return;
	}
	
	public void removeEdge (int i, int j){
		// ADD YOUR CODE HERE
		this.adjacency[i][j]=false;
		this.adjacency[j][i]=false;
		return;
	}
	
	public int nbEdges(){
		// ADD YOUR CODE HERE
		int counter=0;
		for(int i =0;i<this.nbNodes; i++) {
			for(int j=i;j<this.nbNodes; j++) {
				if(this.adjacency[i][j]==true) 
				{
					counter = counter +1;
				}
			}
		}
		return counter; // DON'T FORGET TO CHANGE THE RETURN
	}
	

	 public boolean cycle(int start) {
		 //set up an array to record the visited
		 
		 
		 int[] record = new int[this.nbNodes];
		 
		 for (int i = 0; i < this.nbNodes; i++) {
			 
			 record[i] = -1;
			 //initially all to -1
		 }
		 
		 boolean temp = cycleHelperMethod(record, start, -1);
		 
		 return temp;
	 }

	 public int shortestPath(int start, int end) {
		 
		 /////////INITIALISATIONS////////////
		 int length = 0;
		 //use the last spot to store the destination
		 int[] record = new int[this.nbNodes + 1];
		 
		 
		 //if it is itself
		 if(start==end) {
			 return 0;
		 }
		 //if the wrong order
		 if(start>end) {
			 int temp = start;
			 start = end;
			 end = temp;
		 }

		 for (int i = 0; i < this.nbNodes; i++) {
			 record[i] = -1;
		 }
		 
		 record[this.nbNodes] = end;

		 length = lengthCalculator(record, start, -1) ;
		 
		 //that means there's not shortest path found 
		 if(length == 1001) 
		 {
			 return ( 1 + this.nbNodes );
		 }
		 else {
			 return length;
		 }
	 }
	 
	 
	 
	 
	 //////////////////////HELPER METHODS////////////////////////////

	 public boolean cycleHelperMethod(int[] record, int now, int was) {
		 //a boolean to check if the loop is heading back to origin
		 boolean isBackOrigin = false;

		 for (int j = 0; j < nbNodes; j++)
		 {
			 if (record[j] == -1) {
				 record[j] = now;
				 break;
			 }
		 }
		 
		 //end case
		 if (now == record[0] && was != -1 && was!=record[0]) {	 return true;}
		 
		 //clone a new object since copy does not work for reference type 
		 int [] newRecord = record.clone();
		 //
		 
		 for (int i=0;i<nbNodes;i++) {
			 
		 	if ( adjacency[now][i] && (i != was) && duplicateChecker(record, i) ) 
		 	{
		 		isBackOrigin = cycleHelperMethod(newRecord, i, now);
		 		
		 		//hit back to the origin where we start counting
		 		if (isBackOrigin)
		 		{
		 			return true;
		 		}
		 		
		 	}
		 }
		 return isBackOrigin;
	 }

	 // a helper method to check if one vertex has been already walked through
	 public boolean  duplicateChecker ( int[]  record,  int current ) {
		 
		 for (int k=1;k<this.nbNodes; k++ ) {
			 
			 //already went through 
			 if ( record[k] == current ) 
			 {
				 return false;
			 }
			 
		 }
		 return true;
	 }


	 public int lengthCalculator(int[] record, int currentVertex, int was) {

		 int length = 1000;
		
		 //temp variable for holding the sub length
		 int dummy = -1;
		 
		 for (int k=0;k<this.nbNodes; k++) {
			 
			 if ( record[k] == -1 ) {
				 record[k] = currentVertex;
				 break; //found the one to go
			 }
			 
		 }
		 if(record[0]==record[this.nbNodes]&&adjacency[currentVertex][currentVertex]) {
			 return 1;
		 }
		 if (record[this.nbNodes]==currentVertex && was!=-1) {
			 //it is the destination
			 return 0; 
		 }
		 
		 //
		 int[] newRecord = new int[this.nbNodes + 1];
		 for(int i = 0; i<this.nbNodes+1;i++) {
			 newRecord[i]=record[i];
			 //copy array object
		 }
		 
		 
		 for (int j = 0; j < this.nbNodes; j++) {
			 
			 if ( (j != was) && this.adjacency[currentVertex][j] 
					 && duplicateChecker(record, j)) {
				 
				 dummy = lengthCalculator(newRecord, j, currentVertex);
				 if(dummy < length) {
					 length = dummy;
				 }
				 
			 }
		 }
		 return 1+length;
	 }
}

