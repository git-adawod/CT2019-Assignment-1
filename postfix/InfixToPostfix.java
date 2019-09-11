package postfix;

public class InfixToPostfix {
	
	public static boolean isOperator(char ch) {
		return ch == '(' || ch == ')' || ch == '^' || ch == '*' || ch == '/' || ch == '+' || ch == '-';
	}
	
	public static boolean isOperand(char ch) {
		//Values include 0-9
		return (ch >= '0' && ch <= '9');
	}
	
	private static int getPrecedence(char ch) {
		//The values returned will be used for comparison to determine the order of operation
		switch(ch) {
		//+ and - have lowest precedence (1)
		case '+':
		case '-':
			return 1;
		
		//* and / have medium precedence (2)
		case '*':
		case '/':
			return 2;
		
		//^ has highest precedence (3)	
		case '^':
			return 3;
		}
		return -1;
	}
	
	public static boolean checkFormat(String infix) {
		//Remove all whitespaces
		infix = infix.replaceAll(" ", "");
		//Keep count of brackets
		int openBrace = 0, closeBrace = 0;
		
		for(int i = 0; i < infix.length(); i++)
		{		//If its an operand 0-9, continue
				if(isOperand(infix.charAt(i))) continue;
				if(isOperator(infix.charAt(i))) {
					try {
						//If the operator is a bracket, increment and continue
							//But after the bracket there shouldnt be an operator
						if(infix.charAt(i) == '(') {
							if(isOperator(infix.charAt(i+1)) && infix.charAt(i+1) != '(') {
								System.out.println("\nInvalid format: (" + infix.charAt(i+1));
								return false;
							}
							openBrace++;
							continue;
						}
						if(infix.charAt(i) == ')') {
							if(isOperator(infix.charAt(i+1)) && infix.charAt(i+1) != ')') {
								System.out.println("\nInvalid format: )" + infix.charAt(i+1));
								return false;
							}
							closeBrace++;
							continue;
						}
						//If after the operand another operand is inputed (3 ++ 1) for example
						if(!isOperand(infix.charAt(i+1))) {
							//If its a bracket, no problem (3 + (1 + 2))
							if(infix.charAt(i+1) == '(' || infix.charAt(i+1) == ')')
							{
								continue;
							}
							else {
								System.out.println("\nInvalid Format: " + infix.charAt(i) + "" + infix.charAt(i+1));
								return false;
							}
						}
							
					}
					//Handle exception caused by missing brackets
					catch(java.lang.StringIndexOutOfBoundsException ex)
					{
						if(infix.charAt(i) == ')') return true;
						System.out.println("\nInvalid Format");
						return false;
					}
				}
				else {
					System.out.println("\nInvalid character: " + infix.charAt(i) + "\nPlease enter a valid expression");
					return false;
				}
		}
		
		if(openBrace != closeBrace) {
			System.out.println("\nBrackets do not match\nOpened: " + openBrace + "\nClosed: " + closeBrace);
			return false;
		}
		System.out.println("\nFormat Success");
		return true;
	}
	
	public static String convertToPostfix(String infix) {
		//If the format is invalid
		//if(!checkFormat(infix)) return "";
		
		ArrayStack myStack = new ArrayStack();
		String postfix = "";
		char ch;
		
		for (int i = 0; i < infix.length(); i++) {
			ch = infix.charAt(i);
			
			//The operands come first unless there's a bracket
				//So append the operand to the postfix string
			if(isOperand(ch)) {
				postfix += ch;
			} else if(ch == '(') {
				myStack.push(ch);
				
			} else if(ch == ')') {
				while(!myStack.isEmpty() && myStack.top() != (Object)'(') {
					postfix += myStack.pop();
				}
				
				if(!myStack.isEmpty() && myStack.top() != (Object)'(') {
					return null;
				} else if(!myStack.isEmpty()) {
					myStack.pop();
				}
			} else if(isOperator(ch)) {
				if(!myStack.isEmpty() && getPrecedence(ch) <= getPrecedence((char)myStack.top())) {
					postfix += myStack.pop();
				}
				myStack.push(ch);
			}
		}
		
		while(!myStack.isEmpty()) {
			postfix += myStack.pop();
		}
		
		return postfix;
	}
}
