package test.scopevariable;

public class Password {
	private static final int KEY = 123456;
	private final int nEncryptedPassword;

	protected Password(int nPassword) {
		this.nEncryptedPassword = pEncryptedDecrypt(nPassword);
	}

	private int pEncryptedDecrypt(int nPassword) {
		return nPassword ^ KEY;
	}

	public final void pStoreKey() {
		System.out.println("Saving password as\t" + this.nEncryptedPassword);
	}

	public boolean pLetMeIn(int nPassword) {
		if (pEncryptedDecrypt(nPassword) == this.nEncryptedPassword) {
			System.out.println("WELCOME HOME");
			return true;
		} else {
			System.out.println("THE PASSWORD IS NOT CORRECT");
			return false;
		}
	}
}
