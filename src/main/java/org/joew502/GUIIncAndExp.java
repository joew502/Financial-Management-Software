package org.joew502;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton toggleExpectedTotalsButton;
    private JButton addDefaultTypesButton;

    public GUIIncAndExp(){

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Main.guiMain.guiMainMenu.refreshData();
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
        toggleExpectedTotalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addDefaultTypesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
    }

    public void refreshData() {
        updateTables(incomeTable,"Income");
        updateTables(expenditureTable, "Expenditure");
        float incomeTotal = Main.dataMain.getTotal("Income");
        float expenditureTotal = Main.dataMain.getTotal("Expenditure");
        float balanceTotal = incomeTotal-expenditureTotal;
        balanceLabel.setText("Balance: £"+String.format("%.2f", balanceTotal));
        incomeLabel.setText("Income: £"+String.format("%.2f", incomeTotal));
        expenditureLabel.setText("Expenditure: £"+String.format("%.2f", expenditureTotal));
    }

    private void updateTables(JTable dataTable, String incOrExp) {
        Object[] typeKeys = Main.dataMain.getKeys(incOrExp);
        DefaultTableModel dataTableModel = new DefaultTableModel();
        dataTableModel.setColumnIdentifiers(new Object[]{incOrExp+" Type", "Amount", "Expected", ""});
        for (Object mainKey:typeKeys) {
            dataTableModel.addRow(new Object[]{
                    mainKey,
                    "£"+String.format("%.2f", Main.dataMain.getTotal(incOrExp, (String) mainKey)),
                    "£"+String.format("%.2f", Main.dataMain.getExpectedValue(incOrExp, (String) mainKey)),
                    "View+Edit"});
        }
        dataTable.setModel(dataTableModel);
        dataTable.getColumn(incomeTable.getColumnName(3)).setCellRenderer(new TableButtonCellRender("View+Edit"));
        dataTable.getColumn(incomeTable.getColumnName(3)).setCellEditor(new TableButtonCellEditor("Type", incOrExp, typeKeys));
    }
}