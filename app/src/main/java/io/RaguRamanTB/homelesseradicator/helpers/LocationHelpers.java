package io.RaguRamanTB.homelesseradicator.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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

import io.RaguRamanTB.homelesseradicator.activities.DropHomelessActivity;

public class LocationHelpers extends AsyncTask<String, Void, String> {

    Context context;
    private ProgressDialog progressDialog;

    public LocationHelpers(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {

        String type = voids[0];

        if (type.equals("Nearby")) {
            try {
                String getLocation = voids[1];

                URL url = new URL(Utils.getLocation_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("getLocation","UTF-8")+"="+URLEncoder.encode(getLocation,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

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
        progressDialog.setMessage("Loading ... ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        Utils.arrayList5.clear();
        try {
            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String orgName = jsonObject.optString("org_name");
                String orgAddress = jsonObject.optString("org_address");
                String orgCity = jsonObject.optString("org_city");
                String location = orgName + " : " + "\n" + orgAddress + "\n" + orgCity;
                Utils.arrayList5.add(location);
            }
            Intent intent = new Intent(context, DropHomelessActivity.class);
            context.startActivity(intent);
        } catch (JSONException e) {
//            e.printStackTrace();
            Toast.makeText(context,"No nearby organisations can be found around you :(" + "\n" + "We will try to reach you soon",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
