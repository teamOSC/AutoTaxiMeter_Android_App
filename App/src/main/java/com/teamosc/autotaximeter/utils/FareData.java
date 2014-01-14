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
    private Double[] minFare;
    private Integer[] minKm;
    private Double[] farePerKm;
    private Double[] bookingFee;
    private Double[] waitingCharge;

    public int getLength() {
        return city.length;
    }

    public String[] getOperators() {
        String operators[] = new String[city.length];
        for (int i = 0; i < city.length; i++) {
            operators[i] = city[i] + " " + operator[i];
        }
        return operators;
    }


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
            operator = new String[len];
            minFare = new Double[len];
            minKm = new Integer[len];
            farePerKm = new Double[len];
            bookingFee = new Double[len];
            waitingCharge = new Double[len];

            for (int i = 0; i < len; i++) {
                JSONObject fareElement = fareJsonArray.getJSONObject(i);
                city[i] = fareElement.getString("city");
                operator[i] = fareElement.getString("operator");
                minFare[i] = fareElement.getDouble("min_fare_forfirst_X_km");
                minKm[i] = fareElement.getInt("X");
                farePerKm[i] = fareElement.getDouble("gen_fare_perkm");
                try {
                    bookingFee[i] = fareElement.getDouble("booking_fee");
                } catch (Exception e) {
                    bookingFee[i] = 0.0;
                }
                try {
                    waitingCharge[i] = fareElement.getDouble("waiting_charges");
                } catch (Exception e) {
                    waitingCharge[i] = 0.0;
                }
                /*Log.v(TAG,
                        city[i] + operator[i] + minFare[i] + minKm[i] +
                        farePerKm[i] + bookingFee[i] + waitingCharge[i]);*/
            }

        } catch (JSONException e) {
            Log.e(TAG, "could not parse json data");
            e.printStackTrace();
        }

    }


}
