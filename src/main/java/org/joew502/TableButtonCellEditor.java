package org.joew502;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableButtonCellEditor extends DefaultCellEditor {

    String inOrExp;
    Object[] typeKeys;

    public TableButtonCellEditor(String inOrExp, Object[] typeKeys) {
        super(new JCheckBox());
        this.inOrExp = inOrExp;
        this.typeKeys = typeKeys;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.guiMain.guiInputValue.refreshData(inOrExp, (String) typeKeys[row]);
                Main.guiMain.crd.show(Main.guiMain.cPane, "inputValue");
            }
        });
        return button;
    }
}