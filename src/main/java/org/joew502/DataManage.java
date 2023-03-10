package org.joew502;

import java.io.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class DataManage {
    public static void load(String filePath){
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Main.jsonData = (JSONObject) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Loaded Successfully");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Load Failed");
        }
    }
    public static void save(String filePath){
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Main.jsonData);
            out.close();
            fileOut.close();
            System.out.println("Saved Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save Failed");
        }
    }
    public static void loadJson(String filePath){ //TODO: Remove dev tools
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(filePath));
            Main.jsonData = (JSONObject) obj;
            JSONObject incomeData = (JSONObject) Main.jsonData.get("Income");
            Object[] incomeKeys = incomeData.keySet().toArray();
            List<Object> incomeKeys2 = new ArrayList<>(Arrays.asList(incomeKeys));
            Collections.reverse(incomeKeys2);
            Main.jsonData.remove("Income");
            Main.jsonData.put("Income", new LinkedHashMap<String,Integer>());
            for (Object key : incomeKeys2) {
                ((LinkedHashMap<String,Integer>) Main.jsonData.get("Income")).put((String) key, ((Long) incomeData.get(key)).intValue());
            }
            JSONObject expenditureData = (JSONObject) Main.jsonData.get("Expenditure");
            Object[] expenditureKeys = expenditureData.keySet().toArray();
            List<Object> expenditureKeys2 = new ArrayList<>(Arrays.asList(expenditureKeys));
            Collections.reverse(expenditureKeys2);
            Main.jsonData.remove("Expenditure");
            Main.jsonData.put("Expenditure", new LinkedHashMap<String,Integer>());
            for (Object key : expenditureKeys2) {
                ((LinkedHashMap<String,Integer>) Main.jsonData.get("Expenditure")).put((String) key, ((Long) expenditureData.get(key)).intValue());
            }
            System.out.println("Loaded Successfully");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Load Failed");
        }
    }
    public static void saveJson(String filePath){ //TODO: Remove dev tools
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
