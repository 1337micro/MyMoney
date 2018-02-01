package src;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.awt.*;


public class MyCardsUI implements ActionListener{

	public JPanel getPanel() {
		return panel;
	}

	// panel and table
	JPanel panel ;
	JPanel panButton;
	JTable table;

	//list of cards
	 private ArrayList <Cards> cards_list = new ArrayList<Cards>();


	//button to add and remove
	JButton addCardButton = new JButton(Constants.BUTTON_ADD_CARD);
	JButton removeCardButton = new JButton(Constants.BUTTON_REMOVE_CARD);

	//array of Cards
	final Cards[][] data = {  
	};
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
		table = new JTable(data, columnNames);

		//Create the scroll pane and add the table to it. 
		@SuppressWarnings("deprecation")
		JScrollPane scrollPane = JTable.createScrollPaneForTable(table);
		scrollPane.setPreferredSize(new Dimension(500, 200));

		TableColumn tc = table.getColumnModel().getColumn(4);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		//Add the scroll pane to this panel.
		
		//panel.setLayout(new GridLayout(1, 0)); 
		
		panel= new JPanel();
		panel.add(scrollPane);
		
		//panButton = new JPanel();
		panel.add(addCardButton);
		panel.add(removeCardButton);
		
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
			
			if (type==Cards.CardType.DEBIT) {
				JTextField cardNumber = new JTextField();
				JTextField moneyCurrent = new JTextField();
				
				Object [] fields = {cardNumber, moneyCurrent};
				
				JOptionPane.showInputDialog(null, fields, "Debit Card Information", JOptionPane.OK_CANCEL_OPTION );
				int cardNum=Integer.parseInt(cardNumber.getText());
				double money = Double.parseDouble(moneyCurrent.getText());
				
				Debit card=new Debit(Cards.CardType.DEBIT, cardNum, money );
				cards_list.add(card);
			}
			else {
				JTextField cardNumber = new JTextField();
				JTextField moneyCurrent = new JTextField();
				JTextField limit = new JTextField();
				JTextField moneyOwed = new JTextField();
				
				
				Object [] fields = {cardNumber, moneyCurrent, limit, moneyOwed};
				
				JOptionPane.showInputDialog(null, fields, "Credit Card Information", JOptionPane.OK_CANCEL_OPTION );
				int cardNum=Integer.parseInt(cardNumber.getText());
				double moneyCur = Double.parseDouble(moneyCurrent.getText());
				int limitCard=Integer.parseInt(limit.getText());
				double moneyOwedCard = Double.parseDouble(moneyOwed.getText());
				
				Credit card=new Credit(Cards.CardType.CREDIT, cardNum, limitCard, moneyCur, moneyOwedCard);
				
				cards_list.add(card);
				
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
		
		public Cards getCardFromAccountNumber(int accountNum, List <Cards> list) {
			Cards card= new Cards();
			for(int i=0; i<list.size();i++) {
				if (list.get(i).getCardNumber()==accountNum) 
					{card= list.get(i);
				break;}
				}
			return card;
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame frame= new JFrame();
			
			
			Icon icon = null;
			int cardNumber=(int)JOptionPane.showInputDialog(frame, "Please choose a card to remove", "Removal of a card",JOptionPane.OK_CANCEL_OPTION,icon,cardNum(cards_list),cardNum(cards_list)[0]);
			
			if(cardNumber>0) {
				int indexCard=cards_list.indexOf(getCardFromAccountNumber(cardNumber,cards_list));
				cards_list.remove(indexCard);
			}
		
		}
	}



}



