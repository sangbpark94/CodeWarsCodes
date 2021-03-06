//Solution for http://www.codewars.com/kata/5277dc3ff4bfbd9a36000c1c/train/java

import java.math.BigInteger;

public class Ascii85 {
	
	public static String toAscii85(byte[] data){
		
		String returnStr = "";
		int dataParts = data.length / 4;
		for(int i = dataParts - 1; i >= 0 ; i--){
			
			String word = "";
			
			//turn each data into bits
			for(int j = 0 + (4*i); j < 4 + (4*i); j++){
				String s = "";
				long currData = data[j];
				if(currData < 0){
					currData *= -1;
					currData = 256 - currData;
			    }
				for(int k = 0; k < 8; k++){
					if(currData == 1){
						s = "1" + s;
						break;
					}
					int bit = (int) currData % 2;
					currData /= 2;
					s = bit + s;
				}
				while(s.length() < 8){
					s = "0" + s;
				}
				word += s;
			}
			
			//convert 32 bit string into long using parseLong
			Long num = Long.parseLong(word,2);
			if(num < 0){
				num *= -1;
				num = (long)Math.pow(2,32) - num;
			}
			if(num == 0)
				returnStr = "z" + returnStr;
			else{
				for(int k = 0; k < 5; k++){
					int aNum = (int) ((num % 85) + 33);
					num /= 85;
					returnStr = ((char)aNum) + returnStr;
				}
			}
		}
		
		//last part of string
		int lastLength = data.length % 4;
		if(lastLength > 0){
			String word = "";
			for(int i = 4 * dataParts; i < (4 * dataParts) + lastLength; i++){
				String s = "";
				int currData = data[i];
				if(currData < 0){
					currData *= -1;
					currData = 256 - currData;
			    }
				for(int k = 0; k < 8; k++){
					if(currData == 1){
						s = "1" + s;
						break;
					}
					int bit = currData % 2;
					currData /= 2;
					s = bit + s;
				}
				while(s.length() < 8){
					s = "0" + s;
				}
				word += s;
			}
				for(int j = lastLength; j < 4; j++)
					word += "00000000";
			
			Long num = Long.parseLong(word,2);
			String tempStr = "";
			for(int k = 0; k < 5; k++){
				int aNum = (int) ((num % 85) + 33);
				num /= 85;
				tempStr = ((char)aNum) + tempStr;
			}
			tempStr = tempStr.substring(0, lastLength + 1);
			returnStr += tempStr;
		}
		
		return "<~" + returnStr + "~>";
		
	}
	
	public static byte[] fromAscii85(String data){
    data = data.substring(2, data.length()-2);
		data = data.replace(" ", "");
		data = data.replace("z", "!!!!!");
		int lastLength = data.length() % 5;
    boolean allExc = true;
    if(data.equals(""))
      return new byte[0];
    for(int d = 0; d < data.length(); d++){
      if(data.charAt(d) != '!'){
        allExc = false;
        break;
      }
    }
    if(allExc){
      byte[] returnByte = new byte[data.length() - 1];
      for(int e = 0; e < returnByte.length; e++){
        returnByte[e] = (byte)0;
      }
      return returnByte;
    }
    if(lastLength > 0){
		for(int c = 0; c < 5-lastLength; c++)
			data += "u";
	    while(data.length() < 5){
	      data += "u";
	    }
    }
		byte[] returnByte = new byte[(data.length() / 5) * 4];
		int currByte = 0;
		int dataParts = data.length() / 5;
		for(int i = 0; i < dataParts; i++){
			//convert to ascii values
			int[] asciiArr = new int[5];
			for(int j = 0 + (5 * i), k = 0; j < 5 + (5 * i) && k < 5; j++, k++){
				//subtract 33 to each of them
				asciiArr[k] = ((int) data.charAt(j)) - 33;
			}
			
			//multiply by a * 8^4 + ... + e
			long num = 0;
			for(int m = 4, n = 0; m >= 0 && n < 5; m--, n++){
				num += asciiArr[n] * Math.pow(85, m);
			}
			
			//convert to 32 bit binary number
			String word = "";
			while(word.length() < 32){
				if(num == 1){
					word = "1" + word;
					while(word.length() < 32)
						word = "0" + word;
				}
				else{
					word = Integer.toString((int)(num % 2)) + word;
					num /= 2;
				}
			}
      
			byte[] tempBytes = (new BigInteger(word, 2)).toByteArray();
      if(tempBytes.length < 4){
        for(int q = 0; q < 4 - tempBytes.length; q++)
          returnByte[currByte++] = (byte) 0;
        for(int r = 0; r < tempBytes.length; r++)
          returnByte[currByte++] = tempBytes[r];
      }
      else{
  			for(int p = tempBytes.length - 4; p < tempBytes.length; p++){
          returnByte[currByte++] = tempBytes[p];
        }
      }
			
		}
		if(lastLength > 0){
			byte[] tempByte = new byte[returnByte.length - (5 - lastLength)];
			for(int s = 0; s < tempByte.length; s++)
				tempByte[s] = returnByte[s];
			returnByte = tempByte;
		}
		
		return returnByte;
	}
	
}
