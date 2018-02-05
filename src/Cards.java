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