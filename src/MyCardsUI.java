package src;
//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001075
//Description: implements the user interface for the cards feature.  
//              
//--------------------------------------------------------

import java.util.ArrayList;
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

	// panel and table
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

	//list of cards
	private ArrayList <Cards> cards_list = new ArrayList<Cards>();


	//button to add and remove
	JButton addCardButton = new JButton(Constants.BUTTON_ADD_CARD);
	JButton removeCardButton = new JButton(Constants.BUTTON_REMOVE_CARD);

	//names of the columns
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
			final Optional<JPanel> panelOptional = Optional
					.ofNullable(Main.getApplicationLayout().getCashSpendingUI().getPanel());
			if (panelOptional.isPresent()) panelOptional.get().setVisible(false);

		}

	}
	/*
	 * method to create the basic layout that is to be displayed for the cards feature
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

		//panButton = new JPanel();
		panel.add(addCardButton);
		panel.add(removeCardButton);

		//reading the MyCards.txt to add anyvalues to the table
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

			//TODO : il faut ajuster le Location de l'image
		

			JFrame frame= new JFrame();
			Cards.CardType [] possibilities= {Cards.CardType.DEBIT,Cards.CardType.CREDIT};
			Cards.CardType type= (Cards.CardType)JOptionPane.showInputDialog(frame, "Please choose the type of card you wish to add"
					,"Addition of a card",JOptionPane.QUESTION_MESSAGE,null, possibilities, possibilities[0] );
			String n = "Index Card: " + indexCard;

			if (type==Cards.CardType.DEBIT) {
				//create a panel and a layout that fits the amount of information required.
				JPanel pane=new JPanel(new GridLayout(3,2));

				//create text fields to input the information
				JTextField accNumber = new JTextField(20);
				JTextField cardNumber = new JTextField(20);
				JTextField moneyCurrent = new JTextField(20);

				//creating labels for the text fields

				JLabel aN= new JLabel("Please enter your account Number (4 numbers)");
				JLabel cN= new JLabel("Please enter the card Number (8 numbers)");
				JLabel mC= new JLabel("Please enter the current amount of money available on the card.");

				//setting the labels to the text fields

				aN.setLabelFor(accNumber);
				cN.setLabelFor(cardNumber);
				mC.setLabelFor(moneyCurrent);

				//adding the elements to the panel
				pane.add(aN);
				pane.add(accNumber);
				pane.add(cN);
				pane.add(cardNumber);
				pane.add(mC);
				pane.add(moneyCurrent);

				//make the option panel appear in order to ask the user for information for the card
							int cardInput=JOptionPane.showConfirmDialog(null, pane, "Debit Card Information", JOptionPane.OK_CANCEL_OPTION);
								if(cardInput != 0){
					JOptionPane.getRootFrame().dispose();
				};
				if(cardInput == 0){ 	 //not equal to the cancel button
					cdtp = Cards.CardType.DEBIT;
					accNb = Integer.parseInt(accNumber.getText());
					cardNumD=Integer.parseInt(cardNumber.getText());
					money = Double.parseDouble(moneyCurrent.getText());

					card = new Debit(cdtp, accNb, cardNumD, money);
					cards_list.add(card);
					Object[] data = {cdtp, accNb, cardNumD, money};
					tableModel.addRow(data);
					//cd = new Debit(cdtp, accNb, cardNumD, money);
					MyCards.writeToFile(card);
				}

			}
			if (type==Cards.CardType.CREDIT) {
				//create a panel and a layout that fits the amount of information required.
				JPanel pane=new JPanel(new GridLayout(4,2));

				//creating labels to go with the textfields
				JLabel aN = new JLabel("Please Enter your account Number (4 numbers)");
				JLabel cN = new JLabel("Please Enter the card Number (8 numbers)");
				JLabel mC = new JLabel("Please Enter Amount of you already spent");
				JLabel lt = new JLabel("Please enter your credit limit");

				//creating text fields to take the input from the user
				JTextField accNumber = new JTextField(20);
				JTextField cardNumber = new JTextField(20);
				JTextField moneyCurrent = new JTextField(20);
				JTextField limit = new JTextField(20);

				//setting the labels to their proper textfield
				aN.setLabelFor(accNumber);
				cN.setLabelFor(cardNumber);
				mC.setLabelFor(moneyCurrent);
				lt.setLabelFor(limit);

				//adding the components to the panel
				pane.add(aN);
				pane.add(accNumber);
				pane.add(cN);
				pane.add(cardNumber);
				pane.add(mC);
				pane.add(moneyCurrent);
				pane.add(lt);
				pane.add(limit);

				//popping up the option panel so that the user can input the information
				int cardInput=JOptionPane.showConfirmDialog(null, pane, "Credit Card Information", JOptionPane.OK_CANCEL_OPTION);
				if(cardInput != 0){
					JOptionPane.getRootFrame().dispose();
				};
				if(cardInput == 0){ //not equal to the cancel button
					cdtp = Cards.CardType.CREDIT;
					accNb = Integer.parseInt(accNumber.getText());
					cardNum=Integer.parseInt(cardNumber.getText());
					moneySpent = Double.parseDouble(moneyCurrent.getText());
					limitCard=Double.parseDouble(limit.getText());


					card = new Credit(cdtp, accNb, cardNum, moneySpent, limitCard);
					cards_list.add(card);
					Object[] data = {cdtp, accNb, cardNum, moneySpent};
					tableModel.addRow(data);
					//cd = new Credit(cdtp, accNb, cardNum, moneySpent, limitCard);
					MyCards.writeToFile(card);
				}

			}
		}
	}


	/*
	 * class to implement the display needed for the removal of cards.
	 */

	private class RemoveListener implements ActionListener{

		/*
		 * returns an array with the card numbers of all the cards present in the array.
		 */

		public Object [] cardNum(ArrayList <Cards> a) {
			Object [] cardNumbers=new Object[a.size()];
			for (int i=0;i< a.size(); i++) {
				cardNumbers[i]=a.get(i).getCardNumber();
			}
			return cardNumbers;
		}

		/*
		 * returns the card object corresponding to the card number and list of cards in the parameters.
		 */
		public int getCardFromAccountNumber(double cardNb, List <Cards> list) {
			for(int i=0; i<list.size();i++) {
				if (list.get(i).getCardNumber()==cardNb) 
					if(list.get(i).getType() == CardType.DEBIT){
						card = new Debit(list.get(i).getType(), list.get(i).getAccNb(), list.get(i).getCardNumber(), list.get(i).getMoneyAvailable());
						int ind = i;
						return ind;
						//return card;

					}
				if (list.get(i).getCardNumber()==cardNb) 
					if(list.get(i).getType() == CardType.CREDIT){
						card = new Credit(list.get(i).getType(), list.get(i).getAccNb(), list.get(i).getCardNumber(), list.get(i).getMoneySpent(), list.get(i).getLimit());
						//return card;
						int ind = i;
						return ind;

					}
				//{card= list.get(i);

			}
			///return null;
			return 0;
		}
		/*
		 * method that activates the display when the user clicks on the remove card button. 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel pane = new JPanel(new GridLayout(4,2));
			JComboBox cardslist = new JComboBox(cardNum(cards_list));
			cardslist.setSize(20, 50);
			pane.add(cardslist);

			//JFrame frame= new JFrame();
			//Icon icon = null;
			int cardNumber= JOptionPane.showConfirmDialog(null, pane, "Please choose a card to remove", JOptionPane.OK_CANCEL_OPTION);
		
			if(cardNumber != JOptionPane.YES_OPTION){
				JOptionPane.getRootFrame().dispose();
			};
			if(cardNumber == JOptionPane.YES_OPTION){ 
				indexCard = getCardFromAccountNumber(cardNumber, cards_list);
				//to remove the card from the database textfile MyCards
				if(cards_list.get(indexCard).getType() == CardType.DEBIT){
					cd = new Debit();
					cd.setAccNb(cards_list.get(indexCard).getAccNb());
					cd.setCardNumber(cards_list.get(indexCard).getCardNumber());
					cd.setType(cards_list.get(indexCard).getType());
					cd.setMoneyAvailable(cards_list.get(indexCard).getMoneyAvailable());
				}
				if(cards_list.get(indexCard).getType() == CardType.CREDIT){
					cd = new Credit();
					cd.setAccNb(cards_list.get(indexCard).getAccNb());
					cd.setCardNumber(cards_list.get(indexCard).getCardNumber());
					cd.setType(cards_list.get(indexCard).getType());
					cd.setLimit(cards_list.get(indexCard).getLimit());
					cd.setMoneySpent(cards_list.get(indexCard).getMoneySpent());
					cd.setMoneyAvailable(cards_list.get(indexCard).getMoneyAvailable());

				}
				try {
					MyCards.removeLine(cd);
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




