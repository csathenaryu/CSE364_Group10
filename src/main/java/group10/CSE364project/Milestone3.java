package group10.CSE364project;

import group10.CSE364project.repository.*;
import vimprojector.customdatastructure.Bitmap;
import vimprojector.customdatastructure.OneToMany;
import vimprojector.loadingdata.*;
import vimprojector.parsinginputargs.ParsingAge;
import vimprojector.parsinginputargs.ParsingGender;
import vimprojector.parsinginputargs.ParsingGenres;
import vimprojector.parsinginputargs.ParsingOccupation;
import vimprojector.recommender.RecommendedMovieInfo;
import vimprojector.recommender.TopRating;

import java.util.ArrayList;
import java.util.HashMap;

public class Milestone3 {

    public static ArrayList<MovieData> milestone3(String title, int limit, MovieRepository movieRepository, UserRepository userRepository,
                                                  RatingRepository ratingRepository, LinkRepository linkRepository, PosterRepository posterRepository) {
        ArrayList<MovieData> movieDataArrayList = new ArrayList<>();
        ArrayList<RecommendedMovieInfo> recommendedMovie = new ArrayList<>();

        if (limit <= 0) {
            limit = 10;
        }

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
        ArrayList<HashMap<String, String>> userData = FilePreprocessing.fromUserRepository(userLabel, userRepository);
        ArrayList<HashMap<String, String>> movieData = FilePreprocessing.fromMovieRepository(movieLabel, movieRepository);
        ArrayList<HashMap<String, String>> ratingData = FilePreprocessing.fromRatingRepository(ratingLabel, ratingRepository);
        // ArrayList<HashMap<String, String>> linkData = FilePreprocessing.fromLinkRepository(linkLabel, linkRepository);
        // Get title from movieID
        //HashMap<Integer, String> movieTitleHash = FilePreprocessing.intStringloadHashFrom("data/movies.dat", movieLabel, "MovieID", "Title", charset);
        // Get movie genres from title
        //HashMap<String, String> genresHash = FilePreprocessing.stringStringloadHashFrom("data/movies.dat", movieLabel, "Title", "Genres", charset);

        //HashMap<String, String> movieIDHash = FilePreprocessing.stringStringloadHashFrom("data/movies.dat", movieLabel, "Title", "MovieID", charset);


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

        String[] genderProperty = new ParsingGender().getAllProperty();
        String[] ageProperty = new ParsingAge().getAllProperty();
        String[] occupationProperty = new ParsingOccupation().getAllProperty();
        String[] genresProperty;
        boolean is_valid_title = true;

        if (movieRepository.findByTitle(title).isEmpty()) {
            System.out.println("The given title is invalid or does not exist in our movie data base.");
            System.out.println("The recommended movies will be selected from all genres.");
            genresProperty = new ParsingGenres().getAllProperty();
            is_valid_title = false;
        }
        else {
            genresProperty = new ParsingGenres().parseProperty(movieRepository.findByTitle(title).get(0).getGenres(), "\\|");
        }

        int step = 1;

        while (true) {

            /* 늘리는 순위: occupation (제한을 풀어버림) > age > gender */
            OneToMany genderTargetProperty = new OneToMany("Gender", genderProperty);
            OneToMany ageTargetProperty = new OneToMany("Age", ageProperty);
            OneToMany occupationTargetProperty = new OneToMany("Occupation", occupationProperty);
            OneToMany genresTargetProperty = new OneToMany("Genres", genresProperty);

            OneToMany[] userFilteringCriteria = {genderTargetProperty, ageTargetProperty, occupationTargetProperty};
            OneToMany[] movieFilteringCriteria = {genresTargetProperty};

            Bitmap filteredUser = DataFiltering.filterData(userFilteringCriteria, userData, "UserID");
            Bitmap filteredMovie;

            if (step == 1) {
                DataFilteringByCondition dataFiltering = new DataFilteringByCondition(new TotalEqualStrategy());
                filteredMovie = dataFiltering.filterData(movieFilteringCriteria, movieData, "MovieID");
            } else if (step == 2) {
                DataFilteringByCondition dataFiltering = new DataFilteringByCondition(new ContainsAllStrategy());
                filteredMovie = dataFiltering.filterData(movieFilteringCriteria, movieData, "MovieID");
            } else if (step == 3) {
                DataFilteringByCondition dataFiltering = new DataFilteringByCondition(new ContainsAtLeastOneStrategy());
                filteredMovie = dataFiltering.filterData(movieFilteringCriteria, movieData, "MovieID");
            } else {
                //step = 0; // for branch coverage,
                // step is 0 if it escape while loop.

                //Get all genres
                genresProperty = new ParsingGenres().getAllProperty();
                genresTargetProperty = new OneToMany("Genres", genresProperty);
                movieFilteringCriteria = new OneToMany[]{genresTargetProperty};

                filteredMovie = DataFiltering.filterData(movieFilteringCriteria, movieData, "MovieID");
            }



            // 4. extract top 10 movie
            if (is_valid_title) {
                // exclude input movie
                int inputID = movieRepository.findByTitle(title).get(0).getMovieId();
                // System.out.println(filteredMovie.getAt(inputID));
                filteredMovie.setAt(inputID, false);
                // System.out.println(filteredMovie.getAt(inputID));
            }


            TopRating topRating = new TopRating(ratingData, filteredMovie, filteredUser, limit);
            ArrayList<RecommendedMovieInfo> newlyRecommendedMovie = topRating.getTopRating();
            // System.out.println(newlyRecommendedMovie.get(1).rating);

            // String movieTitle = movieTitleHash.get(movieId);
            // System.out.println("title is " + title);

            if (recommendedMovie.isEmpty()) {
                recommendedMovie = newlyRecommendedMovie;
            } else {
                for (RecommendedMovieInfo recommendedMovieInfo : newlyRecommendedMovie) {
                    if (recommendedMovie.size() >= limit) {
                        break;
                    } else {
                        boolean is_exist = false;
                        for (int i = 0; i < recommendedMovie.size(); i++) {
                            if (recommendedMovieInfo.id == recommendedMovie.get(i).id)
                                is_exist = true;
                        }
                        if (!is_exist) {
                            recommendedMovie.add(recommendedMovieInfo);
                        }
                    }
                }
            }
            // System.out.println("size is " + recommendedMovie.size());
            if (recommendedMovie.size() == limit)
                break;

            // System.out.println("Poor data!");


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
        // System.out.println("recommend for " + inputID);
        // System.out.println("step for " + title + " is " + step);

        // 5. Print top rating movie information
        for (RecommendedMovieInfo recommendedMovieInfo : recommendedMovie) {
            int movieId = recommendedMovieInfo.id;
            float movieRating = recommendedMovieInfo.rating;
            //int ratingCount = recommendedMovieInfo.count;
            String movieTitle = movieRepository.findByMovieId(movieId).get(0).getTitle();
            String movieGenres = movieRepository.findByTitle(movieTitle).get(0).getGenres();
            String imdbId = linkRepository.findByMovieId(movieId).get(0).getImdbId();

            String posterURL;
            if (posterRepository.findByPosterId(movieId).isEmpty()){
                posterURL = "http://skg1891.cafe24.com/wp-content/uploads/2013/11/dummy-image-portrait.jpg";
            }
            else {
                posterURL = posterRepository.findByPosterId(movieId).get(0).getPosterURL();
            }
            movieDataArrayList.add(new MovieData(movieTitle, movieGenres, "(http://www.imdb.com/title/tt" + imdbId + ")", posterURL));


            //repository.save(new MovieData(movieTitle, movieGenres, "(http://www.imdb.com/title/tt" + imdbId + ")"));
            //repository.findAll();
            // System.out.printf("[RATING] %.2f  ", movieRating);
            //System.out.printf("[COUNT] %4d   ", ratingCount);
            //System.out.printf("[%4d] ", movieId);
            // System.out.print(movieGenres + " ");
            //System.out.print(movieTitle + " ");
            //System.out.println("(http://www.imdb.com/title/tt" + imdbId + ") ");
            // System.out.println(movieTitle);
        }
        return movieDataArrayList;
    }
}
