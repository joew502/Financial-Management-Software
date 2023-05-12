package org.joew502;

import javax.swing.*;
import java.io.File;

import static java.lang.System.exit;

/**
 * This class is the controller for the 'Main Menu' Page.
 * This page allows the user to navigate through a range of options.
 */
public class GUIMainMenu {
    public JPanel mainPanel;
    private JButton loadDataButton;
    private JButton saveDataButton;
    private JButton displayInAndExpButton;
    private JButton exitButton;

    /**
     * The constructor initiates the action listeners for each of the buttons in the view
     */
    public GUIMainMenu() {
        loadDataButton.addActionListener(e -> { // On activation this will open a file chooser dialog and the chosen
                                                // file path will be passed to the model for loading.
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(Main.guiMain);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                if (Main.dataMain.load(file.getPath())) {
                    JOptionPane.showMessageDialog(Main.guiMain, "Loaded Successfully");
                } else {
                    JOptionPane.showMessageDialog(Main.guiMain, "Loaded Failed - Ensure file format is" +
                            " correct");
                }
            }
        });
        saveDataButton.addActionListener(e -> { // On activation this will open a file chooser dialog and the chosen
                                                // file path will be passed to the model for saving.
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(Main.guiMain);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                if (Main.dataMain.save(file.getPath())) {
                    JOptionPane.showMessageDialog(Main.guiMain, "Saved Successfully");
                } else {
                    JOptionPane.showMessageDialog(Main.guiMain, "Save Failed");
                }
            }
        });
        displayInAndExpButton.addActionListener(e -> { // On activation this will switch the view to the
                                                       // 'Income and Expenditure' view
            Main.guiMain.guiIncAndExp.refreshData();
            Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
        });
        exitButton.addActionListener(e -> exit(0)); // On activation this will exit the program
    }
}
