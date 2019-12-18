package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import biz.Person;
import fxn.EntityComparator;
import fxn.PersonComparator;
import search.SearchField;
import search.SearchMatchingMode;
import search.SearchMode;

class EntityComparatorTester {

	@Test
	void test() {
		EntityComparator<Person> comp=PersonComparator.getUniqueInstance();
		Person mj=Person.createPerson("mj", "mj@chicagobulls.com", "Michael1", "Michael", "Jordan", Person.Status.BUSY);
		Person magic=Person.createPerson("mj32", "mj@lalakers.com", "Magic1", "Earvin Jr", "Johnson", Person.Status.AVAILABLE);
		Person dj=Person.createPerson("da6", "dj@brooklynnets.com", "DeAndre1", "DeAndre", "Jordan", Person.Status.OPEN);
		Person nate=Person.createPerson("nr2", "naterobinson@slamdunk.com", "Nate1", "Nate", "Robinson", Person.Status.AVAILABLE);
		Person admiral=Person.createPerson("dr50", "davidrobinson@spurs.com", "David1", "David", "ROBINSON", Person.Status.BUSY);
		Person robin=Person.createPerson("rob00", "imaginary@nba.com", "Imaginary1", "Imaginary", "Robin", Person.Status.OPEN);
		assertFalse("User names matched while different, despite SAME matching mode",
				comp.compare(mj, magic, new SearchField[] {SearchField.PERSON_USER_NAME}, SearchMode.AND, SearchMatchingMode.SAME));
		assertTrue("Second user name containing first one not matched despite LIKE matching mode",
				comp.compare(mj, magic, new SearchField[] {SearchField.PERSON_USER_NAME}, SearchMode.AND, SearchMatchingMode.LIKE));
		assertTrue("Second user name containing first one not matched despite LIKE matching mode",
				comp.compare(mj, magic, new SearchField[] {SearchField.PERSON_USER_NAME}, SearchMode.AND, SearchMatchingMode.LIKE));
		assertTrue("Second user name contained in first one not matched despite LIKE matching mode",
				comp.compare(magic, mj, new SearchField[] {SearchField.PERSON_USER_NAME}, SearchMode.AND, SearchMatchingMode.LIKE));
		assertTrue("Second user's last name not matched",
				comp.compare(nate, admiral, new SearchField[] {SearchField.PERSON_LAST_NAME}, SearchMode.AND, SearchMatchingMode.SAME));
		assertTrue("Second user's last name contained in first user's, matched despite LIKE matching mode",
				comp.compare(nate, robin, new SearchField[] {SearchField.PERSON_LAST_NAME}, SearchMode.AND, SearchMatchingMode.LIKE));
		assertFalse("Second user's last name contained in first user's, not matched despite SAME matching mode",
				comp.compare(nate, robin, new SearchField[] {SearchField.PERSON_LAST_NAME}, SearchMode.AND, SearchMatchingMode.SAME));
		assertTrue("Second user's last name containing first user's, matched despite LIKE matching mode",
				comp.compare(robin, nate, new SearchField[] {SearchField.PERSON_LAST_NAME}, SearchMode.AND, SearchMatchingMode.LIKE));
		assertFalse("Second user's last name containing first user's, not matched despite SAME matching mode",
				comp.compare(nate, robin, new SearchField[] {SearchField.PERSON_LAST_NAME}, SearchMode.AND, SearchMatchingMode.SAME));
		
	}

}
