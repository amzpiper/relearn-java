package encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author guoyh
 */
public class KouLingDigestDemo {
    public static void main(String[] args) throws GeneralSecurityException, UnsupportedEncodingException {
        //AES加密可能会发现，密钥长度是固定的128/192/256位，而不是我们用WinZip/WinRAR那样，随便输入几位都可以。
        //因为对称加密算法决定了口令必须是固定长度，然后对明文进行分块加密。又因为安全需求，口令长度往往都是128位以上，即至少16个字符。
        //实际上用户输入的口令并不能直接作为AES的密钥进行加密（除非长度恰好是128/192/256位），并且用户输入的口令一般都有规律，
        //安全性远远不如安全随机数产生的随机口令。因此，用户输入的口令，通常还需要使用PBE算法，采用随机数杂凑计算出真正的密钥，再进行加密。
        //PBE的作用就是把用户输入的口令和一个安全随机的口令采用杂凑后计算出真正的密钥。

        Security.addProvider(new BouncyCastleProvider());

        //原文
        String message = "HelloWorld";
        //密码
        String password = "hello12345";
        //16 bytes随机Salt:
        byte[] salt = SecureRandom.getInstanceStrong().generateSeed(16);
        System.out.printf("salt:%032x\n", new BigInteger(1, salt));

        // 加密:
        byte[] data = message.getBytes(UTF_8);
        byte[] encrypted = encrypt(password, salt, data);
        System.out.println("encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        // 解密:
        byte[] decrypted = decrypt(password, salt, encrypted);
        System.out.println("decrypted: " + new String(decrypted, UTF_8));

        //使用PBE时，我们还需要引入BouncyCastle，并指定算法是PBEwithSHA1and128bitAES-CBC-BC。
        //观察代码，实际上真正的AES密钥是调用Cipher的init()方法时同时传入SecretKey和PBEParameterSpec实现的。
        //在创建PBEParameterSpec的时候，我们还指定了循环次数1000，循环次数越多，暴力破解需要的计算量就越大

        //如果我们把salt和循环次数固定，就得到了一个通用的“口令”加密软件。如果我们把随机生成的salt存储在U盘，
        //就得到了一个“口令”加USB Key的加密软件，它的好处在于，即使用户使用了一个非常弱的口令，没有USB Key仍然无法解密，
        //因为USB Key存储的随机数密钥安全性非常高。
    }

    //加密
    static byte[] encrypt(String password, byte[] salt, byte[] input) throws GeneralSecurityException {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory skeyFactory = SecretKeyFactory.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        SecretKey skey = skeyFactory.generateSecret(keySpec);
        PBEParameterSpec pbeps = new PBEParameterSpec(salt, 1000);
        Cipher cipher = Cipher.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        cipher.init(Cipher.ENCRYPT_MODE, skey, pbeps);
        return cipher.doFinal(input);
    }

    //解密
    static byte[] decrypt(String password, byte[] salt, byte[] input) throws GeneralSecurityException {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory skeyFactory = SecretKeyFactory.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        SecretKey skey = skeyFactory.generateSecret(keySpec);
        PBEParameterSpec pbeps = new PBEParameterSpec(salt, 1000);
        Cipher cipher = Cipher.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        cipher.init(Cipher.DECRYPT_MODE, skey, pbeps);
        return cipher.doFinal(input);
    }
}
