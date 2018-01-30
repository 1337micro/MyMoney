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
		JPanel panel = new JPanel();
		JPanel panButton = new JPanel();
		JTable table = new JTable();

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
			panel.add(scrollPane);
			panButton.add(addCardButton);
			panButton.add(removeCardButton);
			
			addCardButton.addActionListener(new AddCardListener());
			removeCardButton.addActionListener(new RemoveListener());
			
			panel.setVisible(false);
		}


		public void addPanelToLayout(JPanel jPanel, ApplicationLayout applicationLayout){
			applicationLayout.add(jPanel);
		}





		
		private class AddCardListener implements ActionListener{
			
			//creating a pop up for the addition of a debit card
			
			Cards.CardType [] possibilities= {Cards.CardType.DEBIT,Cards.CardType.CREDIT};
			String type= (String)JOptionPane.showInputDialog(frame, "Please choose the type of card you wish to add"
			,"Addition of a card",JOptionPane.QUESTION_MESSAGE, icon, possibilities, Cards.CardType.DEBIT );
			@Override
			public void actionPerformed() {
				
			}
		}
		
		
		
		
		
		private class RemoveListener implements ActionListener{
			
		}

	}


