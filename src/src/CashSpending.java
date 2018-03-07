//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 2: Noemi Lemonnier, 40001085
//Description: CashSpending method to create the object containing all expenditures and the add method involved
// --------------------------------------------------------

package src;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Dictionary;

import src.Cards.CardType;

public class CashSpending {
	//Calculated percentages
	private double amountHousing = 0;
	private double amountFood = 0;
	private double amountUtilities = 0;
	private double amountClothing = 0;
	private double amountMedical = 0;
	private double amountDonations = 0;
	private double amountSavingsInsurance = 0;
	private double amountEntertainment = 0;
	private double amountTransportation = 0;
	private double amountMisc = 0;
	private Budgeting budgeting;
	private Dictionary<ExpenditureType, String> expenditureTypeToBudgetingConstantDictionary;

	/*
	 * Constructor
	 */
	public CashSpending(double housing, double food, double utilities, double clothing, double medical, 
			double donations, double savings, double entertainment, double transportation, double misc) {
		//to make sure no number are 
		if(housing>=0 || food >= 0 || utilities >=0 || clothing >=0 || medical >=0 || donations >=0 || savings >=0 || entertainment >=0 || transportation >= 0 || misc >=0){
			this.amountHousing = housing;
			this.amountFood = food;
			this.amountUtilities = utilities;
			this.amountClothing = clothing;
			this.amountMedical = medical;
			this.amountDonations = donations;
			this.amountSavingsInsurance = savings;
			this.amountEntertainment = entertainment;
			this.amountTransportation = transportation;
			this.amountMisc = misc;
			this.budgeting = new Budgeting();
		}
		else{
			throw new NumberFormatException("Values cannot be below 0$");
		}
	}
	/*
	 * Default Constructor
	 */
	public CashSpending() {
		this.amountHousing = 0;
		this.amountFood = 0;
		this.amountUtilities = 0;
		this.amountClothing = 0;
		this.amountMedical = 0;
		this.amountDonations = 0;
		this.amountSavingsInsurance = 0;
		this.amountEntertainment = 0;
		this.amountTransportation = 0;
		this.amountMisc = 0;
		this.budgeting = new Budgeting();

	}
	/*
	 * Method to print out the object CashSpending
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Cash spendings on\n Housing: " + this.getAmountHousing() + "$ Food: " + this.getAmountFood() + "$ Utilities: " + 
				this.getAmountUtilities() + "$ Clothing: " + this.getAmountClothing() + "$ Medical: " + this.getAmountMedical() + 
				"$ Donations: " + this.getAmountDonations() +  "$ Savings:" + this.getAmountSavingsInsurance() + "$ Entertainment:" 
				+ this.getAmountEntertainment() +   "$ Transportation:"+ this.getAmountTransportation() +  "$ Misc: " + this.getAmountMisc() +  "$";
	}

	/**
	 * Types of Spending you can perform.
	 */
	public enum ExpenditureType {
		HOUSING("HOUSING"),
		FOOD("FOOD"),
		UTILITIES("UTILITIES"),
		CLOTHING("CLOTHING"),
		MEDICAL("MEDICAL"),
		DONATIONS("DONATIONS"),
		SAVINGS("SAVINGS"),
		ENTERTAINMENT("ENTERTAINMENT"),
		TRANSPORTATION("TRANSPORTATION"),
		MISC("MISC");

		private final String description;

		public String toString(){
			return this.description;
		}
		ExpenditureType(final String description){
			this.description = description;
		}
	}

	public void addAmount(double amount, ExpenditureType type){
		if(amount>=0){
			switch (type){
			case HOUSING:
				setAmountHousing(getAmountHousing() + amount);
				break;
			case FOOD:
				setAmountFood(getAmountFood() + amount);
				break;
			case UTILITIES:
				setAmountUtilities(getAmountUtilities() + amount);
				break;
			case CLOTHING:
				setAmountClothing(getAmountClothing() + amount);
				break;
			case MEDICAL:
				setAmountMedical(getAmountMedical() + amount);
				break;
			case DONATIONS:
				setAmountDonations(getAmountDonations() + amount);
				break;
			case SAVINGS:
				setAmountSavingsInsurance(getAmountSavingsInsurance() + amount);
				break;
			case ENTERTAINMENT:
				setAmountEntertainment(getAmountEntertainment() + amount);
				break;
			case TRANSPORTATION:
				setAmountTransportation(getAmountTransportation() + amount);
				break;
			case MISC:
				setAmountMisc(getAmountMisc() + amount);
				break;
			}
		}
	}

	public boolean isOverBudget(double amount, ExpenditureType type){
		//sets the budgeting amounts from the file
		budgeting.readBudgetingFromFile(Constants.BUDGETING_FILE);

		switch (type) {
		case HOUSING:
			return getAmountHousing() + amount > budgeting.getAmountHousing();
		case FOOD:
			return getAmountFood() + amount > budgeting.getAmountFood();
		case UTILITIES:
			return getAmountUtilities() + amount > budgeting.getAmountUtilities();
		case CLOTHING:
			return getAmountClothing() + amount > budgeting.getAmountClothing();
		case MEDICAL:
			return getAmountMedical() + amount > budgeting.getAmountMedical();
		case DONATIONS:
			return getAmountDonations() + amount > budgeting.getAmountDonations();
		case SAVINGS:
			return getAmountSavingsInsurance() + amount > budgeting.getAmountSavingsInsurance();
		case ENTERTAINMENT:
			return getAmountEntertainment() + amount > budgeting.getAmountEntertainment();
		case TRANSPORTATION:
			return getAmountTransportation() + amount > budgeting.getAmountTransportation();
		case MISC:
			return getAmountMisc() + amount > budgeting.getAmountMisc();
		}
		return false;
	}


	/*
	 * Getters and Setters
	 */
	public double getAmountHousing() {
		return amountHousing;
	}
	public void setAmountHousing(double amountHousing) {
		this.amountHousing = amountHousing;
	}
	public double getAmountFood() {
		return amountFood;
	}
	public void setAmountFood(double amountFood) {
		this.amountFood = amountFood;
	}
	public double getAmountUtilities() {
		return amountUtilities;
	}
	public void setAmountUtilities(double amountUtilities) {
		this.amountUtilities = amountUtilities;
	}
	public double getAmountClothing() {
		return amountClothing;
	}
	public void setAmountClothing(double amountClothing) {
		this.amountClothing = amountClothing;
	}
	public double getAmountMedical() {
		return amountMedical;
	}
	public void setAmountMedical(double amountMedical) {
		this.amountMedical = amountMedical;
	}
	public double getAmountDonations() {
		return amountDonations;
	}
	public void setAmountDonations(double amountDonations) {
		this.amountDonations = amountDonations;
	}
	public double getAmountSavingsInsurance() {
		return amountSavingsInsurance;
	}
	public void setAmountSavingsInsurance(double amountSavingsInsurance) {
		this.amountSavingsInsurance = amountSavingsInsurance;
	}
	public double getAmountEntertainment() {
		return amountEntertainment;
	}
	public void setAmountEntertainment(double amountEntertainment) {
		this.amountEntertainment = amountEntertainment;
	}
	public double getAmountTransportation() {
		return amountTransportation;
	}
	public void setAmountTransportation(double amountTransportation) {
		this.amountTransportation = amountTransportation;
	}
	public double getAmountMisc() {
		return amountMisc;
	}
	public void setAmountMisc(double amountMisc) {
		this.amountMisc = amountMisc;
	}



}

