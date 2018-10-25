package one.bihe.bcode;

import java.util.Map;

public class CaptchInfo {
    private Map<String,String> cookie;
    private String captch;

    public CaptchInfo(Map<String, String> cookie, String captch) {
        this.cookie = cookie;
        this.captch = captch;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }

    public void setCookie(Map<String, String> cookie) {
        this.cookie = cookie;
    }

    public String getCaptch() {
        return captch;
    }

    public void setCaptch(String captch) {
        this.captch = captch;
    }
}
