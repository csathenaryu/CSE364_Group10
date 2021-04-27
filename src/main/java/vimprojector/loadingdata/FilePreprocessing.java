package vimprojector.loadingdata;

import vimprojector.customdatastructure.StaticFieldList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class FilePreprocessing {
    // file 이 존재하지 않는 경우 empty arrayList 가 반환됨
    public static ArrayList<HashMap<String, String>> loadDataFrom(String fileName, String[] dataLabel, String charset){
        // try 문 안으로 넣어야 할까?
        StaticFieldList staticFieldList = new StaticFieldList(dataLabel);
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(fileInputStream, charset));
            String dataLine;
            while ((dataLine = bufReader.readLine()) != null) {
                String[] splitData = dataLine.split("::");
                staticFieldList.push(splitData);
            }
            bufReader.close();
        } catch(Exception e) {
            System.out.printf("There is no such file: %s%n", fileName);
        }
        return staticFieldList.getLoadedData();
    }
}
