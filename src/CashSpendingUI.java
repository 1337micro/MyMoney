package src;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import static src.CashSpending.ExpenditureType.*;
public class CashSpendingUI implements ActionListener {
    JPanel thePanel; // the panel containing all our JTextFields for CashSpending feature
    private final int FIELD_SIZE = 10; // number of columns in our GUI text fields

    //list of the JTextField fields having the total amount spent in a particular category.
    private  ArrayList<JTextField> listOfMoneySpentFields = new ArrayList<>();

    public CashSpendingUI(){
        addJTextFieldToListAndSetNotEditable(listOfMoneySpentFields);//add n jtextfields to listOfMoneySpentFields; where n is numbers of Expenditure Types
    }


    /**Add n jtextfields to list; where n is numbers of Expenditure Types
     * @param list that will be populated by JTextFields. We add a JTextField for each enum in ExpenditureType
     */
    private void addJTextFieldToListAndSetNotEditable(List<JTextField> list){
        //no fields supplied, use ordering from enum type
        for(int i =0; i<CashSpending.ExpenditureType.values().length; i++) {
            JTextField jText = new JTextField(FIELD_SIZE);
            jText.setEditable(false);
            list.add(jText);
        }
    }

    /**Update the listOfMoneySpentFields to reflect the total money spent after a user enters a new expenditure
     *
     */
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

    /** Add the necessary SWING elements to the CashSpending panel named "thePanel"
     *
     */
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

    /**Add any JPanel to our application layout
     *
     * @param jPanel Any SWING jpanel
     * @param applicationLayout the application layout object
     */
    public void addPanelToLayout(JPanel jPanel, ApplicationLayout applicationLayout){
        applicationLayout.add(jPanel);
    }
    //TODO make error dialog when input makes no sense
    private void showErrorDialog(){

    }

    /** Create a popup asking the user to input the amount of money they spent on a particular type of expense
     * @param expenditureType type of expense
     * @return Double; the amount of money spent on this expenditureType
     */
    public Double showCashSpendingAmountOfMoneyDialog(CashSpending.ExpenditureType expenditureType){
        Optional<String> inputAmountSpent = Optional.ofNullable(JOptionPane
                .showInputDialog("How much money did you spend on " + expenditureType.toString().toLowerCase() + " ?"));

        double amountSpent = 0;
        try {
            amountSpent = new Double(inputAmountSpent.orElse("0.0"));
        } catch (NumberFormatException e){
            e.printStackTrace();
            showErrorDialog();
        }

        return amountSpent;
    }

    /**
     * Button listening for a click on the "Cash Spending" button. It will show or hide the CashSpending panel "thePanel"
     * @param e SWING argument
     */
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

    /**
     * A class that will act as a listener for the "AddExpense" button on the CashSpending panel "thePanel" variable
     */
    private class AddExpenseListener implements ActionListener {
        /** Create a popup asking the user to select a particular type of expense
         * @param applicationLayout
         * @return the {@link src.CashSpending.ExpenditureType} that the user selected
         */
        private CashSpending.ExpenditureType showCashSpendingExpenditureDialog(ApplicationLayout applicationLayout) {
            final Integer selection = (JOptionPane.showOptionDialog(applicationLayout,"Choose your Expenditure Type: ",
                    "Type of Expense? ",
                    JOptionPane.OK_CANCEL_OPTION,
                    0,
                    null,
                    CashSpending.ExpenditureType.values(),
                    0));
            if(selection < 0) throw new RuntimeException("User closed the selection window");
            return CashSpending.ExpenditureType.values()[selection];

        }

        /**
         * A method to handle the selection made after showCashSpendingExpenditureDialog was called
         * We ask how much the user spent on the selection type
         * @param selection the type of expenditure that was selected
         * @return
         */
        private Double handleSelectionOfExpenditureType(CashSpending.ExpenditureType selection){
            return showCashSpendingAmountOfMoneyDialog(selection);
        }

        /**
         * "AddExpense" Button listening for a click. It will bring up a dialog prompt asking what type of expenditure
         * @param e SWING argument
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            final CashSpending.ExpenditureType expenditureType = showCashSpendingExpenditureDialog(Main.getApplicationLayout());
            final double amountSpent = handleSelectionOfExpenditureType(expenditureType);
            Main.getCashSpending().addExpense(expenditureType, amountSpent);
            updateExpensesFields();
        }
    }
}
