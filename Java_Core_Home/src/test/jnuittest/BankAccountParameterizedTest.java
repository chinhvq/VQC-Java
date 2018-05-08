package test.jnuittest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BankAccountParameterizedTest {
	private BankAccount account;
	private double amount;
	private boolean branch;
	private double expected;

	public BankAccountParameterizedTest(double amount, boolean branch, double expected) {
		this.amount = amount;
		this.branch = branch;
		this.expected = expected;
	}

	@Before
	public void setup() {
		account = new BankAccount("VUONG", "CHINH", 1000, BankAccount.CHECKING);
	}

	@Parameterized.Parameters
	public static Collection<Object[]> testConditions() {
		return Arrays.asList(new Object[][] { { 100, true, 1100 }, { 200, true, 1200 }, { 135.23, true, 1135.23 },
				{ 489.33, true, 1489.33 }, { 1000, true, 2000 }, { 419.31, true, 1419.31 } });
	}

	@Test
	public void testGetBalance_deposit() {
		account.depoist(amount, branch);
		assertEquals(expected, account.getBalance(), .001);
	}

}
