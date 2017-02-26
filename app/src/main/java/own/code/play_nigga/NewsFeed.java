package own.code.play_nigga;

/**
 * Created in NewsFetcher on 2/22/2017.
 */

public class NewsFeed {
    private String news_url;
    private String image_url;
    private String description;
    private String title;
    private String publish_date;
    private String author;

    public NewsFeed(String news_url, String image_url, String description, String title, String author, String publish_date) {
        this.news_url = news_url;
        this.image_url = image_url;
        this.description = description;
        this.title = title;
        this.publish_date = publish_date;
        this.author = author;
    }

    public String getNews_url() {
        return news_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
