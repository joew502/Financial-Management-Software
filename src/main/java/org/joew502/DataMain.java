package org.joew502;

import java.io.*;
import java.util.*;
import org.json.simple.*;

/**
 * This class acts as the Main Model for the program, interacting between the controllers and data structure
 */
@SuppressWarnings("unchecked")
public class DataMain {
    private JSONObject jsonData; // This attribute contains the current loaded data structure for the program

    /**
     * This constructor populates an empty data set on initialisation of a new instance
     */
    public DataMain() {
        jsonData = new JSONObject();
        jsonData.put("Income", new LinkedHashMap<String, JSONObject>());
        jsonData.put("Expenditure", new LinkedHashMap<String, JSONObject>());
    }

    /**
     * Allows a deep copy of the data structure to be made for testing purposes
     * @param dataMain - An instance of DataMain to copy
     */
    public DataMain(DataMain dataMain) {
        this.jsonData = new JSONObject(dataMain.jsonData);
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @return - LHM containing the various related 'type' data
     */
    private LinkedHashMap<String, JSONObject> getHash(String incOrExp) {
        return (LinkedHashMap<String,JSONObject>) jsonData.get(incOrExp);
    }

    /**
     * @param incOrExp  - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @return - LHM containing the various related 'detail' data
     */
    private LinkedHashMap<String, Float> getDetailHash(String incOrExp, String typeKey) {
        return (LinkedHashMap<String, Float>) getHash(incOrExp).get(typeKey).get("Detail");
    }

    /**
     * @param incOrExp  - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @return - JSONObject containing the associated expected value, 'final' boolean and 'detail' LHM
     */
    private JSONObject getHash(String incOrExp, String typeKey) {
        return getHash(incOrExp).get(typeKey);
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @return - Object list containing the keys for all 'detail' for the given type
     */
    public Object[] getKeys(String incOrExp, String typeKey) {
        return getDetailHash(incOrExp, typeKey).keySet().toArray();
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @return Object list containing the keys for all 'types' for the given section
     */
    public Object[] getKeys(String incOrExp) {
        return getHash(incOrExp).keySet().toArray();
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @return - Collection of floats, that are the values related to each 'detail' for the given 'type'
     */
    public Collection<Float
            > getValues(String incOrExp, String typeKey) {
        return getDetailHash(incOrExp,typeKey).values();
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @param detailKey - Detail Key in string form
     * @return - Float value of the given 'detail' for the given 'type'
     */
    public float getValue(String incOrExp, String typeKey, String detailKey) {
        return getDetailHash(incOrExp, typeKey).get(detailKey);
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @return - Float value of the total value for the given type
     */
    public float getTotal(String incOrExp, String typeKey) {
        float total = 0;
        for (float value:getValues(incOrExp,typeKey)) {
            total += value;
        }
        return total;
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @return - Float value of the total current Income or Expenditure
     */
    public float getTotal(String incOrExp) {
        float total = 0;
        for (Object typeKey:getKeys(incOrExp)) {
            total += getTotal(incOrExp, (String) typeKey);
        }
        return total;
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * The given type key is used to check if a type of that name exists, if it doesn't, a new type is created in
     *               this name with the necessary structure.
     * @return - Boolean value signifying if the addition was successful
     */
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

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * Deletes the 'type' specified from the data structure
     */
    public void deleteType(String incOrExp, String typeKey) {
        getHash(incOrExp).remove(typeKey);
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @param detailKey - Detail Key in string form
     * @param value - Float value
     * Adds the given float value to the current value for the related 'detail'
     */
    public void addValue(String incOrExp, String typeKey, String detailKey, float value) {
        getDetailHash(incOrExp, typeKey).put(detailKey, getValue(incOrExp, typeKey, detailKey)+value);
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @param detailKey - Detail Key in string form
     * @param value - Float value
     * Checks if the given 'detail' key already exists, if it doesn't,
     *              a new 'detail' is added with the given starting value.
     * @return - Boolean value signifying if the addition was successful
     */
    public boolean addDetail(String incOrExp, String typeKey, String detailKey, float value) {
        if (getDetailHash(incOrExp, typeKey).containsKey(detailKey)) {
            return false;
        } else {
            getDetailHash(incOrExp, typeKey).put(detailKey, value);
            return true;
        }
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @param detailKey - Detail Key in string form
     * Deletes the 'detail' specified from the data structure
     */
    public void deleteDetail(String incOrExp, String typeKey, String detailKey) {
        getDetailHash(incOrExp, typeKey).remove(detailKey);
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @return - Expected float value of the given type
     */
    public float getExpectedValue(String incOrExp, String typeKey) {
        return (float) getHash(incOrExp, typeKey).get("Expected");
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * In line with the financial logic, the Expected total is calculated by adding all the expected type totals,
     *                 unless the current value is greater of the type is set as final. In either of these cases the
     *                 current value is added instead.
     * @return - Expected float value for the entire section specified
     */
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

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @param value - Float value
     * Sets the expected value of the given type to the given value.
     */
    public void setExpected(String incOrExp, String typeKey, float value) {
        getHash(incOrExp, typeKey).put("Expected", value);
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @return - Boolean signifying whether the type is set to be it's 'final' state
     */
    public boolean getFinal(String incOrExp, String typeKey) {
        return (boolean) getHash(incOrExp, typeKey).get("Final");
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * @return - 'Final' state boolean in string form
     */
    public String getFinalStr(String incOrExp, String typeKey) {
        if (getFinal(incOrExp, typeKey)) {
            return "True";
        }
        return "False";
    }

    /**
     * @param incOrExp - String of "Income" or "Expenditure"
     * @param typeKey - Type Key in string form
     * Toggles the 'final' state boolean for the given type
     */
    public void toggleFinal(String incOrExp, String typeKey) {
        if (getFinal(incOrExp, typeKey)) {
            getHash(incOrExp, typeKey).put("Final", false);
        } else {
            getHash(incOrExp, typeKey).put("Final", true);
        }
    }

    /**
     * @param filePath - File path for file to load
     * Loads the contents of the given file path, checks the contents is compatible and if it is overwrites the
     *                 current data structure.
     * @return - Boolean signifying if the file load was successful
     */
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

    /**
     * @param filePath - File path for file to load
     * Saves the contents of the data structure to the given file path
     * @return  - Boolean signifying if the file save was successful
     */
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

    /**
     * @param dataFile - JSONObject data structure
     * @return - Boolean signifying if the given JSONObject os compatible with the program
     */
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
