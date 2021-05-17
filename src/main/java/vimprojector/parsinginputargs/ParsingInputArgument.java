package vimprojector.parsinginputargs;

import vimprojector.customdatastructure.Pair;

import java.util.ArrayList;


public class ParsingInputArgument {

    public String[] getParsedProperty(Pair propertyMapping, String inputProperty, String delimiter){

        String[] parsedProperty = ArgumentParser.parseByDelimiter(inputProperty, delimiter);

        ArrayList<String> propertyList = new ArrayList<>();
        for (String property : parsedProperty){
            addProperty(propertyList, property.toLowerCase());
        }

        if (propertyList.size() == 0) {
            propertyList = propertyMapping.getAll();
            /*System.out.println("There is no valid input. Terminate program.");
            System.exit(0);*/
        }

        return propertyList.toArray(new String[0]);
    }

    // Override is necessary
    public void addProperty(ArrayList<String> propertyList, String property){
        propertyList.add(property);
    }
}

