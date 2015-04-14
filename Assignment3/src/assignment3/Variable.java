package assignment3;

public class Variable extends Command {

	int value;

	public Variable(int value) {
		this.value = value;
	}

	// @Override
	// public void interpret(Turtle context) {
	// // TODO Auto-generated method stub
	// }

	@Override
	public void accept(Visitor visitor) {
		
	}

}
