package main.java;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;


import vimprojector.customdatastructure.*;
import vimprojector.loadingdata.*;
import vimprojector.recommender.*;


public class RatingTest {

    public static void main(String[] args) {


        // Data Label
        // user: UserID::Gender::Age::Occupation::Zip-code
        String[] userLabel = {"UserID", "Gender", "Age", "Occupation", "Zip-code"};
        // movie: MovieID::Title::Genres
        String[] movieLabel = {"MovieID", "Title", "Genres"};
        // rating: UserID::MovieID::Rating::Timestamp
        String[] ratingLabel = {"UserID", "MovieID", "Rating", "Timestamp"};
        // link: MovieID::imdbID
        String[] linkLabel = {"MovieID", "imdbID"};


        // Load Data
        String charset = "ISO-8859-15";
        ArrayList<HashMap<String, String>> userData = FilePreprocessing.loadDataFrom("data/users.dat", userLabel, charset);
        ArrayList<HashMap<String, String>> movieData = FilePreprocessing.loadDataFrom("data/movies.dat", movieLabel, charset);
        ArrayList<HashMap<String, String>> ratingData = FilePreprocessing.loadDataFrom("data/ratings.dat", ratingLabel, charset);
        HashMap<Integer, String> linkHash = FilePreprocessing.loadHashFrom("data/links.dat", linkLabel, "MovieID", "imdbID", charset);
        HashMap<Integer, String> movieHash = FilePreprocessing.loadHashFrom("data/movies.dat", movieLabel, "MovieID", "Title", charset);



        // Set Target Property and Filter User and Movie
        String[] occupationProperty = {"15"};
        OneToMany occupationTargetProperty = new OneToMany("Occupation", occupationProperty);
        String[] genresProperty = {"action"};
        OneToMany genresTargetProperty = new OneToMany("Genres", genresProperty);

        OneToMany[] userFilteringCriteria = {occupationTargetProperty};
        OneToMany[] movieFilteringCriteria = {genresTargetProperty};

        Bitmap filteredUser = DataFiltering.filterData(userFilteringCriteria, userData, "UserID");
        Bitmap filteredMovie = DataFiltering.filterData(movieFilteringCriteria, movieData, "MovieID");
        filteredUser.print();
        filteredMovie.print();


        // extract top 10 movie
        TopRating topRating = new TopRating(ratingData, filteredMovie, filteredUser);
        ArrayList<RecommendedMovieInfo> recommendedMovie = topRating.getTopRating();
        for (RecommendedMovieInfo recommendedMovieInfo: recommendedMovie){
            int movieId = recommendedMovieInfo.id;
            float movieRating = recommendedMovieInfo.rating;
            String movieTitle = movieHash.get(movieId);
            String imdbId = linkHash.get(movieId);
            System.out.println("["+ movieId + "] " + movieTitle + " (http://www.imdb.com/title/tt" + imdbId + ")   [RATING] " + movieRating);
        }
    }
}