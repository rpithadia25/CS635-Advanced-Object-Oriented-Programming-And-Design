package assignment3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Stream;

public class TurtleInterpreter {

	private static final String MOVE = "move";
	private static final String TURN = "turn";
	private static final String REPEAT = "repeat";
	private static final String END = "end";
	private static final String PENUP = "penUp";
	private static final String PENDOWN = "penDown";
	private static ArrayList<Command> statements;
	private static ArrayList<Expression> expressions;
	private static Turtle turtle;
	private static int repetations;
	private static int repeatCount;
	private static int endCount;
	private static ArrayList<String> repeatList;
	private static HashMap<String, String> variables = new HashMap<String, String>();
	private static TurtleVisitor visitor = new TurtleVisitor();

	private static ArrayList<Command> readFile() {
		ArrayList<Command> commands = new ArrayList<Command>();
		Path path = Paths.get("src/example.txt");
		try {
			Stream<String> lines = Files.lines(path);
			Iterator<String> inputIterator = lines.iterator();
			while (inputIterator.hasNext()) {
				String data = inputIterator.next();
				String[] tokens = data.split(" ");
				String key = tokens[0].trim();
				String value = null;

				if (key.startsWith("$")) {
					variables.put(tokens[0], tokens[2]);
					value = variables.get(key);
				} else if (tokens.length > 1) {
					value = tokens[1];
				}

				switch (key) {
				case PENUP:
					PenUp penUp = new PenUp();
					penUp.accept(visitor);
					// commands.add(new PenUp());
					break;
				case PENDOWN:
					PenDown penDown = new PenDown();
					penDown.accept(visitor);
					// commands.add(new PenDown());
					break;
				case MOVE:
					Move move = new Move(Integer.parseInt(value));
					move.accept(visitor);
					// commands.add(new Move(Integer.parseInt(value)));
					break;
				case TURN:
					Turn turn = new Turn(Integer.parseInt(value));
					turn.accept(visitor);
					// commands.add(new Turn(Integer.parseInt(value)));
					break;
				case REPEAT:
//					Repeat repeat = new Repeat(Integer.parseInt(value));
//					repeat.accept(visitor);
					repeatCount++;
					repeatList = new ArrayList<String>();
					repeatList.add(data.trim());
					while (repeatCount != endCount && inputIterator.hasNext()) {
						data = inputIterator.next();

						if (data.contains("repeat")) {
							repeatCount++;
						}
						if (data.contains("end")) {
							endCount++;
						}
						repeatList.add(data.trim());
					}
					break;
				case END:
					End end = new End();
					end.accept(visitor);
					// commands.add(new End());
					break;
				default:
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return commands;
	}

	private static ArrayList<Expression> readInterpreter() {
		ArrayList<Expression> commands = new ArrayList<Expression>();
		Path path = Paths.get("src/example.txt");
		try {
			Files.lines(path).forEach(
					statement -> {

						String[] tokens = statement.split(" ");
						String key = tokens[0].trim();
						String value = null;

						if (key.startsWith("$")) {
							variables.put(tokens[0], tokens[2]);
							value = variables.get(key);
						} else if (tokens.length > 1) {
							value = tokens[1];
						}

						switch (key) {
						case PENUP:
							commands.add(new ExpressionPenUp());
							break;
						case PENDOWN:
							commands.add(new ExpressionPenDown());
							break;
						case MOVE:
							commands.add(new ExpressionMove(Integer
									.parseInt(value)));
							break;
						case TURN:
							commands.add(new ExpressionTurn(Integer
									.parseInt(value)));
							break;
						case REPEAT:
							if (value.startsWith("$")) {
								commands.add(new ExpressionRepeat(Integer
										.parseInt(variables.get(value))));
							} else {
								commands.add(new ExpressionRepeat(Integer
										.parseInt(value)));
							}
							break;
						case END:
							commands.add(new ExpressionEnd());
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

	private static void interpretExpression(ArrayList<Expression> list) {

		Iterator<Expression> listIterator = list.iterator();

		while (listIterator.hasNext()) {
			Expression currentInterpreter = listIterator.next();
			if (currentInterpreter.getClass() == ExpressionRepeat.class) {
				repetations = ((ExpressionRepeat) currentInterpreter).times;
				expressions = new ArrayList<Expression>();
				while (currentInterpreter.getClass() != ExpressionEnd.class) {
					currentInterpreter = listIterator.next();
					if (currentInterpreter.getClass() != ExpressionEnd.class) {
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

	// TODO: Replace this with test cases
	public static void main(String[] args) {
		// expressions = new ArrayList<Interpreter>();
		// expressions = readInterpreter();
		// turtle = new Turtle();
		// interpretExpression(expressions);

		statements = new ArrayList<Command>();
		// statements = readFile();
		readFile();
		statements = visitor.listOfCommands;
		turtle = new Turtle();
		interpretCommand(statements);
		System.out.println(turtle);
	}
}
