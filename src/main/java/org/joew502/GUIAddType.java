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
    private String currentIncOrExp;
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
                    if (Main.dataMain.addType(currentIncOrExp, addTypeName)) {
                        Main.guiMain.guiIncAndExp.refreshData();
                        Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
                    } else {
                        JOptionPane.showMessageDialog(Main.guiMain,"This "+currentIncOrExp+" type already exists");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable string");
                }
            }
        });
    }
    public void refreshData(String incOrExp) {
        currentIncOrExp = incOrExp;
        addTypeLabel.setText("Add "+incOrExp+" Type");
        addTypeField.setText("");
    }
}
