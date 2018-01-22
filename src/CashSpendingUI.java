package src;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static src.CashSpending.ExpenditureType.*;
public class CashSpendingUI implements ActionListener {
    JPanel thePanel;
    private final int FIELD_SIZE = 10;

    //list of the JTextField fields above, added in the constructor
    private  ArrayList<JTextField> listOfMoneySpentFields = new ArrayList<>();
    public CashSpendingUI(){
        addJTextFieldToListAndSetNotEditable(listOfMoneySpentFields);//add n jtextfields to listOfMoneySpentFields; where n is numbers of Expidenture Types
    }


    private void addJTextFieldToListAndSetNotEditable(List<JTextField> list, JTextField... jTextField){
        //no fields supplied, use ordering from enum type
        for(int i =0; i<CashSpending.ExpenditureType.values().length; i++) {
            JTextField jText = new JTextField(FIELD_SIZE);
            jText.setEditable(false);
            list.add(jText);
        }
    }

    private void updateExpensesFields(){
        for(int i = 0; i<CashSpending.ExpenditureType.values().length; i++){
            CashSpending.ExpenditureType expenditureType = CashSpending.ExpenditureType.values()[i];

            if(Main.getCashSpending().getExpensesOfType(expenditureType) != null &&
                    Main.getCashSpending().getExpensesOfType(expenditureType).size() != 0){
                Double sumOfExpendituresOfThisType = Utilities.sumListOfNumbers(Main.getCashSpending().getExpensesOfType(expenditureType));
                listOfMoneySpentFields.get(i).setText(sumOfExpendituresOfThisType.toString());
            }
        }
    }
    private void buildCashSpendingDisplayPanel(){
        List<JTextField> listOfExpenditureTypes = new ArrayList<>(CashSpending.ExpenditureType.values().length);
        addJTextFieldToListAndSetNotEditable(listOfExpenditureTypes);

        for(int i =0; i<listOfExpenditureTypes.size(); i++) {
            JTextField expenditureType =  listOfExpenditureTypes.get(i);
            expenditureType.setText(CashSpending.ExpenditureType.values()[i].toString());
        }

        thePanel = new JPanel();
        thePanel.setLayout(new BoxLayout(thePanel, BoxLayout.LINE_AXIS));

        for(int i =0; i<listOfExpenditureTypes.size(); i++) {
            JPanel innerPanel = new JPanel();
            innerPanel.add(listOfExpenditureTypes.get(i));
            innerPanel.add(listOfMoneySpentFields.get(i));
            thePanel.add(innerPanel);
        }
        JButton addExpense = new JButton(Constants.BUTTON_ADD_EXPENSE);
        addExpense.addActionListener(new AddExpenseListener());
        thePanel.add(addExpense);
        thePanel.setVisible(false);
    }
    public void addPanelToLayout(JPanel jPanel, ApplicationLayout applicationLayout){
        applicationLayout.add(jPanel);
    }
    //TODO make error dialog when input makes no sense
    private void showErrorDialog(){

    }


    public Double showCashSpendingAmountOfMoneyDialog(CashSpending.ExpenditureType expenditureType){
        String inputAmountSpent = JOptionPane
                .showInputDialog("How much money did you spend on "+ expenditureType.toString().toLowerCase() + " ?");

        double amountSpent = 0;
        try {
            amountSpent = new Double(inputAmountSpent);
        } catch (NumberFormatException e){
            e.printStackTrace();
            showErrorDialog();
        }

        return amountSpent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(thePanel == null) {
            buildCashSpendingDisplayPanel();
            addPanelToLayout(thePanel, Main.getApplicationLayout());
        }

        if (thePanel.isVisible()){
            thePanel.setVisible(false);
        } else {
            thePanel.setVisible(true);
        }
    }

    private class AddExpenseListener implements ActionListener {
        private CashSpending.ExpenditureType showCashSpendingExpenditureDialog(ApplicationLayout applicationLayout) {
            final int selection = JOptionPane.showOptionDialog(applicationLayout,"Choose your Expenditure Type: ",
                    "Type of Expense? ",
                    JOptionPane.OK_CANCEL_OPTION,
                    0,
                    null,
                    CashSpending.ExpenditureType.values(),
                    0);
            return CashSpending.ExpenditureType.values()[selection];

        }
        private Double handleSelectionOfExpenditureType(CashSpending.ExpenditureType selection){
            Double amountSpent;
            switch (selection){
                case GROCERIES:
                    amountSpent = showCashSpendingAmountOfMoneyDialog(GROCERIES);
                    break;
                case RENT:
                    amountSpent = showCashSpendingAmountOfMoneyDialog(RENT);
                    break;
                case TUITION:
                    amountSpent =showCashSpendingAmountOfMoneyDialog(TUITION);
                    break;
                case TAXES:
                    amountSpent =showCashSpendingAmountOfMoneyDialog(TAXES);
                    break;
                default:
                    amountSpent = 0.0;
            }
            return amountSpent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            final CashSpending.ExpenditureType expenditureType = showCashSpendingExpenditureDialog(Main.getApplicationLayout());
            final double amountSpent = handleSelectionOfExpenditureType(expenditureType);

            Main.getCashSpending().addExpense(expenditureType, amountSpent);

            updateExpensesFields();

        }
    }
}
