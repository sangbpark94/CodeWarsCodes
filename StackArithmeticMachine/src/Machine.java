// Solution for https://www.codewars.com/kata/54c1bf903f0696f04600068b/train/java

public class Machine {

	private final CPU cpu;
	
	public Machine(CPU cpu) {
		this.cpu = cpu;
	}
	
	public void exec(String instr) {
		
		String[] instrParts = instr.split(" ");
		if(instrParts[0].equals("push")){
			if(instrParts[1].equals("a") || instrParts[1].equals("b") || instrParts[1].equals("c") || instrParts[1].equals("d"))
				cpu.writeStack(cpu.readReg(instrParts[1]));
			else
				cpu.writeStack(Integer.parseInt(instrParts[1]));
		}
		else if(instrParts[0].equals("pop")){
			if(instrParts.length == 1)
				cpu.popStack();
			else
				cpu.writeReg(instrParts[1], cpu.popStack());
		}
		else if(instrParts[0].equals("pushr")){
			cpu.writeStack(cpu.readReg("a"));
			cpu.writeStack(cpu.readReg("b"));
			cpu.writeStack(cpu.readReg("c"));
			cpu.writeStack(cpu.readReg("d"));
		}
		else if(instrParts[0].equals("pushrr")){
			cpu.writeStack(cpu.readReg("d"));
			cpu.writeStack(cpu.readReg("c"));
			cpu.writeStack(cpu.readReg("b"));
			cpu.writeStack(cpu.readReg("a"));
		}
		else if(instrParts[0].equals("popr")){
			cpu.writeReg("d",cpu.popStack());
			cpu.writeReg("c",cpu.popStack()); 
			cpu.writeReg("b",cpu.popStack()); 
			cpu.writeReg("a",cpu.popStack()); 
		}
		else if(instrParts[0].equals("poprr")){
			cpu.writeReg("a",cpu.popStack());
			cpu.writeReg("b",cpu.popStack()); 
			cpu.writeReg("c",cpu.popStack()); 
			cpu.writeReg("d",cpu.popStack()); 
		}
		else if(instrParts[0].equals("mov")){
			String temp = "";
			for(int j = 0; j < instrParts[1].length() - 1; j++)
				temp += Character.toString(instrParts[1].charAt(j));
			instrParts[1] = temp;
			if(instrParts[1].equals("a") || instrParts[1].equals("b") || instrParts[1].equals("c") || instrParts[1].equals("d"))
				cpu.writeReg(instrParts[2],cpu.readReg(instrParts[1]));
			else
				cpu.writeReg(instrParts[2],Integer.parseInt(instrParts[1]));
		}
		
		else{
			if(instrParts[0].length() == 4){
				cpu.writeStack(cpu.readReg(Character.toString(instrParts[0].charAt(3))));
				instrParts[0] = Character.toString(instrParts[0].charAt(0)) + Character.toString(instrParts[0].charAt(1)) + Character.toString(instrParts[0].charAt(2));
			}
			else if(instrParts[0].charAt(0) == 'o'){
				cpu.writeStack(cpu.readReg(Character.toString(instrParts[0].charAt(2))));
				instrParts[0] = Character.toString(instrParts[0].charAt(0)) + Character.toString(instrParts[0].charAt(1));
			}
			
			if(instrParts[0].equals("add")){
				String reg = "a";
				int numOfArgs;
				if(instrParts[1].charAt(instrParts[1].length() - 1) == ','){
					String temp = "";
					for(int j = 0; j < instrParts[1].length() - 1; j++)
						temp += Character.toString(instrParts[1].charAt(j));
					instrParts[1] = temp;
					reg = instrParts[2];
				}
				if(instrParts[1].equals("a") || instrParts[1].equals("b") || instrParts[1].equals("c") || instrParts[1].equals("d"))
					numOfArgs = cpu.readReg(instrParts[1]);
				else
					numOfArgs = Integer.parseInt(instrParts[1]);
				int sum = 0;
				for(int i = 0; i < numOfArgs; i++)
					sum += cpu.popStack();
				cpu.writeReg(reg,sum);
			}
			else if(instrParts[0].equals("sub")){
				String reg = "a";
				int numOfArgs;
				if(instrParts[1].charAt(instrParts[1].length() - 1) == ','){
					String temp = "";
					for(int j = 0; j < instrParts[1].length() - 1; j++)
						temp += Character.toString(instrParts[1].charAt(j));
					instrParts[1] = temp;
					reg = instrParts[2];
				}
				if(instrParts[1].equals("a") || instrParts[1].equals("b") || instrParts[1].equals("c") || instrParts[1].equals("d"))
					numOfArgs = cpu.readReg(instrParts[1]);
				else
					numOfArgs = Integer.parseInt(instrParts[1]);
				int diff = cpu.popStack();
				for(int i = 1; i < numOfArgs; i++)
					diff -= cpu.popStack();
				cpu.writeReg(reg,diff);
			}	
			else if(instrParts[0].equals("mul")){
				String reg = "a";
				int numOfArgs;
				if(instrParts[1].charAt(instrParts[1].length() - 1) == ','){
					String temp = "";
					for(int j = 0; j < instrParts[1].length() - 1; j++)
						temp += Character.toString(instrParts[1].charAt(j));
					instrParts[1] = temp;
					reg = instrParts[2];
				}
				if(instrParts[1].equals("a") || instrParts[1].equals("b") || instrParts[1].equals("c") || instrParts[1].equals("d"))
					numOfArgs = cpu.readReg(instrParts[1]);
				else
					numOfArgs = Integer.parseInt(instrParts[1]);
				int prod = 1;
				for(int i = 0; i < numOfArgs; i++)
					prod *= cpu.popStack();
				cpu.writeReg(reg,prod);
			}	
			else if(instrParts[0].equals("div")){
				String reg = "a";
				int numOfArgs;
				if(instrParts[1].charAt(instrParts[1].length() - 1) == ','){
					String temp = "";
					for(int j = 0; j < instrParts[1].length() - 1; j++)
						temp += Character.toString(instrParts[1].charAt(j));
					instrParts[1] = temp;
					reg = instrParts[2];
				}
				if(instrParts[1].equals("a") || instrParts[1].equals("b") || instrParts[1].equals("c") || instrParts[1].equals("d"))
					numOfArgs = cpu.readReg(instrParts[1]);
				else
					numOfArgs = Integer.parseInt(instrParts[1]);
				int quot = cpu.popStack();
				for(int i = 1; i < numOfArgs; i++)
					quot /= cpu.popStack();
				cpu.writeReg(reg,quot);
			}	
			else if(instrParts[0].equals("and")){
				String reg = "a";
				int numOfArgs;
				if(instrParts[1].charAt(instrParts[1].length() - 1) == ','){
					String temp = "";
					for(int j = 0; j < instrParts[1].length() - 1; j++)
						temp += Character.toString(instrParts[1].charAt(j));
					instrParts[1] = temp;
					reg = instrParts[2];
				}
				if(instrParts[1].equals("a") || instrParts[1].equals("b") || instrParts[1].equals("c") || instrParts[1].equals("d"))
					numOfArgs = cpu.readReg(instrParts[1]);
				else
					numOfArgs = Integer.parseInt(instrParts[1]);
				int bitAnd = cpu.popStack();
				for(int i = 1; i < numOfArgs; i++)
					bitAnd &= cpu.popStack();
				cpu.writeReg(reg,bitAnd);
			}	
			else if(instrParts[0].equals("or")){
				String reg = "a";
				int numOfArgs;
				if(instrParts[1].charAt(instrParts[1].length() - 1) == ','){
					String temp = "";
					for(int j = 0; j < instrParts[1].length() - 1; j++)
						temp += Character.toString(instrParts[1].charAt(j));
					instrParts[1] = temp;
					reg = instrParts[2];
				}
				if(instrParts[1].equals("a") || instrParts[1].equals("b") || instrParts[1].equals("c") || instrParts[1].equals("d"))
					numOfArgs = cpu.readReg(instrParts[1]);
				else
					numOfArgs = Integer.parseInt(instrParts[1]);
				int bitOr = cpu.popStack();
				for(int i = 1; i < numOfArgs; i++)
					bitOr |= cpu.popStack();
				cpu.writeReg(reg,bitOr);
			}	
			else if(instrParts[0].equals("xor")){
				String reg = "a";
				int numOfArgs;
				if(instrParts[1].charAt(instrParts[1].length() - 1) == ','){
					String temp = "";
					for(int j = 0; j < instrParts[1].length() - 1; j++)
						temp += Character.toString(instrParts[1].charAt(j));
					instrParts[1] = temp;
					reg = instrParts[2];
				}
				if(instrParts[1].equals("a") || instrParts[1].equals("b") || instrParts[1].equals("c") || instrParts[1].equals("d"))
					numOfArgs = cpu.readReg(instrParts[1]);
				else
					numOfArgs = Integer.parseInt(instrParts[1]);
				int bitXor = cpu.popStack();
				for(int i = 1; i < numOfArgs; i++)
					bitXor ^= cpu.popStack();
				cpu.writeReg(reg,bitXor);
			}
		}
	}
}
