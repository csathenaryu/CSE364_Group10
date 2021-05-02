package vimprojector.recommender;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import vimprojector.customdatastructure.Bitmap;
import vimprojector.customdatastructure.OneToMany;
import vimprojector.loadingdata.DataFiltering;
import vimprojector.loadingdata.FilePreprocessing;

import java.util.ArrayList;
import java.util.HashMap;

public class TopRatingTest {

    String[] userLabel = {"UserID", "Gender", "Age", "Occupation", "Zip-code"};
    String[] movieLabel = {"MovieID", "Title", "Genres"};
    String[] ratingLabel = {"UserID", "MovieID", "Rating", "Timestamp"};
    String[] linkLabel = {"MovieID", "imdbID"};

    String charset = "ISO-8859-15";
    ArrayList<HashMap<String, String>> userData = FilePreprocessing.loadDataFrom("data/users.dat", userLabel, charset);
    ArrayList<HashMap<String, String>> movieData = FilePreprocessing.loadDataFrom("data/movies.dat", movieLabel, charset);
    ArrayList<HashMap<String, String>> ratingData = FilePreprocessing.loadDataFrom("data/ratings.dat", ratingLabel, charset);


    Bitmap setUserInput(String[] genderProperty, String[] ageProperty, String[] occupationProperty) {


        OneToMany genderTargetProperty = new OneToMany("Gender", genderProperty);
        OneToMany ageTargetProperty = new OneToMany("Age", ageProperty);
        OneToMany occupationTargetProperty = new OneToMany("Occupation", occupationProperty);

        OneToMany[] userFilteringCriteria = {genderTargetProperty, ageTargetProperty, occupationTargetProperty};

        return DataFiltering.filterData(userFilteringCriteria, userData, "UserID");
    }

    Bitmap setMovieInput(String[] genresProperty) {


        OneToMany genresTargetProperty = new OneToMany("Genres", genresProperty);

        OneToMany[] movieFilteringCriteria = {genresTargetProperty};

        return DataFiltering.filterData(movieFilteringCriteria, movieData, "MovieID");
    }

    void Is_sorted(ArrayList<RecommendedMovieInfo> result) {
        boolean is_sorted_rating;
        for (int i = 0; i < result.size()-1; i++) {
            is_sorted_rating = (result.get(i).rating >= result.get(i+1).rating);
            Assert.assertTrue(is_sorted_rating);
        }
        boolean is_sorted_count;
        for (int i = 0; i < result.size()-1; i++) {
            if (result.get(i).rating == result.get(i+1).rating) {
                is_sorted_count =(result.get(i).count >= result.get(i+1).count);
                Assert.assertTrue(is_sorted_count);
            }
        }

    }

    @Test
    public void getTopRating_normal() {


        // Normal case
        Bitmap filteredUser = setUserInput(new String[]{"m"}, new String[]{"25"}, new String[]{"15"});
        Bitmap filteredMovie = setMovieInput(new String[]{"action"});


        // System.out.println(filteredUser.getAt(9));

        TopRating topRating = new TopRating(ratingData, filteredMovie, filteredUser);
        ArrayList<RecommendedMovieInfo> result = topRating.getTopRating();
        Assert.assertEquals(topRating.getTopRating().get(0).rating, 5, 0.001);
        Assert.assertTrue(result.get(0).rating >= result.get(1).rating);
        Is_sorted(result);

    }

    @Test
    public void getTopRating_wrong() {
        // Wrong argument parsing
        Bitmap filteredUser = setUserInput(new String[]{"m"}, new String[]{"25"}, new String[]{"15"});
        Bitmap filteredMovie = setMovieInput(new String[]{""});

        TopRating topRating = new TopRating(ratingData, filteredMovie, filteredUser);
        ArrayList<RecommendedMovieInfo> result = topRating.getTopRating();
        ArrayList<RecommendedMovieInfo> result_empty = new ArrayList<>();
        Assert.assertEquals(result, result_empty);

        Is_sorted(result);
    }

    @Test
    public void getTopRating_poor_data_1() {
        // Under 18 lawyer
        Bitmap filteredUser = setUserInput(new String[]{"m"}, new String[]{"1"}, new String[]{"11"});
        Bitmap filteredMovie = setMovieInput(new String[]{"horror"});

        TopRating topRating = new TopRating(ratingData, filteredMovie, filteredUser);
        ArrayList<RecommendedMovieInfo> result = topRating.getTopRating();
        Assert.assertTrue(result.size() < 10);
        Is_sorted(result);
    }


    @Test
    public void getTopRating_poor_data_2() {
        // Under 18 retired
        Bitmap filteredUser = setUserInput(new String[]{"m"}, new String[]{"1"}, new String[]{"13"});
        Bitmap filteredMovie = setMovieInput(new String[]{"horror"});

        TopRating topRating = new TopRating(ratingData, filteredMovie, filteredUser);
        ArrayList<RecommendedMovieInfo> result = topRating.getTopRating();
        Assert.assertTrue(result.size() < 10);
        Is_sorted(result);
    }

    @Test
    public void getTopRating_low_score() {
        // 56+ K-12 student
        Bitmap filteredUser = setUserInput(new String[]{"m"}, new String[]{"56"}, new String[]{"10"});
        Bitmap filteredMovie = setMovieInput(new String[]{"horror"});

        TopRating topRating = new TopRating(ratingData, filteredMovie, filteredUser);
        ArrayList<RecommendedMovieInfo> result = topRating.getTopRating();
        Assert.assertTrue(result.size() < 10);
        Is_sorted(result);
    }


}

