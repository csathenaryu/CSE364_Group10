package vimprojector.loadingdata;

import org.junit.jupiter.api.Test;
import vimprojector.customdatastructure.OneToMany;

import static org.junit.jupiter.api.Assertions.*;

public class ContainsAtLeastOneStrategyTest {
    @Test
    public void ContainsAtLeastOneStrategyTest1(){

        ContainsAtLeastOneStrategy t = new ContainsAtLeastOneStrategy();
        String[] a = {"a", "c"};
        String[] inputData = {"a", "b"};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertTrue(t.filter(oneToMany, inputData));
    }
    @Test
    public void ContainsAtLeastOneStrategyTest2(){

        ContainsAtLeastOneStrategy t = new ContainsAtLeastOneStrategy();
        String[] a = {"c", "d"};
        String[] inputData = {"a", "b"};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertFalse(t.filter(oneToMany, inputData));
    }

    @Test
    public void ContainsAtLeastOneStrategyTest3(){

        ContainsAtLeastOneStrategy t = new ContainsAtLeastOneStrategy();
        String[] a = {"c"};
        String[] inputData = {"a", "c"};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertTrue(t.filter(oneToMany, inputData));
    }

    @Test
    public void ContainsAtLeastOneStrategyTest4(){

        ContainsAtLeastOneStrategy t = new ContainsAtLeastOneStrategy();
        String[] a = {"c", "d", "a"};
        String[] inputData = {"a", "b"};

        OneToMany oneToMany = new OneToMany("Genre", a);
        assertTrue(t.filter(oneToMany, inputData));
    }
}