package com.example.kitten.blog;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by kitten on 4/11/15.
 */
public class HttpGetTask extends AsyncTask<String, Void, String> {

    String url;
    PostAdapter mPostAdapter;

    public HttpGetTask(PostAdapter adapter) {
        mPostAdapter = adapter;
    }

    @Override
    protected String doInBackground(String... urls) {
        url = urls[0];
        try {
            return GET();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
           mPostAdapter.clear();
           parseJsonPosts(result);
        }
    }

    public String GET() {
        InputStream inputStream = null;
        String result = "";
        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }

    private void parseJsonPosts(String json) {
        try {
            JSONObject postsJson = new JSONObject(json);
            JSONArray postsArray = postsJson.getJSONArray("posts");
            for (int i = 0; i < postsArray.length(); i++) {
                JSONObject postItem = postsArray.getJSONObject(i);

                int id = postItem.getInt("id");
                String subject = postItem.getString("subject");
                String content = postItem.getString("content");
                String blogImageUrl = postItem.getJSONObject("blog_image").getJSONObject("blog_image").getString("url");
                String instagramLink = postItem.getString("instagram_link");

                Post post = new Post(id, subject, content, blogImageUrl, instagramLink);
                mPostAdapter.add(post);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
