package test;

import org.junit.Assert;
import org.junit.Test;
import src.BitcoinCard;
import src.Cards;

import static src.Cards.CardType.BITCOIN;

public class BitcoinCardTest {
    Cards.CardType cardType = BITCOIN;
    int accountNum = 1;
    int cardNum = 2;
    double moneySpent = 15;
    double limit = 30;
    public BitcoinCard createCard(){
        BitcoinCard longConstructor = new BitcoinCard(BITCOIN,accountNum, cardNum, moneySpent, limit);
        return longConstructor;
    }

    @Test
    public void BitcoinCardTest(){
        BitcoinCard longConstructor = createCard();
        Assert.assertEquals(longConstructor.getCardNumber(), cardNum);
        Assert.assertEquals(longConstructor.type, cardType);
        Assert.assertEquals(longConstructor.getAccNb(), accountNum);
        Assert.assertEquals(longConstructor.getMoneySpent(), moneySpent, 0.001);
        Assert.assertEquals(longConstructor.getLimit(), limit, 0.001);
    }

    @Test
    public void equalsTest(){
        Assert.assertTrue(createCard().equals(createCard()));
    }

    @Test
    public void setMoneyAvailableTest(){
        BitcoinCard longConstructor = createCard();
        longConstructor.setMoneyAvailable(15);
        Assert.assertEquals(longConstructor.getMoneyAvailable(), 15.0, 0.0);
    }


}
