package org.joew502;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

public class GUIEditDetail {
    public JPanel mainPanel;
    private JButton returnButton;
    private JButton submitButton;
    private JTextField valueField;
    private JLabel typeLabel;
    private JLabel valueLabel;
    private String currentIncOrExp;
    private String currentTypeKey;
    private String currentDetailKey;

    public GUIEditDetail() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiIncOrExpDetail.refreshData(currentIncOrExp, currentTypeKey);
                Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpDetail");
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int addValue = Integer.parseInt(valueField.getText());
                    LinkedHashMap<String,Integer> typeData = Main.dataMain.getHash(currentIncOrExp, currentTypeKey);
                    int currentValue = typeData.get(currentDetailKey);
                    typeData.put(currentDetailKey, currentValue+addValue);
                    Main.guiMain.guiIncOrExpDetail.refreshData(currentIncOrExp, currentTypeKey);
                    Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpDetail");
                } catch (Exception NumberFormatException) {
                    JOptionPane.showMessageDialog(Main.guiMain,"Please enter a suitable integer");
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
        valueLabel.setText("Current Value: "+Main.dataMain.getValue(incOrExp, typeKey, detailKey));
    }
}
