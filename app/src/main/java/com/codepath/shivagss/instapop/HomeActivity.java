package com.codepath.shivagss.instapop;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends Activity {

    public static final String CLIENT_ID = "a643f4b0dc344e20bac3db5893e9afe7";
    public static final String POPULAR_URL = "https://api.instagram.com/v1/media/popular?client_id=";
    private final String TAG = HomeActivity.this.getClass().getName();

    private ArrayList<InstagramPhoto> mList = new ArrayList<InstagramPhoto>();
    InstagramPhotosAdapter mAdapter;
    private ListView mListView;

    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPopularPhotos();
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mAdapter = new InstagramPhotosAdapter(this, mList);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchPopularPhotos();
    }

    private void fetchPopularPhotos() {
        mList.clear();
        String request_url = POPULAR_URL + CLIENT_ID;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(request_url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
//                Log.i(TAG, response.toString());
                JSONArray data = null;
                try{
                    data = response.getJSONArray("data");
                    for(int i=0; i< data.length(); i++){
                        JSONObject photo = data.getJSONObject(i);
                        InstagramPhoto instagramPhoto = new InstagramPhoto();
                        instagramPhoto.setUsername(photo.getJSONObject("user").getString("username"));
                        if(!photo.isNull("caption")) {
                            instagramPhoto.setCaption(photo.getJSONObject("caption").getString("text"));
                        }else{
                            instagramPhoto.setCaption("");
                        }
                        if(!photo.isNull("comments")){
                            int count = photo.getJSONObject("comments").getInt("count");
                            instagramPhoto.setCommentsCount(count);
                            if(count > 0){
                                JSONArray comments = photo.getJSONObject("comments").getJSONArray("data");
                                for(int j = comments.length() - 1 ; j >= comments.length() - 2 ; j--){
                                    JSONObject comment = comments.getJSONObject(j);
                                    instagramPhoto.getComments().add(("<b>"+comment.getJSONObject("from").getString("username") + "</b> "+comment.getString("text")));
                                }
                            }
                        }
                        instagramPhoto.setSubmittedTime(photo.getString("created_time"));
                        instagramPhoto.setProfilePicURL(photo.getJSONObject("user").getString("profile_picture"));
                        instagramPhoto.setLikes(photo.getJSONObject("likes").getInt("count"));
                        instagramPhoto.setImageURL(photo.getJSONObject("images").
                                getJSONObject("standard_resolution").getString("url"));
                        instagramPhoto.setImageHeight(photo.getJSONObject("images").
                                getJSONObject("standard_resolution").getInt("height"));
                        mList.add(instagramPhoto);
//                Log.i(TAG, response.toString());
                    }
                }catch (JSONException ex){
                    ex.printStackTrace();
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "Response failed. Status Code : " + statusCode);
                Log.e(TAG, "Response failed. "+ responseString);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.refresh) {
            swipeContainer.setRefreshing(true);
            fetchPopularPhotos();
            swipeContainer.setRefreshing(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
