//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001075
//Iteration 2: Noémi Lemonnier 40001075
//Description: implements the interface cards and serves the purpose of implementing the 
//              specifics of the cards of type CREDIT
//--------------------------------------------------------


package src;


public class Credit implements Cards{
	//extends Cards{
	CardType type;
	int accNb;
	int cardNumber;
	double limit;
	double moneySpent;
	double moneyAvailable;

	public Credit() {
		type=CardType.CREDIT;
		accNb = 0;
		cardNumber=0;
		limit=0;
		moneySpent=0;
		moneyAvailable=limit-moneySpent;
	}

	public Credit(CardType cardType, int accNb, int d, double moneySpent, double limit) {
		this.type=CardType.CREDIT;
		this.accNb = accNb;
		this.cardNumber=d;
		this.limit =limit;
		this.moneySpent=moneySpent;
		this.moneyAvailable=limit-moneySpent;
	}

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
