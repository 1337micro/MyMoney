package src;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Optional;

import javax.swing.*;

public class BudgetingUI implements ActionListener{

	public JPanel getPanel() {
		return panel;
	}

	JPanel panel;
	private final int FIELD_SIZE = 15;

	/**
	 * Button listening for a click on the "Budgeting" button. It will show or hide the Budgeting panel
	 * @param e SWING argument
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(panel == null) {
			buildBudgetingDisplayPanel();
			addPanelToLayout(panel, Main.getApplicationLayout());
		}

		if (panel.isVisible()){
			panel.setVisible(false);
		} else {
			panel.setVisible(true);
			final Optional<JPanel> panelOptional = Optional
					.ofNullable(Main.getApplicationLayout().getCashSpendingUI().getPanel());
			if (panelOptional.isPresent()) panelOptional.get().setVisible(false);

		}

	}


	private NumberFormat amountFormat = NumberFormat.getNumberInstance();
	private double amount = 0;
	JTextField inputAvailableFunds;
	JTextArea outputField;
	JLabel label;
	JButton calculateBudget;


	/** Add the necessary SWING elements to the Budgeting panel
	 *
	 */
	private void buildBudgetingDisplayPanel(){

		//Creating text field
		inputAvailableFunds = new JTextField();
		inputAvailableFunds.setColumns(10);
	//	inputAvailableFunds.setBounds(5, 5, 10, 5);


		//Setting the label for text field
		label = new JLabel("Available Funds: ");
		label.setLabelFor(inputAvailableFunds);

		//Initializing output field
		outputField = new JTextArea();
		outputField.setEditable(false);
		outputField.setVisible(true); //field is invisible until CalculateBudget button is pushed

		//Initializing the panel
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//creating the button
		calculateBudget = new JButton(Constants.BUTTON_CALCULATE_BUDGET);
		calculateBudget.addActionListener(new CalculateAndDisplayBudget());

		//Adding elements to panel
		panel.add(label);
		panel.add(inputAvailableFunds);
		panel.add(calculateBudget);
		panel.add(outputField);
		for(int i =0; i<10; i++) {
			//very poor solution to making the JTextFields smaller along the vertical axis
			JTextArea workAroundElementToSizeBoxes = new JTextArea();
			workAroundElementToSizeBoxes.setText("");
			workAroundElementToSizeBoxes.setEditable(false);
			panel.add(workAroundElementToSizeBoxes);
		}

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



	/**
	 * Inner class to get input from text field, calculate the budget, then display it
	 */
	public class CalculateAndDisplayBudget implements ActionListener{

		private void displayBudget() {
			//get value from input text field
			//ADD ERROR VERIFICATION STAGE
			String inputText = inputAvailableFunds.getText().trim();
			
			try {
				amount = Double.parseDouble(inputText);
			}
			catch(NumberFormatException e) {
				//If input isn't a number, print error message
				
				outputField.setText("\tPlease input a number\n"
						+"\tUse a period and not a comma\n"
						+"\tDo not use the '$' symbol\n");
				if(!outputField.isVisible()) 
					outputField.setVisible(true);
				return;
			}
					
			//Calculate the budget
			Budgetting budget = new Budgetting();
			budget.setAvailableFunds(amount);

			//Display the results
			outputField.setText(budget.toString());

			//Make the outputField visible if it isn't already
			if(!outputField.isVisible()) 
				outputField.setVisible(true);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			displayBudget();
			
		}

		
	}

}
