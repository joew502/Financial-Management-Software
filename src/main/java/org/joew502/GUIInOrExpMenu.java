package org.joew502;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIInOrExpMenu {
    public JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton inputIncomeButton;
    private JButton inputExpenditureButton;
    private JButton returnButton;
public GUIInOrExpMenu() {
    inputIncomeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    inputExpenditureButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

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
