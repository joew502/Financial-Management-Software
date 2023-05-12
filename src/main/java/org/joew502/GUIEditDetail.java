package org.joew502;

import javax.swing.*;

/**
 * This class is the controller for the 'Edit Detail' Page
 * This page allows the user to give a value to add to the current value of a piece of 'detail'.
 */
public class GUIEditDetail {
    public JPanel mainPanel;
    private JButton returnButton;
    private JButton submitButton;
    private JTextField valueField;
    private JLabel typeLabel;
    private JLabel valueLabel;
    private JButton deleteDetailButton;
    private String currentIncOrExp;
    private String currentTypeKey;
    private String currentDetailKey;

    /**
     * The constructor initiates the action listeners for each of the buttons in the view
     */
    public GUIEditDetail() {
        returnButton.addActionListener(e -> { // On activation this will switch the view to the previous pag
            Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
            Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
        });
        submitButton.addActionListener(e -> { // On activation this will take the user's input, validate it
                                              // and send it to the model.
            try {
                float addValue = Float.parseFloat(valueField.getText());
                Main.dataMain.addValue(currentIncOrExp, currentTypeKey, currentDetailKey, addValue);
                Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
                Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
            } catch (Exception NumberFormatException) {
                JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable float");
            }
        });
        deleteDetailButton.addActionListener(e -> { // On activation this will show a confirmation dialog and then
                                                    // request for the model to delete the current 'detail'
            int confirm = JOptionPane.showConfirmDialog(Main.guiMain, "Are you sure you want to delete" +
                    " this detail?", "Confirm Detail Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                Main.dataMain.deleteDetail(currentIncOrExp, currentTypeKey, currentDetailKey);
                Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
                Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
            }
        });
    }

    /**
     * This method updates the view and the controller itself to reflect the current section,
     * type and detail being appended.
     * The method also wipes any remaining input from the fields
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @param detailKey - Detail Key in string form
     */
    public void refreshData(String incOrExp, String typeKey, String detailKey) {
        currentIncOrExp = incOrExp;
        currentTypeKey = typeKey;
        currentDetailKey = detailKey;
        valueField.setText("");
        typeLabel.setText(incOrExp+">"+typeKey+">"+detailKey);
        valueLabel.setText("Current Value: "+ String.format("%.2f", Main.dataMain.getValue(incOrExp, typeKey, detailKey)));
    }
}
