
public class test {
	
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
		int num = Integer.parseInt(subStr) + 1;
		//System.out.println(num);
		String numStr = Integer.toString(num);
		if(numStr.length() > subStr.length())
			numStr = numStr.substring(1);
		else{
			for(int m = numStr.length(); m < subStr.length(); m++)
				numStr = "0" + numStr;
		}
		for(int j = numStr.length() - 1, k = a.length() - 1; j >= 0; j--, k--){
			//System.out.println("numStr.charAt(" + j + ") is: " + numStr.charAt(j));
			//System.out.println("a.charAt(" + k + ") is: " + a.charAt(k));
			if(a.charAt(k) != 'x' && numStr.charAt(j) != a.charAt(k))
				return -1;
		}
		String returnStr = a.substring(0,a.length()-numStr.length());
		returnStr += numStr;
		//System.out.println(returnStr);
		return calcPosition(Integer.parseInt(returnStr));
	}
	
	public static long calcPosition(long index){
		//System.out.println(index);
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
}
