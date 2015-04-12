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
	private static ArrayList<Command> statements;
	private static ArrayList<Interpreter> expressions;
	private static Turtle turtle;
	private static int repetations;
	private static HashMap<String, String> variables = new HashMap<String, String>();
	
	private static ArrayList<Command> readFile() {
		ArrayList<Command> commands = new ArrayList<Command>();
		Path path = Paths.get("src/example.txt");
		try {
			Files.lines(path).forEach(statement -> {
				
				String[] tokens = statement.split(" ");
				String key = tokens[0].trim();
				String value = null;
				
				if(key.startsWith("$")) {
					variables.put(tokens[0], tokens[2]);					
					value = variables.get(key);
				} else if(tokens.length > 1){
					value = tokens[1];
				}
				switch (key) {
				case PENUP:
					commands.add(new PenUp());
					break;
				case PENDOWN:
					commands.add(new PenDown());
					break;
				case MOVE:		
					commands.add(new Move(Integer.parseInt(value)));
					break;
				case TURN:
					commands.add(new Turn(Integer.parseInt(value)));
					break;
				case REPEAT:
					if(value.startsWith("$")) {
						commands.add(new Repeat(Integer.parseInt(variables.get(value))));
					} else {
						commands.add(new Repeat(Integer.parseInt(value)));}
					break;
				case END:
					commands.add(new End());
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
		return commands;
	}
	
	private static ArrayList<Interpreter> readInterpreter() {
		ArrayList<Interpreter> commands = new ArrayList<Interpreter>();
		Path path = Paths.get("src/example.txt");
		try {
			Files.lines(path).forEach(statement -> {
				
				String[] tokens = statement.split(" ");
				String key = tokens[0].trim();
				String value = null;
				
				if(key.startsWith("$")) {
					variables.put(tokens[0], tokens[2]);					
					value = variables.get(key);
				} else if(tokens.length > 1){
					value = tokens[1];
				}
				switch (key) {
				case PENUP:
					commands.add(new InterpretPenUp());
					break;
				case PENDOWN:
					commands.add(new InterpretPenDown());
					break;
				case MOVE:		
					commands.add(new InterpretMove(Integer.parseInt(value)));
					break;
				case TURN:
					commands.add(new InterpretTurn(Integer.parseInt(value)));
					break;
				case REPEAT:
					if(value.startsWith("$")) {
						commands.add(new InterpretRepeat(Integer.parseInt(variables.get(value))));
					} else {
						commands.add(new InterpretRepeat(Integer.parseInt(value)));}
					break;
				case END:
					commands.add(new InterpretEnd());
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
		return commands;
	}

	private static void interpretCommand(ArrayList<Command> list) {

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
	
	private static void interpretExpression(ArrayList<Interpreter> list) {

		Iterator<Interpreter> listIterator = list.iterator();

		while (listIterator.hasNext()) {
			Interpreter currentInterpreter = listIterator.next();
			if (currentInterpreter.getClass() == InterpretRepeat.class) {
				repetations = ((InterpretRepeat) currentInterpreter).times;
				expressions = new ArrayList<Interpreter>();
				while (currentInterpreter.getClass() != InterpretEnd.class) {
					currentInterpreter = listIterator.next();
					if (currentInterpreter.getClass() != InterpretEnd.class) {
						expressions.add(currentInterpreter);
					}
				}			
			} else {
				currentInterpreter.interpret(turtle);
			}
		}
		while (repetations > 0) {
			repetations--;
			interpretExpression(expressions);
		}
	}

	
	//TODO: Replace this with test cases
	public static void main(String[] args) {
//		expressions = new ArrayList<Interpreter>();
//		expressions = readInterpreter();
//		turtle = new Turtle();
//		interpretExpression(expressions);
		
		statements = new ArrayList<Command>();
		statements = readFile();
		turtle = new Turtle();
		interpretCommand(statements);
		System.out.println(turtle);
	}
}

