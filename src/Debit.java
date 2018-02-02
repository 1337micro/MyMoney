package src;

public class Debit extends Cards{
	public Debit() {
		type=CardType.DEBIT;
		accNb = 0;
		cardNumber=0;
		moneyAvailable=0;
	}
	/**
	 * Constructor for debit cards
	 * @param String type
	 * @param int cardNumber
	 * @param double moneyCurrent
	 */
	public Debit(Cards.CardType type, int accNb, int cardNumber, double moneyCurrent) {
		this.type=CardType.DEBIT;
		this.accNb = accNb;
		this.cardNumber=cardNumber;
		this.moneyAvailable=moneyCurrent;
	}
	
	//I did not make getters and setters since they can use the ones 
	//from the super class. Note that the account number of the person
	//will be the card number of the debit card
	
	
}

