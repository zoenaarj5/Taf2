package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fxn.Matcher;
import fxn.StringMatcher;
import search.SearchMatchingMode;

class MatcherTester {

	@Test
	public final void testMatch() {
		Matcher<String> matcher=StringMatcher.getUniqueInstance();
		assertTrue("Identical strings not matched",matcher.match("hello", "hello", SearchMatchingMode.SAME, true));
		assertFalse("Upper-cased string matched despite case-sensitivity",matcher.match("hello", "HELLO", SearchMatchingMode.SAME, true));
		assertTrue("Upper cased string not matched despite case-insensitivity",matcher.match("hello", "HELLO", SearchMatchingMode.SAME, false));
		assertTrue("String containing first string not matched",matcher.match("hello", "helloworld", SearchMatchingMode.LIKE, true));
		assertTrue("String contained in first string not matched",matcher.match("hello", "hell", SearchMatchingMode.LIKE, true));
		assertTrue("String containing first string not matched",matcher.match("hello", "helloworld", SearchMatchingMode.LIKE, true));
		assertTrue("String contained in first string not matched",matcher.match("hello", "hell", SearchMatchingMode.LIKE, true));
		assertFalse("String containing first string matched despite case sensitivity",matcher.match("helLO", "helloworld", SearchMatchingMode.LIKE, true));
		assertFalse("String contained in first string matched despite case sensitivity",matcher.match("hello", "HELl", SearchMatchingMode.LIKE, true));
		assertTrue("String containing first string (case insensitive) not matched",matcher.match("helLO", "helloworld", SearchMatchingMode.LIKE, false));
		assertTrue("String contained in first string (case insensitive) not matched",matcher.match("hello", "HELl", SearchMatchingMode.LIKE, false));
	}

}
