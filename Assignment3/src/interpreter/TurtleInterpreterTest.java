package interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

public class TurtleInterpreterTest {

	protected ArrayList<Expression> expressions = new ArrayList<Expression>();
	protected HashMap<String, String> variables = new HashMap<String, String>();
	protected Turtle turtle;
	protected int repetations;
	protected Point2D expectedLocation = null;
	protected double delta = 1e-8;
	
	private ArrayList<Expression> readFile(String filePath) {
		ArrayList<Expression> commands = new ArrayList<Expression>();
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
					 commands.add(new ExpressionPenUp());
					break;
				case Constants.PENDOWN:
					 commands.add(new ExpressionPenDown());
					break;
				case Constants.MOVE:
					 commands.add(new ExpressionMove(Integer.parseInt(value)));
					break;
				case Constants.TURN:
					 commands.add(new ExpressionTurn(Integer.parseInt(value)));
					break;
				case Constants.REPEAT:
					if (value.startsWith("$")) {
						commands.add(new ExpressionRepeat(Integer
								.parseInt(variables.get(value))));
					} else {
						commands.add(new ExpressionRepeat(Integer
								.parseInt(value)));
					}
					break;
				case Constants.END:
					 commands.add(new ExpressionEnd());
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
	
	private void interpretCommand(ArrayList<Expression> list) {

		Iterator<Expression> listIterator = list.iterator();

		while (listIterator.hasNext()) {
			Expression currentCommand = listIterator.next();
			if (currentCommand.getClass() == ExpressionRepeat.class) {
				repetations = ((ExpressionRepeat) currentCommand).times;
				expressions = new ArrayList<Expression>();
				while (currentCommand.getClass() != ExpressionEnd.class) {
					currentCommand = listIterator.next();
					if (currentCommand.getClass() != ExpressionEnd.class) {
						expressions.add(currentCommand);
					}
				}
			} else { //Any Expression other than Repeat
				currentCommand.interpret(turtle);
			}
		}
		while (repetations > 0) {
			repetations--;
			interpretCommand(expressions);
		}
	}
	
	@Before
	public void setUp() throws Exception {
		turtle = new Turtle();
		interpretCommand(expressions);
	}

	@After
	public void tearDown() throws Exception {
		turtle = null;
		expressions = null;
		variables = null;
	}
	
	@Test
	public void testStatements() {
		expressions = readFile("src/testProgram1.txt");
		assertNotNull(expressions);
	}
	
	@Test
	public void testTurtleProgram() {
		expressions = readFile("src/testProgram1.txt");
		interpretCommand(expressions);		
		assertEquals(22.99, turtle.getCurrentLocation().getX(), delta);
		assertEquals(27.5, turtle.getCurrentLocation().getY(), delta);
	}
	
	@Test
	public void testTurtleProgramContainingRepeat() {
		expressions = readFile("src/testProgram2.txt");
		interpretCommand(expressions);		
		assertEquals(0.0, turtle.getCurrentLocation().getX(), delta);
		assertEquals(0.0, turtle.getCurrentLocation().getY(), delta);
	}
	
	@Test
	public void testDegrees() {
		expressions = readFile("src/testProgram1.txt");
		interpretCommand(expressions);
		assertEquals(30, turtle.getDirection(), delta);
	}

}
