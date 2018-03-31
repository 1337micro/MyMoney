package test;

import src.Budgeting;

public class BudgettingDriver {

	public static void main(String[] args) {
		
		//Testing default constructor
		System.out.println("Using the DEFAULT constructor, with initialized budget of 1000$\n\n");
		
		Budgeting object1 = new Budgeting();
		object1.setAvailableFunds(1000);
		System.out.println(object1);
		
		//Testing parameterized constructor
		System.out.println("Using the PARAMETERIZED contructor:");
		Budgeting object2 = new Budgeting(2500, 30, 10, 10, 5, 5, 3, 7, 10, 10, 10);
		System.out.println(object2);
		
		//Testing Print to file
		System.out.println("Testing Print to file");
		object1.writeToFile();
		
		//Testing read full object from file
		System.out.println("Output should be identical to the first output");
		Budgeting object3 = new Budgeting();
		object3.readBudgetingFromFile("Budgetting.txt");
		System.out.println(object3);

	}

}
