package org.joew502;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUISetExpected {
    public JPanel mainPanel;
    private JButton returnButton;
    private JButton submitButton;
    private JTextField expectedField;
    private JLabel typeLabel;
    private JLabel valueLabel;
    private String currentIncOrExp;
    private String currentTypeKey;
    public GUISetExpected() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
                Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float newValue = Float.parseFloat(expectedField.getText());
                    Main.dataMain.setExpected(currentIncOrExp, currentTypeKey, newValue);
                    Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
                    Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
                } catch (Exception NumberFormatException) {
                    JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable float");
                }
            }
        });
    }
    public void refreshData(String incOrExp, String typeKey) {
        currentIncOrExp = incOrExp;
        currentTypeKey = typeKey;
        expectedField.setText("");
        typeLabel.setText(incOrExp+" > "+typeKey);
        valueLabel.setText("Current Value: "+ String.format("%.2f", Main.dataMain.getExpectedValue(incOrExp, typeKey)));
    }
}
