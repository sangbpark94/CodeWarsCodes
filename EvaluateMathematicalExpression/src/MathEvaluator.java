//Solution http://www.codewars.com/kata/52a78825cdfc2cfc87000005/train/java

public class MathEvaluator {

	public static double calculate(String exp){
		
		System.out.println("exp is: " + exp);
		
		//convert 2/((2+3)*4.33)--6 to 2 / ( ( 2 + 3 * 2) * ( 4.1 * 10 ) ) + 6
		exp = exp.replace(" ","");
		exp = exp.replaceAll("[\\D && [^\\.]]"," $0 ");
		exp = exp.replaceAll("  ", " ");
		exp = exp.replace(" - - ", " + ");
		exp = exp.replace(" - + ", " - ");
		exp = exp.replace(" + - ", " - ");
		
		boolean noMoreParentheses = false;
		
		//determine innermost parentheses following OOP and take out the substring 2 + 3 * 2
		while(true){
			
			String temp = findInnerParentheses(exp);
			String[] tempParts = temp.split(",");
			int indexA = Integer.parseInt(tempParts[0]);
			int indexB = Integer.parseInt(tempParts[1]);
			String s = "";
			if(indexA != -1 && indexB != -1)
				s = exp.substring(indexA, indexB + 1);
			else{
				noMoreParentheses = true;
				s = exp;
			}
			
			//evaluate
			String t = evaluate(s);
			
			//replace
			if(!noMoreParentheses)
				exp = exp.substring(0,indexA) + t + exp.substring(indexB + 1);
			else
				return Double.parseDouble(t);
			
		}
		
	}
	
	public static String findInnerParentheses(String s){
		
		int innerIndexA = -1;
		int innerMaxCount = 0;
		int innerCount = 0;
		
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '(')
				innerCount++;
			else if(s.charAt(i) == ')')
				innerCount--;
			if(innerCount > innerMaxCount){
				innerIndexA = i;
				innerMaxCount = innerCount;
			}
		}
		
		int innerIndexB = innerIndexA + 1;
		
		if(innerIndexA != -1){
			while(true){
				if(s.charAt(innerIndexB) == ')')
					break;
				else
					innerIndexB++;
			}
		}
		else
			innerIndexB = -1;
		
		return Integer.toString(innerIndexA) + "," + Integer.toString(innerIndexB);
		
	}

	public static String evaluate(String s){
		s = s.replace("( ","");
		s = s.replace(" )","");
		System.out.println(s);
		String str = s.replace(" ","");
		String[] strParts = s.split(" ");
		if(str.charAt(0) == '-'){
			String temp = "";
			for(int i = 0; i < strParts.length; i++)
				temp += strParts[i];
			System.out.println(temp);
			return temp;
		}

		for(int i = 0; i < strParts.length; i++){
			if(strParts[i].equals("*")){
				double a = Double.parseDouble(strParts[i-1]);
				double b = Double.parseDouble(strParts[i+1]);
				double prod = a * b;
				strParts[i+1] = Double.toString(prod);
				strParts[i] = "";
				strParts[i-1] = "";
				i++;
			}
			else if(strParts[i].equals("/")){
				double a = Double.parseDouble(strParts[i-1]);
				double b = Double.parseDouble(strParts[i+1]);
				double quot = a / b;
				strParts[i+1] = Double.toString(quot);
				strParts[i] = "";
				strParts[i-1] = "";
				i++;
			}	
		}
		
		s = "";
		
		for(int i = 0; i < strParts.length; i++)
			s += strParts[i];
		
		s = s.replaceAll("[\\D && [^\\.]]"," $0 ");
		s = s.replaceAll("  ", " ");
		
		strParts = s.split(" ");
		
		for(int i = 0; i < strParts.length; i++){
			if(strParts[i].equals("+")){
				double a = Double.parseDouble(strParts[i-1]);
				double b = Double.parseDouble(strParts[i+1]);
				double sum = a + b;
				strParts[i+1] = Double.toString(sum);
				strParts[i] = "";
				strParts[i-1] = "";
				i++;
			}
			else if(strParts[i].equals("-")){
				double a = Double.parseDouble(strParts[i-1]);
				double b = Double.parseDouble(strParts[i+1]);
				double diff = a - b;
				strParts[i+1] = Double.toString(diff);
				strParts[i] = "";
				strParts[i-1] = "";
				i++;
			}	
		}
		s = "";
		for(int i = 0; i < strParts.length; i++)
			s += strParts[i];
		return s;
	}
	
}