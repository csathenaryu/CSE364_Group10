package vimprojector.loadingdata;

import vimprojector.customdatastructure.OneToMany;

public class TotalEqualStrategy implements FilteringStrategy{
    @Override
    public Boolean filter(OneToMany oneToMany, String[] inputProperty) {
        return oneToMany.containsAll(inputProperty) && (oneToMany.getPropertyLength() == inputProperty.length);
    }
}
