package org.joew502;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIIncOrExpType {
    public JPanel mainPanel;
    private JLabel typeLabel;
    private JButton returnButton;
    private JButton addDetailButton;
    private JTable detailTable;
    private JLabel valueLabel;
    private JButton deleteTypeButton;
    private JButton toggleFinalButton;
    private String currentIncOrExp;
    private String currentTypeKey;

    public GUIIncOrExpType() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiIncAndExp.refreshData();
                Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
            }
        });
        addDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiAddDetail.refreshData(currentIncOrExp, currentTypeKey);
                Main.guiMain.crd.show(Main.guiMain.cPane, "addDetail");
            }
        });
        deleteTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(Main.guiMain, "Are you sure you want to delete" +
                        " this type?", "Confirm Type Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    Main.dataMain.deleteType(currentIncOrExp, currentTypeKey);
                    Main.guiMain.guiIncAndExp.refreshData();
                    Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
                }
            }
        });
        toggleFinalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.dataMain.toggleFinal(currentIncOrExp, currentTypeKey);
                Main.guiMain.guiIncOrExpType.refreshData(currentIncOrExp, currentTypeKey);
            }
        });
    }
    public void refreshData(String incOrExp, String typeKey) {
        currentIncOrExp = incOrExp;
        currentTypeKey = typeKey;

        typeLabel.setText(incOrExp+" Type: "+typeKey);
        valueLabel.setText("Current: £"+String.format("%.2f", Main.dataMain.getTotal(incOrExp,typeKey))+
                "    Expected: £"+String.format("%.2f", Main.dataMain.getExpectedValue(incOrExp,typeKey))+
                "    Final Value: "+Main.dataMain.getFinalStr(currentIncOrExp, currentTypeKey));

        DefaultTableModel detailModel = new DefaultTableModel();
        detailModel.setColumnIdentifiers(new Object[]{"Income Detail", "Amount", ""});
        Object[] detailList = Main.dataMain.getKeys(incOrExp, typeKey);
        for (Object detail:detailList) {
            detailModel.addRow(new Object[]{detail, "£"+String.format("%.2f", Main.dataMain.getValue(incOrExp, typeKey, (String) detail)), "Edit"});
        }
        detailTable.setModel(detailModel);
        detailTable.getColumn(detailTable.getColumnName(2)).setCellRenderer(new TableButtonCellRender("Edit"));
        detailTable.getColumn(detailTable.getColumnName(2)).setCellEditor(new TableButtonCellEditor("Detail", incOrExp, detailList, typeKey));
    }
}
