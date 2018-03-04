package src;

public class CashSpendingObject {
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

	/*
	 * Constructor
	 */
	public CashSpendingObject(double housing, double food, double utilities, double clothing, double medical, 
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
		}
		else{
			throw new NumberFormatException("Values cannot be below 0$");
		}
	}
	/*
	 * Default Constructor
	 */
	public CashSpendingObject() {
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
	/*
	 * Methods to add to each type
	 */
	public void addAmountHousing(double amount){
		if(amount>=0){
			double temp = this.getAmountHousing();
			if(temp == this.amountHousing){
				temp+=amount;
				setAmountHousing(temp);
			}
		}
		else{
			throw new NumberFormatException();
		}
	}
	public void addAmountFood(double amount){
		if(amount>=0){
			double temp = this.getAmountFood();
			if(temp == this.amountFood){
				temp+=amount;
				setAmountFood(temp);
			}
		}
		else{
			throw new NumberFormatException();
		}
	}
	public void addAmountUtilities(double amount){
		if(amount>=0){
			double temp = this.getAmountUtilities();
			if(temp == this.amountUtilities){
				temp+=amount;
				setAmountUtilities(temp);
			}
		}
		else{
			throw new NumberFormatException();
		}
	}
	public void addAmountClothing(double amount){
		if(amount>=0){
			double temp = this.getAmountClothing();
			if(temp == this.amountClothing){
				temp+=amount;
				setAmountClothing(temp);
			}
		}
		else{
			throw new NumberFormatException();
		}
	}
	public void addAmountMedical(double amount){
		if(amount>=0){
			double temp = this.getAmountMedical();
			if(temp == this.amountMedical){
				temp+=amount;
				setAmountMedical(temp);
			}
		}
		else{
			throw new NumberFormatException();
		}
	}
	public void addAmountDonations(double amount){
		if(amount>=0){
			double temp = this.getAmountDonations();
			if(temp == this.amountDonations){
				temp+=amount;
				setAmountDonations(temp);
			}
		}
		else{
			throw new NumberFormatException();
		}
	}
	public void addAmountSavings(double amount){
		if(amount>=0){
			double temp = this.getAmountSavingsInsurance();
			if(temp == this.amountSavingsInsurance){
				temp+=amount;
				setAmountSavingsInsurance(temp);
			}
		}
		else{
			throw new NumberFormatException();
		}
	}
	public void addAmountEntertainment(double amount){
		if(amount>=0){
			double temp = this.getAmountEntertainment();
			if(temp == this.amountEntertainment){
				temp+=amount;
				setAmountEntertainment(temp);
			}
		}
		else{
			throw new NumberFormatException();
		}
	}
	public void addAmountTransportation(double amount){
		if(amount>=0){
			double temp = this.getAmountTransportation();
			if(temp == this.amountTransportation){
				temp+=amount;
				setAmountTransportation(temp);
			}
		}
		else{
			throw new NumberFormatException();
		}
	}
	public void addAmountMisc(double amount){
		if(amount>=0){
			double temp = this.getAmountMisc();
			if(temp == this.amountMisc){
				temp+=amount;
				setAmountMisc(temp);
			}
		}
		else{
			throw new NumberFormatException();
		}
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
