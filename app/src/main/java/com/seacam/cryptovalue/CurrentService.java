package com.seacam.cryptovalue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jensensc on 3/9/18.
 */

public class CurrentService {

    public static void findCex(Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.CEX_BASE_URL).newBuilder();
        String url =urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static void findExmo(Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.EXMO_BASE_URL).newBuilder();
        String url =urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static void findGdax(Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.CEX_GDAX_URL).newBuilder();
        String url =urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static void findKracken(Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.CEX_KRACKEN_URL).newBuilder();
        String url =urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Rate> processCexResults(Response response){
        ArrayList<Rate> rates = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            JSONObject cryptoJSON = new JSONObject(jsonData);
            String name = "ETH";
            double cost = cryptoJSON.getDouble("last");
            String site = "CEX";

            Rate rate = new Rate(name, cost, site);
            rates.add(rate);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return rates;
    }

    public ArrayList<Rate> processExmoResults(Response response){
        ArrayList<Rate> rates = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            JSONObject cryptoJSON = new JSONObject(jsonData);
            JSONObject ethStuff = cryptoJSON.getJSONObject("ETH_USD");
            String name = "ETH";
            double cost = ethStuff.getDouble("buy_price");
            String site = "EXMO";

            Rate rate = new Rate(name, cost, site);
            rates.add(rate);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return rates;
    }

    public ArrayList<Rate> processGdaxResults(Response response){
        ArrayList<Rate> rates = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            JSONObject cryptoJSON = new JSONObject(jsonData);
            String name = "ETH";
            double cost = cryptoJSON.getDouble("price");
            String site = "GDAX";

            Rate rate = new Rate(name, cost, site);
            rates.add(rate);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return rates;
    }

    public ArrayList<Rate> processKrackenResults(Response response){
        ArrayList<Rate> rates = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            JSONObject cryptoJSON = new JSONObject(jsonData);
            JSONObject drillDown = cryptoJSON.getJSONObject("result");
            JSONObject anotherDown = drillDown.getJSONObject("XETHZUSD");
            JSONArray ethStuff = anotherDown.getJSONArray("a");
            String name = "ETH";
            double cost = ethStuff.getDouble(0);
            String site = "KRACKEN";

            Rate rate = new Rate(name, cost, site);
            rates.add(rate);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return rates;
    }
}
