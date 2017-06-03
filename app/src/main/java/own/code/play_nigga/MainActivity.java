package own.code.play_nigga;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String LOG_TAG = getClass().getSimpleName();

    private String[] mDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isConnected = getConnectionInfo();
        Log.d(LOG_TAG, "Connection Info: " + isConnected);
        if (isConnected) {
            mTitle = mDrawerTitle = getTitle();
            mDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items);
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerList = (ListView) findViewById(R.id.left_drawer);

            setupToolbar();

            ArrayList<DataModel> drawerItem = new ArrayList<>();
            drawerItem.add(new DataModel("The Hindu"));
            drawerItem.add(new DataModel("The economists"));
            drawerItem.add(new DataModel("CNN"));
            drawerItem.add(new DataModel("BBC News"));
            drawerItem.add(new DataModel("Wall Street Journal"));
            drawerItem.add(new DataModel("The New-York Times"));

            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);

            DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
            mDrawerList.setAdapter(adapter);
            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            setupDrawerToggle();
            mDrawerLayout.setDrawerListener(mDrawerToggle);

            new MaterialDialog.Builder(this)
                    .title("Information")
                    .content("Please select the news Group from the navigation Menu. (At top left of Screen)")
                    .show();
        } else {
            Intent i = new Intent(this, NoConnection.class);
            startActivity(i);
            finish();
        }
    }

    private void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    public boolean getConnectionInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int pos) {
            Fragment fragment = null;
            String url = "";
            switch (pos) {
                case 0:
                    url = "https://newsapi.org/v1/articles?source="
                            + "the-hindu"
                            + "&sortBy=latest&apiKey="
                            + "e1e2e737471c49a4b1e981f086f30c4c";
                    fragment = new Main();
                    break;
                case 1:
                    url = "https://newsapi.org/v1/articles?source="
                            + "the-economist"
                            + "&sortBy=latest&apiKey="
                            + "e1e2e737471c49a4b1e981f086f30c4c";
                    fragment = new TheEconomists();
                    break;
                case 2:
                    url = " https://newsapi.org/v1/articles?source=cnn&sortBy=top&apiKey=e1e2e737471c49a4b1e981f086f30c4c";
                    fragment = new CNN();
                    break;
                case 3:
                    url = "https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=e1e2e737471c49a4b1e981f086f30c4c";
                    fragment = new BBBCNews();
                    break;
                case 4:
                    url = "https://newsapi.org/v1/articles?source=the-wall-street-journal&sortBy=top&apiKey=e1e2e737471c49a4b1e981f086f30c4c";
                    fragment = new Wall_Street_Journal();
                    break;
                case 5:
                    url = "https://newsapi.org/v1/articles?source=the-new-york-times&sortBy=top&apiKey=e1e2e737471c49a4b1e981f086f30c4c";
                    fragment = new New_York_Times();
                    break;
            }
            if (fragment != null) {
                Log.e(LOG_TAG, "Fragment refershes");
                Bundle bundle = new Bundle();
                bundle.putString("Url", url);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                mDrawerList.setItemChecked(pos, true);
                mDrawerList.setSelection(pos);
                setTitle(mDrawerItemTitles[pos]);
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                Log.e(LOG_TAG, "error in creating Fragments");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if  (mDrawerToggle != null)
            mDrawerToggle.syncState();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(title);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
