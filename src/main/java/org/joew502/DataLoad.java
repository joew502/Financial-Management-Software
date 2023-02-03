package org.joew502;

import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class DataLoad {
    public void load(String filePath){
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(filePath));
            DataStore.jsonData = (JSONObject) obj;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
