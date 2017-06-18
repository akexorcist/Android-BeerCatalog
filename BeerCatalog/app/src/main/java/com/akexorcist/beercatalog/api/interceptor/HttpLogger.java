package com.akexorcist.beercatalog.api.interceptor;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Akexorcist on 6/18/2017 AD.
 */

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private final static String TAG = HttpLogger.class.getSimpleName();

    @Override
    public void log(String message) {
        final String logName = "OkHttp";
        if (!message.startsWith("{")) {
            Log.d(logName, message);
            return;
        }
        try {
            String prettyPrintJson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(new JsonParser().parse(message));
            Logger.t(logName).json(prettyPrintJson);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, "html header parse failed");
            e.printStackTrace();
            Log.e(logName, message);
        }
    }
}
