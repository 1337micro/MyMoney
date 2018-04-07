//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001075
//Iteration 2: Noémi Lemonnier 40001075
//Description: Credit.java implements the interface Cards and serves 
//the purpose of implementing the specifics of the cards of type CREDIT
//--------------------------------------------------------


package src;

import java.util.ArrayList;
import java.util.List;

public class Credit implements Cards{
	//declaring attributes
	CardType type;
	int accNb;
	int cardNumber;
	double limit;
	double moneySpent;
	double moneyAvailable;
	List<String> list;
	String tmp ="";
	/*
	 * Default constructor
	 */
	public Credit() {
		type=CardType.CREDIT;
		accNb = 0;
		cardNumber=0;
		limit=0;
		moneySpent=0;
		moneyAvailable=limit-moneySpent;
		this.list = new ArrayList<>();
	}
	/*
	 * Constructor
	 */
	public Credit(CardType cardType, int accNb, int d, double moneySpent, double limit) {
		this.type=CardType.CREDIT;
		this.accNb = accNb;
		this.cardNumber=d;
		this.limit =limit;
		this.moneySpent=moneySpent;
		this.moneyAvailable=limit-moneySpent;
		this.list = new ArrayList<>();
	}
	/*
	 * Method to add a string to the list
	 * @see src.Cards#addExpense(java.lang.String)
	 */
	public void addExpense(String n){
		this.list.add(n);
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
		if((this.getType() == card.getType()) &&(this.getAccNb() == card.getAccNb()) && (this.getCardNumber() == card.getCardNumber()) &&(this.getLimit() == card.getLimit()) && (this.getMoneySpent() == card.getMoneySpent())){
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
	
/*
 * Getters and setters for the attributes
 * (non-Javadoc)
 * @see src.Cards#getType()
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
	public double getMoneySpent() {
		return moneySpent;
	}
	@Override
	public void setMoneySpent(double moneySpent) {
		this.moneySpent = moneySpent;
	}
	@Override
	public void setLimit(double limit) {
		this.limit=limit;
	}
	@Override
	public double getLimit() {
		return limit;
	}
	@Override
	public double getMoneyAvailable() {
		return moneyAvailable;
	}
	@Override
	public void setMoneyAvailable(double moneyAvailable) {	
		if(limit-moneySpent==moneyAvailable)
			this.moneyAvailable=moneyAvailable;
		else {
			this.moneyAvailable=moneyAvailable;
			moneyAvailable=limit-moneySpent;
		} 
	}
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
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


