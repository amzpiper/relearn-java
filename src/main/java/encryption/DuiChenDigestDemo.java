package encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 对称加密算法就是传统的用一个密码进行加密和解密。例如，我们常用的WinZIP和WinRAR对压缩包的加密和解密，就是使用对称加密算法：
 *
 * @author guoyh
 */
public class DuiChenDigestDemo {
    public static void main(String[] args) throws UnsupportedEncodingException, GeneralSecurityException {
        //从程序的角度看，所谓加密，就是这样一个函数，它接收密码和明文，然后输出密文：
        //secret = encrypt(key, message);
        //而解密则相反，它接收密码和密文，然后输出明文：
        //plain = decrypt(key, secret);

        //在软件开发中，常用的对称加密算法有：
        //算法	密钥长度	    工作模式	                填充模式
        //DES	56/64	    ECB/CBC/PCBC/CTR/...	NoPadding/PKCS5Padding/...
        //AES	128/192/256	ECB/CBC/PCBC/CTR/...	NoPadding/PKCS5Padding/PKCS7Padding/...
        //IDEA	128	        ECB	                    PKCS5Padding/PKCS7Padding/...
        //密钥长度直接决定加密强度，而工作模式和填充模式可以看成是对称加密算法的参数和格式选择。
        //Java标准库提供的算法实现并不包括所有的工作模式和所有填充模式，但是通常我们只需要挑选常用的使用就可以了。
        //最后注意，DES算法由于密钥过短，可以在短时间内被暴力破解，所以现在已经不安全了。

        // 原文:
        String message = "Hello, world!";
        System.out.println("Message: " + message);
        // 128位密钥 = 16 bytes Key:
        byte[] key = "1234567890abcdef".getBytes("UTF-8");
        // 加密:
        byte[] data = message.getBytes("UTF-8");
        byte[] encrypted = encrypt(key, data);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        // 解密:
        byte[] decrypted = decrypt(key, encrypted);
        System.out.println("Decrypted: " + new String(decrypted, "UTF-8"));
        //Java标准库提供的对称加密接口非常简单，使用时按以下步骤编写代码：
        //
        //根据算法名称/工作模式/填充模式获取Cipher实例；
        //根据算法名称初始化一个SecretKey实例，密钥必须是指定长度；
        //使用SerectKey初始化Cipher实例，并设置加密或解密模式；
        //传入明文或密文，获得密文或明文。
        //ECB模式是最简单的AES加密模式，它只需要一个固定长度的密钥，固定的明文会生成固定的密文，这种一对一的加密方式会导致安全性降低，
        //更好的方式是通过CBC模式，它需要一个随机数作为IV参数，这样对于同一份明文，每次生成的密文都不同：
        // 原文:
        String message1 = "Hello, world!";
        System.out.println("Message: " + message1);
        // 256位密钥 = 32 bytes Key:
        byte[] key1 = "1234567890abcdef1234567890abcdef".getBytes("UTF-8");
        // 加密:
        byte[] data1 = message1.getBytes("UTF-8");
        byte[] encrypted1 = encrypt1(key1, data1);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted1));
        // 解密:
        byte[] decrypted1 = decrypt1(key1, encrypted1);
        System.out.println("Decrypted: " + new String(decrypted1, "UTF-8"));
    }
    //AES加密模式
    // 加密:
    public static byte[] encrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    // 解密:
    public static byte[] decrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    //CBC模式
    // 加密:
    public static byte[] encrypt1(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        // CBC模式需要生成一个16 bytes的initialization vector:
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] iv = sr.generateSeed(16);
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivps);
        byte[] data = cipher.doFinal(input);
        // IV不需要保密，把IV和密文一起返回:
        return join(iv, data);
    }

    // 解密:
    public static byte[] decrypt1(byte[] key, byte[] input) throws GeneralSecurityException {
        // 把input分割成IV和密文:
        byte[] iv = new byte[16];
        byte[] data = new byte[input.length - 16];
        System.arraycopy(input, 0, iv, 0, 16);
        System.arraycopy(input, 16, data, 0, data.length);
        // 解密:
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivps);
        return cipher.doFinal(data);
    }

    public static byte[] join(byte[] bs1, byte[] bs2) {
        byte[] r = new byte[bs1.length + bs2.length];
        System.arraycopy(bs1, 0, r, 0, bs1.length);
        System.arraycopy(bs2, 0, r, bs1.length, bs2.length);
        return r;
    }
}
