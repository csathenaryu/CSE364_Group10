package main.java;

import vimprojector.customdatastructure.Bitmap;
import vimprojector.customdatastructure.OneToMany;
import vimprojector.loadingdata.DataFiltering;
import vimprojector.loadingdata.FilePreprocessing;
import vimprojector.recommender.RecommendedMovieInfo;
import vimprojector.recommender.TopRating;

import java.util.ArrayList;
import java.util.HashMap;

public class Milestone2 {

    public static void main(String[] args) {

        // 1. Data Label
        // user: UserID::Gender::Age::Occupation::Zip-code
        String[] userLabel = {"UserID", "Gender", "Age", "Occupation", "Zip-code"};
        // movie: MovieID::Title::Genres
        String[] movieLabel = {"MovieID", "Title", "Genres"};
        // rating: UserID::MovieID::Rating::Timestamp
        String[] ratingLabel = {"UserID", "MovieID", "Rating", "Timestamp"};
        // link: MovieID::imdbID
        String[] linkLabel = {"MovieID", "imdbID"};


        // 2. Load Data
        String charset = "ISO-8859-15";
        ArrayList<HashMap<String, String>> userData = FilePreprocessing.loadDataFrom("data/users.dat", userLabel, charset);
        ArrayList<HashMap<String, String>> movieData = FilePreprocessing.loadDataFrom("data/movies.dat", movieLabel, charset);
        ArrayList<HashMap<String, String>> ratingData = FilePreprocessing.loadDataFrom("data/ratings.dat", ratingLabel, charset);
        HashMap<Integer, String> linkHash = FilePreprocessing.loadHashFrom("data/links.dat", linkLabel, "MovieID", "imdbID", charset);
        HashMap<Integer, String> movieHash = FilePreprocessing.loadHashFrom("data/movies.dat", movieLabel, "MovieID", "Title", charset);




        /* Implement code here (Revise 3. and 4.)
            > 'xxProperty' should be implemented. Using 'parsingInputArgs' package to get and parse 'args'.
            > 'topRating' must have 10 entries after this part.
            > When using package, please be careful to import it. */

        // 3. Set Target Property and Filter User and Movie
        String[] genderProperty = {"m"};
        OneToMany genderTargetProperty = new OneToMany("Gender", genderProperty);
        String[] ageProperty = {"25"};
        OneToMany ageTargetProperty = new OneToMany("Age", ageProperty);
        String[] occupationProperty = {"15"};
        OneToMany occupationTargetProperty = new OneToMany("Occupation", occupationProperty);
        String[] genresProperty = {"action"};
        OneToMany genresTargetProperty = new OneToMany("Genres", genresProperty);

        OneToMany[] userFilteringCriteria = {genderTargetProperty, ageTargetProperty, occupationTargetProperty};
        OneToMany[] movieFilteringCriteria = {genresTargetProperty};

        Bitmap filteredUser = DataFiltering.filterData(userFilteringCriteria, userData, "UserID");
        Bitmap filteredMovie = DataFiltering.filterData(movieFilteringCriteria, movieData, "MovieID");


        // 4. extract top 10 movie
        TopRating topRating = new TopRating(ratingData, filteredMovie, filteredUser);
        ArrayList<RecommendedMovieInfo> recommendedMovie = topRating.getTopRating();




        // 5. Print top rating movie information
        for (RecommendedMovieInfo recommendedMovieInfo: recommendedMovie){
            int movieId = recommendedMovieInfo.id;
            float movieRating = recommendedMovieInfo.rating;
            String movieTitle = movieHash.get(movieId);
            String imdbId = linkHash.get(movieId);
            System.out.printf("[RATING] %.2f  ", movieRating);
            System.out.printf("[%4d] ", movieId);
            System.out.print(movieTitle + " ");
            System.out.println("(http://www.imdb.com/title/tt" + imdbId + ") ");
        }
    }
}
