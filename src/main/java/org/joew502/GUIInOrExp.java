package org.joew502;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

public class GUIInOrExp {
    public JPanel mainPanel;
    private JButton returnButton;
    private JTable incomeTable;
    private JTable expenditureTable;
    private JButton addIncTypeButton;
    private JButton addExpTypeButton;

    public GUIInOrExp(){

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.crd.show(Main.guiMain.cPane, "mainMenu");
            }
        });
        addIncTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiAddType.refreshData("Income");
                Main.guiMain.crd.show(Main.guiMain.cPane, "addType");
            }
        });
        addExpTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiAddType.refreshData("Expenditure");
                Main.guiMain.crd.show(Main.guiMain.cPane, "addType");
            }
        });
    }

    public void refreshData() {

        Object[] incomeKeys = ((LinkedHashMap<String,Integer>) Main.jsonData.get("Income")).keySet().toArray();
        DefaultTableModel incomeModel = new DefaultTableModel();
        incomeModel.setColumnIdentifiers(new Object[]{"Income Type", "Amount", ""});
        for (Object incomeKey:incomeKeys) {
            incomeModel.addRow(new Object[]{incomeKey, ((LinkedHashMap<String,Integer>) Main.jsonData.get("Income")).get(incomeKey), "View+Edit"});
        }
        incomeTable.setModel(incomeModel);
        incomeTable.getColumn(incomeTable.getColumnName(2)).setCellRenderer(new TableButtonCellRender());
        incomeTable.getColumn(incomeTable.getColumnName(2)).setCellEditor(new TableButtonCellEditor("Income", incomeKeys));

        Object[] expenditureKeys = ((LinkedHashMap<String,Integer>) Main.jsonData.get("Expenditure")).keySet().toArray();
        DefaultTableModel expenditureModel = new DefaultTableModel();
        expenditureModel.setColumnIdentifiers(new Object[]{"Expenditure Type", "Amount", ""});
        for (Object expenditureKey:expenditureKeys) {
            expenditureModel.addRow(new Object[]{expenditureKey, ((LinkedHashMap<String,Integer>) Main.jsonData.get("Expenditure")).get(expenditureKey), "View+Edit"});
        }
        expenditureTable.setModel(expenditureModel);
        expenditureTable.getColumn(incomeTable.getColumnName(2)).setCellRenderer(new TableButtonCellRender());
        expenditureTable.getColumn(incomeTable.getColumnName(2)).setCellEditor(new TableButtonCellEditor("Expenditure", expenditureKeys));
    }
}