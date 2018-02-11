//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: William Prioriello
//Description: "Model" class keeps track of user-input spending
//--------------------------------------------------------
package src;

import java.util.ArrayList;
import java.util.List;

public class CashSpending {
	
    /**
     * initialize our list of expenses as an empty arraylist
     */
    public CashSpending(){
        this.expenses = new ArrayList<>();
    }

    /**
     * A list containing objects of Expenses which, in turn, contains a type of expenditure and a list of doubles
     * signifying all the
     * purchases made in that category
     */
    private List<Expenses> expenses;

    /** Add a dollar amount that was spent for a specific category of expenditure in enum EpenditureType
     *
     * @param type the type of expenditure
     * @param amount the dollar amount spent on this type of expenditure
     */
    public void addExpense(ExpenditureType type, double amount){
        if(getExpensesOfType(type) == null || getExpensesOfType(type).size() == 0){
            //no such expense yet

            Expenses expense = new Expenses(type);
            expense.getListOfExpenses().add(amount);
            expenses.add(expense);
        } else {
            if(getExpensesOfType(type).size() == 0) throw new RuntimeException("This expense list should not be 0");
            getExpensesOfType(type).add(amount);
            // expense already exists in the lists 'expenses'
        }
    }

    /**Get a list of all the purchases of a particular ExpenditureType
     *
     * @param type The type of expenditure we are looking for
     * @return list of Doubles. All the purchases of a particular ExpenditureType
     */
    public List<Double> getExpensesOfType(ExpenditureType type){
        List<Double> listOfExpense = new ArrayList<>();
        for(Expenses expense : this.expenses){
            if(expense.type == type){
                listOfExpense = expense.listOfExpenses;
            }
        }
        return listOfExpense;
    }


    /**
     * Types of Spending a user can perform.
     */
    public enum ExpenditureType {
        GROCERIES("GROCERIES"),
        RENT("RENT"),
        TUITION("TUITION"),
        TAXES("TAXES");
        private final String description;
        public String toString(){
            return this.description;
        }
        ExpenditureType(final String description){
            this.description = description;
        }
    }

    /**
     * Every Expenses object has an Expenditure type
     * and a list of dollar amounts of all the purchases done for that type.
     */
   public static class Expenses{
       private ExpenditureType type; //an Expenditure type
       private List<Double> listOfExpenses; //a list of dollar amounts of all the purchases done for that type.

       public Expenses(ExpenditureType type) {
           this.type = type;
           this.listOfExpenses = new ArrayList<>();
       }
       public List<Double> getListOfExpenses() {
           return listOfExpenses;
       }
   }
}
