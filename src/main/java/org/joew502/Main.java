package org.joew502;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;

public class Main{
    public static JSONObject jsonData;
    public static GUIMain guiMain;
    public static void main(String[] args) {

        jsonData = new JSONObject();
        jsonData.put("Income", new LinkedHashMap<String,Integer>());
        jsonData.put("Expenditure", new LinkedHashMap<String,Integer>());

        guiMain = new GUIMain();
    }
}