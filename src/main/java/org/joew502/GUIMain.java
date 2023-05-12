package org.joew502;

import javax.swing.*;
import java.awt.*;

/**
 * This class acts as a main controller for the program. The class extends the swing JFrame and on initialisation,
 * initialises and adds all the program views to the card layout.
 */
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

        // Setting the layout of the JFame content pane to a card layout, allowing switching between views
        cPane = getContentPane();
        crd = new CardLayout();
        cPane.setLayout(crd);

        // Each view is initialised and added to the card layout with a relevant identifier
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

        // Variables and bounds are set for the JFrame
        setTitle("Financial Management Software");
        setBounds(200,200,1000,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
