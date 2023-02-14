package org.joew502;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        jsonData = new JSONObject();
        jsonData.put("Income", new JSONObject());
        jsonData.put("Expenditure", new JSONObject());
        CmdMenu.mainMenu();
    }
    static JSONObject jsonData;
}