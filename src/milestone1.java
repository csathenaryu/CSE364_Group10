import java.security.Key;
import java.util.ArrayList;

public class milestone1 {
    public static void main(String[] args) {


    }
}


// args 데이터 전처리
class ArgsParsing {

    class KeyValue{
        String key;
        String value;
        KeyValue(String k, String v){
            key = k;
            value = v;
        }
    }

    ArrayList<KeyValue> occupationMappingDatabase = new ArrayList<>();

    public String[] mapping(ArrayList<KeyValue> dataBase, String arg){

    }

}


class Parsing {
    public String[] parseByDelimiter(String rawData, String delimiter){
        return rawData.split(delimiter);
    }
}


public CustomList makeTargetTable(String fileName, String[] targetsProp, int targetIndex){
    // load data
    // read one line
    // Parsing.parseByDelimiter();
    // containTarget()
    // customLIst -> index: ID
    // CustomList 해당 index를 true로 설정
}

public float getTargetRating(String fileName, CustomList targetMovieList, CustomList targetUserList){
    // load data
    // read one line
    // Parsing.parseByDelimiter();
    // targetMovieList에 movieID 있고, targetUserList에 userID 있으면, update rating
    // see also: RatingManager
}


class CustomList {

    // dataList: arrayList type
    ArrayList<Boolean> dataList;

    // Constructor: How to use template?
    public CustomList(int initialCapacity) {
        dataList = new ArrayList<>(initialCapacity);
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
        float averageRating = (float)sum / (float)count;
        // 소수점 셋째자리에서 반올림
        averageRating++;
        return averageRating;
    }
}