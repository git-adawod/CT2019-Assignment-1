package postfix;

import java.util.Scanner;

public class myApp {
	public static void main(String[] args) {
		/*Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while(loop)
		{
			System.out.println("Enter a valid infix expression: ");
			String userInput = scanner.nextLine();
			
			if(userInput.toLowerCase().contains("exit")) {
				loop = false;
				break;
			}
			
			String postFix = InfixToPostfix.convertToPostfix(userInput);
			if(postFix != "") System.out.println("Postfix expression: " + postFix + "\n");
			
			PostfixCalculator pc = new PostfixCalculator(postFix);
			System.out.println(pc.calculate() + "\n\n");
		}
		scanner.close();*/
		InfixToPostfix i2p = new InfixToPostfix();
		System.out.println(i2p.convertToPostfix("(1+2/3+(4*5)-6)"));
	}
}
