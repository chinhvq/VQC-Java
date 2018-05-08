package test.jnuittest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class UtilitiesTest {
	private Utilities util;

	@Before
	public void setup() {
		util = new Utilities();
	}

	@Test
	public void testEveryNthChar() {
		char[] expected = { 'e', 'l' };
		char[] source = { 'h', 'e', 'l', 'l', 'o' };
		assertArrayEquals(expected, util.everyNthChar(source, 2));
		assertArrayEquals(source, util.everyNthChar(source, 6));
	}

	@Test
	public void testRemovePairs() {
		assertEquals("ABCDEF", util.removePairs("AABCDDEFF"));
		assertEquals("ABCABDEF", util.removePairs("ABCCABDEEF"));
		// assertNull("Did not get null return when argument passed was null",
		// util.removePairs(null));
		assertNull(null, util.removePairs(null));
		assertEquals("A", util.removePairs("A"));
		assertEquals("", util.removePairs(""));
	}

	@Test(expected = ArithmeticException.class)
	public void testConverter_b0() {
		assertEquals(290, util.converter(10, 0));
		fail("should have thrown an ArithmeticException");
	}

	@Test
	public void testConverter() {
		assertEquals(300, util.converter(10, 5));
	}

	@Test
	public void testNullIfOddLength() {
		assertEquals(null, util.nullIfOddLength("hello"));
		assertEquals("hello1", util.nullIfOddLength("hello1"));
		assertNotNull(util.nullIfOddLength("hello1"));
		assertNull(util.nullIfOddLength("Hello"));
	}

}
