package org.joew502;

import javax.swing.*;

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

    public GUIEditDetail() {
        returnButton.addActionListener(e -> {
            Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
            Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
        });
        submitButton.addActionListener(e -> {
            try {
                float addValue = Float.parseFloat(valueField.getText());
                Main.dataMain.addValue(currentIncOrExp, currentTypeKey, currentDetailKey, addValue);
                Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
                Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
            } catch (Exception NumberFormatException) {
                JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable float");
            }
        });
        deleteDetailButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(Main.guiMain, "Are you sure you want to delete" +
                    " this detail?", "Confirm Detail Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                Main.dataMain.deleteDetail(currentIncOrExp, currentTypeKey, currentDetailKey);
                Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
                Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
            }
        });
    }
    public void refreshData(String incOrExp, String typeKey, String detailKey) {
        currentIncOrExp = incOrExp;
        currentTypeKey = typeKey;
        currentDetailKey = detailKey;
        valueField.setText("");
        typeLabel.setText(incOrExp+">"+typeKey+">"+detailKey);
        valueLabel.setText("Current Value: "+ String.format("%.2f", Main.dataMain.getValue(incOrExp, typeKey, detailKey)));
    }
}
