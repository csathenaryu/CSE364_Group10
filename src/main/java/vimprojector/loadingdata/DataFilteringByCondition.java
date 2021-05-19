package vimprojector.loadingdata;

import vimprojector.customdatastructure.Bitmap;
import vimprojector.customdatastructure.OneToMany;

import java.util.ArrayList;
import java.util.HashMap;


public class DataFilteringByCondition {

    private FilteringStrategy filteringStrategy;

    public void setFilteringStrategy(FilteringStrategy filteringStrategy){
        this.filteringStrategy = filteringStrategy;
    }

    public Boolean filter(OneToMany oneToMany, String[] inputProperty){
        return filteringStrategy.filter(oneToMany, inputProperty);
    }

    public Boolean containsTargetProperty(OneToMany oneToMany, HashMap<String, String> data){
        String argument = oneToMany.getArgument();
        String dataProperty = data.get(argument).toLowerCase();
        return filter(oneToMany, dataProperty.split("\\|"));
    }

    public Bitmap filterData(OneToMany[] oneToMany, ArrayList<HashMap<String, String>> loadedData, String keyArgument, FilteringStrategy filteringStrategy){
        setFilteringStrategy(filteringStrategy);
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
