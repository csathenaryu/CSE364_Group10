package vimprojector.loadingdata;

import org.junit.jupiter.api.Test;
import vimprojector.customdatastructure.Bitmap;
import vimprojector.customdatastructure.OneToMany;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static vimprojector.loadingdata.DataFiltering.containsTargetProperty;
import static vimprojector.loadingdata.FilePreprocessing.makeHash;

public class DataFilteringTest {

    String charset = "ISO-8859-15";
    String[] userLabel = {"UserID", "Gender", "Age", "Occupation", "Zip-code"};
    String[] movieLabel = {"MovieID", "Title", "Genres"};
    String[] ratingLabel = {"UserID", "MovieID", "Rating", "Timestamp"};
    ArrayList<HashMap<String, String>> userData = FilePreprocessing.loadDataFrom("data/users.dat", userLabel, charset);
    ArrayList<HashMap<String, String>> movieData = FilePreprocessing.loadDataFrom("data/movies.dat", movieLabel, charset);
    ArrayList<HashMap<String, String>> ratingData = FilePreprocessing.loadDataFrom("data/ratings.dat", ratingLabel, charset);

    @Test
    public void containsTargetPropertyTEST(){
        String[] occupationProperty = {"1", "2", "3"};
        OneToMany occupationTargetProperty = new OneToMany("Occupation", occupationProperty);
        HashMap<String, String> data = new HashMap<String, String>(){{put("Occupation", "4");}};
        assertFalse(containsTargetProperty(occupationTargetProperty, data));

    }



    @Test
    public void filterDataTEST1() {
        String[] genderProperty = {"f"};
        String[] ageProperty = {"1"};
        String[] occupationProperty = {"0"};
        OneToMany genderTargetProperty = new OneToMany("Gender", genderProperty);
        OneToMany ageTargetProperty = new OneToMany("Age", ageProperty);
        OneToMany occupationTargetProperty = new OneToMany("Occupation", occupationProperty);
        OneToMany[] userFilteringCriteria = {genderTargetProperty, ageTargetProperty, occupationTargetProperty};
        Bitmap a = new Bitmap();
        Bitmap b = DataFiltering.filterData(userFilteringCriteria, userData, "UserID");
        assertNotEquals(a,b);
    }


    @Test
    public void filterDataTEST2() {
        String[] genderProperty = {"m"};
        String[] ageProperty = {"56"};
        String[] occupationProperty = {"13"};
        OneToMany genderTargetProperty = new OneToMany("Gender", genderProperty);
        OneToMany ageTargetProperty = new OneToMany("Age", ageProperty);
        OneToMany occupationTargetProperty = new OneToMany("Occupation", occupationProperty);
        OneToMany[] userFilteringCriteria = {genderTargetProperty, ageTargetProperty, occupationTargetProperty};
        Bitmap b = DataFiltering.filterData(userFilteringCriteria, userData, "UserID");
        assertFalse(b.getAt(0));
        assertTrue(b.getAt(90));     //male, 56, 13
        assertTrue(b.getAt(316));    //male, 56, 13
        assertTrue(b.getAt(451));    //male, 56, 13
    }

    @Test
    public void filterDataTEST3() {
        String[] genderProperty = {"f"};
        String[] ageProperty = {"18"};
        String[] occupationProperty = {"0"};
        OneToMany genderTargetProperty = new OneToMany("Gender", genderProperty);
        OneToMany ageTargetProperty = new OneToMany("Age", ageProperty);
        OneToMany occupationTargetProperty = new OneToMany("Occupation", occupationProperty);
        OneToMany[] userFilteringCriteria = {genderTargetProperty, ageTargetProperty, occupationTargetProperty};
        Bitmap b = DataFiltering.filterData(userFilteringCriteria, userData, "UserID");
        assertFalse(b.getAt(0));
        assertTrue(b.getAt(34));       //female, 18, 0
        assertTrue(b.getAt(666));      //female, 18, 0
        assertTrue(b.getAt(378));      //female, 18, 0
    }

    @Test
    public void filterDataTEST4() {
        String[] genderProperty = {"f", "m"};
        String[] ageProperty = {"18", "56"};
        String[] occupationProperty = {"1", "2", "3"};
        OneToMany genderTargetProperty = new OneToMany("Gender", genderProperty);
        OneToMany ageTargetProperty = new OneToMany("Age", ageProperty);
        OneToMany occupationTargetProperty = new OneToMany("Occupation", occupationProperty);
        OneToMany[] userFilteringCriteria = {genderTargetProperty, ageTargetProperty, occupationTargetProperty};
        Bitmap b = DataFiltering.filterData(userFilteringCriteria, userData, "UserID");


        assertFalse(b.getAt(0));
        assertTrue(b.getAt(64));     //male, 18, 1
        assertTrue(b.getAt(18));    //female, 18, 3
        assertFalse(b.getAt(356));    // male, 56, 20
        assertTrue(b.getAt(759));      //female, 56, 3
    }

}