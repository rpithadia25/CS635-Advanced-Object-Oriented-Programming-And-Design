package interpreter;

import assignment3.Context;

public abstract class Expression {

	public int data;

	public abstract void interpret(Context context);

}
