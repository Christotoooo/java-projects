package A5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class mancala {

	public static void main(String[] args) throws IOException {
		
		 FileReader fr = new FileReader("testMancala.txt"); 
		 BufferedReader br = new BufferedReader(fr); 
		 FileWriter fw = new FileWriter("testMancala_solution.txt"); 
		 BufferedWriter bw = new BufferedWriter(fw);
		  

		 int line = Integer.parseInt(br.readLine());

		 for(int i = 0; i <= line-1; i++) {
			 String temLine = br.readLine();
			 int[] board = new int[12];
			 
			 for(int j = 0; j < 23; j+=2) {
				 int temp = temLine.charAt(j) - 48;
				 board[j/2] = temp;
			 }
			 
			 int temp =findMancala(board);
			 String result = String.valueOf(temp);
			 
			 bw.write(result);
			 bw.newLine();
		 }
		 bw.close();
		 fw.close();
		 br.close();
		 fr.close();
	}

	public static int findMancala(int[] keyBoard) {
		int[] temoBoard = null;
		int currentLowest = 12;
		int temp = 0;
		int ending = 0;
		boolean ifEnd = false;
		
		for (int i = 0; i < 11; i++) {
			if ((keyBoard[i] == 1) 
					&& (keyBoard[i + 1] == 1)) {
				
				if ((0 != i) 
						&& ( 0 == keyBoard[i - 1])) {
					
					temoBoard = keyBoard.clone();
					
					temoBoard[i + 1] = 0;
					temoBoard[i] = 0;
					temoBoard[i - 1] = 1;		
					
					temp = findMancala(temoBoard);
					if (temp < currentLowest) {
						currentLowest = temp;
					}
				}
				
				if ((10 != i) 
						&& (0 == keyBoard[i + 2])) {
					
					temoBoard = keyBoard.clone();
					
					temoBoard[i + 2] = 1;
					temoBoard[i + 1] = 0;
					temoBoard[i] = 0;

					temp = findMancala(temoBoard);
					
					if (temp < currentLowest) {
						currentLowest = temp;
					}
				}
			}
		}
		
		for (int i = 0; i < 12; i++) {
			ending = ending + keyBoard[i];
		}
		
		for (int k = 0; k < 11; k++) {
			if ((keyBoard[k] == 1) 
					&& (keyBoard[k + 1] == 1)) {
				ifEnd = true;
			}
		}
		
		if (ifEnd) {
			return currentLowest;
		} else {
			return ending;
		}
		
	}

}
