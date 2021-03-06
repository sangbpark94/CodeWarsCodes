//Solution for https://www.codewars.com/kata/5726f813c8dcebf5ed000a6b/train/java
import java.util.ArrayList;

public class KPrimes {

	public static long[] countKprimes(int k, long start, long end){
		
		ArrayList<Long> list = new ArrayList<Long>();
		
		for(long i = start; i <= end; i++){
			int count = 0;
			long num = i;
			for(long j = 2; j <= num; j++){
				if(num % j == 0){
					count++;
					num /= j;
					j = 1;
					if(count > k)
						break;
				}
			}
			if(count == k)
				list.add(i);
		}
		
		long[] arrList = new long[list.size()];
		
		for(int m = 0; m < list.size(); m++)
			arrList[m] = list.get(m);
		
		return arrList;
	}
	
	public static int puzzle(int s){
		
		long[] a = countKprimes(1, 1, s);
		long[] b = countKprimes(3, 1, s);
		long[] c = countKprimes(7, 1, s);
		
		long sum = 0;
		int count = 0;
		
		for(int i = 0; i < c.length; i++)
			for(int j = 0; j < b.length; j++){
				for(int k = 0; k < a.length; k++){
					sum = c[i] + b[j] + a[k];
					if(sum == s)
						count++;
					else if(sum > s)
						break;
				}
				if(c[i] + b[j] > s)
					break;
			}
		
		return count;
	}
	
}
