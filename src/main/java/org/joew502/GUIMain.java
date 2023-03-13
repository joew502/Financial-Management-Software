package org.joew502;

import javax.swing.*;
import java.awt.*;

public class GUIMain extends JFrame {
    public CardLayout crd;
    public Container cPane;
    public GUIInOrExp guiInOrExp;
    public GUIInputValue guiInputValue;
    public GUIAddType guiAddType;
    public GUIMain(){

        cPane = getContentPane();
        crd = new CardLayout();
        cPane.setLayout(crd);

        GUIMainMenu guiMainMenu = new GUIMainMenu();
        cPane.add("mainMenu", guiMainMenu.mainPanel);

        guiInOrExp = new GUIInOrExp();
        cPane.add("inOrExp", guiInOrExp.mainPanel);

        guiInputValue = new GUIInputValue();
        cPane.add("inputValue", guiInputValue.mainPanel);

        guiAddType = new GUIAddType();
        cPane.add("addType", guiAddType.mainPanel);

        //Image icon = Toolkit.getDefaultToolkit().getImage("fms_icon_small.png");
        //setIconImage(icon);
        //ImageIcon img = new ImageIcon("fms_icon_small.png");
        //setIconImage(img.getImage());
        setTitle("Financial Management Software");
        setBounds(200,200,600,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
