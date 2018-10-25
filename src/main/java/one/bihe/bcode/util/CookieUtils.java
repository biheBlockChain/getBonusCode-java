package one.bihe.bcode.util;

import okhttp3.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CookieUtils {
    public static String getSetCookie(Response response) {
        Map<String, String> map = getSetCookieMap(response);
        return cookieMapToString(map);
    }

    public static String getFirstSetCookie(Response response) {
        List<String> cookie = response.headers("set-cookie");
        StringBuilder cookies = new StringBuilder();
        for (String s : cookie) {
            String[] cks = s.split(";");
            if (cks != null && cks.length > 0) {
                cookies.append(cks[0]).append(";");
            }
        }
        return cookies.toString();
    }

    public static Map<String, String> getSetCookieMap(Response response) {
        List<String> cookie = response.headers("set-cookie");
        Map<String, String> map = new HashMap<>();
        for (String s : cookie) {
            map.putAll(cookieStrToMap(s));
        }
        return map;
    }

    @SafeVarargs
    public static String cookieMapToString(Map<String, String>... ckMap) {
        if (ckMap == null || ckMap.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Map<String, String> map : ckMap) {
            if (map.containsKey("Domain")) {
                map.remove("Domain");
            }
            if (map.containsKey("Expires")) {
                map.remove("Expires");
            }
            if (map.containsKey("Path")) {
                map.remove("Path");
            }
            if (map.containsKey("domain")) {
                map.remove("domain");
            }
            if (map.containsKey("expires")) {
                map.remove("expires");
            }
            if (map.containsKey("path")) {
                map.remove("path");
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
            }
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public static String getCookie(List<String>... cookie) {
        StringBuilder cookies = new StringBuilder();
        for (List<String> strings : cookie) {
            for (String s : strings) {
                cookies.append(s).append(";");
            }
        }
        return cookies.toString();
    }

    public static Map<String, String> cookieStrToMap(String cookieStr) {
        Map<String, String> map = new HashMap<>();
        String[] cks = cookieStr.split(";");
        for (String ck : cks) {
            String[] kv = ck.split("=");
            if (kv.length == 2) {
                if (kv[0].trim().toLowerCase().equals("path")) {
                    continue;
                }
                map.put(kv[0], kv[1]);
            }
        }
        return map;
    }
}
