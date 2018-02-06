//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001075
//Description: create an interface for the cards in order to 
//             link the cards since there are different types but they share some features. 
//--------------------------------------------------------

package src;

public interface Cards {


	public enum CardType{
		DEBIT("DEBIT"), CREDIT("CREDIT");
		String cardtype;
		private CardType(String cardtype){
			this.cardtype = cardtype;
		}
		public String toString(){
			return this.cardtype;
		}

	}
	public CardType getType();
	public void setType(CardType type);
	public int getAccNb();
	public void setAccNb(int accNb) ;
	public int getCardNumber();
	public void setCardNumber(int cardNumber);
	public double getMoneyAvailable();
	public void setMoneyAvailable(double moneyAvailable);
	public double getMoneySpent();
	public void setMoneySpent(double moneySpent);
	public void setLimit(double limit);
	public double getLimit();




}