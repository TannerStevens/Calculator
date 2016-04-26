import static org.junit.Assert.*;

import org.junit.Test;

public class EquationSolverTest {
	Parser p = new Parser();
	EquationSolver es = new EquationSolver();
	double result;
	
	@Test
	public void testDecimals() {
		result = es.Solve("1 2 /");
		assertEquals(result, 0.5, 0);
	}
	
	@Test
	public void testExponentiation(){
		result = es.Solve("2 2 ^");
		assertEquals(result, 4, 0);
	}
	
	@Test
	public void testMultiplication(){
		result = es.Solve("2 2 *");
		assertEquals(result, 4, 0);
	}
	
	@Test
	public void testDivision(){
		result = es.Solve("4 2 /");
		assertEquals(result, 2, 0);
	}
	
	@Test 
	public void testAddition(){
		result = es.Solve("2 2 +");
		assertEquals(result, 4, 0);
	}
	
	@Test
	public void testSubtraction(){
		result = es.Solve("2 2 -");
		assertEquals(result, 0, 0);
	}
	
	@Test
	public void testSin(){
		result = es.Solve("25 sin");
		assertEquals(result, Math.sin(25), 1);
	}
	
	@Test
	public void testCos(){
		result = es.Solve("25 cos");
		assertEquals(result, Math.cos(25), 1);
	}
	
	@Test
	public void testTan(){
		result = es.Solve("25 tan");
		assertEquals(result, Math.tan(25), 1);
	}
}
