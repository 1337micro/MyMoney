package src;

import java.util.ArrayList;
import java.util.List;


public class MyCards {

	public List<Cards> getCards() {
		return cards;
	}

	private List <Cards> cards;
	
	

	
	/*
	 * create an arraylist to store the cards as a sequence
	 */
	public MyCards() {
		this.cards= new ArrayList<>();
	}
	
	/*
	 * method to add a card to the arraylist, debit type
	 */
	public void addCard(Cards.CardType type, int accNb, int cardNumber, double moneyCurrent) {
		if(type == Cards.CardType.DEBIT) {
			Debit debit= new Debit(type, accNb, cardNumber, moneyCurrent);
			cards.add(debit);
		}
	}
	/*
	 * method to add a card to the arrylist credit type
	 */
	public void addCard(Cards.CardType type, int accNb, int cardNumber, double limit, double moneyCurrent) {
		if(type == Cards.CardType.CREDIT) {
			Credit credit = new Credit(type, accNb, cardNumber, limit, moneyCurrent);
			cards.add(credit);
			}
	}
	/*
	 * method to remove a card from the list
	 */
	public void removeCard(int card) {
		cards.remove(card);
	}
	
	
	
	
}

