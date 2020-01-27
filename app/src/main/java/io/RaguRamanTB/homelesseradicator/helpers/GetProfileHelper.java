package io.RaguRamanTB.homelesseradicator.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import io.RaguRamanTB.homelesseradicator.activities.MyProfileActivity;

public class GetProfileHelper extends AsyncTask<String, Void, String> {

    Context context;
    private ProgressDialog progressDialog;

    public GetProfileHelper(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];

        if (type.equals("GetDetails")) {
            try {

                String getName = voids[1];

                URL url = new URL(Utils.getProfile_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("getName","UTF-8")+"="+URLEncoder.encode(getName,"UTF-8");
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
        Utils.profile.clear();
        try {
            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.optString("name");
                String address = jsonObject.optString("address");
                String email = jsonObject.optString("email");
                String phone_number = jsonObject.optString("phone_number");
                String profession = jsonObject.optString("profession");
                String dob = jsonObject.optString("dob");
                String org_name = jsonObject.optString("org_name");
                String org_address = jsonObject.optString("org_address");
                String org_city = jsonObject.optString("org_city");
                Utils.profile.add(name);
                Utils.profile.add(address);
                Utils.profile.add(email);
                Utils.profile.add(phone_number);
                Utils.profile.add(profession);
                Utils.profile.add(dob);
                Utils.profile.add(org_name);
                Utils.profile.add(org_address);
                Utils.profile.add(org_city);
            }
            Intent intent = new Intent(context, MyProfileActivity.class);
            context.startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
