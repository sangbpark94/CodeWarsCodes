//Solution https://www.codewars.com/kata/getting-along-with-integer-partitions/train/java

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class test {
	
	public static String part(long n) {
		String[][] array = new String[(int)n + 1][2000000];
		int[] arraySize = new int[(int) n + 1];
		arraySize[0] = 1;
		array[0][0] = "";
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= i; j++){
				for(int k = 0; k < arraySize[i-j] ; k++){
					String s = array[i-j][k];
					if(s == null)
						s = "";
					if(s.equals(""))
						s += Integer.toString(j);
					else
						s += "," + Integer.toString(j);
					String[] parts = s.split(",");
					Arrays.sort(parts);
					String tempB = parts[0];
					for(int m = 1; m < parts.length; m++)
						tempB += "," + parts[m];
					array[i][arraySize[i]++] = tempB;
				}
			}
			Set<String> shs = new HashSet<>();
			for(int m = 0; m < arraySize[(int) i]; m++)
			    shs.add(array[(int) i][m]);
			array[i] = shs.toArray(array[i]);
			arraySize[i] = shs.size();
		}
		/*
		for(int u = 0; u < (int) n; u++){
			System.out.println("u is: " + u);
			for(int v = 0; v < arraySize[u]; v++)
				System.out.println(array[u][v]);
		}
		*/
		int[] prodArray = new int[100000000];
		int prodArraySize = 0;
		for(int p = 0; p < arraySize[(int) n]; p++){
			String[] strParts = array[(int) n][p].split(",");
			int tempProd = 1;
			for(int q = 0; q < strParts.length; q++)
				tempProd *= Integer.parseInt(strParts[q]);
			prodArray[prodArraySize++] = tempProd; 
		}
		Set<Integer> ihs = new HashSet<>();
		for(int r = 0; r < prodArraySize; r++)
		    ihs.add(prodArray[r]);
		int[] temp = new int[ihs.size()];
		int index = 0;
		for(Integer s : ihs)
			temp[index++] = s;
		prodArray = temp;
		Arrays.sort(prodArray);
		int range = prodArray[prodArray.length - 1] - prodArray[0];
		int sum = 0;
		for(int i = 0; i < prodArray.length; i++)
			sum += prodArray[i];
	    double average = (double) sum / prodArray.length;
		float median;
		if(prodArray.length % 2 == 1)
			median = prodArray[prodArray.length / 2];
		else{
			int a = prodArray[(prodArray.length / 2) - 1];
			int b = prodArray[prodArray.length / 2];
			median = ((float) ((float)a + (float)b) )/ 2;
		}
		return "Range: " + range + " Average: " + String.format("%.2f", average) + " Median: " + String.format("%.2f", median);
	}
}

