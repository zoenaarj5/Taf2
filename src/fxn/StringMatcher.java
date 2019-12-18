package fxn;

import search.SearchMatchingMode;

public class StringMatcher implements Matcher<String>{
	private static StringMatcher uniqueInstance=new StringMatcher();
	public static StringMatcher getUniqueInstance() {
		return uniqueInstance;
	}
	private StringMatcher() {
		super();
	}
	@Override
	public boolean match(String s1,String s2,SearchMatchingMode smm,boolean caseSensitive) {
		String s12Comp=caseSensitive?s1:s1.toUpperCase(),
				s22Comp=caseSensitive?s2:s2.toUpperCase();
		switch(smm) {
			case LIKE:
				System.out.println(s1+" matches "+s2+"? Comparing in LIKE mode");
				return s12Comp.contains(s22Comp)||s22Comp.contains(s12Comp);
			default:
				System.out.println(s1+" matches "+s2+"? Comparing in SAME mode");
				return s12Comp.equals(s22Comp);
		}
	}
}
