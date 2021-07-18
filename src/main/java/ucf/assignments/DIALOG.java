package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Shobit Bandaru
 */
import javax.swing.*;

public interface DIALOG {
    static void showDialog(String message, String title){
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.OK_OPTION);
    }
}
