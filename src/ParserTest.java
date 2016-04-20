import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

	@Test
	public void test() {
		Parser p = new Parser();
		assertEquals("2 2 +", p.translate("2+2"));
		assertEquals("100 25 2 * -", p.translate("100-(25*2)"));
	}

}
