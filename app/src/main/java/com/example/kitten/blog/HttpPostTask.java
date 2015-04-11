package com.example.kitten.blog;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;

/**
 * Created by kitten on 4/11/15.
 */
public class HttpPostTask extends AsyncTask<String, Void, String> {

    String url;
    List<NameValuePair> postParams;

    public HttpPostTask(List<NameValuePair> prms) {
        postParams = prms;
    }

    @Override
    protected String doInBackground(String... urls) {
        url = urls[0];
        try {
            POST(postParams);
        } catch (Exception e) {
            Log.e("HttpAsyncTask", e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {

    }



    public void POST(List<NameValuePair> params) {
        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost post = new HttpPost(url);

            post.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse response = httpclient.execute(post);
        } catch (Exception e) {
            Log.d("PostRequest", e.getLocalizedMessage());
        }
    }
}
