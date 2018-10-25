package one.bihe.bcode.util;

import okhttp3.OkHttpClient;

import javax.net.ssl.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public class OkhttpUtils {
    private static Proxy proxy;

    public static void initProxy(String ip, int port) {
        proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
    }

    public static void stopProxy() {
        proxy = null;
    }

    public static OkHttpClient getClient() {
        return getClientFollowRedirects(true);
    }

    public static OkHttpClient getProxyClient(Proxy proxy) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(createSSLSocketFactory(), new TrustAllManager());
        builder.hostnameVerifier(new TrustAllHostnameVerifier());
        if (proxy != null) {
            builder.proxy(proxy);
        }
        return builder.build();
    }

    public static OkHttpClient getClientFollowRedirects(boolean follow) {
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        mBuilder.followRedirects(follow).followSslRedirects(follow);
        mBuilder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
        return getClient(mBuilder);
    }

    public static OkHttpClient getClient(OkHttpClient.Builder builder) {
        builder.sslSocketFactory(createSSLSocketFactory(), new TrustAllManager());
        builder.hostnameVerifier(new TrustAllHostnameVerifier());
        if (proxy != null) {
            builder.proxy(proxy);
        }
        return builder.build();
    }

    /**
     * 默认信任所有的证书
     * TODO 最好加上证书认证，主流App都有自己的证书
     *
     * @return
     */
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)

                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
