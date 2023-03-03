package org.joew502;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIInOrExpMenu {
    public JPanel mainPanel;
    private JButton inputIncomeButton;
    private JButton inputExpenditureButton;
    private JButton returnButton;
public GUIInOrExpMenu() {
    inputIncomeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.guiMain.guiTypeSelect.refreshData("Income");
            Main.guiMain.crd.show(Main.guiMain.cPane, "typeSelect");
        }
    });
    inputExpenditureButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.guiMain.guiTypeSelect.refreshData("Expenditure");
            Main.guiMain.crd.show(Main.guiMain.cPane, "typeSelect");
        }
    });
    returnButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.guiMain.crd.show(Main.guiMain.cPane, "mainMenu");
        }
    });
}
}
