package org.joew502;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableButtonCellRender extends DefaultTableCellRenderer {
    private String buttonText = null;
    public TableButtonCellRender(String buttonText){
        this.buttonText = buttonText;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        JButton button = new JButton();
        button.setText(buttonText);
        return button;
    }
}
