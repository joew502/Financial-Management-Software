package org.joew502;

import javax.swing.*;
import javax.swing.table.*;

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
    private JButton addDefaultTypesButton;
    private JLabel detailLabel;

    public GUIIncAndExp(){

        returnButton.addActionListener(e -> {
            //Main.guiMain.guiMainMenu.refreshData();
            Main.guiMain.crd.show(Main.guiMain.cPane, "mainMenu");
        });
        addIncTypeButton.addActionListener(e -> {
            Main.guiMain.guiAddType.refreshData("Income");
            Main.guiMain.crd.show(Main.guiMain.cPane, "addType");
        });
        addExpTypeButton.addActionListener(e -> {
            Main.guiMain.guiAddType.refreshData("Expenditure");
            Main.guiMain.crd.show(Main.guiMain.cPane, "addType");
        });
        addDefaultTypesButton.addActionListener(e -> {
            String[] incomeTypeKeys = new String[] {"Membership", "Fundraising", "Sponsorship", "Other Income"};
            for (String typeKey:incomeTypeKeys) {
                Main.dataMain.addType("Income", typeKey);
            }
            String[] expenditureTypeKeys = new String[] {"AU Membership", "BUCS Affiliation", "NGB Membership",
                    "Facility Contribution", "Facility Hire", "Equipment Purchase", "Facility Hire",
                    "Coaching Costs", "Facility Hire", "Competition Event Costs", "Other Event Costs",
                    "Other Costs"};
            for (String typeKey:expenditureTypeKeys) {
                Main.dataMain.addType("Expenditure", typeKey);
            }
            refreshData();
        });
    }

    public void refreshData() {
        updateTables(incomeTable,"Income");
        updateTables(expenditureTable, "Expenditure");
        float currentIncTotal = Main.dataMain.getTotal("Income");
        float currentExpTotal = Main.dataMain.getTotal("Expenditure");
        float currentBalanceTotal = currentIncTotal-currentExpTotal;
        float expectedIncTotal = Main.dataMain.getExpectedTotal("Income");
        float expectedExpTotal = Main.dataMain.getExpectedTotal("Expenditure");
        float expectedBalanceTotal = expectedIncTotal-expectedExpTotal;
        float minTotal = currentIncTotal-expectedExpTotal;
        float maxTotal = expectedIncTotal-currentExpTotal;
        balanceLabel.setText("Balance: £"+String.format("%.2f", expectedBalanceTotal));
        incomeLabel.setText("Income: £"+String.format("%.2f", expectedIncTotal)+
                " (Current: £"+String.format("%.2f", currentIncTotal)+")");
        expenditureLabel.setText("Expenditure: £"+String.format("%.2f", expectedExpTotal)+
                " (Current: £"+String.format("%.2f", currentExpTotal)+")");
        detailLabel.setText("(Current: £"+String.format("%.2f", currentBalanceTotal)+"    Min: £"+
                String.format("%.2f", minTotal)+"    Max: £"+String.format("%.2f", maxTotal)+")");
    }

    private void updateTables(JTable dataTable, String incOrExp) {
        Object[] typeKeys = Main.dataMain.getKeys(incOrExp);
        DefaultTableModel dataTableModel = new DefaultTableModel();
        dataTableModel.setColumnIdentifiers(new Object[]{incOrExp+" Type", "Amount", "Expected", ""});
        for (Object typeKey:typeKeys) {
            String expectedValue;
            if (Main.dataMain.getFinal(incOrExp, (String) typeKey)) {
                expectedValue = "Final";
            } else {
                expectedValue = "£"+String.format("%.2f", Main.dataMain.getExpectedValue(incOrExp, (String) typeKey));
            }
            dataTableModel.addRow(new Object[]{
                    typeKey,
                    "£"+String.format("%.2f", Main.dataMain.getTotal(incOrExp, (String) typeKey)),
                    expectedValue,
                    "View+Edit"});
        }
        dataTable.setModel(dataTableModel);
        dataTable.getColumn(incomeTable.getColumnName(3)).setCellRenderer(new TableButtonCellRender("View+Edit"));
        dataTable.getColumn(incomeTable.getColumnName(3)).setCellEditor(new TableButtonCellEditor("Type", incOrExp, typeKeys));
    }
}