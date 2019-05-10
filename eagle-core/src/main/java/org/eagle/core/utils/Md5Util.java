package org.eagle.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @ClassName: Md5Util
 * @Description: MD5加密工具类
 * @Author lucy
 * @Date 2019/5/10 14:02
 * @Version 1.0
 */
public class Md5Util {

    private static final Logger log = LoggerFactory.getLogger(Md5Util.class);

    /***
     * MD5加盐加密
     * @param string
     * @param salt
     * @return
     */
    public static String getMD5(String string, String salt) {
        return getMD5_32(string + salt);
    }

    /**
     * md5 加密后英文是大写
     * @param src
     * @return
     */
    public static String getMD5_32(String src) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] data = md.digest(src.getBytes());
            //遍历字节数组，取出高四位和低四位对应的字符,最终会得到长度为32的字符串
            for (byte b : data) {
                //高4位
                stringBuffer.append(chars[(b >> 4) & 0x0F]);
                //低4位
                stringBuffer.append(chars[b & 0x0F]);
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            // TODO: handle exception
            log.info("[大写MD5生成error]");
        }
        return null;
    }

    /***
     * md5 加密后英文是小写 加密与getMD5_32算法一样
     * @param str
     * @return
     */
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (Exception e) {
            log.info("[小写MD5生成error]");

        }
        return str;
    }
}
