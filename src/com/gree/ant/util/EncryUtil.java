package com.gree.ant.util;

import com.gree.ant.vo.Cbase000VO;
import org.nutz.lang.Lang;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * The type Encry util.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 3DES加密的封装,kv字符串转换,密码加密hash
 * @title EncryUtil
 * @createTime 2017 :10:25 04:10:21.
 */
public class EncryUtil {

    private static final Logger logger = LoggerFactory.getLogger(EncryUtil.class);

    public static String captcha_attr = "nutz_captcha";

    /**
     * Check captcha boolean.
     *
     * @param expected 期望获得的验证码
     * @param actual   实际上的验证码
     * @return the boolean
     * @description 验证输入的验证码是否相同
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:25 04:10:59.
     */
    public static boolean checkCaptcha(String expected, String actual) {
        if (expected == null || actual == null || actual.length() == 0 || actual.length() > 24)
            return false;
        return actual.equalsIgnoreCase(expected);
    }

    /**
     * Password encode string.
     *
     * @param password 密码
     * @param slat     密码的key
     * @return the string
     * @description 对密码进行‘SHA512’加密.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:25 04:10:39.
     */
    public static String passwordEncode(String password, String slat) {
        String str = slat + password + slat + password.substring(4);
        return Lang.digest("SHA-512", str);
    }

    private static final String Iv = "\0\0\0\0\0\0\0\0";
    private static final String Transformation = "DESede/CBC/PKCS5Padding";

    public static String _3DES_encode(byte[] key, byte[] data) {
        SecretKey deskey = new SecretKeySpec(key, "DESede");
        IvParameterSpec iv = new IvParameterSpec(Iv.getBytes());
        try {
            Cipher c1 = Cipher.getInstance(Transformation);
            c1.init(Cipher.ENCRYPT_MODE, deskey, iv);
            byte[] re = c1.doFinal(data);
            return Lang.fixedHexString(re);
        } catch (Exception e) {
            logger.info("3DES FAIL?", e);
            e.printStackTrace();
        }
        return null;
    }

    public static String _3DES_decode(byte[] key, byte[] data) {
        SecretKey deskey = new SecretKeySpec(key, "DESede");
        IvParameterSpec iv = new IvParameterSpec(Iv.getBytes());
        try {
            Cipher c1 = Cipher.getInstance(Transformation);
            c1.init(Cipher.DECRYPT_MODE, deskey, iv);
            byte[] re = c1.doFinal(data);
            return new String(re);
        } catch (Exception e) {
            logger.debug("BAD 3DES decode", e);
        }
        return null;
    }

    /**
     * Kv 2 map nut map.
     *
     * @param kv 多组键值对，单组中间用'='隔开,每组间用'，'隔开
     * @return NutMap
     * @description 对键值对进行封装
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:25 04:10:26.
     */
    public static NutMap kv2map(String kv) {
        NutMap re = new NutMap();
        if (kv == null || kv.length() == 0 || !kv.contains("="))
            return re;
        String[] tmps = kv.split(",");
        for (String tmp : tmps) {
            if (!tmp.contains("="))
                continue;
            String[] tmps2 = tmp.split("=", 2);
            re.put(tmps2[0], tmps2[1]);
        }
        return re;
    }

    /**
     * Random passwd string.
     *
     * @param cbase000VO the cbase 000 vo
     * @return the string
     * @description 生成随机密码
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:25 04:10:09.
     */
    public static String randomPasswd(Cbase000VO cbase000VO) {
        String passwd = R.sg(10).next();
        String slat = R.sg(48).next();
        logger.info(slat+"="+passwd);
        cbase000VO.setDSCA(slat);
        cbase000VO.setPAWD(passwordEncode(passwd, slat));
        return passwd;
    }

    public static byte[] hexstr2bytearray(String str) {
        byte[] re = new byte[str.length() / 2];
        for (int i = 0; i < re.length; i++) {
            int r = Integer.parseInt(str.substring(i*2, i*2+2), 16);
            re[i] = (byte)r;
        }
        return re;
    }

}
