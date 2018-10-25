package one.bihe.bcode.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

public class JsonHelper {
    private static Gson sGson = new Gson();

    public static String toJson(Object o) {
        if (o == null) {
            return "";
        }
        return sGson.toJson(o);
    }


    public static JsonObject fromJson(Object json) {
        return fromJson(json, JsonObject.class);
    }

    public static <T> T fromJson(Object json, Class<T> tClass) {
        if (json == null) {
            return null;
        }
        if (json instanceof JsonElement) {
            try {
                return sGson.fromJson((JsonElement) json, tClass);
            } catch (Throwable t) {

            }
        }
        try {
            T t = sGson.fromJson(json.toString(), tClass);
            return t;
        } catch (Throwable t) {

        }
        return null;
    }

    public static <T> T fromJson(Object json, Type tClass) {
        try {
            if (json == null) {
                return null;
            }
            if (json instanceof JsonElement) {
                return sGson.fromJson((JsonElement) json, tClass);
            }
            return sGson.fromJson(json.toString(), tClass);
        } catch (Throwable t) {

        }
        return null;
    }

    public static Gson getGson() {
        return sGson;
    }
}
