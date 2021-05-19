package vimprojector.loadingdata;

import vimprojector.customdatastructure.OneToMany;

public interface FilteringStrategy {
    Boolean filter(OneToMany oneToMany, String[] inputProperty);
}
