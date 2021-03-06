//Solution for https://www.codewars.com/kata/582c1092306063791c000c00/train/java
import java.util.ArrayList;

public class InfiniteDigitalString {
	
	public static ArrayList<Long> result = new ArrayList<Long>();
	
	public static long findPosition(final String s){
		//Check for all zeros
		boolean allZeros = true;
		for(int n = 0; n < s.length(); n++)
			if(s.charAt(n) != '0')
				allZeros = false;
		if(allZeros){
			String numStr = "1" + s;
			return calcPosition(Integer.parseInt(numStr)) + 1;
		}
		result.removeAll(result);
		for(int length = 1; length <= s.length(); length++){
			for(int lengthIndex = 0; lengthIndex < length; lengthIndex++){
				String a = "";
				for(int i = 0; i < length; i++){
					if(lengthIndex + i < s.length())
						a += Character.toString(s.charAt(lengthIndex + i));
					else
						a += "x";
				}
				//checks if a is a valid number
				if(isValidA(a)){
					boolean goToNextLengthIndex = false;
					
					//Check previous if previous exists
					if(lengthIndex != 0){
						//if a does have an x
						if(a.charAt(a.length()-1) == 'x'){
							String prev = "";
							for(int j = 0; j < length - lengthIndex; j++){
								prev += "x";
							}
							for(int k = 0; k < lengthIndex; k++){
								prev += Character.toString(s.charAt(k));
							}
							long temp = fillInAndConsecutive(a, prev);
							if(temp != -1)
								result.add(temp - lengthIndex);
							goToNextLengthIndex = true;
								
						}
						//if a doesn't have an x
						else if (a.charAt(a.length()-1) != 'x'){
							String prev = "";
							//get prev length
							long prevLong = Long.parseLong(a) - 1;
							int prevLength = (Long.toString(prevLong)).length();
							//pad with x's
							for(int j = 0; j < prevLength - lengthIndex; j++){
								prev += "x";
							}
							//add the prev string
							for(int k = 0; k < lengthIndex; k++){
								prev += Character.toString(s.charAt(k));
							}
							if(!isConsecutive(prev,a)){
								goToNextLengthIndex = true;
							}
						}
					}
					
					//this works
					while(!goToNextLengthIndex){
						String curr = a;
						long nextLong = Long.parseLong(a) + 1;
						String b = Long.toString(nextLong);
						int strPos = lengthIndex + curr.length();
						while(strPos + b.length() <= s.length()){
							String next = s.substring(strPos, strPos + b.length());
							if(!isConsecutive(curr, next)){
								goToNextLengthIndex = true;
								break;
							}
							curr = next;
							nextLong = Long.parseLong(curr) + 1;
							b = Long.toString(nextLong);
							strPos += b.length();
						}
						if(!goToNextLengthIndex){
							if(strPos == s.length()){
								result.add(calcPosition(Long.parseLong(a)) - lengthIndex);
							}
							else{
								String last = "";
								for(int m = strPos; m < s.length(); m++)
									last += Character.toString(s.charAt(m));
								for(int n = last.length(); n < b.length(); n++)
									last += "x";
								if(isConsecutive(curr,last))
									result.add(calcPosition(Long.parseLong(a)) - lengthIndex);
							}
							goToNextLengthIndex = true;
						}
					}
				}
			}
			if(!result.isEmpty()){
				long min = result.get(0);
				for(int p = 1; p < result.size(); p++)
					if(result.get(p) < min)
						min = result.get(p);
				return min;
			}
		}
		return 0;
	}
	
	//this works
	public static long calcPosition(long index){
		long sum = 0;
		long checkAmt = 10;
		while(checkAmt < index){
			sum += 9 * (checkAmt / 10) * (Math.log10(checkAmt));
			checkAmt *= 10;
		}
		checkAmt /= 10;
		sum += (index - checkAmt) * Math.log10(checkAmt * 10);
		return sum;
	}
	
	//checks if a is a valid number (can't be 01 or 001 or 093, etc.)
	public static boolean isValidA(String a){
		if(a.charAt(0) == '0')
			return false;
		return true;
	}
	
	//checks if two numbers are consecutive
	public static boolean isConsecutive(String a, String b){
		boolean bHasX = false;
		for(int i = 0; i < b.length(); i++)
			if(b.charAt(i) == 'x')
				bHasX = true;
		if(bHasX){
			long numA = Long.parseLong(a);
			long tempNum = numA + 1;
			String checkNum = Long.toString(tempNum);
			if(b.length() != checkNum.length())
				return false;
			for(int i = 0; i < checkNum.length(); i++)
				if(checkNum.charAt(i) != b.charAt(i) && b.charAt(i) != 'x')
					return false;
			return true;
		}
		else{
			long numB = Long.parseLong(b);
			long tempNum = numB - 1;
			String checkNum = Long.toString(tempNum);
			if(a.length() != checkNum.length())
				return false;
			for(int i = 0; i < checkNum.length(); i++)
				if(checkNum.charAt(i) != a.charAt(i) && a.charAt(i) != 'x')
					return false;
			return true;
		}
	}
	
	//checks prev and a (with x's) to see if they are consecutive
	public static long fillInAndConsecutive(String a, String prev){
		//if matches return calcPosition(Integer.parseInt(a))
		int i = 0;
		while(true){
			if(prev.charAt(i) != 'x')
				break;
			else 
				i++;
		}
		String subStr = prev.substring(i);
		long num = Long.parseLong(subStr) + 1;
		String numStr = Long.toString(num);
		if(numStr.length() > subStr.length())
			numStr = numStr.substring(1);
		else{
			for(int m = numStr.length(); m < subStr.length(); m++)
				numStr = "0" + numStr;
		}
		for(int j = numStr.length() - 1, k = a.length() - 1; j >= 0; j--, k--){
			if(a.charAt(k) != 'x' && numStr.charAt(j) != a.charAt(k))
				return -1;
		}
		String returnStr = a.substring(0,a.length()-numStr.length());
		returnStr += numStr;
		return calcPosition(Long.parseLong(returnStr));
	}

}
