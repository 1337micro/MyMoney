//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Description: Class testing the methods for the cards feature.
//--------------------------------------------------------
package src;

import  org.junit.Test;
import static org.junit.Assert.*;
import src.MyCards;
import src.Cards;
import src.Debit;
import src.Credit;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;



public class MyCardsTest {
	Credit creditcard = new Credit();
	Debit debitcard = new Debit();
	Credit cc= new Credit(Cards.CardType.CREDIT,1234,12345678,200.00,500.00);
	//testing the constructor, setters and getters for the credit cards
	@Test
	public void testDefaultConstructorCredit() {
		assertEquals("Verifying card type",Cards.CardType.CREDIT, creditcard.getType());
		assertEquals("Verifying account number",0, creditcard.getAccNb());
		assertEquals("Verifying card number",0,creditcard.getCardNumber());
		assertEquals("Verifying card limit",0, creditcard.getLimit(),0);
		assertEquals("Verifying money spent",0, creditcard.getMoneySpent(),0);
		assertEquals("Verifying money available",0, creditcard.getMoneyAvailable(),0);
	}
	@Test
	public void testCreditConstructor() {		
		assertEquals("Verifying card type",Cards.CardType.CREDIT, cc.getType());
		assertEquals("Verifying account number",1234,cc.getAccNb());
		assertEquals("Verifying card number",12345678,cc.getCardNumber());
		assertEquals("Verifying card limit",500.00, cc.getLimit(),0);
		assertEquals("Verifying money spent",200.00, cc.getMoneySpent(),0);
		assertEquals("Verifying money available",300.00, cc.getMoneyAvailable(),0);
	}
	@Test
	public void testCreditSetGetType() {
		creditcard.setType(Cards.CardType.CREDIT);
		assertEquals("Verifying card type",Cards.CardType.CREDIT,creditcard.getType());
		
	}
	@Test
	public void testCreditSetGetCardNum() {
		creditcard.setCardNumber(12345678);
		assertEquals("Verifying card number",12345678,creditcard.getCardNumber());
	}
	@Test
	public void testCreditSetGetAccNum() {
		creditcard.setAccNb(1234);
		assertEquals("Verifying account number",1234, creditcard.getAccNb());
	}
	@Test
	public void testCreditSetGetMoneyAvailable() {
		creditcard.setMoneyAvailable(200);
		assertEquals("Verifying money available",200,creditcard.getMoneyAvailable(),0);
	}
	@Test
	public void testCreditSetGetMoneySpent() {
		creditcard.setMoneySpent(200);
		assertEquals("Verifying money spent",200, creditcard.getMoneySpent(),0);
	}
	@Test
	public void testCreditSetGetLimit() {
		creditcard.setLimit(500);
		assertEquals("Verifying card limit",500, creditcard.getLimit(),0);
	}
	//testing constructors, setters and getters for the debit cards
	@Test
	public void testDefaultDebitConstructor() {
		assertEquals("Verifying card type",Cards.CardType.DEBIT, debitcard.getType());
		assertEquals("Verifying account number",0,debitcard.getAccNb());
		assertEquals("Verifying card number",0,debitcard.getCardNumber());
		assertEquals("Verifying money available",0.0, debitcard.getMoneyAvailable(),0);
	}
	@Test
	public void testDebitConstructor() {
		Debit dc = new Debit(Cards.CardType.DEBIT, 1234, 12345678, 500.00);
		assertEquals("Verifying card type",Cards.CardType.DEBIT, debitcard.getType());
		assertEquals("Verifying account number",1234,dc.getAccNb());
		assertEquals("Verifying card number",12345678,dc.getCardNumber());
		assertEquals("Verifying money available",500.0, dc.getMoneyAvailable(),0);
	}
	@Test
	public void testDebitSetGetType() {
		debitcard.setType(Cards.CardType.DEBIT);
		assertEquals("Verifying card type",Cards.CardType.DEBIT, debitcard.getType());
	}
	@Test
	public void testDebitSetGetAccNb() {
		debitcard.setAccNb(1234);
		assertEquals("Verifying account number",1234, debitcard.getAccNb());
	}
	@Test
	public void testDebitSetGetCardNumber() {
		debitcard.setCardNumber(12345678);
		assertEquals("Verifying card number",12345678, debitcard.getCardNumber());
	}
	@Test
	public void testDebitSetGetMoneyAvailable() {
		debitcard.setMoneyAvailable(200.00);
		assertEquals("Verifying money available",200.00, debitcard.getMoneyAvailable(),0);
	}
	
	//tests for the MyCards
	MyCards cardsList = new MyCards();
	//testing if list is created when starting an instance of MyCards
	@Test
	public void testMyCardsConstructorGetter() {
		List <Cards> expected =  new ArrayList<>();
		assertEquals("Verifying the array listis created",expected,cardsList.getCards() );
	}
	@Test
	public void testAddCardDebit() {
		cardsList.addCard(Cards.CardType.DEBIT, 1234, 12345678, 200.00);
		List <Cards> cardL=cardsList.getCards();
		int index = MyCards.getCardFromAccountNumber(12345678,cardL);
		Cards card = cardsList.get(index);	
		assertEquals("Verifying that the debit card has been added", true, cardsList.getCards().contains(card));
	}
	@Test
	public void testAddCardCredit() {
		cardsList.addCard(Cards.CardType.CREDIT, 1234, 12345678,500.00, 200.00);
		List <Cards> cardL=cardsList.getCards();
		int index = MyCards.getCardFromAccountNumber(12345678,cardL);
		Cards card = cardsList.get(index);	
		assertEquals("Verifying that the credit card has been added",true, cardsList.getCards().contains(card));
	}
	@Test
	public void testRemoveCard() {
		cardsList.addCard(Cards.CardType.DEBIT, 1234, 12345678, 500);
		List <Cards> cardL=cardsList.getCards();
		int index = MyCards.getCardFromAccountNumber(12345678,cardL);
		Cards card = cardsList.get(index);
		cardsList.removeCard(index);
		assertEquals("Verifying if a card is removed", false, cardsList.getCards().contains(card));
		
	}
	@Test
	public void testGetCardFromAccNum() {
		cardsList.addCard(Cards.CardType.DEBIT, 1234, 12345678, 500);
		cardsList.addCard(Cards.CardType.DEBIT, 6548, 98563254, 800);
		List <Cards> cardL=cardsList.getCards();
		int index = MyCards.getCardFromAccountNumber(12345678,cardL);
		assertEquals("Verifying that the index of the card is correct", 0, MyCards.getCardFromAccountNumber(12345678, cardL));
	}
}
