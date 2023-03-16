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
    private JLabel balanceLabel;
    private JLabel incomeLabel;
    private JLabel expenditureLabel;

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
        int incomeTotal = Main.dataMain.getTotal("Income");
        int expenditureTotal = Main.dataMain.getTotal("Expenditure");
        int balanceTotal = incomeTotal-expenditureTotal;
        balanceLabel.setText("Balance: £"+balanceTotal);
        incomeLabel.setText("Income: £"+incomeTotal);
        expenditureLabel.setText("Expenditure: £"+expenditureTotal);
    }

    private void updateTables(JTable dataTable, String incOrExp) {
        Object[] typeKeys = Main.dataMain.getKeys(incOrExp);
        DefaultTableModel dataTableModel = new DefaultTableModel();
        dataTableModel.setColumnIdentifiers(new Object[]{incOrExp+" Type", "Amount", ""});
        for (Object mainKey:typeKeys) {
            dataTableModel.addRow(new Object[]{mainKey, Main.dataMain.getTotal(incOrExp, (String) mainKey), "View+Edit"});
        }
        dataTable.setModel(dataTableModel);
        dataTable.getColumn(incomeTable.getColumnName(2)).setCellRenderer(new TableButtonCellRender("View+Edit"));
        dataTable.getColumn(incomeTable.getColumnName(2)).setCellEditor(new TableButtonCellEditor("Type", incOrExp, typeKeys));
    }
}