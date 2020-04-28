package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Account;

public class AccountTest {
	
	private Account testAccount;
	
	@Before
	public void setUp() {
		
		testAccount = new Account();
		
	}
	
	
	@Test
	public void testEmailMethods() {
		
		testAccount.setEmail("email@gmail.com");
		
		assertEquals("email@gmail.com", testAccount.getEmail());
	}
	
	@Test
	public void testPasswordMethods() {
		
		testAccount.setPassword("password123");
		
		assertEquals("password123", testAccount.getPassword());
	}
	
	@Test
	public void testEmailConfirmMethods() {
		
		testAccount.setEmailConfirm(true);;
		
		assertTrue("password123", testAccount.getEmailConfirm());
	}
}
