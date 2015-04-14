package assignment3;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TurtleVisitorTest {

	protected ArrayList<Command> statements = new ArrayList<Command>();
	protected HashMap<String, String> variables = new HashMap<String, String>();
	protected Turtle turtle;
	protected int repetations;
	protected double delta = 1e-8;
	protected int repeatCount;
	protected int endCount;
	protected TurtleVisitor turtleVisitor = new TurtleVisitor();
	protected ArrayList<String> repeatList;
	
	private ArrayList<Command> readFile() {
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
				case Constants.PENUP:
					PenUp penUp = new PenUp();
					penUp.accept(turtleVisitor);
					break;
				case Constants.PENDOWN:
					PenDown penDown = new PenDown();
					penDown.accept(turtleVisitor);
					break;
				case Constants.MOVE:
					Move move = new Move(Integer.parseInt(value));
					move.accept(turtleVisitor);
					break;
				case Constants.TURN:
					Turn turn = new Turn(Integer.parseInt(value));
					turn.accept(turtleVisitor);
					break;
				case Constants.REPEAT:
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
				case Constants.END:
					End end = new End();
					end.accept(turtleVisitor);
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
	
	@Before
	public void setUp() throws Exception {
		statements = new ArrayList<Command>();
		turtle = new Turtle();
	}

	@After
	public void tearDown() throws Exception {
		turtle = null;
		statements = null;
		variables = null;
	}
	
	@Test
	public void testVisitor() {
		readFile();
		statements = turtleVisitor.listOfCommands;
		interpretCommand(statements);
		assertEquals(22.99, turtle.getCurrentLocation().getX(), delta);
		assertEquals(27.5, turtle.getCurrentLocation().getY(), delta);
	}

}
