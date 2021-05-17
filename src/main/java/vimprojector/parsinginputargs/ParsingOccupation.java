package vimprojector.parsinginputargs;

import vimprojector.customdatastructure.Pair;

import java.util.ArrayList;

public class ParsingOccupation extends ParsingInputArgument{
    static Pair propertyMapping = new Pair(
            "academic:1,educator:1,artist:2,clerical:3,admin:3,collegestudent:4,gradstudent:4,customerservice:5,doctor:6,healthcare:6,executive:7,managerial:7,farmer:8,homemaker:9,k-12student:10,lawyer:11,programmer:12,retired:13,sales:14,marketing:14,scientist:15,self-employed:16,technician:17,engineer:17,tradesman:18,craftsman:18,unemployed:19,writer:20");

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
            if(propertyList.contains(mapping)){
                // pass
            }
            else{
                propertyList.add(propertyMapping.get(property));
            }
        }
        else{
            if(propertyList.contains("0")){
                System.out.printf("Invalid occupation '%s' will be categorized as 'Other'.\n", property);
                // pass
            }
            else{
                propertyList.add("0");
                System.out.printf("Invalid occupation '%s' will be categorized as 'Other'.\n", property);
            }
        }
    }
}
