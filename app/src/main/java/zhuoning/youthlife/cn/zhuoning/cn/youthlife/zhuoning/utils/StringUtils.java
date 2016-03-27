package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.utils;

/**
 * Created by Andy on 16/1/12.
 */
public class StringUtils {
    public static String formatString(String string) {
        int begin = string.indexOf("{");
        int end = string.lastIndexOf("}") + 1;
        if(begin < 0 || end < 0)
            return "";
        return string.substring(begin, end);
    }
}