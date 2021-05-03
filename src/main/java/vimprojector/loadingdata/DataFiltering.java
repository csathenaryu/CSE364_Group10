package vimprojector.loadingdata;

import vimprojector.customdatastructure.Bitmap;
import vimprojector.customdatastructure.OneToMany;

import java.util.ArrayList;
import java.util.HashMap;


public class DataFiltering {

    public static Boolean containsTargetProperty(OneToMany oneToMany, HashMap<String, String> data){
        String argument = oneToMany.getArgument();
        String dataProperty = data.get(argument).toLowerCase();
        return oneToMany.containsAtLeastOne(dataProperty.split("\\|"));
    }

    public static Bitmap filterData(OneToMany[] oneToMany, ArrayList<HashMap<String, String>> loadedData, String keyArgument){
        Bitmap markedData = new Bitmap();
        // 예컨대, argument = "Occupation", "Gender"
        for (HashMap<String, String> data : loadedData) {
            boolean containsAll = true;
            for (OneToMany targetProp : oneToMany) {
                if (!containsTargetProperty(targetProp, data)) {
                    containsAll = false;
                    break;
                }
            }
            int key = Integer.parseInt(data.get(keyArgument));
            markedData.push(key, containsAll);
        }
        return markedData;
    }
}
