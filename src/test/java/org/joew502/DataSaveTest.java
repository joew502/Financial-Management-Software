package org.joew502;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;

public class DataSaveTest {

    @Test
    public void save() {
        DataSave dataSave = new DataSave();
        JSONObject jsonObject = new JSONObject();
        dataSave.save(jsonObject, "", "testfile");

        File checkFile = new File("testfile.json");
        Assert.assertTrue(checkFile.exists());

        if(checkFile.exists()){
            if (!checkFile.delete()){
                System.out.println("Error Deleting test file");
            }
        }
    }
}