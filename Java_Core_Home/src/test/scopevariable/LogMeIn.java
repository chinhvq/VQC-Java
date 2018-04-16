package test.scopevariable;

public class LogMeIn {

	public static void main(String[] args) {
		int nPassword = 19830807;
		Password password = new Password(nPassword);
		password.pStoreKey();
		password.pLetMeIn(nPassword);
		password.pLetMeIn(213142423);
		password.pLetMeIn(19830807);

	}

}
