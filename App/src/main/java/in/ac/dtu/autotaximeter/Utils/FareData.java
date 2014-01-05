package in.ac.dtu.autotaximeter.Utils;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Struct;
import java.util.ArrayList;

import in.ac.dtu.autotaximeter.FareApplication;

/**
 * Created by championswimmer on 5/1/14.
 */
public class FareData {
    public String city = "Delhi";
    public String operator = "Auto";
    public Double farePerKm = 8.0;
    public Double fixedFare = 25.0;
    public Integer fixedKm = 2;
    public Double bookingFee = 0.0;
    public FareData fareData[];

    public Context context = FareApplication.getFareContext();

    public class getFareData extends AsyncTask <Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String json = null;
            try {

                InputStream fareJson = context.getAssets().open("fare.json");
                int size = fareJson.available();
                byte[] buffer = new byte[size];
                fareJson.read(buffer);
                fareJson.close();
                json = new String(buffer, "UTF-8");


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return json;

        }

        protected void onPostExecute (String json) {

            ArrayList<JSONObject> fareList = new ArrayList<JSONObject>();
            JSONArray fareArray = new JSONArray(fareList);

            for (int i = 0; i < fareArray.length(); i++) {
                JSONObject fareObj = null;
                try {
                    fareObj = fareArray.getJSONObject(i);
                    fareData[i].city = fareObj.getString("city");
                    fareData[i].operator = fareObj.getString("operator");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }


    }

}
