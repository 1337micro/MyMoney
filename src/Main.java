//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: Whole team
//Description: Main driver class
//--------------------------------------------------------
package src;
import javax.swing.*;

public class Main{


    private static CashSpending cashSpending = new CashSpending();
    private static ApplicationLayout applicationLayout = new ApplicationLayout();
    private static Budgetting budgetting = new Budgetting();
    private static MyCards myCards= new MyCards();


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
    
    public static Budgetting getBudgetting() {
    	return budgetting;
    }



    public static MyCards getMyCards() {
    	return myCards;
    }
}