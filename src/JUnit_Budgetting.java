package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class JUnit_Budgetting {

	Budgetting budget = new Budgetting();
	
	@Test
	void testCalculateHousing() {
		budget.setAvailableFunds(1000);
		assertEquals(300,budget.calculateHousing());
	}

	@Test
	void testCalculateFood() {
		budget.setAvailableFunds(1000);
		assertEquals(100,budget.calculateFood());
	}
	
	@Test
	void testCalculateUtilities() {
		budget.setAvailableFunds(1000);
		assertEquals(70,budget.calculateUtilities());
	}

	@Test
	void testCalculateClothing() {
		budget.setAvailableFunds(1000);
		assertEquals(50,budget.calculateClothing());
	}

	@Test
	void testCalculateMedical() {
		budget.setAvailableFunds(1000);
		assertEquals(70,budget.calculateMedical());
	}

	@Test
	void testCalculateDonations() {
		budget.setAvailableFunds(1000);
		assertEquals(100,budget.calculateDonations());
	}

	@Test
	void testCalculateSavingsInsurance() {
		budget.setAvailableFunds(1000);
		assertEquals(70,budget.calculateSavingsInsurance());
	}

	@Test
	void testCalculateEntertainement() {
		budget.setAvailableFunds(1000);
		assertEquals(70,budget.calculateEntertainement());
	}

	@Test
	void testCalculateTransportation() {
		budget.setAvailableFunds(1000);
		assertEquals(120,budget.calculateTransportation());
	}

	@Test
	void testCalculateMisc() {
		budget.setAvailableFunds(1000);
		assertEquals(50,budget.calculateMisc());
	}

}
