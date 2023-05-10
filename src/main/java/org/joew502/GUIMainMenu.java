package org.joew502;

import javax.swing.*;
import java.io.File;

import static java.lang.System.exit;

public class GUIMainMenu {
    public JPanel mainPanel;
    private JButton loadDataButton;
    private JButton saveDataButton;
    private JButton displayInAndExpButton;
    private JButton exitButton;

    public GUIMainMenu() {
        loadDataButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(Main.guiMain);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                if (Main.dataMain.load(file.getPath())) {
                    JOptionPane.showMessageDialog(Main.guiMain, "Loaded Successfully");
                } else {
                    JOptionPane.showMessageDialog(Main.guiMain, "Loaded Failed - Ensure file format is" +
                            " correct");
                }
            }
        });
        saveDataButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(Main.guiMain);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                if (Main.dataMain.save(file.getPath())) {
                    JOptionPane.showMessageDialog(Main.guiMain, "Saved Successfully");
                } else {
                    JOptionPane.showMessageDialog(Main.guiMain, "Save Failed");
                }
            }
        });
        displayInAndExpButton.addActionListener(e -> {
            Main.guiMain.guiIncAndExp.refreshData();
            Main.guiMain.crd.show(Main.guiMain.cPane, "incAndExp");
        });
        exitButton.addActionListener(e -> exit(0));
    }
}
