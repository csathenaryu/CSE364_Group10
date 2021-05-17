package vimprojector.parsinginputargs;

import vimprojector.customdatastructure.Pair;

import java.util.ArrayList;

public class ParsingGenres extends ParsingInputArgument{
    static Pair propertyMapping = new Pair(
            "action:action,adventure:adventure,animation:animation,children's:children's,comedy:comedy,crime:crime,documentary:documentary,drama:drama,fantasy:fantasy,film-noir:film-noir,horror:horror,musical:musical,mystery:mystery,romance:romance,sci-fi:sci-fi,thriller:thriller,war:war,western:western");

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
            else{
                // pass
            }
        }
        else {
            System.out.printf("Invalid genres '%s' will be ignored\n", property);
        }
    }
}
