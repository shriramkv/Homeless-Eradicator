package io.RaguRamanTB.homelesseradicator.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

public class BackgroundWorker extends AsyncTask <String, Void, String> {

    Context context;
    AlertDialog alertDialog;

    public BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];
        String register_url = "https://a4416073.ngrok.io/register.php";
        if (type.equals("Register")) {

        }
        return null;
    }
}
