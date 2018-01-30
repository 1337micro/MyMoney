package src;

public class Credit extends Cards{
	int limit;
	double moneyOwed;
	
	public Credit() {
		type=CardType.CREDIT;
		cardNumber=0;
		limit=0;
		moneyCurrent=0;
		moneyOwed=limit-moneyCurrent;
	}
	
	public Credit(CardType type, int cardNumber, int limit, double moneyCurrent, double moneyOwed) {
		this.type=CardType.CREDIT;
		this.cardNumber=cardNumber;
		this.limit =limit;
		this.moneyCurrent=moneyCurrent;
		this.moneyOwed=moneyOwed;
	}
	
	
	//need to create setters and getter for limit and moneyOwed. the others can be from the super class
	
	//making setters
	/**
	 * setters for the limit of the credit card
	 * @param int limit
	 */
	public void setLimit(int limit) {
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
	
	//making getters
	/**
	 * getter for the gredit card limit
	 * @return int of the credit card limit
	 */
	public int getLimit() {
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
