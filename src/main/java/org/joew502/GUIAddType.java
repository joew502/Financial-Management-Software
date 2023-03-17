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
                    LinkedHashMap<String,LinkedHashMap<String,Float>> incOrExpData = Main.dataMain.getHash(currentIncOrExp);
                    if (incOrExpData.containsKey(addTypeName)) {
                        JOptionPane.showMessageDialog(Main.guiMain,"This "+currentIncOrExp+" type already exists");
                    } else {
                        incOrExpData.put(addTypeName, new LinkedHashMap<String,Float>());
                        Main.guiMain.guiIncAndExp.refreshData();
                        Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
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
