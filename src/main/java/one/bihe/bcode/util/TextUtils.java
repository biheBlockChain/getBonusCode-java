package one.bihe.bcode.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wrbug on 2017/5/4.
 */
public class TextUtils {
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isEmpty(CharSequence... charSequences) {
        if (charSequences != null) {
            for (CharSequence charSequence : charSequences) {
                if (isEmpty(charSequence)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isEqual(CharSequence charSequence1, CharSequence charSequence2) {
        if (charSequence1 != null && charSequence2 != null) {
            return charSequence1.toString().equals(charSequence2.toString());
        }
        return false;
    }

    public static boolean isEqualsIgnoreCase(CharSequence charSequence1, CharSequence charSequence2) {
        if (charSequence1 != null && charSequence2 != null) {
            return charSequence1.toString().equalsIgnoreCase(charSequence2.toString());
        }
        return false;
    }

    public static boolean isDigitsOnly(CharSequence str) {
        if (isEmpty(str)) {
            return false;
        }
        final int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public static boolean hasEmoji(String content) {
        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean isHex(String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        return number.matches("0[xX][0-9a-fA-F]+");
    }


    public static boolean isNumber(String txt) {
        if (TextUtils.isEmpty(txt)) {
            return false;
        }
        return txt.matches("[0-9]+(\\.[0-9])?[0-9]*");
    }

    public static double toDouble(String text) {
        if (!isNumber(text)) {
            return 0;
        }
        try {
            return Double.parseDouble(text);
        } catch (Throwable t) {

        }
        return 0;
    }

    public static long toLong(String text) {
        if (!isNumber(text)) {
            return 0;
        }
        try {
            return Long.parseLong(text);
        } catch (Throwable t) {

        }
        return 0;
    }

    public static int toInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Throwable t) {

        }
        return 0;
    }
}
