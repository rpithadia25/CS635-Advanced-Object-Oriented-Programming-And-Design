package command;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
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

public class TurtleCommandTest {

	protected ArrayList<Command> commands = new ArrayList<Command>();
	protected HashMap<String, String> variables = new HashMap<String, String>();
	protected Turtle turtle;
	protected int repetations;
	protected Point2D expectedLocation = null;
	protected double delta = 1e-8;

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
					commands.add(new CommandPenUp());
					break;
				case Constants.PENDOWN:
					commands.add(new CommandPenDown());
					break;
				case Constants.MOVE:
					commands.add(new CommandMove(Integer.parseInt(value)));
					break;
				case Constants.TURN:
					commands.add(new CommandTurn(Integer.parseInt(value)));
					break;
				case Constants.REPEAT:
					if (value.startsWith("$")) {
						commands.add(new CommandRepeat(Integer
								.parseInt(variables.get(value))));
					} else {
						commands.add(new CommandRepeat(Integer.parseInt(value)));
					}
					break;
				case Constants.END:
					commands.add(new CommandEnd());
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
				commands = new ArrayList<Command>();
				while (currentCommand.getClass() != CommandEnd.class) {
					currentCommand = listIterator.next();
					if (currentCommand.getClass() != CommandEnd.class) {
						commands.add(currentCommand);
					}
				}
			} else { // Any Command other than Repeat
				currentCommand.interpret(turtle);
			}
		}
		while (repetations > 0) {
			repetations--;
			interpretCommand(commands);
		}
	}

	@Before
	public void setUp() throws Exception {
		turtle = new Turtle();
		interpretCommand(commands);
	}

	@After
	public void tearDown() throws Exception {
		turtle = null;
		commands = null;
		variables = null;
	}

	@Test
	public void testStatements() {
		commands = readFile("src/testProgram1.txt");
		assertNotNull(commands);
	}

	@Test
	public void testTurtleProgram() {
		commands = readFile("src/testProgram1.txt");
		interpretCommand(commands);
		assertEquals(22.99, turtle.getCurrentLocation().getX(), delta);
		assertEquals(27.5, turtle.getCurrentLocation().getY(), delta);
	}

	@Test
	public void testTurtleProgramContainingRepeat() {
		commands = readFile("src/testProgram2.txt");
		interpretCommand(commands);
		assertEquals(0.0, turtle.getCurrentLocation().getX(), delta);
		assertEquals(0.0, turtle.getCurrentLocation().getY(), delta);
	}

	@Test
	public void testDegrees() {
		commands = readFile("src/testProgram1.txt");
		interpretCommand(commands);
		assertEquals(30, turtle.getDirection(), delta);
	}
}
