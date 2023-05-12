package org.joew502;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;

/**
 * This class creates a modified version of the DefaultCellEditor that allows the functionality
 * of a JButton in a table cell.
 */
public class TableButtonCellEditor extends DefaultCellEditor {

    String typeOrDetail;
    String incOrExp;
    Object[] keys;
    String typeKey;

    public TableButtonCellEditor(String typeOrDetail, String incOrExp, Object[] keys) {
        this(typeOrDetail, incOrExp, keys, "");
    }

    /**
     * @param typeOrDetail - String indicating what situation the table is in and therefore what
     *                     action listener to implement
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param keys - Object list of keys in the table
     * @param typeKey - Type Key in string form
     */
    public TableButtonCellEditor(String typeOrDetail, String incOrExp, Object[] keys, String typeKey) {
        super(new JCheckBox());
        this.typeOrDetail = typeOrDetail;
        this.incOrExp = incOrExp;
        this.keys = keys;
        this.typeKey = typeKey;
    }

    /**
     * @param table      the <code>JTable</code> that is asking the
     *                   editor to edit; can be <code>null</code>
     * @param value      the value of the cell to be edited; it is
     *                   up to the specific editor to interpret
     *                   and draw the value.  For example, if value is
     *                   the string "true", it could be rendered as a
     *                   string or it could be rendered as a check
     *                   box that is checked.  <code>null</code>
     *                   is a valid value
     * @param isSelected true if the cell is to be rendered with
     *                   highlighting
     * @param row        the row of the cell being edited
     * @param column     the column of the cell being edited
     * This default method has been modified with a button and two potential different action listeners depending on
     *                   the use case of the table button.
     * @return
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JButton button = new JButton();
        if (typeOrDetail.equals("Type")) {
            button.addActionListener(e -> {
                Main.guiMain.guiIncOrExpType.refreshData(incOrExp, (String) keys[row]);
                Main.guiMain.crd.show(Main.guiMain.cPane, "incOrExpType");
            });
            button.setText("View+Edit");
        } else {
            button.addActionListener(e -> {
                Main.guiMain.guiEditDetail.refreshData(incOrExp, typeKey, (String) keys[row]);
                Main.guiMain.crd.show(Main.guiMain.cPane, "editDetail");
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