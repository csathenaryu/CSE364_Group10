package vimprojector.parsinginputargs;

import vimprojector.customdatastructure.Pair;
import java.util.ArrayList;

public class ParsingAge extends ParsingInputArgument{

    Pair propertyMapping = new Pair(
            "under18:1,18-24:18,25-34:25,35-44:35,45-49:45,50-55:50,56+:56");

    public String[] parseProperty(String inputProp, String delimiter){
        return getParsedProperty(propertyMapping, inputProp, delimiter);
    }

    public String[] getAllProperty(){
        return propertyMapping.getAll().toArray(new String[0]);
    }

    @Override
    public void addProperty(ArrayList<String> propertyList, String property) {
        int age = Integer.parseInt(property);
        if (age >= 0){
            String key = "";
            if (age < 18) {
                key = "under18";
            } else if (age <= 24) {
                key = "18-24";
            } else if (age <= 34) {
                key = "25-34";
            } else if (age <= 44) {
                key = "35-44";
            } else if (age <= 49) {
                key = "45-49";
            } else if (age <= 55) {
                key = "50-55";
            } else{
                key = "56+";
            }

            String mappedProperty = propertyMapping.get(key);
            if (!propertyList.contains(mappedProperty)){
                propertyList.add(mappedProperty);
            } else {
                System.out.println(mappedProperty + " is invalid or already exist. Try input other ages.");
            }
        } else{
            System.out.println("invalid age.");
        }
    }
}