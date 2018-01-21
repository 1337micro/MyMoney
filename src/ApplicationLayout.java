package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationLayout extends JFrame{
    private ImageIcon image;
    private JLabel label1;

    public ApplicationLayout() {
        // setting a menubar so there can be a header
        JMenuBar menubar = new JMenuBar();
        JMenu txt = new JMenu(Constants.APP_WELCOME_TITLE);
        menubar.add(txt);
        setJMenuBar(menubar);


        // creating the toolbar to put the buttons on the side
        JToolBar vertical = new JToolBar(JToolBar.VERTICAL);
        vertical.setFloatable(false);
        vertical.setMargin(new Insets(Constants.APP_LAYOUT_MARGIN_TOP,
                Constants.APP_LAYOUT_MARGIN_LEFT,
                Constants.APP_LAYOUT_MARGIN_BOTTOM,
                Constants.APP_LAYOUT_MARGIN_RIGHT));

        vertical.setBackground(Color.DARK_GRAY);

        //creating panel for each button so they can be aligned
        JPanel frameTBbutt1 = new JPanel();
        frameTBbutt1.setBackground(Color.DARK_GRAY);
        JPanel frameTBbutt2 = new JPanel();
        frameTBbutt2.setBackground(Color.DARK_GRAY);
        JPanel frameTBbutt3 = new JPanel();
        frameTBbutt3.setBackground(Color.DARK_GRAY);

        //creating buttons and setting their size
        JButton cashspending = new JButton(Constants.BUTTON_SPENDING);
        cashspending.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        cashspending.addActionListener(new CashSpendingUI());
        JButton cards = new JButton(Constants.BUTTON_CARDS);
        cards.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        JButton budgeting = new JButton(Constants.BUTTON_BUDGET);
        budgeting.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));


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
        /*removed this because it was conflicting with my panel visibility
        JPanel sidePan = new JPanel();
        image = new ImageIcon(getClass().getResource("../smile.jpg"));
        label1 = new JLabel(image);
        sidePan.add(label1);
        add(sidePan, BorderLayout.CENTER);
        */

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


}
