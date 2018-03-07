//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Sabrina Rieck, 40032864
//Description: A visual testing of the Budgetting class
//--------------------------------------------------------
package src;

public class BudgetingDriver {

	public static void main(String[] args) {
		
		//Testing default constructor
		System.out.println("Using the DEFAULT constructor, with initialized budget of 1000$\n\n");
		
		Budgeting object1 = new Budgeting();
		object1.setAvailableFunds(1000);
		System.out.println(object1);
		
		//Testing parameterized constructor
		System.out.println("Using the PARAMETERIZED contructor:");
		Budgeting object2 = new Budgeting(2500, 30, 10, 10, 5, 5, 4, 7, 10, 10, 10);
		System.out.println(object2);
		
		//Testing Print to file
		object1.writeToFile();
		
		//Testing read full object from file
		System.out.println("Output should be identical to the first output");
		Budgeting object3 = new Budgeting();
		object3.readBudgetingFromFile(Constants.BUDGETING_FILE);
		System.out.println(object3);

	}

}

