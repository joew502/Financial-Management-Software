package org.joew502;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIAddDetail {
    public JPanel mainPanel;
    private JTextField nameField;
    private JTextField valueField;
    private JButton returnButton;
    private JButton addDetailButton;
    private JLabel typeLabel;
    private String currentIncOrExp;
    private String currentType;
    public GUIAddDetail() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiIncOrExpDetail.refreshData(currentIncOrExp, currentType);
                Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpDetail");
            }
        });
        addDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String addDetailName = nameField.getText();
                    float addDetailValue = Float.parseFloat(valueField.getText());
                    if (Main.dataMain.addDetail(currentIncOrExp, currentType, addDetailName, addDetailValue)) {
                        Main.guiMain.guiIncOrExpDetail.refreshData(currentIncOrExp, currentType);
                        Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpDetail");
                    } else {
                        JOptionPane.showMessageDialog(Main.guiMain,"This detail already exists in "+currentIncOrExp+">"+currentType);
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable string for the name and float for the value");
                }
            }
        });
    }
    public void refreshData(String incOrExp, String type) {
        currentIncOrExp = incOrExp;
        currentType = type;
        typeLabel.setText(incOrExp+">"+type);
        nameField.setText("");
        valueField.setText("");
    }
}