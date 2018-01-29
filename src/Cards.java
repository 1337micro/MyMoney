package src;

public class Cards {
	public enum CardType{
		DEBIT("debit"), CREDIT("credit");
		String cardtype;
		private CardType(String cardtype){
			this.cardtype = cardtype;
		}
		public String toString(){
			return this.cardtype;
		}
	}
	protected CardType type;
	protected int cardNumber;
	protected double moneyCurrent;
	
	public Cards() {
		type=CardType.DEBIT;
		moneyCurrent=0;
		cardNumber=0;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getMoneyCurrent() {
		return moneyCurrent;
	}

	public void setMoneyCurrent(double moneyCurrent) {
		this.moneyCurrent = moneyCurrent;
	}
}
