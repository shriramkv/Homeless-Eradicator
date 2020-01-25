package io.RaguRamanTB.homelesseradicator.helpers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class ForumPostHelper extends AsyncTask<String, Void, String> {

    Context context;
    private AlertDialog alertDialog;
    private ProgressDialog progressDialog;

    public ForumPostHelper(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {

        String type = voids[0];

        if (type.equals("Post")) {
            try {
                String getTitle = voids[1];
                String getIdea = voids[2];
                String getIdeaBy = Utils.USERNAME;
                URL url = new URL(Utils.putidea_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("getTitle","UTF-8")+"="+URLEncoder.encode(getTitle,"UTF-8")+"&"
                        +URLEncoder.encode("getIdea","UTF-8")+"="+URLEncoder.encode(getIdea,"UTF-8")+"&"
                        +URLEncoder.encode("getIdeaBy","UTF-8")+"="+URLEncoder.encode(getIdeaBy,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("Refresh")) {
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
        alertDialog = new AlertDialog.Builder(context).create();
        if (result.equals("Your idea has been suggested to the team. Thank you for idea!")) {
            alertDialog.setTitle("Idea Submission Status");
            alertDialog.setMessage(result);
            alertDialog.show();
            alertDialog.setCancelable(true);
        } else {
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
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
