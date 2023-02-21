package org.joew502;

import org.json.simple.JSONObject;

public class Main{
    public static JSONObject jsonData;
    public static GUIMain guiMain;
    public static void main(String[] args) {

        jsonData = new JSONObject();
        jsonData.put("Income", new JSONObject());
        jsonData.put("Expenditure", new JSONObject());

        guiMain = new GUIMain();
        
        //CmdMenu.mainMenu();
    }
}