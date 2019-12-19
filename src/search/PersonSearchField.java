package search;

import java.util.Map;

import biz.Person;
import fxn.StringMatcher;

public enum PersonSearchField implements SearchField<Person>{
	PERSON_ID,
	PERSON_USER_NAME,
	PERSON_EMAIL,
	PERSON_PASSWORD,
	PERSON_FIRST_NAME,
	PERSON_LAST_NAME,
	PERSON_STATUS;

	@Override
	public boolean matchFields(Person p1, Person p2, SearchMatchingMode matchingMode, boolean caseSensitive) {
		switch(this) {
			case PERSON_EMAIL:
				return StringMatcher.getUniqueInstance().match(p1.getEmail(), p2.getEmail(), matchingMode, caseSensitive);
			case PERSON_FIRST_NAME:
				return StringMatcher.getUniqueInstance().match(p1.getFirstName(), p2.getFirstName(), matchingMode, caseSensitive);
			case PERSON_ID:
				return p1.getId()==p2.getId();
			case PERSON_LAST_NAME:
				return StringMatcher.getUniqueInstance().match(p1.getLastName(), p2.getLastName(), matchingMode, caseSensitive);
			case PERSON_PASSWORD:
				return StringMatcher.getUniqueInstance().match(p1.getPassword(), p2.getPassword(), SearchMatchingMode.SAME, true);
			case PERSON_STATUS:
				return p1.getStatus()==p2.getStatus();
			case PERSON_USER_NAME:
				return StringMatcher.getUniqueInstance().match(p1.getUserName(), p2.getUserName(), matchingMode, caseSensitive);
			default:
				return matchingMode==SearchMatchingMode.LIKE;
			
			}
	}

	@Override
	public boolean matchElement(Person p,Object value,SearchMatchingMode matchingMode, boolean caseSensitive) {
		switch(this) {
			case PERSON_EMAIL:
				return StringMatcher.getUniqueInstance().match(p.getEmail(), value.toString(), matchingMode, caseSensitive);
			case PERSON_FIRST_NAME:
				return StringMatcher.getUniqueInstance().match(p.getFirstName(), value.toString(), matchingMode, caseSensitive);
			case PERSON_ID:
				return Integer.valueOf(p.getId()).equals(Integer.valueOf(value.toString()));
			case PERSON_LAST_NAME:
				return StringMatcher.getUniqueInstance().match(p.getLastName(), value.toString(), matchingMode, caseSensitive);
			case PERSON_PASSWORD:
				return StringMatcher.getUniqueInstance().match(p.getPassword(), value.toString(), matchingMode, caseSensitive);
			case PERSON_STATUS:
				return p.getStatus().equals(value);
			case PERSON_USER_NAME:
				return StringMatcher.getUniqueInstance().match(p.getUserName(), value.toString(), matchingMode, caseSensitive);
			default:
				return matchingMode==SearchMatchingMode.LIKE;
		
		}
	}

}
