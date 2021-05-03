package vimprojector.parsinginputargs;

import vimprojector.customdatastructure.Pair;

import java.util.ArrayList;

public class ParsingGender extends ParsingInputArgument{
     Pair propertyMapping = new Pair(
            "m:m,f:f");

    public String[] parseProperty(String inputProp, String delimiter){
        return getParsedProperty(propertyMapping, inputProp, delimiter);
    }

    public String[] getAllProperty(){
        return propertyMapping.getAll().toArray(new String[0]);
    }

    @Override
    public void addProperty(ArrayList<String> propertyList, String property){
        if (propertyMapping.has(property)){
            String mapping = propertyMapping.get(property);
            if(!propertyList.contains(mapping)){
                propertyList.add(propertyMapping.get(property));
            }
        }
        else{
            System.out.printf("Invalid gender '%s' will be ignored.\n", property);
        }
    }
}
