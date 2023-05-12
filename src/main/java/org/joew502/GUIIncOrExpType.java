package org.joew502;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This class is the controller for the 'View Type' Page
 * This page allows the user to view the balances and detail breakdown for a given type.
 */
public class GUIIncOrExpType {
    public JPanel mainPanel;
    private JLabel typeLabel;
    private JButton returnButton;
    private JButton addDetailButton;
    private JTable detailTable;
    private JLabel valueLabel;
    private JButton deleteTypeButton;
    private JButton toggleFinalButton;
    private JButton editExpectedButton;
    private String currentIncOrExp;
    private String currentTypeKey;

    /**
     * The constructor initiates the action listeners for each of the buttons in the view
     */
    public GUIIncOrExpType() {
        returnButton.addActionListener(e -> { // On activation this will switch the view to the previous page
            Main.guiMain.guiIncAndExp.refreshData();
            Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
        });
        addDetailButton.addActionListener(e -> { // On activation this will switch the view to the 'Add Detail' view
            Main.guiMain.guiAddDetail.refreshData(currentIncOrExp, currentTypeKey);
            Main.guiMain.crd.show(Main.guiMain.cPane, "addDetail");
        });
        deleteTypeButton.addActionListener(e -> { // On activation this will show a confirmation dialog and then
                                                  // request for the model to delete the current 'type'.
            int confirm = JOptionPane.showConfirmDialog(Main.guiMain, "Are you sure you want to delete" +
                    " this type?", "Confirm Type Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                Main.dataMain.deleteType(currentIncOrExp, currentTypeKey);
                Main.guiMain.guiIncAndExp.refreshData();
                Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
            }
        });
        toggleFinalButton.addActionListener(e -> { // On activation this will instruct the model to toggle the 'final'
                                                   // status of the type and then refresh the view.
            Main.dataMain.toggleFinal(currentIncOrExp, currentTypeKey);
            refreshData(currentIncOrExp, currentTypeKey);
        });
        editExpectedButton.addActionListener(e -> { // On activation this will switch the view to
                                                    // the 'Set Expected' view
            Main.guiMain.guiSetExpected.refreshData(currentIncOrExp, currentTypeKey);
            Main.guiMain.crd.show(Main.guiMain.cPane, "setExpected");
        });
    }

    /**
     * When this method is called, it will re-form the given table and update the totals with updated
     * information from the model.
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     */
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
            detailModel.addRow(new Object[]{detail, "£"+String.format("%.2f", Main.dataMain.getValue(incOrExp,
                    typeKey, (String) detail)), "Edit"});
        }
        detailTable.setModel(detailModel);
        detailTable.getColumn(detailTable.getColumnName(2)).setCellRenderer(new TableButtonCellRender(
                "Edit"));
        detailTable.getColumn(detailTable.getColumnName(2)).setCellEditor(new TableButtonCellEditor(
                "Detail", incOrExp, detailList, typeKey));
    }
}
