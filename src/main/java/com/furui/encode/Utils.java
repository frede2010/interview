package com.furui.encode;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.Crypt;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.codec.net.URLCodec;

import java.io.UnsupportedEncodingException;

/**
 * @author furui
 * @date 2019/3/14 0014
 */
public class Utils {
    public static void base64(){
        System.out.println("===============base64======================");
        Base64 base64 = new Base64();
        String s = base64.encodeToString("测试22222222222".getBytes());
        System.out.println(s);
        String s1 = new String(base64.decode(s));
        System.out.println(s1);
    }

    public static void md5(){
        System.out.println("===============MD5======================");
        String result = DigestUtils.md5Hex("测试");
        System.out.println(result);
    }

    public static void urlCodec() throws UnsupportedEncodingException, DecoderException {
        System.out.println("===============utf编码解码======================");
        URLCodec codec = new URLCodec();
        String s = codec.encode("测试", "utf-8");
        System.out.println(s);

        String s1 = codec.decode(s, "utf-8");
        System.out.println(s1);
    }

    public static void sha256() throws UnsupportedEncodingException, DecoderException {
        System.out.println("===============SHA256======================");
        String password = "qwer1234";
        String s = DigestUtils.sha256Hex(password);
        System.out.println(s);
    }

}
