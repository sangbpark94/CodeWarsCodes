
public class Main {

	public static void main(String[] args){
		/*
		String s = "easy";
		byte[] data = s.getBytes();
		System.out.println(Ascii85.toAscii85(data));
		s = "moderate";
		data = s.getBytes();
		System.out.println(Ascii85.toAscii85(data));
		s = "somewhat difficult";
		data = s.getBytes();
		System.out.println(Ascii85.toAscii85(data));
		s = "ea";
		data = s.getBytes();
		System.out.println(Ascii85.toAscii85(data));
		*/
		
		String s = "<~ARTY*~>";
		byte[] dataA = (Ascii85.fromAscii85(s));
		String word = "";
		for(int i = 0; i < dataA.length; i++)
			word += (char) dataA[i];
		System.out.println(word);
		
		s = "<~D/WrrEaa'$~>";
		byte[] dataB = (Ascii85.fromAscii85(s));
		word = "";
		for(int i = 0; i < dataB.length; i++)
			word += (char) dataB[i];
		System.out.println(word);
		
		s = "<~F)Po,GA(E,+Co1uAnbatCif~>";
		byte[] dataC = (Ascii85.fromAscii85(s));
		word = "";
		for(int i = 0; i < dataC.length; i++)
			word += (char) dataC[i];
		System.out.println(word);
		
		s = "<~!!~>";
		byte[] dataD = (Ascii85.fromAscii85(s));
		word = "";
		for(int i = 0; i < dataD.length; i++)
			word += (char) dataD[i];
		System.out.println(word);
		
		s = "<~zH=_,8/T>`AAncL$A,~>";
		byte[] dataE = (Ascii85.fromAscii85(s));
		word = "";
		for(int i = 0; i < dataE.length; i++)
			word += (char) dataE[i];
		System.out.println(word);
	}
	
}
