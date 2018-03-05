package src;
//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Genevieve Plante-Brisebois 40003112
//Help received from the Programmer Organizer: Noémi Lemonnier 40001075
//Iteration 2: Noemi Lemonnier 40001085
//Description: implements the user interface for the My Cards feature.  
//            
//--------------------------------------------------------

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.ColorUIResource;
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
	protected static String line = null; 
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);

	//list of cards
	protected static ArrayList <Cards> cards_list = new ArrayList<Cards>();

	//button to add and remove
	JButton addCardButton = new JButton(Constants.BUTTON_ADD_CARD);
	JButton removeCardButton = new JButton(Constants.BUTTON_REMOVE_CARD);

	public static ArrayList<Cards> getListCards(){
		return cards_list;

	}
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

		//setting the custom table model to the class I created 
		tableModel =  new DefaultTableModel(COLUMN_NAMES, 0);
		table = new JTable(tableModel){
			public boolean isCellEditable(int row,int column){
				switch(column){ 
				case 4: return true;   //this will allow the user to click on the checkbox
				default: return false;  //all other column are not editable
				}  
			}}; 


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

			//setting the panel
			panel= new JPanel();
			JPanel pan2 = new JPanel();
			pan2.setBackground(new Color(204, 204, 255));
			pan2.add(scrollPane, BorderLayout.CENTER);
			JPanel pan3 = new JPanel();
			pan3.setBackground(new Color(204, 204, 255));
			pan3.add(addCardButton);
			pan3.add(removeCardButton);
			panel.add(pan2);
			panel.add(pan3);
			panel.setBorder(compound);
			panel.setBackground(new Color(204, 204, 255));
			panel.setVisible(false);

			//reading the MyCards.txt to add any values to the table
			MyCards.readFromTheFile(cards_list, tableModel);

			//adding the buttons and setting their sizes and adding their Listener
			addCardButton.setPreferredSize(new Dimension(150,25));
			removeCardButton.setPreferredSize(new Dimension(150,25));
			addCardButton.addActionListener(new AddCardListener());
			removeCardButton.addActionListener(new RemoveListener());


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
				UIManager.put("OptionPane.background",new ColorUIResource(204, 204, 255));
				UIManager.put("Panel.background",new ColorUIResource(255, 255, 255));
				// getting the location of the checkbox
				tableModel = (DefaultTableModel) e.getSource();
				//String columnName = tableModel.getColumnName(column);
				Boolean checked = (Boolean) tableModel.getValueAt(row, column);
				if (checked) {
					//System.out.println(columnName + ": " + true + "\n" + row );
					//setting the panel for the details of the cards
					Box box1 = Box.createHorizontalBox();
					Box box2 = Box.createHorizontalBox();
					Box box3 = Box.createHorizontalBox();
					Box boxFinal = Box.createVerticalBox();
					JTextField accNm, accAddr;
					JPanel pane=new JPanel();
					pane.setPreferredSize(new Dimension(500, 100));

					if(cards_list.get(row).getType() == Cards.CardType.DEBIT){
						JLabel txt1 = new JLabel ("\tThis is a debit card.");
						box1.add(txt1);

						JLabel txt2 = new JLabel ("Past transactions: ");
						accNm = new JTextField("need to put transactions here");
						accNm.setEditable(false);
						box2.add(txt2);
						box2.add(accNm);

						boxFinal.add(box1);
						boxFinal.add(box2);
						pane.add(boxFinal);

						//displaying
						int option = (int) JOptionPane.showConfirmDialog(null, pane, "Card Information", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
						if(option == 0 || option == -1){
							JOptionPane.getRootFrame().dispose();
							tableModel.setValueAt(false, row, column);
						}

					}
					else if(cards_list.get(row).getType() == Cards.CardType.CREDIT){
						JLabel txt1 = new JLabel ("\tThis is a credit card.");
						box1.add(txt1);

						JLabel txt2 = new JLabel ("Credit Card Limit:    ");
						double lmtCard = (cards_list.get(row).getLimit());
						String lmt = String.valueOf(lmtCard);
						accNm = new JTextField(lmt);
						accNm.setEditable(false);
						box2.add(txt2);
						box2.add(accNm);

						JLabel txt3 = new JLabel ("Past Transactions:");
						accAddr = new JTextField("need to put transactions here");
						accAddr.setEditable(false);
						box3.add(txt3);
						box3.add(accAddr);

						boxFinal.add(box1);
						boxFinal.add(box2);
						boxFinal.add(box3);
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
						JLabel txt1 = new JLabel ("\tThis is a Loyalty card.");
						box1.add(txt1);

						JLabel txt2 = new JLabel ("Your Points Value in Cash: $");
						double pts = (cards_list.get(row).getMoneyAvailable());
						String ptsCash = String.valueOf(pts);
						accNm = new JTextField(ptsCash);
						accNm.setEditable(false);
						box2.add(txt2);
						box2.add(accNm);

						JLabel txt3 = new JLabel ("Past Transactions:");
						accAddr = new JTextField("need to put transactions");
						accAddr.setEditable(false);
						box3.add(txt3);
						box3.add(accAddr);

						boxFinal.add(box1);
						boxFinal.add(box2);
						boxFinal.add(box3);
						pane.add(boxFinal);

						int option = (int) JOptionPane.showConfirmDialog(null, pane, "Card Information", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
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
	 * Private class to customize the events that will happen when the user clicks on the add card button
	 */
	private class AddCardListener implements ActionListener{
		/*
		 * Displays a window to allow the user to add a card, either credit or debit when the user clicks on the add card button
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			UIManager.put("OptionPane.background",new ColorUIResource(204, 204, 255));
			UIManager.put("Panel.background",new ColorUIResource(255, 255, 255));
			Cards.CardType [] possibilities= {Cards.CardType.DEBIT,Cards.CardType.CREDIT, Cards.CardType.LOYALTY};
			Cards.CardType type= (Cards.CardType)JOptionPane.showInputDialog(null, "Please choose the type of card you wish to add"
					,"Addition of a card",JOptionPane.QUESTION_MESSAGE,Constants.CARDS_IMAGE, possibilities, possibilities[0] );
			//if the user select Debit
			if (type==Cards.CardType.DEBIT) {

				//create a panel and a layout that fits the amount of information required.
				JPanel pane=new JPanel(new GridLayout(7,2));

				//create text fields to input the information
				JTextField accNumber = new JTextField(5);
				JTextField cardNumber = new JTextField(10);
				JTextField moneyCurrent = new JTextField(20);


				//creating labels for the text fields
				JLabel aN= new JLabel("Please enter your account Number (4 numbers)");
				JLabel cN= new JLabel("Please enter the card Number (8 numbers)");
				JLabel mC= new JLabel("Please enter the card's current amount of money");

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
				int cardInput=  JOptionPane.showConfirmDialog(null, pane, "Debit Card Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, Constants.DEBIT_IMAGE);

				//if the user clicks on the CANCEL button or Closes the window
				if(cardInput != 0){
					JOptionPane.getRootFrame().dispose();
				};
				//if the user clicks on the YES/OK BUTTON
				if(cardInput == 0){ 	
					try{
						cdtp = Cards.CardType.DEBIT;
						accNb = Integer.parseInt(accNumber.getText());
						cardNum = Integer.parseInt(cardNumber.getText());
						money = Double.parseDouble(moneyCurrent.getText());

						if( !(accNb > 0 || cardNum > 0 || money > 0)){
							throw new NumberFormatException();
						}
						card = new Debit(cdtp, accNb, cardNum, money);
						//if the card already exists
						boolean isDuplicate = MyCards.readToFindDuplicate(card, tableModel);
						if(isDuplicate == true){
							throw new NumberFormatException();
						}
						cards_list.add(card);
						Object[] data = {cdtp, accNb, cardNum, money};
						tableModel.addRow(data);
						MyCards.writeToFile(card);

					}//if the user enters a string or an invalid number or a card duplicate
					catch (NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, Constants.INVALID_MSG,Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
						int opt = JOptionPane.CLOSED_OPTION;
						if(opt != 0){
							JOptionPane.getRootFrame().dispose();
						}
					}

				}

			}

			//if the user select Loyalty
			if (type==Cards.CardType.LOYALTY) {

				//create a panel and a layout that fits the amount of information required.
				JPanel pane=new JPanel(new GridLayout(7,2));

				//create text fields to input the information
				JTextField emailTxt = new JTextField(5);
				JTextField cardNumber = new JTextField(10);
				JTextField ptsAvailable = new JTextField(20);


				//creating labels for the text fields
				JLabel eN= new JLabel("Please enter your account email");
				JLabel cN= new JLabel("Please enter the card Number (8 numbers)");
				JLabel pN= new JLabel("Please enter the card's current amount of points");

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
				Image newimg = image.getScaledInstance(300, 215,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				imgPan = new ImageIcon(newimg);  // transform it back
				//JLabel imgLab = new JLabel(imgPan);

				//make the option panel appear in order to ask the user for information for the card
				int cardInput=  JOptionPane.showConfirmDialog(null, pane, "Loyalty Card Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imgPan);
				//if the user clicks on the CANCEL button or Closes the window
				if(cardInput != 0){
					JOptionPane.getRootFrame().dispose();
				};
				//if the user clicks on the YES/OK BUTTON
				if(cardInput == 0){ 	
					try{
						//making sure the data entered by the user are integers and doubles
						String eml = emailTxt.getText();
						boolean emlBool = MyCards.isValid(eml);
						//if user enters a negative number
						if( emlBool == false){
							throw new IllegalStateException();
						}
						cdtp = Cards.CardType.LOYALTY;
						email = emailTxt.getText();
						cardNum=Integer.parseInt(cardNumber.getText());
						pointsAvailable = Integer.parseInt(ptsAvailable.getText());

						if(cardNum<0 || pointsAvailable<=0){
							throw new NumberFormatException();
						}

						card = new LoyaltyCard(cdtp, email, cardNum, pointsAvailable);

						//if the card already exists
						boolean isDuplicate = MyCards.readToFindDuplicate(card, tableModel);
						if(isDuplicate == true){
							throw new  NumberFormatException();
						}
						cards_list.add(card);
						Object[] data = {cdtp, email, cardNum, pointsAvailable, false};
						tableModel.addRow(data);

						//writing the data that will be added to the database text file
						MyCards.writeToFile(card);
					}
					//if the user enters an invalid number or email or a card duplicate
					catch(IllegalStateException nf){
						JOptionPane.showMessageDialog(null, Constants.INVALID_MSG,Constants.INVALID_TITLE, JOptionPane.WARNING_MESSAGE, Constants.WARNING_IMAGE);
						int opt = JOptionPane.CLOSED_OPTION;
						if(opt != 0){
							JOptionPane.getRootFrame().dispose();
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
			//if the user select Credit
			if (type==Cards.CardType.CREDIT) {
				//create a panel and a layout that fits the amount of information required.
				JPanel pane=new JPanel(new GridLayout(8,10));

				//creating labels to go with the textfields
				JLabel aN = new JLabel("Please Enter your account Number (4 numbers)");
				JLabel cN = new JLabel("Please Enter the card Number (8 numbers)");
				JLabel mC = new JLabel("Please Enter the amount of you already spent");
				JLabel lt = new JLabel("Please enter your credit limit");

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
				int cardInput=JOptionPane.showConfirmDialog(null, pane, "Credit Card Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, Constants.CREDIT_IMAGE);

				//if the user clicks on the CANCEL button or Closes the window
				if(cardInput != 0){
					JOptionPane.getRootFrame().dispose();
				};
				//if the user clicks on the YES/OK BUTTON
				if(cardInput == 0){ 
					try{
						//making sure the data entered by the user are integers and doubles
						cdtp = Cards.CardType.CREDIT;
						accNb = Integer.parseInt(accNumber.getText());
						cardNum = Integer.parseInt(cardNumber.getText());
						moneySpent = Double.parseDouble(moneyCurrent.getText());
						limitCard = Double.parseDouble(limit.getText());

						//if user enters a negative number
						if(accNb<=0 || cardNum<=0 || moneySpent<=0 || limitCard<=0){
							throw new NumberFormatException();
						}

						card = new Credit(cdtp, accNb, cardNum, moneySpent, limitCard);

						//if the card already exists
						boolean isDuplicate = MyCards.readToFindDuplicate(card, tableModel);
						if(isDuplicate == true){
							throw new NumberFormatException();
						}

						cards_list.add(card);
						Object[] data = {cdtp, accNb, cardNum, moneySpent};
						tableModel.addRow(data);

						//writing the data that will be added to the database text file
						MyCards.writeToFile(card);
					}
					//if the user enters a string or an invalid number or a card duplicated
					catch (NumberFormatException  nfe){
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
					//checks if the selected card is a debit one
					if(cards_list.get(indexCard).getType() == CardType.DEBIT){
						//format the String line as the selected card information should appear in the text file 
						line = cards_list.get(indexCard).getType() +","+ cards_list.get(indexCard).getAccNb() +","+ cards_list.get(indexCard).getCardNumber() +","+ cards_list.get(indexCard).getMoneyAvailable();
						System.out.println(line); 
					}
					//checks if the selected card is a credit one
					if(cards_list.get(indexCard).getType() == CardType.CREDIT){
						//format the String line as the selected card information should appear in the text file
						line = cards_list.get(indexCard).getType() +","+ cards_list.get(indexCard).getAccNb() +","+ cards_list.get(indexCard).getCardNumber() +","+ cards_list.get(indexCard).getMoneySpent()+","+cards_list.get(indexCard).getLimit()+","+ cards_list.get(indexCard).getMoneyAvailable();
						System.out.println(line);
					}
					if(cards_list.get(indexCard).getType() == CardType.LOYALTY){
						//format the String line as the selected card information should appear in the text file
						line = cards_list.get(indexCard).getType() +","+ cards_list.get(indexCard).getEmail() +","+ cards_list.get(indexCard).getCardNumber() +","+ cards_list.get(indexCard).getPointsAvailable() + "," + cards_list.get(indexCard).getMoneyAvailable();
						System.out.println(line);
					}
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

}




