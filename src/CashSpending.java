package src;

import java.util.ArrayList;
import java.util.List;

public class CashSpending {

    public CashSpending(){
        this.expenses = new ArrayList<>();
    }

    private List<Expenses> expenses;

    public void addExpense(ExpenditureType type, double amount){
        if(getExpensesOfType(type) == null){
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
    public List<Double> getExpensesOfType(ExpenditureType type){
        List<Double> listOfExpense = null;
        for(Expenses expense : this.expenses){
            if(expense.type == type){
                listOfExpense = expense.listOfExpenses;
            }
        }
        return listOfExpense;
    }




    public enum ExpenditureType {
        GROCERIES,
        RENT,
        TUITION,
        TAXES
    }

   public static class Expenses{
       private ExpenditureType type;
       private List<Double> listOfExpenses;

       public Expenses(ExpenditureType type) {
           this.type = type;
           this.listOfExpenses = new ArrayList<Double>();
       }


       public ExpenditureType getType() {
           return type;
       }
       public List<Double> getListOfExpenses() {
           return listOfExpenses;
       }
   }
}
