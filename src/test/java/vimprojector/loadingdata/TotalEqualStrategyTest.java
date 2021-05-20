package vimprojector.loadingdata;

import org.junit.Test;
import vimprojector.customdatastructure.OneToMany;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TotalEqualStrategyTest {
    @Test
    public void TotalEqualStrategyTest1(){

        TotalEqualStrategy t = new TotalEqualStrategy();
        String[] a = {"a", "b"};
        String[] inputData = {"a", "b"};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertTrue(t.filter(oneToMany, inputData));
    }

    @Test
    public void TotalEqualStrategyTest2(){

        TotalEqualStrategy t = new TotalEqualStrategy();
        String[] a = {"a", "b"};
        String[] inputData = {"a", "b", "c"};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertFalse(t.filter(oneToMany, inputData));
    }
    @Test
    public void TotalEqualStrategyTest3(){

        TotalEqualStrategy t = new TotalEqualStrategy();
        String[] a = {"a", "b"};
        String[] inputData = {"a", "c"};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertFalse(t.filter(oneToMany, inputData));
    }

    @Test
    public void TotalEqualStrategyTest4(){

        TotalEqualStrategy t = new TotalEqualStrategy();
        String[] a = {};
        String[] inputData = {"a"};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertFalse(t.filter(oneToMany, inputData));
    }

    @Test
    public void TotalEqualStrategyTest5(){

        TotalEqualStrategy t = new TotalEqualStrategy();
        String[] a = {};
        String[] inputData = {};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertTrue(t.filter(oneToMany, inputData));
    }

    @Test
    public void TotalEqualStrategyTest6(){

        TotalEqualStrategy t = new TotalEqualStrategy();
        String[] a = {"a"};
        String[] inputData = {};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertFalse(t.filter(oneToMany, inputData));
    }
}