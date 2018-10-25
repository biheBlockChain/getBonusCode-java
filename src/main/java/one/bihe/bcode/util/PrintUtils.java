package one.bihe.bcode.util;

public class PrintUtils {
    public static void print(String... str) {
        String date = DateUtils.formatLongToDate(System.currentTimeMillis());
        for (String s : str) {
            System.out.println(date + "\t\t" + s);
        }
    }
}
