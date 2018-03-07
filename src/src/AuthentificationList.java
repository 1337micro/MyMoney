package src;
//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 2: Noemi Lemonnier 40001085
//Description: Class to create a list of Authentification objects and have different methods used.
//        
//--------------------------------------------------------

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AuthentificationList {
	private static List <AuthentificationUser> users_list;
	private static PrintWriter pw = null;
	private static BufferedReader reader;
	private static String username;
	private static String password;

	/*
	 * method to get the list of Authentification
	 */
	public static List<AuthentificationUser> getUsersList() {
		return users_list;
	}

	/*
	 * Default constructor of the class
	 */
	public AuthentificationList() {
		users_list= new ArrayList<>();
	}

	/*
	 * returns the Authentific at a specified index
	 */
	public AuthentificationUser get(int index) {
		return users_list.get(index);
	}

	/*
	 * method to add a user to the array list
	 */
	public void addUser(String usernm, String passwrd) {
		AuthentificationUser user = new AuthentificationUser(usernm,passwrd);
		users_list.add(user);
	}


	/*
	 * method to remove a user from the list
	 */
	public void removeUser(int index) {
		users_list.remove(index);
	}

	/*
	 * returns the list in arrayList <Authentification> format
	 */
	public ArrayList <AuthentificationUser> getArrayList() {
		return (ArrayList <AuthentificationUser>)users_list;
	}
	/*
	 * returns the index of the corresponding card number from the array
	 */
	static public int getIndexUserFromUserName(String usernm, List <AuthentificationUser> list) {
		//goes through the array
		for(int i=0; i<list.size();i++) {
			if(list.get(i).getUsername().equals(usernm)){
				return i;
			}
		}
		//if nothing is find 
		return 0;
	}

	/*
	 * method to write to the database textfile
	 */
	public static void writeToFile(AuthentificationUser newUser){

		// opening file stream to write log
		try {
			pw = new PrintWriter(new FileOutputStream(Constants.AUTHENTIFICATION_FILE, true));
		} catch (Exception e) {
			System.out.println("Error while creating file");
			System.exit(1);
		}

		newUser = new AuthentificationUser(newUser.getUsername(), newUser.getPassword());	
		pw.println(newUser.getUsername() + ","+ newUser.getPassword());

		// Closing file stream
		try {
			pw.close();
		} catch (Exception e) {
			System.out.println("Error while closing file");
			System.exit(1);
		}
	}

	/*
	 * method to find duplicate if user inputs a new card
	 */
	public static boolean readToFindDuplicate(AuthentificationUser user) {
		// Open file to read from
		try {
			reader = new BufferedReader(new FileReader(Constants.AUTHENTIFICATION_FILE));
		} catch (Exception e) {
			System.out.println("Error when opening file.");
			System.out.println("Program will terminate.");
			System.exit(0);
		}
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {

				String[] lineArray = line.split(",");

				username = lineArray[0];
				password = lineArray[1];

				AuthentificationUser userTst = new AuthentificationUser(username, password);

				if(userTst.getUsername().equals(user.getUsername()) && userTst.getPassword().equals(user.getPassword())){
					return true;}
			}
		} catch (IOException e) {
			System.out.println("Error while reading file");
			System.out.println("Program will terminate.");
			System.exit(0);
		}
		// Closing reading stream
		try {
			if (reader != null)
				reader.close();

		} catch (Exception e) {
			System.out.println("Error when closing file.");
			System.out.println("Program will terminate.");
			System.exit(0);
		}
		return false;
	}

	/*
	 * method to read the database textfile
	 */
	public static void readFromTheFile(ArrayList <AuthentificationUser> user_list) {

		// Open file to read from
		try {
			reader = new BufferedReader(new FileReader(Constants.AUTHENTIFICATION_FILE));
		} catch (Exception e) {
			System.out.println("Error when opening file.");
			System.out.println("Program will terminate.");
			System.exit(0);
		}

		//Check to make sure correct file is being read
		String line = null;


		try{
			while ((line = reader.readLine()) != null) {

				String[] lineArray = line.split(",");

				username = lineArray[0];
				password = lineArray[1];
				if((username !=null) && (password != null)){
					AuthentificationUser userTst = new AuthentificationUser(username, password);
					user_list.add(userTst);
				}
				else{
					throw new NumberFormatException();
				}
			}

		}catch(NumberFormatException ne){
			System.out.println("Line had an error and was ignored by the program:" + line);
		}
		catch (IOException e) {
			System.out.println("Error while reading file");
			System.out.println("Program will terminate.");
			System.exit(0);
		}

		// Closing reading stream
		try {
			if (reader != null)
				reader.close();

		} catch (Exception e) {
			System.out.println("Error when closing file.");
			System.out.println("Program will terminate.");
			System.exit(0);
		}

	}



}

