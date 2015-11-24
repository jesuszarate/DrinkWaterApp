package com.zarate.jesus.drinkwater.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by jesuszarate on 11/24/15.
 */
public class NetworkClass
{

    String BASE_URL = "http://155.99.90.115:5000/todo/api/v1.0/tasks/2";

    public String GetRequest()
    {
        try
        {
            URL url = new URL(BASE_URL);
            HttpURLConnection getRequest = (HttpURLConnection)url.openConnection();
            Scanner hwScanner = new Scanner(getRequest.getInputStream());

            StringBuilder stringBuilder = new StringBuilder();
            while(hwScanner.hasNext())
                stringBuilder.append(hwScanner.nextLine());

            String helloWorld = stringBuilder.toString();
            return helloWorld;
        }
        catch (Exception e)
        {
            Log.e("GetRequest", e.toString());
        }
        return "Didn't work";
    }

    public boolean initBattleGrid(Context context)
    {
        String stringUrl = BASE_URL;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
        {
            new DownloadBattleGridTask().execute(stringUrl);
            return true;
        } else
        {
            return false;
        }
    }

    private class DownloadBattleGridTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... urls)
        {
            // params comes from the execute() call: params[0] is the url.
            return GetRequest();
            //return requestBattleGrid(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result)
        {
            try
            {
                String whatIsResult = result;

            }catch (NullPointerException ne){
                // Do nothing if there was a NullPointerException.
                Log.e("NullPointerException In GetGrid", ne.toString());
            }
            catch (Exception e)
            {

                if (result.equals("Player id is not a valid GUID"))
                {

                } else
                {

                }
            }
        }
    }

    private String requestBattleGrid(String myurl) throws IOException
    {



        InputStream is = null;

        try
        {
            //JSONObject jsonobj = new JSONObject();

            //jsonobj.put(idTag, playerId);

            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httppostreq = new HttpPost(myurl);

            //StringEntity stringEntity = new StringEntity(jsonobj.toString());

            //stringEntity.setContentType("application/json");
            //httppostreq.setEntity(stringEntity);

            HttpResponse httpResponse = httpclient.execute(httppostreq);

            String responseText;

            responseText = EntityUtils.toString(httpResponse.getEntity());

            return responseText;

        }
        //catch (JSONException e)
        //{
          //  e.printStackTrace();
        //}
        catch (Exception e)
        {
            Log.e("Request", e.toString());

            if (is != null)
            {
                is.close();
            }
        }
        return  null;
    }
}
