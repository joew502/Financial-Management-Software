package org.joew502;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUITypeSelect {
    public JPanel mainPanel;
    private JLabel inputTypeLabel;
    private JButton returnButton;
    private JButton addTypeButton;
    private JPanel buttonPanel;
    private String currentInOrExp;

    public GUITypeSelect() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.crd.show(Main.guiMain.cPane, "inOrExpMenu");
            }
        });
        addTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiAddType.refreshData(currentInOrExp);
                Main.guiMain.crd.show(Main.guiMain.cPane, "addType");
            }
        });
    }
    public void refreshData(String inOrExp) {

        currentInOrExp = inOrExp;

        inputTypeLabel.setText("Choose an "+inOrExp+" type to change");
        
        addTypeButton.setText("Add "+inOrExp+" Type");

        Object[] typeKeys = ((JSONObject) Main.jsonData.get(inOrExp)).keySet().toArray();

        buttonPanel.removeAll();

        int count = typeKeys.length;
        buttonPanel.setLayout(new GridLayout(count,1));

        for (int index = 0; index < count; index++) {
            JButton button = new JButton((String) typeKeys[index]);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.guiMain.guiInputValue.refreshData(inOrExp, button.getText());
                    Main.guiMain.crd.show(Main.guiMain.cPane, "inputValue");
                }
            });
            buttonPanel.add(button);
        }
    }
}
