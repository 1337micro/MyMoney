package src;

public class Debit extends Cards{
	String type;
	int cardNumber;
	double moneyCurrent;
	
	public Debit() {
		type="debit";
		cardNumber=0;
		moneyCurrent=0;
	}
	/**
	 * Constructor for debit cards
	 * @param String type
	 * @param int cardNumber
	 * @param double moneyCurrent
	 */
	public Debit(String type, int cardNumber, double moneyCurrent) {
		this.type=type;
		this.cardNumber=cardNumber;
		this.moneyCurrent=moneyCurrent;
	}
	
	//I did not make getters and setters since they can use the ones 
	//from the super class. Note that the account number of the person
	//will be the card number of the debit card
	
	
}
