package biz;

public class Person {
	public Person createPerson(String userName,String email,String password,String firstName,String lastName,Status status) {
		Person p=new Person();
		p.setUserName(userName);
		p.setEmail(email);
		p.setPassword(password);
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setStatus(status);
		return p;
	}
	private Person() {
		super();
	}
	enum Status{
		AVAILABLE,BUSY,OPEN
	}
	private int id;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	private String userName,email,password,firstName,lastName;
	private Status status;
}
