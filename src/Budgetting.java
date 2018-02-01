import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Budgetting {

	//Percentage instance members
	//Getters and Setters are at the end of the document
	double percentHousing;
	double percentFood;
	double percentUtilities;
	double percentClothing;
	double percentMedical;
	double percentDonations;
	double percentSavingsInsurance;
	double percentEntertainement;
	double percentTransportation;
	double percentMisc;

	//Other instance members
	double availableFunds;


	//Default constructor with recommended percentages
	//Based on http://www.leavedebtbehind.com/frugal-living/budgeting/10-recommended-category-percentages-for-your-family-budget/
	public Budgetting() {
		this.percentHousing = 30;
		this.percentFood = 10;
		this.percentUtilities = 7;
		this.percentClothing = 5;
		this.percentMedical = 7;
		this.percentDonations = 10;
		this.percentSavingsInsurance = 7;
		this.percentEntertainement = 7;
		this.percentTransportation = 12;
		this.percentMisc = 5;

		this.availableFunds = 0;
	}

	//Constructor with parameters
	//ERROR testing needed for iteration 2
	public Budgetting(double funds, double housing, double food, double utilities, double clothing, double medical, 
			double donations, double savings, double entertainement, double transportation, double misc) {

		double total = housing + food + utilities + clothing + medical + donations + savings + entertainement + transportation + misc;

		if(total>100) {
			System.out.println("Total exceeds 100%\n");
		}
		if(total<100) {
			//Calculate missing percentage points
			double missing = 100-total;
			setPercentMisc(getPercentMisc() + missing);
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
		this.percentEntertainement = entertainement;
		this.percentTransportation = transportation;
		this.percentMisc = misc;

		this.availableFunds = funds;
	}

	//Methods to calculate the percentages
	public double calculateHousing() {
		return percentHousing*availableFunds/100;
	}

	public double calculateFood() {
		return percentFood*availableFunds/100;
	}

	public double calculateUtilities() {
		return percentUtilities*availableFunds/100;
	}

	public double calculateClothing() {
		return percentClothing*availableFunds/100;
	}

	public double calculateMedical() {
		return percentMedical*availableFunds/100;
	}

	public double calculateDonations() {
		return percentDonations*availableFunds/100;
	}

	public double calculateSavingsInsurance() {
		return percentSavingsInsurance*availableFunds/100;
	}

	public double calculateEntertainement() {
		return percentEntertainement*availableFunds/100;
	}

	public double calculateTransportation() {
		return percentTransportation*availableFunds/100;
	}

	public double calculateMisc() {
		return percentMisc*availableFunds/100;
	}

	//Display
	public String toString(){
		String toPrint = "";

		toPrint += "With " + getAvailableFunds() + "$ in available funds, it is recommended that you spend:\n";
		toPrint += calculateHousing() + "$ (" + getPercentHousing() + "%) for Housing\n";
		toPrint += calculateFood() + "$ (" + getPercentFood() + "%) for Food\n";
		toPrint += calculateUtilities() + "$ (" + getPercentUtilities() + "%) for Utilities\n";
		toPrint += calculateClothing() + "$ (" + getPercentClothing() + "%) for Clothing\n";
		toPrint += calculateMedical() + "$ (" + getPercentMedical() + "%) for Medical\n";
		toPrint += calculateDonations() + "$ (" + getPercentDonations() + "%) for Donations\n";
		toPrint += calculateSavingsInsurance() + "$ (" + getPercentSavingsInsurance() + "%) for Savings and Insurance\n";
		toPrint += calculateEntertainement() + "$ (" + getPercentEntertainement() + "%) for Entertainement\n";
		toPrint += calculateTransportation() + "$ (" + getPercentTransportation() + "%) for Transportation\n";
		toPrint += calculateMisc() + "$ (" + getPercentMisc() + "%) for Miscellaneous\n";

		return toPrint;
	}

	//Write to file
	public void writeToFile(){
		// opening file stream to write log
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream("Budgetting.txt"));
		} catch (Exception e) {
			System.out.println("Error while creating file");
			System.exit(1);
		}
		
		//Print to file according to
		//AvailableFunds:Amount
		//Section:Percentage:Amount
		
		pw.println("AvailableFunds:" + getAvailableFunds());
		pw.println("Housing:" + getPercentHousing() + ":" + calculateHousing());
		pw.println("Food:" + getPercentFood() + ":" + calculateFood());
		pw.println("Utilities:" + getPercentUtilities() + ":" + calculateUtilities());
		pw.println("Clothing:" + getPercentClothing() + ":" + calculateClothing());
		pw.println("Medical:" + getPercentMedical() + ":" + calculateMedical());
		pw.println("SavingsInsurance:" + getPercentSavingsInsurance() + ":" + calculateSavingsInsurance());
		pw.println("Entertainement:" + getPercentEntertainement() + ":" + calculateEntertainement());
		pw.println("Transportation:" + getPercentTransportation() + ":" + calculateTransportation());
		pw.println("Misc:" + getPercentMisc() + ":" + calculateMisc());

		// Closing file stream
		try {
			pw.close();
		} catch (Exception e) {
			System.out.println("Error while closing file");
			System.exit(1);
		}
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

	public double getPercentEntertainement() {
		return percentEntertainement;
	}

	public void setPercentEntertainement(double percentEntertainement) {
		this.percentEntertainement = percentEntertainement;
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



}
