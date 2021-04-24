package main.java;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        String[] genres;
        String[] occupation;

        GetProperObjects getProperObjects = new GetProperObjects();
        GetRating getRating = new GetRating();

        // Args[0]: movie genres
        try{
            genres = ArgsPreprocessing.preprocess(MovieGenres.list, args[0], "\\|", new MovieGenres());
        } catch (Exception e){
            genres = MovieGenres.list.getAll().toArray(new String[0]);
        }
        // Args[1]: occupation
        try{
            occupation = ArgsPreprocessing.preprocess(Occupation.list, args[1], "\\|", new Occupation());
        } catch (Exception e){
            occupation = Occupation.list.getAll().toArray(new String[0]);
        }

        System.out.println("Your genres are "+ Arrays.toString(genres));
        System.out.println("Your occupation code is " + Arrays.toString(occupation));
        System.out.println();

        CustomList movieList = getProperObjects.makeTargetTable("data/movies.dat", genres, 2);
        CustomList userList = getProperObjects.makeTargetTable("data/users.dat", occupation, 3);
        float avgRating = getRating.getTargetRating("data/ratings.dat", movieList, userList);
        // System.out.println(avgRating);
        System.out.println(String.format("Average rating is %.2f", avgRating));

    }
}


// ArgsPreprocessing: input argument 데이터 전처리
// preprocess: inputArg를 parsing하여 String[]으로 반환, filter 함수를 이용하여 잘못된 입력값은 걸러내고 제대로 된 입력값만 걸러내어 filtered에 저장
// transform: filtering 후 남은 argument가 없는 경우, 모든 property를 반환함
class ArgsPreprocessing{

    // inputArgument랑, targetArgument의 list는 사실상 같은 객체임
    // 어떻게 문제를 해결하는 게 좋을까?
    public static String[] preprocess(Pair inputArgument, String inputArg, String delimiter, InputArgument targetArgument){
        String[] parsedArg = Parser.parseByDelimiter(inputArg, delimiter);
        ArrayList<String> filtered = new ArrayList<>();
        for (String arg : parsedArg){
            targetArgument.filter(filtered, arg.toLowerCase());
        }
        return transform(filtered, inputArgument);
    }

    public static String[] transform(ArrayList<String> mappedArg, Pair inputArgument){
        ArrayList<String> temp;
        if (mappedArg.size() != 0){
            temp = mappedArg;
        } else{
            temp = inputArgument.getAll();
            System.out.println("There is no valid input. Terminate program.");
            System.exit(0);
        }
        return temp.toArray(new String[0]);
    }
}


// InputArgument: argument input 전처리 과정에서 사용될 filter 함수를 선언
// Argument 종류에 따라 전처리 과정에 사용되는 filter가 달라지므로, InputArgument를 상속받아 overriding 해줄 것
interface InputArgument {
    void filter(ArrayList<String> mappedArg, String lowerArg);
}

// Pair 대신 다른 data structure를 알아볼 것
class MovieGenres implements InputArgument{
    static Pair list = new Pair(
            "action:action,animation:animation,children's:children's,comedy:comedy,crime:crime,documentary:documentary,drama:drama,fantasy:fantasy,film-noir:film-noir,horror:horror,musical:musical,mystery:mystery,romance:romance,scifi:scifi,thriller:thriller,war:war,western:western");

    @Override
    public void filter(ArrayList<String> mappedArg, String lowerArg){
        if (list.has(lowerArg) && (!mappedArg.contains(lowerArg))){
            mappedArg.add(list.get(lowerArg));
        }
        else {
            System.out.println(lowerArg + " is invalid or already exist. Try using another genres.");
        }
    }
}

class Occupation implements InputArgument{
    // mapping 정보는 "InputFormat:DataFormat"으로 주어지며, ","로 구분되어 있음
    // mapping 정보를 나열할 때는 lowercase로 작성, 공백 없이 작성
    static Pair list = new Pair(
            "other:0,academic:1,educator:1,artist:2,clerical:3,admin:3,collegestudent:4,gradstudent:4,customerservice:5,doctor:6,healthcare:6,executive:7,managerial:7,farmer:8,homemaker:9,k-12student:10,lawyer:11,programmer:12,retired:13,sales:14,marketing:14,scientist:15,self-employed:16,technician:17,engineer:17,tradesman:18,craftsman:18,unemployed:19,writer:20");

    @Override
    public void filter(ArrayList<String> mappedArg, String lowerArg){
        if (list.has(lowerArg) && !mappedArg.contains(lowerArg)){
            mappedArg.add(list.get(lowerArg));
        } else if(!mappedArg.contains("0")){
            mappedArg.add("0");
        }
    }
}

class Age implements InputArgument{
    static Pair list = new Pair(
            "under18:1,18-24:18,25-34:25,35-44:35,45-49:45,50-55:50,56+:56");

    @Override
    public void filter(ArrayList<String> mappedArg, String lowerArg) {
        if (list.has(lowerArg) && (!mappedArg.contains(lowerArg))){
            mappedArg.add(list.get(lowerArg));
        }
    }
}


// Pair: HashMap으로 InputFormat:DataFormat mapping 정보를 저장
// Key<String>: input format
// Value<String>: data format
// HashMap이 아니라 다른 Data Structure를 사용하는 경우, 아래의 함수를 필수로 구현할 것
// Constructor: String -> void, String으로 주어진 mapping 정보를 parsing하여 key, value로 저장
// has: String -> Boolean, 해당 key가 존재하는지 판단
// get: String -> String, given key에 해당하는 value를 반환, 'has'를 이용하여 해당 key가 존재하는지 확인한 후 사용할 것
// getAll: void -> ArrayList<String>, 모든 value를 반환, filter condition이 주어지지 않은 경우 사용
// see also: ArgsPreprocessing, InputArgumentType, main()
class Pair {
    HashMap<String, String> pairs = new HashMap<>();

    public Pair(String pairString){
        String[] mappingList = pairString.split(",");
        for (String mapping : mappingList){
            String[] keyValue = mapping.split(":");
            pairs.put(keyValue[0], keyValue[1]);
        }
    }

    public boolean has(String key){
        return pairs.containsKey(key);
    }

    public String get(String key){
        return pairs.get(key);
    }

    public ArrayList<String> getAll(){
        Set<String> keys = pairs.keySet();
        ArrayList<String> allKeys = new ArrayList<>();
        keys.forEach(key -> {
            if(!allKeys.contains(pairs.get(key))) {
                allKeys.add(pairs.get(key));
            }
        });
        return allKeys;
    }
}


class Parser {
    public static String[] parseByDelimiter(String rawData, String delimiter){
        return rawData.split(delimiter);
    }
}



class CustomList {
    // dataList: arrayList type
    ArrayList<Boolean> dataList;

    // Constructor: How to use template?
    public CustomList(int a) {
        dataList = new ArrayList<Boolean>(Collections.nCopies(a,false));
    }

    // get length
    public int getLength(){
        return dataList.size();
    }

    // get element
    public boolean getAt(int index){
        return dataList.get(index);
    }

    // push element
    // data 넣을 때 sorting 필요?
    public void push(int index, boolean value){
        dataList.add(index, value);
    }

    // set element
    public void setAt(int index, boolean value) {
        if (index < dataList.size())
            dataList.set(index, value);
        else {
            addElementTillIndex(index);
            dataList.add(index, value);
        }
    }

    //If index is not found, add element until the index
    public void addElementTillIndex (int index) {

        for (int i = dataList.size() ; i < index ; i++){
            dataList.add(i, false);
        }
    }
}

class GetProperObjects {

    /*fileName = file path, targetsProp = parsed input data, targetIndex = */
    public CustomList makeTargetTable(String fileName, String[] targetsProp, int targetIndex) {


        CustomList targetTable = new CustomList(10000);

        try {

            File file = new File(fileName);
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(filereader);
            String fileLine = "";

            while ((fileLine = bufReader.readLine()) != null) {
                String[] parsedFileLine = Parser.parseByDelimiter(fileLine, "::");  // delimiter를 parameter로 받으면 안되나
                int targetID = Integer.parseInt(parsedFileLine[0]);
                targetTable.setAt(targetID, returnTrueOrFalse(parsedFileLine, targetsProp, targetIndex));
            }
            bufReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        return targetTable;
    }

    public boolean returnTrueOrFalse(String[] line, String[] targetsProp, int targetIndex) {

        if (targetsProp.length == 0)
            return false;

        for (String str : targetsProp) {
            String a = line[targetIndex];
            a = a.toLowerCase();

            String[] parse_line = Parser.parseByDelimiter(a, "\\|");
            int flag = 0;
            for (String str2 : parse_line) {
                if (str.equals(str2)) {
                    flag++;
                }
            }
            if (flag == 0) {
                return false;
            }
        }
        return true;
    }
}

class GetRating {
    int userID;
    int movieID;
    int rating;

    public GetRating(){
        userID = 0;
        movieID = 0;
        rating = 0;
    }

    public float getTargetRating(String fileName, CustomList targetMovieList, CustomList targetUserList) {
        RatingManager ratingManager = new RatingManager();

        File file = new File(fileName);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader buffer = new BufferedReader(reader);
        String line = "";
        while (true) {
            try {
                if (!((line = buffer.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            getRatingInfo(line);
            updateTargetRating(ratingManager, targetMovieList, targetUserList);
        }

        // see also: RatingManager

        float AverageRating = ratingManager.getAverageRating();
        return AverageRating;
    }

    public void getRatingInfo (String line) {
        String[] parse = Parser.parseByDelimiter(line,"::");
        userID = Integer.parseInt(parse[0]);
        movieID = Integer.parseInt(parse[1]);
        rating = Integer.parseInt(parse[2]);
    }

    public void updateTargetRating (RatingManager ratingManager, CustomList targetMovieList, CustomList targetUserList) {
        if (targetUserList.getAt(userID) && targetMovieList.getAt(movieID)) {
            ratingManager.update(rating);
        }
    }
}


class RatingManager {
    int sum;
    int count;

    public RatingManager(){
        sum = 0;
        count = 0;
    }

    public void updateSum(int rating){
        sum += rating;
    }

    public void updateCount(){
        count++;
    }

    // sum 과 count 를 증가시킴
    public void update(int rating){
        updateSum(rating);
        updateCount();
    }

    // average rating 을 반환
    public float getAverageRating(){
        if (count == 0) {
            System.out.println("No information");
            return 0;
        }
        float averageRating = (float)sum / (float)count;
        // 소수점 셋째자리에서 반올림
        return averageRating;
    }


    // 각 영화의
}

class GetTopRating {
    HashMap<String, float> movie_rating = new HashMap<String, float>();
    HashMap<String, Integer> movie_rating_num = new HashMap<String, Integer>();
    HashMap<String, Integer> movie_rating_sum = new HashMap<String, Integer>();

    void Get_movie_rating (String fileName, CustomList targetMovieList, CustomList targetUserList) {
        // 	rating 읽으면서 target 해당하면
        //	update_movie_rating (movie id, movie rating)
        //	rating list = get_movie_average ()
        //	get_top_rating (rating list)
   }

   void update_movie_rating (String id, Integer rating) {
       movie_rating_num.put(id, movie_rating_num.get(id) + 1); // id에 해당하는 num 1 증가
       movie_rating_sum.put(id, movie_rating_sum.get(id) + rating);// id에 해당하는 rating update
   }

    get_movie_average (String id) {
        float count = (float) movie_rating_num.get(id);
        float rating_sum = (float) movie_rating_sum.get(id);
        if (count != 0) {
            float average_rating = rating_sum / count;
        }
        movie_rating.put(id, average_rating);
    }

    sort_movie_average () {
        movie_rating value 기준으로 내림차순 정렬
        return movie_rating_list;
    }

    get_top_rating () {
        movie_rating_list에서 상위 10개의 id를 recommended (arraylist) 로 받아옴
        return recommended (arraylist)
    }

}