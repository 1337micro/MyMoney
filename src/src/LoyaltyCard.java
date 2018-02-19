package src;

public class LoyaltyCard implements Cards{
	CardType type;
	String accNb;
	int cardNumber;
	int pointsAvailable;
	double moneyAvailable;


	//default constructor
	public LoyaltyCard() {
		type=CardType.LOYALTY;
		accNb = "";
		cardNumber=0;
		pointsAvailable=0;
		moneyAvailable=0;
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
