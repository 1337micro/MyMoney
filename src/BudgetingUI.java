package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;

public class BudgetingUI implements ActionListener{

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
	        }
		
	}
	

	private NumberFormat amountFormat = NumberFormat.getNumberInstance();
	private double amount = 0;
	JFormattedTextField inputAvailableFunds;
	JLabel label;
	JButton calculateBudget;
   
	/** Add the necessary SWING elements to the Budgeting panel
    *
    */
	private void buildBudgetingDisplayPanel(){

		//Creating text field
	   inputAvailableFunds = new JFormattedTextField(amountFormat);
       inputAvailableFunds.setValue(new Double(amount));
       inputAvailableFunds.setColumns(10);
       
       //Setting the label for text field
       label = new JLabel("Available Funds: ");
       label.setLabelFor(inputAvailableFunds);
       
       //Initializing the panel
       panel = new JPanel();
       panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

       //creating the button
       calculateBudget = new JButton(Constants.BUTTON_CALCULATE_BUDGET);
       //calculateBudget.addActionListener(new AddBudgetListener());
       
       //Adding elements to panel
       panel.add(label);
       panel.add(inputAvailableFunds);
       panel.add(calculateBudget);
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

 
   
}
