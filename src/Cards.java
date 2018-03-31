//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001075
//Iteration 2: Noemi Lemonnier 40001085
//Description: Cards.java is an interface used by Debit, Credit, LoyaltyCards classes
//in order to link the cards since there are different types but they share some features. 
//--------------------------------------------------------

package src;

import java.util.List;

public interface Cards {


	enum CardType{
		DEBIT("DEBIT"), CREDIT("CREDIT"), LOYALTY("LOYALTYCARD");
		String cardtype;
		CardType(String cardtype){
			this.cardtype = cardtype;
		}
		public String toString(){
			return this.cardtype;
		}

	}
	String listFormat();
	CardType getType();
	void setType(CardType type);
	int getAccNb();
	void setAccNb(int accNb) ;
	int getCardNumber();
	void setCardNumber(int cardNumber);
	double getMoneyAvailable();
	void setMoneyAvailable(double moneyAvailable);
	double getMoneySpent();
	void setMoneySpent(double moneySpent);
	void setLimit(double limit);
	double getLimit();
	String getEmail();
	void setEmail(String email);
	int getPointsAvailable();
	void setPointsAvailable(int pointsAvailable);
	boolean equals(Cards card);
	List<String> getList();
	void setList(List<String> list);
	String getStringList();
	
}

