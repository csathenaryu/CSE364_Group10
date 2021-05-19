package vimprojector.loadingdata;

import org.springframework.boot.test.context.SpringBootTest;
import vimprojector.customdatastructure.OneToMany;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContainsAllStrategyTests {

    @Test
    public void ContainsAllStrategyTest1() {
        ContainsAllStrategy T = new ContainsAllStrategy();
        String[] a = {"hoho", "haha", "hihi"};
        OneToMany oneToMany = new OneToMany("Genre", a);
        String[] inputProperty = {"hoho", "hihi", "haha"};
        assertTrue(T.filter(oneToMany, inputProperty));
    }
}