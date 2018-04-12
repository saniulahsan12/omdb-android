package info.saniulahsan.omdbapp;

public class MovieItem {

    private String title;
    private String year;
    private String type;
    private String imdbID;
    private String imageUrl;

    public MovieItem(String title, String year, String type, String imdbID, String imageUrl) {
        this.title = title;
        this.year = year;
        this.type = type;
        this.imdbID = imdbID;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImdbID() {
        return imdbID;
    }
}
