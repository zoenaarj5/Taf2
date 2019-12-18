package dax;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biz.Person;
import fxn.PersonComparator;
import fxn.StringMatcher;
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
	public List<Person> fetch(Map<SearchField, Object> criteria, SearchMode mode, SearchMatchingMode matchingMode) {
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
	public boolean match(Person p, Map<SearchField, Object> criteria, SearchMode mode, SearchMatchingMode matchingMode,
			boolean caseSensitive) {
		for(Map.Entry<SearchField, Object> entry:criteria.entrySet()) {
			SearchField field=entry.getKey();
			Object value=entry.getValue();
			switch(mode) {
				case AND:
					switch(field) {
						case PERSON_EMAIL:
							if(!StringMatcher.getUniqueInstance().match(p.getEmail(), (String)value, matchingMode, caseSensitive)) {
								return false;
							}
							break;
						case PERSON_FIRST_NAME:
							if(!StringMatcher.getUniqueInstance().match(p.getFirstName(),(String)value,matchingMode, caseSensitive)) {
								return false;
							}
							break;
						case PERSON_ID:
							if(!Integer.valueOf(p.getId()).equals(value)) {
								return false;
							}
							break;
						case PERSON_LAST_NAME:
							if(!StringMatcher.getUniqueInstance().match(p.getLastName(), (String)value, matchingMode,caseSensitive)) {
								return false;
							}
							break;
						case PERSON_PASSWORD:
							if(!StringMatcher.getUniqueInstance().match(p.getPassword(), (String)value, SearchMatchingMode.SAME, true)) {
								return false;
							}
							break;
						case PERSON_STATUS:
							if(!StringMatcher.getUniqueInstance().match(p.getStatus().toString(), (String)value, matchingMode, false)) {
								return false;
							}
							break;
						case PERSON_USER_NAME:
							if(!StringMatcher.getUniqueInstance().match(p.getUserName(), (String)value, matchingMode, caseSensitive)) {
								return false;
							}
							break;
						default:
							break;
					}
					break;
				case OR:
					switch(entry.getKey()) {
						case PERSON_EMAIL:
							if(StringMatcher.getUniqueInstance().match(p.getEmail(), (String)value, matchingMode, caseSensitive)) {
								return true;
							}
							break;
						case PERSON_FIRST_NAME:
							if(StringMatcher.getUniqueInstance().match(p.getFirstName(),(String)value,matchingMode, caseSensitive)) {
								return true;
							}
							break;
						case PERSON_ID:
							if(Integer.valueOf(p.getId()).equals(value)) {
								return true;
							}
							break;
						case PERSON_LAST_NAME:
							if(StringMatcher.getUniqueInstance().match(p.getLastName(), (String)value, matchingMode,caseSensitive)) {
								return true;
							}
							break;
						case PERSON_PASSWORD:
							if(StringMatcher.getUniqueInstance().match(p.getPassword(), (String)value, SearchMatchingMode.SAME, true)) {
								return true;
							}
							break;
						case PERSON_STATUS:
							if(StringMatcher.getUniqueInstance().match(p.getStatus().toString(), (String)value, matchingMode, false)) {
								return true;
							}
							break;
						case PERSON_USER_NAME:
							if(StringMatcher.getUniqueInstance().match(p.getUserName(), (String)value, matchingMode, caseSensitive)) {
								return true;
							}
							break;
						default:
							break;
					}
				}
		}
		return mode==SearchMode.AND;
	}
}
