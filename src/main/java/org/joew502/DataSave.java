package org.joew502;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
public class DataSave {
    public void save(String filePath){
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(DataStore.jsonData.toJSONString());
            file.close();
            System.out.println("Saved Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save Failed");
        }
    }
}
