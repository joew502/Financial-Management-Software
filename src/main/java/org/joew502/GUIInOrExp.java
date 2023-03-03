package org.joew502;

import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIInOrExp {
    public JPanel mainPanel;
    private JButton returnButton;
    private JTable incomeTable;
    private JTable expenditureTable;

    public GUIInOrExp(){

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.crd.show(Main.guiMain.cPane, "mainMenu");
            }
        });
    }

    public void refreshData() {

        Object[] incomeKeys = ((JSONObject) Main.jsonData.get("Income")).keySet().toArray();
        DefaultTableModel incomeModel = new DefaultTableModel();
        incomeModel.setColumnIdentifiers(new Object[]{"Income Type", "Amount"});
        for (Object incomeKey:incomeKeys) {
            incomeModel.addRow(new Object[]{incomeKey, ((JSONObject) Main.jsonData.get("Income")).get(incomeKey)});
        }
        incomeTable.setModel(incomeModel);

        Object[] expenditureKeys = ((JSONObject) Main.jsonData.get("Expenditure")).keySet().toArray();
        DefaultTableModel expenditureModel = new DefaultTableModel();
        expenditureModel.setColumnIdentifiers(new Object[]{"Expenditure Type", "Amount"});
        for (Object expenditureKey:expenditureKeys) {
            expenditureModel.addRow(new Object[]{expenditureKey, ((JSONObject) Main.jsonData.get("Expenditure")).get(expenditureKey)});
        }
        expenditureTable.setModel(expenditureModel);
    }
}