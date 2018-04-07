//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001085
//Iteration 2: Noémi Lemonnier 40001085
//Iteration 3: Noémi Lemonnier 40001085
//Description: Debit.java implements the interface Cards and serves 
//the purpose of implementing the specifics of the cards of type DEBIT
//--------------------------------------------------------

package src;

import java.util.ArrayList;
import java.util.List;

public class Debit implements Cards{
	//declaring attributes
	CardType type;
	int accNb;
	int cardNumber;
	double moneyAvailable;
	List<String> list;
	String tmp ="";

	/*
	 * Default Constructor
	 */
	public Debit() {
		type=CardType.DEBIT;
		accNb = 0;
		cardNumber=0;
		moneyAvailable=0.0;
		this.list = new ArrayList<>();
	}
	/**
	 * Constructor
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
	 * Method to add a string to the list
	 * @see src.Cards#addExpense(java.lang.String)
	 */
	public void addExpense(String n){
		List<String> listTemp = new ArrayList<>();
		for(int i =0; i< list.size(); i++){
			listTemp.add(list.get(i));
			list.remove(i);
		}
		listTemp.add(n);
		
		for(int j=0; j< listTemp.size(); j++){
			list.add(listTemp.get(j));
		}
		
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
	 * Method to check if the card is equal to another card
	 */
	@Override
	public boolean equals(Cards card) {
		if((this.getType() == card.getType()) &&(this.getAccNb() == card.getAccNb()) && (this.getCardNumber() == card.getCardNumber()) && (this.getMoneyAvailable() == card.getMoneyAvailable())){
			return true;
		}
		return false;
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


}



