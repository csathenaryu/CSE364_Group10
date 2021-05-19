package vimprojector.loadingdata;

import vimprojector.customdatastructure.OneToMany;

public class ContainsAtLeastOneStrategy implements FilteringStrategy{
    @Override
    public Boolean filter(OneToMany oneToMany, String[] inputProperty) {
        return oneToMany.containsAtLeastOne(inputProperty);
    }
}
