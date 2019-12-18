package dax;

import java.util.List;
import java.util.Map;

import search.SearchField;
import search.SearchMode;
import  search.SearchMatchingMode;
public interface DataAx<T> {
	List<T> fetchAll();
	List<T> search(Map<SearchField,Object> criteria,SearchMode mode,SearchMatchingMode matchingMode);
	boolean match(T t,Map<SearchField,Object> criteria,SearchMode mode,SearchMatchingMode matchingMode);
}
