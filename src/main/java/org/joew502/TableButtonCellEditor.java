package org.joew502;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class TableButtonCellEditor extends DefaultCellEditor {

    String typeOrDetail;
    String incOrExp;
    Object[] keys;
    String typeKey;

    public TableButtonCellEditor(String typeOrDetail, String incOrExp, Object[] keys) {
        this(typeOrDetail, incOrExp, keys, "");
    }
    public TableButtonCellEditor(String typeOrDetail, String incOrExp, Object[] keys, String typeKey) {
        super(new JCheckBox());
        this.typeOrDetail = typeOrDetail;
        this.incOrExp = incOrExp;
        this.keys = keys;
        this.typeKey = typeKey;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JButton button = new JButton();
        if (typeOrDetail.equals("Type")) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.guiMain.guiIncOrExpType.refreshData(incOrExp, (String) keys[row]);
                    Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
                }
            });
            button.setText("View+Edit");
        } else {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.guiMain.guiEditDetail.refreshData(incOrExp, typeKey, (String) keys[row]);
                    Main.guiMain.crd.show(Main.guiMain.cPane, "editDetail");
                }
            });
            button.setText("Edit");
        }
        return button;
    }
    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    public Object getCellEditorValue() {
        return null;
    }
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }
}