package test.regularexpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RExpression {

	public static void main(String[] args) {
		String string = "I am a String .. Yes I am";
		System.out.print("Original String \n\t" + string);
		System.out.print("\nReplace I with You \n\t" + string.replaceAll("I", "You"));
		System.out.print("\nCharacter Class - Wildcard \n\t" + string.replaceAll(".", "Y"));
		System.out.print("\nCarrot Boundary Matcher - Wildcard \n\t" + string.replaceAll("^I am", "Soldier"));
		System.out.println("\nWheter string is start with String or not ? " + string.matches("^String"));
		System.out
				.println("Wheter string is start with 'I am' or not ? " + string.matches("^I am a String . Yes I am"));
		System.out.println("Wheter string is start with 'I am' or not ? " + string.matches("^I am"));
		System.out.print("Boundary Matcher Dollar Sign \n\t" + string.replaceAll("I am$", "THE-END"));
		System.out.print("\nSpecific Match (per character match) \n\t" + string.replaceAll("[I]", "Soldier"));
		System.out.print(
				"\nSpecific Match (with match following character) \n\t" + string.replaceAll("[I][am]", "Soldier"));
		System.out.print(
				"\nSpecific Match (with match following character) \n\t" + string.replaceAll("[I][ ]", "Soldier"));
		System.out.println("\n\t" + string.replaceAll("[aA]m", "'m"));
		System.out.print("Carrot as class -> negative \n\t" + string.replaceAll("[^ ]", "X"));
		System.out.print("\nRepace all non-space \n\t" + string.replaceAll("[^\\s]", "X"));
		System.out.print("\nRepace all non-space2 \n\t" + string.replaceAll("\\S", "X"));
		System.out.println("\nOpps\n\t" + string.replaceAll("[a-zA-Z0-9]", "X"));
		System.out.print("Ignore Case sensitive \n\t" + string.replaceAll("(?i)[a-z0-9]", "X"));
		System.out.print("\nIgnore Case sensitive support Unicode\n\t" + string.replaceAll("(?iu)[a-z0-9]", "X"));
		System.out.print("\nRepace all number \n\t" + string.replaceAll("[0-9]", "X"));
		System.out.print("\nRepace all number \n\t" + string.replaceAll("\\d", "X"));
		System.out.print("\nRepace all non-digit \n\t" + string.replaceAll("\\D", "X"));
		System.out.print("\nRepace all space \n\t" + string.replaceAll("\\s", "-"));
		System.out.print("\nRepace all a-z A-Z 0-9 _underscore \n\t" + string.replaceAll("\\w", "X"));
		System.out.print("\nRepace all non of (a-z A-Z 0-9 _underscore) \n\t" + string.replaceAll("\\W", "X"));
		System.out.print("\nHow interest \\b is \n\t" + string.replaceAll("\\b", "-"));
		System.out.print("\nDOT. is \n\t" + string.replaceAll("\\.", "X"));
		System.out.print("\nQuantifier Number of Repeation is \n\t" + string.replaceAll("\\.{1}", "X"));
		System.out.print("\nQuantifier Range of Repeation is \n\t" + string.replaceAll("\\.{1,5}", "X"));
		System.out.print("\nQuantifier Donot care Number of Repeation is \n\t" + string.replaceAll("\\.+", "X"));
		System.out.println("\nQuantifier * to match any character after a -> it can be m or any other character \n\t"
				+ string.replaceAll("am*", "X"));
		String string2 = "abcDeeeG12Ghhiiiijkl99z";
		System.out.print("combined pattern \n\t" + string2.replaceAll("h+i*j", "-"));

		StringBuffer htmlText = new StringBuffer("<h1>Heading Text</h1>");
		htmlText.append("<h2>Sub Heading</h2>");
		htmlText.append("<p>This is a parapgrahp about something</p>");
		htmlText.append("<p>This is a another parapgrahp about something</p>");
		htmlText.append("<h2>Summary</h2>");
		htmlText.append("<p>Summary here</p");

		// String h2Pattern = ".*<h2>.*";
		String h2Pattern = "<h2>";
		Pattern pattern = Pattern.compile(h2Pattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher matcher = pattern.matcher(htmlText);
		System.out.println("\n" + matcher.matches());
		matcher.reset();

		int count = 0;
		while (matcher.find()) {
			count++;
			System.out.println("Occurrent " + count + ": " + matcher.start() + " to " + matcher.end());
		}

		// String h2GroupPattern = "(<h2>.*</h2>)";
		// greedy quantifier *
		// String h2GroupPattern = "(<h2>.*</h2>)";
		// lazy quantifier ? two way <h2>.*?</h2> && <h2>.+?</h2>
		// String h2GroupPattern = "(<h2>.*?</h2>)";
		String h2GroupPattern = "(<h2>.+?</h2>)";
		Pattern groupPattern = Pattern.compile(h2GroupPattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher groupMatcher = groupPattern.matcher(htmlText);
		System.out.println("\n" + groupMatcher.matches());
		groupMatcher.reset();

		while (groupMatcher.find()) {
			System.out.println("Occurrence : " + groupMatcher.group(1));
		}

		String h2GroupPatternContentOnly = "(<h2>)(.+?)(</h2>)";
		Pattern groupPatternContentOnly = Pattern.compile(h2GroupPatternContentOnly,
				Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher groupMatcherContentOnly = groupPatternContentOnly.matcher(htmlText);
		System.out.println("\n" + groupMatcherContentOnly.matches());
		groupMatcherContentOnly.reset();

		while (groupMatcherContentOnly.find()) {
			System.out.println("Occurrence : " + groupMatcherContentOnly.group(2));
		}

		System.out.println("OR operator");
		System.out.println("harry".replaceAll("[h|H]arry", "Larry"));
		System.out.println("Harry".replaceAll("[h|H]arry", "Larry"));

		System.out.println("NOT operator");
		String tvTest = "tstvtkt";
		// String tNotVExp = "t[^v]";
		String tNotVExp = "t(?!v)";
		Pattern tNotVPatter = Pattern.compile(tNotVExp, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		Matcher tNotVMatcher = tNotVPatter.matcher(tvTest);
		count = 0;
		while (tNotVMatcher.find()) {
			count++;
			System.out.println("Occurred " + count + " : " + tNotVMatcher.start() + " to " + tNotVMatcher.end());
		}

		// find US telephone number
		String phone1 = "1234567890";
		String phone2 = "(123) 456-7890"; // match
		String phone3 = "123 456-7890";
		String phone4 = "(123)456-7890";
		String regExp = "^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$";
		System.out.println("phone 1 : " + phone1.matches(regExp));
		System.out.println("phone 2 : " + phone2.matches(regExp));
		System.out.println("phone 3 : " + phone3.matches(regExp));
		System.out.println("phone 4 : " + phone4.matches(regExp));

		// check visa card
		String visa1 = "4444444444444"; // match
		String visa2 = "5444444444444";
		String visa3 = "4444444444444444";// match
		String visa4 = "4444";
		regExp = "^4[0-9]{12}([0-9]{3})?$";
		System.out.println("Visa 1 : " + visa1.matches(regExp));
		System.out.println("Visa 2 : " + visa2.matches(regExp));
		System.out.println("Visa 3 : " + visa3.matches(regExp));
		System.out.println("Visa 4 : " + visa4.matches(regExp));

		// challenge
		String challenge1 = "I want a bike.";
		String challenge2 = "I want a ball.";
		regExp = "I want a \\w+.";
		System.out.println("===Using String.matches()===");
		System.out.println("challenge 1 : " + challenge1.matches(regExp));
		System.out.println("challenge 2 : " + challenge1.matches(regExp));

		System.out.println("===Using Matcher.matches()===");
		regExp = "I want a (bike|ball).";
		Pattern patternC = Pattern.compile(regExp);
		Matcher matcherC = patternC.matcher(challenge1);
		System.out.println("challenge 1 : " + matcherC.matches());
		matcherC = patternC.matcher(challenge2);
		System.out.println("challenge 2 : " + matcherC.matches());

		System.out.println("===============================");
		String challenge4 = "Replaces all blank with underscore.";
		System.out.println(challenge4.replaceAll("[ ]", "_"));
		System.out.println(challenge4.replaceAll("\\s", "_"));

		System.out.println("===============================");
		String challenge5 = "aaabccccccccdddefffg";
		System.out.println("challenge5 : " + challenge5.matches("\\w+"));
		System.out.println("challenge5 : " + challenge5.matches("[a-g]+"));
		System.out.println("challenge5 : " + challenge5.matches("[abcdefg]+"));
		System.out.println("challenge5 : " + challenge5.matches("a{3}bc{8}d{3}ef{3}g"));
		System.out.println("challenge5 : most strickest : " + challenge5.matches("^a{3}bc{8}d{3}ef{3}g$"));
		System.out.println("challenge5 : most strickest - VERIFIED -> "
				+ challenge5.replaceAll("^a{3}bc{8}d{3}ef{3}g$", "REPLACED"));
		
		System.out.println("===============================");
		String challenge7 = "abcd.1234";
		System.out.println("challenge7 : " + challenge7.matches("\\w+\\.[0-9]+"));
		System.out.println("challenge7 : " + challenge7.matches("^([a-z]|[A-Z])+\\.\\d+$"));
		System.out.println("challenge7 : most strickest : " + challenge7.matches("^([a-z]|[A-Z])+\\.[0-9]+$"));
		
		System.out.println("===============================");
		System.out.println("Challenge 8 - extract all number");
		String challenge8 = "abcd.135uvqz.7xcbd.999";
		regExp = "[a-zA-Z]+\\.(\\d+)";
		Pattern pattern8 = Pattern.compile(regExp);
		Matcher matcher8 = pattern8.matcher(challenge8);
		while(matcher8.find()) {
			System.out.println("Occurred : " + matcher8.group(1));			
		}
		
		System.out.println("===============================");
		System.out.println("Challenge 9 - extract all number");
		String challenge9 = "abcd.135\tuvqz.7\txcbd.999\n";
		regExp = "[a-zA-Z]+\\.(\\d+)\\s";
		Pattern pattern9 = Pattern.compile(regExp);
		Matcher matcher9 = pattern9.matcher(challenge9);
		count = 0;
		while(matcher9.find()) {
			count++;
			System.out.println("Occurred : " + matcher9.group(1));	
			System.out.println("Occurred " + count + " : " + matcher9.start() + " to " + (matcher9.end() - 1));
		}
		
		System.out.println("===============================");
		System.out.println("Challenge 10 - extract all number with their index");
		String challenge10 = "abcd.135\tuvqz.7\txcbd.999\n";
		regExp = "[a-zA-Z]+\\.(\\d+)\\s";
		Pattern pattern10 = Pattern.compile(regExp);
		Matcher matcher10 = pattern10.matcher(challenge10);
		count = 0;
		while(matcher10.find()) {
			count++;
			System.out.println(" Occurred " + count + "- Found : " + matcher10.group(1) +  "\t at : \t" + matcher10.start(1) + "\t to \t" + (matcher10.end(1) - 1));
		}
		
		System.out.println("===============================");
		System.out.println("Challenge 11 - extract all content inside the {} with their index");
		String challenge11 = "{0, 2}, {0, 5}, {1, 3}, {a, b}, {2, 4}, {x, y}";
		regExp = "\\{(.+?)\\}";
		Pattern pattern11 = Pattern.compile(regExp);
		Matcher matcher11 = pattern11.matcher(challenge11);
		count = 0;
		while(matcher11.find()) {
			count++;
			System.out.println(" Occurred " + count + "- Found : " + matcher11.group(1) +  "\t at : \t" + matcher11.start(1) + "\t to \t" + (matcher11.end(1) - 1));
		}
		
		System.out.println("===============================");
		System.out.println("Challenge 11a - extract all DIGIT content only inside the {} with their index");
		String challenge11a = "{0, 2}, {0, 5}, {1, 3}, {a, b}, {2, 4}, {x, y}";
		regExp = "\\{(\\d+, \\d+)\\}";
		Pattern pattern11a = Pattern.compile(regExp);
		Matcher matcher11a = pattern11a.matcher(challenge11a);
		count = 0;
		while(matcher11a.find()) {
			count++;
			System.out.println(" Occurred " + count + "- Found : " + matcher11a.group(1) +  "\t at : \t" + matcher11a.start(1) + "\t to \t" + (matcher11a.end(1) - 1));
		}
		
		System.out.println("===============================");
		System.out.println("US zipcode");
		String usZipCode1 = "11111";
		System.out.println("usZipCode1 : " + usZipCode1.matches("^\\d{5}$"));
		System.out.println("usZipCode1 : " + usZipCode1.matches("^\\d{5}(-\\\\d{4})?$"));
		String usZipCode2 = "11111-1111";
		System.out.println("usZipCode2 : " + usZipCode2.matches("^\\d{5}-\\d{4}$"));
	}

}

// matcher work with char sequence interface that means that we can use matches
// with strings stringbuffer stringbuilder and other classes that implement that
// interface generally we use a matcher when we want to find multiple
// occurrences of a pattern or when we want to use the same pattern multiple
// sequences

// just like the string . matches method wants to do so what we need to do is
// write a regular expression is going to match the entire text so we can do
// this quite easy with a regular expression - . character class with a star
// quantifier ==> . will match every character and star means zero or more so
// effectively what we're saying here is that can they can be anything before
// anything after the h2
