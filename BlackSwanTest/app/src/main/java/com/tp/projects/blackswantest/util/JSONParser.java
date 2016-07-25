package com.tp.projects.blackswantest.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Peti on 2016. 07. 21..
 */
public class JSONParser {

    private static Gson gson = new Gson();

    public static Object returnParsedClass(JsonElement data, Class cls) {
        return gson.fromJson(data, cls);
    }

}
