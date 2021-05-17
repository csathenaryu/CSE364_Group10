package vimprojector.customdatastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Pair {
    HashMap<String, String> pairs = new HashMap<>();

    public Pair(String pairString){
        String[] mappingList = pairString.split(",");
        for (String mapping : mappingList){
            String[] keyValue = mapping.split(":");
            try{
                pairs.put(keyValue[0], keyValue[1]);
            } catch(Exception e){

            }
        }
    }

    public boolean has(String key){
        return pairs.containsKey(key);
    }

    public String get(String key){
        return pairs.get(key);
    }

    public ArrayList<String> getAll(){
        Set<String> keys = pairs.keySet();
        ArrayList<String> allKeys = new ArrayList<>();
        keys.forEach(key -> {
            allKeys.add(pairs.get(key));
        });
        return allKeys;
    }
}
