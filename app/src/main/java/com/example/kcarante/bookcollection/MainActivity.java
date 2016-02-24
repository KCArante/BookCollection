package com.example.kcarante.bookcollection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //JSON Node names
    private static final String TAG_ID = "_id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_GENRE = "genre";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_ISREAD = "isRead";

    //url
    private static String url = "http://joseniandroid.herokuapp.com/api/books";

    ArrayList<Books> b = new ArrayList<Books>();
    JSONArray books = null;
    ArrayList<String> titleBooks = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        GetBooks task = new GetBooks();
        task.execute();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, titleBooks);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GetBooks extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            HttpUtils httpUtils = new HttpUtils();

            // Making a request to url and getting response
            String jsonStr = httpUtils.getResponse(url, "GET");
            Log.d("Response: ", "> " + jsonStr);

            if(jsonStr != null){
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);

                    for(int i=0; i<jsonArray.length(); i++){

                        JSONObject samp = jsonArray.getJSONObject(i);
                        Books bookSample = new Books();

                        String id = samp.getString(TAG_ID);
                        String title = samp.getString(TAG_TITLE);
                        String genre = samp.getString(TAG_GENRE);
                        String author = samp.getString(TAG_AUTHOR);
                        boolean isRead = samp.getBoolean(TAG_ISREAD);

                        bookSample.setId(id);
                        bookSample.setTitle(title);
                        bookSample.setGenre(genre);
                        bookSample.setAuthor(author);
                        bookSample.setIsRead(isRead);

                        titleBooks.add(title);
                        b.add(bookSample);
                    }
               }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
            else {
                Log.d("Server", "Error to connect on url");
            }

            return null;
        }

    }
}
