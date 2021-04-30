package main.java;

import vimprojector.customdatastructure.Bitmap;
import vimprojector.customdatastructure.OneToMany;
import vimprojector.loadingdata.DataFiltering;
import vimprojector.loadingdata.FilePreprocessing;
import vimprojector.parsinginputargs.*;
import vimprojector.recommender.RecommendedMovieInfo;
import vimprojector.recommender.TopRating;

import java.util.ArrayList;
import java.util.HashMap;

public class Milestone2 {

    public static void main(String[] args) {

        ArrayList<RecommendedMovieInfo> recommendedMovie = new ArrayList<>();
        String[] genderProperty;
        String[] ageProperty;
        String[] occupationProperty;
        String[] genresProperty;

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
        //args[0] = "m";
        //args[1] = "18";
        //args[2] = "11";
        //args[3] = "adventure|animation";
        // 3. Set Target Property and Filter User and Movie
        //String[] genderProperty = {"m"};
        //String[] ageProperty = {"18"};
        //String[] occupationProperty = {"11"};
        //String[] genresProperty = {"adventure", "animation"};


        while(true) {

            try{
                if (args[0].equals("")){
                    throw new Exception();
                }
                genderProperty = new ParsingGender().parseProperty(args[0], "\\|");
            } catch (Exception e){
                genderProperty = new ParsingGender().getAllProperty();
            }

            try{
                if (args[1].equals("")){
                    throw new Exception();
                }
                ageProperty = new ParsingAge().parseProperty(args[1], "\\|");
            } catch (Exception e){
                ageProperty = new ParsingAge().getAllProperty();
            }

            try{
                if (args[2].equals("")){
                    throw new Exception();
                }
                occupationProperty = new ParsingOccupation().parseProperty(args[2], "\\|");
            } catch (Exception e){
                occupationProperty = new ParsingOccupation().getAllProperty();
            }

            try{
                if (args[3].equals("")){
                    throw new Exception();
                }
                genresProperty = new ParsingGenres().parseProperty(args[3], "\\|");
            } catch (Exception e){
                genresProperty = new ParsingGenres().getAllProperty();
            }




            /* 늘리는 순위: occupation (제한을 풀어버림) > age > gender */
            OneToMany genderTargetProperty = new OneToMany("Gender", genderProperty);
            OneToMany ageTargetProperty = new OneToMany("Age", ageProperty);
            OneToMany occupationTargetProperty = new OneToMany("Occupation", occupationProperty);
            OneToMany genresTargetProperty = new OneToMany("Genres", genresProperty);

            OneToMany[] userFilteringCriteria = {genderTargetProperty, ageTargetProperty, occupationTargetProperty};
            OneToMany[] movieFilteringCriteria = {genresTargetProperty};

            Bitmap filteredUser = DataFiltering.filterData(userFilteringCriteria, userData, "UserID");
            Bitmap filteredMovie = DataFiltering.filterData(movieFilteringCriteria, movieData, "MovieID");


            // 4. extract top 10 movie
            TopRating topRating = new TopRating(ratingData, filteredMovie, filteredUser);
            ArrayList<RecommendedMovieInfo> newlyRecommendedMovie = topRating.getTopRating();

            if (recommendedMovie.isEmpty()){
                recommendedMovie = newlyRecommendedMovie;
            }
            else {
                for (RecommendedMovieInfo recommendedMovieInfo : newlyRecommendedMovie) {
                    if (recommendedMovie.size() < 10 && !recommendedMovie.contains(recommendedMovieInfo)) {
                        recommendedMovie.add(recommendedMovieInfo);
                        continue;
                    }
                    break;
                }
            }

            if (recommendedMovie.size() == 10)
                break;

            if (!args[2].equals("")){
                args[2] = "";
                continue;
            }

            if (!args[1].equals("")){
                args[1] = "";
                continue;
            }

            if (!args[0].equals("")){
                args[0] = "";
            }
            else
                break;

        }



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
