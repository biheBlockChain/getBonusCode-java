package one.bihe.bcode.util;

import okhttp3.*;

import java.io.IOException;

public class RuoKuai {
    private final static String SOFT_ID = "114991";
    private final static String SOFT_KEY = "df1ef759986d4824bd16a42d9ec0760e";

    public static Response getCode(String username, String password, String base64Data) throws IOException {
        OkHttpClient client = OkhttpUtils.getClient();
        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW" +
                "\r\nContent-Disposition: form-data; name=\"username\"\r\n\r\n" + username +
                "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW" +
                "\r\nContent-Disposition: form-data; name=\"password\"\r\n\r\n" + password +
                "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW" +
                "\r\nContent-Disposition: form-data; name=\"typeid\"\r\n\r\n3060" +
                "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW" +
                "\r\nContent-Disposition: form-data; name=\"softid\"\r\n\r\n" + SOFT_ID +
                "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW" +
                "\r\nContent-Disposition: form-data; name=\"softkey\"\r\n\r\n" + SOFT_KEY +
                "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW" +
                "\r\nContent-Disposition: form-data; name=\"image\" ; filename=\"1.png\"\r\nContent-Type: application/octet-stream\r\nContent-Transfer-Encoding: base64\r\n\r\n" + base64Data +
                "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
                .url("http://api.ruokuai.com/create.json")
                .post(body)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("cache-control", "no-cache")
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
}
