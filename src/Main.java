package src;

import src.ApplicationLayout;

import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        // Use the system theme, this is purely aesthetic
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //displaying the window
        ApplicationLayout applicationLayout = new ApplicationLayout();
        applicationLayout.setVisible(true);
    }
}