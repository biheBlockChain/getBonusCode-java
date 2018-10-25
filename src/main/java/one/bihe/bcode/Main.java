package one.bihe.bcode;

import com.google.gson.JsonObject;
import okhttp3.Response;
import one.bihe.bcode.util.*;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static one.bihe.bcode.util.PrintUtils.print;

public class Main {
    private static Map<String, String> cookie = new HashMap<>();
    private static ConfigInfo sConfigInfo;
    private static final String CONFIG_FILE = "config.json";
    private static long systemDelay;
    private static Stack<CaptchInfo> sCaptchInfoStack = new Stack<>();
    private static boolean success = false;

    public static void main(String[] args) {
        print("\n币合博纳云抢码脚本 版本号：" + Version.versionName +
                "\n使用后面的邀请地址注册，更容易成功，博纳云注册：https://console.bonuscloud.io/signUp?refer=264a1ce0d14511e894a05731b778d621" +
                "\n更多工具请关注：币合区块链" +
                "\n币合官方群：490389116\n--------\n");
        Version.check();
        if (!loadConfig()) {
            return;
        }
        if (!doLogin()) {
            print("登录失败");
            return;
        }
        print("开始检查若快打码");
        CaptchInfo captcha = getCaptcha(0);
        if (captcha == null) {
            print("若快打码失败");
            return;
        }
        while (true) {
            startLoop();
        }
    }

    private static void startLoop() {
        Date date = new Date(System.currentTimeMillis() / 1000 * 1000);
        date.setSeconds(0);
        date.setMinutes(0);
        date.setHours(date.getHours() + 1);
        long startTime = date.getTime() + systemDelay;
        print("抢码时间:" + DateUtils.formatLongToDate(date.getTime()));
        boolean preGet = false;
        while (true) {
            long delta = startTime - System.currentTimeMillis() - sConfigInfo.getLeadTimeMills();
            if (delta <= 2 * 60 * 1000 && !preGet) {
                preGet = true;
                sCaptchInfoStack.clear();
                getPreCaptcha();
            }
            if (delta <= 500) {
                success = false;
                start();
                break;
            }
            print((delta / 1000) + "秒后开始抢购");
            if (delta <= 5000) {
                sleep(delta - 100);
                continue;
            }
            if (delta <= 3 * 60 * 1000) {
                sleep(3000);
                continue;
            }
            sleep(10000);
        }
    }

    private static void getPreCaptcha() {
        print("开始预获取验证码");
        new Thread(() -> {
            do {
                CaptchInfo captcha = getCaptcha(System.currentTimeMillis());
                if (captcha != null) {
                    sCaptchInfoStack.push(captcha);
                    print("已成功获取" + sCaptchInfoStack.size() + "张验证码");
                }
            } while (sCaptchInfoStack.size() < sConfigInfo.getPreCaptchaCount());
        }).start();
    }


    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }

    }

    private static void start() {
        for (int i = 0; i < sConfigInfo.getThread(); i++) {
            int threasNum = i + 1;
            new Thread(() -> {
                for (long l = 1; l < sConfigInfo.getLoopTimes() + 1; l++) {
                    if (success) {
                        break;
                    }
                    print("线程" + threasNum + ", 第" + l + "次");
                    CaptchInfo captcha = null;
                    if (!sCaptchInfoStack.isEmpty()) {
                        captcha = sCaptchInfoStack.pop();
                    } else {
                        captcha = getCaptcha(0);
                    }
                    if (captcha != null) {
                        if (getBcode(captcha)) {
                            print("\n\n\n已抢到激活码\n\n\n");
                            success = true;
                            return;
                        }
                    }
                }
            }).start();
            sleep(100);
        }
    }


    private static boolean loadConfig() {
        print("读取配置文件");
        String str = FileUtils.readFile(CONFIG_FILE);
        if (!str.contains("{") || !str.contains("}")) {
            print("读取失败，请检查config.json配置");
            return false;
        }
        str = str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1);
        sConfigInfo = JsonHelper.fromJson(str, ConfigInfo.class);
        if (sConfigInfo == null) {
            print("读取失败，请检查config.json配置");
            return false;
        }
        if (TextUtils.isEmpty(sConfigInfo.getEmail(), sConfigInfo.getPassword())) {
            print("请检查 博纳云登录邮箱 或 博纳云登录密码 是否填写");
            return false;
        }
        if (TextUtils.isEmpty(sConfigInfo.getRuokuaiPassword(), sConfigInfo.getRuokuaiUsername())) {
            print("请检查 若快账号 或 若快密码 是否填写");
            return false;
        }
        StringBuilder builder = new StringBuilder("配置加载成功：\r\n");
        builder.append("博纳云账号：").append(sConfigInfo.getEmail()).append("\n")
                .append("若快账号：").append(sConfigInfo.getRuokuaiUsername()).append("\n")
                .append("每个线程抢购次数：").append(sConfigInfo.getLoopTimes()).append("\n")
                .append("提前时间（毫秒）：").append(sConfigInfo.getLeadTimeMills()).append("\n")
                .append("线程数：").append(sConfigInfo.getThread()).append("\n")
                .append("预获取验证码数量：").append(sConfigInfo.getThread()).append("\n");
        print(builder.toString());
        return true;
    }

    private static boolean doLogin() {
        print("开始登录");
        Map<String, Object> map = new HashMap<>();
        map.put("email", sConfigInfo.getEmail());
        map.put("password", sConfigInfo.getPassword());
        try {
            Response response = HttpUtils.post("/api/user/login", null, map);
            String date = response.header("date");
            long parse = Date.parse(date);
            systemDelay = System.currentTimeMillis() - parse;
            print("服务器延时：" + systemDelay);
            String result = response.body().string();
            JsonObject jsonObject = JsonHelper.fromJson(result);
            if (jsonObject.has("code") && jsonObject.get("code").getAsInt() == 200) {
                print("登录成功");
                cookie.putAll(CookieUtils.getSetCookieMap(response));
                return true;
            } else {
                print(result);
                return false;
            }
        } catch (IOException e) {
        }
        return doLogin();
    }

    private static CaptchInfo getCaptcha(long timestamp) {
        try {
            if (timestamp == 0) {
                timestamp = System.currentTimeMillis();
            }
            Response response = HttpUtils.download("/api/web/captcha/get/?" + timestamp / 1000);
            String data = is2base64(response.body().byteStream());
            Map<String, String> cookieMap = CookieUtils.getSetCookieMap(response);
            response = RuoKuai.getCode(sConfigInfo.getRuokuaiUsername(), sConfigInfo.getRuokuaiPassword(), data);
            String result = response.body().string();
            JsonObject jsonObject = JsonHelper.fromJson(result);
            if (jsonObject.has("Result")) {
                return new CaptchInfo(cookieMap, jsonObject.get("Result").getAsString());
            }
            print(result);
        } catch (IOException e) {
            print("获取验证码失败：" + e.getMessage());
        }
        return null;
    }


    private static String is2base64(InputStream inputStream) throws IOException {
        byte[] bytes = IOUtils.toByteArray(inputStream);
        String imageStr = Base64.getEncoder().encodeToString(bytes);
        return imageStr;
    }

    private static boolean getBcode(CaptchInfo captchInfo) {
        Map<String, String> header = new HashMap<>();
        header.put("cookie", CookieUtils.cookieMapToString(cookie, captchInfo.getCookie()));
        Map<String, Object> params = new HashMap<>();
        params.put("captcha", captchInfo.getCaptch());
        try {
            Response response = HttpUtils.post("/api/bcode/get/", header, params);
            String json = response.body().string();
            JsonObject jsonObject = JsonHelper.fromJson(json);
            if (jsonObject != null) {
                if (jsonObject.has("code") && jsonObject.get("code").getAsInt() == 200) {
                    return true;
                }
            }
            print(json);
        } catch (IOException e) {
            print("抢购失败：" + e.getMessage());
        }
        return false;
    }


}
