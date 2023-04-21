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
        jsonData.put("Income", new LinkedHashMap<String, JSONObject>());
        jsonData.put("Expenditure", new LinkedHashMap<String, JSONObject>());
    }
    private LinkedHashMap<String, JSONObject> getHash(String incOrExp) {
        return (LinkedHashMap<String,JSONObject>) jsonData.get(incOrExp);
    }
    private LinkedHashMap<String, Float> getDetailHash(String incOrExp, String typeKey) {
        return (LinkedHashMap<String, Float>) getHash(incOrExp).get(typeKey).get("Detail");
    }
    private JSONObject getHash(String incOrExp, String typeKey) {
        return getHash(incOrExp).get(typeKey);
    }
    public Object[] getKeys(String incOrExp, String typeKey) {
        return getDetailHash(incOrExp, typeKey).keySet().toArray();
    }
    public Object[] getKeys(String incOrExp) {
        return getHash(incOrExp).keySet().toArray();
    }
    public Collection<Float
            > getValues(String incOrExp, String typeKey) {
        return getDetailHash(incOrExp,typeKey).values();
    }
    public float getValue(String incOrExp, String typeKey, String detailKey) {
        return getDetailHash(incOrExp, typeKey).get(detailKey);
    }
    public float getTotal(String incOrExp, String typeKey) {
        float total = 0;
        for (float value:getValues(incOrExp,typeKey)) {
            total += value;
        }
        return total;
    }
    public float getTotal(String incOrExp) {
        float total = 0;
        for (Object typeKey:getKeys(incOrExp)) {
            total += getTotal(incOrExp, (String) typeKey);
        }
        return total;
    }
    public boolean isEmpty() {
        return getHash("Income").isEmpty() && getHash("Expenditure").isEmpty();
    }
    public boolean addType(String incOrExp, String typeKey) {
        if (getHash(incOrExp).containsKey(typeKey)) {
            return false;
        } else {
            getHash(incOrExp).put(typeKey, new JSONObject());
            getHash(incOrExp, typeKey).put("Detail", new LinkedHashMap<String, Float>());
            getHash(incOrExp, typeKey).put("Expected", 0F);
            getHash(incOrExp, typeKey).put("Final", false);
            return true;
        }
    }
    public void deleteType(String incOrExp, String typeKey) {
        getHash(incOrExp).remove(typeKey);
    }
    public void addValue(String incOrExp, String typeKey, String detailKey, float value) {
        getDetailHash(incOrExp, typeKey).put(detailKey, getValue(incOrExp, typeKey, detailKey)+value);
    }
    public boolean addDetail(String incOrExp, String typeKey, String detailKey, float value) {
        if (getDetailHash(incOrExp, typeKey).containsKey(detailKey)) {
            return false;
        } else {
            getDetailHash(incOrExp, typeKey).put(detailKey, value);
            return true;
        }
    }
    public void deleteDetail(String incOrExp, String typeKey, String detailKey) {
        getDetailHash(incOrExp, typeKey).remove(detailKey);
    }
    public float getExpectedValue(String incOrExp, String typeKey) {
        return (float) getHash(incOrExp, typeKey).get("Expected");
    }
    public float getExpectedTotal(String incOrExp) {
        float total = 0;
        for (Object typeKey:getKeys(incOrExp)) {
            float expectedValue = getExpectedValue(incOrExp, (String) typeKey);
            float currentValue = getTotal(incOrExp, (String) typeKey);
            if (expectedValue > currentValue && !getFinal(incOrExp, (String) typeKey)) {
                total += expectedValue;
            } else {
                total += currentValue;
            }
        }
        return total;
    }
    public boolean getFinal(String incOrExp, String typeKey) {
        return (boolean) getHash(incOrExp, typeKey).get("Final");
    }
    public String getFinalStr(String incOrExp, String typeKey) {
        if (getFinal(incOrExp, typeKey)) {
            return "True";
        }
        return "False";
    }
    public void toggleFinal(String incOrExp, String typeKey) {
        if (getFinal(incOrExp, typeKey)) {
            getHash(incOrExp, typeKey).put("Final", false);
        } else {
            getHash(incOrExp, typeKey).put("Final", true);
        }
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

            String[] incAndExp = new String[] {"Income", "Expenditure"};
            for (String incOrExp:incAndExp) {
                JSONObject data = (JSONObject) jsonData.get(incOrExp);
                Object[] typeKeys = data.keySet().toArray();
                List<Object> typeKeys2 = new ArrayList<>(Arrays.asList(typeKeys));
                Collections.reverse(typeKeys2);
                LinkedHashMap<String,JSONObject> newData = new LinkedHashMap<String,JSONObject>();
                String[] incomeTypeKeys = new String[] {"Membership", "Fundraising", "Sponsorship", "Other Income"};
                String[] expenditureTypeKeys = new String[] {"AU Membership", "BUCS Affiliation", "NGB Membership",
                        "Facility Contribution", "Facility Hire", "Equipment Purchase", "Facility Hire",
                        "Coaching Costs", "Facility Hire", "Competition Event Costs", "Other Event Costs",
                        "Other Costs"};
                if (incOrExp == "Income") {
                    for (String incomeTypeKey:incomeTypeKeys) {
                        newData.put(incomeTypeKey, new JSONObject());
                    }
                } else {
                    for (String expTypeKey:expenditureTypeKeys) {
                        newData.put(expTypeKey, new JSONObject());
                    }
                }
                for (Object typeKey:typeKeys2) {
                    //newIncomeData.put((String) incomeKey, new JSONObject());
                    newData.get(typeKey).put("Detail", new LinkedHashMap<String, Float>());
                    newData.get(typeKey).put("Expected", ((Double) ((JSONObject) data.get(typeKey)).get("Expected")).floatValue());
                    newData.get(typeKey).put("Final", ((boolean) ((JSONObject) data.get(typeKey)).get("Final")));
                    JSONObject detail = (JSONObject) ((JSONObject) data.get(typeKey)).get("Detail");
                    Object[] detailKeys = detail.keySet().toArray();
                    List<Object> detailKeys2 = new ArrayList<>(Arrays.asList(detailKeys));
                    Collections.reverse(detailKeys2);
                    LinkedHashMap<String, Float> newDetail = (LinkedHashMap<String, Float>) newData.get(typeKey).get("Detail");
                    for (Object incomeDetailKey:detailKeys2) {
                        newDetail.put((String) incomeDetailKey, ((Double) detail.get(incomeDetailKey)).floatValue());
                    }
                }
                jsonData.remove(incOrExp);
                jsonData.put(incOrExp, newData);
            }

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
