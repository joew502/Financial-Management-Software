package org.joew502;

import javax.swing.*;
import java.awt.*;

public class GUIMain extends JFrame {
    public CardLayout crd;
    public Container cPane;
    public GUIIncAndExp guiIncAndExp;
    public GUIInputValue guiInputValue;
    public GUIAddType guiAddType;
    public GUIIncOrExpDetail guiIncOrExpDetail;
    public GUIMain(){

        cPane = getContentPane();
        crd = new CardLayout();
        cPane.setLayout(crd);

        GUIMainMenu guiMainMenu = new GUIMainMenu();
        cPane.add("mainMenu", guiMainMenu.mainPanel);

        guiIncAndExp = new GUIIncAndExp();
        cPane.add("incAndExp", guiIncAndExp.mainPanel);

        guiInputValue = new GUIInputValue();
        cPane.add("inputValue", guiInputValue.mainPanel);

        guiAddType = new GUIAddType();
        cPane.add("addType", guiAddType.mainPanel);

        guiIncOrExpDetail = new GUIIncOrExpDetail();
        cPane.add("incOrExpDetail", guiIncOrExpDetail.mainPanel);

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
