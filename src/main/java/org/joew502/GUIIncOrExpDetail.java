package org.joew502;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

public class GUIIncOrExpDetail {
    public JPanel mainPanel;
    private JLabel detailLabel;
    private JButton returnButton;
    private JButton addButton;
    private JTable detailTable;

    public GUIIncOrExpDetail() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void refreshData(String incOrExp, String key) {
        detailLabel.setText(incOrExp+" Detail: "+key);

        LinkedHashMap<String,Integer> detailData = ((LinkedHashMap<String,LinkedHashMap<String,Integer>>) Main.jsonData.get(incOrExp)).get(key);

        Object[] detailList =  detailData.keySet().toArray();
        DefaultTableModel detailModel = new DefaultTableModel();
        detailModel.setColumnIdentifiers(new Object[]{"Income Type", "Amount", ""});
        for (Object detail:detailList) {
            detailModel.addRow(new Object[]{detail, detailData.get(detail), "Edit"});
        }
        detailTable.setModel(detailModel);
        detailTable.getColumn(detailTable.getColumnName(2)).setCellRenderer(new TableButtonCellRender());
        detailTable.getColumn(detailTable.getColumnName(2)).setCellEditor(new TableButtonCellEditor("Detail", "Income", detailList));
    }
}
