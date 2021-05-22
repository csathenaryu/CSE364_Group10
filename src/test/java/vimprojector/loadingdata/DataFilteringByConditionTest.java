package vimprojector.loadingdata;

import org.junit.jupiter.api.Test;
import vimprojector.customdatastructure.Bitmap;
import vimprojector.customdatastructure.OneToMany;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DataFilteringByConditionTest {

    // test argument and prop
    String arg = "Fruit";
    String[] prop = {"apple", "banana", "melon", "strawberry"};
    OneToMany oneToMany = new OneToMany(arg, prop);
    OneToMany[] argList = {oneToMany};

    @Test
    void filterDataTest_TotalEquals_True() {

        // 모든 prop 를 다 포함하는 경우
        HashMap<String, String> data = new HashMap<>();
        data.put("ID", "1");
        data.put("Fruit", "apple|banana|melon|strawberry");

        ArrayList<HashMap<String, String>> hash = new ArrayList<>();
        hash.add(data);

        // Take Total Equal Strategy
        DataFilteringByCondition dataFilter = new DataFilteringByCondition(new TotalEqualStrategy());
        Bitmap result = dataFilter.filterData(argList, hash, "ID");
        for(int i=1; i<2; i++) {
            assertTrue(result.getAt(i));
        }
    }

    @Test
    void filterDataTest_TotalEquals_False() {

        // prop 가 누락된 경우
        HashMap<String, String> data = new HashMap<>();
        data.put("ID", "1");
        data.put("Fruit", "banana|melon|strawberry");
        // 다른 prop 가 추가된 경우
        HashMap<String, String> data2 = new HashMap<>();
        data2.put("ID", "2");
        data2.put("Fruit", "apple|banana|melon|strawberry|watermelon");
        // prop 가 누락되고 다른 prop 가 추가된 경우
        HashMap<String, String> data3 = new HashMap<>();
        data3.put("ID", "3");
        data3.put("Fruit", "apple|melon|strawberry|watermelon");

        ArrayList<HashMap<String, String>> hash = new ArrayList<>();
        hash.add(data);
        hash.add(data2);
        hash.add(data3);

        // Take Total Equal Strategy
        DataFilteringByCondition dataFilter = new DataFilteringByCondition(new TotalEqualStrategy());
        Bitmap result = dataFilter.filterData(argList, hash, "ID");
        for(int i=1; i<4; i++) {
            assertFalse(result.getAt(i));
        }
    }

    @Test
    void filterDataTest_ContainsAtLeastOne_True() {

        // prop 를 하나만 포함하는 경우
        HashMap<String, String> data1 = new HashMap<>();
        data1.put("ID", "1");
        data1.put("Fruit", "apple");
        // target prop 중 일부를 포함하는 경우
        HashMap<String, String> data2 = new HashMap<>();
        data2.put("ID", "2");
        data2.put("Fruit", "banana|strawberry");
        // 모든 prop 를 포함하는 경우
        HashMap<String, String> data3 = new HashMap<>();
        data3.put("ID", "3");
        data3.put("Fruit", "apple|banana|melon|strawberry");
        // 다른 prop 도 포함하는 경우
        HashMap<String, String> data4 = new HashMap<>();
        data4.put("ID", "4");
        data4.put("Fruit", "apple|banana|strawberry|watermelon");

        ArrayList<HashMap<String, String>> hash = new ArrayList<>();
        hash.add(data1);
        hash.add(data2);
        hash.add(data3);
        hash.add(data4);

        // Take Contains At Least One Strategy
        DataFilteringByCondition dataFilter = new DataFilteringByCondition(new ContainsAtLeastOneStrategy());
        Bitmap result = dataFilter.filterData(argList, hash, "ID");
        for(int i=1; i<5; i++) {
            assertTrue(result.getAt(i));
        }
    }

    @Test
    void filterDataTest_ContainsAtLeastOne_False() {

        // target prop 를 포함하지 않는 경우
        HashMap<String, String> data1 = new HashMap<>();
        data1.put("ID", "1");
        data1.put("Fruit", "watermelon");
        // target prop 를 포함하지 않는 경우 (여러개)
        HashMap<String, String> data2 = new HashMap<>();
        data2.put("ID", "2");
        data2.put("Fruit", "watermelon|tangerine");

        ArrayList<HashMap<String, String>> hash = new ArrayList<>();
        hash.add(data1);
        hash.add(data2);

        // Take Total Equal Strategy
        DataFilteringByCondition dataFilter = new DataFilteringByCondition(new ContainsAtLeastOneStrategy());
        Bitmap result = dataFilter.filterData(argList, hash, "ID");
        for(int i=1; i<3; i++) {
            assertFalse(result.getAt(i));
        }
    }

    @Test
    void filterDataTest_ContainsAll_True() {
        // prop 만 포함하는 경우
        HashMap<String, String> data1 = new HashMap<>();
        data1.put("ID", "1");
        data1.put("Fruit", "apple|banana|melon|strawberry");
        // target prop 와 다른 prop 를 포함하는 경우
        HashMap<String, String> data2 = new HashMap<>();
        data2.put("ID", "2");
        data2.put("Fruit", "apple|banana|melon|strawberry|watermelon");

        ArrayList<HashMap<String, String>> hash = new ArrayList<>();
        hash.add(data1);
        hash.add(data2);

        // Take Contains All Strategy
        DataFilteringByCondition dataFilter = new DataFilteringByCondition(new ContainsAllStrategy());
        Bitmap result = dataFilter.filterData(argList, hash, "ID");
        for(int i=1; i<3; i++) {
            assertTrue(result.getAt(i));
        }
    }

    @Test
    void filterDataTest_ContainsAll_False() {
        // 일부 prop 만 포함하는 경우
        HashMap<String, String> data1 = new HashMap<>();
        data1.put("ID", "1");
        data1.put("Fruit", "apple|banana|strawberry");
        // 일부 target prop 와 다른 prop 를 포함하는 경우
        HashMap<String, String> data2 = new HashMap<>();
        data2.put("ID", "2");
        data2.put("Fruit", "apple|banana|strawberry|watermelon");
        // target prop 를 포함하지 않는 경우 (여러개)
        HashMap<String, String> data3 = new HashMap<>();
        data3.put("ID", "3");
        data3.put("Fruit", "watermelon|tangerine");

        ArrayList<HashMap<String, String>> hash = new ArrayList<>();
        hash.add(data1);
        hash.add(data2);
        hash.add(data3);

        // Take Contains All Strategy
        DataFilteringByCondition dataFilter = new DataFilteringByCondition(new ContainsAllStrategy());
        Bitmap result = dataFilter.filterData(argList, hash, "ID");
        for(int i=1; i<4; i++) {
            assertFalse(result.getAt(i));
        }
    }
}