package org.joew502;

import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("Name", "Joe");
        //DataSave dataSave = new DataSave();
        //String saveStatus = dataSave.save(jsonObject, "", "output");
        //System.out.println(saveStatus);
        //JSONObject loadedData = new DataLoad().load("output.json");
        new MainCmdMenu().mainMenuSwitch();
    }
}