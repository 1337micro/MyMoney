//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration2: Noemi Lemonnier, 40001085
//Iteration3: Noemi Lemonnier, 40001085
//Description: CashSpending "view" class
// --------------------------------------------------------
package src;
import java.util.ArrayList;

import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import src.Cards.CardType;
import src.CashSpending.ExpenditureType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class CashSpendingUI implements ActionListener {
	public JPanel getPanel() {
		return panel;
	}
	//declaring attributes 
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
	JPanel panel; // the panel containing all our JTextFields for CashSpending feature
	private static PrintWriter pw = null;
	private static BufferedReader reader;
	private static Cards cardTemp;
	private static String transactions;
	private static int i = 1;
	private static int nbCard;
	protected static JTable table;
	protected static DefaultTableModel tableModel;
	protected static Object[] COLUMN_NAMES = {"Housing", "Food","Utilities","Clothing", "Medical","Donations","Savings","Entertainment","Transportation","Misc"};
	JButton addExpense = new JButton(Constants.BUTTON_ADD_EXPENSE);
	JButton showExpense = new JButton(Constants.BUTTON_SHOW_EXPENSE);
	protected static CashSpending expense = new CashSpending();
	@SuppressWarnings("rawtypes")
	JComboBox listExpense;
	@SuppressWarnings("rawtypes")
	JComboBox boxCards;
	private Dictionary<Integer, ExpenditureType> indexToExpenditureTypeDictionary;

	/**
	 * Button listening for a click on the "Cash Spending" button. It will show or hide the CashSpending panel "thePanel"
	 * @param e SWING argument
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (panel == null) {
			CashSpendingUI();
			addPanelToLayout(panel, AuthentificationUI.getApplicationLayout());
		}

		if (panel.isVisible()){
			panel.setVisible(false);
		} else {
			panel.setVisible(true);
			//hide all other panels that are not cash spending
			final Optional<JPanel> budgetingPanelOptional = Optional
					.ofNullable(AuthentificationUI.getApplicationLayout().getBudgetingUI().getPanel());
			if ( budgetingPanelOptional.isPresent())  budgetingPanelOptional.get().setVisible(false);

			final Optional<JPanel> cardsPanelOptional = Optional
					.ofNullable(AuthentificationUI.getApplicationLayout().getMyCardsUI().getPanel());
			if ( cardsPanelOptional.isPresent()) cardsPanelOptional.get().setVisible(false);

		}
	}


	@SuppressWarnings("serial")
	public void CashSpendingUI() {
		//setting panels
		panel = new JPanel();
		JPanel panTable = new JPanel();
		JPanel panButton = new JPanel();
		JPanel panTxt = new JPanel();

		panel.setBackground(new Color(204, 255, 229));
		panel.setBorder(compound);
		panel.setVisible(false);

		panButton.setBackground(new Color(228, 228, 228));
		panButton.setPreferredSize(new Dimension(750,43));
		panButton.setBorder(raisedbevel);
		panTxt.setBackground(new Color(228, 228, 228));

		JLabel txtCashSp = new JLabel("Please note that you need to load your cards before entering expenses.");
		txtCashSp.setFont(new Font("Arial", Font.BOLD, 14));
		panTxt.add(txtCashSp);
		panTxt.setPreferredSize(new Dimension(750,40));
		panTxt.setBorder(raisedbevel);
		//setting the custom table model to the class I created 
		tableModel =  new DefaultTableModel(COLUMN_NAMES, 0);
		table = new JTable(tableModel){
			public boolean isCellEditable(int row,int column){
				switch(column){ 
				default: return false;  //all other column are not editable
				}  
			}};

			//setting the color of the grid and font of header column titles
			table.setGridColor(new Color(238,239,242));
			table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));

			//making sure the user cannot move around the columns nor edit data 
			table.getTableHeader().setReorderingAllowed(false);
			table.setRowSelectionAllowed(false);

			//Create the scroll pane and add the table to it. 
			@SuppressWarnings("deprecation")
			JScrollPane scrollPane = JTable.createScrollPaneForTable(table);
			scrollPane.setPreferredSize(new Dimension(750, 300));



			addExpense.addActionListener(new AddExpenseListener());
			showExpense.addActionListener(new ShowExpenseListener());

			//setting the panel


			panTable.setBackground(new Color(204, 255, 229));
			panTable.add(scrollPane, BorderLayout.CENTER);


			panButton.add(addExpense);
			panButton.add(showExpense);

			panel.add(panButton);
			panel.add(panTable);
			panel.add(panTxt, BorderLayout.SOUTH);


			indexToExpenditureTypeDictionary = new Hashtable<Integer, ExpenditureType>();
			indexToExpenditureTypeDictionary.put(0, ExpenditureType.HOUSING);
			indexToExpenditureTypeDictionary.put(1, ExpenditureType.FOOD);
			indexToExpenditureTypeDictionary.put(2, ExpenditureType.UTILITIES);
			indexToExpenditureTypeDictionary.put(3, ExpenditureType.CLOTHING);
			indexToExpenditureTypeDictionary.put(4, ExpenditureType.MEDICAL);
			indexToExpenditureTypeDictionary.put(5, ExpenditureType.DONATIONS);
			indexToExpenditureTypeDictionary.put(6, ExpenditureType.SAVINGS);
			indexToExpenditureTypeDictionary.put(7, ExpenditureType.ENTERTAINMENT);
			indexToExpenditureTypeDictionary.put(8, ExpenditureType.TRANSPORTATION);
			indexToExpenditureTypeDictionary.put(9, ExpenditureType.MISC);
	}


	/**Add any JPanel to our application layout
	 *
	 * @param jPanel Any SWING JPanel
	 * @param applicationLayout the application layout object
	 */
	public void addPanelToLayout(JPanel jPanel, ApplicationLayout applicationLayout) {
		applicationLayout.add(jPanel);
	}

	/*
	 * Private class to customize the events that will happen when the user clicks on the add card button
	 */
	private class AddExpenseListener implements ActionListener{
		public Object [] cardNum(ArrayList <Cards> a) {
			Object [] cardNumbers=new Object[a.size()];
			for (int i=0;i< a.size(); i++) {
				cardNumbers[i]=a.get(i).getCardNumber();
			}
			return cardNumbers;
		}

		/*
		 * Displays a window to allow the user to add a card, either credit or debit when the user clicks on the add card button
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {

			//create a panel and a layout that fits the amount of information required.
			JPanel pane=new JPanel(new GridLayout(7,2));

			//create text fields to input the information
			listExpense = new JComboBox<Object>(ExpenditureType.values());
			boxCards = new JComboBox<Object>(cardNum(MyCardsUI.getListCards()));
			if(MyCardsUI.getListCards().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please load your cards on clicking on My Cards tab",Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
				int opt = JOptionPane.CLOSED_OPTION;
				if(opt != 0){
					JOptionPane.getRootFrame().dispose();
				}
			}
			else{
				nbCard = MyCardsUI.getListCards().get(boxCards.getSelectedIndex()).getCardNumber();

				//creating labels for the text fields
				JLabel aN= new JLabel("Select the expense type:");
				aN.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel bN= new JLabel("Enter the amount of money:");
				bN.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel cN= new JLabel("Select the card used for this transaction");
				cN.setFont(new Font("Calibri", Font.BOLD, 14));
				JTextField amountTxt = new JTextField(10);

				//setting the labels to the text fields
				bN.setLabelFor(amountTxt);

				//adding the elements to the panel
				pane.add(aN);
				pane.add(listExpense);
				pane.add(bN);
				pane.add(amountTxt);
				pane.add(cN);
				pane.add(boxCards);


				int option=  JOptionPane.showConfirmDialog(null, pane, "Adding an Expense", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				//if the user clicks on the CANCEL button or Closes the window
				if(option != 0){
					JOptionPane.getRootFrame().dispose();
				};

				//if the user clicks on the YES/OK BUTTON
				if(option == 0){ 	
					try{
						//to get the expenditure choosen and the amount entered
						int index = listExpense.getSelectedIndex();
						int cardIndex = boxCards.getSelectedIndex();
						double amountMoney = Double.parseDouble(amountTxt.getText());
						addingToTheExpenditure(index, cardIndex, amountMoney);

						//add element to the table
						Object[] rowData = {expense.getAmountHousing(), expense.getAmountFood(), expense.getAmountUtilities(), expense.getAmountClothing(), 
								expense.getAmountMedical(), expense.getAmountDonations(), expense.getAmountSavingsInsurance(), expense.getAmountEntertainment(), 
								expense.getAmountTransportation(), expense.getAmountMisc()};
						tableModel.addRow(rowData);
						//to make sure only one line is shown
						if(tableModel.getRowCount()>1){
							tableModel.removeRow(0);
						}
					}
					catch (NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, Constants.INVALID_MSG,Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
						int opt = JOptionPane.CLOSED_OPTION;
						if(opt != 0){
							JOptionPane.getRootFrame().dispose();
						}
					}
				}

			}
		}
	}

	/*
	 * Method used to check the expenditure the user choosed to add money on
	 */
	public void addingToTheExpenditure(int index, int cardIndex, double amount){
		try{
			JPanel pane = new JPanel();
			pane.setBorder(compound);
			pane.setBackground(Color.WHITE);

			//if amount enter is invalid
			if(amount < 0 ){
				String e = "Please enter a valid amount of money!";
				throw new NumberFormatException(e);
			}


			//checks if we are not going over budget 
			if((index >=0 || index<=9) && (amount>=0) && (expense.isOverBudget(amount, indexToExpenditureTypeDictionary.get(index))==true)) {
				//creating labels for the text fields
				String se = String.format("You will go over your budget by spending %s on %s", amount,  indexToExpenditureTypeDictionary.get(index));
				JLabel aN= new JLabel(se);
				aN.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel bN= new JLabel("\nDo you want to add this expense?");
				bN.setFont(new Font("Calibri", Font.BOLD, 14));

				//adding the elements to the panel
				pane.add(aN);
				pane.add(bN);

				// user gets the chose of adding or not the expense
				int option=  JOptionPane.showConfirmDialog(null, pane, "Alert Going Over Budget", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				System.out.println(option);
				//if the user clicks on the YES/OK BUTTON
				if(option == 0){ 
					String n = String.format("!! User went over its budget by doing the transaction of %s in %s with card number %s !!", amount,  indexToExpenditureTypeDictionary.get(index), MyCardsUI.getListCards().get(cardIndex).getCardNumber());
					writeToFile(n);
					addingExpenseTableandFile(cardIndex, index, amount);
				}
				
				//if the user clicks on the CANCEL button or Closes the window
				if(option != 0){
					JOptionPane.getRootFrame().dispose();
				};

			}
			if((index >=0 || index<=9) && (amount>=0) && (expense.isOverBudget(amount, indexToExpenditureTypeDictionary.get(index))==false)){ 
				addingExpenseTableandFile(cardIndex, index, amount);


			}

		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, e.getMessage(),Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
			int opt = JOptionPane.CLOSED_OPTION;
			if(opt != 0){
				JOptionPane.getRootFrame().dispose();
			}
		}
	}

	public void  addingExpenseTableandFile(int cardIndex, int index, double amount){
		
		//setting the Cards cardTemp as the selected card by user
		if(MyCardsUI.getListCards().get(cardIndex).getType() == CardType.DEBIT){
			cardTemp = new Debit();
			cardTemp.setType(MyCardsUI.getListCards().get(cardIndex).getType());
			cardTemp.setCardNumber(MyCardsUI.getListCards().get(cardIndex).getCardNumber());
			cardTemp.setAccNb(MyCardsUI.getListCards().get(cardIndex).getAccNb());
			cardTemp.setMoneyAvailable(MyCardsUI.getListCards().get(cardIndex).getMoneyAvailable());
		}
		if(MyCardsUI.getListCards().get(cardIndex).getType() == CardType.CREDIT){
			cardTemp = new Credit();
			cardTemp.setType(MyCardsUI.getListCards().get(cardIndex).getType());
			cardTemp.setCardNumber(MyCardsUI.getListCards().get(cardIndex).getCardNumber());
			cardTemp.setAccNb(MyCardsUI.getListCards().get(cardIndex).getAccNb());
			cardTemp.setMoneySpent(MyCardsUI.getListCards().get(cardIndex).getMoneySpent());
			cardTemp.setLimit(MyCardsUI.getListCards().get(cardIndex).getLimit());
			cardTemp.setMoneyAvailable(MyCardsUI.getListCards().get(cardIndex).getMoneyAvailable());
		}
		if(MyCardsUI.getListCards().get(cardIndex).getType() == CardType.LOYALTY){
			cardTemp = new LoyaltyCard();
			cardTemp.setType(MyCardsUI.getListCards().get(cardIndex).getType());
			cardTemp.setEmail(MyCardsUI.getListCards().get(cardIndex).getEmail());
			cardTemp.setCardNumber(MyCardsUI.getListCards().get(cardIndex).getCardNumber());
			cardTemp.setPointsAvailable(MyCardsUI.getListCards().get(cardIndex).getPointsAvailable());
			cardTemp.setMoneyAvailable(MyCardsUI.getListCards().get(cardIndex).getMoneyAvailable());
		}
		if(MyCardsUI.getListCards().get(cardIndex).getType() == CardType.BITCOIN){
			cardTemp = new BitcoinCard();
			cardTemp.setType(MyCardsUI.getListCards().get(cardIndex).getType());
			cardTemp.setCardNumber(MyCardsUI.getListCards().get(cardIndex).getCardNumber());
			cardTemp.setAccNb(MyCardsUI.getListCards().get(cardIndex).getAccNb());
			cardTemp.setMoneySpent(MyCardsUI.getListCards().get(cardIndex).getMoneySpent());
			cardTemp.setLimit(MyCardsUI.getListCards().get(cardIndex).getLimit());
			cardTemp.setMoneyAvailable(MyCardsUI.getListCards().get(cardIndex).getMoneyAvailable());
		}
		boolean didItPass;
		//depending on which expenditure the user selected
		switch (index) {
		case 0: 
			//changing the money in the selected card
			didItPass = MyCardsUI.getCardTransactionDone(cardIndex, amount); 
			if(didItPass == true){
				expense.addAmount(amount, ExpenditureType.HOUSING); //adding the expense to the table
				i++; //adding number of operations
				//getting the transaction with format as String
				transactions = MyCards.formatTransactionCards(ExpenditureType.HOUSING, amount, cardTemp.getCardNumber());
				//adding the transaction to the cards_list at the selected card
				MyCardsUI.addToTheList(cardTemp, transactions);
				//writing expense done to the TransactionsDone.txt
				writeToFile(ExpenditureType.HOUSING, amount	, cardTemp.getCardNumber());} 
			break;

		case 1: 
			didItPass = MyCardsUI.getCardTransactionDone(cardIndex, amount);
			if(didItPass == true){
				expense.addAmount(amount, ExpenditureType.FOOD);
				i++;
				transactions = MyCards.formatTransactionCards(ExpenditureType.FOOD, amount, cardTemp.getCardNumber());
				MyCardsUI.addToTheList(cardTemp, transactions);
				writeToFile(ExpenditureType.FOOD, amount, cardTemp.getCardNumber());}
			break;

		case 2: 
			didItPass = MyCardsUI.getCardTransactionDone(cardIndex, amount);
			if(didItPass == true){
				expense.addAmount(amount, ExpenditureType.UTILITIES);
				i++;
				transactions = MyCards.formatTransactionCards(ExpenditureType.UTILITIES, amount, cardTemp.getCardNumber());
				MyCardsUI.addToTheList(cardTemp, transactions);
				writeToFile(ExpenditureType.UTILITIES, amount, cardTemp.getCardNumber());}
			break;

		case 3: 
			didItPass = MyCardsUI.getCardTransactionDone(cardIndex, amount);
			if(didItPass == true){
				expense.addAmount(amount, ExpenditureType.CLOTHING);
				i++;
				transactions = MyCards.formatTransactionCards(ExpenditureType.CLOTHING, amount, cardTemp.getCardNumber());
				MyCardsUI.addToTheList(cardTemp, transactions);
				writeToFile(ExpenditureType.CLOTHING, amount, cardTemp.getCardNumber());}
			break;

		case 4: 
			didItPass = MyCardsUI.getCardTransactionDone(cardIndex, amount);
			if(didItPass == true){
				expense.addAmount(amount, ExpenditureType.MEDICAL);
				i++;
				transactions = MyCards.formatTransactionCards(ExpenditureType.MEDICAL, amount, cardTemp.getCardNumber());
				MyCardsUI.addToTheList(cardTemp, transactions);
				writeToFile(ExpenditureType.MEDICAL, amount, cardTemp.getCardNumber());}
			break;

		case 5: 
			didItPass = MyCardsUI.getCardTransactionDone(cardIndex, amount);
			if(didItPass == true){
				expense.addAmount(amount, ExpenditureType.DONATIONS);
				i++;
				transactions = MyCards.formatTransactionCards(ExpenditureType.DONATIONS, amount, cardTemp.getCardNumber());
				MyCardsUI.addToTheList(cardTemp, transactions);
				writeToFile(ExpenditureType.DONATIONS, amount, cardTemp.getCardNumber());}
			break;

		case 6: 
			didItPass = MyCardsUI.getCardTransactionDone(cardIndex, amount);
			if(didItPass == true){
				expense.addAmount(amount, ExpenditureType.SAVINGS);
				i++;
				transactions = MyCards.formatTransactionCards(ExpenditureType.SAVINGS, amount, cardTemp.getCardNumber());
				MyCardsUI.addToTheList(cardTemp, transactions);
				writeToFile(ExpenditureType.SAVINGS, amount, cardTemp.getCardNumber());}
			break;

		case 7: 
			didItPass = MyCardsUI.getCardTransactionDone(cardIndex, amount);
			if(didItPass == true){
				expense.addAmount(amount, ExpenditureType.ENTERTAINMENT);
				i++;
				transactions = MyCards.formatTransactionCards(ExpenditureType.ENTERTAINMENT, amount, cardTemp.getCardNumber());
				MyCardsUI.addToTheList(cardTemp, transactions);
				writeToFile(ExpenditureType.ENTERTAINMENT, amount, cardTemp.getCardNumber());}
			break;

		case 8: 
			didItPass = MyCardsUI.getCardTransactionDone(cardIndex, amount);
			if(didItPass == true){
				expense.addAmount(amount, ExpenditureType.TRANSPORTATION);
				i++;
				transactions = MyCards.formatTransactionCards(ExpenditureType.TRANSPORTATION, amount, cardTemp.getCardNumber());
				MyCardsUI.addToTheList(cardTemp, transactions);
				writeToFile(ExpenditureType.TRANSPORTATION, amount, cardTemp.getCardNumber());}
			break;

		case 9: 
			didItPass = MyCardsUI.getCardTransactionDone(cardIndex, amount);
			if(didItPass == true){
				expense.addAmount(amount, ExpenditureType.MISC);
				i++;
				transactions = MyCards.formatTransactionCards(ExpenditureType.MISC, amount, cardTemp.getCardNumber());
				MyCardsUI.addToTheList(cardTemp, transactions);
				writeToFile(ExpenditureType.MISC, amount, cardTemp.getCardNumber());}
			break;	
		}
	}
	/*
	 * To show all transactions done
	 */
	private class ShowExpenseListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//JPanel pane=new JPanel(new GridLayout(3,2));
			JPanel pane=new JPanel();
			try{
				//creating labels for the text fields
				//JLabel aN= new JLabel("Here is the list of all transactions done:");
				//aN.setFont(new Font("Calibri", Font.BOLD, 14));

				JTextArea listExp = new JTextArea(3,50); //set size;
				listExp.setText(readFromTheFile()); 
				JScrollPane jp;
				listExp.setEditable(false); //not editable by user
				listExp.setBorder(loweredbevel); //giving bounders
				//listExp.setPreferredSize(new Dimension(700,300));

				jp = new JScrollPane(listExp); //so if many transactions user can scroll
				jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //location of the scroll
				jp.setPreferredSize(new Dimension(750,300));
				//adding the elements to the panel
				//pane.add(aN);
				pane.add(jp);
				pane.setPreferredSize(new Dimension(800, 100));


				int option=  JOptionPane.showConfirmDialog(null, pane, "List of All Expenses Done", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				//if the user clicks on the CANCEL button or Closes the window
				if(option != 0 || option == 0){
					JOptionPane.getRootFrame().dispose();
				};
			} catch (Exception e) {
				System.err.println("Error while clearing the expenses.");
				e.printStackTrace();
			}
		}

	}
	/*
	 * method to read the database textfile
	 */
	public static String readFromTheFile() {

		// Open file to read from
		try {
			reader = new BufferedReader(new FileReader(Constants.TRANSACTIONS_FILE));

		} catch (Exception e) {
			System.out.println("Error when opening file.");
			System.out.println("Program will terminate.");
			System.exit(0);
		}
		try {
			String line = null;
			StringBuffer stringBuffer = new StringBuffer();
			while((line = reader.readLine())!=null){

				stringBuffer.append(line).append("\n");
			}

			return stringBuffer.toString();

		} catch (IOException e) {
			System.out.println("Error while reading file");
			System.out.println("Program will terminate.");
			System.exit(0);
		}


		// Closing reading stream
		try {
			if (reader != null)
				reader.close();

		} catch (Exception e) {
			System.out.println("Error when closing file.");
			System.out.println("Program will terminate.");
			System.exit(0);
		}
		return null;

	}

	/*
	 * method to write to the database textfile with 3 attributes
	 */
	public static void writeToFile(ExpenditureType type, double amountMn, int num){
		// opening file stream to write log
		try {
			pw = new PrintWriter(new FileOutputStream(Constants.TRANSACTIONS_FILE, true));
		} catch (Exception e) {
			System.out.println("Error while creating file");
			System.exit(1);
		}
		String n = String.format("Transaction #%d for expenditure %s with an amount of $ %s paid with card #%d was completed.", i, type, amountMn, num);
		pw.println(n);

		// Closing file stream
		try {
			pw.close();
		} catch (Exception e) {
			System.out.println("Error while closing file");
			System.exit(1);
		}
	}

	/*
	 * method to write to the database textfile with 3 attributes
	 */
	public static void writeToFile(String n){
		// opening file stream to write log
		try {
			pw = new PrintWriter(new FileOutputStream(Constants.TRANSACTIONS_FILE, true));
		} catch (Exception e) {
			System.out.println("Error while creating file");
			System.exit(1);
		}
		pw.println(n);

		// Closing file stream
		try {
			pw.close();
		} catch (Exception e) {
			System.out.println("Error while closing file");
			System.exit(1);
		}
	}

	/*
	 * method to clear the database textfile
	 */
	public static void clearDataBaseTransactionsDone() throws IOException{
		if (Constants.TRANSACTIONS_FILE.exists() && Constants.TRANSACTIONS_FILE.isFile())
		{
			//delete if exists
			Constants.TRANSACTIONS_FILE.delete();
		}
		Constants.TRANSACTIONS_FILE.createNewFile();

	}

	/*
	 * Method to obtain the card Number selected by user when expense added
	 */
	public static int getNbCard() {
		return nbCard;
	}




}



