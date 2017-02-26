package own.code.play_nigga;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created in NewsFetcher on 2/22/2017.
 */

public class NewsArticleLoader extends AsyncTaskLoader<ArrayList<NewsFeed>> {

    private String LOG_TAG = getClass().getSimpleName();
    private String urlString;

    public NewsArticleLoader(Context context, String url) {
        super(context);
        urlString = url;
    }

    @Override
    public ArrayList<NewsFeed> loadInBackground() {
        ArrayList<NewsFeed> temp = new ArrayList<>();
        JsonParser jsonParser = new JsonParser(urlString);
        String response = jsonParser.makeHttpRequest();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray articles = jsonObject.getJSONArray("articles");
            for (int i = 0; i < articles.length(); i++) {
                JSONObject object = articles.getJSONObject(i);
                String author = object.getString("author");
                String title = object.getString("title");
                String description = object.getString("description");
                String article_url = object.getString("url");
                String image_url = object.getString("urlToImage");
                String publish_date = object.getString("publishedAt");
                temp.add(new NewsFeed(article_url, image_url, description, title, author, publish_date));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "error in Json Extraction");
            e.printStackTrace();
        }
        return temp;
    }
}
