package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import biz.Person;
import dax.PeopleAx;

class PeopleAxTester {
	PeopleAx pax=PeopleAx.getUniqueInstance();
	List<Person> list=pax.fetchAll();
	int n=list.size();
	@Test
	void test() {
		pax.resetList();
		assertEquals("List size not 0 after resetting list",pax.getPeopleList().size(),0);
		printPeopleList();
		pax.initListWithExample();
		Assert.assertNotEquals("List size is 0 after resetting it to example list", pax.getPeopleList().size(),0);
		pax.resetList();
		assertTrue("New user not added",pax.add(Person.createPerson("MJ", "mj@bulls.com", "Michael1", "Michael", "Jordan", Person.Status.BUSY)));
		assertEquals("List size not 1 after adding one user",pax.getPeopleList().size(),1);
		assertFalse("User added despite not unique user name",pax.add(Person.createPerson("MJ", "mj@lakers.com", "Magic1", "Earvin", "Johnson", Person.Status.OPEN)));
		assertEquals("BIS - List size not 1 after not adding another user",pax.getPeopleList().size(),1);
		assertTrue("User not added despite unique user name, verdomme",pax.add(Person.createPerson("MJ32", "mj@lakers.com", "Magic1", "Earvin", "Johnson", Person.Status.OPEN)));
		printPeopleList();
	}
	private void printPeopleList() {
		System.out.println("-----START");
		pax.fetchAll().stream().forEach((p)->{
			System.out.println("-----------");
			System.out.println(":\t"+p.getId());
			System.out.println(":\t"+p.getEmail());
			System.out.println(":\t"+p.getUserName());
			System.out.println(":\t"+p.getFirstName());
			System.out.println(":\t"+p.getLastName());
			System.out.println(":\t"+p.getStatus());
		});
		System.out.println("----END");
	}
}
