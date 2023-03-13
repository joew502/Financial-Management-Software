package org.joew502;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

public class GUIMainMenu {
    public JPanel mainPanel;
    private JButton loadDataButton;
    private JButton saveDataButton;
    private JButton displayInAndExpButton;
    private JButton exitButton;
    private JPanel buttonPanel;
    private JButton loadDataJSONButton;
    private JButton saveDataJSONButton;

    public GUIMainMenu() {
    loadDataButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DataManage.load("program_data.ser");
        }
    });
    saveDataButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DataManage.save("program_data.ser");
        }
    });
    displayInAndExpButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.guiMain.guiInOrExp.refreshData();
            Main.guiMain.crd.show(Main.guiMain.cPane, "inOrExp");
        }
    });
    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            exit(0);
        }
    });
        loadDataJSONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataManage.loadJson("program_data.json");
            }
        });
        saveDataJSONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataManage.saveJson("program_data.json");
            }
        });
    }
}
