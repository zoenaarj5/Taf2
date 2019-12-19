package fxn;

import search.SearchField;
import search.SearchMatchingMode;
import search.SearchMode;
/**
 * @interface Used to compare objects related to database entities
 * */
public interface EntityComparator<T> {
	/**
	 * @param: t1 and t2: T-class objects to compare
	 * @param: fields: array of T-class objects (mentioning which attributes are being used to compare the two objects
	 * @param: mode: SearchMode:
	 * 				-if AND, all the mentioned fields must match in order to validate the comparison. If no field is mentioned, the comparison returns true
	 * 				- if OR, one matching field among the mentioned ones is enough to validate the comparison. If no field is mentioned, the comparison returns false. 
	 * @param: matchingMode:
	 *			- if SAME, and if the compared fields are strings, the two must be equal.
	 *			- if LIKE, and if the compared fields are strings, one must contain or be contained in the other (Example: Robin and Robinson)
	 * @returns: true if two T-class objects' mentioned attributes(listed in "fields" parameter match.
	 * Comparing two "Person" objects.
	 * Tip: If no search field is given(empty array), the two objects are matched if the search mode is AND, and not matched otherwise(OR).
	 * */
	boolean compare(T t1,T t2,SearchField<T>[] fields,SearchMode mode,SearchMatchingMode matchingMode,boolean caseSensitive);
}
