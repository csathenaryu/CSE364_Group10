package group10.CSE364project;

public class InputMovieData {
    private String title;
    private int limit;

    InputMovieData(){}

    InputMovieData(String title, int limit){
        this.title = title;
        this.limit = limit;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getLimit() {
        return this.limit;
    }
    public void setLimit(int limit) { this.limit = limit; }

}
