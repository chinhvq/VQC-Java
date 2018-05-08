package test.debugger;

public class DebuggerDemo {
	public static void main(String[] args) {
		StringUltilities utils = new StringUltilities();
		StringBuilder sb = new StringBuilder();
		while (sb.length() < 10) {
			utils.addChar(sb, 'a');
		}
		System.out.println(sb);
		String str = "abcdefg";
		String result = utils.addAndPrefix(utils.addSuffix(str));
		System.out.println(result);
	}
}
