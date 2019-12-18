package vmd;

import java.util.List;

import biz.Person;
import dax.PeopleAx;

public class SignInViewModel {
	private String login,password;
	private String checkMessage;

	public String getLogin() {
		return login;
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
	public void signIn() {
		List<Person> list=PeopleAx.getUniqueInstance().fetchAll();
	}
}
