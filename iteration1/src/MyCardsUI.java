
//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noemi Lemonnier 40001085
//Description: implements the user interface for the cards feature.  
//              
//--------------------------------------------------------
package src;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import src.Cards.CardType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.awt.*;


public class MyCardsUI implements ActionListener{

	public JPanel getPanel() {
		return panel;
	}

	// Panel and table
	Cards cd;
	Cards card;
	DefaultTableModel tableModel;
	int accNb;
	CardType cdtp;
	JPanel panel ;
	JPanel panButton;
	JTable table;
	Object[] data;
	int cardNumD;
	double money;
	int cardNum;
	double moneyCur ;
	double moneySpent;
	double limitCard;
	double moneyOwedCard;
	int indexCard;

	// List of cards
	private ArrayList <Cards> cards_list = new ArrayList<Cards>();


	// Button to add and remove Card
	JButton addCardButton = new JButton(Constants.BUTTON_ADD_CARD);
	JButton removeCardButton = new JButton(Constants.BUTTON_REMOVE_CARD);

	// Names of the columns
	final Object[] columnNames = {"Card type", 
			"Account Number",
			"Card Number",
			"Amount", 
	"Select"};
	
	/*
	 * Method to trigger the display of the cards feature when the user clicks on the MY Cards button on the application.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(panel == null) {
			MyCardsUI();
			addPanelToLayout(panel, Main.getApplicationLayout());
		}

		if (panel.isVisible()){
			panel.setVisible(false);
		} else {
			panel.setVisible(true);
			final Optional<JPanel> cashSpendingOptional = Optional
					.ofNullable(Main.getApplicationLayout().getCashSpendingUI().getPanel());
			if (cashSpendingOptional.isPresent()) cashSpendingOptional.get().setVisible(false);

			final Optional<JPanel> budgetingPanelOptional = Optional
					.ofNullable(Main.getApplicationLayout().getBudgetingUI().getPanel());
			if ( budgetingPanelOptional.isPresent())  budgetingPanelOptional.get().setVisible(false);

		}

	}
	
	/*
	 * Method to create the basic layout that is to be displayed for the cards feature
	 */
	public void MyCardsUI() {
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setRowSelectionAllowed(false);


		//Create the scroll pane and add the table to it. 
		@SuppressWarnings("deprecation")
		JScrollPane scrollPane = JTable.createScrollPaneForTable(table);
		scrollPane.setPreferredSize(new Dimension(500, 200));

		TableColumn tc = table.getColumnModel().getColumn(4);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		
		//Add the scroll pane to this panel.
		panel= new JPanel();
		panel.add(scrollPane);

		panel.add(addCardButton);
		panel.add(removeCardButton);

		//reading the MyCards.txt to add any values to the table
		MyCards.readFromTheFile(cards_list, tableModel);

		addCardButton.addActionListener(new AddCardListener());
		removeCardButton.addActionListener(new RemoveListener());

		panel.setVisible(false);
	}

	/*
	 * Adding panel to layout
	 */
	public void addPanelToLayout(JPanel jPanel, ApplicationLayout applicationLayout){
		applicationLayout.add(jPanel);
	}




	/*
	 * Private class to customize the events that will happen when the user clicks on the add card button
	 */

	private class AddCardListener implements ActionListener{
		/*
		 * Displays a window to allow the user to add a card, either credit or debit when the user clicks on the add card button
		 * 
		 */
		@Override

		public void actionPerformed(ActionEvent arg0) {



			JFrame frame= new JFrame();
			Cards.CardType [] possibilities= {Cards.CardType.DEBIT,Cards.CardType.CREDIT};
			Cards.CardType type= (Cards.CardType)JOptionPane.showInputDialog(frame, "Please choose the type of card you wish to add"
					,"Addition of a card",JOptionPane.QUESTION_MESSAGE,null, possibilities, possibilities[0] );
			String n = "Index Card: " + indexCard;

			if (type==Cards.CardType.DEBIT) {
				// Create a panel and a layout that fits the amount of information required.
				JPanel pane=new JPanel(new GridLayout(3,2));

				// Create text fields to input the information
				JTextField accNumber = new JTextField(20);
				JTextField cardNumber = new JTextField(20);
				JTextField moneyCurrent = new JTextField(20);

				// Creating labels for the text fields

				JLabel aN= new JLabel("Please enter your account Number (4 numbers)");
				JLabel cN= new JLabel("Please enter the card Number (8 numbers)");
				JLabel mC= new JLabel("Please enter the current amount of money available on the card.");

				// Setting the labels to the text fields

				aN.setLabelFor(accNumber);
				cN.setLabelFor(cardNumber);
				mC.setLabelFor(moneyCurrent);

				// Adding the elements to the panel
				pane.add(aN);
				pane.add(accNumber);
				pane.add(cN);
				pane.add(cardNumber);
				pane.add(mC);
				pane.add(moneyCurrent);

				// Make the option panel appear in order to ask the user for information for the card
				int cardInput=JOptionPane.showConfirmDialog(null, pane, "Debit Card Information", JOptionPane.OK_CANCEL_OPTION);
				if(cardInput != 0){
					JOptionPane.getRootFrame().dispose();
				};
				if(cardInput == 0){ 	 //not equal to the cancel button
					try{
						cdtp = Cards.CardType.DEBIT;
						accNb = Integer.parseInt(accNumber.getText());
						cardNum=Integer.parseInt(cardNumber.getText());
						money = Double.parseDouble(moneyCurrent.getText());
						if( accNb <=0 || cardNum <=0 || money <=0){
							throw new NumberFormatException();
						}
						card = new Debit(cdtp, accNb, cardNum, money);
						// Checks if the card already exists
						boolean isDuplicate = MyCards.readToFindDuplicate(card, tableModel);
						if(isDuplicate == true){
							throw new Exception();
						}
						cards_list.add(card);
						Object[] data = {cdtp, accNb, cardNum, money};
						tableModel.addRow(data);
						MyCards.writeToFile(card);

					}catch (IOException e) {
						e.printStackTrace();
					}catch(NumberFormatException evt){
						JOptionPane.showMessageDialog(null, "You have entered an invalid number!" , "Invalid Input", JOptionPane.CLOSED_OPTION);
						int opt = JOptionPane.CLOSED_OPTION;
						if(opt != 0){
							JOptionPane.getRootFrame().dispose();
						}
					}
					catch(Exception err){
						JOptionPane.showMessageDialog(null, "You have entered a card number that already exist!" , "Invalid Input", JOptionPane.CLOSED_OPTION);
						int opt = JOptionPane.CLOSED_OPTION;
						if(opt != 0){
							JOptionPane.getRootFrame().dispose();
						}
					}

				}

			}
			if (type==Cards.CardType.CREDIT) {
				// Create a panel and a layout that fits the amount of information required.
				JPanel pane=new JPanel(new GridLayout(4,2));

				// Creating labels to go with the textfields
				JLabel aN = new JLabel("Please Enter your account Number (4 numbers)");
				JLabel cN = new JLabel("Please Enter the card Number (8 numbers)");
				JLabel mC = new JLabel("Please Enter Amount of you already spent");
				JLabel lt = new JLabel("Please enter your credit limit");

				// Creating text fields to take the input from the user
				JTextField accNumber = new JTextField(20);
				JTextField cardNumber = new JTextField(20);
				JTextField moneyCurrent = new JTextField(20);
				JTextField limit = new JTextField(20);

				// Setting the labels to their proper textfield
				aN.setLabelFor(accNumber);
				cN.setLabelFor(cardNumber);
				mC.setLabelFor(moneyCurrent);
				lt.setLabelFor(limit);

				// Adding the components to the panel
				pane.add(aN);
				pane.add(accNumber);
				pane.add(cN);
				pane.add(cardNumber);
				pane.add(mC);
				pane.add(moneyCurrent);
				pane.add(lt);
				pane.add(limit);

				// Popping up the option panel so that the user can input the information
				int cardInput=JOptionPane.showConfirmDialog(null, pane, "Credit Card Information", JOptionPane.OK_CANCEL_OPTION);
				if(cardInput != 0){
					JOptionPane.getRootFrame().dispose();
				};
				if(cardInput == 0){ //not equal to the cancel button
					try{
						cdtp = Cards.CardType.CREDIT;
						accNb = Integer.parseInt(accNumber.getText());
						cardNum=Integer.parseInt(cardNumber.getText());
						moneySpent = Double.parseDouble(moneyCurrent.getText());
						limitCard=Double.parseDouble(limit.getText());
						if( accNb <=0 || cardNum <=0 || moneySpent <=0 || limitCard <=0){
							throw new NumberFormatException();
						}
						card = new Credit(cdtp, accNb, cardNum, moneySpent, limitCard);
						// Checks if the card already exists
						boolean isDuplicate = MyCards.readToFindDuplicate(card, tableModel);
						if(isDuplicate == true){
							throw new Exception();
						}
						cards_list.add(card);
						Object[] data = {cdtp, accNb, cardNum, moneySpent};
						tableModel.addRow(data);
						MyCards.writeToFile(card);
					}catch (IOException e) {
						e.printStackTrace();
					}catch(NumberFormatException evt){
						JOptionPane.showMessageDialog(null, "You have entered an invalid number!" , "Invalid Input", JOptionPane.CLOSED_OPTION);
						int opt = JOptionPane.CLOSED_OPTION;
						if(opt != 0){
							JOptionPane.getRootFrame().dispose();
						}
					}
					catch(Exception err){
						JOptionPane.showMessageDialog(null, "You have entered a card number that already exist!" , "Invalid Input", JOptionPane.CLOSED_OPTION);
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
	 * Class to implement the display needed for the removal of cards.
	 */
	private class RemoveListener implements ActionListener{

		/*
		 * Returns an array with the card numbers of all the cards present in the array.
		 */
		public Object [] cardNum(ArrayList <Cards> a) {
			Object [] cardNumbers=new Object[a.size()];
			for (int i=0;i< a.size(); i++) {
				cardNumbers[i]=a.get(i).getCardNumber();
			}
			return cardNumbers;
		}

		
		/*
		 * Method that activates the display when the user clicks on the remove card button. 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel pane = new JPanel(new GridLayout(4,2));
			JComboBox cardslist = new JComboBox(cardNum(cards_list));
			cardslist.setSize(20, 50);
			pane.add(cardslist);

			int optionChoosed= JOptionPane.showConfirmDialog(null, pane, "Please choose a card to remove", JOptionPane.OK_CANCEL_OPTION);

			int index = cardslist.getSelectedIndex();
			String line="";
			Cards card = cards_list.get(index);

			if(optionChoosed != JOptionPane.YES_OPTION){
				JOptionPane.getRootFrame().dispose();
			};
			if(optionChoosed == JOptionPane.YES_OPTION){ 
				indexCard = MyCards.getCardFromAccountNumber(card.getCardNumber(), cards_list);
				// To remove the card from the database textfile MyCards.txt
				if(cards_list.get(indexCard).getType() == CardType.DEBIT){
					cd = new Debit();
					cd.setAccNb(cards_list.get(indexCard).getAccNb());
					cd.setCardNumber(cards_list.get(indexCard).getCardNumber());
					cd.setType(cards_list.get(indexCard).getType());
					cd.setMoneyAvailable(cards_list.get(indexCard).getMoneyAvailable());
					line = cards_list.get(indexCard).getType() +","+ cards_list.get(indexCard).getAccNb() +","+ cards_list.get(indexCard).getCardNumber() +","+ cards_list.get(indexCard).getMoneyAvailable();
					System.out.println(line);
				}
				if(cards_list.get(indexCard).getType() == CardType.CREDIT){
					cd = new Credit();
					cd.setAccNb(cards_list.get(indexCard).getAccNb());
					cd.setCardNumber(cards_list.get(indexCard).getCardNumber());
					cd.setType(cards_list.get(indexCard).getType());
					cd.setLimit(cards_list.get(indexCard).getLimit());
					cd.setMoneySpent(cards_list.get(indexCard).getMoneySpent());
					cd.setMoneyAvailable(cards_list.get(indexCard).getMoneyAvailable());
					line = cards_list.get(indexCard).getType() +","+ cards_list.get(indexCard).getAccNb() +","+ cards_list.get(indexCard).getCardNumber() +","+ cards_list.get(indexCard).getMoneySpent()+","+cards_list.get(indexCard).getLimit()+","+ cards_list.get(indexCard).getMoneyAvailable();

				}
				try {
					MyCards.removeLine(line);
					System.out.println("It Removed Card#" + cd.getCardNumber());
				} catch (IOException e1) {
					System.out.println("Error in removing the line");
					e1.printStackTrace();
				}
				cards_list.remove(indexCard);
				tableModel.removeRow(indexCard);
			}
		}
	}
}