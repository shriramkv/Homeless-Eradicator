package io.RaguRamanTB.homelesseradicator.helpers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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

public class EditProfileHelper extends AsyncTask<String, Void, String> {

    Context context;
    private AlertDialog alertDialog;
    private ProgressDialog progressDialog;

    public EditProfileHelper(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];

        if (type.equals("EditProfile")) {
            try {
                String getName = voids[1];
                String getAddress = voids[2];
                String getEmailId = voids[3];
                String getPhoneNumber = voids[4];
                String getProfession = voids[5];
                String getDob = voids[6];
                String getPassword = voids[7];
                String getOrgName = voids[8];
                String getOrgAddress = voids[9];
                String getOrgCity = voids[10];
                String isAVolunteer = voids[11];
                String isAOrgIncharge = voids[12];

                if (isAVolunteer.equals("TRUE")) {
                    isAVolunteer = "1";
                } else if (isAVolunteer.equals("FALSE")) {
                    isAVolunteer = "0";
                }

                if (isAOrgIncharge.equals("TRUE")) {
                    isAOrgIncharge = "1";
                } else if (isAOrgIncharge.equals("FALSE")) {
                    isAOrgIncharge = "0";
                }

                URL url = new URL(Utils.editprofile_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("getName","UTF-8")+"="+URLEncoder.encode(getName,"UTF-8")+"&"
                        +URLEncoder.encode("getAddress","UTF-8")+"="+URLEncoder.encode(getAddress,"UTF-8")+"&"
                        +URLEncoder.encode("getEmailId","UTF-8")+"="+URLEncoder.encode(getEmailId,"UTF-8")+"&"
                        +URLEncoder.encode("getPhoneNumber","UTF-8")+"="+URLEncoder.encode(getPhoneNumber,"UTF-8")+"&"
                        +URLEncoder.encode("getProfession","UTF-8")+"="+URLEncoder.encode(getProfession,"UTF-8")+"&"
                        +URLEncoder.encode("getDob","UTF-8")+"="+URLEncoder.encode(getDob,"UTF-8")+"&"
                        +URLEncoder.encode("getPassword","UTF-8")+"="+URLEncoder.encode(getPassword,"UTF-8")+"&"
                        +URLEncoder.encode("getOrgName","UTF-8")+"="+URLEncoder.encode(getOrgName,"UTF-8")+"&"
                        +URLEncoder.encode("getOrgAddress","UTF-8")+"="+URLEncoder.encode(getOrgAddress,"UTF-8")+"&"
                        +URLEncoder.encode("getOrgCity","UTF-8")+"="+URLEncoder.encode(getOrgCity,"UTF-8")+"&"
                        +URLEncoder.encode("isAVolunteer","UTF-8")+"="+URLEncoder.encode(isAVolunteer,"UTF-8")+"&"
                        +URLEncoder.encode("isAOrgIncharge","UTF-8")+"="+URLEncoder.encode(isAOrgIncharge,"UTF-8");
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
        alertDialog = new AlertDialog.Builder(context).create();
        if (result.equals("Edit profile successful!")){
            alertDialog.setTitle("Profile Status");
            alertDialog.setMessage(result);
            alertDialog.show();
            alertDialog.setCancelable(true);
        } else {
            alertDialog.setTitle("Internal Error");
            alertDialog.setMessage(result);
            alertDialog.show();
            alertDialog.setCancelable(true);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
