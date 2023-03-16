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
    private JButton setFinancialPlanButton;

    public GUIMainMenu() {
    loadDataButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            resultLabel.setText(Main.dataMain.load("program_data.ser"));
            /*JFileChooser fileChooser = new JFileChooser(); TODO Remove Dev Tools
            int option = fileChooser.showOpenDialog(Main.guiMain);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                resultLabel.setText(Main.dataMain.load(file.getPath()));
            }*/
        }
    });
    saveDataButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            resultLabel.setText(Main.dataMain.save("program_data.ser"));
            /*JFileChooser fileChooser = new JFileChooser(); TODO Remove Dev Tools
            int option = fileChooser.showSaveDialog(Main.guiMain);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                resultLabel.setText(Main.dataMain.save(file.getPath()));
            }else{
                resultLabel.setText("Save Canceled");
            }*/
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
