package org.joew502;

import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class DataManage {
    public static void load(String filePath){
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(filePath));
            Main.jsonData = (JSONObject) obj;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void save(String filePath){
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(Main.jsonData.toJSONString());
            file.close();
            System.out.println("Saved Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save Failed");
        }
    }
}
