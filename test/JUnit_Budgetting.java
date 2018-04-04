package test;

import static org.junit.Assert.*;

import org.junit.Test;
import src.Budgeting;
import src.BudgetingUI;
import src.Constants;



import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class JUnit_Budgetting {

	Budgeting budget = new Budgeting();
	Budgeting budgetReadFromFile = new Budgeting(100000.0, Constants.DEFAULTBUDGETINGPERCENTAGES_FILE);
	Budgeting budgetPercentages = new Budgeting(6000, 20, 5, 5, 10, 6, 15, 7, 7, 15, 5);
	BudgetingUI budgetUI = new BudgetingUI();
	
	public final static File PERSONALIZED_BUDGET_FILE = new File("PersonalizedBudgetFile.txt");
	
	//Test default constructor budgeting
	@Test
	public void testDefaultConstructorBudgeting() {
		assertEquals(0,budget.getAvailableFunds(),0);
		assertEquals(30,budget.getPercentHousing(),0);
		assertEquals(10,budget.getPercentFood(),0);
		assertEquals(7,budget.getPercentUtilities(),0);
		assertEquals(5,budget.getPercentClothing(),0);
		assertEquals(7,budget.getPercentMedical(),0);
		assertEquals(10,budget.getPercentDonations(),0);
		assertEquals(7,budget.getPercentSavingsInsurance(),0);
		assertEquals(7,budget.getPercentEntertainment(),0);
		assertEquals(12,budget.getPercentTransportation(),0);
		assertEquals(5,budget.getPercentMisc(),0);
		
		//test constructor budgeting with two parameters
	}
	@Test
	public void testConstructorBudgeting() {
		assertEquals(100000,budgetReadFromFile.getAvailableFunds(),0);
	
	}
	//test constructor budgeting with percentages
	@Test
	public void testConstructorBudgetingWithPercentages() {
		assertEquals(6000,budgetPercentages.getAvailableFunds(),0);
		assertEquals(20,budgetPercentages.getPercentHousing(),0);
		assertEquals(5,budgetPercentages.getPercentFood(),0);
		assertEquals(5,budgetPercentages.getPercentUtilities(),0);
		assertEquals(10,budgetPercentages.getPercentClothing(),0);
		assertEquals(6,budgetPercentages.getPercentMedical(),0);
		assertEquals(15,budgetPercentages.getPercentDonations(),0);
		assertEquals(7,budgetPercentages.getPercentSavingsInsurance(),0);
		assertEquals(7,budgetPercentages.getPercentEntertainment(),0);
		assertEquals(15,budgetPercentages.getPercentTransportation(),0);
		assertEquals(10,budgetPercentages.getPercentMisc(),0);
	}
		
    // test getters and setters for percentages 
	
	@Test
	public void testSetGetPercentHousing() {
		budget.setPercentHousing(25);
		assertEquals(25,budget.getPercentHousing(),0);
	}
	
	@Test
	public void testSetGetPercentFood() {
		budget.setPercentFood(25);
		assertEquals(25,budget.getPercentFood(),0);
	}
	
	@Test
	public void testSetGetPercentUtilities() {
		budget.setPercentUtilities(25);
		assertEquals(25,budget.getPercentUtilities(),0);
	}
	
	@Test
	public void testSetGetPercentClothing() {
		budget.setPercentClothing(25);
		assertEquals(25,budget.getPercentClothing(),0);
	}
	@Test
	public void testSetGetPercentMedical() {
		budget.setPercentMedical(25);
		assertEquals(25,budget.getPercentMedical(),0);
	}
	
	@Test
	public void testSetGetPercentDonations() {
		budget.setPercentDonations(25);
		assertEquals(25,budget.getPercentDonations(),0);
	}
	
	@Test
	public void testSetGetPercentSavingsInsurance() {
		budget.setPercentSavingsInsurance(25);
		assertEquals(25,budget.getPercentSavingsInsurance(),0);
	}
	
	@Test
	public void testSetGetPercentEntertainment() {
		budget.setPercentEntertainment(25);
		assertEquals(25,budget.getPercentEntertainment(),0);
	}
	
	@Test
	public void testSetGetPercentTransportation() {
		budget.setPercentTransportation(25);
		assertEquals(25,budget.getPercentTransportation(),0);
	}
	
	@Test
	public void testSetGetPercentMisc() {
		budget.setPercentMisc(25);
		assertEquals(25,budget.getPercentMisc(),0);
	}
	
	@Test
	public void testSetGetAvailableFunds() {
		budget.setAvailableFunds(25);
		assertEquals(25,budget.getAvailableFunds(),0);
	}
	
	
	//TEST GETTERS AND SETTERS FOR AMOUNT
	
	@Test
	public void testSetGetAmountHousing() {
		budget.setAmountHousing(250);
		assertEquals(250,budget.getAmountHousing(),0);
	}
	
	@Test
	public void testSetGetAmountFood() {
		budget.setAmountFood(250);
		assertEquals(250,budget.getAmountFood(),0);
	}
	
	@Test
	public void testSetGetAmountUtilities() {
		budget.setAmountUtilities(250);
		assertEquals(250,budget.getAmountUtilities(),0);
	}
	
	@Test
	public void testSetGetAmountClothing() {
		budget.setAmountClothing(250);
		assertEquals(250,budget.getAmountClothing(),0);
	}
	@Test
	public void testSetGetAmountMedical() {
		budget.setAmountMedical(250);
		assertEquals(250,budget.getAmountMedical(),0);
	}
	
	@Test
	public void testSetGetAmountDonations() {
		budget.setAmountDonations(250);
		assertEquals(250,budget.getAmountDonations(),0);
	}
	
	@Test
	public void testSetGetAmountSavingsInsurance() {
		budget.setAmountSavingsInsurance(250);
		assertEquals(250,budget.getAmountSavingsInsurance(),0);
	}
	
	@Test
	public void testSetGetAmountEntertainment() {
		budget.setAmountEntertainment(250);
		assertEquals(250,budget.getAmountEntertainment(),0);
	}
	
	@Test
	public void testSetGetAmountTransportation() {
		budget.setAmountTransportation(250);
		assertEquals(250,budget.getAmountTransportation(),0);
	}
	
	@Test
	public void testSetGetAmountMisc() {
		budget.setAmountMisc(25);
		assertEquals(25,budget.getAmountMisc(),0);
	}
	
    // test calculate categories	
	@Test
	public void testCalculateHousing() {
		budget.setAvailableFunds(1000);
		assertEquals(300,budget.calculateHousing(),0);
	}

	@Test
	public void testCalculateFood() {
		budget.setAvailableFunds(1000);
		assertEquals(100,budget.calculateFood(),0);
	}
	
	@Test
	public void testCalculateUtilities() {
		budget.setAvailableFunds(1000);
		assertEquals(70,budget.calculateUtilities(),0);
	}

	@Test
	public void testCalculateClothing() {
		budget.setAvailableFunds(1000);
		assertEquals(50,budget.calculateClothing(),0);
	}

	@Test
	public void testCalculateMedical() {
		budget.setAvailableFunds(1000);
		assertEquals(70,budget.calculateMedical(),0);
	}

	@Test
	public void testCalculateDonations() {
		budget.setAvailableFunds(1000);
		assertEquals(100,budget.calculateDonations(),0);
	}

	@Test
	public void testCalculateSavingsInsurance() {
		budget.setAvailableFunds(1000);
		assertEquals(70,budget.calculateSavingsInsurance(),0);
	}

	@Test
	public void testCalculateEntertainement() {
		budget.setAvailableFunds(1000);
		assertEquals(70,budget.calculateEntertainment(),0);
	}

	@Test
	public void testCalculateTransportation() {
		budget.setAvailableFunds(1000);
		assertEquals(120,budget.calculateTransportation(),0);
	}

	@Test
	public void testCalculateMisc() {
		budget.setAvailableFunds(1000);
		assertEquals(50,budget.calculateMisc(),0);
	}
	
	@Test
	//Test to see if the "print()" method created its intended file
	public void testPrintBudget(){
		budgetUI.print();
		assertTrue(PERSONALIZED_BUDGET_FILE.exists()&&!PERSONALIZED_BUDGET_FILE.isDirectory());
	}

}
