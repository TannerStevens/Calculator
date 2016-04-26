import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {
	Parser p = new Parser();
	
	@Test
	public void testSingleDigits() {
		assertEquals("2 2 +", p.translate("2+2"));
	}
	
	@Test
	public void testMultidigit(){
		assertEquals("24 200 -", p.translate("24-200"));
	}
	
	@Test
	public void testCharacterVariables(){
		assertEquals("x 2 +", p.translate("x+2"));
		assertEquals("2 x +", p.translate("2+x"));
	}
	
	@Test
	public void testStringVariables(){
		assertEquals("var 2 +", p.translate("var+2"));
		assertEquals("xy 2 +", p.translate("xy+2"));
		
		assertEquals("2 var +", p.translate("2+var"));
		assertEquals("2 xy +", p.translate("2+xy"));
	}
	
	@Test
	public void testParenthesis(){
		assertEquals("100 25 2 * -", p.translate("100-(25*2)"));
		assertEquals("4 2 1 + * 6 10 / -", p.translate("4*(2+1)-6/10"));
	}
	
	@Test
	public void testMulticharacterOperators(){
		assertEquals("25 sin", p.translate("sin(25)"));
		assertEquals("25 sin 25 +", p.translate("sin(25)+25"));
		assertEquals("25 cos", p.translate("cos(25)"));
		assertEquals("25 tan", p.translate("tan(25)"));
		assertEquals("25 log", p.translate("log(25)"));
		assertEquals("25 sin 25 + 10 /", p.translate("(sin(25)+25)/10"));
	}

}