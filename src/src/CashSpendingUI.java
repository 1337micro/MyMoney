//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration2: Noemi Lemonnier
//Description: CashSpending "view" class
// --------------------------------------------------------
package src;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import src.CashSpending.ExpenditureType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class CashSpendingUI implements ActionListener {
	public JPanel getPanel() {
		return panel;
	}

	//declaring attributes 
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
	JPanel panel; // the panel containing all our JTextFields for CashSpending feature
	protected static JTable table;
	protected static DefaultTableModel tableModel;
	protected static Object[] COLUMN_NAMES = {"HOUSING", "FOOD","UTILITIES","CLOTHING", "MEDICAL","DONATIONS","SAVINGS","ENTERTAINMENT","TRANSPORTATION","MISC"};
	JButton addExpense = new JButton(Constants.BUTTON_ADD_EXPENSE);
	protected static CashSpending expense = new CashSpending();
	@SuppressWarnings("rawtypes")
	JComboBox listExpense;
	@SuppressWarnings("rawtypes")
	JComboBox boxCards;


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


	public void CashSpendingUI() {
		JLabel lab = new JLabel("Please note that you need to save your cards first.");
		//setting the custom table model to the class I created 
		tableModel =  new DefaultTableModel(COLUMN_NAMES, 0);
		table = new JTable(tableModel);
		//setting the color of the grid
		table.setGridColor(new Color(238,239,242));
		//making sure the user cannot move around the columns nor edit data 
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowSelectionAllowed(false);

		//Create the scroll pane and add the table to it. 
		@SuppressWarnings("deprecation")
		JScrollPane scrollPane = JTable.createScrollPaneForTable(table);
		scrollPane.setPreferredSize(new Dimension(750, 300));

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setBackground(new Color(204, 255, 229));
		panel.setBorder(compound);

		addExpense.addActionListener(new AddExpenseListener());

		//setting the panel
		panel= new JPanel();
		JPanel pan2 = new JPanel();
		pan2.setBackground(new Color(204, 255, 229));
		pan2.add(scrollPane, BorderLayout.CENTER);
		JPanel pan3 = new JPanel();
		pan3.setBackground(new Color(204, 255, 229));
		pan3.add(addExpense);
		panel.add(lab);
		panel.add(pan2);
		panel.add(pan3);
		panel.setBorder(compound);
		panel.setBackground(new Color(204, 255, 229));
		panel.setVisible(false);
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
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			UIManager.put("OptionPane.background",new ColorUIResource(204, 255, 229));
			UIManager.put("Panel.background",new ColorUIResource(255, 255, 255));
			//create a panel and a layout that fits the amount of information required.
			JPanel pane=new JPanel(new GridLayout(7,2));

			//create text fields to input the information
			listExpense = new JComboBox(ExpenditureType.values());
			boxCards = new JComboBox(cardNum(MyCardsUI.getListCards()));

			//creating labels for the text fields
			JLabel aN= new JLabel("Please select the expense type:");
			JLabel bN= new JLabel("Please enter the amount of money:");
			JLabel cN= new JLabel("Which card was used for this transaction?");
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
					double amountMoney = Double.parseDouble(amountTxt.getText());
					addingToTheExpenditure(index, amountMoney);

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
	
	/*
	 * Method used to check the expenditure the user choosed to add money on
	 */
	public void addingToTheExpenditure(int index, double temp){
		try{
			if((index >=0 || index<=9) && (temp>=0)){ 
				switch (index) {
				case 0: expense.addAmountHousing(temp);
				break;
				case 1: expense.addAmountFood(temp);
				break;
				case 2: expense.addAmountUtilities(temp);
				break;
				case 3: expense.addAmountClothing(temp);
				break;
				case 4: expense.addAmountMedical(temp);
				break;
				case 5: expense.addAmountDonations(temp);
				break;
				case 6: expense.addAmountSavings(temp);
				break;
				case 7: expense.addAmountEntertainment(temp);
				break;
				case 8: expense.addAmountTransportation(temp);
				break;
				case 9: expense.addAmountMisc(temp);
				break;	
				}
			}
		} catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, Constants.INVALID_MSG,Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
			int opt = JOptionPane.CLOSED_OPTION;
			if(opt != 0){
				JOptionPane.getRootFrame().dispose();
			}
		}
	}

}
