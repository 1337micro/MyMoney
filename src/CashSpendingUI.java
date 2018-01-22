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
    private JTextField amountSpentOnGroceries = new JTextField(FIELD_SIZE);
    private JTextField amountSpentOnRent = new JTextField(FIELD_SIZE);
    private JTextField amountSpentOnTuition = new JTextField(FIELD_SIZE);
    private JTextField amountSpentOnTaxes = new JTextField(FIELD_SIZE);

    //list of the JTextField fields above, added in the constructor
    private  ArrayList<JTextField> listOfExpenseFields = new ArrayList<>();
    public CashSpendingUI(){
        addJTextFieldToListAndSetNotEditable(listOfExpenseFields, amountSpentOnGroceries, amountSpentOnRent, amountSpentOnTuition, amountSpentOnTaxes);
    }
    final Object[] typesOfExpenditure = {GROCERIES, RENT, TUITION, TAXES};




    private void addJTextFieldToListAndSetNotEditable(List<JTextField> list, JTextField... jTextField){
        for(JTextField field: jTextField){
            setNotEditable(field);
            list.add(field);
        }
    }
    private void setNotEditable(JTextField textField){
        textField.setEditable(false);
    }
    private void updateExpensesFields(){
        for(Object expenditure: typesOfExpenditure){
            CashSpending.ExpenditureType expenditureType = (CashSpending.ExpenditureType) expenditure;

            if(Main.getCashSpending().getExpensesOfType(expenditureType) != null &&
                    Main.getCashSpending().getExpensesOfType(expenditureType).size() != 0){
                Double sumOfExpendituresOfThisType = Utilities.sumListOfNumbers(Main.getCashSpending().getExpensesOfType(expenditureType));
                switch (expenditureType){
                    case GROCERIES:
                        amountSpentOnGroceries.setText(sumOfExpendituresOfThisType.toString());
                        break;
                    case RENT:
                        amountSpentOnRent.setText(sumOfExpendituresOfThisType.toString());
                        break;
                    case TUITION:
                        amountSpentOnTuition.setText(sumOfExpendituresOfThisType.toString());
                        break;
                    case TAXES:
                        amountSpentOnTaxes.setText(sumOfExpendituresOfThisType.toString());
                        break;
                }
            }


        }
    }

    private void buildCashSpendingDisplayPanel(){
        JTextField groceries = new JTextField(FIELD_SIZE);
        JTextField rent = new JTextField(FIELD_SIZE);
        JTextField tuition = new JTextField(FIELD_SIZE);
        JTextField taxes = new JTextField(FIELD_SIZE);
        List<JTextField> listOfExpenditureTypes = new ArrayList<>(4);
        addJTextFieldToListAndSetNotEditable(listOfExpenditureTypes, groceries, rent, tuition, taxes);


        groceries.setText(GROCERIES.toString());
        rent.setText(RENT.toString());
        tuition.setText(TUITION.toString());
        taxes.setText(TAXES.toString());





        thePanel = new JPanel();
        thePanel.setLayout(new BoxLayout(thePanel, BoxLayout.LINE_AXIS));


        for(Object expenditure: typesOfExpenditure){
            JPanel innerPanel = new JPanel();
            //String expenditureString = (expenditure).toString();
            switch ((CashSpending.ExpenditureType) expenditure){
                case GROCERIES:
                    innerPanel.add(groceries);
                    innerPanel.add(amountSpentOnGroceries);
                    break;
                case RENT:
                    innerPanel.add(rent);
                    innerPanel.add(amountSpentOnRent);
                    break;
                case TUITION:
                    innerPanel.add(tuition);
                    innerPanel.add(amountSpentOnTuition);
                    break;
                case TAXES:
                    innerPanel.add(taxes);
                    innerPanel.add(amountSpentOnTaxes);
                    break;
            }
            thePanel.add(innerPanel);
        }
        JButton addExpense = new JButton("Add an expense");
        addExpense.addActionListener(new AddExpenseListener());
        thePanel.add(addExpense);

        thePanel.setVisible(false);



    }
    public void addPanelToLayout(JPanel jPanel, ApplicationLayout applicationLayout){
        applicationLayout.add(jPanel);
    }
    public void rmvPanelToLayout(JPanel jPanel, ApplicationLayout applicationLayout){
        applicationLayout.remove(jPanel);
    }
    public void addComponentsToPanel(JPanel jPanel){
        JOptionPane.showInputDialog("test");
    }


    private void showErrorDialog(){

    }


    public Double showCashSpendingAmountOfMoneyDialog(CashSpending cashSpending,
                                                       CashSpending.ExpenditureType expenditureType){
        String inputAmountSpent = JOptionPane
                .showInputDialog("How much money did you spend on "+ expenditureType.toString().toLowerCase() + " ?");

        double amountSpent = 0;
        try {
            amountSpent = new Double(inputAmountSpent);
        } catch (NumberFormatException e){
            e.printStackTrace();
            showErrorDialog();
        }
       // cashSpending.addExpense(expenditureType, amountSpent);
        return amountSpent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Cash spending selected");

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
                    typesOfExpenditure,
                    0);
            return (CashSpending.ExpenditureType) typesOfExpenditure[selection];

        }
        private Double handleSelectionOfExpenditureType(CashSpending cashSpending, CashSpending.ExpenditureType selection){
            //showCashSpendingAmountOfMoneyDialog(cashSpending selection)
            System.out.print(selection.toString());


            Double amountSpent;
            switch (selection){
                case GROCERIES:
                    amountSpent = showCashSpendingAmountOfMoneyDialog(cashSpending, GROCERIES);
                    break;
                case RENT:
                    amountSpent = showCashSpendingAmountOfMoneyDialog(cashSpending, RENT);
                    break;
                case TUITION:
                    amountSpent =showCashSpendingAmountOfMoneyDialog(cashSpending, TUITION);
                    break;
                case TAXES:
                    amountSpent =showCashSpendingAmountOfMoneyDialog(cashSpending, TAXES);
                    break;
                default:
                    amountSpent = 0.0;
                    throw new RuntimeException("Invalid amount entered");
            }
            return amountSpent;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            final CashSpending.ExpenditureType expenditureType = showCashSpendingExpenditureDialog(Main.getApplicationLayout());
            final double amountSpent = handleSelectionOfExpenditureType(Main.getCashSpending(), expenditureType);

            Main.getCashSpending().addExpense(expenditureType, amountSpent);

            updateExpensesFields();

        }
    }
}
