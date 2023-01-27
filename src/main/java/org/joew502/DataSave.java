package org.joew502;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
public class DataSave {
    public String save(JSONObject jsonData, String filePath, String fileName){
        String fullPath = filePath+fileName+".json";
        try {
            FileWriter file = new FileWriter(fullPath);
            file.write(jsonData.toJSONString());
            file.close();
            return "Saved Successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Save Failed";
        }
    }
}
