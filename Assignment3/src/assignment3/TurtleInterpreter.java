package assignment3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TurtleInterpreter {

	private static final String MOVE = "move";
	private static final String TURN = "turn";
	private static final String REPEAT = "repeat";
	private static final String END = "end";
	private static final String PENUP = "penUp";
	private static final String PENDOWN = "penDown";
	private ArrayList<Command> statements;
	private Turtle turtle;
	private static int repetations;
	private HashMap<String, String> variables = new HashMap<String, String>();
	
	//TODO Create separate classes for these methods
	protected void move(String input) {
		if(input.startsWith("$")) {
			statements.add(new Move(Integer.parseInt(variables.get(input))));
		} else {
			statements.add(new Move(Integer.parseInt(input)));
		}
	}
	
	protected void penUp() {
		statements.add(new PenUp());
	}
	
	protected void penDown() {
		statements.add(new PenDown());
	}
	
	protected void turn(String input) {
		if(input.startsWith("$")) {
			statements.add(new Turn(Integer.parseInt(variables.get(input))));
		} else {
			statements.add(new Turn(Integer.parseInt(input)));}
	}
	
	protected void repeat(String input) {
		if(input.startsWith("$")) {
			statements.add(new Repeat(Integer.parseInt(variables.get(input))));
		} else {
			statements.add(new Repeat(Integer.parseInt(input)));}
	}
	
	protected void end() {
		statements.add(new End());
	}
	
	private void/*ArrayList<Command>*/ readFile() {
		//ArrayList<Command> commands = new ArrayList<Command>();
		statements = new ArrayList<Command>();
		Path path = Paths.get("src/example.txt");
		try {
			Files.lines(path).forEach(statement -> {
				
				String[] tokens = statement.split(" ");
				if(tokens[0].startsWith("$")) {
					variables.put(tokens[0], tokens[2]);
				}
				switch (tokens[0].trim()) {
				case PENUP:
					penUp();
					break;
				case PENDOWN:
					penDown();
					break;
				case MOVE:
					//commands.add(new Move(Integer.parseInt(tokens[1])));
					move(tokens[1]);
					break;
				case TURN:
					//commands.add(new Turn(Integer.parseInt(tokens[1])));
					turn(tokens[1]);
					break;
				case REPEAT:
					//commands.add(new Repeat(Integer.parseInt(tokens[1])));
					repeat(tokens[1]);
					break;
				case END:
					//commands.add(new End());
					end();
					break;
				default:
					break;
				}
			});

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return commands;
	}

	private void interpretCommand(ArrayList<Command> list) {

		Iterator<Command> listIterator = list.iterator();

		while (listIterator.hasNext()) {
			Command currentCommand = listIterator.next();
			if (currentCommand.getClass() == Repeat.class) {
				repetations = ((Repeat) currentCommand).times;
				statements = new ArrayList<Command>();
				while (currentCommand.getClass() != End.class) {
					currentCommand = listIterator.next();
					if (currentCommand.getClass() != End.class) {
						statements.add(currentCommand);
					}
				}			
			} else {
				currentCommand.interpret(turtle);
			}
		}
		while (repetations > 0) {
			repetations--;
			interpretCommand(statements);
		}
	}

	
	//TODO: Replace this with test cases
	public static void main(String[] args) {
		
		TurtleInterpreter interpreter = new TurtleInterpreter();
		interpreter.readFile();
		interpreter.turtle = new Turtle();
		interpreter.interpretCommand(interpreter.statements);
		System.out.println(interpreter.turtle);
	}
}

