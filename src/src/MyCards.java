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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import src.Cards.CardType;
import src.CashSpending.ExpenditureType;


public class MyCards {
	//declaring attributes
	private static List <Cards> cards;
	private static List <String> lst_string;
	private static PrintWriter pw = null;
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

	/*
	 * Default Constructor
	 */
	public MyCards() {
		this.cards= new ArrayList<>();
	}


	/*
	 * method to add expenses  to the card list of string
	 */
	public static void addToTheList(Cards cardTmp,  String n){
		List<String> temp_lst= new ArrayList<>();
		for(int i=0; i < cards.size(); i++){
			//if find a card in the ArrayList that is the same as the one input
			if(cards.get(i).getCardNumber() == cardTmp.getCardNumber()){
				//set the temporary arraylist as the card initial list of expense
				temp_lst = cards.get(i).getList();
				temp_lst.add(n); //add the new expense to the initial list
				cardTmp.setList(temp_lst); //set the new list to the card object
			}
			else{ //if no card already exist with the cardNumber as the one input
				temp_lst.add(n);
				cardTmp.setList(temp_lst);
				cards.add(cardTmp);
			}
		}
	}


	/*
	 * to format the output 
	 */
	public static String formatTransactionCards(ExpenditureType type, double amountMn, int cardNb){
		//String n = cardNb+"# paid transaction for expenditure " + type + " of $" + amountMn + "\n";
		String n = "Transaction for expenditure " + type + " of $" + amountMn + "\n";
		return n;
	}

	/*
	 * Method to check if email address are email addresses
	 */
	public static Boolean isValid(String email) {
		return email.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	}

	/*
	 * method to get the index of a card based on its card number
	 */
	public static int getIndexFromCardNb(int num){
		for(int i = 0; i< cards.size(); i++){
			if(cards.get(i).getCardNumber() == num){
				return i;
			}
		}
		return -1;

	}

	/*
	 * method to remove a card from the list
	 */
	public void removeCard(int card) {
		cards.remove(card);
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
		else if (type == CardType.BITCOIN){

			BitcoinCard bitcoinCard = new BitcoinCard(type, accNb, cardNumber, limit, moneySpent);
			cards.add(bitcoinCard);

		}
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
	 * returns the index of the corresponding card number from the array
	 */
	static public int getIndexCardFromAccountNumber(int cardNb, List <Cards> list) {
		//Cards cardTmp;
		//goes through the array
		for(int i=0; i<list.size();i++) {
			//if you get a match with the card number inputed and one in the array
			if (list.get(i).getCardNumber()==cardNb){ 
				//if it is a debit
				return i;
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
		if( newCard.getType() == CardType.BITCOIN){
			newCard = new BitcoinCard(newCard.getType(), newCard.getAccNb(), newCard.getCardNumber(), newCard.getMoneySpent(), newCard.getLimit());
			pw.println(newCard.getType() + "," + newCard.getAccNb() + "," + newCard.getCardNumber() + "," + newCard.getMoneySpent()+ "," +newCard.getLimit()+ "," +newCard.getMoneyAvailable());
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
				else if(line.startsWith("BITCOIN")){
					String[] lineArray = line.split(",");
					cdtp = CardType.BITCOIN;
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
						if((accNb>=0) && (cardNum>=0) && (money>=0)){ //to make sure no input is null
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
					if(line.startsWith("BITCOIN")){
						String[] lineArray = line.split(",");
						cdtp = CardType.BITCOIN;
						accNb = Integer.parseInt(lineArray[1]);
						cardNum = Integer.parseInt(lineArray[2]);
						moneySpent= Double.parseDouble(lineArray[3]);
						limitCard=Double.parseDouble(lineArray[4]);
						moneyAvailable=Double.parseDouble(lineArray[5]);
						double verifyMoney = limitCard-moneySpent;
						boolean moneyBool= (verifyMoney== verifyMoney);
						if((accNb>=0) && (cardNum>=0) && (moneySpent>=0) && (limitCard>=0) && (moneyBool==true)){
							BitcoinCard cd = new BitcoinCard(cdtp, accNb, cardNum, moneySpent, limitCard);
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

	/*
	 * Method used to modify a card when a transaction is done in the database textfile MyCards.txt
	 */
	public static void modifyFile(String oldString, String newString)
	{
		String oldContent = "";
		FileWriter writer = null;
		try
		{
			reader = new BufferedReader(new FileReader(Constants.MYCARDS_FILE));
			//Reading all the lines of input text file into oldContent
			String line = reader.readLine();
			while (line != null) 
			{
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}

			//Replacing oldString with newString in the oldContent
			String newContent = oldContent.replaceAll(oldString, newString);

			//Rewriting the input text file with newContent
			writer = new FileWriter(Constants.MYCARDS_FILE);
			writer.write(newContent);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				//Closing the resources
				reader.close();
				writer.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	/*
	 * Get and Set for List<Cards> cards
	 */
	public List<Cards> getCards() {
		return cards;
	}
	/*
	 * Get and Set for List <String> lst_string
	 */
	public static void setLst_string(List<String> list, int index) {
		cards.get(index).setList(list);
	}
	public static List<String> getLst_string() {
		return lst_string;
	}
	/*
	 * Method to get the index of the List <String> lst_string;
	 */
	public String getIndex(int index){
		return lst_string.get(index);
	}
	/*
	 * Method to get  the card at a specified index of the List <Cards> cards
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
}


