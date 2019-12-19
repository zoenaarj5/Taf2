package test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import vmd.SignInViewModel;

class SignInViewModelTester {

	@Test
	void test() {
		SignInViewModel sivm=new SignInViewModel();
		assertNotNull("Check message is null",sivm.getCheckMessage());
		assertNotNull("Login is null",sivm.getLogin());
		assertNotNull("Password is null",sivm.getPassword());
		assertNull("Logged user is null",sivm.getLoggedUser()==null?null:"OK");
		sivm.setLogin("MJ45");
		sivm.setPassword("Michael1");
		sivm.signIn();
		assertNull("User logged despite unknown user name",sivm.getLoggedUser()==null?null:"OK");
		sivm.setLogin("MJ");
		sivm.setPassword("Michael11");
		sivm.signIn();
		assertNull("User logged despite wrong password",sivm.getLoggedUser()==null?null:"OK");
		sivm.setLogin("MJ");
		sivm.setPassword("Michael1");
		sivm.signIn();
		assertNotNull("Logged user is null",sivm.getLoggedUser()==null?null:"OK");
	}
}
