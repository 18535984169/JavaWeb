package myssm.Util;

/**
 * @create 2023- 05- 17- 22:40
 * @desc
 */
public class StringUtil {
    public static boolean isEmpty(String str){
        return str==null || "".equals(str);
    }
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
