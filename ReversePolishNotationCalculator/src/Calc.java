//Solution for https://www.codewars.com/kata/52f78966747862fc9a0009ae/train/java
import java.util.regex.Pattern;

public class Calc {

	public static double evaluate(String expr){
		
		System.out.println(expr);
		if(expr.equals(""))
			return 0;
		Stack stk = new Stack();
		String[] parts = expr.split(" ");
		for(int i = 0; i < parts.length; i++){
			if(Pattern.matches("[\\d]+[\\.]*[\\d]*",parts[i])){
				stk.push(new Node(parts[i]));
				System.out.println(parts[i]);
			}
			else if(parts[i].equals("+")){
				double b = Double.parseDouble((stk.pop()).data);
				double a = Double.parseDouble((stk.pop()).data);
				Node n = new Node(Double.toString(a + b));
				stk.push(n);
			}
			else if(parts[i].equals("-")){
				double b = Double.parseDouble((stk.pop()).data);
				double a = Double.parseDouble((stk.pop()).data);
				Node n = new Node(Double.toString(a - b));
				stk.push(n);
			}
			else if(parts[i].equals("*")){
				double b = Double.parseDouble((stk.pop()).data);
				double a = Double.parseDouble((stk.pop()).data);
				Node n = new Node(Double.toString(a * b));
				stk.push(n);
			}
			else if(parts[i].equals("/")){
				double b = Double.parseDouble((stk.pop()).data);
				double a = Double.parseDouble((stk.pop()).data);
				Node n = new Node(Double.toString(a / b));
				stk.push(n);
			}
		}
		double returnNum = Double.parseDouble((stk.pop()).data);
		return returnNum;
	}
	
}

class Stack {

	public static Node top;
	
	public Stack(){
		top = null;
	}
	
	public Stack(Node n){
		top = n;
	}
	
	public boolean isEmpty(){
		if(top == null)
			return true;
		return false;
	}
	
	public void push(Node n){
		if(this.isEmpty())
			top = n;
		else{
			n.next = top;
			top = n;
		}
	}
	
	public Node pop(){
		if(this.isEmpty())
			throw new NullPointerException();
		else{
			Node temp = top;
			top = top.next;
			return temp;
		}
	}
	
}

class Node {

	public String data;
	public Node next;
	
	public Node(String d){
		data = d;
		next = null;
	}
	
	public Node(String d, Node n){
		data = d;
		next = n;
	}
	
}
