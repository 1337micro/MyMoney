//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 2: Noemi Lemonnier 40001085
//Description: Application layout class
//--------------------------------------------------------


package src;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class ApplicationLayout extends JFrame{

	private  CashSpendingUI cashSpendingUI;
	private BudgetingUI budgetingUI;
	private MyCardsUI cardsUI;

	public ApplicationLayout() {
	
		// setting a menubar so there can be a header
		JMenuBar menubar = new JMenuBar();
		JMenu txt = new JMenu(Constants.APP_WELCOME_TITLE);
		menubar.add(txt);
		setJMenuBar(menubar);

		//setting the image
		ImageIcon imgPan = new ImageIcon("unicorn.png"); // load the image to a imageIcon
		Image image = imgPan.getImage(); // transform it 
		Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imgPan = new ImageIcon(newimg);  // transform it back
		JLabel imgLab = new JLabel(imgPan);

		// creating the toolbar to put the buttons on the side
		JToolBar vertical = new JToolBar(JToolBar.VERTICAL);
		vertical.setFloatable(false);
		vertical.setMargin(new Insets(Constants.APP_LAYOUT_MARGIN_TOP,
				Constants.APP_LAYOUT_MARGIN_LEFT,
				Constants.APP_LAYOUT_MARGIN_BOTTOM,
				Constants.APP_LAYOUT_MARGIN_RIGHT));

		vertical.setBackground(new Color(204, 204, 205));

		//creating panel for each button so they can be aligned
		JPanel frameTBbutt1 = new JPanel();
		frameTBbutt1.setBackground(new Color(204, 204, 205));
		JPanel frameTBbutt2 = new JPanel();
		frameTBbutt2.setBackground(new Color(204, 204, 205));
		JPanel frameTBbutt3 = new JPanel();
		frameTBbutt3.setBackground(new Color(204, 204, 205));
		JPanel frameTImg = new JPanel();
		frameTImg.setBackground(new Color(204, 204, 205));
		
		//creating buttons and setting their size
		JButton cashspending = new JButton(Constants.BUTTON_SPENDING);
		cashspending.setFont(new Font("Courier New", Font.ITALIC, 14));
		cashspending.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
		CashSpendingUI cashSpendingUI = new CashSpendingUI();
		this.cashSpendingUI = cashSpendingUI;        
		cashspending.addActionListener(cashSpendingUI);

		JButton cards = new JButton(Constants.BUTTON_CARDS);
		cards.setFont(new Font("Courier New", Font.ITALIC, 14));
		cards.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
		MyCardsUI cardsUI = new MyCardsUI();
		this.cardsUI=cardsUI;
		cards.addActionListener(cardsUI);


		JButton budgeting = new JButton(Constants.BUTTON_BUDGET);
		budgeting.setFont(new Font("Courier New", Font.ITALIC, 14));
		budgeting.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
		BudgetingUI budgetingUI = new BudgetingUI();
		this.budgetingUI = budgetingUI;
		budgeting.addActionListener(budgetingUI);


		//adding buttons to their panels
		frameTBbutt1.add(cards);
		frameTBbutt2.add(cashspending);
		frameTBbutt3.add(budgeting);
		frameTImg.add(imgLab);
		//adding panels to the toolbar
		vertical.add(frameTBbutt1);
		vertical.add(frameTBbutt2);
		vertical.add(frameTBbutt3);
		vertical.add(frameTImg);

		//adding toolbar to the frame
		add(vertical, BorderLayout.WEST);

		//setting the version status of the application we can update that in time
		JLabel statusbar = new JLabel(Constants.APP_VERSION);
		add(statusbar, BorderLayout.SOUTH);

		//setting the frame attributes
		setSize(Constants.APP_LAYOUT_WIDTH, Constants.APP_LAYOUT_HEIGHT);
		setResizable(false);
		setTitle(Constants.APP_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public CashSpendingUI getCashSpendingUI() {
		return cashSpendingUI;
	}

	public BudgetingUI getBudgetingUI() {
		return budgetingUI;
	}

	public MyCardsUI getMyCardsUI() {
		return cardsUI;
	}


}


