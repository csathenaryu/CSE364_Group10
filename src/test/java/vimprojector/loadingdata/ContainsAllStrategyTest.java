package vimprojector.loadingdata;

import org.junit.jupiter.api.Test;
import vimprojector.customdatastructure.OneToMany;

import static org.junit.jupiter.api.Assertions.*;

public class ContainsAllStrategyTest {

    @Test
    public void ContainsAllStrategyTest1(){

        ContainsAllStrategy t = new ContainsAllStrategy();
        String[] a = {"a", "b", "c"};
        String[] inputData = {"a", "b"};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertFalse(t.filter(oneToMany, inputData));
    }


    @Test
    public void ContainsAllStrategyTest2(){

        ContainsAllStrategy t = new ContainsAllStrategy();
        String[] a = {"a", "b"};
        String[] inputData = {"a", "b", "c"};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertTrue(t.filter(oneToMany, inputData));
    }


    @Test
    public void ContainsAllStrategyTest3(){

        ContainsAllStrategy t = new ContainsAllStrategy();
        String[] a = {};
        String[] inputData = {};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertTrue(t.filter(oneToMany, inputData));
    }
}