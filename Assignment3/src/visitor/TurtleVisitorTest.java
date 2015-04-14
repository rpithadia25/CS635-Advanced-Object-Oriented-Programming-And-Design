package visitor;

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

import assignment3.Constants;
import assignment3.Turtle;
import command.Command;
import command.CommandEnd;
import command.CommandMove;
import command.CommandPenDown;
import command.CommandPenUp;
import command.CommandRepeat;
import command.CommandTurn;

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
	
	private ArrayList<Command> readFile(String filePath) {
		ArrayList<Command> commands = new ArrayList<Command>();
		Path path = Paths.get(filePath);
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
					CommandPenUp penUp = new CommandPenUp();
					penUp.accept(turtleVisitor);
					break;
				case Constants.PENDOWN:
					CommandPenDown penDown = new CommandPenDown();
					penDown.accept(turtleVisitor);
					break;
				case Constants.MOVE:
					CommandMove move = new CommandMove(Integer.parseInt(value));
					move.accept(turtleVisitor);
					break;
				case Constants.TURN:
					CommandTurn turn = new CommandTurn(Integer.parseInt(value));
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
					CommandEnd end = new CommandEnd();
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
			if (currentCommand.getClass() == CommandRepeat.class) {
				repetations = ((CommandRepeat) currentCommand).times;
				statements = new ArrayList<Command>();
				while (currentCommand.getClass() != CommandEnd.class) {
					currentCommand = listIterator.next();
					if (currentCommand.getClass() != CommandEnd.class) {
						statements.add(currentCommand);
					}
				}
			} else { //Any Command other than Repeat
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
		readFile("src/testProgram1.txt");
		statements = turtleVisitor.listOfCommands;
		interpretCommand(statements);
		assertEquals(22.99, turtle.getCurrentLocation().getX(), delta);
		assertEquals(27.5, turtle.getCurrentLocation().getY(), delta);
	}
	
	@Test
	public void testVisitorWithRepeat() {
		readFile("src/testProgram2.txt");
		statements = turtleVisitor.listOfCommands;
		interpretCommand(statements);
		assertEquals(10.0, turtle.getCurrentLocation().getX(), delta);
		assertEquals(0.0, turtle.getCurrentLocation().getY(), delta);
	}
	
	@Test
	public void testDegrees() {
		readFile("src/testProgram1.txt");
		statements = turtleVisitor.listOfCommands;
		interpretCommand(statements);
		assertEquals(30, turtle.getDirection(), delta);
	}
	
	@Test
	public void testDistance() {
		readFile("src/testProgram1.txt");
		statements = turtleVisitor.listOfCommands;
		interpretCommand(statements);
		assertEquals(45, turtle.getDistance());
	}

}
