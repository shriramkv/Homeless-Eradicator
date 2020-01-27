package io.RaguRamanTB.homelesseradicator.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.fragments.BestIdeasFragment;
import io.RaguRamanTB.homelesseradicator.fragments.OngoingProjectsFragment;

public class OngoingHelper extends AsyncTask<String, Void, String> {

    Context context;
    private ProgressDialog progressDialog;

    public  OngoingHelper(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];
        if (type.equals("GetOngoing")) {
            try {
                URL url = new URL(Utils.ongoing_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

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
        Utils.arrayList4.clear();
        try {
            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.optString("title");
                String idea = jsonObject.optString("idea");
                String ideaby = jsonObject.optString("ideaby");
                if (ideaby.length() == 0) {
                    ideaby = "Anonymous";
                }
                String totals = title + " : " + "\n" + idea + "\n" + "By - " + ideaby;
                Utils.arrayList4.add(totals);
            }
            Fragment fragment = new OngoingProjectsFragment();
            loadFragment(fragment);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            ((AppCompatActivity) context).getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
