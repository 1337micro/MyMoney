//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 2: Real Nguyen, 27566263
//Description: CashSpending method to create the object containing all expenditures and the add method involved
// --------------------------------------------------------


package test;

import org.junit.Test;

import src.Budgeting;
import src.CashSpending;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CashSpendingTest{
    CashSpending.ExpenditureType expenditureTypeFood =  CashSpending.ExpenditureType.FOOD;
    CashSpending.ExpenditureType expenditureTypeHousing =  CashSpending.ExpenditureType.HOUSING;
    CashSpending.ExpenditureType expenditureTypeMisc =  CashSpending.ExpenditureType.MISC;

    double dollarsSpentOnFoodApple = 23.12;
    double dollarsSpentOnFoodBanana = 1.1;
    double dollarsSpentOnHousing = 1223.12;
    double dollarsSpentOnMisc = 223.12;

    @Test
    public void addExpenseTest(){
        CashSpending spending = new CashSpending();
        double totalFood = 0;
        spending.addAmount(dollarsSpentOnFoodApple, expenditureTypeFood);
        totalFood += dollarsSpentOnFoodApple;
        assertEquals(totalFood, spending.getAmountFood(), 0);        
        spending.addAmount(dollarsSpentOnFoodBanana, expenditureTypeFood);
        totalFood += dollarsSpentOnFoodBanana;
        assertEquals(totalFood, spending.getAmountFood(), 0);     
        
        spending.addAmount(dollarsSpentOnHousing, expenditureTypeHousing);
        assertEquals(dollarsSpentOnHousing, spending.getAmountHousing(), 0);
    }

    @Test
    public void defaultConstructorTest(){
    	//Test if default constructor is instantiated and sets default values
        CashSpending spending = new CashSpending();
        assertNotNull(spending);
        assertEquals(0, spending.getAmountHousing(), 0);
        assertEquals(0, spending.getAmountFood(), 0);
        assertEquals(0, spending.getAmountUtilities(), 0);
        assertEquals(0, spending.getAmountClothing(), 0);
        assertEquals(0, spending.getAmountMedical(), 0);
        assertEquals(0, spending.getAmountDonations(), 0);
        assertEquals(0, spending.getAmountSavingsInsurance(), 0);
        assertEquals(0, spending.getAmountEntertainment(), 0);
        assertEquals(0, spending.getAmountTransportation(), 0);
        assertEquals(0, spending.getAmountMisc(), 0);
    }

    @Test
    public void overloadedConstructorTest()
    {
    	//Test if overloaded constructor is instantiated and sets values passed as parameters
    	CashSpending spending = new CashSpending(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertNotNull(spending);
        assertEquals(1, spending.getAmountHousing(), 0);
        assertEquals(2, spending.getAmountFood(), 0);
        assertEquals(3, spending.getAmountUtilities(), 0);
        assertEquals(4, spending.getAmountClothing(), 0);
        assertEquals(5, spending.getAmountMedical(), 0);
        assertEquals(6, spending.getAmountDonations(), 0);
        assertEquals(7, spending.getAmountSavingsInsurance(), 0);
        assertEquals(8, spending.getAmountEntertainment(), 0);
        assertEquals(9, spending.getAmountTransportation(), 0);
        assertEquals(10, spending.getAmountMisc(), 0);
    }    
    
    @Test(expected = NumberFormatException.class)
    public void overloadedConstructorInvalidValueTest()
    {
    	//Test that overloaded constructor throws exception if at least one invalid value is passed
    	CashSpending spending = new CashSpending(1, 2, 3, 4, 5, 6, 7, 8, 9, -1);
    	assertNull(spending);
    }
    
    @Test
    public void defaultIsOverBudgetDefaultBudgetingFileTest(){    	
    	//Test that cash spending is over budget with default budgeting file	
    	double budgetingTest = 300;
    	CashSpending spending = new CashSpending();
    	
    	assertFalse(spending.isOverBudget(budgetingTest, CashSpending.ExpenditureType.HOUSING));
    	assertTrue(spending.isOverBudget(budgetingTest, CashSpending.ExpenditureType.FOOD));
    }
    
    @Test
    public void defaultIsOverBudgetDefaultCustomFileTest(){    	
    	//Test that cash spending is over budget with custom budgeting file	
    	double budgetingTest = 35.65;
    	CashSpending spending = new CashSpending();
    	
    	assertTrue(spending.isOverBudget(budgetingTest, CashSpending.ExpenditureType.UTILITIES, "BudgetingTest.txt"));
    	assertFalse(spending.isOverBudget(budgetingTest, CashSpending.ExpenditureType.TRANSPORTATION, "BudgetingTest.txt"));
    }
    
    @Test
    public void defaultIsOverBudgetWithDefaultConstructorTest(){    	
    	//Test that cash spending object instantiated with default constructor is not over budget
    	CashSpending spending = new CashSpending();
    	
    	assertFalse(spending.isOverBudget(spending.getAmountHousing(), CashSpending.ExpenditureType.HOUSING));
    	assertFalse(spending.isOverBudget(spending.getAmountFood(), CashSpending.ExpenditureType.FOOD));
    	assertFalse(spending.isOverBudget(spending.getAmountUtilities(), CashSpending.ExpenditureType.UTILITIES));
    	assertFalse(spending.isOverBudget(spending.getAmountClothing(), CashSpending.ExpenditureType.CLOTHING));
    	assertFalse(spending.isOverBudget(spending.getAmountMedical(), CashSpending.ExpenditureType.MEDICAL));
    	assertFalse(spending.isOverBudget(spending.getAmountDonations(), CashSpending.ExpenditureType.DONATIONS));
    	assertFalse(spending.isOverBudget(spending.getAmountSavingsInsurance(), CashSpending.ExpenditureType.SAVINGS));
    	assertFalse(spending.isOverBudget(spending.getAmountEntertainment(), CashSpending.ExpenditureType.ENTERTAINMENT));
    	assertFalse(spending.isOverBudget(spending.getAmountTransportation(), CashSpending.ExpenditureType.TRANSPORTATION));
    	assertFalse(spending.isOverBudget(spending.getAmountMisc(), CashSpending.ExpenditureType.MISC));
    }
    
    @Test
    public void defaultIsOverBudgetWithOverloadedConstructorTest(){    	
    	//Test that cash spending that's exactly on budget is not over budget   	
    	Budgeting budgeting = new Budgeting();
    	CashSpending spending = new CashSpending(
    			budgeting.getAmountHousing(),
    			budgeting.getAmountFood(),
    			budgeting.getAmountUtilities(),
    			budgeting.getAmountClothing(),
    			budgeting.getAmountMedical(),
    			budgeting.getAmountDonations(),
    			budgeting.getAmountSavingsInsurance(),
    			budgeting.getAmountEntertainment(),
    			budgeting.getAmountTransportation(),
    			budgeting.getAmountMisc()
		);
    	
    	assertFalse(spending.isOverBudget(spending.getAmountHousing(), CashSpending.ExpenditureType.HOUSING));
    	assertFalse(spending.isOverBudget(spending.getAmountFood(), CashSpending.ExpenditureType.FOOD));
    	assertFalse(spending.isOverBudget(spending.getAmountUtilities(), CashSpending.ExpenditureType.UTILITIES));
    	assertFalse(spending.isOverBudget(spending.getAmountClothing(), CashSpending.ExpenditureType.CLOTHING));
    	assertFalse(spending.isOverBudget(spending.getAmountMedical(), CashSpending.ExpenditureType.MEDICAL));
    	assertFalse(spending.isOverBudget(spending.getAmountDonations(), CashSpending.ExpenditureType.DONATIONS));
    	assertFalse(spending.isOverBudget(spending.getAmountSavingsInsurance(), CashSpending.ExpenditureType.SAVINGS));
    	assertFalse(spending.isOverBudget(spending.getAmountEntertainment(), CashSpending.ExpenditureType.ENTERTAINMENT));
    	assertFalse(spending.isOverBudget(spending.getAmountTransportation(), CashSpending.ExpenditureType.TRANSPORTATION));
    	assertFalse(spending.isOverBudget(spending.getAmountMisc(), CashSpending.ExpenditureType.MISC));
    }
}
