package xxx.yyy;

import org.junit.Test;

import static xxx.yyy.App.path_of_movies;

class testFunction{
    @Test
    public void test(){
        String[] inputvalue = {"Horror", "Comedy"};
        Function func = new Function();
        CustomList a = func.makeTargetTable(path_of_movies, inputvalue, 2);
        org.junit.Assert.assertEquals("Results", false, a.dataList.get(13));
        System.out.println(a.dataList);
    }

}