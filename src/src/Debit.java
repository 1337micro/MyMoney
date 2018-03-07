//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001075
//Iteration 2: Noémi Lemonnier 40001075
//Description: implements the interface cards and serves the purpose of implementing the 
//              specifics of the cards of type DEBIT
//--------------------------------------------------------

package src;

import java.util.ArrayList;
import java.util.List;

public class Debit implements Cards{

	CardType type;
	int accNb;
	int cardNumber;
	double moneyAvailable;
	List<String> list;
	String tmp ="";

	public Debit() {
		type=CardType.DEBIT;
		accNb = 0;
		cardNumber=0;
		moneyAvailable=0.0;
		this.list = new ArrayList<>();
	}
	/**
	 * Constructor for debit cards
	 * @param CardType type
	 * @param int accNb
	 * @param int cardNumber
	 * @param double moneyCurrent
	 */
	public Debit(CardType cardType, int accNb, int cardNumber, double moneyAvailable) {
		this.type=CardType.DEBIT;
		this.accNb = accNb;
		this.cardNumber=cardNumber;
		this.moneyAvailable=moneyAvailable;
		this.list = new ArrayList<>();
	}
	/*
	 * Method to get one string with all the information
	 */
	public String listFormat(){
		String tmp="";
		for(int i =0 ; i< this.getList().size(); i++){
			tmp+=tmp+ this.getList().get(i) +"\n";
		}
		return tmp;
	}

	/*
	 * Getters and Setter for the class attributes
	 */
	@Override
	public CardType getType() {
		return this.type;
	}
	@Override
	public void setType(CardType type) {
		this.type = type;
	}

	@Override
	public int getAccNb() {
		return accNb;
	}
	@Override
	public void setAccNb(int accNb) {
		this.accNb = accNb;
	}

	@Override
	public int getCardNumber() {
		return cardNumber;
	}
	@Override
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public double getMoneyAvailable() {
		return this.moneyAvailable;
	}
	@Override
	public void setMoneyAvailable(double moneyAvailable) {
		this.moneyAvailable = moneyAvailable;	
	}
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	//not used in this case
	@Override
	public double getMoneySpent() {
		return 0;
	}

	//not used in this case
	@Override
	public void setMoneySpent(double moneySpent) {
	}

	//not used in this case
	@Override
	public void setLimit(double limit) {	
	}

	//not used in this case
	@Override
	public double getLimit() {
		return 0;
	}
	//not used in this case
	@Override
	public int getPointsAvailable() {
		return 0;
	}
	//not used in this case
	@Override
	public void setPointsAvailable(int pointsAvailable) {
	}
	//not used in this case
	@Override
	public String getEmail() {
		return null;
	}
	//not used in this case
	@Override
	public void setEmail(String email) {
	}

	/*
	 * Method to check if the card is equal to another card
	 */
	@Override
	public boolean equals(Cards card) {
		if((this.getType() == card.getType()) &&(this.getAccNb() == card.getAccNb()) && (this.getCardNumber() == card.getAccNb()) && (this.getMoneyAvailable() == card.getMoneyAvailable())){
			return true;
		}
		return false;
	}
	/*
	 * method to get each string of their list
	 */
	@Override
	public String getStringList() {
		for(int i=0; i< this.getList().size(); i++){
			tmp+= this.getList().get(i);
		}
		return tmp;
	}
	
}

