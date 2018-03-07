//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001075
//Iteration 2: Noemi Lemonnier 40001085
//Description: create an interface for the cards in order to 
//             link the cards since there are different types but they share some features. 
//--------------------------------------------------------

package src;

import java.util.List;

public interface Cards {


	public enum CardType{
		DEBIT("DEBIT"), CREDIT("CREDIT"), LOYALTY("LOYALTYCARD");
		String cardtype;
		private CardType(String cardtype){
			this.cardtype = cardtype;
		}
		public String toString(){
			return this.cardtype;
		}

	}
	public String listFormat();
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
	public String getEmail();
	public void setEmail(String email);
	public int getPointsAvailable();
	public void setPointsAvailable(int pointsAvailable);
	public boolean equals(Cards card);
	public List<String> getList();
	public void setList(List<String> list);
	public String getStringList();
	
}
