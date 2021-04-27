package vimprojector.parsinginputargs;

import vimprojector.customdatastructure.Pair;
import java.util.ArrayList;

public class ParsingGenres extends ParsingInputArgument{
    static Pair propertyMapping = new Pair(
            "action:action,animation:animation,children's:children's,comedy:comedy,crime:crime,documentary:documentary,drama:drama,fantasy:fantasy,film-noir:film-noir,horror:horror,musical:musical,mystery:mystery,romance:romance,scifi:scifi,thriller:thriller,war:war,western:western");

    public String[] parseProperty(String inputProp, String delimiter){
        return getParsedProperty(propertyMapping, inputProp, delimiter);
    }

    public String[] getAllProperty(){
        return propertyMapping.getAll().toArray(new String[0]);
    }

    @Override
    public void addProperty(ArrayList<String> propertyList, String property){
        if (propertyMapping.has(property) && (!propertyList.contains(property))){
            propertyList.add(propertyMapping.get(property));
        }
        else {
            System.out.println(property + " is invalid or already exist. Try using another genres.");
        }
    }
}
