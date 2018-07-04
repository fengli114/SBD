package com.mhy.sdk.utils;

import android.util.Base64;

/**
 * Created by Administrator on 2017/9/25.base64加密
 */

public class NSRBase64 {

    public static byte[] encodeToByte(String content) {
        byte[] result = null;
        try {
            result = Base64.encode(content.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public static byte[] encodeToByte(byte[] content) {
        byte[] result = null;
        try {
            result = Base64.encode(content, Base64.DEFAULT);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public static String encodeToString(byte[] content) {
        String result = null;
        try {
            result = Base64.encodeToString(content, Base64.DEFAULT);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    //base64加密
    public static String encodeToString(String content) {
        String result = null;
        try {
            result = Base64.encodeToString(content.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }


    public static String decodeToString(String content) {
        String result = null;
        try {
            byte[] byteResult = Base64.decode(content, Base64.DEFAULT);
            result = new String(byteResult, "UTF-8");
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public static String decodeToString(byte[] content) {
        String result = null;
        try {
            byte[] byteResult = Base64.decode(content, Base64.DEFAULT);
            result = new String(byteResult, "UTF-8");
        } catch (Exception e) {
            result = null;
        }
        return result;
    }


    public static byte[] decodeToByte(String content) {
        byte[] result = null;
        try {
            result = Base64.decode(content, Base64.DEFAULT);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public static byte[] decodeToByte(byte[] content) {
        byte[] result = null;
        try {
            result = Base64.decode(content, Base64.DEFAULT);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }
}
