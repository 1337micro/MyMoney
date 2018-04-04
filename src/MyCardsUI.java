package src;
//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001075
//Iteration 2: Noemi Lemonnier 40001085
//Description: MyCardsUI.java is a class that display the user interface 
// for the My Cards class.  
//            
//--------------------------------------------------------

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import src.Cards.CardType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Optional;
import java.awt.*;


public class MyCardsUI implements ActionListener{

	public JPanel getPanel() {
		return panel;
	}

	// declaring used attributes
	protected static Object[] COLUMN_NAMES = {"Card type", "Account Number","Card Number", "Amount", "Select"};
	protected static TableColumn tc;
	UIManager UI;
	protected static Cards card;
	protected static DefaultTableModel tableModel;
	protected static JPanel panel;
	protected static JTable table;
	protected static CardType cdtp;
	protected static int accNb;
	protected static String email;
	protected static int cardNum;
	protected static double money;
	protected static double moneySpent;
	protected static double limitCard;
	protected static int pointsAvailable;
	protected static int indexCard;
	protected static int indexDebitCard;
	protected static int indexCreditCard;
	protected static String line = null; 
	@SuppressWarnings("rawtypes")
	protected static JComboBox boxDebitCards;
	@SuppressWarnings("rawtypes")
	protected static JComboBox boxCreditCards;
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
	//list of cards
	protected static ArrayList <Cards> cards_list = new ArrayList<Cards>();
	protected static ArrayList<Cards> creditCard;
	protected static ArrayList<Cards> debitCard;
	//button to add and remove
	JButton addCardButton = new JButton(Constants.BUTTON_ADD_CARD);
	JButton removeCardButton = new JButton(Constants.BUTTON_REMOVE_CARD);
	JButton paiementButton  = new JButton (Constants.BUTTON_PAIEMENT_CARD);



	/*
	 * Method to trigger the display of the cards feature when the user clicks on the MyCards button on the application.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(panel == null) {
			MyCardsUI();
			addPanelToLayout(panel, AuthentificationUI.getApplicationLayout());
		}
		if (panel.isVisible()){
			panel.setVisible(false);
		} else {
			panel.setVisible(true);
			final Optional<JPanel> cashSpendingOptional = Optional
					.ofNullable(AuthentificationUI.getApplicationLayout().getCashSpendingUI().getPanel());
			if (cashSpendingOptional.isPresent()) cashSpendingOptional.get().setVisible(false);

			final Optional<JPanel> budgetingPanelOptional = Optional
					.ofNullable(AuthentificationUI.getApplicationLayout().getBudgetingUI().getPanel());
			if ( budgetingPanelOptional.isPresent())  budgetingPanelOptional.get().setVisible(false);

		}

	}

	/*
	 * method to create the basic layout that is to be displayed for the cards feature
	 */
	@SuppressWarnings("serial")
	public void MyCardsUI(){
		//setting the panel
		panel= new JPanel();
		JPanel panButton = new JPanel();
		JPanel panTable = new JPanel();
		
		//panels background colors
		panTable.setBackground(Constants.MYCARDS_COLOR);
		panButton.setBackground(new Color(228, 228, 228));
		panButton.setPreferredSize(new Dimension(750,43));
		panButton.setBorder(raisedbevel);

		//setting the custom table model to the class I created 
		tableModel =  new DefaultTableModel(COLUMN_NAMES, 0);
		table = new JTable(tableModel){
			public boolean isCellEditable(int row,int column){
				switch(column){ 
				case 4: return true;   //this will allow the user to click on the checkbox
				default: return false;  //all other column are not editable
				}  
			}}; 
			table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));

			//making sure the user cannot move around the columns nor edit data 
			table.getTableHeader().setReorderingAllowed(false);
			table.setRowSelectionAllowed(false);

			//setting the color of the grid
			table.setGridColor(new Color(238,239,242));
			table.getColumnModel().getColumn(4).setMaxWidth(70);
			table.getModel().addTableModelListener(new AddBooleanListener());

			//setting the "checkbox"
			tc = table.getColumnModel().getColumn(4);
			tc.setCellEditor(table.getDefaultEditor(Boolean.class));
			tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));

			//Create the scroll pane and add the table to it. 
			@SuppressWarnings("deprecation")
			JScrollPane scrollPane = JTable.createScrollPaneForTable(table);
			scrollPane.setPreferredSize(new Dimension(750, 300));

			
			panButton.add(addCardButton);
			panButton.add(removeCardButton);
			panButton.add(paiementButton);
			panTable.add(scrollPane, BorderLayout.CENTER);
			
			
			panel.add(panButton);
			panel.add(panTable);
			panel.setBorder(compound);
			panel.setBackground(Constants.MYCARDS_COLOR); //background color
			panel.setVisible(false);

			//reading the MyCards.txt to add any values to the table
			MyCards.readFromTheFile(cards_list, tableModel);

			//adding the buttons and setting their sizes and adding their Listener
			addCardButton.setPreferredSize(new Dimension(150,25));
			removeCardButton.setPreferredSize(new Dimension(150,25));
			paiementButton.setPreferredSize(new Dimension(150,25));
			addCardButton.addActionListener(new AddCardListener());
			removeCardButton.addActionListener(new RemoveListener());
			paiementButton.addActionListener(new PaiementListener());

	}

	/*
	 * Adding panel to layout
	 */
	public void addPanelToLayout(JPanel jPanel, ApplicationLayout applicationLayout){
		applicationLayout.add(jPanel);
	}

	/*
	 * Private class to customize the events that will happen when the user clicks on the "checkbox" in a row in the Select column
	 */
	private class AddBooleanListener implements TableModelListener{
		/*
		 * Displays a new row to show more information about the selected card
		 */
		@Override
		public void tableChanged(TableModelEvent e) {
			int row = e.getFirstRow();
			int column = e.getColumn();
			if (column == 4) {
				//changing the color of the panel and optionpane
				// getting the location of the checkbox
				tableModel = (DefaultTableModel) e.getSource();
				//String columnName = tableModel.getColumnName(column);
				Boolean checked = (Boolean) tableModel.getValueAt(row, column);
				if (checked) {
					//setting the panel for the details of the cards
					Box box1 = Box.createHorizontalBox();
					Box box2 = Box.createHorizontalBox();
					Box box3 = Box.createHorizontalBox();
					Box box4 = Box.createHorizontalBox();
					Box boxFinal = Box.createVerticalBox();
					JTextArea accNm;
					JTextArea transacBox;
					JScrollPane jp;
					JPanel pane=new JPanel();
					pane.setPreferredSize(new Dimension(500, 100));

					if(cards_list.get(row).getType() == Cards.CardType.DEBIT){
						JLabel txt2 = new JLabel ("Transactions done with this card: ");
						txt2.setFont(new Font("Calibri", Font.BOLD, 14)); // set font
						transacBox = new JTextArea(3,30); //set size
						
						String tmpStr = "";
						for(int i=0; i< cards_list.get(row).getList().size(); i++){
							tmpStr += cards_list.get(row).getList().get(i);
						}
						transacBox.setText(tmpStr); // get transactions of the cards
						
						transacBox.setEditable(false); //not editable by user
						transacBox.setBorder(loweredetched); //giving bounders
						jp = new JScrollPane(transacBox); //so if many transactions user can scroll
						jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //location of the scroll
						//layout
						box2.add(txt2);
						box4.add(jp);
						boxFinal.add(box2);
						boxFinal.add(box4);
						pane.add(boxFinal);

						//displaying
						int option = (int) JOptionPane.showConfirmDialog(null, pane, "Card Information", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
						if(option == 0 || option == -1){
							JOptionPane.getRootFrame().dispose();
							tableModel.setValueAt(false, row, column);
						}

					}
					else if(cards_list.get(row).getType() == Cards.CardType.CREDIT){
						JLabel txt2 = new JLabel ("Credit Card Limit:   $");
						txt2.setFont(new Font("Calibri", Font.BOLD, 14)); //set font
						double lmtCard = (cards_list.get(row).getLimit()); //get the card limit
						String lmt = String.valueOf(lmtCard); //get the value
						accNm = new JTextArea(lmt); //set the value
						accNm.setEditable(false); //not editable by user
						accNm.setBorder(raisedbevel);
						//layout
						box1.add(txt2);
						box1.add(accNm);
						JLabel txt3 = new JLabel ("Transactions done with this card: ");
						txt3.setFont(new Font("Calibri", Font.BOLD, 14)); // set font
						transacBox = new JTextArea(3,30); //set size
						
						String tmpStr = "";
						for(int i=0; i< cards_list.get(row).getList().size(); i++){
							tmpStr += cards_list.get(row).getList().get(i);
						}
						transacBox.setText(tmpStr); // get transactions of the cards
						
						transacBox.setEditable(false); //not editable by user
						transacBox.setBorder(loweredetched); //giving bounders
						jp = new JScrollPane(transacBox); //so if many transactions user can scroll
						jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //location of the scroll
						//layout
						box3.add(txt3);
						box4.add(jp);
						boxFinal.add(box1);
						boxFinal.add(box3);
						boxFinal.add(box4);
						pane.add(boxFinal);

						//displaying
						int option = (int) JOptionPane.showConfirmDialog(null, pane, "Card Information", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
						//when user exit the window
						if(option == 0 || option == -1){
							JOptionPane.getRootFrame().dispose();
							tableModel.setValueAt(false, row, column);
						}

					}
					else if(cards_list.get(row).getType() == Cards.CardType.LOYALTY){
						JLabel txt2 = new JLabel ("Your Points Value in Cash: $");
						txt2.setFont(new Font("Calibri", Font.BOLD, 14));
						double pts = (cards_list.get(row).getMoneyAvailable());
						String ptsCash = String.valueOf(pts);
						accNm = new JTextArea(ptsCash);
						accNm.setEditable(false);
						accNm.setBorder(raisedbevel);
						box2.add(txt2);
						box2.add(accNm);

						JLabel txt3 = new JLabel ("Transactions done with this card: ");
						txt3.setFont(new Font("Calibri", Font.BOLD, 14));
						transacBox = new JTextArea(3,30);
						
						String tmpStr = "";
						for(int i=0; i< cards_list.get(row).getList().size(); i++){
							tmpStr += cards_list.get(row).getList().get(i);
						}
						transacBox.setText(tmpStr); // get transactions of the cards
						
						transacBox.setEditable(false);
						transacBox.setBorder(loweredetched);						
						jp = new JScrollPane(transacBox);
						jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						box3.add(txt3);
						box4.add(jp);

						boxFinal.add(box2);
						boxFinal.add(box3);
						boxFinal.add(box4);
						pane.add(boxFinal);

						int option = (int) JOptionPane.showConfirmDialog(null, pane, "Card Information", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
						if(option == 0 || option == -1){
							JOptionPane.getRootFrame().dispose();
							tableModel.setValueAt(false, row, column);
						}
					}
					else if(cards_list.get(row).getType() == CardType.BITCOIN){
						JLabel txt2 = new JLabel ("Bitcoin Exchange Account Card Limit:   $");
						txt2.setFont(new Font("Calibri", Font.BOLD, 14)); //set font
						double lmtCard = (cards_list.get(row).getLimit()); //get the card limit
						String lmt = String.valueOf(lmtCard); //get the value
						accNm = new JTextArea(lmt); //set the value
						accNm.setEditable(false); //not editable by user
						accNm.setBorder(raisedbevel);
						//layout
						box1.add(txt2);
						box1.add(accNm);
						JLabel txt3 = new JLabel ("Transactions done with this card: ");
						txt3.setFont(new Font("Calibri", Font.BOLD, 14)); // set font
						transacBox = new JTextArea(3,30); //set size
						
						String tmpStr = "";
						for(int i=0; i< cards_list.get(row).getList().size(); i++){
							tmpStr += cards_list.get(row).getList().get(i);
						}
						transacBox.setText(tmpStr); // get transactions of the cards
						
						transacBox.setEditable(false); //not editable by user
						transacBox.setBorder(loweredetched); //giving bounders
						jp = new JScrollPane(transacBox); //so if many transactions user can scroll
						jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //location of the scroll
						//layout
						box3.add(txt3);
						box4.add(jp);
						boxFinal.add(box1);
						boxFinal.add(box3);
						boxFinal.add(box4);
						pane.add(boxFinal);

						//displaying
						int option = (int) JOptionPane.showConfirmDialog(null, pane, "Exchange account Card Information", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
						//when user exit the window
						if(option == 0 || option == -1){
							JOptionPane.getRootFrame().dispose();
							tableModel.setValueAt(false, row, column);
						}

					}


				} 
			}

		}

	}

	/*
	 * Private class to customize the events that will happen when the user clicks on the paiement button
	 */
	private class PaiementListener implements ActionListener {
		/* 
		 * returns an array with the card numbers of all debit cards present in the array as Object
		 */
		public Object [] debitNum(ArrayList <Cards> a) {
			debitCard = new ArrayList<Cards>();
			for (int i=0;i< a.size(); i++) {
				if(a.get(i).getType() == Cards.CardType.DEBIT){
					debitCard.add(a.get(i));
				}
			}
			Object [] cardNumbers = new Object[debitCard.size()];
			for (int i=0;i< debitCard.size(); i++) {
				cardNumbers[i] = debitCard.get(i).getCardNumber();
			}
			return cardNumbers;
		}

		/* 
		 * returns an array with the card numbers of all credit cards present in the array as Object
		 */
		public Object [] creditNum(ArrayList <Cards> a) {
			creditCard = new ArrayList<Cards>();
			for (int i=0;i< a.size(); i++) {
				if(a.get(i).getType() == Cards.CardType.CREDIT){
					creditCard.add(a.get(i));
				}
			}
			Object [] cardNumbers = new Object[creditCard.size()];
			for (int i=0;i< creditCard.size(); i++) {
				cardNumbers[i] = creditCard.get(i).getCardNumber();
			}
			return cardNumbers;
		}


		/*
		 * Displays a window to allow the user to make a paiement froma  debit card toa  credit card
		 *
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try{
				//setting the panel
				JPanel pane = new JPanel(new GridLayout(10,5));
				JLabel lab1 = new JLabel("This option is to make a payment to one of your credit cards.");
				JLabel lab2 = new JLabel("Select the debit card");
				boxDebitCards = new JComboBox<Object>(debitNum(cards_list));
				JLabel lab3 = new JLabel("Enter the amount to transfer");
				JTextField txt3 = new JTextField();
				JLabel lab4 = new JLabel("Select the credit card");
				boxCreditCards = new JComboBox<Object>(creditNum(cards_list));
				JLabel lab5 = new JLabel("Do you want to confirm this transaction?");
				pane.add(lab1);
				pane.add(lab2);
				pane.add(boxDebitCards);
				pane.add(lab3);
				pane.add(txt3);
				pane.add(lab4);
				pane.add(boxCreditCards);
				pane.add(lab5);

				//window
				int opt = JOptionPane.showConfirmDialog(null, pane, "Payment of a Credit Card", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				//if user clicks on OK
				if(opt ==0){
					//getting the selected card number by the user for credit and debit cards
					int cardDebitNb = debitCard.get(boxDebitCards.getSelectedIndex()).getCardNumber();
					int cardCreditNb = creditCard.get(boxCreditCards.getSelectedIndex()).getCardNumber();
					//get the amount entered
					int amountToPay = Integer.parseInt(txt3.getText());

					//exception thrown 
					if(amountToPay <0 || txt3.getText() == null || amountToPay>  debitCard.get(boxDebitCards.getSelectedIndex()).getMoneyAvailable()){
						throw new NumberFormatException();
					}

					//to get the index of the card number selected from the existing array list
					indexDebitCard = MyCards.getIndexCardFromAccountNumber(cardDebitNb, cards_list);
					//to get the index of the card number selected from the existing array list
					indexCreditCard = MyCards.getIndexCardFromAccountNumber(cardCreditNb, cards_list);
					String lnDb = getLineFormatTextfile(indexDebitCard);
					String lnCd = getLineFormatTextfile(indexCreditCard);

					//if the card Number selected is at the index obtained by getIndexCardFromAccountNumber in the existing array list
					if(cardDebitNb == cards_list.get(indexDebitCard).getCardNumber() && cardCreditNb == cards_list.get(indexCreditCard).getCardNumber() && amountToPay>0){
						double amountAfterTransactionDebit = cards_list.get(indexDebitCard).getMoneyAvailable() - amountToPay;
						double amountAfterTransactionCredit = cards_list.get(indexCreditCard).getMoneySpent() - amountToPay;
						cards_list.get(indexDebitCard).setMoneyAvailable(amountAfterTransactionDebit);
						cards_list.get(indexCreditCard).setMoneySpent(amountAfterTransactionCredit);
						String lnDb2 = getLineFormatTextfile(indexDebitCard);
						String lnCd2 = getLineFormatTextfile(indexCreditCard);

						//modify the database text file line about that card
						MyCards.modifyFile(lnDb, lnDb2); 
						//modify the database text file line about that card
						MyCards.modifyFile(lnCd, lnCd2); 

						//modify the value in the table
						Object obtDb = amountAfterTransactionDebit;
						table.setValueAt(obtDb, indexDebitCard, 3);
						//modify the value in the table
						Object obtCd = amountAfterTransactionCredit;
						table.setValueAt(obtCd, indexCreditCard, 3);
						//fire the change
						tableModel.fireTableDataChanged();
						String trs = String.format("Payment Transaction with debit card #%s for credit card  #%s with an amount of $ %s was completed.", cards_list.get(indexDebitCard).getCardNumber(), cards_list.get(indexCreditCard).getCardNumber(), amountToPay);
						CashSpendingUI.writeToFile(trs);
						cards_list.get(indexDebitCard).addExpense(trs);
					}
				}
				//if user doesn't click on OK
				if(opt !=0){
					JOptionPane.getRootFrame().dispose();
				}

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, Constants.INVALID_MSG, Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
				int opt = JOptionPane.CLOSED_OPTION;
				if (opt != 0) {
					JOptionPane.getRootFrame().dispose();
				}
			}
		}

	}

	/*
	 * Private class to customize the events that will happen when the user clicks on the add card button
	 */
	private class AddCardListener implements ActionListener {
		/*
		 * Displays a window to allow the user to add a card, either credit or debit when the user clicks on the add card button
		 *
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {

			Cards.CardType[] possibilities = Cards.CardType.values();
			Cards.CardType type = (Cards.CardType) JOptionPane.showInputDialog(null, "Choose  type of card to add\t\t"
					, "Addition of a card", JOptionPane.QUESTION_MESSAGE, Constants.CARDS_IMAGE, possibilities, possibilities[0]);
			//if the user select Debit
			if (type == Cards.CardType.DEBIT) {

				//create a panel and a layout that fits the amount of information required.
				JPanel pane = new JPanel(new GridLayout(7, 2));

				//create text fields to input the information
				JTextField accNumber = new JTextField(5);
				JTextField cardNumber = new JTextField(10);
				JTextField moneyCurrent = new JTextField(20);


				//creating labels for the text fields
				JLabel aN = new JLabel("Enter your account Number (4 numbers)");
				aN.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel cN = new JLabel("Enter the card Number (8 numbers)");
				cN.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel mC = new JLabel("Enter the card's current amount of money");
				mC.setFont(new Font("Calibri", Font.BOLD, 14));

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
				int cardInput = JOptionPane.showConfirmDialog(null, pane, "Debit Card Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, Constants.DEBIT_IMAGE);

				//if the user clicks on the CANCEL button or Closes the window
				if (cardInput != 0) {
					JOptionPane.getRootFrame().dispose();
				}
				;
				//if the user clicks on the YES/OK BUTTON
				if (cardInput == 0) {
					try {
						cdtp = Cards.CardType.DEBIT;
						accNb = Integer.parseInt(accNumber.getText());
						cardNum = Integer.parseInt(cardNumber.getText());
						money = Double.parseDouble(moneyCurrent.getText());

						//checking if attributes have negatif values or outside range accepted values
						if (accNb < 0 || cardNum < 0 || money < 0 || accNb > 9999 || cardNum > 99999999) {
							throw new NumberFormatException();
						}
						card = new Debit(cdtp, accNb, cardNum, money);
						//if the card already exists
						boolean isDuplicate = MyCards.readToFindDuplicate(card, tableModel);
						if (isDuplicate == true) {
							throw new NumberFormatException();
						}
						//adding the card to the arraylist of Cards
						cards_list.add(card);
						Object[] data = {cdtp, accNb, cardNum, money};
						//adding row to the table
						tableModel.addRow(data);
						//writing the data that will be added to the database text file
						MyCards.writeToFile(card);


					}//if the user enters a string or an invalid number or a card duplicate
					catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, Constants.INVALID_MSG, Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
						int opt = JOptionPane.CLOSED_OPTION;
						if (opt != 0) {
							JOptionPane.getRootFrame().dispose();
						}
					}

				}

			}

			//if the user select Loyalty
			if (type == Cards.CardType.LOYALTY) {

				//create a panel and a layout that fits the amount of information required.
				JPanel pane = new JPanel(new GridLayout(7, 2));

				//create text fields to input the information
				JTextField emailTxt = new JTextField(5);
				JTextField cardNumber = new JTextField(10);
				JTextField ptsAvailable = new JTextField(20);


				//creating labels for the text fields
				JLabel eN = new JLabel("Enter your account email");
				eN.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel cN = new JLabel("Enter the card Number (8 numbers)");
				cN.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel pN = new JLabel("Enter the card's current amount of points");
				pN.setFont(new Font("Calibri", Font.BOLD, 14));

				//setting the labels to the text fields
				eN.setLabelFor(emailTxt);
				cN.setLabelFor(cardNumber);
				pN.setLabelFor(ptsAvailable);

				//adding the elements to the panel
				pane.add(eN);
				pane.add(emailTxt);
				pane.add(cN);
				pane.add(cardNumber);
				pane.add(pN);
				pane.add(ptsAvailable);

				//setting the image
				ImageIcon imgPan = new ImageIcon("loyaltyImage.png"); // load the image to a imageIcon
				Image image = imgPan.getImage(); // transform it 
				Image newimg = image.getScaledInstance(300, 215, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
				imgPan = new ImageIcon(newimg);  // transform it back
				//JLabel imgLab = new JLabel(imgPan);

				//make the option panel appear in order to ask the user for information for the card
				int cardInput = JOptionPane.showConfirmDialog(null, pane, "Loyalty Card Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imgPan);
				//if the user clicks on the CANCEL button or Closes the window
				if (cardInput != 0) {
					JOptionPane.getRootFrame().dispose();
				}
				;
				//if the user clicks on the YES/OK BUTTON
				if (cardInput == 0) {
					try {
						//making sure the data entered by the user are integers and doubles
						String eml = emailTxt.getText();
						boolean emlBool = MyCards.isValid(eml);
						//if user enters a negative number
						if (emlBool == false) {
							throw new NumberFormatException();
						}
						cdtp = Cards.CardType.LOYALTY;
						email = emailTxt.getText();
						cardNum = Integer.parseInt(cardNumber.getText());
						pointsAvailable = Integer.parseInt(ptsAvailable.getText());

						//making sure all attributes are valid 
						if (cardNum < 0 || pointsAvailable < 0 || cardNum > 99999999) {
							throw new NumberFormatException();
						}

						card = new LoyaltyCard(cdtp, email, cardNum, pointsAvailable);

						//if the card already exists
						boolean isDuplicate = MyCards.readToFindDuplicate(card, tableModel);
						System.out.println(isDuplicate);
						if (isDuplicate == true) {
							throw new NumberFormatException();
						}
						//adding the card to the arraylist of Cards
						cards_list.add(card);
						Object[] data = {cdtp, email, cardNum, pointsAvailable, false};
						//adding row to the table
						tableModel.addRow(data);
						//writing the data that will be added to the database text file
						MyCards.writeToFile(card);
					}
					//if the user enters an invalid number or email or a card duplicate
					catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, Constants.INVALID_MSG, Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
						int opt = JOptionPane.CLOSED_OPTION;
						if (opt != 0) {
							JOptionPane.getRootFrame().dispose();
						}
					}


				}
			}
			//if the user select Credit
			if (type == Cards.CardType.CREDIT) {
				//create a panel and a layout that fits the amount of information required.
				JPanel pane = new JPanel(new GridLayout(8, 10));

				//creating labels to go with the textfields
				JLabel aN = new JLabel("Enter your account Number (4 numbers)");
				aN.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel cN = new JLabel("Enter the card Number (8 numbers)");
				cN.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel mC = new JLabel("Enter the amount of you already spent");
				mC.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel lt = new JLabel("Enter your credit limit");
				lt.setFont(new Font("Calibri", Font.BOLD, 14));

				//creating text fields to take the input from the user
				JTextField accNumber = new JTextField(5);
				JTextField cardNumber = new JTextField(10);
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
				int cardInput = JOptionPane.showConfirmDialog(null, pane, "Credit Card Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, Constants.CREDIT_IMAGE);

				//if the user clicks on the CANCEL button or Closes the window
				if (cardInput != 0) {
					JOptionPane.getRootFrame().dispose();
				}
				;
				//if the user clicks on the YES/OK BUTTON
				if (cardInput == 0) {
					try {
						//making sure the data entered by the user are integers and doubles
						cdtp = Cards.CardType.CREDIT;
						accNb = Integer.parseInt(accNumber.getText());
						cardNum = Integer.parseInt(cardNumber.getText());
						moneySpent = Double.parseDouble(moneyCurrent.getText());
						limitCard = Double.parseDouble(limit.getText());

						//if user enters a negative number 
						if (accNb < 0 || cardNum < 0 || moneySpent < 0 || limitCard < 0 || accNb > 9999 || cardNum > 99999999) {
							throw new NumberFormatException();
						}

						card = new Credit(cdtp, accNb, cardNum, moneySpent, limitCard);

						//if the card already exists
						boolean isDuplicate = MyCards.readToFindDuplicate(card, tableModel);
						if (isDuplicate == true) {
							throw new NumberFormatException();
						}
						//adding the card to the arraylist of Cards
						cards_list.add(card);
						Object[] data = {cdtp, accNb, cardNum, moneySpent};
						//adding row to the table
						tableModel.addRow(data);
						//writing the data that will be added to the database text file
						MyCards.writeToFile(card);
					}
					//if the user enters a string or an invalid number or a card duplicated
					catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, Constants.INVALID_MSG, Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
						int opt = JOptionPane.CLOSED_OPTION;
						if (opt != 0) {
							JOptionPane.getRootFrame().dispose();
						}
					}

				}
			}
			if (type == CardType.BITCOIN) {
				JPanel pane = new JPanel(new GridLayout(8, 10));

				//creating labels to go with the textfields
				JLabel aN = new JLabel("Enter your Exchange account Number (4 numbers)");
				aN.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel cN = new JLabel("Enter the Exchange password (8 numbers)");
				cN.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel mC = new JLabel("Enter the amount of you already spent in mBTC");
				mC.setFont(new Font("Calibri", Font.BOLD, 14));
				JLabel lt = new JLabel("Enter your bitcoin balance in mBTC");
				lt.setFont(new Font("Calibri", Font.BOLD, 14));

				//creating text fields to take the input from the user
				JTextField accNumber = new JTextField(5);
				JTextField cardNumber = new JTextField(10);
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
				int cardInput = JOptionPane.showConfirmDialog(null, pane, "Bitcoin Card Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, Constants.CREDIT_IMAGE);

				//if the user clicks on the CANCEL button or Closes the window
				if (cardInput != 0) {
					JOptionPane.getRootFrame().dispose();
				}
				;
				//if the user clicks on the YES/OK BUTTON
				if (cardInput == 0) {
					try {
						//making sure the data entered by the user are integers and doubles
						cdtp = CardType.BITCOIN;
						accNb = Integer.parseInt(accNumber.getText());
						cardNum = Integer.parseInt(cardNumber.getText());
						moneySpent = Double.parseDouble(moneyCurrent.getText());
						limitCard = Double.parseDouble(limit.getText());

						//if user enters a negative number
						if (accNb < 0 || cardNum < 0 || moneySpent < 0 || limitCard < 0 || accNb > 9999 || cardNum > 99999999) {
							throw new NumberFormatException();
						}
						card = new BitcoinCard(cdtp, accNb, cardNum, moneySpent, limitCard);

						//if the card already exists
						boolean isDuplicate = MyCards.readToFindDuplicate(card, tableModel);
						if (isDuplicate == true) {
							throw new NumberFormatException();
						}
						//adding the card to the arraylist of Cards
						cards_list.add(card);
						Object[] data = {cdtp, accNb, cardNum, moneySpent};
						//adding row to the table
						tableModel.addRow(data);
						//writing the data that will be added to the database text file
						MyCards.writeToFile(card);
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, Constants.INVALID_MSG, Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
						int opt = JOptionPane.CLOSED_OPTION;
						if (opt != 0) {
							JOptionPane.getRootFrame().dispose();
						}
					}
				}


			}
		}
	}



	/*
	 * class to implement the display needed for the removal of cards.
	 */
	private class RemoveListener implements ActionListener{
		/*
		 * returns an array with the card numbers of all the cards present in the array as Object
		 * this methods is used to get the cardNumber selected to remove it
		 */
		public Object [] cardNum(ArrayList <Cards> a) {
			Object [] cardNumbers=new Object[a.size()];
			for (int i=0;i< a.size(); i++) {
				cardNumbers[i]=a.get(i).getCardNumber();
			}
			return cardNumbers;
		}

		/*
		 * method that activates the display when the user clicks on the remove card button. 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				//getting the selected card number by the user
				int cardNb= (int)JOptionPane.showInputDialog(null, "Please choose the card to remove","Removal of a card",JOptionPane.QUESTION_MESSAGE,Constants.CARDS_IMAGE, cardNum(cards_list), cardNum(cards_list)[0] );

				//to get the index of the card number selected from the existing array list
				indexCard = MyCards.getIndexCardFromAccountNumber(cardNb, cards_list);

				//if the card Number selected is at the index obtained by getIndexCardFromAccountNumber in the existing array list
				if(cardNb == cards_list.get(indexCard).getCardNumber()){
					line = getLineFormatTextfile(indexCard);
					try {
						//calls the method in MyCards to remove the selected card in the Text File
						MyCards.removeLine(line);
						System.out.println("It Removed Card#" + cards_list.get(indexCard).getCardNumber());
					} catch (IOException e1) {
						System.out.println("Error in removing the line");
						e1.printStackTrace();
					}
					//to remove the selected Card from the array list and from the table
					cards_list.remove(indexCard);
					tableModel.removeRow(indexCard);
				}
			}
			//if the user clicks on Cancel button or Exit the JOptionPane without removing any cards
			catch(NullPointerException err){
				JOptionPane.getRootFrame().dispose();
			}
		}
	}
	/*
	 * Method to apply the change in the card information 
	 */
	public static boolean getCardTransactionDone(int index, double amount){
		try{ 
			String ln = getLineFormatTextfile(index);
			if(cards_list.get(index).getType().equals(CardType.DEBIT)){
				if(cards_list.get(index).getMoneyAvailable() >= amount){ //if enough money in the account
					//set new amount money spent
					double newAmount = cards_list.get(index).getMoneyAvailable() - amount;
					cards_list.get(index).setMoneyAvailable(newAmount);	
					//Format the new expense
					String ln2 = getLineFormatTextfile(index); 
					//modify the database text file line about that card
					MyCards.modifyFile(ln, ln2); 
					//modify the value in the table
					Object obt = newAmount;
					table.setValueAt(obt, index, 3);
					//fire the change
					tableModel.fireTableDataChanged();
					return true;
				}
				else{
					throw new NumberFormatException();
				}
			}
			else if(cards_list.get(index).getType().equals(CardType.CREDIT)){
				//calculate money spent and what is left
				double totalSpent = cards_list.get(index).getMoneySpent() + amount;
				double newMoneyAvailable = cards_list.get(index).getLimit() - totalSpent;
				if(cards_list.get(index).getMoneyAvailable() >= amount){ //if enough money in the account
					//set new amount money spent and what is left between limit and money spent
					cards_list.get(index).setMoneySpent(totalSpent);
					cards_list.get(index).setMoneyAvailable(newMoneyAvailable);
					//Format the new expense
					String ln2 = getLineFormatTextfile(index);
					//modify the database text file line about that card
					MyCards.modifyFile(ln, ln2);
					//modify the value in the table
					Object obt = totalSpent;
					table.setValueAt(obt, index, 3);
					//fire the change
					tableModel.fireTableDataChanged();
					return true;
				}
				else{
					throw new NumberFormatException();
				}

			}
			else if(cards_list.get(index).getType().equals(CardType.LOYALTY)){
				int totalPoints = LoyaltyCard.moneyInPoints(amount);

				if(cards_list.get(index).getPointsAvailable() >= totalPoints){ //if enough money in the account
					//calculate what is left of the points
					int whatIsLeft = cards_list.get(index).getPointsAvailable() - totalPoints;
					//transform points in money
					double pointsInMoney = whatIsLeft/100;
					//set new amount points and new amount money
					cards_list.get(index).setPointsAvailable(whatIsLeft);
					cards_list.get(index).setMoneyAvailable(pointsInMoney);
					//Format the new expense
					String ln2 = getLineFormatTextfile(index);
					//modify the database text file line about that card
					MyCards.modifyFile(ln, ln2);
					//modify the value in the table
					Object obt = whatIsLeft;
					table.setValueAt(obt, index, 3);
					//fire the change
					tableModel.fireTableDataChanged();
					return true;
				}
				else{
					throw new NumberFormatException();
				}
			}
			else if(cards_list.get(index).getType().equals(CardType.BITCOIN)){
				//calculate money spent and what is left
				double totalSpent = cards_list.get(index).getMoneySpent() + amount;
				double newMoneyAvailable = cards_list.get(index).getLimit() - totalSpent;
				if(cards_list.get(index).getMoneyAvailable() >= amount){ //if enough money in the account
					//set new amount money spent and what is left between limit and money spent
					cards_list.get(index).setMoneySpent(totalSpent);
					cards_list.get(index).setMoneyAvailable(newMoneyAvailable);
					//Format the new expense
					String ln2 = getLineFormatTextfile(index);
					//modify the database text file line about that card
					MyCards.modifyFile(ln, ln2);
					//modify the value in the table
					Object obt = totalSpent;
					table.setValueAt(obt, index, 3);
					//fire the change
					tableModel.fireTableDataChanged();
					return true;
				}
				else{
					throw new NumberFormatException();
				}

			}
			// if the card number is does not match
		} catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "You have exceeded your account money!\nThis transaction was not added.",Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
			int opt = JOptionPane.CLOSED_OPTION;
			if(opt != 0){
				JOptionPane.getRootFrame().dispose();
			}
		}
		return false;
	}

	/*
	 * Method to obtain the card information as presented in the database textfile "MyCards.txt"
	 */
	public static String getLineFormatTextfile(int indexCard){
		//checks if the selected card is a debit one
		if(cards_list.get(indexCard).getType() == CardType.DEBIT){
			//format the String line as the selected card information should appear in the text file 
			line = cards_list.get(indexCard).getType() +","+ cards_list.get(indexCard).getAccNb() +","+ cards_list.get(indexCard).getCardNumber() +","+ cards_list.get(indexCard).getMoneyAvailable(); 
			return line;
		}
		//checks if the selected card is a credit one
		if(cards_list.get(indexCard).getType() == CardType.CREDIT){
			//format the String line as the selected card information should appear in the text file
			line = cards_list.get(indexCard).getType() +","+ cards_list.get(indexCard).getAccNb() +","+ cards_list.get(indexCard).getCardNumber() +","+ cards_list.get(indexCard).getMoneySpent()+","+cards_list.get(indexCard).getLimit()+","+ cards_list.get(indexCard).getMoneyAvailable();
			return line;
		}
		if(cards_list.get(indexCard).getType() == CardType.LOYALTY){
			//format the String line as the selected card information should appear in the text file
			line = cards_list.get(indexCard).getType() +","+ cards_list.get(indexCard).getEmail() +","+ cards_list.get(indexCard).getCardNumber() +","+ cards_list.get(indexCard).getPointsAvailable() + "," + cards_list.get(indexCard).getMoneyAvailable();
			return line;
		}
		if(cards_list.get(indexCard).getType() == CardType.BITCOIN){
			//format the String line as the selected card information should appear in the text file
			line = cards_list.get(indexCard).getType() +","+ cards_list.get(indexCard).getAccNb() +","+ cards_list.get(indexCard).getCardNumber() +","+ cards_list.get(indexCard).getMoneySpent()+","+cards_list.get(indexCard).getLimit()+","+ cards_list.get(indexCard).getMoneyAvailable();
			return line;
		}
		return null;
	}

	/*
	 * method to add expenses 
	 */
	public static void addToTheList(Cards cardTmp,  String n){
		List<String> temp_lst= new ArrayList<>();
		for(int i=0; i < cards_list.size(); i++){
			//if a card already in the list has the same card number as the input one
			if(cards_list.get(i).getCardNumber() == cardTmp.getCardNumber()){ 
				//set temp_lst as the initial list
				temp_lst = cards_list.get(i).getList();
				// add to it the new expense
				temp_lst.add(n);
				//set temp_lst as new list of cards object
				cardTmp.setList(temp_lst);
				break;
			}
		}
	}

	/*
	 * Get and Set for ArrayList <Cards> cards_list
	 */
	public static ArrayList<Cards> getListCards(){
		return cards_list;
	}
	public static void setLst_string(List<String> list, int index) {
		cards_list.get(index).setList(list);
	}
}







