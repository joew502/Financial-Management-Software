package org.joew502;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIAddType {
    public JPanel mainPanel;
    private JLabel addTypeLabel;
    private JButton returnButton;
    private JButton submitButton;
    private JTextField addTypeField;
    private String currentInOrExp;
    public GUIAddType() {
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
                    String addTypeName = addTypeField.getText();
                    JSONObject incomeData = (JSONObject) Main.jsonData.get(currentInOrExp);
                    if (incomeData.containsKey(addTypeName)) {
                        JOptionPane.showMessageDialog(Main.guiMain,"This income type already exists");
                    } else {
                        ((JSONObject) Main.jsonData.get(currentInOrExp)).put(addTypeName, 0);
                        Main.guiMain.guiTypeSelect.refreshData(currentInOrExp);
                        Main.guiMain.crd.show(Main.guiMain.cPane, "typeSelect");
                    }
                } catch (Exception NumberFormatException) {
                    JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable string");
                }
            }
        });
    }
    public void refreshData(String inOrExp) {
        currentInOrExp = inOrExp;
        addTypeLabel.setText("Add "+inOrExp+" Type");
        addTypeField.setText("");
    }
}
