package postfix;
import javax.swing.JOptionPane;

public class ArrayStack implements Stack {
	//Stack limit
	protected int capacity;
	//Actual limit
	protected static final int CAPACITY = 1000;
	//The stack
	protected Object S[];
	//Top of stack
	protected int top = -1;
	
	//Default capacity 
	public ArrayStack() {
		this(CAPACITY);
	}
	
	//Set capacity
	public ArrayStack(int cap) {
		capacity = (cap > 0) ? cap : CAPACITY;
		S = new Object[capacity];
	}

	@Override
	public void push(Object n) {
		//Handle full stack
		if(isFull()) {
			System.out.println("Stack is Full");
			return;
		}
		//Increment top
		top++;
		//Put the object at the top
		S[top] = n;
		
	}

	@Override
	public Object pop() {
		Object element;
		//Handle empty stack
		if (isEmpty()) {
		   System.out.println("Stack is Empty");
		    return  null;
		}
		//Get the top element
		element = S[top];
		//Remove the element
		S[top] = null;
		//Decrement top
		top--;
		return element;
	}

	@Override
	public Object top() {
		if (isEmpty()) {
			 System.out.println("Stack is Empty");
			 return null;
		 }
		 return S[top];
	}

	@Override
	public boolean isEmpty() {
		return (top < 0);
	}

	@Override
	public boolean isFull() {
		return (top == capacity-1);
	}

	@Override
	public int size() {
		return (top + 1);
	}
}
