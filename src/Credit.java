package src;

public class Credit extends Cards{

	double limit;
	double moneyOwed;

	public Credit() {
		type=CardType.CREDIT;
		accNb = 0;
		cardNumber=0;
		limit=0;
		moneyCurrent=0;
		moneyOwed=moneyCurrent-limit;
	}

	public Credit(Cards.CardType type, int accNb, int cardNumber, double moneyCurrent, double limit) {
		this.type=CardType.CREDIT;
		this.accNb = accNb;
		this.cardNumber=cardNumber;
		this.limit =limit;
		this.moneyCurrent=moneyCurrent;
		this.moneyOwed=moneyCurrent-limit;
	}

	//need to create setters and getter for limit and moneyOwed. the others can be from the super class

	//making setters
	/**
	 * setters for the limit of the credit card
	 * @param int limit
	 */
	public void setLimit(double limit) {
		this.limit=limit;
	}
	/**
	 * setters for the money currently owed by the card owner
	 * @param double moneyOwed
	 */
	public void setMoneyOwed(double moneyOwed) {
		if(limit-moneyOwed==moneyCurrent)
			this.moneyOwed=moneyOwed;
		else {
			this.moneyOwed=moneyOwed;
			moneyCurrent=limit-moneyOwed;
		} 

	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getAccNb() {
		return accNb;
	}

	public void setAccNb(int accNb) {
		this.accNb = accNb;
	}

	//making getters
	/**
	 * getter for the gredit card limit
	 * @return int of the credit card limit
	 */
	public double getLimit() {
		return limit;
	}
	/**
	 * getter for the money owed by the owner of the card
	 * @return double of the amount of money owed by the owner of the card
	 */
	public double getMoneyOwed() {
		return moneyOwed;
	}
}

