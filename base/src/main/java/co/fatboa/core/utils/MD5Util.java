package co.fatboa.core.utils;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Author: hl
 * @Description: TODO
 * @Date: 18:17 2018/8/25
 * @Modified By: MD5工具类
 * @Version 1.0
 */
public class MD5Util {

    //工具类不允许被实例化
    private MD5Util() throws Exception {
        throw new Exception("异常");
    }

    /**
     * 获取文件MD5
     *
     * @param in
     * @return
     */
    public static String getFileMD5(InputStream in) {
        MessageDigest digest = null;
        byte buffer[] = new byte[8192];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件MD5
     *
     * @param bytes
     * @return
     */
    public static String getFileMD5(byte[] bytes) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(bytes);
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {

        }
    }

    /**
     * 添油加醋
     * 仅用于登录
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        byte[] md5Bytes = md5.digest(str.getBytes());
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
