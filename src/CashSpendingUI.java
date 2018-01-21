package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static src.CashSpending.ExpenditureType.*;
public class CashSpendingUI implements ActionListener {
    JPanel thePanel;
    public CashSpendingUI(){

    }
    final Object[] typesOfExpenditure = {CashSpending.ExpenditureType.GROCERIES,
            CashSpending.ExpenditureType.RENT,
            CashSpending.ExpenditureType.TUITION,
            CashSpending.ExpenditureType.TAXES};

    JTextField amountSpentOnGroceries = new JTextField(10);
    JTextField amountSpentOnRent = new JTextField(10);
    JTextField amountSpentOnTuition = new JTextField(10);
    JTextField amountSpentOnTaxes = new JTextField(10);
    private void updateExpensesFields(){
        for(Object expenditure: typesOfExpenditure){
            CashSpending.ExpenditureType expenditureType = (CashSpending.ExpenditureType) expenditure;

            if(Main.getCashSpending().getExpensesOfType(expenditureType) != null &&
                    Main.getCashSpending().getExpensesOfType(expenditureType).size() != 0){
                switch (expenditureType){

                    case GROCERIES:
                        amountSpentOnGroceries.setText(Utilities.sumListOfNumbers(Main.getCashSpending().getExpensesOfType(expenditureType)).toString());
                        break;
                    case RENT:
                        amountSpentOnRent.setText(Utilities.sumListOfNumbers(Main.getCashSpending().getExpensesOfType(expenditureType)).toString());
                        break;
                    case TUITION:
                        amountSpentOnTuition.setText(Utilities.sumListOfNumbers(Main.getCashSpending().getExpensesOfType(expenditureType)).toString());
                        break;
                    case TAXES:
                        amountSpentOnTaxes.setText(Utilities.sumListOfNumbers(Main.getCashSpending().getExpensesOfType(expenditureType)).toString());
                        break;
                }
            }


        }
    }

    private void buildCashSpendingDisplayPanel(){
        JTextField groceries = new JTextField(10);
        JTextField rent = new JTextField(10);
        JTextField tuition = new JTextField(10);
        JTextField taxes = new JTextField(10);


        groceries.setText(CashSpending.ExpenditureType.GROCERIES.toString());
        rent.setText(CashSpending.ExpenditureType.RENT.toString());
        tuition.setText(CashSpending.ExpenditureType.TUITION.toString());
        taxes.setText(CashSpending.ExpenditureType.TAXES.toString());





        thePanel = new JPanel();
        thePanel.setLayout(new BoxLayout(thePanel, BoxLayout.LINE_AXIS));


        for(Object expenditure: typesOfExpenditure){
            JPanel innerPanel = new JPanel();
            String expenditureString = (expenditure).toString();
            switch (expenditureString){
                case "GROCERIES":
                    innerPanel.add(groceries);
                    innerPanel.add(amountSpentOnGroceries);
                    break;
                case "RENT":
                    innerPanel.add(rent);
                    innerPanel.add(amountSpentOnRent);
                    break;
                case "TUITION":
                    innerPanel.add(tuition);
                    innerPanel.add(amountSpentOnTuition);
                    break;
                case "TAXES":
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
