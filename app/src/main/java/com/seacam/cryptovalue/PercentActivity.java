package com.seacam.cryptovalue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PercentActivity extends AppCompatActivity {
    public static final String TAG = PercentActivity.class.getSimpleName();
    public ArrayList<Rate> rates = new ArrayList<>();
    Double CEXprice;
    Double Exmoprice;
    Double Gdaxprice;
    Double Krackenprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);
        getCex();
        getExmo();
        getGdax();
        getKracken();
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
                rates = currentService.processCexResults(response);

                PercentActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        double[] currencyRates = new double[rates.size()];
                        for (int i = 0; i < currencyRates.length; i++ ) {
                            currencyRates[i] = rates.get(i).getCost();
                        }



                        for (Rate rate : rates) {
                            CEXprice = rate.getCost();
                            Log.d(TAG, "Name " + rate.getName());
                            Log.d(TAG, "Cost " + rate.getCost());
                            Log.d(TAG, "Site " + rate.getSite());

                        }
                    }
                });
            }
        });
    }

    private void getExmo() {
        final CurrentService currentService = new CurrentService();
        currentService.findExmo(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { e.printStackTrace(); }

            @Override
            public void onResponse(Call call, Response response) {
                rates = currentService.processExmoResults(response);

                PercentActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        double[] currencyRates = new double[rates.size()];
                        for (int i = 0; i < currencyRates.length; i++){
                            currencyRates[i] = rates.get(i).getCost();
                        }

                        for (Rate rate: rates) {
                            Exmoprice = rate.getCost();
                            Log.d(TAG, "Name " + rate.getName());
                            Log.d(TAG, "Cost " + rate.getCost());
                            Log.d(TAG, "Site " + rate.getSite());
                        }
                    }
                });
            }
        });
    }

    private void getGdax() {
        final CurrentService currentService = new CurrentService();
        currentService.findGdax(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { e.printStackTrace(); }

            @Override
            public void onResponse(Call call, Response response) {
                rates = currentService.processGdaxResults(response);

                PercentActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        double[] currencyRates = new double[rates.size()];
                        for (int i = 0; i < currencyRates.length; i++) {
                            currencyRates[i] = rates.get(i).getCost();
                        }

                        for (Rate rate: rates) {
                            Gdaxprice = rate.getCost();
                            Log.d(TAG, "Name " + rate.getName());
                            Log.d(TAG, "Cost " + rate.getCost());
                            Log.d(TAG, "Site " + rate.getSite());
                        }
                    }
                });

            }
        });
    }

    private void getKracken() {
        final CurrentService currentService = new CurrentService();
        currentService.findKracken(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                rates = currentService.processKrackenResults(response);

                PercentActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        double[] currencyRates = new double[rates.size()];
                        for (int i = 0; i < currencyRates.length; i++) {
                            currencyRates[i] = rates.get(i).getCost();
                        }

                        for (Rate rate: rates) {
                            Krackenprice = rate.getCost();
                            Log.d(TAG, "Name " + rate.getName());
                            Log.d(TAG, "Cost " + rate.getCost());
                            Log.d(TAG, "Site " + rate.getSite());
                        }
                    }
                });

            }
        });
    }
}
