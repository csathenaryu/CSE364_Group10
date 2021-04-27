package vimprojector.customdatastructure;

import java.util.ArrayList;
import java.util.HashMap;

public class StaticFieldList {
    String[] field;
    int fieldLength;
    ArrayList<HashMap<String, String>> data;

    public StaticFieldList(String[] fieldList){
        field = fieldList;
        fieldLength = fieldList.length;
        data = new ArrayList<>();
    }

    public void push(String[] splitData){
        int splitDataLength = Math.min(splitData.length, fieldLength);
        // assert splitDataLength == labelLength
        HashMap<String, String> temp = new HashMap<>();
        for(int i=0; i<splitDataLength; i++){
            temp.put(field[i], splitData[i]);
        }
        data.add(temp);
    }

    public ArrayList<HashMap<String, String>> getLoadedData(){
        return data;
    }
}
