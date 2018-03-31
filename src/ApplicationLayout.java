//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 2: Noemi Lemonnier 40001085
//Description: ApplicationLayout.java is a class used to do the presentation of the main menu of the MyMoney App
//It has 3 buttons to have access to the 3 features of the application: MyCards, CashSpending, Budgeting
//--------------------------------------------------------


package src;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class ApplicationLayout extends JFrame{
	// declaring attributes
	private  CashSpendingUI cashSpendingUI;
	private BudgetingUI budgetingUI;
	private MyCardsUI cardsUI;

	public ApplicationLayout() {

		// setting a menubar and its header
		JMenuBar menubar = new JMenuBar();
		JMenu txt = new JMenu(Constants.APP_WELCOME_TITLE);
		menubar.add(txt);
		setJMenuBar(menubar);

		//setting the Unicorn image
		Image image = Constants.IMG_UNICORN.getImage();  //transforming the type of imgPan
		Image newimg = image.getScaledInstance(100, 100,java.awt.Image.SCALE_SMOOTH); //resizing the image   
		Constants.IMG_UNICORN = new ImageIcon(newimg);  // transform it back
		JLabel imgLab = new JLabel(Constants.IMG_UNICORN); //setting the image as a Label

		// creating the toolbar to put the buttons on the side
		JToolBar vertical = new JToolBar(JToolBar.VERTICAL);
		vertical.setFloatable(false);
		vertical.setMargin(new Insets(Constants.APP_LAYOUT_MARGIN_TOP,
				Constants.APP_LAYOUT_MARGIN_LEFT,
				Constants.APP_LAYOUT_MARGIN_BOTTOM,
				Constants.APP_LAYOUT_MARGIN_RIGHT));
		//setting the background color
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

		//creating the button for CashSpending feature and setting their size
		JButton cashspending = new JButton(Constants.BUTTON_SPENDING);
		cashspending.setFont(new Font("Courier New", Font.ITALIC, 14)); //setting font
		cashspending.setForeground(new Color(0, 204, 102)); //setting text color
		cashspending.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)); //setting size
		CashSpendingUI cashSpendingUI = new CashSpendingUI();
		this.cashSpendingUI = cashSpendingUI;        
		cashspending.addActionListener(cashSpendingUI); //when button is clicked

		//creating the button for MyCards feature and setting their size
		JButton cards = new JButton(Constants.BUTTON_CARDS);
		cards.setFont(new Font("Courier New", Font.ITALIC, 14));//setting font
		cards.setForeground(new Color(178, 102, 255));//setting text color
		cards.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));//setting size
		MyCardsUI cardsUI = new MyCardsUI();
		this.cardsUI=cardsUI;
		cards.addActionListener(cardsUI);//when button is clicked

		//creating the button for Budgeting feature and setting their size
		JButton budgeting = new JButton(Constants.BUTTON_BUDGET);
		budgeting.setFont(new Font("Courier New", Font.ITALIC, 14));//setting font
		budgeting.setForeground(new Color(0, 204, 204));//setting text color
		budgeting.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));//setting size
		BudgetingUI budgetingUI = new BudgetingUI();
		this.budgetingUI = budgetingUI;
		budgeting.addActionListener(budgetingUI);//when button is clicked


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


