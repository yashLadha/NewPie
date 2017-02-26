package own.code.play_nigga;

/**
 * Created in NewsFetcher on 2/22/2017.
 */

public final class urlManager {
    private static String Article_the_hindu_url = "https://newsapi.org/v1/articles?source="
            + "the-hindu"
            + "&sortBy=latest&apiKey="
            + "e1e2e737471c49a4b1e981f086f30c4c";

    private static String Article_url_CNBC = "https://newsapi.org/v1/articles?source="
            + "cnbc"
            + "&sortBy=latest&apiKey="
            + "e1e2e737471c49a4b1e981f086f30c4c";

    private static String Article_url_DailyMail = "https://newsapi.org/v1/articles?source="
            + "daily-mail"
            + "&sortBy=latest&apiKey="
            + "e1e2e737471c49a4b1e981f086f30c4c";

    private static String Article_url_google_news = "https://newsapi.org/v1/articles?source="
            + "google-news"
            + "&sortBy=latest&apiKey="
            + "e1e2e737471c49a4b1e981f086f30c4c";

    private static String Article_url_the_economist = "https://newsapi.org/v1/articles?source="
            + "the-economist"
            + "&sortBy=latest&apiKey="
            + "e1e2e737471c49a4b1e981f086f30c4c";

    public urlManager() {
    }

    public static String getArticle_the_hindu_url() {
        return Article_the_hindu_url;
    }

    public static String getArticle_url_CNBC() {
        return Article_url_CNBC;
    }

    public static String getArticle_url_DailyMail() {
        return Article_url_DailyMail;
    }

    public static String getArticle_url_google_news() {
        return Article_url_google_news;
    }

    public static String getArticle_url_the_economist() {
        return Article_url_the_economist;
    }
}
