package vimprojector.parsinginputargs;

import vimprojector.customdatastructure.Pair;
import java.util.ArrayList;

public class ParsingGender extends ParsingInputArgument{
    static Pair propertyMapping = new Pair(
            "m:m,f:f");

    public String[] parseProperty(String inputProp, String delimiter){
        return getParsedProperty(propertyMapping, inputProp, delimiter);
    }

    @Override
    public void addProperty(ArrayList<String> propertyList, String property){
        if (propertyMapping.has(property) && !propertyList.contains(property)){
            propertyList.add(propertyMapping.get(property));
        }
        else{
            System.out.println(property + " is invalid or already exist. Try using another gender.");
        }
    }
}
