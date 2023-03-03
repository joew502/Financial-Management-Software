package org.joew502;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                Main.guiMain.crd.show(Main.guiMain.cPane, "typeSelect");
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int addValue = Integer.parseInt(valueField.getText());
                    String typeKey = incomeTypeLabel.getText();
                    int currentValue = Integer.parseInt(((JSONObject) Main.jsonData.get(currentInOrExp)).get(typeKey).toString());
                    ((JSONObject) Main.jsonData.get(currentInOrExp)).put(typeKey, currentValue+addValue);
                    Main.guiMain.guiTypeSelect.refreshData(currentInOrExp);
                    Main.guiMain.crd.show(Main.guiMain.cPane, "typeSelect");
                } catch (Exception NumberFormatException) {
                    JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable integer");
                }
            }
        });
    }
    public void refreshData(String inOrExp, String typeKey) {
        currentInOrExp = inOrExp;
        valueField.setText("");
        inputLabel.setText("Input "+inOrExp);
        incomeTypeLabel.setText(typeKey);
        valueLabel.setText("Current Value: "+((JSONObject) Main.jsonData.get(inOrExp)).get(typeKey));
    }
}
