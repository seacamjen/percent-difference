package com.seacam.cryptovalue;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    public ArrayList<Rate> rates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCex();
//        getExmo();
//        getGdax();
//        getKracken();
    }

    private void getCex() {
        final CurrentService currentService = new CurrentService();
        currentService.findCex(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                rates = CurrentService.processResults(response);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] currencyRates = new String[rates.size()];
                        for (int i = 0; i < currencyRates.length; i++){
                            currencyRates[i] = rates.get(i).getName();
                            Log.d(TAG, rates.get(i).getName());
                        }

                        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, currencyRates);


                        for (Rate rate : rates){

                            Log.d(TAG, "Name: " + rate.getName());
                            Log.d(TAG, "Cost: " + rate.getCost());
                            Log.d(TAG, "Site: " + rate.getSite());

                        }
                    }
                });

//                    String jsonData = response.body().string();
//                    if(response.isSuccessful()){
//                        Log.v(TAG, jsonData);
//
//                    }


            }
        });
    }

//    private void getExmo() {
//        final CurrentService currentService = new CurrentService();
//        currentService.findExmo(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try{
//                    String jsonData = response.body().string();
//                    Log.v(TAG, jsonData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

//    private void getGdax() {
//        final CurrentService currentService = new CurrentService();
//        currentService.findGdax(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try{
//                    String jsonData = response.body().string();
//                    Log.v(TAG, jsonData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

//    private void getKracken() {
//        final CurrentService currentService = new CurrentService();
//        currentService.findKracken(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try{
//                    String jsonData = response.body().string();
//                    Log.v(TAG, jsonData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
