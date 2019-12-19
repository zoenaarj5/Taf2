package search;

import java.util.Map;

public interface SearchField<T> {
	boolean matchFields(T t1,T t2,SearchMatchingMode matchingMode,boolean caseSensitive);
	boolean matchElement(T t,Object value,SearchMatchingMode matchingMode,boolean caseSensitive);
}
