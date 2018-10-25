package one.bihe.bcode;

import okhttp3.Response;
import one.bihe.bcode.util.HttpUtils;
import one.bihe.bcode.util.JsonHelper;
import one.bihe.bcode.util.TextUtils;

import java.io.IOException;

import static one.bihe.bcode.util.PrintUtils.print;

public class Version {
    public static String versionName = "1.0.2";

    public static void check() {
        print("正在检查新版本");
        try {
            Response response = HttpUtils.get("https://raw.githubusercontent.com/WrBug/getBonusCode-java/master/version.json", null);
            String string = response.body().string();
            VersionInfo versionInfo = JsonHelper.fromJson(string, VersionInfo.class);
            if (versionInfo == null) {
                return;
            }
            if (TextUtils.isEqual(versionName, versionInfo.getVersionName())) {
                print("无需更新");
                return;
            }
            print("\n---------\n发现新版本：" + versionInfo.getVersionName() + "\n版本说明：\n"+versionInfo.getDesc()+"\n下载地址：\n"+versionInfo.getDownloadUrl()+"\n请自行前往下载");
        } catch (IOException e) {

        }
    }
}
