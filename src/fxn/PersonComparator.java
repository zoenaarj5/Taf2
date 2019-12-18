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
	public boolean compare(Person p1, Person p2, SearchField[] fields, SearchMode mode,
			SearchMatchingMode matchingMode) {
		Matcher<String> matcher=StringMatcher.getUniqueInstance();
		for(SearchField sf:fields) {
			switch(mode) {
			case AND:
				switch(sf) {
				case PERSON_EMAIL:
					if(!matcher.match(p1.getEmail(), p2.getEmail(), matchingMode,false)) {
						return false;
					}
					break;
				case PERSON_FIRST_NAME:
					if(!matcher.match(p1.getFirstName(), p2.getFirstName(), matchingMode,false)) {
						return false;
					}
					break;
				case PERSON_ID:
					if(p1.getId()!=p2.getId()) {
						return false;
					}
					break;
				case PERSON_LAST_NAME:
					if(!matcher.match(p1.getLastName(), p2.getLastName(), matchingMode,false)) {
						return false;
					}
					break;
				case PERSON_PASSWORD:
					if(!p1.getPassword().equals(p2.getPassword())) {
						return false;
					}
					break;
				case PERSON_STATUS:
					if(!matcher.match(p1.getStatus().toString(), p2.getStatus().toString(), matchingMode,false)) {
						return false;
					}
					break;
				default:
					continue;
				
				}
				break;
			case OR:
				switch(sf) {
				case PERSON_EMAIL:
					if(matcher.match(p1.getEmail(), p2.getEmail(), matchingMode,false)) {
						return true;
					}
					break;
				case PERSON_FIRST_NAME:
					if(matcher.match(p1.getFirstName(), p2.getFirstName(), matchingMode,false)) {
						return true;
					}
					break;
				case PERSON_ID:
					if(p1.getId()==p2.getId()) {
						return true;
					}
					break;
				case PERSON_LAST_NAME:
					if(matcher.match(p1.getLastName(), p2.getLastName(), matchingMode,false)) {
						return true;
					}
					break;
				case PERSON_PASSWORD:
					if(p1.getPassword().equals(p2.getPassword())) {
						return true;
					}
					break;
				case PERSON_STATUS:
					if(matcher.match(p1.getStatus().toString(), p2.getStatus().toString(), matchingMode,false)) {
						return true;
					}
					break;
				default:
					continue;
				
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
