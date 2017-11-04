package com.r7.juanmanuelrojasp.apexmoviesapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Juan Manuel Rojas P on 11/08/2017.
 */

public class ApexMoviesPreferences {

    private Context context;
    private static SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public ApexMoviesPreferences(Context context){
        this.context = context;
    }

    public void putStringPreferences(String sharedPreferences, String name, String value) {
        preferences = context.getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public String getStringPreferences(String sharedPreferences, String valor, String defaul) {
        preferences = context.getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE);
        return preferences.getString(valor,defaul);
    }

    public void putIntPreferences(String sharedPreferences, String name, int value) {
        preferences = context.getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putInt(name, value);
        editor.apply();
    }

    public int getIntPreferences(String sharedPreferences, String key, int default_) {
        preferences = context.getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE);
        return preferences.getInt(key,default_);
    }
}
