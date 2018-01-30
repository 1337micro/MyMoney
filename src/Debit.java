package src;

public class Debit extends Cards{
	public Debit() {
		type=CardType.DEBIT;
		cardNumber=0;
		moneyCurrent=0;
	}
	/**
	 * Constructor for debit cards
	 * @param String type
	 * @param int cardNumber
	 * @param double moneyCurrent
	 */
	public Debit(CardType type, int cardNumber, double moneyCurrent) {
		this.type=CardType.CREDIT;
		this.cardNumber=cardNumber;
		this.moneyCurrent=moneyCurrent;
	}
	
	
	//I did not make getters and setters since they can use the ones 
	//from the super class. Note that the account number of the person
	//will be the card number of the debit card
	
	
}
