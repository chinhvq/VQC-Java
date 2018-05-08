package test.jnuittest;

public class Utilities {

	// return a char array containing every nth char,
	// when sourceArray.length < n ==> return sourceArray
	public char[] everyNthChar(char[] sourceArray, int n) {
		if (sourceArray == null || sourceArray.length < n) {
			return sourceArray;
		}

		int returnLength = sourceArray.length / n;
		char[] result = new char[returnLength];
		int index = 0;
		for (int i = n - 1; i < sourceArray.length; i += n) {
			result[index++] = sourceArray[i];
		}
		return result;
	}

	// remove pairs of same character that are next
	public String removePairs(String source) {
		if (source == null || source.length() < 2) {
			return source;
		}

		StringBuilder sb = new StringBuilder();
		char[] string = source.toCharArray();
		for (int i = 0; i < string.length -1; i++) {
			if (string[i] != string[i + 1]) {
				sb.append(string[i]);
			}
		}
		//add the last character which is always safe
		sb.append(string[string.length - 1]);
		return sb.toString();
	}

	// perform a conversion based on some internal business rull
	public int converter(int a, int b) {
		if(b != 0) {
			return (a / b) + (a * 30) - 2;
		} else {
			throw new ArithmeticException();
		}
	}
	
	public String nullIfOddLength(String source) {
		if(source.length() % 2 ==0) {
			return source;
		}
		return null;
	}
}
