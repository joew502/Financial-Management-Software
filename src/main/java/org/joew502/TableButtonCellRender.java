package org.joew502;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * This class creates a modified version of the DefaultTableCellRenderer that allows the appearance of a JButton
 * within a table cell.
 */
public class TableButtonCellRender extends DefaultTableCellRenderer {
    @SuppressWarnings("FieldMayBeFinal")
    private String buttonText;
    public TableButtonCellRender(String buttonText){
        this.buttonText = buttonText;
    }

    /**
     * @param table      the <code>JTable</code>
     * @param value      the value to assign to the cell at
     *                   <code>[row, column]</code>
     * @param isSelected true if cell is selected
     * @param hasFocus   true if cell has focus
     * @param row        the row of the cell to render
     * @param column     the column of the cell to render
     * This default method has been modified to include the creation of a JButton
     * @return
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        JButton button = new JButton();
        button.setText(buttonText);
        return button;
    }
}
