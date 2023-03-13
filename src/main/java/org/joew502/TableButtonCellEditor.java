package org.joew502;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableButtonCellEditor extends DefaultCellEditor {

    String typeOrDetail;
    String incOrExp;
    Object[] keys;

    public TableButtonCellEditor(String typeOrDetail, String incOrExp, Object[] keys) {
        super(new JCheckBox());
        this.typeOrDetail = typeOrDetail;
        this.incOrExp = incOrExp;
        this.keys = keys;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JButton button = new JButton();
        if (typeOrDetail.equals("Type")) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.guiMain.guiInputValue.refreshData(incOrExp, (String) keys[row]);
                    Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpDetail");
                }
            });
        } else {
            button.addActionListener(new ActionListener() { // TODO Update
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.guiMain.guiInputValue.refreshData(incOrExp, (String) keys[row]);
                    Main.guiMain.crd.show(Main.guiMain.cPane, "inputValue");
                }
            });
        }
        return button;
    }
}