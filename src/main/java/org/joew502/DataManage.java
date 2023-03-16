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
            LinkedHashMap<String,LinkedHashMap<String,Integer>> newIncomeData = new LinkedHashMap<String,LinkedHashMap<String,Integer>>();
            for (Object incomeKey:incomeKeys2) {
                newIncomeData.put((String) incomeKey, new LinkedHashMap<String,Integer>());
                JSONObject incomeKeyDetail = (JSONObject) incomeData.get(incomeKey);
                Object[] incomeDetailKeys = incomeKeyDetail.keySet().toArray();
                List<Object> incomeDetailKeys2 = new ArrayList<>(Arrays.asList(incomeDetailKeys));
                Collections.reverse(incomeDetailKeys2);
                LinkedHashMap<String,Integer> newIncomeKeyDetail = newIncomeData.get(incomeKey);
                for (Object incomeDetailKey:incomeDetailKeys2) {
                    newIncomeKeyDetail.put((String) incomeDetailKey, ((Long) incomeKeyDetail.get(incomeDetailKey)).intValue());
                }
            }
            Main.jsonData.remove("Income");
            Main.jsonData.put("Income", newIncomeData);

            JSONObject expenditureData = (JSONObject) Main.jsonData.get("Expenditure");
            Object[] expenditureKeys = expenditureData.keySet().toArray();
            List<Object> expenditureKeys2 = new ArrayList<>(Arrays.asList(expenditureKeys));
            Collections.reverse(expenditureKeys2);
            LinkedHashMap<String,LinkedHashMap<String,Integer>> newExpenditureData = new LinkedHashMap<String,LinkedHashMap<String,Integer>>();
            for (Object expenditureKey:expenditureKeys2) {
                newExpenditureData.put((String) expenditureKey, new LinkedHashMap<String,Integer>());
                JSONObject expenditureKeyDetail = (JSONObject) expenditureData.get(expenditureKey);
                Object[] detailKeys = expenditureKeyDetail.keySet().toArray();
                List<Object> detailKeys2 = new ArrayList<>(Arrays.asList(detailKeys));
                Collections.reverse(detailKeys2);
                LinkedHashMap<String,Integer> newExpenditureKeyDetail = newExpenditureData.get(expenditureKey);
                for (Object detailKey:detailKeys2) {
                    newExpenditureKeyDetail.put((String) detailKey, ((Long) expenditureKeyDetail.get(detailKey)).intValue());
                }
            }
            Main.jsonData.remove("Expenditure");
            Main.jsonData.put("Expenditure", newExpenditureData);

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
