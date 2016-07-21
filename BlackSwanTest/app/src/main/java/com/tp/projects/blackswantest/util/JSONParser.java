package com.tp.projects.blackswantest.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Peti on 2016. 07. 21..
 */
public class JSONParser {

    private static Gson gson = new Gson();

    public static String readStream(String filePath, Context ctx) {
        String data = "";

        try {
            InputStream is = ctx.getAssets().open(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder out = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            data = out.toString();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static Object returnParsedClass(JsonElement data, Class cls){
        return gson.fromJson(data,cls);
    }

    public static Object returnParsedClass(String data, Class cls){
        return gson.fromJson(data,cls);
    }
}
