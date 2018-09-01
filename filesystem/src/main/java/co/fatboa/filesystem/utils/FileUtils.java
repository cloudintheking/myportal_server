package co.fatboa.filesystem.utils;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Author: hl
 * @Description: TODO
 * @Date: 18:17 2018/8/25
 * @Modified By:
 * @Version 1.0
 */
public class FileUtils {
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
    public String getFileMD5(byte[] bytes) {
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
}
