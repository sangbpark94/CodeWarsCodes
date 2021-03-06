//Solution for https://www.codewars.com/kata/527fde8d24b9309d9b000c4e/train/java

import java.util.ArrayList;

public class BreakPieces {
	
	private static int[][] travArrA;
	private static int[][] travArrB;
	
	public static String[] process(String shape){
		
		System.out.println(shape);
		String[] parts = shape.split("\n");
		int width = 0;
		for(int i = 0; i < parts.length; i++){
			if(parts[i].length() > width)
				width = parts[i].length();
		}
		int height = parts.length;
		int[][] arr = new int[height][width];
		for(int r = 0; r < height; r++){
			int indexA = -1;
			int indexB = -1;
			for(int c = 0; c < parts[r].length(); c++){
				if(parts[r].charAt(c) == '|'){
					indexA = c;
					break;
				}
			}
			for(int c = parts[r].length() - 1; c >= 0; c--){
				if(parts[r].charAt(c) == '|' || parts[r].charAt(c) == '+'){
					indexB = c;
					break;
				}
			}
			if(indexA != -1 && indexB != -1)
			for(int c = indexA + 1; c < indexB; c++){
				if(parts[r].charAt(c) == ' ')
					arr[r][c] = 1;
			}
		}
		print(arr);
		ArrayList<int[][]> arrList = separate(arr);
		String[] returnArr = convert(arrList, parts); 
		
		return returnArr;
	}
	
	public static ArrayList<int[][]> separate(int[][] arr){
		ArrayList<int[][]>  list = new ArrayList<int[][]>();
		int[][] arrCopy = arr;
		while(true){
			int firstR = -1;
			int firstC = -1;
			boolean flag = false;
			for(int c = 0; c < arr[0].length; c++){
				for(int r = 0; r < arr.length; r++){
					//System.out.println(r + " " + c);
					if(arr[r][c] == 1){
						firstR = r;
						firstC = c;
						flag = true;
						break;
					}
				}
				if(flag)
					break;
			}
			if(firstR == -1 && firstC == -1)
				break;
			//System.out.println(firstR + " " + firstC);
			int[][] tempArr = traverse(arr,firstR,firstC);
			tempArr = fill(tempArr,arrCopy);
			arrCopy = remove(arrCopy,tempArr);
			tempArr = expand(tempArr);
			tempArr = squarify(tempArr);
			list.add(tempArr);
		}
		return list;
	}
	
	public static int[][] traverse(int[][] arr, int r, int c){
		
		//right, down, left up traversal
		travArrA = arr;
		travArrB = new int[arr.length][arr[0].length];
		recTraverse(r,c);
		//print(travArrB);
		return travArrB;
	}
	
	public static void recTraverse(int r, int c){
		if(travArrA[r][c] != 0){
			travArrB[r][c] = 1;
			if(r != 0 && travArrB[r-1][c] != 1)
				recTraverse(r-1,c);
			if(c != travArrB[0].length - 1 && travArrB[r][c+1] != 1)
				recTraverse(r,c+1);
			if(r != travArrB.length - 1 && travArrB[r+1][c] != 1)
				recTraverse(r+1,c);
			if(c != 0 && travArrB[r][c-1] != 1)
				recTraverse(r,c-1);
		}
	}
	
	public static int[][] merge(int[][] a, int[][] b, int[][] c, int[][] d){
		int[][] arr = new int[a.length][a[0].length];
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < arr[i].length; j++)
				if(a[i][j] == 1 || b[i][j] == 1 || c[i][j] == 1 || d[i][j] == 1)
					arr[i][j] = 1;
		print(arr);
		return arr;
	}
	
	public static int[][] fill(int [][] arr, int[][] origArr){
		
		//print(arr);
		//top to bottom
		for(int c = 0; c < arr[0].length; c++)
			for(int r = 0; r < arr.length; r++){
				if(arr[r][c] != 1)
					arr[r][c] = 2;
				else
					break;
			}
			
		//bottom to top
		for(int c = 0; c < arr[0].length; c++)
			for(int r = arr.length - 1; r >= 0; r--){
				if(arr[r][c] != 1)
					arr[r][c] = 2;
				else
					break;
			}
		
		//left to right
		for(int r = 0; r < arr.length; r++)
			for(int c = 0; c < arr[r].length; c++){
				if(arr[r][c] != 1)
					arr[r][c] = 2;
				else
					break;
			}
				
		//right to left
		for(int r = 0; r < arr.length; r++)
			for(int c = arr[r].length - 1; c >= 0; c--){
				if(arr[r][c] != 1)
					arr[r][c] = 2;
				else
					break;
			}
		
		//print(arr);
		
		//flip
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < arr[i].length; j++){
				if(arr[i][j] == 0 && origArr[i][j] == 1)
					arr[i][j] = 1;
				else if(arr[i][j] == 2)
					arr[i][j] = 0;
			}
		
		//print(arr);
		
		return arr;
	}
	
	public static int[][] remove(int[][] fromArr, int[][] toRemoveArr){
		for(int i = 0; i < fromArr.length; i++)
			for(int j = 0; j < fromArr[i].length; j++)
				if(toRemoveArr[i][j] == 1)
					fromArr[i][j] = 0;
		//print(fromArr);
		return fromArr;
	}
	
	public static int[][] expand(int[][] arr){
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < arr[i].length; j++)
				if(arr[i][j] == 1){
					if(arr[i - 1][j - 1] == 0)
						arr[i - 1][j - 1] = 2;
					if(arr[i][j - 1] == 0)
						arr[i][j - 1] = 2;
					if(arr[i + 1][j] == 0)
						arr[i + 1][j - 1] = 2;
					if(arr[i - 1][j] == 0)
						arr[i - 1][j] = 2;
					if(arr[i + 1][j] == 0)
						arr[i + 1][j] = 2;
					if(arr[i - 1][j + 1] == 0)
						arr[i - 1][j + 1] = 2;
					if(arr[i][j + 1] == 0)
						arr[i][j + 1] = 2;
					if(arr[i + 1][j + 1] == 0)
						arr[i + 1][j + 1] = 2;
				}
		
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < arr[i].length; j++)
				if(arr[i][j] == 2)
					arr[i][j] = 1;
		
		//print(arr);
		return arr;
	}
	
	public static int[][] squarify(int[][] arr){
		int leftX = arr[0].length, rightX = -1, bottomY = -1, topY = arr.length;
		
		for(int r = 0; r < arr.length; r++)
			for(int c = 0; c < arr[r].length; c++)
				if(arr[r][c] == 1){
					if(c < leftX)
						leftX = c;
					break;
				}
		
		for(int r = 0; r < arr.length; r++)
			for(int c = arr[r].length - 1; c >= 0; c--)
				if(arr[r][c] == 1){
					if(c > rightX)
						rightX = c;
					break;
				}
		
		for(int c = 0; c < arr[0].length; c++){
			for(int r = 0; r < arr.length; r++)
				if(arr[r][c] == 1){
					if(r < topY)
						topY = r;
					break;
				}
		}
		
		for(int c = 0; c < arr[0].length; c++){
			for(int r = arr.length - 1; r >= 0; r--)
				if(arr[r][c] == 1){
					if(r > bottomY)
						bottomY = r;
					break;
				}
		}
		
		//System.out.println(leftX + " " + rightX + " " + topY + " " + bottomY);
		for(int i = topY; i <= bottomY; i++)
			for(int j = leftX; j <= rightX; j++)
				if(arr[i][j] == 0)
					arr[i][j] = 2;
		
		return arr;
	}
	
	public static String[] convert(ArrayList<int[][]> arrList, String[] strArray){
		String[] returnArr = new String[arrList.size()];
		//System.out.println(arrList.size());
		for(int index = 0; index < arrList.size(); index++){
			String s = "";
			boolean reachedFirstLine = false;
			for(int i = 0; i < (arrList.get(index)).length; i++){
				boolean allZerosFlag = true;
				for(int j = 0; j < (arrList.get(index))[i].length; j++){
					if((arrList.get(index))[i][j] == 2){
						s += " ";
						allZerosFlag = false;
						reachedFirstLine = true;
					}
					else if((arrList.get(index))[i][j] == 1){
						s += Character.toString(strArray[i].charAt(j));
						allZerosFlag = false;
						reachedFirstLine = true;
					}
				}
				if(allZerosFlag && reachedFirstLine)
					break;
				else if(reachedFirstLine)
					s += "\n";
			}
			returnArr[index] = s;
		}
		
		return removeDuplicates(returnArr);
	}
	
	public static String[] removeDuplicates(String[] arr){
		String[] returnArr = new String[arr.length];
		for(int index = 0; index < arr.length; index++){
			String[] parts = arr[index].split("\n");
			for(int i = 0; i < parts.length; i++)
				for(int j = 0; j < parts[i].length(); j++)
					if(parts[i].charAt(j) == '+'){
						if(j != 0 && j != parts[i].length() - 1){
							if(parts[i].charAt(j - 1) == '-' && parts[i].charAt(j + 1) == '-')
								parts[i] = parts[i].substring(0, j) + "-" + parts[i].substring(j + 1);
						}
						else if(i != 0 && i != parts.length - 1){
							if(parts[i - 1].charAt(j) == '|' && parts[i + 1].charAt(j) == '|')
								parts[i] = parts[i].substring(0, j) + "|" + parts[i].substring(j + 1);
						}
					}
			returnArr[index] = String.join("\n", parts);
		}
		if(returnArr.length == 2){
			String temp = returnArr[0];
			returnArr[0] = returnArr[1];
			returnArr[1] = temp;
		}
		
		System.out.println(returnArr[0]);
		return trim(returnArr);
		
	}
	
	public static String[] trim(String[] arr){
		String[] returnArr = new String[arr.length];
		for(int index = 0; index < arr.length; index++){
			String[] parts = arr[index].split("\n");
			for(int i = 0; i < parts.length; i++)
				for(int j = parts[i].length() - 1; j >= 0; j--){
					if(parts[i].charAt(j) == ' ')
						parts[i] = parts[i].substring(0, j);
					else break;
				}
			returnArr[index] = String.join("\n", parts);
		}
		return returnArr;
	}
	
	public static void print(int[][] arr){
		for(int i = 0; i < arr.length; i++){
			String s = "";
			for(int j = 0; j < arr[i].length; j++)
				s += arr[i][j];
			System.out.println(s);
		}
		System.out.println();
	}
	
}
