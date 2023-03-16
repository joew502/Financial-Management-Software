package org.joew502;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

//@SuppressWarnings("unchecked")
public class DataMain {
    private JSONObject jsonData;
    public DataMain() {
        jsonData = new JSONObject();
        jsonData.put("Income", new LinkedHashMap<String,Integer>());
        jsonData.put("Expenditure", new LinkedHashMap<String,Integer>());
    }
    public LinkedHashMap<String,LinkedHashMap<String,Integer>> getHash(String incOrExp) {
        return (LinkedHashMap<String,LinkedHashMap<String,Integer>>) jsonData.get(incOrExp);
    }
    public LinkedHashMap<String,Integer> getHash(String incOrExp, String typeKey) {
        return getHash(incOrExp).get(typeKey);
    }
    public Object[] getKeys(String incOrExp, String typeKey) {
        return getHash(incOrExp, typeKey).keySet().toArray();
    }
    public Object[] getKeys(String incOrExp) {
        return getHash(incOrExp).keySet().toArray();
    }
    public Collection<Integer> getValues(String incOrExp, String typeKey) {
        return getHash(incOrExp,typeKey).values();
    }
    public Integer getValue(String incOrExp, String typeKey, String detailKey) {
        return getHash(incOrExp, typeKey).get(detailKey);
    }
    public Integer getTotal(String incOrExp, String typeKey) {
        Integer total = 0;
        for (Integer value:getValues(incOrExp,typeKey)) {
            total += value;
        }
        return total;
    }
    public Integer getTotal(String incOrExp) {
        Integer total = 0;
        for (Object typeKey:getKeys(incOrExp)) {
            total += getTotal(incOrExp, (String) typeKey);
        }
        return total;
    }
    public String load(String filePath){
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            jsonData = (JSONObject) in.readObject();
            in.close();
            fileIn.close();
            return "Loaded Successfully";
        } catch(Exception e) {
            e.printStackTrace();
            return "Load Failed";
        }
    }
    public String save(String filePath){
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(jsonData);
            out.close();
            fileOut.close();
            return "Saved Successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Save Failed";
        }
    }
    public void loadJson(String filePath){ //TODO: Remove dev tools
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(filePath));
            jsonData = (JSONObject) obj;

            JSONObject incomeData = (JSONObject) jsonData.get("Income");
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
            jsonData.remove("Income");
            jsonData.put("Income", newIncomeData);

            JSONObject expenditureData = (JSONObject) jsonData.get("Expenditure");
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
            jsonData.remove("Expenditure");
            jsonData.put("Expenditure", newExpenditureData);

            System.out.println("Loaded Successfully");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Load Failed");
        }
    }
    public void saveJson(String filePath){ //TODO: Remove dev tools
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(jsonData.toJSONString());
            file.close();
            System.out.println("Saved Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save Failed");
        }
    }
}
