package src;
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


	public void addPanelToLayout(JPanel jPanel, ApplicationLayout applicationLayout){
		applicationLayout.add(jPanel);
	}






	private class AddCardListener implements ActionListener{

		@Override

		public void actionPerformed(ActionEvent arg0) {

			final Icon Icon = null;
			JFrame frame= new JFrame();
			Cards.CardType [] possibilities= {Cards.CardType.DEBIT,Cards.CardType.CREDIT};
			Cards.CardType type= (Cards.CardType)JOptionPane.showInputDialog(frame, "Please choose the type of card you wish to add"
					,"Addition of a card",JOptionPane.QUESTION_MESSAGE,Icon, possibilities, possibilities[0] );
			String n = "Index Card: " + indexCard;
			if (type==Cards.CardType.DEBIT) {

				JTextField accNumber = new JTextField("Please Enter your account Number (4 numbers)");
				JTextField cardNumber = new JTextField("Please Enter the card Number (5 numbers)");
				JTextField moneyCurrent = new JTextField(n);

				Object [] fields = {accNumber, cardNumber, moneyCurrent};

				JOptionPane.showInputDialog(null, fields, "Debit Card Information", JOptionPane.OK_CANCEL_OPTION);

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
			if (type==Cards.CardType.CREDIT) {
				JTextField accNumber = new JTextField("Please Enter your account Number (4 numbers)");
				JTextField cardNumber = new JTextField("Please Enter the card Number (5 numbers)");
				JTextField moneyCurrent = new JTextField("Please Enter Amount of you already spent");
				JTextField limit = new JTextField("Please enter your credit limit");



				Object [] fields = {accNumber, cardNumber, moneyCurrent, limit};

				JOptionPane.showInputDialog(null, fields, "Credit Card Information", JOptionPane.OK_CANCEL_OPTION );
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




	private class RemoveListener implements ActionListener{
		public Object [] cardNum(ArrayList <Cards> a) {
			Object [] cardNumbers=new Object[a.size()];
			for (int i=0;i< a.size(); i++) {
				cardNumbers[i]=a.get(i).getCardNumber();
			}
			return cardNumbers;
		}

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

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame frame= new JFrame();
			Icon icon = null;
			int cardNumber= (int) JOptionPane.showInputDialog(frame, "Please choose a card to remove", "Removal of a card",JOptionPane.OK_CANCEL_OPTION,icon,cardNum(cards_list),cardNum(cards_list)[0]);
			if(cardNumber>0) {
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



