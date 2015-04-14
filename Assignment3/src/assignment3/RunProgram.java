package assignment3;

import interpreter.Expression;
import interpreter.ExpressionEnd;
import interpreter.ExpressionMove;
import interpreter.ExpressionPenDown;
import interpreter.ExpressionPenUp;
import interpreter.ExpressionRepeat;
import interpreter.ExpressionTurn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Stream;

import visitor.DistanceVisitorImpl;
import visitor.TurtleVisitorImpl;
import command.Command;
import command.CommandEnd;
import command.CommandMove;
import command.CommandPenDown;
import command.CommandPenUp;
import command.CommandRepeat;
import command.CommandTurn;

public class RunProgram {

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
	private static TurtleVisitorImpl visitor = new TurtleVisitorImpl();
	private static DistanceVisitorImpl distanceCoveredVisitor = new DistanceVisitorImpl();
	private static int distanceCovered;

	private static ArrayList<Command> readFile() {
		ArrayList<Command> commands = new ArrayList<Command>();
		Path path = Paths.get("src/testProgram1.txt");
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
					CommandPenUp penUp = new CommandPenUp();
					penUp.accept(visitor);
					// commands.add(new PenUp());
					break;
				case PENDOWN:
					CommandPenDown penDown = new CommandPenDown();
					penDown.accept(visitor);
					// commands.add(new PenDown());
					break;
				case MOVE:
					CommandMove move = new CommandMove(Integer.parseInt(value));
					move.accept(visitor);
					distanceCovered = move.acceptDistanceCovered(distanceCoveredVisitor);
					// commands.add(new Move(Integer.parseInt(value)));
					break;
				case TURN:
					CommandTurn turn = new CommandTurn(Integer.parseInt(value));
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
					CommandEnd end = new CommandEnd();
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
		Path path = Paths.get("src/testProgram1.txt");
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
			if (currentCommand.getClass() == CommandRepeat.class) {
				repetations = ((CommandRepeat) currentCommand).times;
				statements = new ArrayList<Command>();
				while (currentCommand.getClass() != CommandEnd.class) {
					currentCommand = listIterator.next();
					if (currentCommand.getClass() != CommandEnd.class) {
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
//		 expressions = new ArrayList<Expression>();
//		 expressions = readInterpreter();
//		 turtle = new Turtle();
//		 interpretExpression(expressions);

		statements = new ArrayList<Command>();
		statements = readFile();
//		readFile();
//		statements = visitor.listOfCommands;
		turtle = new Turtle();
		interpretCommand(statements);
		System.out.println(turtle);
		System.out.println(distanceCoveredVisitor.getDistanceCovered());
	}
}
