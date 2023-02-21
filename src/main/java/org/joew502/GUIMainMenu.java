package org.joew502;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

public class GUIMainMenu {
    private JButton loadDataButton;
    private JButton saveDataButton;
    private JButton inputInOrExpButton;
    private JButton displayInAndExpButton;
    private JButton exitButton;
    public JPanel mainPanel;
    private JPanel buttonPanel;

    public GUIMainMenu() {
    loadDataButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DataManage.load("program_data.json");
        }
    });
    saveDataButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DataManage.save("program_data.json");
        }
    });
    inputInOrExpButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.guiMain.crd.show(Main.guiMain.cPane, "inOrExpMenu");
        }
    });
    displayInAndExpButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CmdMenu.displayInAndExp();
        }
    });
    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            exit(0);
        }
    });
}
}
