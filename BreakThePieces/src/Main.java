
public class Main {

	public static void main(String[] args){
		/*
		String shape = String.join("\n", new String[] {	"+-------------------+--+",
													  	"|                   |  |",
														"|                   |  |",
														"|  +----------------+  |",
														"|  |                   |",
														"|  |                   |",
														"+--+-------------------+"	});
		
		String[] arr = BreakPieces.process(shape);
		for(int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
		*/
		/*
		String shape = String.join("\n", new String[] {	"+-----------------+",
													  	"|                 |",
														"|   +-------------+",
														"|   |",
														"|   |",
														"|   |",
														"|   +-------------+",
														"|                 |",
														"|                 |",
														"+-----------------+"});

		String[] arr = BreakPieces.process(shape);
		for(int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
		*/
		
		String shape = String.join("\n", new String[] {	"   +-----+       ",       
				   										"   |     |       ",       
													    "   |     |       ",       
													    "   +-----+-----+ ", 
													    "         |     | ", 
													    "         |     | ", 
													    "         +-----+ "}); 

		String[] arr = BreakPieces.process(shape);
		for(int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
		
	}
	
}
