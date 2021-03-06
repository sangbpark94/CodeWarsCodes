//Solution for https://www.codewars.com/kata/585894545a8a07255e0002f1/train/java

import java.util.Arrays;

public class ScreenLock {

	public static final int[][] adj =	{{1,3,4,5,7,-2,1,2,-2,4,8,-2,3,6,-1},	//A 0
							 			 {0,2,3,4,5,6,8,-2,4,7,-1},				//B 1
							 			 {1,3,4,5,7,-2,1,0,-2,4,6,-2,5,8,-1},	//C 2
							 			 {0,1,2,4,6,7,8,-2,4,5,-1},				//D 3
							 			 {0,1,2,3,5,6,7,8,-1},					//E 4
							 			 {0,1,2,4,6,7,8,-2,4,3,-1},				//F 5
							 			 {1,3,4,5,7,-2,3,0,-2,4,2,-2,7,8,-1},	//G 6
							 			 {0,2,3,4,5,6,8,-2,4,1,-1},				//H 7
							 			 {1,3,4,5,7,-2,4,0,-2,5,2,-2,7,6,-1}};	//I 8
	
	public static int calculateCombinations(char startPosition, int patternLength){

		if(patternLength < 1 || patternLength > 9)
			return 0;
		else if(patternLength == 1){
			return 1;
		}
		
		int count = 0;
		int[] usedLetters = new int[9];
		Arrays.fill(usedLetters, 0);
		usedLetters[0] = (int) startPosition - 65;
		boolean from_backtrack = false;
		int pos = 0;
		
		int[] posArray = new int[9];
		Arrays.fill(posArray, -1);
		
		while(true){
			while(pos < patternLength){
				if(!from_backtrack){
					pos++;
					if(pos == patternLength)
						break;
					posArray[pos] = -1;
				}
				from_backtrack = false;
				while(usedLetters[pos] != -1){
					usedLetters[pos] = adj[usedLetters[pos-1]][++posArray[pos]];
					while(usedLetters[pos] == -2){
						usedLetters[pos] = adj[usedLetters[pos-1]][++posArray[pos]];
						if(containsEleAtPos(usedLetters, pos)){
							usedLetters[pos] = adj[usedLetters[pos-1]][++posArray[pos]];
						}
						else{
							posArray[pos]+=2;
							usedLetters[pos] = adj[usedLetters[pos-1]][posArray[pos]];
						}
					}
					if(usedLetters[pos] == -1){
						usedLetters[pos] = 0;
						posArray[pos] = -1;
						pos--;
						if(pos == 0)
							return count;
						from_backtrack = true;
					}
					if(isValid(usedLetters, pos)){
						break;
					}
				}
			}
			count++;
			pos--;
			from_backtrack = true;
		}
			
	}
	
	public static boolean isValid(int[] arr, int pos){
		int i = 0;
		while(i < pos){
			if(arr[i] == arr[pos])
				return false;
			i++;
		}
		return true;
	}
	
	public static boolean containsEleAtPos(int[] arr, int pos){
		int i = 0;
		while(i < pos){
			if(arr[i] == arr[pos])
				return true;
			i++;
		}
		return false;
	}

}