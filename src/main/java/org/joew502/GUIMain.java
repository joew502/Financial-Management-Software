package org.joew502;

import javax.swing.*;
import java.awt.*;

public class GUIMain extends JFrame {
    public CardLayout crd;
    public Container cPane;
    public GUIMainMenu guiMainMenu;
    public GUIIncAndExp guiIncAndExp;
    public GUIEditDetail guiEditDetail;
    public GUIAddType guiAddType;
    public GUIIncOrExpType guiIncOrExpType;
    public GUIAddDetail guiAddDetail;
    public GUISetExpected guiSetExpected;
    public GUIMain(){

        cPane = getContentPane();
        crd = new CardLayout();
        cPane.setLayout(crd);

        guiMainMenu = new GUIMainMenu();
        cPane.add("mainMenu", guiMainMenu.mainPanel);

        guiIncAndExp = new GUIIncAndExp();
        cPane.add("incAndExp", guiIncAndExp.mainPanel);

        guiEditDetail = new GUIEditDetail();
        cPane.add("editDetail", guiEditDetail.mainPanel);

        guiAddType = new GUIAddType();
        cPane.add("addType", guiAddType.mainPanel);

        guiIncOrExpType = new GUIIncOrExpType();
        cPane.add("incOrExpType", guiIncOrExpType.mainPanel);

        guiAddDetail = new GUIAddDetail();
        cPane.add("addDetail", guiAddDetail.mainPanel);

        guiSetExpected = new GUISetExpected();
        cPane.add("setExpected", guiSetExpected.mainPanel);

        setTitle("Financial Management Software");
        setBounds(200,200,1000,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
