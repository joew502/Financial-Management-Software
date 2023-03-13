package org.joew502;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

public class GUIInputValue {
    public JPanel mainPanel;
    private JButton returnButton;
    private JButton submitButton;
    private JTextField valueField;
    private JLabel incomeTypeLabel;
    private JLabel valueLabel;
    private JLabel inputLabel;
    private String currentInOrExp;

    public GUIInputValue() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiIncAndExp.refreshData();
                Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int addValue = Integer.parseInt(valueField.getText());
                    String typeKey = incomeTypeLabel.getText();
                    int currentValue = Integer.parseInt(((LinkedHashMap<String, Integer>) Main.jsonData.get(currentInOrExp)).get(typeKey).toString());
                    ((LinkedHashMap<String, Integer>) Main.jsonData.get(currentInOrExp)).put(typeKey, currentValue+addValue);
                    Main.guiMain.guiIncAndExp.refreshData();
                    Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
                } catch (Exception NumberFormatException) {
                    JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable integer");
                }
            }
        });
    }
    public void refreshData(String incOrExp, String typeKey) {
        currentInOrExp = incOrExp;
        valueField.setText("");
        inputLabel.setText("Input "+incOrExp);
        incomeTypeLabel.setText(typeKey);
        valueLabel.setText("Current Value: "+((LinkedHashMap<String, Integer>) Main.jsonData.get(incOrExp)).get(typeKey));
    }
}
