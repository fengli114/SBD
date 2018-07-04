package com.mhy.sdk.utils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil
{

    public static final String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？≧▽≦]";

    /**
     * 替换sqlite特殊字符转义符
     *
     * @param keyWord
     * @return
     */
    public static String sqliteEscape(String keyWord)
    {
        if (StringUtil.isNotEmpty(keyWord))
        {
//            keyWord = keyWord.replace("/", "//");
            keyWord = keyWord.replace("'", "''");
            keyWord = keyWord.replace("[", "/[");
            keyWord = keyWord.replace("]", "/]");
            keyWord = keyWord.replace("%", "/%");
            keyWord = keyWord.replace("&", "/&");
            keyWord = keyWord.replace("_", "/_");
            keyWord = keyWord.replace("(", "/(");
            keyWord = keyWord.replace(")", "/)");
        }
        return keyWord;
    }
    /**
     * 方法名称:transMapToString
     * 传入参数:map
     * 返回值:String 形如 username'chenziwen^password'1234
     */
    public static String transMapToString(Map map){
        java.util.Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (java.util.Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append( "'" ).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? "^" : "");
        }
        return sb.toString();
    }
    /**
     * 将Map转化为Json
     *
     * @param map
     * @return String
     */
    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }

    /**
     * 方法名称:transStringToMap
     * 传入参数:mapString 形如 username'chenziwen^password'1234
     * 返回值:Map
     */
    public static Map transStringToMap(String mapString){
        Map map = new HashMap();
        java.util.StringTokenizer items;
        for(StringTokenizer entrys = new StringTokenizer(mapString, "^"); entrys.hasMoreTokens();
            map.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))
            items = new StringTokenizer(entrys.nextToken(), "'");
        return map;
    }

    /**
     * 判断字符串是否为空?
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str)
    {
        return (str == null || str.length() == 0);
    }

    /**
     * 判断字符串是否为空?
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str)
    {
        return (str != null && str.length() != 0);
    }

    /**
     * 判断文本是否相同
     *
     * @param actual
     * @param expected
     * @return
     */
    public static boolean isEquals(String actual, String expected)
    {
        return ObjectUtils.isEquals(actual, expected);
    }

    /**
     * 去除 所有特殊字符
     * `~!@#$%^&*()+=|{}':;',\[\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？
     * */
    public static String removeSpecialChar(String str){
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
