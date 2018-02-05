package test;

import static org.junit.Assert.*;

import org.junit.Test;
import src.Budgetting;

import static org.junit.Assert.assertEquals;

public class JUnit_Budgetting {

	Budgetting budget = new Budgetting();
	
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

}
