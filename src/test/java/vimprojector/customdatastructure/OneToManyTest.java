package vimprojector.customdatastructure;

import org.junit.Test;

import static org.junit.Assert.*;

public class OneToManyTest {
    String argument = "a";
    String[] property = {"a", "b", "c", "d", "e"};

    @Test
    public void getArgumentTest_ArgumentA_a(){
        OneToMany oneToMany = new OneToMany(argument, property);
        assertEquals("a", oneToMany.getArgument());
    }

    @Test
    public void containsAll_Subset_true() {
        OneToMany oneToMany = new OneToMany(argument, property);
        String[] inputProperty = {"a", "b"};
        assertEquals(true, oneToMany.containsAll(inputProperty));
    }

    @Test
    public void containsAll_NotIncluded_false() {
        OneToMany oneToMany = new OneToMany(argument, property);
        String[] inputProperty = {"a", "b", "f"};
        assertEquals(false, oneToMany.containsAll(inputProperty));
    }

    @Test
    public void containsAtLeastOne_Subset_true() {
        OneToMany oneToMany = new OneToMany(argument, property);
        String[] inputProperty = {"a", "b"};
        assertEquals(true, oneToMany.containsAtLeastOne(inputProperty));
    }

    @Test
    public void containsAtLeastOne_NotIncluded_true() {
        OneToMany oneToMany = new OneToMany(argument, property);
        String[] inputProperty = {"a", "b", "f"};
        assertEquals(true, oneToMany.containsAtLeastOne(inputProperty));
    }

    @Test
    public void containsAtLeastOne_NotIncluded_false() {
        OneToMany oneToMany = new OneToMany(argument, property);
        String[] inputProperty = {"f", "g"};
        assertEquals(false, oneToMany.containsAtLeastOne(inputProperty));
    }
}