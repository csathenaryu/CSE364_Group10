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

    public static HashMap<Integer, String> loadHashFrom(String fileName, String[] dataLabel, String key, String value, String charset){
        // try 문 안으로 넣어야 할까?
        HashMap<Integer, String> hashMap = new HashMap<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(fileInputStream, charset));
            String dataLine;
            while ((dataLine = bufReader.readLine()) != null) {
                String[] splitData = dataLine.split("::");
                HashMap<String, String> hash = makeHash(dataLabel, splitData);
                hashMap.put(Integer.parseInt(hash.get(key)), hash.get(value));
            }
            bufReader.close();
        } catch(Exception e) {
            System.out.printf("There is no such file: %s%n", fileName);
        }
        return hashMap;
    }

    public static HashMap<String, String> makeHash(String[] dataLabel, String[] splitData){
        HashMap<String, String> hashMap = new HashMap<>();
        int size = dataLabel.length;
        for(int i=0; i<size; i++){
            hashMap.put(dataLabel[i], splitData[i]);
        }
        return hashMap;
    }
}
