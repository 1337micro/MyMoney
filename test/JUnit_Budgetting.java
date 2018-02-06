//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Sabrina Rieck, 40032864
//Description: Unit tests the budgetting class
//--------------------------------------------------------

package src;
import static org.junit.Assert.*;

import org.junit.Test;

public class JUnit_Budgetting {

	Budgetting budget = new Budgetting();

	//TESTING THE CALCULATIONS
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
	public void testCalculateEntertainment() {
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


	//TESTING THE GETTERS AND SETTERS
	@Test
	public void testSetAndGetPercentHousing() {
		budget.setPercentHousing(16);
		assertEquals(16, budget.getPercentHousing(),0);
	}

	@Test
	public void testSetAndGetPercentFood() {
		budget.setPercentFood(15);
		assertEquals(15, budget.getPercentFood(),0);
	}

	@Test
	public void testSetAndGetPercentUtilities() {
		budget.setPercentUtilities(14);
		assertEquals(14, budget.getPercentUtilities(),0);
	}

	@Test
	public void testSetAndGetPercentClothing() {
		budget.setPercentClothing(17);
		assertEquals(17, budget.getPercentClothing(),0);
	}

	@Test
	public void testSetAndGetPercentMedical() {
		budget.setPercentMedical(11);
		assertEquals(11, budget.getPercentMedical(),0);
	}

	@Test
	public void testSetAndGetPercentDonations() {
		budget.setPercentDonations(15);
		assertEquals(15, budget.getPercentDonations(),0);
	}

	@Test
	public void testSetAndGetPercentSavingsInsurance() {
		budget.setPercentSavingsInsurance(25);
		assertEquals(25, budget.getPercentSavingsInsurance(),0);
	}

	@Test
	public void testSetAndGetPercentEntertainment() {
		budget.setPercentEntertainment(18);
		assertEquals(18, budget.getPercentEntertainment(),0);
	}

	@Test
	public void testSetAndGetPercentTransportation() {
		budget.setPercentTransportation(19);
		assertEquals(19, budget.getPercentTransportation(),0);
	}

	@Test
	public void testSetAndGetPercentMisc() {
		budget.setPercentMisc(15);
		assertEquals(15, budget.getPercentMisc(),0);
	}

	@Test
	public void testSetAndGetAvailableFunds() {
		budget.setAvailableFunds(15);
		assertEquals(15, budget.getAvailableFunds(),0);
	}

	@Test
	public void testSetAndGetAmountHousing() {
		budget.setAmountHousing(153);
		assertEquals(153, budget.getAmountHousing(),0);
	}

	@Test
	public void testSetAndGetAmountFood() {
		budget.setAmountFood(783);
		assertEquals(783, budget.getAmountFood(),0);
	}

	@Test
	public void testSetAndGetAmountUtilities() {
		budget.setAmountUtilities(96);
		assertEquals(96, budget.getAmountUtilities(),0);
	}

	@Test
	public void testSetAndGetAmountClothing() {
		budget.setAmountClothing(253);
		assertEquals(253, budget.getAmountClothing(),0);
	}

	@Test
	public void testSetAndGetAmountMedical() {
		budget.setAmountMedical(187);
		assertEquals(187, budget.getAmountMedical(),0);
	}

	@Test
	public void testSetAndGetAmountDonations() {
		budget.setAmountDonations(53);
		assertEquals(53, budget.getAmountDonations(),0);
	}

	@Test
	public void testSetAndGetAmountSavingsInsurance() {
		budget.setAmountSavingsInsurance(1539);
		assertEquals(1539, budget.getAmountSavingsInsurance(),0);
	}

	@Test
	public void testSetAndGetAmountEntertainment() {
		budget.setAmountEntertainment(13);
		assertEquals(13, budget.getAmountEntertainment(),0);
	}

	@Test
	public void testSetAndGetAmountTransportation() {
		budget.setAmountTransportation(456);
		assertEquals(456, budget.getAmountTransportation(),0);
	}

	@Test
	public void testSetAndGetAmountMisc() {
		budget.setAmountMisc(193);
		assertEquals(193, budget.getAmountMisc(),0);
	}

	@Test
	public void testWriteAndReadFromFile() {
		//Testing Print to file
		budget.writeToFile();

		//Testing read full object from file
		Budgetting budget2 = new Budgetting();
		budget2.readBudgetingFromFile("Budgetting.txt");
		
		assertEquals(budget.getAmountTransportation(), budget2.getAmountTransportation(), 0);

	}
}
