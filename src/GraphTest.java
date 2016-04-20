import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {

	@Test
	public void test() {
		Parser p = new Parser();
		Graph g = new Graph();
		g.setEq(p.translate("(x+2)/y+var"));
		
		assertEquals(g.getVariables().toString(), "[var, x, y]");
	}

}
