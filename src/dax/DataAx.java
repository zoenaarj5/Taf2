package dax;

import java.util.List;
import java.util.Map;

import search.SearchField;
import search.SearchMode;
import  search.SearchMatchingMode;
public interface DataAx<T> {
	List<T> fetchAll();
	List<T> fetch(Map<SearchField,Object> criteria,SearchMode mode,SearchMatchingMode matchingMode);
	boolean find(String term);
	default boolean match(T t,Map<SearchField,Object> criteria,boolean caseSensitive) {
		return match(t,criteria,SearchMode.OR,SearchMatchingMode.LIKE,caseSensitive);
	}
	default boolean match(T t,Map<SearchField,Object> criteria,SearchMode mode,boolean caseSensitive) {
		return match(t,criteria,mode,SearchMatchingMode.LIKE,caseSensitive);
	}
	default boolean match(T t,Map<SearchField,Object> criteria,SearchMatchingMode matchingMode,boolean caseSensitive) {
		return match(t,criteria,SearchMode.OR,matchingMode,caseSensitive);
	}
	default boolean match(T t,Map<SearchField,Object> criteria,SearchMatchingMode matchingMode) {
		return match(t,criteria,SearchMode.OR,matchingMode,false);
	}
	default boolean match(T t,Map<SearchField,Object> criteria) {
		return match(t,criteria,SearchMode.OR,SearchMatchingMode.LIKE,false);
	}
	default boolean match(T t,Map<SearchField,Object> criteria,SearchMode mode) {
		return match(t,criteria,mode,SearchMatchingMode.LIKE,false);
	}
	default boolean match(T t,Map<SearchField,Object> criteria,SearchMode mode,SearchMatchingMode matchingMode) {
		return match(t,criteria,mode,matchingMode,false);
	}
	boolean match(T t,Map<SearchField,Object> criteria,SearchMode mode,SearchMatchingMode matchingMode,boolean caseSensitive);
}
