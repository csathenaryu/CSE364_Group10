package vimprojector.recommender;

public class RatingCounter {
    int sum;
    int count;

    public RatingCounter(){
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
/*
        if (count == 0) {
            // System.out.println("No information");
            return 0;
        }
*/

        float averageRating = (float)sum / (float)count;
        // 소수점 셋째자리에서 반올림
        return averageRating;
    }
}
