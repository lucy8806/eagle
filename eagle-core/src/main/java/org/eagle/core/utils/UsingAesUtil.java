package org.eagle.core.utils;

import org.eagle.core.consts.CommonConst;

/**
 * @ClassName: UsingAesUtil
 * @Description: TODO
 * @Author lucy
 * @Date 2019/5/10 14:17
 * @Version 1.0
 */
public class UsingAesUtil {
    /***
     * 使用AES加密
     * @param password
     * @param salt
     * @return
     * @throws Exception
     */
    public static String encrypt(String password, String salt) throws Exception {
        return AesUtil.encrypt(Md5Util.getMD5(CommonConst.ZYD_SECURITY_KEY, salt), password);
    }

    /***
     * 使用AES解密
     * @param encryptPassword
     * @param salt
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptPassword, String salt) throws Exception {
        return AesUtil.decryt(Md5Util.getMD5(CommonConst.ZYD_SECURITY_KEY, salt), encryptPassword);
    }

    public static void main(String[] args) {
        try {
            String temp= UsingAesUtil.encrypt("123456","admin");
            System.out.printf(temp);
        }catch (Exception e){
        }

    }
}
