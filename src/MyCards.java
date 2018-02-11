//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noemi Lemonnier 40001085
//Description: Brings together the debit and credit classes together to make the program work. 
//				It is the junction of all other card related files.
//--------------------------------------------------------


package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import src.Cards.CardType;


public class MyCards {
	private static List <Cards> cards;
	private static PrintWriter pw = null;
	private static File file = new File("src/MyCards.txt");
	private static BufferedWriter bw;
	private static BufferedReader reader;
	private static CardType cdtp;
	private static int cardNumD;
	private static int accNb;
	private static double money;
	private static int cardNum;
	private static double moneyAvailable;
	private static double moneySpent;
	private static double limitCard;

	public List<Cards> getCards() {
		return cards;
	}

	/*
	 * Creates an Arraylist to store the cards as a sequence
	 */
	public MyCards() {
		this.cards= new ArrayList<>();
	}

	/*
	 * Method to add a Debit card to the Arraylist
	 */
	public void addCard(Cards.CardType type, int accNb, int cardNumber, double moneyAvailable) {
		if(type == Cards.CardType.DEBIT) {
			Debit debit= new Debit(type, accNb, cardNumber, moneyAvailable);
			cards.add(debit);
		}
	}

	/*
	 * Method to add a Credit card to the Arraylist
	 */
	public void addCard(Cards.CardType type, int accNb, int cardNumber, double limit, double moneySpent) {
		if(type == Cards.CardType.CREDIT) {
			Credit credit = new Credit(type, accNb, cardNumber, limit, moneySpent);
			cards.add(credit);

		}
	}

	/*
	 * Method to remove any card from the list
	 */
	public void removeCard(int card) {
		cards.remove(card);
	}

	/*
	 * Method to write to the database textfile (MyCards.txt)
	 */
	public static void writeToFile(Cards newCard) throws IOException{

		// Opening file stream to write log
		try {

			pw = new PrintWriter(new FileOutputStream(file, true));
		} catch (Exception e) {
			System.out.println("Error while writing to the file");
			System.exit(1);
		}

		// Checks the type of the card that needs to be added to the Database and adjust its constructor
		if(newCard.getType() == CardType.DEBIT){
			newCard = new Debit(newCard.getType(), newCard.getAccNb(), newCard.getCardNumber(), newCard.getMoneyAvailable());	
			pw.println(newCard.getType() + "," + newCard.getAccNb() + "," + newCard.getCardNumber() + "," + newCard.getMoneyAvailable());

		}
		if( newCard.getType() == CardType.CREDIT){
			newCard = new Credit(newCard.getType(), newCard.getAccNb(), newCard.getCardNumber(), newCard.getMoneySpent(), newCard.getLimit());
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
	 * Method to clear the database textfile (MyCards.txt)
	 */
	public static void clearDataBaseMyCards() throws IOException{
		if (file.exists() && file.isFile())
		{
			//delete if exists
			file.delete();
		}
		file.createNewFile();
	}
	/*
	 * Method to find duplicate if user inputs a new card
	 */
	public static boolean readToFindDuplicate(Cards cardInput, DefaultTableModel model) {
		// Open file to read from
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			System.out.println("Error when opening file1.");
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
					cardNumD = Integer.parseInt(lineArray[2]);
					money= Double.parseDouble(lineArray[3]);

					Debit cd = new Debit(cdtp, accNb, cardNumD, money);
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
	 * Method to read the database textfile (MyCards.txt)
	 */
	public static void readFromTheFile(ArrayList <Cards> cards_list, DefaultTableModel model) {

		// Open file to read from
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			System.out.println("Error when opening file2.");
			System.out.println("Program will terminate.");
			System.exit(0);
		}

		//Check to make sure correct file is being read
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				if(line.startsWith("DEBIT")){
					String[] lineArray = line.split(",");
					cdtp = CardType.DEBIT;
					accNb = Integer.parseInt(lineArray[1]);
					cardNumD = Integer.parseInt(lineArray[2]);
					money= Double.parseDouble(lineArray[3]);

					Debit cd = new Debit(cdtp, accNb, cardNumD, money);
					cards_list.add(cd);
					// Adding it to the table
					Object[] data = {cdtp, accNb, cardNumD, money};
					model.addRow(data);
				}
				if(line.startsWith("CREDIT")){
					String[] lineArray = line.split(",");
					cdtp = CardType.CREDIT;
					accNb = Integer.parseInt(lineArray[1]);
					cardNum = Integer.parseInt(lineArray[2]);
					moneySpent= Double.parseDouble(lineArray[3]);
					limitCard=Double.parseDouble(lineArray[4]);
					moneyAvailable=Double.parseDouble(lineArray[5]);
					Credit cd = new Credit(cdtp, accNb, cardNum, moneySpent, limitCard);
					cd.setMoneyAvailable(moneyAvailable);
					cards_list.add(cd);
					//adding values to table
					Object[] data = {cdtp, accNb, cardNum, moneySpent};
					model.addRow(data);
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

	}

	/*
	 * Method to remove from the database textfile (MyCards.txt)
	 */
	public static void removeLine(String cardLine) throws IOException{

		bw = new BufferedWriter(new FileWriter(file));
		reader = new BufferedReader(new FileReader(file));
		String currentLine;
		while((currentLine = reader.readLine()) != null){

			String trimmedLine = currentLine.trim();
			if(trimmedLine.equals(cardLine)){
				currentLine = "";
			}
			bw.write(currentLine + System.getProperty("line.separator"));

		}
		bw.close();
		reader.close();

	}

/*
 * Returns the card object corresponding to the card number and list of cards in the parameters.
 */
public static int getCardFromAccountNumber(int cardNb, List <Cards> list) {
	Cards card;
	for(int i=0; i<list.size();i++) {
		if (list.get(i).getCardNumber()==cardNb) 
			if(list.get(i).getType() == CardType.DEBIT){
				card = new Debit(list.get(i).getType(), list.get(i).getAccNb(), list.get(i).getCardNumber(), list.get(i).getMoneyAvailable());
				int ind = i;
				return ind;
				
			}
		if (list.get(i).getCardNumber()==cardNb) 
			if(list.get(i).getType() == CardType.CREDIT){
				card = new Credit(list.get(i).getType(), list.get(i).getAccNb(), list.get(i).getCardNumber(), list.get(i).getMoneySpent(), list.get(i).getLimit());
								int ind = i;
				return ind;

			}
	
	}
	return 0;
}
/*
 * Returns the card at a specified index
 */
public Cards get(int index) {
	return cards.get(index);
}
/*
 * Returns the list of cards in ArrayList <Cards> format
 */
public ArrayList <Cards> getArrayList() {
	return (ArrayList <Cards>)cards;
}
}

