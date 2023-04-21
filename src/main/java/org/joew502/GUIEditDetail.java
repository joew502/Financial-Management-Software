package org.joew502;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                    float addValue = Float.parseFloat(valueField.getText());
                    Main.dataMain.addValue(currentIncOrExp, currentTypeKey, currentDetailKey, addValue);
                    Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
                    Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
                } catch (Exception NumberFormatException) {
                    JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable float");
                }
            }
        });
        deleteDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(Main.guiMain, "Are you sure you want to delete" +
                        " this detail?", "Confirm Detail Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    Main.dataMain.deleteDetail(currentIncOrExp, currentTypeKey, currentDetailKey);
                    Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
                    Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
                }
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
