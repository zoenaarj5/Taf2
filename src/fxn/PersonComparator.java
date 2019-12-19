package fxn;

import biz.Person;
import search.SearchField;
import search.SearchMatchingMode;
import search.SearchMode;

public class PersonComparator implements EntityComparator<Person>{
	private static PersonComparator uniqueInstance=new PersonComparator();
	private PersonComparator() {
		super();
	}
	@Override
	public boolean compare(Person p1, Person p2, SearchField<Person>[] fields, SearchMode mode,
			SearchMatchingMode matchingMode,boolean caseSensitive) {
		for(SearchField<Person> sf:fields) {
			switch(mode) {
				case AND:
					if(!sf.matchFields(p1, p2, matchingMode, caseSensitive)) {
						return false;
					}
					break;
				case OR:
					if(sf.matchFields(p1, p2, matchingMode, caseSensitive)) {
						return true;
					}
				break;
			default:
				break;
			}
		}
		return mode==SearchMode.AND;
	}
	public static PersonComparator getUniqueInstance() {
		return uniqueInstance;
	}

}
