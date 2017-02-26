package own.code.play_nigga;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TheEconomists extends Fragment{

    private String LOG_TAG = getClass().getSimpleName();

    private NewsAdapter newsAdapter;
    private ListView listView;
    private String newsArticleUrl;

    public TheEconomists() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_the_economists, container, false);
        listView = (ListView) v.findViewById(R.id.News_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsFeed temp = (NewsFeed) parent.getItemAtPosition(position);
                Log.d(LOG_TAG, temp.getNews_url());
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(temp.getNews_url()));
                startActivity(i);
            }
        });
        newsArticleUrl = getArguments().getString("Url");
        TheEconomistsAsyncTask asyncTask = new TheEconomistsAsyncTask();
        asyncTask.execute();
        return v;
    }

    private class TheEconomistsAsyncTask extends AsyncTask<Void, Void, ArrayList<NewsFeed>> {

        @Override
        protected ArrayList<NewsFeed> doInBackground(Void... params) {
            ArrayList<NewsFeed> temp = new ArrayList<>();
            JsonParser jsonParser = new JsonParser(newsArticleUrl);
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

        @Override
        protected void onPostExecute(ArrayList<NewsFeed> newsFeeds) {
            newsAdapter = new NewsAdapter(getContext(), 0, newsFeeds);
            updateUI(newsAdapter);
        }
    }


    private void updateUI(NewsAdapter adapter) {
        listView.setAdapter(adapter);
    }
}
