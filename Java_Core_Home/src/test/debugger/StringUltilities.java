package test.debugger;

public class StringUltilities {
	private StringBuilder sBuilder = new StringBuilder();
	private int charsAdded = 0;
	
	public void addChar(StringBuilder sBuilder, char c) {
		sBuilder.append(c);
		charsAdded++;
	}
	
	public String addAndPrefix(String str) {
		return "Prefix_" + str.toUpperCase();
	}
	
	public String addSuffix(String str) {
		return str + "_Suffix";
	}
}
