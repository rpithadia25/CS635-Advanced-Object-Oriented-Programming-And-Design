package assignment3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class RunTurtleProgram {

	private static final String MOVE = "move";
	private static final String TURN = "turn";
	private static final String REPEAT = "repeat";
	private static final String END = "end";
	private static final String VARIABLE = "$";
	private static ArrayList<Expression> statements = parseProgram();
	private static Turtle turtle;
	private static int repetations;

	static ArrayList<Expression> parseProgram() {
		ArrayList<Expression> commands = new ArrayList<Expression>();
		Path path = Paths.get("src/example.txt");
		try {
			Files.lines(path).forEach(statement -> {
				String[] tokens = statement.split(" ");
				switch (tokens[0].trim()) {
				case MOVE:
					commands.add(new Move(tokens[1]));
					break;
				case TURN:
					commands.add(new Turn(tokens[1]));
					break;
				case VARIABLE:
					//TODO
					break;
				case REPEAT:
					commands.add(new Repeat(Integer.parseInt(tokens[1])));
					break;
				case END:
					commands.add(new End());
				}
			});

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return commands;
	}

	public static void executeExpression(ArrayList<Expression> list) {

		Iterator<Expression> listIterator = list.iterator();

		while (listIterator.hasNext()) {
			Expression currentExpression = listIterator.next();
			if (currentExpression.getClass() == Repeat.class) {
				repetations = ((Repeat) currentExpression).times;
				statements = new ArrayList<Expression>();
				while (currentExpression.getClass() != End.class) {
					currentExpression = listIterator.next();
					if (currentExpression.getClass() != End.class) {
						statements.add(currentExpression);
					}
				}			
			} else {
				currentExpression.interpret(turtle);
				System.out.println(turtle.distance + " : "+turtle);
			}
		}
		while (repetations > 0) {
			repetations--;
			executeExpression(statements);
		}
	}

	public static void main(String[] args) {
		
		turtle = new Turtle();
		executeExpression(statements);
		System.out.println(turtle);
	}
}
