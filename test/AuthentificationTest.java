package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
//import org.apache.commons.io.FileUtils;

import src.AuthentificationUser;
import src.AuthentificationList;
import src.AuthentificationUI;
import src.Cards;
import src.Constants;
import src.MyCards;

public class AuthentificationTest {

	/*
	 * test Authentification class
	 */
	AuthentificationUser userNull = new AuthentificationUser();
	AuthentificationUser userTst = new AuthentificationUser("e_lem","12sd");

	@Test
	public void testDefaultConstructorAuthentification(){
		assertEquals("Verifying username","",userNull.getUsername());
		assertEquals("Verifying password","",userNull.getPassword());
	}

	@Test
	public void testConstructorAuthentification(){
		assertEquals("Verifying username","e_lem",userTst.getUsername());
		assertEquals("Verifying password","12sd",userTst.getPassword());
	}
	@Test
	public void testAuthentificationSetGetUsername() {
		userNull.setUsername("d_jhg");
		assertEquals("Verifying username","d_jhg",userNull.getUsername());

	}
	@Test
	public void testAuthentificationSetGetPassword() {
		userNull.setPassword("1234");
		assertEquals("Verifying password","1234",userNull.getPassword());

	}

	/*
	 * Test for AuthentificationList class
	 */
	AuthentificationList users_list = new AuthentificationList();
	//testing if list is created when starting an instance of MyCards
	@Test
	public void testAuthentificationListConstructorGetter() {
		List <AuthentificationUser> expected =  new ArrayList<>();
		assertEquals("Verifying the array list created",expected,users_list.getUsersList());
	}

	@Test
	public void testAddUser() {
		users_list.addUser("w_eds", "1234");
		List <AuthentificationUser> listL = users_list.getUsersList();
		int index = AuthentificationList.getIndexUserFromUserName("w_eds", listL);
		AuthentificationUser usr = users_list.get(index);	
		assertEquals("Verifying that the debit card has been added", true, users_list.getUsersList().contains(usr));
	}

	@Test
	public void testRemoveUser() {
		users_list.addUser("q_sdf","1234");
		List <AuthentificationUser> listL = users_list.getUsersList();
		int index = AuthentificationList.getIndexUserFromUserName("q_sdf",listL);
		AuthentificationUser usr = users_list.get(index);
		users_list.removeUser(index);
		assertEquals("Verifying if a card is removed", false, users_list.getUsersList().contains(usr));

	}
	@Test
	public void testGetIndexUserFromUserName() {
		users_list.addUser("q_sdf","1234");
		users_list.addUser("x_cbv","1234");
		List <AuthentificationUser> listL=users_list.getUsersList();
		int index = AuthentificationList.getIndexUserFromUserName("q_sdf",listL);
		assertEquals("Verifying that the index of the card is correct", 0, AuthentificationList.getIndexUserFromUserName("q_sdf",listL), AuthentificationList.getIndexUserFromUserName("x_cbv",listL));
	}
	
//	@Test
//	public void testDuplicateFile() {
//		File test = new File("DefaultBudgetTest.txt");
//		try {
//			AuthentificationUI.budgetDup(Constants.DEFAULT_BUDGET_FILE,test);
//		} catch (IOException e) {
//			System.out.println("Error when attempting to duplicate file, will now exit");
//			System.exit(0);
//		}
//		
//		boolean compareFiles = FileUtils.contentEquals(Constants.DEFAULT_BUDGET_FILE,test);
//		
//		
//		
//	}
}

