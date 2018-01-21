package src;

public class Cards {

	String type;
	int cardNumber;
	double moneyCurrent;
	
	public Cards() {
		type="debit";
		moneyCurrent=0;
		cardNumber=0;
	}
	/**
	 * getter for type
	 * @return type of card
	 */
	public String getType() {
		return type;
	}
	/**
	 * getter for card number
	 * @return card number which is the same as account number for debit cards
	 */
	public int getCardNumber() {
		return cardNumber;
	}
	/**
	 * getter for the current amount of money available in the card
	 * @return money currently available
	 */
	public double getMoneyCurrent() {
		return moneyCurrent;
	}
	/**
	 * setter for the type of card
	 * @param String type
	 */
	public void setType(String type) {
		this.type=type;
	}
	/**
	 * setter for the card number
	 * @param int cardNumber
	 */
	public void setCardNumber(int cardNumber) {
		this.cardNumber=cardNumber;
	}
	/**
	 * setter for the money currently available
	 * @param double moneyCurrent
	 */
	public void setMoneyCurrent(double moneyCurrent) {
		this.moneyCurrent=moneyCurrent;
	}
	
}
