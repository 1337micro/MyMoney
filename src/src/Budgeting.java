//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Sabrina Rieck, 40032864
//Iteration 2: Ornela Bregu, 26898580
//Description: Budgeting class calculates a user's budget according to an amount of available money
//				Takes in percentages and an amount, calculates amounts based on percentages and returns those amounts
//				Has a read and write to file system
//--------------------------------------------------------

package src;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Budgeting {

	//Percentage instance members
	//Getters and Setters are at the end of the document
	private double percentHousing;
	private double percentFood;
	private double percentUtilities;
	private double percentClothing;
	private double percentMedical;
	private double percentDonations;
	private double percentSavingsInsurance;
	private double percentEntertainment;
	private double percentTransportation;
	private double percentMisc;

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

	//Other instance members
	private double availableFunds =0;
	

	
	/**
	 * Deafult constructor reads percentages from default file, has no available funds
	 */
	//Default constructor with recommended percentages
	//Based on http://www.leavedebtbehind.com/frugal-living/budgeting/10-recommended-category-percentages-for-your-family-budget/
	public Budgeting() {
		//Get the default percentages from a file
		readBudgetingFromFile(Constants.DEFAULTBUDGETINGPERCENTAGES_FILE);

		this.availableFunds = 0;
	}
	
	/**
	 * Constructor reads percentages from chosen file, has no available funds
	 * @param fileName
	 */
	//Read percentages from chosen file
	public Budgeting(String fileName) {
		readBudgetingFromFile(fileName);
		
	}
	
	/**
	 * Constructor reads percentages from chosen file, and has input for available funds
	 * @param availableFunds
	 * @param fileName
	 */
	//Read percentage from chosen file with custom available of funds
	public Budgeting(Double availableFunds, String fileName) {
		readBudgetingFromFile(fileName);
		this.availableFunds = availableFunds;
	}

	/**
	 * Constructor takes in all percentages manually as parameters, as well as amount for available funds
	 * @param funds
	 * @param housing
	 * @param food
	 * @param utilities
	 * @param clothing
	 * @param medical
	 * @param donations
	 * @param savings
	 * @param entertainment
	 * @param transportation
	 * @param misc
	 */
	//Constructor with parameters
	//ERROR testing needed for iteration 2
	public Budgeting(double funds, double housing, double food, double utilities, double clothing, double medical, 
			double donations, double savings, double entertainment, double transportation, double misc) {

		double total = housing + food + utilities + clothing + medical + donations + savings + entertainment + transportation + misc;

		if(total>100) {
			System.out.println("Total exceeds 100%\n");
		}
		if(total<100) {
			//Calculate missing percentage points
			double missing = 100-total;
			misc= misc+ missing;
			System.out.println("Total was below 100%\n");
			System.out.println("Extra " + missing + "% was added to Misc\n");
		}

		this.percentHousing = housing;
		this.percentFood = food;
		this.percentUtilities = utilities;
		this.percentClothing = clothing;
		this.percentMedical = medical;
		this.percentDonations = donations;
		this.percentSavingsInsurance = savings;
		this.percentEntertainment = entertainment;
		this.percentTransportation = transportation;
		this.percentMisc = misc;

		this.availableFunds = funds;
	}

	/**
	 * Runs all the methods that calculate amounts based on percentages
	 */
	//Calculate amounts
	//Methods listed here are near the bottom of the document
	public void calculateAmountsFromPercentages() {
		calculateHousing();
		calculateFood();
		calculateUtilities();
		calculateClothing();
		calculateMedical();
		calculateDonations();
		calculateSavingsInsurance();
		calculateEntertainment();
		calculateTransportation();
		calculateMisc(); 
	}

	/**
	 * A display method to display available funds, and percentages and amounts for each category
	 */
	//Display
	public String toString(){
		calculateAmountsFromPercentages();
		String toPrint = "";

		toPrint += "With " + getAvailableFunds() + "$ in available funds, it is recommended that you spend:\n";
		toPrint += getAmountHousing() + "$ (" + getPercentHousing() + "%) for Housing\n";
		toPrint += getAmountFood() + "$ (" + getPercentFood() + "%) for Food\n";
		toPrint += getAmountUtilities() + "$ (" + getPercentUtilities() + "%) for Utilities\n";
		toPrint += getAmountClothing() + "$ (" + getPercentClothing() + "%) for Clothing\n";
		toPrint += getAmountMedical() + "$ (" + getPercentMedical() + "%) for Medical\n";
		toPrint += getAmountDonations() + "$ (" + getPercentDonations() + "%) for Donations\n";
		toPrint += getAmountSavingsInsurance() + "$ (" + getPercentSavingsInsurance() + "%) for Savings and Insurance\n";
		toPrint += getAmountEntertainment() + "$ (" + getPercentEntertainment() + "%) for Entertainment\n";
		toPrint += getAmountTransportation() + "$ (" + getPercentTransportation() + "%) for Transportation\n";
		toPrint += getAmountMisc() + "$ (" + getPercentMisc() + "%) for Miscellaneous\n";

		return toPrint;
	}

	/**
	 * Writes and object to file
	 * Print to file according to
	 * BudgetingDatabaseFile
	 * AvailableFunds:Amount
	 * Section:Percentage:Amount
	 */
	//Write results to file
	public void writeToFile(){
		calculateAmountsFromPercentages();

		// opening file stream to write log
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream(Constants.BUDGETING_FILE));
		} catch (Exception e) {
			System.out.println("Error while creating file");
			System.exit(1);
		}

		//Print to file according to
		//AvailableFunds:Amount
		//Section:Percentage:Amount
		pw.println("BudgetingDatabaseFile");
		pw.println("AvailableFunds:" + getAvailableFunds());
		pw.println("Housing:" + getPercentHousing() + ":" + getAmountHousing());
		pw.println("Food:" + getPercentFood() + ":" + getAmountFood());
		pw.println("Utilities:" + getPercentUtilities() + ":" + getAmountUtilities());
		pw.println("Clothing:" + getPercentClothing() + ":" + getAmountClothing());
		pw.println("Medical:" + getPercentMedical() + ":" + getAmountMedical());
		pw.println("Donations:" + getPercentDonations() + ":" + getAmountDonations());
		pw.println("SavingsInsurance:" + getPercentSavingsInsurance() + ":" + getAmountSavingsInsurance());
		pw.println("Entertainment:" + getPercentEntertainment() + ":" + getAmountEntertainment());
		pw.println("Transportation:" + getPercentTransportation() + ":" + getAmountTransportation());
		pw.println("Misc:" + getPercentMisc() + ":" + getAmountMisc());

		// Closing file stream
		try {
			pw.close();
		} catch (Exception e) {
			System.out.println("Error while closing file");
			System.exit(1);
		}
	}

	/**
	 * Reads from files that have
	 * BudgetingDatabaseFile
	 * as the first sentence
	 * @param fileName
	 */
	//Read from file
	public void readBudgetingFromFile(String fileName) {
		// Open file to read from
		BufferedReader br = null;
		FileReader fr = null;

		try {
			final String property = System.getProperty("user.dir");
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
		} catch (Exception e) {
			System.out.println("Error when opening file.");
			System.out.println("Program will terminate.");
			System.exit(0);
		}

		//Check to make sure correct file is being read
		String line = null;
		try {
			line = br.readLine();
			if(!line.equalsIgnoreCase("BudgetingDatabaseFile")) {
				System.out.println("Wrong File is being read");
				System.exit(0);
			}	
		}catch (IOException e) {
			System.out.println("Error while reading file");
			System.out.println("Program will terminate.");
			System.exit(0);
		}

		//Read values in the file
		line = null;
		try {
			while ((line = br.readLine()) != null) {
				String[] lineArray = line.split(":");
				switch (lineArray[0]) {
				case "Housing": setPercentHousing(Double.parseDouble(lineArray[1]));
								if(lineArray.length>2)
									setAmountHousing(Double.parseDouble(lineArray[2]));
								break;
				case "Food": setPercentFood(Double.parseDouble(lineArray[1]));
								if(lineArray.length>2)
									setAmountFood(Double.parseDouble(lineArray[2]));
								break;
				case "Utilities": setPercentUtilities(Double.parseDouble(lineArray[1]));
									if(lineArray.length>2)
										setAmountUtilities(Double.parseDouble(lineArray[2]));
									break;
				case "Clothing": setPercentClothing(Double.parseDouble(lineArray[1]));
								if(lineArray.length>2)
									setAmountClothing(Double.parseDouble(lineArray[2]));
								break;
				case "Medical": setPercentMedical(Double.parseDouble(lineArray[1]));
								if(lineArray.length>2)
									setAmountMedical(Double.parseDouble(lineArray[2]));
								break;
				case "Donations": setPercentDonations(Double.parseDouble(lineArray[1]));
								if(lineArray.length>2)
									setAmountDonations(Double.parseDouble(lineArray[2]));
								break;
				case "SavingsInsurance": setPercentSavingsInsurance(Double.parseDouble(lineArray[1]));
								if(lineArray.length>2)
									setAmountSavingsInsurance(Double.parseDouble(lineArray[2]));
								break;
				case "Entertainment": setPercentEntertainment(Double.parseDouble(lineArray[1]));
										if(lineArray.length>2)
											setAmountEntertainment(Double.parseDouble(lineArray[2]));
										break;
				case "Transportation": setPercentTransportation(Double.parseDouble(lineArray[1]));
										if(lineArray.length>2)
											setAmountTransportation(Double.parseDouble(lineArray[2]));
										break;
				case "Misc": setPercentMisc(Double.parseDouble(lineArray[1]));
								if(lineArray.length>2)
									setAmountMisc(Double.parseDouble(lineArray[2]));
								break;
				case "AvailableFunds": setAvailableFunds(Double.parseDouble(lineArray[1]));
									break;
				}
			}
		} catch (IOException e) {
			System.out.println("Error while reading file");
			System.out.println("Program will terminate.");
			System.exit(0);
		}


		// Closing reading stream
		try {
			if (br != null)
				br.close();
			if (fr != null)
				fr.close();
		} catch (Exception e) {
			System.out.println("Error when closing file.");
			System.out.println("Program will terminate.");
			System.exit(0);
		}

	}

	//Methods to calculate the percentages
	public double calculateHousing() {
		amountHousing = percentHousing*availableFunds/100;
		return amountHousing;
	}

	public double calculateFood() {
		amountFood = percentFood*availableFunds/100;
		return amountFood;
	}

	public double calculateUtilities() {
		amountUtilities = percentUtilities*availableFunds/100;
		return amountUtilities;
	}

	public double calculateClothing() {
		amountClothing = percentClothing*availableFunds/100;
		return amountClothing;
	}

	public double calculateMedical() {
		amountMedical = percentMedical*availableFunds/100;
		return amountMedical;
	}

	public double calculateDonations() {
		amountDonations = percentDonations*availableFunds/100;
		return amountDonations;
	}

	public double calculateSavingsInsurance() {
		amountSavingsInsurance = percentSavingsInsurance*availableFunds/100;
		return amountSavingsInsurance;
	}

	public double calculateEntertainment() {
		amountEntertainment = percentEntertainment*availableFunds/100;
		return amountEntertainment;
	}

	public double calculateTransportation() {
		amountTransportation = percentTransportation*availableFunds/100;
		return amountTransportation;
	}

	public double calculateMisc() {
		amountMisc = percentMisc*availableFunds/100;
		return amountMisc;
	}

	//GETTERS AND SETTERS
	public double getPercentHousing() {
		return percentHousing;
	}

	public void setPercentHousing(double percentHousing) {
		this.percentHousing = percentHousing;
	}

	public double getPercentFood() {
		return percentFood;
	}

	public void setPercentFood(double percentFood) {
		this.percentFood = percentFood;
	}

	public double getPercentUtilities() {
		return percentUtilities;
	}

	public void setPercentUtilities(double percentUtilities) {
		this.percentUtilities = percentUtilities;
	}

	public double getPercentClothing() {
		return percentClothing;
	}

	public void setPercentClothing(double percentClothing) {
		this.percentClothing = percentClothing;
	}

	public double getPercentMedical() {
		return percentMedical;
	}

	public void setPercentMedical(double percentMedical) {
		this.percentMedical = percentMedical;
	}

	public double getPercentDonations() {
		return percentDonations;
	}

	public void setPercentDonations(double percentDonations) {
		this.percentDonations = percentDonations;
	}

	public double getPercentSavingsInsurance() {
		return percentSavingsInsurance;
	}

	public void setPercentSavingsInsurance(double percentSavingsInsurance) {
		this.percentSavingsInsurance = percentSavingsInsurance;
	}

	public double getPercentEntertainment() {
		return percentEntertainment;
	}

	public void setPercentEntertainment(double percentEntertainment) {
		this.percentEntertainment = percentEntertainment;
	}

	public double getPercentTransportation() {
		return percentTransportation;
	}

	public void setPercentTransportation(double percentTransportation) {
		this.percentTransportation = percentTransportation;
	}

	public double getPercentMisc() {
		return percentMisc;
	}

	public void setPercentMisc(double percentMisc) {
		this.percentMisc = percentMisc;
	}

	public double getAvailableFunds() {
		return availableFunds;
	}

	public void setAvailableFunds(double availableFunds) {
		this.availableFunds = availableFunds;
	}

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


