package src;
//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 2: Noémi Lemonnier 40001075
//Description: LoyaltyCard.java implements the interface Cards and serves 
//the purpose of implementing the specifics of the cards of type LoyaltyCard
//--------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

public class LoyaltyCard implements Cards{
	//declaring attributes
	CardType type;
	String accNb;
	int cardNumber;
	int pointsAvailable;
	double moneyAvailable;
	List<String> list;
	String tmp ="";

	/*
	 * Default constructor
	 */
	public LoyaltyCard() {
		type=CardType.LOYALTY;
		accNb = "";
		cardNumber=0;
		pointsAvailable=0;
		moneyAvailable=0;
		this.list = new ArrayList<>();
	}

	/**
	 * Constructor for LoyaltyCard 
	 * @param CardType type
	 * @param int accNb
	 * @param int cardNumber
	 * @param double moneyCurrent
	 */
	public LoyaltyCard(CardType cardType, String email, int cardNumber, int pointsAvailable) {
		this.type=CardType.LOYALTY;
		this.accNb = email;
		this.cardNumber=cardNumber;
		this.pointsAvailable=pointsAvailable;
		this.moneyAvailable = (pointsAvailable/100);
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
	 * method that convert money in points
	 */
	public static int moneyInPoints(double money){
		int total =  (int) (money*100);
		return total;
	}
	/*
	 * Method to check if the card is equal to another card
	 */
	@Override
	public boolean equals(Cards card) {
		if((this.getType() == card.getType()) &&(this.getAccNb() == card.getAccNb()) && (this.getCardNumber() == card.getCardNumber()) && (this.getPointsAvailable() == card.getPointsAvailable())&&(this.getMoneyAvailable() == card.getMoneyAvailable())){
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
	public String getEmail() {
		return this.accNb;
	}
	public void setEmail(String email) {
		this.accNb = email;
	}
	@Override
	public int getCardNumber() {
		return this.cardNumber;
	}
	@Override
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getPointsAvailable() {
		return pointsAvailable;
	}
	public void setPointsAvailable(int pointsAvailable) {
		this.pointsAvailable = pointsAvailable;
	}
	@Override
	public double getMoneyAvailable() {
		return this.moneyAvailable;
	}
	@Override
	public void setMoneyAvailable(double moneyAvailable) {
		if((pointsAvailable/100) == moneyAvailable)
			this.moneyAvailable=moneyAvailable;
		else {
			this.moneyAvailable=moneyAvailable;
			moneyAvailable=(pointsAvailable/100);
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
	public int getAccNb() {
		return 0;
	}

	//not used in this case
	@Override
	public void setAccNb(int accNb) {
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

}

