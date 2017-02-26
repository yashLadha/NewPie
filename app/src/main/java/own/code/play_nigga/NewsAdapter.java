package own.code.play_nigga;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created in NewsFetcher on 2/22/2017.
 */

public class NewsAdapter extends ArrayAdapter<NewsFeed> {

    public NewsAdapter(Context context, int resource, ArrayList<NewsFeed> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null)
            v = LayoutInflater.from(getContext()).inflate(R.layout.news_feed, null);
        NewsFeed temp = getItem(position);
        TextView tvArticleName = (TextView) v.findViewById(R.id.tvArticleTopic);
        TextView tvArticleDescription = (TextView) v.findViewById(R.id.tvDescription);

        tvArticleDescription.setText(temp.getDescription());
        tvArticleName.setText(temp.getTitle());
        return v;
    }

}
