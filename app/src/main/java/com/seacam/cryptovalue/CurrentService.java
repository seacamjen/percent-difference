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

    public static ArrayList<Rate> processResults(Response response){
        ArrayList<Rate> rates = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            JSONObject cryptoJSON = new JSONObject(jsonData);
            JSONArray exchangeJSON = cryptoJSON.getJSONArray("exchange");
            for (int i = 0; i <exchangeJSON.length(); i++){
                JSONObject amountJSON = exchangeJSON.getJSONObject(i);
                String name = "ETH";
                double cost = amountJSON.getJSONObject("ETH_USD").getDouble("buy_price");
                String site = "EXMO";

                Rate rate = new Rate(name, cost, site);
                rates.add(rate);
            }
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
