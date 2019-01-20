package A5;
// Java program to count islands in boolean 2D matrix 
import java.util.*;  
import java.io.*; 
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

class islands
{ 
	//No of rows and columns 
	static final int ROW = 100, COL = 100; 

	// A function to check if a given cell (row, col) can 
	// be included in DFS 
	boolean isSafe(int M[][], int row, int col, 
				boolean visited[][]) 
	{ 
		// row number is in range, column number is in range 
		// and value is 1 and not yet visited 
		//System.out.println(Arrays.deepToString(M));
		//System.out.println(Arrays.deepToString(visited));
		//
		return (row >= 0) && (row < M.length) && 
			(col >= 0) && (col < M[0].length) && 
			(M[row][col]==1 && !visited[row][col]); 
	} 

	// A utility function to do DFS for a 2D boolean matrix. 
	// It only considers the 8 neighbors as adjacent vertices 
	void DFS(int M[][], int row, int col, boolean visited[][]) 
	{ 
		// These arrays are used to get row and column numbers 
		// of 8 neighbors of a given cell 
		//int rowNbr[] = new int[] {-1, -1, -1, 0, 0, 1, 1, 1}; 
		//int colNbr[] = new int[] {-1, 0, 1, -1, 1, -1, 0, 1}; 
		int rowNbr[] = new int[] {-1, 0, 0,1}; 
		int colNbr[] = new int[] {0,-1, 1, 0}; 

		// Mark this cell as visited 
		visited[row][col] = true; 

		// Recur for all connected neighbours 
		for (int k = 0; k < 4; ++k) 
			if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited) ) 
				DFS(M, row + rowNbr[k], col + colNbr[k], visited); 
	} 

	// The main function that returns count of islands in a given 
	// boolean 2D matrix 
	int countIslands(int M[][]) 
	{ 
		// Make a bool array to mark visited cells. 
		// Initially all cells are unvisited 
		
		
		boolean visited[][] = new boolean[M.length][M[0].length]; 


		// Initialize count as 0 and traverse through the all cells 
		// of given matrix 
		int count = 0; 
		for (int i = 0; i < M.length; i++) 
			for (int j = 0; j < M[0].length; j++) 
				if (M[i][j]==1 && !visited[i][j]) // If a cell with 
				{								 // value 1 is not 
					// visited yet, then new island found, Visit all 
					// cells in this island and increment island count 
					DFS(M, i, j, visited); 
					++count; 
				} 

		return count; 
	} 

	// Driver method 
	public static void main (String[] args) throws java.lang.Exception 
	{ 
		 
		//////////////////////////////////////////////
		/*int M[][]= new int[][] {{1, 1, 0, 0, 0}, 
								{0, 1, 0, 0, 1}, 
								{1, 0, 0, 1, 1}, 
								{0, 0, 0, 0, 0}, 
								{1, 0, 1, 0, 1} 
								}; 
		islands I = new islands(); 
		System.out.println("Number of islands is: "+ I.countIslands(M)); */
		///////////////////////////////////////////////////////////////////
		
		//read from file  replace # with 0 and - with 1.
		
		FileReader fr = new FileReader("testIslands.txt");
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter("testIslands_solution.txt"); 
		BufferedWriter bw = new BufferedWriter(fw);
		int numMatrices = 0;	
		//READ THE TOTAL NUMBER OF MATRICES
		try {
			//fr = new FileReader(args[0]);
			//br = new BufferedReader(fr);
			String sCurrentLine;
			sCurrentLine = br.readLine();
			//System.out.println(sCurrentLine);
			numMatrices = Integer.parseInt(sCurrentLine);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int bigCounter =0; bigCounter<numMatrices;bigCounter++) {
			//read two ints of one matrix
			String temp ="";
			String[] tempArr = {"",""};
			String part1 = tempArr[0];
			String part2 = tempArr[1];
			try {
				String currentLine = br.readLine();
				tempArr = currentLine.split(" ");
				part1 = tempArr[0];
				part2 = tempArr[1];
				int num1 = Integer.parseInt(part1);
				int num2 = Integer.parseInt(part2);
				int[][] M=new int[num1][num2];
				//System.out.println(num2);
				for(int i =0;i<=num1-1;i++) {
					String matrixLine = br.readLine(); 
					//System.out.println(matrixLine);
					for(int j=0;j<=num2-1;j++) {
						char tempIndex = matrixLine.charAt(j); 
						if(tempIndex=='#') {
							M[i][j] =0;
						}else if(tempIndex=='-')
							M[i][j] =1;
					}
				}
				System.out.println(Arrays.deepToString(M));
				System.out.println(M.length);
				System.out.println(M[0].length);
				islands I = new islands(); 
				String result = String.valueOf(I.countIslands(M));
				System.out.println("Number of islands is: "+ result);
				
				
				bw.write(result);
				bw.newLine();
				//bw.newLine();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
		 bw.close();
		 fw.close();
		 br.close();
		 fr.close();
		/////br close
	}
} 