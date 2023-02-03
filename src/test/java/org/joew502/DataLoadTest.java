package org.joew502;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DataLoadTest {

    @Test
    public void load() {

        /*JSONObject jsonObjectSave = new JSONObject();
        jsonObjectSave.put("testvalue","1");
        new DataSave().save(jsonObjectSave, "", "testfile");
        File checkFile = new File("testfile.json");
        Assert.assertTrue(checkFile.exists());

        /*JSONObject jsonObjectLoad = new DataLoad().load("testfile.json");

        String testValue = (String)jsonObjectLoad.get("testvalue");

        Assert.assertEquals("1",testValue);

        if(checkFile.exists()){
            if (!checkFile.delete()){
                System.out.println("Error Deleting test file");
            }
        }*/
    }
}