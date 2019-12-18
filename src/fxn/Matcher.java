package fxn;

import search.SearchMatchingMode;

public interface Matcher<T> {
	boolean match(T t1,T t2,SearchMatchingMode matchingMode,boolean caseSensitive);
}
