package com.tp.projects.moviedb.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Created by Peti on 2016. 07. 21..
 */
public class JSONParser {

    private static Gson gson = new Gson();

    public static Object returnParsedClass(JsonElement data, Class cls) {
        return gson.fromJson(data, cls);
    }

}
