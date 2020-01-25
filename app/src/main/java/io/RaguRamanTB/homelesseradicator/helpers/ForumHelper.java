package io.RaguRamanTB.homelesseradicator.helpers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import io.RaguRamanTB.homelesseradicator.activities.ForumActivity;

public class ForumHelper extends AsyncTask<String, Void, String> {

    Context context;
    private ProgressDialog progressDialog;

    public ForumHelper(Context ctx) {
        context = ctx;
    }


    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];

        if (type.equals("All")) {
            try {
                URL url = new URL(Utils.forum_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line = bufferedReader.readLine())!=null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading Status ... ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        Utils.arrayList.clear();
        try {
            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.optString("title");
                Utils.arrayList.add(title);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent2 = new Intent(context, ForumActivity.class);
        context.startActivity(intent2);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
