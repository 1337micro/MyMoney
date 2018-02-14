//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001075
//Iteration 2: Noemi Lemonnier 40001085
//Description: Brings together the debit, credit, loyalty classes together to make the program work. 
//				It is the junction of all other card related files.
//--------------------------------------------------------


package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import src.Cards.CardType;


public class MyCards {
	private static List <Cards> cards;
	private static PrintWriter pw = null;
	//TODO: make sure the MYCARDS.TXT file is in the MyMoney folder
	private static BufferedReader reader;
	private static CardType cdtp;
	private static String eml;
	private static int accNb;
	private static double money;
	private static int cardNum;
	private static double moneyAvailable;
	private static double moneySpent;
	private static double limitCard;
	private static int pointsAvailable;
	private static boolean emlBool;

	public List<Cards> getCards() {
		return cards;
	}

	/*
	 * create an arraylist to store the cards as a sequence
	 */
	public MyCards() {
		this.cards= new ArrayList<>();
	}

	/*
	 * returns the card at a specified index
	 */
	public Cards get(int index) {											
		return cards.get(index);
	}

	/*
	 * returns the list in arrayList <Cards> format
	 */
	public ArrayList <Cards> getArrayList() {
		return (ArrayList <Cards>)cards;
	}

	/*
	 * method to add a card to the arraylist, debit type
	 */
	public void addCard(Cards.CardType type, int accNb, int cardNumber, double moneyAvailable) {
		if(type == Cards.CardType.DEBIT) {
			Debit debit= new Debit(type, accNb, cardNumber, moneyAvailable);
			cards.add(debit);
		}
	}

	/*
	 * method to add a card to the arraylist credit type
	 */
	public void addCard(Cards.CardType type, int accNb, int cardNumber, double limit, double moneySpent) {
		if(type == Cards.CardType.CREDIT) {
			Credit credit = new Credit(type, accNb, cardNumber, limit, moneySpent);
			cards.add(credit);

		}
	}
	/*
	 * Method to check if email address are email addresses
	 */
	public static Boolean isValid(String email) {
		return email.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	}

	/*
	 * method to add a card to the arraylist loyaltyCard type
	 */
	public void addCard(Cards.CardType type, String email, int cardNumber, int pointsAvailable) {
		if(type == Cards.CardType.LOYALTY) {
			emlBool= isValid(email);
			if(emlBool == true){
				LoyaltyCard loyaltyCard = new LoyaltyCard(type, email, cardNumber, pointsAvailable);
				cards.add(loyaltyCard);
			}

		}
	}

	/*
	 * method to remove a card from the list
	 */
	public void removeCard(int card) {
		cards.remove(card);
	}

	/*
	 * returns the index of the corresponding card number from the array
	 */
	static public int getIndexCardFromAccountNumber(int cardNb, List <Cards> list) {
		//Cards cardTmp;
		//goes through the array
		for(int i=0; i<list.size();i++) {

			//if you get a match with the card number inputed and one in the array
			if (list.get(i).getCardNumber()==cardNb){ 

				//if it is a debit
				if(list.get(i).getType() == CardType.DEBIT){
					//cardTmp = new Debit(list.get(i).getType(), list.get(i).getAccNb(), list.get(i).getCardNumber(), list.get(i).getMoneyAvailable());
					return i;

				}
				//if it is a credit
				if(list.get(i).getType() == CardType.CREDIT){
					//cardTmp = new Credit(list.get(i).getType(), list.get(i).getAccNb(), list.get(i).getCardNumber(), list.get(i).getMoneySpent(), list.get(i).getLimit());

					return i;
				}
				//if it is a loyalty card
				if(list.get(i).getType() == CardType.LOYALTY){
					//cardTmp = new LoyaltyCard(list.get(i).getType(), list.get(i).getEmail(), list.get(i).getCardNumber(), list.get(i).getPointsAvailable());

					return i;

				}

			}
		}
		//if nothing is find 
		return 0;
	}
	/*
	 * method to write to the database textfile
	 */
	public static void writeToFile(Cards newCard){

		// opening file stream to write log
		try {
			pw = new PrintWriter(new FileOutputStream(Constants.MYCARDS_FILE, true));
		} catch (Exception e) {
			System.out.println("Error while creating file");
			System.exit(1);
		}

		//checks the type of the card that needs to be added to the Database and adjust its constructor
		if(newCard.getType() == CardType.DEBIT){
			newCard = new Debit(newCard.getType(), newCard.getAccNb(), newCard.getCardNumber(), newCard.getMoneyAvailable());	
			pw.println(newCard.getType() + "," + newCard.getAccNb() + "," + newCard.getCardNumber() + "," + newCard.getMoneyAvailable());
		}
		if( newCard.getType() == CardType.CREDIT){
			newCard = new Credit(newCard.getType(), newCard.getAccNb(), newCard.getCardNumber(), newCard.getMoneySpent(), newCard.getLimit());
			pw.println(newCard.getType() + "," + newCard.getAccNb() + "," + newCard.getCardNumber() + "," + newCard.getMoneySpent()+ "," +newCard.getLimit()+ "," +newCard.getMoneyAvailable());
		}
		if( newCard.getType() == CardType.LOYALTY){
			newCard = new LoyaltyCard(newCard.getType(), newCard.getEmail(), newCard.getCardNumber(), newCard.getPointsAvailable());
			pw.println(newCard.getType() + "," + newCard.getEmail() + "," + newCard.getCardNumber() + "," + newCard.getPointsAvailable() + "," + newCard.getMoneyAvailable());
		}

		// Closing file stream
		try {
			pw.close();
		} catch (Exception e) {
			System.out.println("Error while closing file");
			System.exit(1);
		}
	}

	/*
	 * method to clear the database textfile
	 */
	public static void clearDataBaseMyCards() throws IOException{
		if (Constants.MYCARDS_FILE.exists() && Constants.MYCARDS_FILE.isFile())
		{
			//delete if exists
			Constants.MYCARDS_FILE.delete();
		}
		Constants.MYCARDS_FILE.createNewFile();
	}
	/*
	 * method to find duplicate if user inputs a new card
	 */
	public static boolean readToFindDuplicate(Cards cardInput, DefaultTableModel model) {
		// Open file to read from
		try {
			reader = new BufferedReader(new FileReader(Constants.MYCARDS_FILE));
		} catch (Exception e) {
			System.out.println("Error when opening file.");
			System.out.println("Program will terminate.");
			System.exit(0);
		}
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				if(line.startsWith("DEBIT")){
					String[] lineArray = line.split(",");
					cdtp = CardType.DEBIT;
					accNb = Integer.parseInt(lineArray[1]);
					cardNum = Integer.parseInt(lineArray[2]);
					money= Double.parseDouble(lineArray[3]);
					Debit cd = new Debit(cdtp, accNb, cardNum, money);

					if( cd.getCardNumber() == cardInput.getCardNumber()){
						return true;
					}
				}
				else if(line.startsWith("CREDIT")){
					String[] lineArray = line.split(",");
					cdtp = CardType.CREDIT;
					accNb = Integer.parseInt(lineArray[1]);
					cardNum = Integer.parseInt(lineArray[2]);
					moneySpent= Double.parseDouble(lineArray[3]);
					limitCard=Double.parseDouble(lineArray[4]);
					moneyAvailable=Double.parseDouble(lineArray[5]);
					Credit cd = new Credit(cdtp, accNb, cardNum, moneySpent, limitCard);
					if( cd.getCardNumber() == cardInput.getCardNumber()){
						return true;
					}
				}
				else if(line.startsWith("LOYALTYCARD")){
					String[] lineArray = line.split(",");
					cdtp = CardType.LOYALTY;
					eml = lineArray[1];
					cardNum = Integer.parseInt(lineArray[2]);
					pointsAvailable= Integer.parseInt(lineArray[3]);
					money=Double.parseDouble(lineArray[4]);
					LoyaltyCard cd = new LoyaltyCard(cdtp, eml, cardNum, pointsAvailable);
					if( cd.getCardNumber() == cardInput.getCardNumber()){
						return true;
					}
				}

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
	public static void readFromTheFile(ArrayList <Cards> cards_list, DefaultTableModel model) {

		// Open file to read from
		try {
			reader = new BufferedReader(new FileReader(Constants.MYCARDS_FILE));
		} catch (Exception e) {
			System.out.println("Error when opening file.");
			System.out.println("Program will terminate.");
			System.exit(0);
		}

		//Check to make sure correct file is being read
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				try{
					if(line.startsWith("DEBIT")){
						String[] lineArray = line.split(",");
						cdtp = CardType.DEBIT;
						accNb = Integer.parseInt(lineArray[1]);
						cardNum = Integer.parseInt(lineArray[2]);
						money= Double.parseDouble(lineArray[3]);
						if((accNb>=0) && (cardNum>=0) && (money>=0)){
							Debit cd = new Debit(cdtp, accNb, cardNum, money);
							cards_list.add(cd);
							//adding it to the table
							Object[] data = {cdtp, accNb, cardNum, money};
							model.addRow(data);
						}
						else{
							throw new NumberFormatException();
						}
					}
					if(line.startsWith("CREDIT")){
						String[] lineArray = line.split(",");
						cdtp = CardType.CREDIT;
						accNb = Integer.parseInt(lineArray[1]);
						cardNum = Integer.parseInt(lineArray[2]);
						moneySpent= Double.parseDouble(lineArray[3]);
						limitCard=Double.parseDouble(lineArray[4]);
						moneyAvailable=Double.parseDouble(lineArray[5]);
						double verifyMoney = limitCard-moneySpent;
						boolean moneyBool= (verifyMoney== verifyMoney);
						if((accNb>=0) && (cardNum>=0) && (moneySpent>=0) && (limitCard>=0) && (moneyBool==true)){
							Credit cd = new Credit(cdtp, accNb, cardNum, moneySpent, limitCard);
							cd.setMoneyAvailable(moneyAvailable);
							cards_list.add(cd);
							//adding values to table
							Object[] data = {cdtp, accNb, cardNum, moneySpent};
							model.addRow(data);
						}
						else{
							throw new NumberFormatException();
						}
					}
					if(line.startsWith("LOYALTYCARD")){
						String[] lineArray = line.split(",");
						cdtp = CardType.LOYALTY;
						eml = lineArray[1];
						emlBool = isValid(eml);
						cardNum = Integer.parseInt(lineArray[2]);
						pointsAvailable= Integer.parseInt(lineArray[3]);
						if(emlBool == true && cardNum>=0 && pointsAvailable>=0){
							LoyaltyCard cd = new LoyaltyCard(cdtp, eml, cardNum, pointsAvailable); 
							cards_list.add(cd);
							//adding it to the table
							Object[] data = {cdtp, eml, cardNum, pointsAvailable};
							model.addRow(data);

						}
						else{
							throw new NumberFormatException();
						}
					}
				}catch(NumberFormatException ne){
					System.out.println("Line had an error and was ignored by the program:" + line);

				};

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

	}

	/*
	 * method to remove from the database textfile MyCards
	 */
	public static void removeLine(String cardLine) throws IOException{
		try{
			reader = new BufferedReader(new FileReader(Constants.MYCARDS_FILE));
		}catch(IOException e){
			System.out.println("Wrong file");
		}
		String line;
		String input = "";
		while ((line = reader.readLine()) != null){

			if (line.equals(cardLine)){
				line = "";
				System.out.println("Data deleted from the DB Textfile.");
			}
			input += line + '\n';

		}

		FileOutputStream File = new FileOutputStream(Constants.MYCARDS_FILE);
		File.write(input.getBytes());
		reader.close();
		File.close();
	}


}
