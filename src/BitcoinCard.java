package src;

import java.util.ArrayList;
import java.util.List;

import static src.Cards.CardType.BITCOIN;

public class BitcoinCard implements Cards {

    public CardType type;
    int accNb;
    int cardNumber;
    double limit;
    double moneySpent;
    double moneyAvailable;
    List<String> list;

    public BitcoinCard() {
        type=CardType.BITCOIN;
        accNb = 234235234;
        cardNumber=535999993;
        limit=15000;
        moneySpent=0;
        moneyAvailable=limit-moneySpent;
        this.list = new ArrayList<>();
    }
    public BitcoinCard(CardType cardType, int accNb, int d, double moneySpent, double limit) {
        this.type=CardType.BITCOIN;
        this.accNb = accNb;
        this.cardNumber=d;
        this.limit =limit;
        this.moneySpent=moneySpent;
        this.moneyAvailable=limit-moneySpent;
        this.list = new ArrayList<>();
    }
    @Override
    public boolean equals(Cards card) {
        if((this.getType() == card.getType()) &&(this.getAccNb() == card.getAccNb()) && (this.getCardNumber() == card.getCardNumber()) &&(this.getLimit() == card.getLimit()) && (this.getMoneySpent() == card.getMoneySpent())){
            return true;
        }
        return false;
    }

    /*
     * method to get each string of their list
     */
    @Override
    public String getStringList() {
        String tmp = "";
        for(int i=0; i< this.getList().size(); i++){
            tmp+= this.getList().get(i);
        }
        return tmp;
    }
    public String listFormat(){
        String tmp="";
        for(int i =0 ; i< this.getList().size(); i++){
            tmp+=tmp+ this.getList().get(i) +"\n";
        }
        return tmp;
    }

    /*
     * Getters and setters for the attributes
     * (non-Javadoc)
     * @see src.Cards#getType()
     */
    @Override
    public CardType getType() {
        return this.type;
    }
    @Override
    public void setType(CardType type) {
        this.type = type;

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
    public int getCardNumber() {
        return cardNumber;
    }
    @Override
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    @Override
    public double getMoneySpent() {
        return moneySpent;
    }
    @Override
    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }
    @Override
    public void setLimit(double limit) {
        this.limit=limit;
    }
    @Override
    public double getLimit() {
        return limit;
    }

    @Override
    public String getEmail() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setEmail(String email) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPointsAvailable() {
        return 0;
    }

    @Override
    public void setPointsAvailable(int pointsAvailable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getMoneyAvailable() {
        return moneyAvailable;
    }
    @Override
    public void setMoneyAvailable(double moneyAvailable) {
        if(limit-moneySpent==moneyAvailable)
            this.moneyAvailable=moneyAvailable;
        else {
            this.moneyAvailable=moneyAvailable;
            moneyAvailable=limit-moneySpent;
        }
    }
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
