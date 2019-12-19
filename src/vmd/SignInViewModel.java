package vmd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.Command;

import biz.Person;
import dax.PeopleAx;
import search.PersonSearchField;
import search.SearchField;
import search.SearchMatchingMode;
import search.SearchMode;

public class SignInViewModel {
	private String login="",password="";
	private String checkMessage="";
	private Person loggedUser;
	public String getLogin() {
		return login;
	}

	public Person getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(Person loggedUser) {
		this.loggedUser = loggedUser;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckMessage() {
		return checkMessage;
	}

	public void setCheckMessage(String checkMessage) {
		this.checkMessage = checkMessage;
	}
	@Command
	public void signIn() {
		Map<SearchField<Person>,Object> criteria=new HashMap<>();
		criteria.put(PersonSearchField.PERSON_USER_NAME, login);
		List<Person> res=PeopleAx.getUniqueInstance().fetch(criteria, SearchMode.AND, SearchMatchingMode.SAME);
		if(res.isEmpty()) {
			this.setLoggedUser(null);
			this.setCheckMessage("User not found. Please try again");
		}else {
			this.setLoggedUser(res.get(0));
			this.setCheckMessage("Welcome home, "+loggedUser.getFirstName()+" "+loggedUser.getLastName());
		}
	}
}
