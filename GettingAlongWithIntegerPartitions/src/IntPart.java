//Solution https://www.codewars.com/kata/getting-along-with-integer-partitions/train/java

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class IntPart {
	
	public static String part(long n) {
		ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>((int)n+1);
		for(int q = 0; q <= (int) n; q++)
			array.add(new ArrayList<String>(0));
		ArrayList<String> enumZero = new ArrayList<String>(1);
		enumZero.add("");
		array.add(0,enumZero);
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= i; j++){
				for(int k = 0; k < (array.get(i-j)).size(); k++){
					String s = (array.get(i-j)).get(k);
					if(s.equals(""))
						s += Integer.toString(j);
					else
						s += "," + Integer.toString(j);
					String[] parts = s.split(",");
					Arrays.sort(parts);
					String tempB = parts[0];
					for(int m = 1; m < parts.length; m++)
						tempB += "," + parts[m];
					(array.get(i)).add(tempB);
				}
			}
			Set<String> hs = new HashSet<>();
			hs.addAll(array.get(i));
			(array.get(i)).clear();
			(array.get(i)).addAll(hs);
		}
		
		ArrayList<Integer> prod = new ArrayList<Integer>(0);
		for(int i = 0; i < (array.get((int)n)).size(); i++){
			String str = (array.get((int)n)).get(i);
			String[] strParts = str.split(",");
			int tempProd = 1;
			for(int j = 0; j < strParts.length; j++)
				tempProd *= Integer.parseInt(strParts[j]);
			if(!prod.contains(tempProd))
				prod.add(tempProd);
		}
		int[] prodArray = new int[prod.size()];
		for(int i = 0; i < prod.size(); i++)
			prodArray[i] = prod.get(i);
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

