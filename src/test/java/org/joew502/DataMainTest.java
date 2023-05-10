package org.joew502;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DataMainTest {
    private DataMain dataTest;

    @Before
    public void setUp(){
        dataTest = new DataMain();
        String[] incAndExp = new String[] {"Income", "Expenditure"};
        for (String incOrExp:incAndExp) {
            for (int i = 1; i < 3; i++) {
                String typeName = "Test "+incOrExp+" Type "+i;
                dataTest.addType(incOrExp, typeName);
                dataTest.setExpected(incOrExp, typeName, i*10F);
                for (int a = 1; a < 3; a++) {
                    String detailName = "Test "+incOrExp+" Detail "+i+a;
                    dataTest.addDetail(incOrExp, typeName, detailName, a);
                }
            }
        }
    }

    @After
    public void tearDown(){
        dataTest = null;
    }

    @Test
    public void getKeys() {
        assertArrayEquals(dataTest.getKeys("Expenditure", "Test Expenditure Type 1"),
                new Object[]{"Test Expenditure Detail 11", "Test Expenditure Detail 12"});
    }

    @Test
    public void testGetKeys() {
        assertArrayEquals(dataTest.getKeys("Income"),
                new Object[]{"Test Income Type 1", "Test Income Type 2"});
    }

    @Test
    public void getValues() {
        assertTrue(dataTest.getValues("Income", "Test Income Type 1").contains(1F));
        assertTrue(dataTest.getValues("Income", "Test Income Type 1").contains(2F));
    }

    @Test
    public void getValue() {
        assertEquals(dataTest.getValue("Income", "Test Income Type 2",
                "Test Income Detail 21"), 1F, 0F);
    }

    @Test
    public void getTotal() {
        assertEquals(dataTest.getTotal("Income", "Test Income Type 1"), 3F, 0F);
    }

    @Test
    public void testGetTotal() {
        assertEquals(dataTest.getTotal("Income"), 6F, 0F);
    }

    @Test
    public void addType() {
        assertArrayEquals(dataTest.getKeys("Income"),
                new Object[]{"Test Income Type 1", "Test Income Type 2"});
        dataTest.addType("Income", "Test Income Type 3");
        assertArrayEquals(dataTest.getKeys("Income"),
                new Object[]{"Test Income Type 1", "Test Income Type 2", "Test Income Type 3"});
    }

    @Test
    public void deleteType() {
        assertArrayEquals(dataTest.getKeys("Income"),
                new Object[]{"Test Income Type 1", "Test Income Type 2"});
        dataTest.deleteType("Income", "Test Income Type 2");
        assertArrayEquals(dataTest.getKeys("Income"),
                new Object[]{"Test Income Type 1"});
    }

    @Test
    public void addValue() {
        assertEquals(dataTest.getValue("Income", "Test Income Type 2",
                "Test Income Detail 21"), 1F, 0F);
        dataTest.addValue("Income", "Test Income Type 2",
                "Test Income Detail 21", 3F);
        assertEquals(dataTest.getValue("Income", "Test Income Type 2",
                "Test Income Detail 21"), 4F, 0F);
    }

    @Test
    public void addDetail() {
        assertArrayEquals(dataTest.getKeys("Expenditure", "Test Expenditure Type 1"),
                new Object[]{"Test Expenditure Detail 11", "Test Expenditure Detail 12"});
        dataTest.addDetail("Expenditure", "Test Expenditure Type 1",
                "Test Expenditure Detail 13", 3F);
        assertArrayEquals(dataTest.getKeys("Expenditure", "Test Expenditure Type 1"),
                new Object[]{"Test Expenditure Detail 11", "Test Expenditure Detail 12", "Test Expenditure Detail 13"});
    }

    @Test
    public void deleteDetail() {
        assertArrayEquals(dataTest.getKeys("Expenditure", "Test Expenditure Type 1"),
                new Object[]{"Test Expenditure Detail 11", "Test Expenditure Detail 12"});
        dataTest.deleteDetail("Expenditure", "Test Expenditure Type 1",
                "Test Expenditure Detail 12");
        assertArrayEquals(dataTest.getKeys("Expenditure", "Test Expenditure Type 1"),
                new Object[]{"Test Expenditure Detail 11"});
    }

    @Test
    public void getExpectedValue() {
        assertEquals(dataTest.getExpectedValue("Income", "Test Income Type 1"), 10F, 0F);
    }

    @Test
    public void getExpectedTotal() {
        assertEquals(dataTest.getExpectedTotal("Income"), 30F, 0F);
    }

    @Test
    public void setExpected() {
        assertEquals(dataTest.getExpectedValue("Income", "Test Income Type 1"), 10F, 0F);
        dataTest.setExpected("Income", "Test Income Type 1", 20F);
        assertEquals(dataTest.getExpectedValue("Income", "Test Income Type 1"), 20F, 0F);
    }

    @Test
    public void getFinal() {
        assertFalse(dataTest.getFinal("Income", "Test Income Type 1"));
    }

    @Test
    public void getFinalStr() {
        assertEquals(dataTest.getFinalStr("Income", "Test Income Type 1"), "False");
    }

    @Test
    public void toggleFinal() {
        assertFalse(dataTest.getFinal("Income", "Test Income Type 1"));
        dataTest.toggleFinal("Income", "Test Income Type 1");
        assertTrue(dataTest.getFinal("Income", "Test Income Type 1"));
    }

    @Test
    public void saveAndLoad() {
        DataMain dataTestCopy = new DataMain(dataTest);
        dataTest.save("testfile.ser");
        dataTest = null;
        dataTest = new DataMain();
        dataTest.load("testfile.ser");
        assertArrayEquals(dataTest.getKeys("Income"), dataTestCopy.getKeys("Income"));

        File myObj = new File("testfile.ser");
        assertTrue(myObj.delete());
    }
}