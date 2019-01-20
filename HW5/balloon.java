package A5;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

class balloon{
	//initialize the matrix map
	int[][] initMatrix(int row,int col){
		int[][] matrix = new int[row][col];
		for (int i=0;i<row;i++) {         // DONT FORGET +1 to offset the effects of bounds
			for (int j=0;j<col;j++) {
				matrix[i][j] = 0;
			}
		}
		return matrix;
	}
	
	//return true if the arrow is still inside the matrix map
	boolean isInside(int[][] M, int currentRow, int currentCol) {
		if (M.length <= currentRow) {
			return false;
		}
		if (M[0].length <= currentCol) {
			return false;
		}
		return true;
	}
	
	//return true if the specific location contains a balloon
	boolean isThere(int[][] M, int currentRow,int currentCol) {
		if(M[currentRow][currentCol]==1) {
			return true;
		}else {
			return false;
		}
			
	}
	
	//destroy one balloon
	int[][] destroy(int[][] M,int row,int col){
		M[row][col] = 0;
		return M;
	}
	
	
	//return true if specific row is empty
	boolean isEmptyRow(int[][] M,int row) {
		if(row<0) {
			return false;
		}
		//System.out.println(row);
		for(int j=0;j<M[row].length;j++) {
			if(M[row][j]==1) {
				return false;
			}
		}
		return true;
	}
	
	//return true if all balloons are shot
	boolean isEmpty(int[][] M) {
		for(int i=0;i<M.length;i++) {
			for(int j=0;j<M[0].length;j++) {
				if(M[i][j]==1) {
					return false;
				}
			}
		}
		return true;
	}
	
	//get largest element of an int array
	int getLargest(int[] arr) {
		int temp =-1;
		for (int i =0;i<arr.length;i++) {
			if(arr[i]>=temp) {
				temp=arr[i];
			}
		}
		return temp;
	}
	
	
	//shoot an arrow at height h
	int[][] shoot(int[][] M, int height){
		if(isEmptyRow(M,height)) {
			return M;
		}
		int currentHeight = height;
		int currentCol = 0;
		for(int j=currentCol;j<M[0].length;j++) {
			if(isEmptyRow(M,currentHeight)) {
				return M;
			}
			if(M[currentHeight][j]==1) {
				M = destroy(M,currentHeight,j);//shoot one balloon
				currentHeight--;
				currentCol = j+1;
				if(!isInside(M,currentHeight,currentCol)) {
					return M;
				}
				if(isEmptyRow(M,currentHeight)) {
					return M;
				}
			}
		}
		return M;
	}
	
	public static void main(String[] args) throws IOException {
		balloon temp = new balloon();
		//int[][] matrix = {{0,0,0,1},{0,0,1,0},{0,1,0,0},{1,0,0,0}};
		//int[][] result = temp.shoot(matrix, 0);
		//System.out.println(Arrays.deepToString(result));
		FileReader fr = new FileReader("testBalloons.txt");
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter("testBalloons_solution.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		String currentLine ="";
		int numMaps = 0;
		//read the first line i.e. overall num of maps
		try {
			currentLine = br.readLine();
			numMaps = Integer.parseInt(currentLine);
		}catch(IOException e) {
			e.printStackTrace();
		}
		//read the second line 
		//i.e. number of balloons of each map 
		String[] strNumEach = {""};
		//initialization for array
		int[] numEach = new int[numMaps];
		for(int i=0;i<numMaps;i++) {
			numEach[i] = 0;
		}
		//2nd line numbers ===> int[] numEach
		try {
			currentLine = br.readLine();
			strNumEach = currentLine.split(" ");
			for (int i=0;i<numMaps;i++) {
				numEach[i] = Integer.parseInt(strNumEach[i]);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		//loop one map
		//for loop ici
		for(int i = 0;i<numMaps;i++) {
			//init a matrix
			int counter=0;
			//e.g.
			int[][] M = {{0,0,0,1},{0,0,1,0},{0,1,0,0},{1,0,0,0}};
			String[] partsOneLine = new String[numEach[i]];
			int[] numsOneLine = new int[numEach[i]];	
			try {
				currentLine = br.readLine();
				partsOneLine = currentLine.split(" ");
				for(int j=0;j<numEach[i];j++) {
					numsOneLine[j]=Integer.parseInt(partsOneLine[j]);
				}
				int rowMax = temp.getLargest(numsOneLine);
				//System.out.println(rowMax);
				//System.out.println(numEach[i]);
				M = temp.initMatrix(rowMax+1, numEach[i]);
				//System.out.println(Arrays.deepToString(M));
				//put in balloons
				for(int j=0;j<numEach[i];j++) {
					//System.out.println("numsOneLine[j]"+numsOneLine[j]);
					M[numsOneLine[j]][j] = 1;
				}
				//System.out.println(Arrays.deepToString(M));
			}catch(IOException e) {
				e.printStackTrace();
			}
			//now we deja have M all set
			//now begin shooting
			for(int h =M.length-1;h>=0;h--) {
				int currentHeight= h;
				if(temp.isEmptyRow(M, currentHeight)) {
					continue;
				}
				M = temp.shoot(M, currentHeight);
				counter++;
				if(!temp.isEmptyRow(M, currentHeight)) {
					h++;
				}
			}
			bw.write(String.valueOf(counter));
			bw.newLine();
		}
		 bw.close();
		 fw.close();
		 br.close();
		 fr.close();
	}
}


