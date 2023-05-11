package org.joew502;

import javax.swing.*;

public class GUIAddDetail {
    public JPanel mainPanel;
    private JTextField nameField;
    private JTextField valueField;
    private JButton returnButton;
    private JButton addDetailButton;
    private JLabel typeLabel;
    private String currentIncOrExp;
    private String currentTypeKey;
    public GUIAddDetail() {
        returnButton.addActionListener(e -> {
            Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
            Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
        });
        addDetailButton.addActionListener(e -> {
            try {
                String addDetailName = nameField.getText();
                float addDetailValue = Float.parseFloat(valueField.getText());
                if (Main.dataMain.addDetail(currentIncOrExp, currentTypeKey, addDetailName, addDetailValue)) {
                    Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
                    Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
                } else {
                    JOptionPane.showMessageDialog(Main.guiMain,"This detail already exists in "+currentIncOrExp+">"+ currentTypeKey);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable string for the name and float for the value");
            }
        });
    }
    public void refreshData(String incOrExp, String type) {
        currentIncOrExp = incOrExp;
        currentTypeKey = type;
        typeLabel.setText(incOrExp+">"+type);
        nameField.setText("");
        valueField.setText("");
    }
}
