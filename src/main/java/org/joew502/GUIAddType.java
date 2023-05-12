package org.joew502;

import javax.swing.*;

/**
 * This class is the controller for the 'Add Type' Page
 * This page allows the user to give a name to create a new 'type' with.
 */
public class GUIAddType {
    public JPanel mainPanel;
    private JLabel addTypeLabel;
    private JButton returnButton;
    private JButton submitButton;
    private JTextField addTypeField;
    private String currentIncOrExp;

    /**
     * The constructor initiates the action listeners for each of the buttons in the view
     */
    public GUIAddType() {
        // On activation this will switch the view to the previous page
        returnButton.addActionListener(e -> Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp"));
        submitButton.addActionListener(e -> { // On activation this will take the user's input, validate it
                                              // and send it to the model.
            try {
                String addTypeName = addTypeField.getText();
                if (Main.dataMain.addType(currentIncOrExp, addTypeName)) {
                    Main.guiMain.guiIncAndExp.refreshData();
                    Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
                } else {
                    JOptionPane.showMessageDialog(Main.guiMain,"This "+currentIncOrExp+" type already exists");
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable string");
            }
        });
    }

    /**
     * This method updates the view and the controller itself to reflect the current section being appended
     * The method also wipes any remaining input from the fields
     * @param incOrExp - String of "Income" or "Expenditure"
     */
    public void refreshData(String incOrExp) {
        currentIncOrExp = incOrExp;
        addTypeLabel.setText("Add "+incOrExp+" Type");
        addTypeField.setText("");
    }
}
