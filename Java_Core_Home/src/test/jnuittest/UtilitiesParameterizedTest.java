package test.jnuittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UtilitiesParameterizedTest {
	private Utilities util = new Utilities();
	private String source;
	private String expected;

	public UtilitiesParameterizedTest(String source, String expected) {
		this.source = source;
		this.expected = expected;
	}

	@Before
	public void setup() {
		util = new Utilities();
	}

	@Parameterized.Parameters
	public static Collection<Object[]> testConditions() {
		return Arrays.asList(new Object[][] { { "ABCDEFF", "ABCDEF" }, { "A", "A" }, { "AB88EFFG", "AB8EFG" },
				{ "112233445566", "123456" }, { "ZYZQQB", "ZYZQB" } });
	}

	@Test
	public void testRemovePairs() {
		assertEquals(expected, util.removePairs(source));
	}
}
