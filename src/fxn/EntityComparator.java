package fxn;

import search.SearchField;
import search.SearchMatchingMode;
import search.SearchMode;

public interface EntityComparator<T> {
	boolean compare(T t1,T t2,SearchField[] fields,SearchMode mode,SearchMatchingMode matchingMode);
}
