public class Lcs {
	
	static String lcs(String a, String b){
		
		String output = "";
		
		if(a.equals("") || b.equals(""))
			return output;

		
		int pos = 0;
		
		while(pos < b.length() || pos < a.length()){
			
			//System.out.println("Iterate While Loop");
			
			boolean reInitPos = false;
			boolean skip = false;
			
			if(pos < b.length() - 1){
				for(int i = 0; i <= pos - 1 && i < b.length() - 1; i++){
					//System.out.println("Entered forloop 1");
					if(a.charAt(i) == b.charAt(pos)){
						output += a.charAt(i);
						a = a.substring(i + 1, a.length());
						if(pos < b.length() - 1)
							b = b.substring(pos, b.length());
						else
							b = "";
						reInitPos = true;
						skip = true;
						break;
					}
				}
			}
			
			if(!skip && pos < a.length() - 1){
				for(int j = 0; j <= pos && j < b.length(); j++){
					//System.out.println("Entered forloop 2");
					if(b.charAt(j) == a.charAt(pos)){
						output += a.charAt(pos);
						if(j < b.length() - 1)
							b = b.substring(j + 1, b.length());
						else
							b = "";
						if(pos < a.length() - 1)
							a = a.substring(pos + 1, a.length());
						else
							a = "";
						//System.out.println("a is: " + a);
						//System.out.println("b is: " + b);
						reInitPos = true;
						break;
					}
				}
			}
			
			if(reInitPos)
				pos = 0;
			else
				pos++;
		}

		return output;
	}
	
}

//https://www.codewars.com/kata/593ff8b39e1cc4bae9000070/train/java

