package org.joew502;

import javax.swing.*;

/**
 * This class is the controller for the 'Add Detail' Page
 * This page allows the user to give a name and value to create a new piece of 'detail' with.
 */
public class GUIAddDetail {
    public JPanel mainPanel;
    private JTextField nameField;
    private JTextField valueField;
    private JButton returnButton;
    private JButton addDetailButton;
    private JLabel typeLabel;
    private String currentIncOrExp;
    private String currentTypeKey;

    /**
     * The constructor initiates the action listeners for each of the buttons in the view
     */
    public GUIAddDetail() {
        returnButton.addActionListener(e -> { // On activation this will switch the view to the previous page
            Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
            Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
        });
        addDetailButton.addActionListener(e -> { // On activation this will take the user's inputs, validate them
                                                 // and send them to the model.
            try {
                String addDetailName = nameField.getText();
                float addDetailValue = Float.parseFloat(valueField.getText());
                if (Main.dataMain.addDetail(currentIncOrExp, currentTypeKey, addDetailName, addDetailValue)) {
                    Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
                    Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
                } else {
                    JOptionPane.showMessageDialog(Main.guiMain,"This detail already exists in "+
                            currentIncOrExp+">"+ currentTypeKey);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(Main.guiMain,
                        "Please enter a suitable string for the name and float for the value");
            }
        });
    }

    /**
     * This method updates the view and the controller itself to reflect the current section and type being appended
     * The method also wipes any remaining input from the fields
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param type - Type Key in string form
     */
    public void refreshData(String incOrExp, String type) {
        currentIncOrExp = incOrExp;
        currentTypeKey = type;
        typeLabel.setText(incOrExp+">"+type);
        nameField.setText("");
        valueField.setText("");
    }
}
