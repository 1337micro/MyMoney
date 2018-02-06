//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001075
//Description: implements the interface cards and serves the purpose of implementing the 
//              specifics of the cards of type DEBIT
//--------------------------------------------------------

package src;

public class Debit implements Cards{
//extends Cards{
	CardType type;
	int accNb;
	int cardNumber;
	double moneyAvailable;
	
	public Debit() {
		type=CardType.DEBIT;
		accNb = 0;
		cardNumber=0;
		moneyAvailable=0.0;
	}
	/**
	 * Constructor for debit cards
	 * @param String type
	 * @param int cardNumber
	 * @param double moneyCurrent
	 */
	public Debit(CardType cardType, int accNb, int cardNumber, double moneyAvailable) {
		this.type=CardType.DEBIT;
		this.accNb = accNb;
		this.cardNumber=cardNumber;
		this.moneyAvailable=moneyAvailable;
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
	public int getCardNumber() {
		return cardNumber;
	}
	@Override
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
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
	public double getMoneyAvailable() {
		return this.moneyAvailable;
	}
	@Override
	public void setMoneyAvailable(double moneyAvailable) {
		this.moneyAvailable = moneyAvailable;
		
	}
	
	// not used in debit
	@Override
	public double getMoneySpent() {
		
		return 0;
	}
	@Override
	public void setMoneySpent(double moneySpent) {
		
		
	}
	@Override
	public void setLimit(double limit) {
		
		
	}
	@Override
	public double getLimit() {
		
		return 0;
	}
	
	
	
	
	
	
}
