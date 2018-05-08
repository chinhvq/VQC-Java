package test.jnuittest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankAccountTest {
	BankAccount account;
	private static int count;

	@BeforeClass
	public static void beforeClass() {
		System.out.println("Start Testing. Count = " + count++);
	}

	@Before
	public void setup() {
		account = new BankAccount("VUONG", "CHINH", 1000, BankAccount.CHECKING);
	}

	@Test
	public void testGetBalance_deposit() {
		account.depoist(200, true);
		assertEquals(1200, account.getBalance(), 0);
	}

	@Test
	public void testGetBalance_withdraw() {
		account.withdraw(200, true);
		assertEquals(800, account.getBalance(), 0);
	}

	@Test
	public void testDepoist() {
		double balance = account.depoist(200, true);
		assertEquals(1200, balance, 0);
	}

	@Test
	public void testWithdraw_branch() {
		double balance = account.withdraw(600, true);
		assertEquals(400, balance,0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithdraw_notBranch()  {
		double balance = account.withdraw(600, false);
		fail("should have thrown an IllegalArgumentException");
	}

	@Test
	public void isChecking_true() {
		assertTrue("The account is not a checking account", account.isChecking());
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("After testing. Count = " + count++);
	}
	
	@After
	public void tearDown() {
		System.out.println("Count = " + count++);
	}
}
