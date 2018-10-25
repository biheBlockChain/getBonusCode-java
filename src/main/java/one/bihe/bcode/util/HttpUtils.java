package one.bihe.bcode.util;

import okhttp3.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class HttpUtils {
    private static final String API_URL = "https://ec2-54-199-229-221.ap-northeast-1.compute.amazonaws.com";


    public static Response get(String url, Map<String, String> header) throws IOException {
        OkHttpClient client = OkhttpUtils.getClient();

        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        if (!CollectionUtils.isEmpty(header)) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Response response = client.newCall(builder.build()).execute();
        return response;
    }


    public static Response post(String path, Map<String, String> header, Map<String, Object> params) throws IOException {
        return post(API_URL, path, header, params);
    }

    public static Response post(String apiUrl, String path, Map<String, String> header, Map<String, Object> params) throws IOException {
        OkHttpClient client = OkhttpUtils.getClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, JsonHelper.toJson(params));
        Request.Builder builder = new Request.Builder()
                .url(apiUrl + path)
                .post(body);
        if (!CollectionUtils.isEmpty(header)) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Request request = builder.addHeader("content-type", "application/json")
                .build();
        return client.newCall(request).execute();
    }


    public static Response download(String path) throws IOException {
        String url = API_URL + path;
        OkHttpClient client = OkhttpUtils.getClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
}
