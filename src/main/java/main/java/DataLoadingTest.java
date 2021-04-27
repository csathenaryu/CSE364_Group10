package main.java;

import vimprojector.customdatastructure.*;
import vimprojector.loadingdata.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DataLoadingTest {
    public static void main(String[] args) {
        // Data Label
        // rating: UserID::MovieID::Rating::Timestamp
        String[] ratingLabel = {"UserID", "MovieID", "Rating", "Timestamp"};
        // user: UserID::Gender::Age::Occupation::Zip-code
        String[] userLabel = {"UserID", "Gender", "Age", "Occupation", "Zip-code"};
        // movie: MovieID::Title::Genres
        String[] movieLabel = {"MovieID", "Title", "Genres"};

        // Target Property
        String[] genderProperty = {"M"};
        OneToMany gender = new OneToMany("Gender", genderProperty);
        String[] occupationProperty = {"1", "2"};
        OneToMany occupation = new OneToMany("Occupation", occupationProperty);
        String[] genresProperty = {"Drama", "Animation"};
        OneToMany genres = new OneToMany("Genres", genresProperty);

        OneToMany[] user = {gender, occupation};
        OneToMany[] movie = {genres};

        String charset = "ISO-8859-15";
        ArrayList<HashMap<String, String>> movieData = FilePreprocessing.loadDataFrom("data/movies.dat", movieLabel, charset);
        Bitmap filteredMovieData = DataFiltering.filterData(movie, movieData, "MovieID");
        ArrayList<HashMap<String, String>> userData = FilePreprocessing.loadDataFrom("data/users.dat", userLabel, charset);
        Bitmap filteredUserData = DataFiltering.filterData(user, userData, "UserID");
        filteredUserData.print();
        filteredMovieData.print();
    }
}
