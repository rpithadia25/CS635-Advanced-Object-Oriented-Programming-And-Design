package interpreter;

import assignment3.Turtle;

public abstract class Expression {

	public int data;

	public abstract void interpret(Turtle context);

}
