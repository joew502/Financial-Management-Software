package org.joew502;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

public class GUIIncAndExp {
    public JPanel mainPanel;
    private JButton returnButton;
    private JTable incomeTable;
    private JTable expenditureTable;
    private JButton addIncTypeButton;
    private JButton addExpTypeButton;

    public GUIIncAndExp(){

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
        updateTables(incomeTable,"Income");
        updateTables(expenditureTable, "Expenditure");
    }

    private void updateTables(JTable dataTable, String incOrExp) {
        LinkedHashMap<String,LinkedHashMap<String,Integer>> incOrExpData = (LinkedHashMap<String,LinkedHashMap<String,Integer>>) Main.jsonData.get(incOrExp);
        Object[] mainKeys = incOrExpData.keySet().toArray();
        DefaultTableModel dataTableModel = new DefaultTableModel();
        dataTableModel.setColumnIdentifiers(new Object[]{incOrExp+" Type", "Amount", ""});
        for (Object mainKey:mainKeys) {
            LinkedHashMap<String,Integer> typeData = incOrExpData.get((String) mainKey);
            Integer typeTotal = 0;
            for (Integer detailKey:typeData.values()) {
                typeTotal += detailKey;
            }
            dataTableModel.addRow(new Object[]{mainKey, typeTotal, "View+Edit"});
        }
        dataTable.setModel(dataTableModel);
        dataTable.getColumn(incomeTable.getColumnName(2)).setCellRenderer(new TableButtonCellRender("View+Edit"));
        dataTable.getColumn(incomeTable.getColumnName(2)).setCellEditor(new TableButtonCellEditor("Type", incOrExp, mainKeys));
    }
}