package postfix;

public class PostfixCalculator {
	String postfix;
	ArrayStack myStack = new ArrayStack();
	double leftOperand, rightOperand;
	
	public PostfixCalculator(String postfix) {
		this.postfix = postfix;
		for(int i = 0; i < postfix.length(); i++) {
			myStack.push(postfix.charAt(i));
		}
	}
	
	public String calculate() {
		if(postfix == "") return "Cannot Calculate";
		
		for(int i = 0; i < postfix.length(); i++) {
			if(InfixToPostfix.isOperand(postfix.charAt(i))) {
				myStack.push(postfix.charAt(i));
				
			} else if(InfixToPostfix.isOperator(postfix.charAt(i))) {
				rightOperand = Double.parseDouble(myStack.pop().toString());
				leftOperand = Double.parseDouble(myStack.pop().toString());
				switch(postfix.charAt(i)) {
				case '+':
					myStack.push(leftOperand + rightOperand);
					break;
				case '-':
					myStack.push(leftOperand - rightOperand);
					break;
				case '*':
					myStack.push(leftOperand * rightOperand);
					break;
				case '/':
					myStack.push(leftOperand / rightOperand);
					break;
				case '^':
					myStack.push(Math.pow(leftOperand, rightOperand));
				}
			}
		}
		return myStack.pop().toString();
	}
}
