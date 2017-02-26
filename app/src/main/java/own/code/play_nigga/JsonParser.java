package own.code.play_nigga;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created in NewsFetcher on 2/22/2017.
 */

public class JsonParser {

    private String LOG_TAG = getClass().getSimpleName();

    private String urlString = null;
    private URL url = null;

    public JsonParser(String urlString) {
        this.urlString = urlString;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "url is not converted");
            e.printStackTrace();
        }
    }

    public String makeHttpRequest() {
        String jsonResponse = "";
        HttpsURLConnection urlConnection = null;
        InputStream inputStream = null;
        if (url == null)
            return jsonResponse;
        try {
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            jsonResponse = parseJson(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }

    private String parseJson(InputStream stream) {
        StringBuilder builder = new StringBuilder();
        if (stream != null) {
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            try {
                String line = bufferedReader.readLine();
                while (line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "error in parseJson");
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
