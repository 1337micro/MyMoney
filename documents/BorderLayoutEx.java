import java.awt.*;
import javax.swing.*;



public class BorderLayoutEx extends JFrame {
	 private ImageIcon image;
	 private JLabel label1;
	 
    public BorderLayoutEx() {

    	// setting a menubar so there can be a header 
        JMenuBar menubar = new JMenuBar();
        JMenu txt = new JMenu("Welcome to MyMoney Desktop Application");
        menubar.add(txt);
        setJMenuBar(menubar);
       
        
        // creating the toolbar to put the buttons on the side
        JToolBar vertical = new JToolBar(JToolBar.VERTICAL);
        vertical.setFloatable(false);
        vertical.setMargin(new Insets(10, 10, 10, 10));
        vertical.setBackground(Color.DARK_GRAY);
        
        //creating panel for each button so they can be aligned
        JPanel frameTBbutt1 = new JPanel();
        frameTBbutt1.setBackground(Color.DARK_GRAY);
        JPanel frameTBbutt2 = new JPanel();
        frameTBbutt2.setBackground(Color.DARK_GRAY);
        JPanel frameTBbutt3 = new JPanel();
        frameTBbutt3.setBackground(Color.DARK_GRAY);
        
        //creating buttons and setting their size
        JButton cashspending = new JButton("Cash Spendings");
        cashspending.setPreferredSize(new Dimension(150, 20));
        JButton cards = new JButton("My cards");
        cards.setPreferredSize(new Dimension(150, 20));
        JButton budgeting = new JButton("Budgeting");
        budgeting.setPreferredSize(new Dimension(150, 20));
  
        
        //adding buttons to their panels
        frameTBbutt1.add(cashspending);
        frameTBbutt2.add(cards);
        frameTBbutt3.add(budgeting);
        
        //adding panels to the toolbar
        vertical.add(frameTBbutt1);
        vertical.add(frameTBbutt2);
        vertical.add(frameTBbutt3);
        
        //adding toolbar to the frame
        add(vertical, BorderLayout.WEST);
        
        //create a panel so we can change what ever to display the different functions
        JPanel sidePan = new JPanel();
        image = new ImageIcon(getClass().getResource("smile.jpg"));
        label1 = new JLabel(image);
        sidePan.add(label1);
        add(sidePan, BorderLayout.CENTER);
        
        //setting the version status of the application we can update that in time
        JLabel statusbar = new JLabel("Version 1.0.0");
        add(statusbar, BorderLayout.SOUTH);

        //setting the frame attributes
        setSize(800, 500);
        setResizable(false);
        setTitle("MyMoney Desktop Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
//displaying the window
            BorderLayoutEx ex = new BorderLayoutEx();
            ex.setVisible(true);
        
    }
}