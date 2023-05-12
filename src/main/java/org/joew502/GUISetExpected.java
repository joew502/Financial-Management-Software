package org.joew502;

import javax.swing.*;

/**
 * This class is the controller for the 'Set Expected' Page
 * This page allows the user to give a value to set a type's expected value to
 */
public class GUISetExpected {
    public JPanel mainPanel;
    private JButton returnButton;
    private JButton submitButton;
    private JTextField expectedField;
    private JLabel typeLabel;
    private JLabel valueLabel;
    private String currentIncOrExp;
    private String currentTypeKey;

    /**
     * The constructor initiates the action listeners for each of the buttons in the view
     */
    public GUISetExpected() {
        returnButton.addActionListener(e -> { // On activation this will switch the view to the previous page
            Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
            Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
        });
        submitButton.addActionListener(e -> { // On activation this will take the user's input, validate it
                                              // and send it to the model.
            try {
                float newValue = Float.parseFloat(expectedField.getText());
                Main.dataMain.setExpected(currentIncOrExp, currentTypeKey, newValue);
                Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
                Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
            } catch (Exception NumberFormatException) {
                JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable float");
            }
        });
    }

    /**
     * This method updates the view and the controller itself to reflect the current section and type being amended
     * The method also wipes any remaining input from the fields
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     */
    public void refreshData(String incOrExp, String typeKey) {
        currentIncOrExp = incOrExp;
        currentTypeKey = typeKey;
        expectedField.setText("");
        typeLabel.setText(incOrExp+" > "+typeKey);
        valueLabel.setText("Current Value: "+ String.format("%.2f", Main.dataMain.getExpectedValue(incOrExp, typeKey)));
    }
}
