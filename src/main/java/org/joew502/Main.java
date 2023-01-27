package org.joew502;

import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        //Creating a JSONObject object
        JSONObject jsonObject = new JSONObject();
        //Inserting key-value pairs into the json object
        jsonObject.put("ID", "1");
        DataSave dataSave = new DataSave();
        String saveStatus = dataSave.save(jsonObject, "", "output");
        System.out.println(saveStatus);
    }
}