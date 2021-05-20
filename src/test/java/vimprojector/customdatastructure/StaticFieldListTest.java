package vimprojector.customdatastructure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class StaticFieldListTest {
    String[] userLabel = {"UserID", "Gender", "Age"};
    int fieldSize = userLabel.length;
    String[] userInfo = {"20181072", "F", "23"};
    String[] userInfo2 = {"20181073", "M", "24"};
    String[] userInfoLarger = {"20181072", "F", "23", "CSE"};
    String[] userInfoSmaller = {"20181072", "F"};
    String[] userInfoSmallerResult = {"20181072", "F", null};
    String[] userInfoSmaller2 = {"", "F", "23"};
    String[] userInfoSmallerResult2 = {null, "F", "23"};

    @Test
    public void push_OneElement_IdGenderAge() {
        StaticFieldList staticFieldList = new StaticFieldList(userLabel);
        staticFieldList.push(userInfo);
        ArrayList<HashMap<String, String>> data = staticFieldList.getLoadedData();
        for(HashMap<String, String> d : data){
            for(int i=0; i<fieldSize; i++){
                assertEquals(userInfo[i], d.get(userLabel[i]));
            }
        }
    }

    @Test
    public void push_TenElement_IdGenderAge() {
        StaticFieldList staticFieldList = new StaticFieldList(userLabel);
        for(int i=0; i<10; i++){
            staticFieldList.push(userInfo2);
        }
        ArrayList<HashMap<String, String>> data = staticFieldList.getLoadedData();
        for(HashMap<String, String> d : data){
            for(int i=0; i<fieldSize; i++){
                assertEquals(userInfo2[i], d.get(userLabel[i]));
            }
        }
    }

    @Test
    public void push_LargeDataField_IdGenderAge() {
        StaticFieldList staticFieldList = new StaticFieldList(userLabel);
        staticFieldList.push(userInfoLarger);
        ArrayList<HashMap<String, String>> data = staticFieldList.getLoadedData();
        for(HashMap<String, String> d : data){
            for(int i=0; i<fieldSize; i++){
                assertEquals(userInfo[i], d.get(userLabel[i]));
            }
        }
    }

    @Test
    public void push_SmallDataField_IdGenderNull() {
        StaticFieldList staticFieldList = new StaticFieldList(userLabel);
        staticFieldList.push(userInfoSmaller);
        ArrayList<HashMap<String, String>> data = staticFieldList.getLoadedData();
        for(HashMap<String, String> d : data){
            for(int i=0; i<fieldSize; i++){
                assertEquals(userInfoSmallerResult[i], d.get(userLabel[i]));
            }
        }
    }

    @Test
    public void push_SmallDataField2_NullGenderAge() {
        StaticFieldList staticFieldList = new StaticFieldList(userLabel);
        staticFieldList.push(userInfoSmaller2);
        ArrayList<HashMap<String, String>> data = staticFieldList.getLoadedData();
        for(HashMap<String, String> d : data){
            for(int i=0; i<fieldSize; i++){
                assertEquals(userInfoSmallerResult2[i], d.get(userLabel[i]));
            }
        }
    }

}