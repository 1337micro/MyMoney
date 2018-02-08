//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Description: Class testing the methods for the cards feature.
//--------------------------------------------------------
package test;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import src.MyCards;
import src.Cards;
import src.Debit;
import src.Credit;
import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;

public class MyCardsTest {
	Credit creditcard = new Credit();
	Debit debitcard = new Debit();
	
	//testing the constructor, setters and getters for the credit cards
	@Test
	public void testDefaultConstructorCredit() {
		assertEquals("Verifying card type",Cards.CardType.CREDIT, creditcard.getType());
		assertEquals("Verifying account number",0, creditcard.getAccNb());
		assertEquals("Verifying card number",0,creditcard.getCardNumber());
		assertEquals("Verifying card limit",0, creditcard.getLimit());
		assertEquals("Verifying money spent",0, creditcard.getMoneySpent());
		assertEquals("Verifying money available",0, creditcard.getMoneyAvailable());
	}
	@Test
	public void testCreditConstructor() {
		Credit cc= new Credit(Cards.CardType.CREDIT, 1234,12345678, 200.00, 500.00);
		assertEquals("Verifying card type",Cards.CardType.CREDIT, cc.getType());
		assertEquals("Verifying account number",1234, creditcard.getAccNb());
		assertEquals("Verifying card number",12345678,creditcard.getCardNumber());
		assertEquals("Verifying card limit",500.00, creditcard.getLimit());
		assertEquals("Verifying money spent",200.00, creditcard.getMoneySpent());
		assertEquals("Verifying money available",300.00, creditcard.getMoneyAvailable());
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
		assertEquals("Verifying money available",200,creditcard.getMoneyAvailable());
	}
	@Test
	public void testCreditSetGetMoneySpent() {
		creditcard.setMoneySpent(200);
		assertEquals("Verifying money spent",200, creditcard.getMoneySpent());
	}
	@Test
	public void testCreditSetGetLimit() {
		creditcard.setLimit(500);
		assertEquals("Verifying card limit",500, creditcard.getLimit());
	}
	//testing constructors, setters and getters for the debit cards
	@Test
	public void testDefaultDebitConstructor() {
		assertEquals("Verifying card type",Cards.CardType.DEBIT, debitcard.getType());
		assertEquals("Verifying account number",0,debitcard.getAccNb());
		assertEquals("Verifying card number",0,debitcard.getCardNumber());
		assertEquals("Verifying money available",0.0, debitcard.getMoneyAvailable());
	}
	@Test
	public void testDebitConstructor() {
		Debit dc = new Debit(Cards.CardType.DEBIT, 1234, 12345678, 500.00);
		assertEquals("Verifying card type",Cards.CardType.DEBIT, debitcard.getType());
		assertEquals("Verifying account number",1234,debitcard.getAccNb());
		assertEquals("Verifying card number",12345678,debitcard.getCardNumber());
		assertEquals("Verifying money available",500.0, debitcard.getMoneyAvailable());
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
		assertEquals("Verifying money available",200.00, debitcard.getMoneyAvailable());
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
		Debit expected = new Debit(Cards.CardType.DEBIT, 1234, 12345678, 200.00);
		assertEquals("Verifying that the debit card has been added", true, cardsList.getCards().contains(expected));
	}
	@Test
	public void testAddCardCredit() {
		cardsList.addCard(Cards.CardType.CREDIT, 1234, 12345678,500.00, 200.00);
		Credit expected = new Credit(Cards.CardType.CREDIT, 1234, 12345678,500.00, 200.00);
		assertEquals("Verifying that the credit card has been added", true, cardsList.getCards().contains(expected));
	}
	//test remove method
}
