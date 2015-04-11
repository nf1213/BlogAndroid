package com.example.kitten.blog;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by kitten on 4/11/15.
 */
public class HttpGetTask extends AsyncTask<String, Void, String> {

    String url;
    int postId = -1;
    String response;

    public HttpGetTask(String responseString) {

    }

    public HttpGetTask(String responseString, int id) {
        postId = id;
    }

    @Override
    protected String doInBackground(String... urls) {
        try {
            return GET();
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

    public String GET() {
        InputStream inputStream = null;
        String result = "";
        try {
            if(postId > 0) {
                url = url + "/" + postId;
            }
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
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }
}
