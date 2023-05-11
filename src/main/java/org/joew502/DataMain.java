package org.joew502;

import java.io.*;
import java.util.*;
import org.json.simple.*;

@SuppressWarnings("unchecked")
public class DataMain {
    private JSONObject jsonData;
    public DataMain() {
        jsonData = new JSONObject();
        jsonData.put("Income", new LinkedHashMap<String, JSONObject>());
        jsonData.put("Expenditure", new LinkedHashMap<String, JSONObject>());
    }
    public DataMain(DataMain dataMain) {
        this.jsonData = new JSONObject(dataMain.jsonData);
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
    public void setExpected(String incOrExp, String typeKey, float value) {
        getHash(incOrExp, typeKey).put("Expected", value);
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
    public boolean load(String filePath){
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            jsonData = (JSONObject) in.readObject();
            in.close();
            fileIn.close();
            return dataStructCheck(jsonData);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean save(String filePath){
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(jsonData);
            out.close();
            fileOut.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @SuppressWarnings("unused")
    private boolean dataStructCheck(JSONObject dataFile) {
        if (!dataFile.containsKey("Income")) {
            return false;
        }
        if (!dataFile.containsKey("Expenditure")) {
            return false;
        }
        String[] incAndExp = new String[] {"Income", "Expenditure"};
        for (String incOrExp:incAndExp) {
            LinkedHashMap<String, JSONObject> incOrExpData;
            try {
                incOrExpData = (LinkedHashMap<String, JSONObject>) dataFile.get(incOrExp);
            } catch (Exception e) {
                return false;
            }
            for (String typeKey:incOrExpData.keySet()) {
                JSONObject typeData = incOrExpData.get(typeKey);
                String[] typeComponents = new String[] {"Expected", "Final", "Detail"};
                for (String typeComponent:typeComponents) {
                    if (!typeData.containsKey(typeComponent)) {
                        return false;
                    }
                }
                if (!(typeData.get("Expected") instanceof Float)) {
                    return false;
                }
                if (!(typeData.get("Final") instanceof Boolean)) {
                    return false;
                }
                try {
                    LinkedHashMap<String, Float> detailData = (LinkedHashMap<String, Float>) typeData.get("Detail");
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return true;
    }
}
