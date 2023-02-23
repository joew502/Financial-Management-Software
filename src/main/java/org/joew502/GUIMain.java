package org.joew502;

import javax.swing.*;
import java.awt.*;

public class GUIMain extends JFrame {
    public CardLayout crd;
    public Container cPane;
    public GUIInOrExp guiInOrExp;
    public GUIMain(){

        cPane = getContentPane();
        crd = new CardLayout();
        cPane.setLayout(crd);

        GUIMainMenu guiMainMenu = new GUIMainMenu();
        cPane.add("mainMenu", guiMainMenu.mainPanel);

        GUIInOrExpMenu guiInOrExpMenu = new GUIInOrExpMenu();
        cPane.add("inOrExpMenu", guiInOrExpMenu.mainPanel);

        guiInOrExp = new GUIInOrExp();
        cPane.add("inOrExp", guiInOrExp.mainPanel);

        //ImageIcon img = new ImageIcon("");
        //mainMenu.setIconImage(img.getImage());
        setTitle("Financial Management Software");
        setBounds(200,200,600,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
