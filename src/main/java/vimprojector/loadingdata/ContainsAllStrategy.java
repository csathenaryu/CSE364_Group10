package vimprojector.loadingdata;

import vimprojector.customdatastructure.OneToMany;

public class ContainsAllStrategy implements FilteringStrategy{
    @Override
    public Boolean filter(OneToMany oneToMany, String[] inputProperty) {
        return oneToMany.containsAll(inputProperty);
    }
}
