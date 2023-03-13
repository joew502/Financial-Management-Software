package org.joew502;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

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
                Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String addTypeName = addTypeField.getText();
                    LinkedHashMap<String,Integer> incomeData = (LinkedHashMap<String,Integer>) Main.jsonData.get(currentInOrExp);
                    if (incomeData.containsKey(addTypeName)) {
                        JOptionPane.showMessageDialog(Main.guiMain,"This income type already exists");
                    } else {
                        ((LinkedHashMap<String,Integer>) Main.jsonData.get(currentInOrExp)).put(addTypeName, 0);
                        Main.guiMain.guiIncAndExp.refreshData();
                        Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
                    }
                } catch (Exception NumberFormatException) {
                    JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable string");
                }
            }
        });
    }
    public void refreshData(String incOrExp) {
        currentInOrExp = incOrExp;
        addTypeLabel.setText("Add "+incOrExp+" Type");
        addTypeField.setText("");
    }
}
