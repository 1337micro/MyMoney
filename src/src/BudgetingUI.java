//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Sabrina Rieck, 40032864
//Iteration 2: Ornela Bregu, 26898580
//Description: BudgetingUI class links the Budgetting class to the interface. 
//				Takes user input and returns value to the interface
//--------------------------------------------------------

package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;


public class BudgetingUI implements ActionListener{

	public JPanel getPanel() {
		return panel;
	}

	//declaring and initializing variables
	JPanel panel;
	UIManager UI;
	protected double av_funds = 0;
	protected double p_housing = 0;
	protected double p_food = 0;
	protected double p_utilities = 0;
	protected double p_clothing = 0;
	protected double p_medical = 0;
	protected double p_donations = 0;
	protected double p_savings = 0;
	protected double p_entertainment = 0;
	protected double p_transportation = 0;
	protected double p_misc = 0;
	JTextField inputAvailableFunds;
	JTextArea outputField;
	JLabel label;
	JButton calculateBudget;
	JButton changePercentages; //add a button to change the percentages for each category
	JButton clearPercentages; //add a button to clear budget and percentages
	JButton showBudget; //add a button to show the personalized budget
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
	Border lineBdr = BorderFactory.createLineBorder(Color.BLACK);
	/**
	 * Button listening for a click on the "Budgeting" button. It will show or hide the Budgeting panel
	 * @param e SWING argument
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(panel == null) {
			buildBudgetingDisplayPanel();
			addPanelToLayout(panel, AuthentificationUI.getApplicationLayout());
		}

		if (panel.isVisible()){
			panel.setVisible(false);
		} else {
			panel.setVisible(true);
			final Optional<JPanel> panelOptional = Optional
					.ofNullable(AuthentificationUI.getApplicationLayout().getCashSpendingUI().getPanel());
			if (panelOptional.isPresent()) panelOptional.get().setVisible(false);

			final Optional<JPanel> cardsPanelOptional = Optional
					.ofNullable(AuthentificationUI.getApplicationLayout().getMyCardsUI().getPanel());
			if ( cardsPanelOptional.isPresent()) cardsPanelOptional.get().setVisible(false);

		}

	}

	/** 
	 * Add the necessary SWING elements to the Budgeting panel
	 */
	private void buildBudgetingDisplayPanel(){

		//Initializing output field
		outputField = new JTextArea();
		outputField.setEditable(false);
		outputField.setVisible(false); //field is invisible until CalculateBudget button is pushed

		//Initializing the panel
		panel = new JPanel();

		

		//setting the background
		panel.setBackground(new Color(204, 255, 255));
		outputField.setBackground(new Color(255, 255, 224));
		outputField.setBorder(lineBdr);
	
		panel.setBorder(compound);

		//creating the buttons
		calculateBudget = new JButton(Constants.BUTTON_CALCULATE_BUDGET);
		calculateBudget.addActionListener(new CalculateAndDisplayBudget());


		changePercentages = new JButton(Constants.BUTTON_CHANGE_PERCENTAGES);
		changePercentages.addActionListener(new DisplayPercentages());

		clearPercentages = new JButton(Constants.BUTTON_CLEAR_PERCENTAGES);
		clearPercentages.addActionListener(new ClearPercentages());

		showBudget = new JButton(Constants.BUTTON_SHOW_BUDGET);
		showBudget.addActionListener(new showPersonalizedBudget());

		//Adding elements to panel
		panel.add(calculateBudget);
		panel.add(changePercentages);
		panel.add(showBudget);
		panel.add(clearPercentages);
		panel.add(outputField, BorderLayout.CENTER);
		panel.setVisible(false);
	}


	/**Add any JPanel to our application layout
	 *
	 * @param jPanel Any SWING jpanel
	 * @param applicationLayout the application layout object
	 */
	public void addPanelToLayout(JPanel jPanel, ApplicationLayout applicationLayout){
		applicationLayout.add(jPanel);
	}

	/*
	 * To display the budget 
	 */
	public class showPersonalizedBudget implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Budgeting budget = new Budgeting(Constants.BUDGETING_FILE);
			double avF = budget.getAvailableFunds();
			if (avF==0)
			{
				JOptionPane.showMessageDialog(null, Constants.INVALID_MSG2,Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
				outputField.setText(Constants.BGT_MSG3);
			}
			else if  (avF!=0) 
			{   outputField.setVisible(true);
				outputField.setText(budget.toString());}

		}
	}

	/*
	 * Method to clear the default percentages and modify them
	 */
	public class ClearPercentages implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int input = JOptionPane.showConfirmDialog(null, Constants.BGT_MSG2,Constants.BGT_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(input != 0){
				JOptionPane.getRootFrame().dispose();
			};
			if(input == 0){ 	
				Budgeting budget = new Budgeting(Constants.DEFAULTBUDGETINGPERCENTAGES_FILE );
				budget.writeToFile();
				outputField.setText(Constants.BGT_MSG3);

			}
		}
	}
	/*
	 * Method to display a window where user can enter new percentages for each field
	 */
	public class DisplayPercentages implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			UIManager.put("OptionPane.background",new ColorUIResource(204, 255, 255));
			UIManager.put("Panel.background",new ColorUIResource(255, 255, 224));
			JPanel panel1=new JPanel(new GridLayout(14,20));


			JLabel availableFunds = new JLabel("Please Enter your Available Funds: ");
			JLabel labelHousing = new JLabel("Housing %:");
			JLabel labelFood = new JLabel("Food %: ");
			JLabel labelUtilities = new JLabel("Utilities %: ");
			JLabel labelClothing = new JLabel("Clothing %: ");
			JLabel labelMedical = new JLabel("Medical %: ");
			JLabel labelDonations = new JLabel("Donations %: ");
			JLabel labelSavings = new JLabel("Savings %: ");
			JLabel labelEntertainment = new JLabel("Entertainment %: ");
			JLabel labelTransportation = new JLabel("Transportation %: ");
			JLabel labelMisc = new JLabel("Misc %: ");

			JTextField inputAvailableFunds = new JTextField(10);
			JTextField inputPercentageHousing = new JTextField(10);
			JTextField inputPercentageFood = new JTextField(10);
			JTextField inputPercentageUtilities = new JTextField(10);
			JTextField inputPercentageClothing = new JTextField(10);
			JTextField inputPercentageMedical = new JTextField(10);
			JTextField inputPercentageDonations = new JTextField(10);
			JTextField inputPercentageSavings = new JTextField(10);
			JTextField inputPercentageEntertainment = new JTextField(10);
			JTextField inputPercentageTransportation = new JTextField(10);
			JTextField inputPercentageMisc = new JTextField(10);

			availableFunds.setLabelFor(inputAvailableFunds);
			labelHousing.setLabelFor(inputPercentageHousing);
			labelFood.setLabelFor(inputPercentageFood);
			labelUtilities.setLabelFor(inputPercentageUtilities);
			labelClothing.setLabelFor(inputPercentageClothing);
			labelMedical.setLabelFor(inputPercentageMedical);
			labelDonations.setLabelFor(inputPercentageDonations);
			labelSavings.setLabelFor(inputPercentageSavings);
			labelEntertainment.setLabelFor(inputPercentageEntertainment);
			labelTransportation.setLabelFor(inputPercentageTransportation);
			labelMisc.setLabelFor(inputPercentageMisc);

			//adding the components to the panel

			panel1.add(availableFunds);
			panel1.add(inputAvailableFunds);
			panel1.add(labelHousing);
			panel1.add(inputPercentageHousing);
			panel1.add(labelFood);
			panel1.add(inputPercentageFood);
			panel1.add(labelUtilities);
			panel1.add(inputPercentageUtilities);
			panel1.add(labelClothing);
			panel1.add(inputPercentageClothing);
			panel1.add(labelMedical);
			panel1.add(inputPercentageMedical);
			panel1.add(labelDonations);
			panel1.add(inputPercentageDonations);
			panel1.add(labelSavings);
			panel1.add(inputPercentageSavings);
			panel1.add(labelEntertainment);
			panel1.add(inputPercentageEntertainment);
			panel1.add(labelTransportation);
			panel1.add(inputPercentageTransportation);
			panel1.add(labelMisc);
			panel1.add(inputPercentageMisc);

			panel1.setBorder(compound);

			//popping up the option panel so that the user can input the information
			int percentageInput=JOptionPane.showConfirmDialog(null, panel1, "Budgetting Percentages", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(percentageInput != 0){
				JOptionPane.getRootFrame().dispose(); };

				if(percentageInput == 0){ 	
					try{
						av_funds = Double.parseDouble(inputAvailableFunds.getText());
						p_housing = Double.parseDouble(inputPercentageHousing.getText());
						p_food = Double.parseDouble(inputPercentageFood.getText());
						p_utilities = Double.parseDouble(inputPercentageUtilities.getText());
						p_clothing = Double.parseDouble(inputPercentageClothing.getText());
						p_medical = Double.parseDouble(inputPercentageMedical.getText());
						p_donations = Double.parseDouble(inputPercentageDonations.getText());
						p_savings = Double.parseDouble(inputPercentageSavings.getText());
						p_entertainment = Double.parseDouble(inputPercentageEntertainment.getText());
						p_transportation = Double.parseDouble(inputPercentageTransportation.getText());
						p_misc = Double.parseDouble(inputPercentageMisc.getText());

						if( !(av_funds > 0 || p_housing > 0 || p_utilities > 0 
								|| p_clothing > 0 || p_medical > 0 || p_donations > 0 || 
								p_savings > 0 || p_entertainment > 0 || p_transportation > 0|| p_misc> 0))
						{
							throw new NumberFormatException();
						}

						Budgeting budget = new Budgeting(av_funds,p_food,p_housing,p_utilities, p_clothing, p_medical, p_donations, p_savings, p_entertainment, p_transportation, p_misc);


						outputField.setText(budget.toString());

						int input = JOptionPane.showConfirmDialog(null, Constants.BGT_MSG,Constants.BGT_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						if(input != 0){
							JOptionPane.getRootFrame().dispose();
						};
						//if the user clicks on the YES/OK BUTTON
						if(input == 0){ 	
							budget.writeToFile();
						}


					}
					catch (NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, Constants.INVALID_MSG,Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
						int opt = JOptionPane.CLOSED_OPTION;
						if(opt != 0){
							JOptionPane.getRootFrame().dispose();
						}
					}
				}}}
	/**
	 * Inner class to get input from text field, calculate the budget, then display it
	 */
	public class CalculateAndDisplayBudget implements ActionListener{
		private void displayBudget() {
			UIManager.put("OptionPane.background",new ColorUIResource(204, 255, 255));
			UIManager.put("Panel.background",new ColorUIResource(255, 255, 224));

			JPanel panel2=new JPanel();
			JTextField inputAFunds = new JTextField(10);
			//Setting the label for text field
			JLabel aF = new JLabel("Please enter your available funds: ");
			aF.setLabelFor(inputAFunds);

			panel2.add(aF);
			panel2.add(inputAFunds);

			int amountInput=JOptionPane.showConfirmDialog(null, panel2, "Available Funds", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(amountInput != 0){
				JOptionPane.getRootFrame().dispose();
			};
			//if the user clicks on the YES/OK BUTTON
			if(amountInput == 0){ 	
				try{
					av_funds = Double.parseDouble(inputAFunds.getText());
					if( !(av_funds > 0)){
						throw new NumberFormatException();
					}

					//Calculate the budget
					Budgeting budget = new Budgeting();
					budget.setAvailableFunds(av_funds);

					//Display the results
					outputField.setVisible(true);
					outputField.setText(budget.toString());
					int input = JOptionPane.showConfirmDialog(null, Constants.BGT_MSG,Constants.BGT_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if(input != 0){
						JOptionPane.getRootFrame().dispose();
					};
					//if the user clicks on the YES/OK BUTTON
					if(input == 0){ 	
						budget.writeToFile();
					}}
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, Constants.INVALID_MSG1,Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
					int opt = JOptionPane.CLOSED_OPTION;
					if(opt != 0){
						JOptionPane.getRootFrame().dispose();
					}}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			displayBudget();
		}
	}
}


