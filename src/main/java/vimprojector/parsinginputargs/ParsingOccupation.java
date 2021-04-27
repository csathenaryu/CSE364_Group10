package vimprojector.parsinginputargs;

import vimprojector.customdatastructure.Pair;
import java.util.ArrayList;

public class ParsingOccupation extends ParsingInputArgument{
    static Pair propertyMapping = new Pair(
            "other:0,academic:1,educator:1,artist:2,clerical:3,admin:3,collegestudent:4,gradstudent:4,customerservice:5,doctor:6,healthcare:6,executive:7,managerial:7,farmer:8,homemaker:9,k-12student:10,lawyer:11,programmer:12,retired:13,sales:14,marketing:14,scientist:15,self-employed:16,technician:17,engineer:17,tradesman:18,craftsman:18,unemployed:19,writer:20");

    public String[] parseProperty(String inputProp, String delimiter){
        return getParsedProperty(propertyMapping, inputProp, delimiter);
    }

    @Override
    public void addProperty(ArrayList<String> propertyList, String property){
        if (propertyMapping.has(property) && !propertyList.contains(property)){
            propertyList.add(propertyMapping.get(property));
        }
        else if(!propertyList.contains("0")){
            System.out.println(property + " is invalid or already exist. Try using another occupation.");
            propertyList.add("0");
        }
    }
}
