package org.joew502;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class DataMainTest {

    /*
    @Test
    public void save() {
        // SETUP
        Main.jsonData = new JSONObject();
        Main.jsonData.put("Test1","1"); // Prepare data to be saved
        DataMain.save("Test_File_1.json"); // Save test file

        // TEST
        File testFile1 = new File("Test_File_1.json"); // Try to load file
        Assert.assertTrue(testFile1.exists()); // Test file exists

        // TEARDOWN
        if(testFile1.exists()){ // Delete file if it does exist
            if (!testFile1.delete()){
                System.out.println("Error Deleting test file");
            }
        }
        Main.jsonData.remove("Test1"); // Delete test data
    }
    @Test
    public void saveAndLoad() {
        // SETUP - Save
        Main.jsonData = new JSONObject();
        Main.jsonData.put("Test2", "2");
        DataMain.save("Test_File_2.json");

        // TEST - Save
        File testFile1 = new File("Test_File_2.json");
        Assert.assertTrue(testFile1.exists());

        // SETUP - Load
        Main.jsonData.remove("Test2"); // Delete test data
        Main.jsonData = new JSONObject();
        Assert.assertFalse(Main.jsonData.containsKey("Test2"));

        // TEST - Load
        DataMain.load("Test_File_2.json");
        Assert.assertTrue(Main.jsonData.containsKey("Test2"));
        Assert.assertEquals("2", Main.jsonData.get("Test2"));

        // TEARDOWN
        if(testFile1.exists()){
            if (!testFile1.delete()){
                System.out.println("Error Deleting test file");
            }
        }
        Main.jsonData.remove("Test2");
    }*/
}