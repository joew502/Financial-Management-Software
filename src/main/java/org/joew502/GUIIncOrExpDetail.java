package org.joew502;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Objects;

public class GUIIncOrExpDetail {
    public JPanel mainPanel;
    private JLabel detailLabel;
    private JButton returnButton;
    private JButton addButton;
    private JTable detailTable;
    private String currentIncOrExp;
    private String currentType;

    public GUIIncOrExpDetail() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiIncAndExp.refreshData();
                Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiAddDetail.refreshData(currentIncOrExp, currentType);
                Main.guiMain.crd.show(Main.guiMain.cPane, "addDetail");
            }
        });
    }
    public void refreshData(String incOrExp, String typeKey) {
        currentIncOrExp = incOrExp;
        currentType = typeKey;

        detailLabel.setText(incOrExp+" Detail: "+typeKey);

        DefaultTableModel detailModel = new DefaultTableModel();
        detailModel.setColumnIdentifiers(new Object[]{"Income Type", "Amount", ""});
        Object[] detailList = Main.dataMain.getKeys(incOrExp, typeKey);
        for (Object detail:detailList) {
            detailModel.addRow(new Object[]{detail, String.format("%.2f", Main.dataMain.getValue(incOrExp, typeKey, (String) detail)), "Edit"});
        }
        detailTable.setModel(detailModel);
        detailTable.getColumn(detailTable.getColumnName(2)).setCellRenderer(new TableButtonCellRender("Edit"));
        detailTable.getColumn(detailTable.getColumnName(2)).setCellEditor(new TableButtonCellEditor("Detail", incOrExp, detailList, typeKey));
    }
}
