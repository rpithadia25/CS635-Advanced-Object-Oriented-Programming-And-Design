package assignment3;

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

public class TurtleCommandTest {

	protected ArrayList<Command> commands = new ArrayList<Command>();
	protected HashMap<String, String> variables = new HashMap<String, String>();
	protected Turtle turtle;
	protected int repetations;
	protected Point2D expectedLocation = null;
	protected double delta = 1e-8;
	
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
					 commands.add(new PenUp());
					break;
				case Constants.PENDOWN:
					 commands.add(new PenDown());
					break;
				case Constants.MOVE:
					 commands.add(new Move(Integer.parseInt(value)));
					break;
				case Constants.TURN:
					 commands.add(new Turn(Integer.parseInt(value)));
					break;
				case Constants.REPEAT:
					if (value.startsWith("$")) {
						commands.add(new Repeat(Integer
								.parseInt(variables.get(value))));
					} else {
						commands.add(new Repeat(Integer
								.parseInt(value)));
					}
					break;
				case Constants.END:
					 commands.add(new End());
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
				commands = new ArrayList<Command>();
				while (currentCommand.getClass() != End.class) {
					currentCommand = listIterator.next();
					if (currentCommand.getClass() != End.class) {
						commands.add(currentCommand);
					}
				}
			} else {
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
		commands = readFile();
		assertNotNull(commands);
	}
	
	@Test
	public void testInterpretCommand() {
		commands = readFile();
		interpretCommand(commands);		
		assertEquals(22.99, turtle.getCurrentLocation().getX(), delta);
		assertEquals(27.5, turtle.getCurrentLocation().getY(), delta);
	}

}
