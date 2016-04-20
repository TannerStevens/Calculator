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

}
