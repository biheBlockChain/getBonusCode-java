package one.bihe.bcode;

public class ConfigInfo {
    private String email = "";
    private String password = "";
    private String ruokuaiUsername = "";
    private String ruokuaiPassword = "";
    private long loopTimes = 10;
    private long leadTimeMills;
    private int thread = 3;
    private int preCaptchaCount = 30;


    public int getPreCaptchaCount() {
        return preCaptchaCount;
    }

    public void setPreCaptchaCount(int preCaptchaCount) {
        this.preCaptchaCount = preCaptchaCount;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuokuaiUsername() {
        return ruokuaiUsername;
    }

    public void setRuokuaiUsername(String ruokuaiUsername) {
        this.ruokuaiUsername = ruokuaiUsername;
    }

    public String getRuokuaiPassword() {
        return ruokuaiPassword;
    }

    public void setRuokuaiPassword(String ruokuaiPassword) {
        this.ruokuaiPassword = ruokuaiPassword;
    }

    public long getLoopTimes() {
        return loopTimes;
    }

    public void setLoopTimes(long loopTimes) {
        this.loopTimes = loopTimes;
    }

    public long getLeadTimeMills() {
        return leadTimeMills;
    }

    public void setLeadTimeMills(long leadTimeMills) {
        this.leadTimeMills = leadTimeMills;
    }
}
