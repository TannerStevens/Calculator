import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

	@Test
	public void test() {
		Parser p = new Parser();
		assertEquals("2 2 +", p.translate("2+2"));
		assertEquals("10 2 -", p.translate("10-2"));
		assertEquals("100 25 2 * -", p.translate("100-(25*2)"));
		assertEquals("4 2 1 + * 6 10 / -", p.translate("4*(2+1)-6/10"));
		assertEquals("2 5 2 ^ * 2 - 4 +", p.translate("2*5^2-2+4"));
	}

}