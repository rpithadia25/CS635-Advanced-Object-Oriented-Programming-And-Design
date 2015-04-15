package command;

import java.util.Stack;

import assignment3.Context;

public class CommandManager {
	public Stack<Command> commands = new Stack<Command>();
	
	public void execute(Command command, Context context) {
		command.interpret(context);
		commands.push(command);
	}
	
	public void undo(Context context) {
		if(commands.size() > 0) {
			Command command = (Command) commands.pop();
			command.undo(context);
		}
	}
}
