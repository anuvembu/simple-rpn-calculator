package rpnCalculator;

import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class RPN {
	private static final Scanner scan = new Scanner(System.in);	
	static Stack<Double> rpnStack = new Stack<>();
	
	/*
	 * Computes the outcome of a given expression in Reverse Polish Notation
	 * @param str the expression to compute
	 */
	public static String compute(String str) throws 
	ArithmeticException, EmptyStackException, NumberFormatException{
		
		String result = null;
		try {
			for(String s: str.split("\\s+")) {			

				if(s.matches("[+|*|/|]") || s.matches("-")) {			
					if(rpnStack.size() < 2) {						
						System.out.println("operator:" + s +" (position: " + (str.indexOf(s)*2+1) + "): insufficient parameters");
						break;
					}
					else {
						double rightNum = rpnStack.pop();
						double leftNum = rpnStack.pop();
						switch(s) {
						case "+": rpnStack.push(rightNum+leftNum); break;
						case "-": rpnStack.push(leftNum-rightNum); break;
						case "*": rpnStack.push(rightNum*leftNum); break;
						case "/": rpnStack.push(leftNum/rightNum); break;						
						}
					}
				}					
				else if(s.matches("sqrt")) {
					if(rpnStack.size() < 1) {						
						System.out.println("operator:" + s +" (position: " + str.indexOf(s)+ "): insufficient parameters");
						//break;
					}
					else {
						double d = rpnStack.pop();
						rpnStack.push(Math.sqrt(d));
					}					
				}
				else if(s.matches("undo")) {
					if(!rpnStack.isEmpty())
						rpnStack.pop();
				}
				else if(s.matches("clear")) {
					rpnStack.clear();
				}
				else {			
					rpnStack.push(Double.valueOf(s));
				}
			}	
		}catch (NumberFormatException e) {
			System.out.println("Valid inputs are whitespace separated real numbers and operators +,-,*,/,sqrt,undo,clear");
		} catch (ArithmeticException e) {
			System.out.println("Invalid arithmetic operation");
		} catch(Throwable err) {
			System.out.println(err.getMessage()); 
		}
		
		//Result formatting
		String temp = rpnStack.toString().replace(",", "");		
		result = temp.substring(1, temp.length()-1);
		//DecimalFormat dForm = new DecimalFormat("#");
		result = result.replaceAll("([0-9])\\.0+([^0-9]|$)", "$1$2");
		return result;
	}
	
	/*
	 * Command line input is read for params
	 */
	public static void main(String[] args) {		
		try {		
			System.out.println("Enter the input for RPN calculator");
			while(scan.hasNextLine()) {
				String expr = scan.nextLine();
				//System.out.println("Input is "+expr);
				String result = compute(expr);
				System.out.println("stack: "+result);
			}
		} catch(Throwable err) {
			System.out.println(err.getMessage());
		}
	}
}
