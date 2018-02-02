package src;

public class Credit extends Cards{

	double limit;
	double moneySpent;

	public Credit() {
		type=CardType.CREDIT;
		accNb = 0;
		cardNumber=0;
		limit=0;
		moneySpent=0;
		moneyAvailable=limit-moneySpent;
	}

	public Credit(Cards.CardType type, int accNb, int cardNumber, double moneySpent, double limit) {
		this.type=CardType.CREDIT;
		this.accNb = accNb;
		this.cardNumber=cardNumber;
		this.limit =limit;
		this.moneySpent=moneySpent;
		this.moneyAvailable=limit-moneySpent;
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
	 * setting the money available on the credit card
	 * @param moneyAvailable
	 */
	public void setMoneyOwed(double moneyAvailable) {
		if(limit-moneySpent==moneyAvailable)
			this.moneyAvailable=moneyAvailable;
		else {
			this.moneyAvailable=moneyAvailable;
			moneyAvailable=limit-moneySpent;
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
	
	public double getMoneySpent() {
		return moneySpent;
	}

	public void setMoneySpent(double moneySpent) {
		this.moneySpent = moneySpent;
	}

	/**
	 * getter for the money owed by the owner of the card
	 * @return double of the amount of money owed by the owner of the card
	 */
	public double getMoneyAvailable() {
		return moneyAvailable;
	}
}

