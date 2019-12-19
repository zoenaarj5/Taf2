package dax;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biz.Person;
import fxn.PersonComparator;
import search.PersonSearchField;
import search.SearchField;
import search.SearchMatchingMode;
import search.SearchMode;

public class PeopleAx implements DataAx<Person> {
	public static PeopleAx uniqueInstance=new PeopleAx();
	private List<Person> peopleList;
	public static PeopleAx getUniqueInstance() {
		return uniqueInstance;
	}
	public List<Person> getPeopleList() {
		return peopleList;
	}
	private PeopleAx() {
		super();
		initListWithExample();
	}
	public void resetList() {
		peopleList=new ArrayList<>();
	}
	public void initListWithExample(){
		this.peopleList=new ArrayList<>();
		peopleList.add(Person.createPerson("mj","mj2chicagobulls.com", "Michael1", "Michael","Jordan", Person.Status.BUSY));
		peopleList.add(Person.createPerson("mj32", "mqgic@lalakers.com", "Magic1", "Earvin Jr", "Johnson", Person.Status.BUSY));
		peopleList.add(Person.createPerson("dj","dj@brooklynnets.com","DeAndre1", "DeAndre","Jordan",Person.Status.OPEN));
		peopleList.add(Person.createPerson("jc","jc@clippers.com", "", "Jamal", "Crawford",Person.Status.AVAILABLE));
		peopleList.add(Person.createPerson("dr", "davidrobinson@spurs.com","David1", "David","Robinson",Person.Status.BUSY));
		peopleList.add(Person.createPerson("nr", "naterobinson@celtics.com","Nate1", "Nate","Robinson",Person.Status.BUSY));
	}
	@Override
	public List<Person> fetchAll() {
		return peopleList;
	}
	@Override
	public List<Person> fetch(Map<SearchField<Person>, Object> criteria, SearchMode mode, SearchMatchingMode matchingMode) {
		final List<Person> list=new ArrayList<>();
		final List<Person> usualList=peopleList;
		usualList.stream().forEach((p)->{
			if(this.match(p, criteria, mode, matchingMode)) {
				list.add(p);
			}
		});
		return list;
	}
	@Override
	public boolean find(String term) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean match(Person p, Map<SearchField<Person>, Object> criteria, SearchMode mode, SearchMatchingMode matchingMode,
			boolean caseSensitive) {
			for(Map.Entry<SearchField<Person>, Object> entry:criteria.entrySet()) {
				SearchField<Person> field=entry.getKey();
				Object value=entry.getValue();
				switch(mode) {
					case AND:
						if(!field.matchElement(p, value, matchingMode, caseSensitive)) {
							return false;
						}
						break;
					case OR:
						if(field.matchElement(p, value, matchingMode, caseSensitive)) {
							return true;
						}
						
						
				}
			}
			return mode==SearchMode.AND;
		}
	@Override
	public boolean add(Person p) {
		SearchField<Person>[] crit=new PersonSearchField[] {PersonSearchField.PERSON_EMAIL,PersonSearchField.PERSON_USER_NAME,PersonSearchField.PERSON_ID};
		for(Person pers:peopleList) {
			if(PersonComparator.getUniqueInstance().compare(pers, p, crit, SearchMode.OR, SearchMatchingMode.SAME,false)) {
				return false;
			}
		}
		peopleList.add(p);
		return true;
	}
}
