package group10.CSE364project;

//import org.springframework.beans.factory.annotation.Autowired;
import vimprojector.customdatastructure.Bitmap;
import vimprojector.customdatastructure.OneToMany;
import vimprojector.loadingdata.DataFiltering;
import vimprojector.loadingdata.FilePreprocessing;
import vimprojector.parsinginputargs.ParsingAge;
import vimprojector.parsinginputargs.ParsingGender;
import vimprojector.parsinginputargs.ParsingGenres;
import vimprojector.parsinginputargs.ParsingOccupation;
import vimprojector.recommender.RecommendedMovieInfo;
import vimprojector.recommender.TopRating;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
//import java.util.List;

public class Milestone2 {

    //@Autowired
    //MoviedataRepository repository;

    public MoviedataRepository milestone2_loadRepository(String[] args, MoviedataRepository repository) {

        ArrayList<MovieData> movieDataArrayList = milestone2(args, "data/ratings.dat");

        repository.saveAll(movieDataArrayList);

        return repository;
    }

    public ArrayList<MovieData> milestone2(String[] args, String rating_file) {

        ArrayList<MovieData> movieDataArrayList = new ArrayList<>();
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
        ArrayList<HashMap<String, String>> ratingData = FilePreprocessing.loadDataFrom(rating_file, ratingLabel, charset);
        HashMap<Integer, String> linkHash = FilePreprocessing.intStringloadHashFrom("data/links.dat", linkLabel, "MovieID", "imdbID", charset);
        HashMap<Integer, String> movieHash = FilePreprocessing.intStringloadHashFrom("data/movies.dat", movieLabel, "MovieID", "Title", charset);
        HashMap<Integer, String> genresHash = FilePreprocessing.intStringloadHashFrom("data/movies.dat", movieLabel, "MovieID", "Genres", charset);



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

        int step = 1;

        while(true) {

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
            TopRating topRating = new TopRating(ratingData, filteredMovie, filteredUser, 10);
            ArrayList<RecommendedMovieInfo> newlyRecommendedMovie = topRating.getTopRating();
            // System.out.println(newlyRecommendedMovie.get(1).rating);

            if (recommendedMovie.isEmpty()){
                recommendedMovie = newlyRecommendedMovie;
            }
            else {
                for (RecommendedMovieInfo recommendedMovieInfo : newlyRecommendedMovie) {
                    if (recommendedMovie.size() >= 10){
                        break;
                    }
                    else {
                        boolean is_exist = false;
                        for (int i = 0; i < recommendedMovie.size(); i++) {
                            if (recommendedMovieInfo.id == recommendedMovie.get(i).id)
                                is_exist = true;
                        }
                        if(!is_exist) {
                            recommendedMovie.add(recommendedMovieInfo);
                        }
                    }
                }
            }

            if (recommendedMovie.size() == 10)
                break;

            // System.out.println("Poor data!");

            if (step == 1){
                occupationProperty = new ParsingOccupation().getAllProperty();
            } else if(step == 2){
                ageProperty = new ParsingAge().getAllProperty();
            } else if(step == 3){
                genderProperty = new ParsingGender().getAllProperty();
            } else{
                step = 0; // for branch coverage,
                // step is 0 if it escape while loop.
                break;
            }
            step++;
            /*
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
                break;*/

        }

        // 5. Print top rating movie information
        for (RecommendedMovieInfo recommendedMovieInfo: recommendedMovie){
            int movieId = recommendedMovieInfo.id;
            //float movieRating = recommendedMovieInfo.rating;
            //int ratingCount = recommendedMovieInfo.count;
            String movieTitle = movieHash.get(movieId);
            String movieGenres = genresHash.get(movieId);
            String imdbId = linkHash.get(movieId);
            movieDataArrayList.add(new MovieData(movieTitle, movieGenres, "(http://www.imdb.com/title/tt" + imdbId + ")"));


            //repository.save(new MovieData(movieTitle, movieGenres, "(http://www.imdb.com/title/tt" + imdbId + ")"));
            //repository.findAll();
            //System.out.printf("[RATING] %.2f  ", movieRating);
            //System.out.printf("[COUNT] %4d   ", ratingCount);
            //System.out.printf("[%4d] ", movieId);
            //System.out.print(movieGenres + " ");
            //System.out.print(movieTitle + " ");
            //System.out.println("(http://www.imdb.com/title/tt" + imdbId + ") ");
            //System.out.println(movieTitle);
        }
    return movieDataArrayList;

    }
}