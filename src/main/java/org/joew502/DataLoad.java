package org.joew502;

import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class DataLoad {
    public JSONObject load(String filePath){
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(filePath));
            return (JSONObject)obj;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
