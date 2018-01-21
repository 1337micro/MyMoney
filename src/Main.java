package src;

import src.ApplicationLayout;

import javax.swing.*;

import static src.CashSpending.*;

public class Main{


    private static CashSpending cashSpending = new CashSpending();
    private static ApplicationLayout applicationLayout = new ApplicationLayout();

    public static void main(String[] args) {
        // Use the system theme, this is purely aesthetic
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //displaying the window

        applicationLayout.setVisible(true);


    }

    public static CashSpending getCashSpending() {
        return cashSpending;
    }
    public static ApplicationLayout getApplicationLayout() {
        return applicationLayout;
    }
}