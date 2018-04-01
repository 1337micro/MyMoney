package src;
import java.awt.Color;
//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 2: Noemi Lemonnier 40001085
//Description: Class to regroup values that will be used in diverse classes or many times.
// Files location are regrouped here in case they change 
//        
//--------------------------------------------------------
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Constants {
	// Application constants
	public static final int APP_LAYOUT_MARGIN_TOP = 10;
	public static final int APP_LAYOUT_MARGIN_LEFT = 10;
	public static final int APP_LAYOUT_MARGIN_RIGHT = 10;
	public static final int APP_LAYOUT_MARGIN_BOTTOM = 10;
	public static final int APP_LAYOUT_HEIGHT = 500;
	public static final int APP_LAYOUT_WIDTH = 1000;
	public static final String APP_TITLE = "MyMoney Desktop Application";
	public static final String APP_WELCOME_TITLE = "Welcome to " + APP_TITLE;
	public static final String APP_VERSION = "Version 2.0.0";

	//button constants 
	public static final int BUTTON_WIDTH = 150;
	public static final int BUTTON_HEIGHT= 30;
	public static final String BUTTON_BUDGET = "Budgeting";
	public static final String BUTTON_CALCULATE_BUDGET = "Calculate Budget";
	public static final String BUTTON_SHOW_BUDGET = "My Budget";
	public static final String BUTTON_CHANGE_PERCENTAGES = "Change %";
	public static final String BUTTON_CLEAR_PERCENTAGES = "Clear Budget";
	public static final String BUTTON_CARDS = "My Cards";
	public static final String BUTTON_ADD_CARD = "Add a card";
	public static final String BUTTON_REMOVE_CARD = "Remove a card";
	public static final String BUTTON_SPENDING = "Cash Spending";
	public static final String BUTTON_ADD_EXPENSE = "Add an expense";
	public static final String BUTTON_CLEAR_EXPENSE = "Clear all expenses";

	//color constant
	public static final Color CASHSPENDING_COLOR = new Color(204, 255, 229);
	public static final Color MYCARDS_COLOR = new Color(204, 204, 255);
	public static final Color BUDGETING_COLOR = new Color(204, 255, 255);
	public static final Color AUTHENTIFICATION_COLOR = new Color(204, 204, 205);
	
	//invalid constants
	public static final String INVALID_TITLE = "INVALID INPUT";
	public static final String INVALID_MSG = "You have entered an invalid value or a duplicate.\nPlease try again.";
	public static final String INVALID_MSG1 = "You have entered an invalid value. \nPlease try again"; 
	public static final String INVALID_MSG2 = "You do not have a valid budget in our records. \nPlease create a new budget!";
	public static final String INVALID_MSG_OVER_BUDGET = "You have entered a value that is over your budget.";

	// budgetUI constants
	public static final String BGT_TITLE = "SAVE BUDGET";
	public static final String BGT_MSG = "Do you want to save this budget?";
	public static final String BGT_MSG1 = "Do you want to save your personalized percentages?";
	public static final String BGT_MSG2 = "Do you want to delete your budget?";
	public static final String BGT_MSG3 = "WE'RE SORRY! NO EXCISTING BUDGET IN OUR RECORDS";

	//image location
	public static final ImageIcon WARNING_IMAGE = new ImageIcon("warningIcon.png");
	public static final ImageIcon CARDS_IMAGE = new ImageIcon("cardsImage.png");
	public static final Icon CREDIT_IMAGE = new ImageIcon("creditImage.png");
	public static final Icon DEBIT_IMAGE = new ImageIcon("debitImage.png");
	public static final Icon LOYALTY_IMAGE = new ImageIcon("loyaltyImage.png");
	public static ImageIcon IMG_UNICORN = new ImageIcon("unicorn.png");

	//Authentification constants
	public final static File AUTHENTIFICATION_FILE = new File("Authentification.txt");
	public final static File MYCARDS_FILE = new File("MyCards.txt");
	public final static String DEFAULTBUDGETINGPERCENTAGES_FILE = "DefaultBudgetingPercentages.txt";
	public final static String BUDGETING_FILE = "Budgeting.txt";
	public final static File TRANSACTIONS_FILE = new File("TransactionsDone.txt");

	//Budgeting constants
	public final static char SEPARATOR = ':';
	public final static String AVAILABLEFUNDS = "AvailableFunds";
	public final static String HOUSING_TYPE = "Housing";
	public final static String FOOD_TYPE = "Food";
	public final static String UTILITIES_TYPE = "Utilities";
	public final static String CLOTHING_TYPE = "Clothing";
	public final static String MEDICAL_TYPE = "Medical";
	public final static String DONATIONS_TYPE = "Donations";
	public final static String SAVINGSINSURANCE_TYPE = "SavingsInsurance";
	public final static String ENTERTAINMENT_TYPE = "Entertainment";
	public final static String TRANSPORTATION_TYPE = "Transportation";
	public final static String MISC_TYPE = "Misc";
}




