package com.teamosc.autotaximeter.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by championswimmer on 11/1/14.
 */
public class FareData {

    public String TAG = "AutoTaxiMeter:FareData";

    private String[] city;
    private String[] operator;
    private Float[] minFare;
    private Integer[] minKm;
    private Float[] farePerKm;
    private Float[] bookingFee;
    private Float[] waitingCharge;


    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("fare.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e(TAG, "could not load fare.json");
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public FareData(Context context) {

        try {
            JSONArray fareJsonArray = new JSONArray(loadJSONFromAsset(context));
            int len = fareJsonArray.length();
            city = new String[len];
            for (int i = 0; i < len; i++) {
                JSONObject fareElement = fareJsonArray.getJSONObject(i);
                city[i] = fareElement.getString("city");
                Log.v(TAG, city[i]);
            }

        } catch (JSONException e) {
            Log.e(TAG, "could not parse json data");
            e.printStackTrace();
        }

    }


}
