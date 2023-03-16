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
    private JButton loadDataJSONButton;
    private JButton saveDataJSONButton;
    private JLabel resultLabel;

    public GUIMainMenu() {
    loadDataButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            resultLabel.setText(Main.dataMain.load("program_data.ser"));
        }
    });
    saveDataButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            resultLabel.setText(Main.dataMain.save("program_data.ser"));
        }
    });
    displayInAndExpButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.guiMain.guiIncAndExp.refreshData();
            Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
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
                Main.dataMain.loadJson("program_data.json");
            }
        });
        saveDataJSONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.dataMain.saveJson("program_data.json");
            }
        });
    }
}
